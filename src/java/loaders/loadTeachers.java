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
public class loadTeachers extends HttpServlet {
    dbConn conn;
    HttpSession session;
    String output;
    String subject_id,stream_id;
    String teacher_id,fullname;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          conn = new dbConn();
          subject_id=request.getParameter("subject_id");
          stream_id=request.getParameter("stream_id");
          
          output="<option value=\"\">Choose Teacher</option>";
          String getTeachers="SELECT teacher_details.teacher_id AS t_id,teacher_details.first_name AS fname,teacher_details.middle_name AS mname,teacher_details.last_name AS lname "
                  + "FROM teacher_details LEFT JOIN teacher_subject ON teacher_details.teacher_id=teacher_subject.teacher_id "
                  + "LEFT JOIN teacher_classes ON teacher_subject.ts_id=teacher_classes.ts_id "
                  + "WHERE teacher_classes.stream_id=? AND teacher_subject.subject_id=?";
          conn.pst=conn.conn.prepareStatement(getTeachers);
          conn.pst.setString(1, stream_id);
          conn.pst.setString(2, subject_id);
          
          conn.rs=conn.pst.executeQuery();
          while(conn.rs.next()){
          teacher_id=conn.rs.getString(1);
          fullname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
         output+="<option value=\""+teacher_id+"\">"+fullname+"</option>";
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
            Logger.getLogger(loadTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadTeachers.class.getName()).log(Level.SEVERE, null, ex);
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
