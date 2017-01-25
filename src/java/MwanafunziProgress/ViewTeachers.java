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
public class ViewTeachers extends HttpServlet {
String output="";
dbConn conn;
String teacher_id,tsc_no,fullname,phone_no,national_id,responsibility;
String subject_details;
int position;
IDGenerator RGenerator;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          conn = new dbConn();
          RGenerator = new IDGenerator();
          position=0;
         
          output="<table id=\"example1\" class=\"table table-bordered table-striped\">";
output+="<thead>"
        + "<tr>"
        + "<th>Position</th>"
        + "<th>Teacher Name</th>"
        + "<th>TSC Number</th>"
        + "<th>Phone Number</th>"
        + "<th>National ID</th>"
        + "<th>Responsibility</th>"
        + "<th>Subjects.</th>"
        + "<th>Edit</th>"
        + "</tr>"
        + "</thead>";
output+="<tbody>";

    String getTeacher="SELECT teacher_details.teacher_id AS teacher_id,tsc_no,first_name,middle_name,last_name,phone_no,national_id,responsibility_name "
    + " FROM teacher_details LEFT JOIN responsibilities ON "
    + "teacher_details.responsibility_id=responsibilities.responsibility_id "
    + " WHERE teacher_details.is_active=?"
    + " ORDER BY first_name,middle_name,last_name"
    + "";
    conn.pst=conn.conn.prepareStatement(getTeacher);
    conn.pst.setInt(1, 1);
    
    conn.rs=conn.pst.executeQuery();
    while(conn.rs.next()){
        position++;
      teacher_id=conn.rs.getString(1); 
      tsc_no=conn.rs.getString(2);
      if(conn.rs.getString(4)!=null){
      fullname=conn.rs.getString(3)+" "+conn.rs.getString(4)+" "+conn.rs.getString(5);
      }
      else{
      fullname=conn.rs.getString(3)+" "+conn.rs.getString(5);    
      }
      phone_no=conn.rs.getString(6);
      national_id=conn.rs.getString(7);
      responsibility=conn.rs.getString(8);
      subject_details="";
      //GET SUBJECTS TAUGHT AND CLASSES
      
      String getSubClass="SELECT subjects.subject_label AS label,streams.stream AS stream "
      + "FROM teacher_subject LEFT JOIN subjects ON teacher_subject.subject_id=subjects.subject_id "
              + "LEFT JOIN teacher_classes ON teacher_subject.ts_id=teacher_classes.ts_id"
              + " LEFT JOIN streams ON teacher_classes.stream_id=streams.stream_id "
              + "WHERE teacher_subject.teacher_id=?";
      conn.pst1=conn.conn.prepareStatement(getSubClass);
      conn.pst1.setString(1, teacher_id);
      conn.rs1=conn.pst1.executeQuery();
      while(conn.rs1.next()){
          String subject_label=conn.rs1.getString(1);
          String stream=conn.rs1.getString(2);
          if(stream!=null){
     subject_details+=subject_label+" ["+stream+"]";
          }
      }
      
      output+="<tr>"
              + "<td>"+position+". </td>"
              + "<td>"+fullname+"</td>"
              + "<td>"+tsc_no+"</td>"
              + "<td>"+phone_no+"</td>"
              + "<td>"+national_id+"</td>"
              + "<td>"+responsibility+"</td>"
              + "<td>"+subject_details+"</td>"
              + "<td><a href=\"editParent.php?p="+teacher_id+"&id="+RGenerator.current_id()+"\" class=\"btn btn-block btn-warning\">Edit</a> </td>"
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
        Logger.getLogger(ViewTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ViewTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
