/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MwanafunziProgress;

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
public class ViewStudents extends HttpServlet {
String output="";
dbConn conn;
int current_year,position;
IDGenerator RGenerator;
String fullname,parentname;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          conn = new dbConn();
           RGenerator= new IDGenerator();
          
         current_year=Calendar.getInstance().get(Calendar.YEAR); 
          
output="<table id=\"example1\" class=\"table table-bordered table-striped\">";
output+="<thead>"
        + "<tr>"
        + "<th>Position</th>"
        + "<th>Student Name</th>"
        + "<th>Admission No</th>"
        + "<th>Gender</th>"
        + "<th>Stream</th>"
        + "<th>Parent Name</th>"
        + "<th>Edit</th>"
        + "</tr>"
        + "</thead>";
        output+="<tbody>";

          position=0;
         String getStudent="SELECT student_details.student_id AS student_id, student_details.first_name AS sfname,student_details.middle_name AS smname,"
         + "student_details.last_name AS slname,student_details.admission_no AS admission_no,student_details.gender AS sGender,parent_details.first_name AS pfname,"
         + "parent_details.middle_name AS pmname,parent_details.last_name AS plname,streams.stream stream_label "
         + "FROM  student_details LEFT JOIN parent_details ON student_details.parent_id=parent_details.parent_id "
         + "LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id "
         + "LEFT JOIN streams ON student_classes.stream_id=streams.stream_id "
         + "WHERE student_classes.year=?" ;
         conn.pst=conn.conn.prepareStatement(getStudent);
         conn.pst.setInt(1, current_year);
          conn.rs=conn.pst.executeQuery();
          while(conn.rs.next()){
              String student_id=conn.rs.getString(1);
              if(conn.rs.getString(3)==null){
              fullname=conn.rs.getString(2)+" "+conn.rs.getString(4);
               }
              else{
              fullname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
              }
              String admission_no = conn.rs.getString(5);
              String gender=conn.rs.getString(6);
              if(conn.rs.getString(8)!=null){
              parentname=conn.rs.getString(7)+" "+conn.rs.getString(8)+" "+conn.rs.getString(9);
              }
              else{
              parentname=conn.rs.getString(7)+" "+conn.rs.getString(9);
              }
              String student_class = conn.rs.getString(10);
              
              position++;
              output+="<tr>"
                      + "<td>"+position+". </td>"
                      + "<td>"+fullname+"</td>"
                      + "<td>"+admission_no+"</td>"
                      + "<td>"+gender+"</td>"
                      + "<td>"+student_class+"</td>"
                      + "<td>"+parentname+"</td>"
                      + "<td><a href=\"#?p="+student_id+"&id="+RGenerator.current_id()+"\" class=\"btn btn-block btn-warning\">Edit</a> </td>"
                      + "</tr>";
          }
           output+="</tbody>";

 output+="</table>";
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
        Logger.getLogger(ViewStudents.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ViewStudents.class.getName()).log(Level.SEVERE, null, ex);
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
