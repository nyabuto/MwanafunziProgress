/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Savers;

import MwanafunziProgress.IDGenerator;
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
public class addStudent extends HttpServlet {
String output="";
String admission_no,first_name,middle_name,last_name,dob,gender,parent_id;
String is_physically_challenged,stream_id,student_id;
int current_year;
dbConn conn;
HttpSession session;
IDGenerator rand;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        session=request.getSession();
      conn = new dbConn();
      rand = new IDGenerator();
      
      student_id=rand.current_id();
      current_year=Calendar.getInstance().get(Calendar.YEAR);
      admission_no=request.getParameter("admission_number");
      first_name=request.getParameter("first_name");
      middle_name=request.getParameter("middle_name");
      last_name=request.getParameter("last_name");
      dob=request.getParameter("date_of_birth");
      gender=request.getParameter("gender");
     parent_id=request.getParameter("parent_id");
     is_physically_challenged=request.getParameter("is_physically_challenged");
     stream_id=request.getParameter("stream_id");
     
      if(!checkExistence(conn,admission_no)){
       // add this student.
       String inserter="INSERT INTO student_details(admission_no,first_name,middle_name,last_name,gender,date_of_birth,year_joined,parent_id,is_physically_challenged,student_id)"
               + "VALUES(?,?,?,?,?,?,?,?,?,?)";
       conn.pst=conn.conn.prepareStatement(inserter);
       conn.pst.setString(1, admission_no);
       conn.pst.setString(2, first_name);
       conn.pst.setString(3, middle_name);
       conn.pst.setString(4, last_name);
       conn.pst.setString(5, gender);
       conn.pst.setString(6, dob);
       conn.pst.setInt(7, current_year);
       conn.pst.setString(8, parent_id);
       conn.pst.setString(9, is_physically_challenged);
       conn.pst.setString(10, student_id);
       conn.pst.executeUpdate();
       
       
       //update student classes
       student_id=getStudentID(conn, admission_no);
       String id=rand.current_id();
       String inserterSC="INSERT INTO student_classes (student_id,stream_id,year,id) VALUES (?,?,?,?)";
       conn.pst1 = conn.conn.prepareStatement(inserterSC);
       conn.pst1.setString(1, student_id);
       conn.pst1.setString(2, stream_id);
       conn.pst1.setInt(3, current_year);
       conn.pst1.setString(4,id);
       conn.pst1.executeUpdate();
       
       output="1";
      }
      else{
        output="0";   
      }
      out.println(output);
        }
     //redirect to the next page.
      session.setAttribute("student_added", output);
      response.sendRedirect("addStudent.jsp");
    }
    
    private String getStudentID(dbConn conn,String admission_no) throws SQLException{
        String s_id="";
        String getID="SELECT student_id FROM student_details WHERE admission_no=?";
        conn.pst=conn.conn.prepareStatement(getID);
        conn.pst.setString(1, admission_no);
        conn.rs=conn.pst.executeQuery();
        while(conn.rs.next()){
        s_id=conn.rs.getString(1);
        }
        
        return s_id;
    }
   private boolean checkExistence(dbConn conn, String admission_no) throws SQLException{
    boolean exist=true;
    String checker="SELECT count(student_id) FROM student_details WHERE admission_no=?";
    conn.pst = conn.conn.prepareStatement(checker);
    conn.pst.setString(1, admission_no);
    conn.rs=conn.pst.executeQuery();
    if(conn.rs.next()){
    exist= conn.rs.getInt(1)>0;
    }
   
    return exist;
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
        Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
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
