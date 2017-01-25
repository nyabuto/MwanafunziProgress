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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mwamb
 */
public class isBack extends HttpServlet {
String absentee_id,output,toDay,dOut;
dbConn conn;
IDGenerator rand;
int numDays;
String student_title="",current_time;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           conn = new dbConn();
           rand = new IDGenerator();
           
           absentee_id=request.getParameter("id");
           dOut=request.getParameter("dout");
//           
           toDay=rand.toDay();
           numDays=rand.getDays(dOut, toDay);
           System.out.println("num days : "+numDays);
            
           String update="UPDATE absentee_sheet SET date_in=?,days_out=?, is_back=? WHERE id=?";
           conn.pst=conn.conn.prepareStatement(update);
           conn.pst.setString(1, toDay);
           conn.pst.setInt(2, numDays);
           conn.pst.setInt(3, 1);
           conn.pst.setString(4, absentee_id);
           
           conn.pst.executeUpdate();
          
           //GET DETAILS AND SEND SMS NOTIFICATION====================>
           String getDetails="SELECT parent_details.first_name AS pfname, parent_details.middle_name as pmname," +
                "parent_details.last_name AS plname,parent_details.phone_number AS parent_phone," +
                "student_details.first_name AS sfname,student_details.middle_name AS smname," +
                "student_details.last_name AS slname, student_details.gender AS gender, "
                + "student_details.admission_no AS admission_no, absentee_sheet.date_out AS date_sent," +
                "reason_categories.reason AS reason_category FROM absentee_sheet " +
                "LEFT JOIN reason_categories ON absentee_sheet.reason_category_id=reason_categories.reason_id " +
                "LEFT JOIN student_classes ON absentee_sheet.sc_id=student_classes.id " +
                "LEFT JOIN student_details ON student_classes.student_id=student_details.student_id " +
                "LEFT JOIN parent_details ON student_details.parent_id=parent_details.parent_id " +
                "WHERE absentee_sheet.id=?";
           conn.pst=conn.conn.prepareStatement(getDetails);
           conn.pst.setString(1, absentee_id);
           conn.rs=conn.pst.executeQuery();
           if(conn.rs.next()){
              String  parent_name=conn.rs.getString("pfname")+" "+conn.rs.getString("pmname")+" "+conn.rs.getString("plname");
              String parent_phone=conn.rs.getString("parent_phone");
              String student_name=conn.rs.getString("sfname")+" "+conn.rs.getString("smname")+" "+conn.rs.getString("slname");
              String admission_no=conn.rs.getString("admission_no");
              String date_out=conn.rs.getString("date_sent");
              String reason_category=conn.rs.getString("reason_category");
              String gender=conn.rs.getString("gender");
              
              if(gender.equalsIgnoreCase("male")){
                  student_title="He";
              }
              else{
                  student_title="She";
              }
              current_time=rand.getTime();
              String message="Hi "+parent_name+", we have received back your child "+student_name+" adm_no: "+admission_no+" at "+current_time+"."
              + " "+student_title+" was sent home on "+date_out+".\nReason: "+reason_category+".";
               System.out.println("message : "+message+" to : "+parent_phone);
               
              // send message to parent
               SendSMS sendSMS = new SendSMS();
               sendSMS.ActionSend(message, parent_phone);
           }
           
           
   // response.sendRedirect("LeaveForm.jsp");
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
        Logger.getLogger(isBack.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(isBack.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(isBack.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(isBack.class.getName()).log(Level.SEVERE, null, ex);
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
