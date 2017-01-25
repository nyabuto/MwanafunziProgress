/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Savers;

import MwanafunziProgress.DatabaseConstants;
import MwanafunziProgress.IDGenerator;
import SMS.SendSMS;
import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
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
public class saveLeaveForm extends HttpServlet {
    dbConn conn;
    HttpSession session;
    int output;
    String expected_date,reason_id,description,id,sc_id,date_out;
    int current_year,term_id;
    IDGenerator rand;
    String student_id;
    DatabaseConstants dCons;
    String parent_name,parent_phone,student_name,admission_no,gender,student_title;
    String reason_category,current_time;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           conn = new dbConn();
           session=request.getSession();
           rand = new IDGenerator();
           dCons = new DatabaseConstants();
           parent_name=parent_phone=student_name=admission_no=gender=student_title=reason_category="";
            System.out.println("called to save");
           if(session.getAttribute("student_id")!=null){
            student_id=session.getAttribute("student_id").toString();
               System.out.println("got student id:"+student_id);
           current_year=rand.getCurrentYear();
           date_out=rand.toDay();
           id=rand.current_id();
           term_id=dCons.getTerm(conn, date_out);
           
           expected_date=request.getParameter("edate");
           reason_id=request.getParameter("reason_id");
           description=request.getParameter("description");
           sc_id=getSCID(conn, student_id,current_year);
           if(!checkExistence(conn,sc_id)){
//            add exit as new entry

String insert="INSERT INTO absentee_sheet (id,sc_id,term_id,reason_category_id,date_out,expected_return_date,reason) "
        + "VALUES(?,?,?,?,?,?,?)";
conn.pst=conn.conn.prepareStatement(insert);
conn.pst.setString(1, id);
conn.pst.setString(2, sc_id);
conn.pst.setInt(3, term_id);
conn.pst.setString(4, reason_id);
conn.pst.setString(5, date_out);
conn.pst.setString(6, expected_date);
conn.pst.setString(7, description);

conn.pst.executeUpdate();
output=1;
// send message to parents 

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
              
              String getReason="SELECT reason FROM reason_categories WHERE reason_id='"+reason_id+"'";
              conn.rs=conn.st.executeQuery(getReason);
              while(conn.rs.next()){
                 reason_category=conn.rs.getString(1);
                 }
              current_time=rand.getTime();
                System.out.println("current time : "+current_time);
               
                String message="Hi "+parent_name+", we have sent home your "+student_title+" "+student_name+" adm_no: "+admission_no+" on "+date_out+" "
              + "at "+current_time+".\nReason: "+reason_category+".";
               System.out.println("message : "+message+" to : "+parent_phone);
               
              // send message to parent
               SendSMS sendSMS = new SendSMS();
               sendSMS.ActionSend(message, parent_phone);
               }
           else{
               output=0;
           }
        
           }
           else{
               student_id="";//student id not found
               output=2;
           }   
            out.println(output);
        }
    }

    private boolean checkExistence(dbConn conn,String sc_id) throws SQLException{
        boolean exist=true;
        
        String checkExist="SELECT COUNT(id) FROM absentee_sheet WHERE sc_id=? && is_back=?";
       conn.pst=conn.conn.prepareStatement(checkExist);
    conn.pst.setString(1, sc_id);
    conn.pst.setInt(2, 0);
     
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        exist=conn.rs.getInt(1)>0;
    }
    else{
        exist=false;
    }
        return exist;
    }
    
    private String getSCID(dbConn conn, String student_id,int current_year) throws SQLException{
    String scid=""; 
    String getSc = "SELECT id FROM student_classes WHERE student_id=? AND year=?";
    conn.pst=conn.conn.prepareStatement(getSc);
    conn.pst.setString(1, student_id);
    conn.pst.setInt(2, current_year);
    
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
        scid=conn.rs.getString(1);
    }
    else{
        scid="";
    }
    
    return scid;
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
            Logger.getLogger(saveLeaveForm.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(saveLeaveForm.class.getName()).log(Level.SEVERE, null, ex);
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
