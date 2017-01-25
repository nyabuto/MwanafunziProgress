/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

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
public class login extends HttpServlet {
HttpSession session;
String username,password;
String error;
String user_id,full_name,link_id,type_id;
String redirect_to="",output="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            dbConn conn = new dbConn();
            session = request.getSession();
            
            username=request.getParameter("username");
            password=request.getParameter("password");
            output="0";
            user_id=full_name=link_id=type_id="";
            
            if(username.equals("") || password.equals("")){
                error="Enter username or password";
            }
            else{
                //check in the database
                String query="SELECT user_id,link_id,type_id FROM users WHERE username=? AND password=?";
                conn.pst=conn.conn.prepareStatement(query);
                conn.pst.setString(1, username);
                conn.pst.setString(2, password);
                conn.rs=conn.pst.executeQuery();
                if(conn.rs.next()){
                 user_id = conn.rs.getString("user_id");
                 link_id = conn.rs.getString("link_id");
                 type_id = conn.rs.getString("type_id");
                    System.out.println("here it is : ");
                }
                else{
                    
                }
                
                if(type_id.equals("1")){
                   redirect_to="Exams.jsp";
                   output="1";
                }
                else{
                 redirect_to="index.jsp";   
                }
            }
           session.setAttribute("user_id", user_id);
           session.setAttribute("redirect_to", redirect_to);
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
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
