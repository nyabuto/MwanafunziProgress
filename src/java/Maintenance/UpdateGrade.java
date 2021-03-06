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
public class UpdateGrade extends HttpServlet {
HttpSession session;
String points_id,points,grade;
String output;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          session=request.getSession();
            dbConn conn = new dbConn();
            
            points=request.getParameter("points");
            grade=request.getParameter("grade");
            points_id=request.getParameter("points_id");
            grade=grade.replace("_", "+");
            
            System.out.println("points id : "+points_id+" points="+points+" grade :"+grade);   
            //            check existence of similar record
        String checker="SELECT points_id FROM points WHERE (points=? OR grade=?) AND points_id!=?";
        conn.pst=conn.conn.prepareStatement(checker);
        conn.pst.setString(1, points);
        conn.pst.setString(2, grade);
        conn.pst.setString(3, points_id);

        conn.rs=conn.pst.executeQuery();
        if(conn.rs.next()){
        //    record exist
        output="<font color=\"red\"><b>ERROR: Grade already set.</b></font>";
        }
        else{
    
            String updator="UPDATE points SET points=?, grade=? WHERE points_id=?";
            conn.pst=conn.conn.prepareStatement(updator);
            conn.pst.setString(1, points);
            conn.pst.setString(2, grade);
            conn.pst.setString(3, points_id);
            
            conn.pst.executeUpdate();
            output="<font color=\"green\"><b>Grade updated successfully.</b></font>";
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
        Logger.getLogger(UpdateGrade.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(UpdateGrade.class.getName()).log(Level.SEVERE, null, ex);
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
