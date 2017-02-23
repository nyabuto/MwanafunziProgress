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
public class UpdateStream extends HttpServlet {
HttpSession session;
String stream_id,class_id,stream_name,output;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          dbConn conn = new dbConn();
          
          stream_id=request.getParameter("stream_id");
//          class_id=request.getParameter("class_id");
          stream_name=request.getParameter("stream_name");
            System.out.println("stream id: "+stream_id+" stream name : "+stream_name);
          String checker="SELECT stream_id FROM streams WHERE stream=? AND stream_id!=?";
          conn.pst=conn.conn.prepareStatement(checker);
//          conn.pst.setString(1, class_id);
          conn.pst.setString(1, stream_name);
          conn.pst.setString(2, stream_id);
          conn.rs=conn.pst.executeQuery();
          if(conn.rs.next()){
          output="<font color=\"\"><b>Error: Stream details already exist.</b></font>";    
          }
          else{
           String updator="UPDATE streams SET stream=? WHERE stream_id=?";
           conn.pst=conn.conn.prepareStatement(updator);
//           conn.pst.setString(1, class_id);
           conn.pst.setString(1, stream_name);
           conn.pst.setString(2, stream_id);
           conn.pst.executeUpdate();
           output="<font color=\"green\"><b>Stream details updated successfully.</b></font>";
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
        Logger.getLogger(UpdateStream.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(UpdateStream.class.getName()).log(Level.SEVERE, null, ex);
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
