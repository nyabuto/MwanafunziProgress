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
public class ViewParents extends HttpServlet {
String output="";
dbConn conn;
String parent_id,first_name,middle_name,last_name,gender,national_id,
phone_number,physical_address,relationship,occupation;
int position;
String student_details,parentName;
int current_year,studentCounter;
IDGenerator RGenerator;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           conn = new dbConn();
           position=studentCounter=0;
           RGenerator= new IDGenerator();
           
           current_year=Calendar.getInstance().get(Calendar.YEAR);
           output="<table id=\"example1\" class=\"table table-bordered table-striped\">";
output+="<thead>"
        + "<tr>"
        + "<th>Position</th>"
        + "<th>Parent Name</th>"
        + "<th>Gender</th>"
         + "<th>National ID</th>"
        + "<th>Phone Number</th>"
         + "<th>Physical Address</th>"
         + "<th>Relationship</th>"
        + "<th>Occupation</th>"
        + "<th style=\"width:200px;\">Student (s)</th>"
        + "<th>Edit</th>"
        + "</tr>"
        + "</thead>";
output+="<tbody>";
           
           String getAllParents="SELECT parent_id,first_name,middle_name,"
           + "last_name,gender,national_id,phone_number,physical_address,"
           + "relationship_name,occupation_name FROM parent_details LEFT JOIN parent_relationships "
           + "ON parent_details.relationship_id=parent_relationships.relationship_id "
           + "LEFT JOIN occupation ON parent_details.occupation_id=occupation.occupation_id "
           + "WHERE is_active=? "
           + "ORDER BY first_name,middle_name,last_name ASC";
        conn.pst=conn.conn.prepareStatement(getAllParents);
        conn.pst.setInt(1, 1);
        conn.rs=conn.pst.executeQuery();
        while(conn.rs.next()){
            position++;
            studentCounter=0;
         parent_id = conn.rs.getString(1);
         first_name = conn.rs.getString(2);
         middle_name = conn.rs.getString(3);
         last_name = conn.rs.getString(4);
         if(middle_name==null){middle_name="";}
         parentName=first_name+" "+middle_name+" "+last_name;
         gender = conn.rs.getString(5);
         national_id = conn.rs.getString(6);
         phone_number = conn.rs.getString(7);
         physical_address = conn.rs.getString(8);
         relationship = conn.rs.getString(9);
         occupation = conn.rs.getString(10);
        student_details="";
         //GET STUDENT
         
         String getStudent="SELECT first_name,middle_name,last_name,admission_no, stream FROM student_details "
                 + "LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id "
                 + "LEFT JOIN streams ON student_classes.stream_id=streams.stream_id "
                 + "WHERE student_classes.year=? && student_details.parent_id=?";
         conn.pst1=conn.conn.prepareStatement(getStudent);
         conn.pst1.setInt(1, current_year);
         conn.pst1.setString(2, parent_id);
         
         conn.rs1=conn.pst1.executeQuery();
         while(conn.rs1.next()){
             studentCounter++;
             String student_name;
             if(conn.rs1.getString(2)==null){
              student_name=conn.rs1.getString(1)+" "+conn.rs1.getString(3);     
             }
             else{
             student_name=conn.rs1.getString(1)+" "+conn.rs1.getString(2)+" "+conn.rs1.getString(3);
             }
             
             String admission_no=conn.rs1.getString(4);
             String stream=conn.rs1.getString(5);
           student_details+=student_name+", "+admission_no+"["+stream+"]<br>";  
             
         }
         if(studentCounter==0){
             student_details="<font color=\"red\">No Student</font>";
         }
         output+="<tr>"
                 + "<td>"+position+"</td>"
                 + "<td>"+parentName+"</td>"
                 + "<td>"+gender+"</td>"
                 + "<td>"+national_id+"</td>"
                 + "<td>"+phone_number+"</td>"
                 + "<td>"+physical_address+"</td>"
                 + "<td>"+relationship+"</td>"
                 + "<td>"+occupation+"</td>"
                 + "<td>"+student_details+"</td>"
                 + "<td><a href=\"#?p="+parent_id+"&id="+RGenerator.current_id()+"\" class=\"btn btn-block btn-warning\">Edit</a> </td>"
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
        Logger.getLogger(ViewParents.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ViewParents.class.getName()).log(Level.SEVERE, null, ex);
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
