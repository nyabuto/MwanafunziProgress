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
public class UpdateSchoolTerms extends HttpServlet {
HttpSession session;
String term_name_id,year,start_date,end_date,term_id;
String output;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           session = request.getSession();
           dbConn conn = new dbConn();
           
           term_id=request.getParameter("term_id");
           year=request.getParameter("year");
           start_date=request.getParameter("start_date");
           end_date=request.getParameter("end_date");
           term_name_id=request.getParameter("term_name_id");
           
           String checker="SELECT term_id FROM school_terms WHERE ((year=? AND term_name_id=?) "
                   + "OR (? BETWEEN start_date AND end_date) OR (? BETWEEN start_date AND end_date)) AND term_id!=?";
           conn.pst=conn.conn.prepareStatement(checker);
           conn.pst.setString(1, year);
           conn.pst.setString(2, term_name_id);
           conn.pst.setString(3, start_date);
           conn.pst.setString(4, end_date);
           conn.pst.setString(5, term_id);
           
           conn.rs=conn.pst.executeQuery();
           if(conn.rs.next()){
               output="School term dates or name conflict";
           }
           else{
               String updator="UPDATE school_terms SET term_name_id=?,year=?,start_date=?,end_date=? WHERE term_id=?";
               conn.pst=conn.conn.prepareStatement(updator);
               conn.pst.setString(1, term_name_id);
               conn.pst.setString(2, year);
               conn.pst.setString(3, start_date);
               conn.pst.setString(4, end_date);
               conn.pst.setString(5, term_id);
               
               conn.pst.executeUpdate();
               output="School term dates updated successfully.";
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
        Logger.getLogger(UpdateSchoolTerms.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(UpdateSchoolTerms.class.getName()).log(Level.SEVERE, null, ex);
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
