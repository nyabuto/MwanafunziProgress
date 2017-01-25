/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Savers;

import MwanafunziProgress.IDGenerator;
import SMS.SendSMS;
import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mwamb
 */
public class SaveFees extends HttpServlet {
    HttpSession session;
    String[] sc_ids;
    int position;
    String sc_id,mode_id,amount,transaction_id,pay_id,output;
    String parent_name,parent_phone,student_name,admission_no,gender,student_title;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           session=request.getSession();
            dbConn conn = new dbConn();
            IDGenerator rand = new IDGenerator();
            pay_id=rand.current_id();
            
            output="";
            parent_name=parent_phone=student_name=admission_no=gender=student_title="";
            position=Integer.parseInt(request.getParameter("position"));
            mode_id=request.getParameter("mode_id");
            amount=request.getParameter("amount");
            transaction_id=request.getParameter("transaction_id");
            
            sc_id="";
            if(session.getAttribute("sc_ids")!=null){
             sc_ids=session.getAttribute("sc_ids").toString().split(",");
              if(position<=sc_ids.length){
             sc_id=sc_ids[position-1];
             
             //update records
             String inserter="INSERT INTO fees_paid (pay_id,sc_id,amount,mode_id,transaction_id) VALUES(?,?,?,?,?)";
             conn.pst=conn.conn.prepareStatement(inserter);
             conn.pst.setString(1, pay_id);
             conn.pst.setString(2, sc_id);
             conn.pst.setString(3, amount);
             conn.pst.setString(4, mode_id);
             conn.pst.setString(5, transaction_id);
             conn.pst.executeUpdate();
             output="Fees Saved Successfuly.";
             
             //send sms to parent
             
            String getDetails="SELECT parent_details.first_name AS pfname, parent_details.middle_name as pmname," +
                "parent_details.last_name AS plname,parent_details.phone_number AS parent_phone," +
                "student_details.first_name AS sfname,student_details.middle_name AS smname," +
                "student_details.last_name AS slname, student_details.gender AS gender, "
                + "student_details.admission_no AS admission_no FROM  student_classes "
                + "LEFT JOIN student_details ON student_classes.student_id=student_details.student_id " +
                "LEFT JOIN parent_details ON student_details.parent_id=parent_details.parent_id " +
                "WHERE student_classes.id=?";

conn.pst=conn.conn.prepareStatement(getDetails);
           conn.pst.setString(1, sc_id);
           conn.rs=conn.pst.executeQuery();
           if(conn.rs.next()){

              parent_name=conn.rs.getString("pfname")+" "+conn.rs.getString("pmname")+" "+conn.rs.getString("plname");
              parent_phone=conn.rs.getString("parent_phone");
              student_name=conn.rs.getString("sfname")+" "+conn.rs.getString("smname")+" "+conn.rs.getString("slname");
              admission_no=conn.rs.getString("admission_no");
              gender=conn.rs.getString("gender");
            }
              if(gender.equalsIgnoreCase("male")){
                  student_title="boy";
              }
              else{
                  student_title="girl";
              }
              
              
              String message="Hi "+parent_name+", Fees payments worth Ksh."+amount+"/= has been made to student account \n["+student_name+" adm_no: "+admission_no+"]."
                      + "\nThanks for continued support.";
               System.out.println("message : "+message+" to : "+parent_phone);
               
                // send message to parent
               SendSMS sendSMS = new SendSMS();
               sendSMS.ActionSend(message, parent_phone);
              
              
//              end of sending sms
             
            }
            else{
//            exit
            output="Failed to get Student.";
            }
            }
            else{
            output="ERROR: UNKNOWN STUDENT";    
            }
            out.println(output);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaveFees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaveFees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
