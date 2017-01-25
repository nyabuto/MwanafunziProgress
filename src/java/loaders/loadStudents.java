/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loaders;

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
public class loadStudents extends HttpServlet {
String output="";
String class_id,stream_id,query,name;
int current_year;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            dbConn conn = new dbConn();
            session=request.getSession();
            class_id=stream_id=query="";
            
            current_year=Calendar.getInstance().get(Calendar.YEAR);
            output="<option value=\"\">Choose Student</option>";
            if(session.getAttribute("class_id")!=null){
                class_id=session.getAttribute("class_id").toString();
            }
            if(session.getAttribute("stream_id")!=null){
                stream_id=session.getAttribute("stream_id").toString();
            }
            if(class_id.equals("")){
                query="SELECT student_details.student_id AS student_id,admission_no,first_name,middle_name,last_name "
                +"FROM student_details "
                + "LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id "
                + "WHERE is_active=? && year=? ORDER BY first_name,middle_name,last_name";
              conn.pst = conn.conn.prepareStatement(query);
              conn.pst.setInt(1, 1);
              conn.pst.setInt(2, current_year);
            }
            else if(stream_id.equals("")){
             query="SELECT student_details.student_id AS student_id,admission_no,first_name,middle_name,last_name "
            +"FROM student_details LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id"
            + " LEFT JOIN streams ON student_classes.stream_id=streams.stream_id "
            + "WHERE is_active=? && streams.class_id=? && year=? ORDER BY first_name,middle_name,last_name";   
             conn.pst = conn.conn.prepareStatement(query);
             conn.pst.setInt(1, 1);
             conn.pst.setString(2, class_id);
             conn.pst.setInt(3, current_year);
            }
            else if(!stream_id.equals("")){
             query="SELECT student_details.student_id AS student_id,admission_no,first_name,middle_name,last_name "
            +"FROM student_details LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id"
            + " LEFT JOIN streams ON student_classes.stream_id=streams.stream_id "
            + "WHERE is_active=? && streams.stream_id=? && year=? ORDER BY first_name,middle_name,last_name";     
             conn.pst = conn.conn.prepareStatement(query);
              conn.pst.setInt(1, 1);
              conn.pst.setString(2, stream_id);
              conn.pst.setInt(3, current_year);
            }
            conn.rs=conn.pst.executeQuery();
            while(conn.rs.next()){
                String student_id=conn.rs.getString(1);
                String national_id=conn.rs.getString(2);
                if(conn.rs.getString(4)==null){
                name=conn.rs.getString(3)+" "+conn.rs.getString(5);
                }
                else{
                name=conn.rs.getString(3)+" "+conn.rs.getString(4)+" "+conn.rs.getString(5);
                }
                output+="<option value=\""+student_id+"\">"+name+" ("+national_id+")</option>";
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
        Logger.getLogger(loadStudents.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadStudents.class.getName()).log(Level.SEVERE, null, ex);
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
