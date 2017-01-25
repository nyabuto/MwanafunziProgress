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
import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mwamb
 */
public class saveMark extends HttpServlet {
    HttpSession session;
    dbConn conn;
    String output="";
    String sc_id,subject_id,teacher_id,marks,exam_id;
    int exists;
    String se_id;
    IDGenerator rand;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          conn= new dbConn();
          session=request.getSession();
          rand = new IDGenerator();
          
          sc_id=request.getParameter("sc_id");
          subject_id=request.getParameter("subject_id");
          teacher_id=request.getParameter("teacher_id");
          marks=request.getParameter("marks");
          exam_id=request.getParameter("exam_id");
          
            System.out.println("teacher : id"+teacher_id+" sc _id :"+sc_id);
          se_id=checkExistence(conn,subject_id,sc_id,exam_id);
         
         if(se_id.equals("")){
         se_id=rand.current_id();
         String inserter="INSERT INTO student_exams (student_exams_id,sc_id,teacher_id,subject_id,marks,exam_id) VALUES (?,?,?,?,?,?)";
         conn.pst=conn.conn.prepareStatement(inserter);
         conn.pst.setString(1, se_id);
         conn.pst.setString(2, sc_id);
         conn.pst.setString(3, teacher_id);
         conn.pst.setString(4, subject_id);
         conn.pst.setString(5, marks);
         conn.pst.setString(6, exam_id);
         conn.pst.executeUpdate();
        output=""; 
         }
         else{
         String updator="UPDATE student_exams SET marks=? WHERE student_exams_id=?";
         conn.pst=conn.conn.prepareStatement(updator);
         conn.pst.setString(1, marks);
         conn.pst.setString(2, se_id);
         conn.pst.executeUpdate();    
         } 
          
            out.println(output);
        }
    }

    public String checkExistence(dbConn conn,String subject_id,String sc_id,String exam_id) throws SQLException{
        String id="";
        
        String getID="SELECT student_exams_id FROM student_exams WHERE sc_id=? AND subject_id=? AND exam_id=?";
        conn.pst=conn.conn.prepareStatement(getID);
        conn.pst.setString(1, sc_id);
        conn.pst.setString(2, subject_id);
        conn.pst.setString(3, exam_id);
        
        conn.rs=conn.pst.executeQuery();
        if(conn.rs.next()){
            id=conn.rs.getString(1);
        }
        
        return id;
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
            Logger.getLogger(saveMark.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(saveMark.class.getName()).log(Level.SEVERE, null, ex);
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
