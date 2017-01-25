/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Savers;

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

/**
 *
 * @author mwamb
 */
public class SaveExams extends HttpServlet {
String output="";
String exam_id,student_id,subject_id,stream_id,marks,teacher_id;
String sc_id;
int current_year,numSubjects;
dbConn conn;
int totalMarks,totalPoints;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            conn = new dbConn();
            current_year=Calendar.getInstance().get(Calendar.YEAR); 
           totalMarks=totalPoints=0;
           
          exam_id=request.getParameter("exam_id");
          student_id=request.getParameter("student_id");
          stream_id=request.getParameter("stream_id");
          teacher_id=request.getParameter("teacher_id");
          sc_id=getStudentSCID(conn, student_id,stream_id,current_year);
          
          numSubjects=Integer.parseInt(request.getParameter("numSubjects"));
          
          for(int i=0;i<numSubjects;i++){
          subject_id=request.getParameter("subject_id"+i);
          marks=request.getParameter("marks"+i); 
         // Save to the database the individual subjects 
         String addScore="INSERT INTO student_exams(sc_id,teacher_id,subject_id,marks) VALUES(?,?,?,?)";
         conn.pst=conn.conn.prepareStatement(addScore);
         conn.pst.setString(1, sc_id);
         conn.pst.setString(2, teacher_id);
         conn.pst.setString(3, subject_id);
         conn.pst.setString(4, marks);
         conn.pst.executeUpdate();
         
         //sum score and points
         totalMarks+=Integer.parseInt(marks);
         totalPoints+=getPoint(conn,Integer.parseInt(marks));
          }
          // update the totals table
          String updateTotals="INSERT INTO exam_totals (sc_id,exam_id,total_marks,total_points) VALUES(?,?,?,?)";
          conn.pst=conn.conn.prepareStatement(updateTotals);
          conn.pst.setString(1, sc_id);
          conn.pst.setString(2, exam_id);
          conn.pst.setInt(3, totalMarks);
          conn.pst.setInt(4, totalPoints);
          
          conn.pst.executeUpdate();
        }
     
        response.sendRedirect("AddExams.jsp");
    }
    
    
    private int getPoint(dbConn conn, int marks) throws SQLException{
    int points=0;

    String getPoints="SELECT points FROM marks_ranges WHERE ? BETWEEN min AND max";
    conn.pst.setInt(1, marks);
    conn.pst=conn.conn.prepareStatement(getPoints);
    conn.rs=conn.pst.executeQuery();
    while(conn.rs.next()){
        points=conn.rs.getInt(1);
    }

   return points;    
    }
     private String getStudentSCID(dbConn conn, String student_id,String stream_id,int year) throws SQLException{
         String sc=""; 
         String getSC="SELECT id FROM student_classes WHERE student_id=? AND stream_id=? AND year=?";
         conn.pst=conn.conn.prepareStatement(getSC);
         conn.pst.setString(1, student_id);
         conn.pst.setString(2, stream_id);
         conn.pst.setInt(3, year);
         
         conn.rs=conn.pst.executeQuery();
         while(conn.rs.next()){
         sc=conn.rs.getString(1);
         }
         
         return sc;
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
        Logger.getLogger(SaveExams.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SaveExams.class.getName()).log(Level.SEVERE, null, ex);
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
