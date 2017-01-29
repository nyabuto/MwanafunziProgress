/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maintenance;

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
public class UpdateExams extends HttpServlet {
HttpSession session;
String output;
String exam_id,term_id,class_id,year;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           session=request.getSession();
           dbConn conn = new dbConn();
           
           year=request.getParameter("year");
           class_id=request.getParameter("class_id");
           term_id=request.getParameter("term_id");
           exam_id=request.getParameter("exam_id");
           
           String checkExistence="SELECT exam_id FROM exams WHERE year=? AND term_id=?";
         conn.pst=conn.conn.prepareStatement(checkExistence);
         conn.pst.setString(1, year);
         conn.pst.setString(2, term_id);
         
         conn.rs=conn.pst.executeQuery();
         if(conn.rs.next()){
          output="Similar record exist";   
         }
         else{
             
         
           String updater="UPDATE exams SET year=?,class_id=?,term_id=? WHERE exam_id=?";
           conn.pst=conn.conn.prepareStatement(updater);
           conn.pst.setString(1, year);
           conn.pst.setString(2, class_id);
           conn.pst.setString(3, term_id);
           conn.pst.setString(4, exam_id);
           
           conn.pst.executeUpdate();
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
        Logger.getLogger(UpdateExams.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(UpdateExams.class.getName()).log(Level.SEVERE, null, ex);
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
