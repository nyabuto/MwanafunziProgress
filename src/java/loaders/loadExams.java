/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loaders;

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

/**
 *
 * @author mwamb
 */
public class loadExams extends HttpServlet {
String output="";
int current_year;
String term_id;
int counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            dbConn conn = new dbConn();
            
            counter=0;
            current_year=Calendar.getInstance().get(Calendar.YEAR);
            output="";
          String getData="SELECT exams.exam_id AS exam_id, exams.year AS year, exam_titles.title_name AS title_name,"
                  + "terms.term_name AS term "
                  + "FROM exams LEFT JOIN exam_titles ON exams.title_id=exam_titles.title_id "
                  + "LEFT JOIN school_terms ON exams.term_id=school_terms.term_id "
                  + "LEFT JOIN terms ON school_terms.term_name_id=terms.term_id "
                  + "WHERE exams.year=? "
                  + "ORDER BY exam_id DESC LIMIT 5";
          conn.pst = conn.conn.prepareStatement(getData);
          conn.pst.setInt(1, current_year);
          conn.rs=conn.pst.executeQuery();
          while(conn.rs.next()){
              String exam_id=conn.rs.getString(1);
              String year=conn.rs.getString(2);
              String exam_title=conn.rs.getString(3);
              String term=conn.rs.getString(4);
              counter++;
              output+="<option value=\""+exam_id+"\">"+term+", "+exam_title+" , "+year+"</option>";
          }
          if(counter==0){
         output+="<option value=\"\">No Exam Found</option>";    
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
        Logger.getLogger(loadExams.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadExams.class.getName()).log(Level.SEVERE, null, ex);
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
