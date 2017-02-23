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
public class LoadExams extends HttpServlet {
HttpSession session;
String exam_id,year,exam_title,term_name;
String output;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            dbConn conn = new dbConn();
            
            output="<hr><button style=\"margin-left:80%; width: auto;\" class=\"btn btn-success btn-lg\" id=\"add_exam\">New Exam</button>";
            output+="<br><br><table id=\"example1\" class=\"table table-bordered table-striped\">";
            output+="<thead>"
                    + "<tr>"
                    + "<th>Position</th>"
                    + "<th>Year</th>"
                    + "<th>Term</th>"
                    + "<th>Exam Title</th>"
                    + "<th>Delete</th>"
                    + "</tr>"
                    + "</thead>";
                    output+="<tbody>";
            
            position=0;
            String getdata="SELECT exam_id,year,terms.term_name AS term, exam_titles.title_name AS exam_title,"
                    + "exams.term_id AS term_id, exams.title_id AS title_id FROM"
                    + " exams LEFT JOIN terms ON exams.term_id=terms.term_id LEFT JOIN exam_titles ON "
                    + " exams.title_id=exam_titles.title_id ORDER BY year DESC,term_id ASC, title_id DESC";
            conn.rs=conn.st.executeQuery(getdata);
            while(conn.rs.next()){
                exam_id=conn.rs.getString(1);
                year=conn.rs.getString(2);
                term_name=conn.rs.getString(3);
                exam_title=conn.rs.getString(4);
                position++;
                output+="<tr>"
                    + "<input type=\"hidden\" name=\"ival_"+position+"\" id=\"ival_"+position+"\" value=\""+exam_id+"\">"
                    + "<td style=\"width:10%\">"+position+"</td>"
                    + "<td style=\"width:25%; text-align:center;\"><p id=\""+position+"\">"+year+"</p></td>"
                    + "<td style=\"width:25%; text-align:center;\"><p id=\""+position+"\">"+term_name+"</p></td>"
                    + "<td style=\"width:25%; text-align:center;\"><p id=\""+position+"\">"+exam_title+"</p></td>"
                    + "<td style=\"width:15%\"><button type=\"button\" class=\"btn btn-block btn-danger\" onclick=\"deleter_exam("+position+");\" style=\"height:40px; width: 150px\"><b>Delete</b></button></td>"
                    + "</tr>";  
            }
            output+="</tbody>";
            output+="</table>";
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
        Logger.getLogger(LoadExams.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadExams.class.getName()).log(Level.SEVERE, null, ex);
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
