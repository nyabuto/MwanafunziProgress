/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sessions;

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
public class SetStudentSession extends HttpServlet {
HttpSession session;
String student_id;
int current_year;
String student_details;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          session=request.getSession();
          dbConn conn = new dbConn();
          current_year=Calendar.getInstance().get(Calendar.YEAR);
          student_id=request.getParameter("student_id");
          session.setAttribute("student_id", student_id);
         String getStudentName="SELECT first_name,middle_name,last_name,admission_no,streams.stream AS stream, streams.class_id AS class FROM "
                 + "student_details LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id"
                 + " LEFT JOIN streams ON streams.stream_id=student_classes.stream_id "
                 + "WHERE student_details.student_id=? && student_classes.year=?"; 
         conn.pst=conn.conn.prepareStatement(getStudentName);
         conn.pst.setString(1, student_id);
         conn.pst.setInt(2, current_year);
         conn.rs=conn.pst.executeQuery();
         if(conn.rs.next()){
         student_details=conn.rs.getString(1)+" "+conn.rs.getString(2)+" "+conn.rs.getString(3)+", <b>Adm No."+conn.rs.getString(4)+"</b> ["+conn.rs.getString(5)+"] ";
         }
         else{
             student_details="No student found";
         }
         out.println(student_details);
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
        Logger.getLogger(SetStudentSession.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SetStudentSession.class.getName()).log(Level.SEVERE, null, ex);
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
