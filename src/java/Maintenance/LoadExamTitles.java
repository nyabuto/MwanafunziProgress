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
public class LoadExamTitles extends HttpServlet {
    HttpSession session;
    String output,title_id,title_name;
    int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          dbConn conn = new dbConn();
          
          output="<hr><button style=\"margin-left:80%; width: auto;\" class=\"btn btn-success btn-lg\" id=\"add_exam_title\">New Exam Title</button>";
            output+="<br><br><table id=\"example1\" class=\"table table-bordered table-striped\">";
            output+="<thead>"
                    + "<tr>"
                    + "<th>Position</th>"
                    + "<th>Exam Title</th>"
                    + "<th>Edit</th>"
                    + "<th>Delete</th>"
                    + "</tr>"
                    + "</thead>";
                    output+="<tbody>";
            
            position=0;
          String getData="SELECT title_id,title_name FROM exam_titles ORDER BY title_id ASC";
          conn.rs=conn.st.executeQuery(getData);
          while(conn.rs.next()){
              title_id=conn.rs.getString(1);
              title_name=conn.rs.getString(2);
              
              position++;
                output+="<tr>"
                    + "<input type=\"hidden\" name=\"ival_"+position+"\" id=\"ival_"+position+"\" value=\""+title_id+"\">"
                    + "<td style=\"width:10%\">"+position+"</td>"
                    + "<td style=\"width:50%; text-align:center;\"><p id=\""+position+"\">"+title_name+"</p></td>"
                    + "<td style=\"width:20%\"><button type=\"button\" class=\"btn btn-block btn-warning\" onclick=\"updator_exam_titles("+position+");\" style=\"height:40px; width: 150px\"><b>Edit</b></button></td>"
                    + "<td style=\"width:20%\"><button type=\"button\" class=\"btn btn-block btn-danger\" onclick=\"deleter_exam_title("+position+");\" style=\"height:40px; width: 150px\"><b>Delete</b></button></td>"
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
            Logger.getLogger(LoadExamTitles.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadExamTitles.class.getName()).log(Level.SEVERE, null, ex);
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
