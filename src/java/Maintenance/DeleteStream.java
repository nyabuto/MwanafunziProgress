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
public class DeleteStream extends HttpServlet {
    HttpSession session;
    String stream_id,output;
    int student_counter,teacher_counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           dbConn conn = new dbConn();
           
           stream_id=request.getParameter("stream_id");
          student_counter=teacher_counter=0; 
//           check students
            String checker_student="SELECT count(id) from student_classes WHERE stream_id=?";
            conn.pst=conn.conn.prepareStatement(checker_student);
            conn.pst.setString(1, stream_id);
            conn.rs=conn.pst.executeQuery();
            if(conn.rs.next()){
             student_counter=conn.rs.getInt(1);
            }
            
//            check teachers
               String checker_teachers="SELECT COUNT(tc_id) FROM teacher_classes WHERE stream_id=?"; 
               conn.pst=conn.conn.prepareStatement(checker_teachers);
               conn.pst.setString(1, stream_id);
                conn.rs=conn.pst.executeQuery();
                if(conn.rs.next()){
                 teacher_counter=conn.rs.getInt(1);
                }
        if(student_counter>0 || teacher_counter>0){
//            exist record associativity
            output="<font color=\"red\"><b>Error: Failed to delete stream.</b></font>";
        }
        else{
            String deleter="DELETE FROM streams WHERE stream_id=?";
            conn.pst=conn.conn.prepareStatement(deleter);
            conn.pst.setString(1, stream_id);
            conn.pst.executeUpdate();
            output="<font color=\"green\"><b>Stream deleted successfully.</b></font>";
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
            Logger.getLogger(DeleteStream.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteStream.class.getName()).log(Level.SEVERE, null, ex);
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
