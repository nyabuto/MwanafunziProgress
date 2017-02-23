/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Savers;

import MwanafunziProgress.IDGenerator;
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
public class SaveUser extends HttpServlet {
HttpSession session;
String user_id,username,password,link_id,type_id,output;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session = request.getSession();
            dbConn conn = new dbConn();
            IDGenerator rand = new IDGenerator();
            
            username=request.getParameter("username");
            password=request.getParameter("password");
            link_id=request.getParameter("link_id");
            type_id=request.getParameter("type_id");
            
//            checker
            String checker="SELECT id FROM users WHERE username=? OR (link_id=? AND type_id=?)";
            conn.pst=conn.conn.prepareStatement(checker);
            conn.pst.setString(1, username);
            conn.pst.setString(2, link_id);
            conn.pst.setString(3, type_id);
            
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
                output="User already exist";
            }
            else{
              user_id=rand.current_id();
              String inserter="INSERT INTO users (user_id,username,password,link_id,type_id) VALUES (?,?,?,?,?)";
              conn.pst=conn.conn.prepareStatement(inserter);
              conn.pst.setString(1, user_id);
              conn.pst.setString(2, username);
              conn.pst.setString(3, password);
              conn.pst.setString(4, link_id);
              conn.pst.setString(5, type_id);
              conn.pst.executeUpdate();
                output="User added successfully.";
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
        Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
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
