/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maintenance;

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
public class NewClasses extends HttpServlet {
HttpSession session;
String output;
String class_name,id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session = request.getSession();
            dbConn conn = new dbConn();
            class_name=request.getParameter("class_name");
            
//            check existence
if(!class_name.equals("") || class_name.length()<=1){
String checker="SELECT id FROM classes WHERE name=?";
conn.pst=conn.conn.prepareStatement(checker);
conn.pst.setString(1, class_name);
     conn.rs=conn.pst.executeQuery();
     if(conn.rs.next()){
         output="<font color=\"red\"><b>Class already exist.</b></font>";
         
     }
     else{
         String adder="INSERT INTO classes (name) VALUES(?)";
         conn.pst=conn.conn.prepareStatement(adder);
         conn.pst.setString(1, class_name);
         
         conn.pst.executeUpdate();
         output="<font color=\"green\"><b>Class added successfully.</b></font>";
     }
}
else{
    output="<font color=\"red\"><b>Error: No class entered or length is unacceptable.</b></font>";
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
        Logger.getLogger(NewClasses.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(NewClasses.class.getName()).log(Level.SEVERE, null, ex);
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
