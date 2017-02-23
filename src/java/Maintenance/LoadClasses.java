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
public class LoadClasses extends HttpServlet {

    HttpSession session;
    String output;
    int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session = request.getSession();
            dbConn conn = new dbConn();
            
            output="<hr><button style=\"margin-left:80%; width: auto;\" class=\"btn btn-success btn-lg\" id=\"add_class\">New Class</button>";
            output+="<br><br><table id=\"example1\" class=\"table table-bordered table-striped\">";
            output+="<thead>"
                    + "<tr>"
                    + "<th>Position</th>"
                    + "<th>Class</th>"
                    + "<th>Edit</th>"
                    + "<th>Delete</th>"
                    + "</tr>"
                    + "</thead>";
            output+="<tbody>";        
            
            position=0;
            String loadClasses="SELECT id,name FROM classes ORDER BY id ASC";
            conn.rs=conn.st.executeQuery(loadClasses);
            while(conn.rs.next()){
                position++;
                output+="<tr>"
                    + "<input type=\"hidden\" name=\"ival_"+position+"\" id=\"ival_"+position+"\" value=\""+conn.rs.getString(1)+"\">"
                    + "<td style=\"width:10%\">"+position+"</td>"
                    + "<td style=\"width:50%; text-align:center;\"><p id=\""+position+"\">"+conn.rs.getString(2)+"</p></td>"
                    + "<td style=\"width:20%\"><button type=\"button\" class=\"btn btn-block btn-warning\" onclick=\"updator_class("+position+");\" style=\"height:40px; width: 150px\"><b>Edit</b></button></td>"
                    + "<td style=\"width:20%\"><button type=\"button\" class=\"btn btn-block btn-danger\" onclick=\"deleter_class("+position+");\" style=\"height:40px; width: 150px\"><b>Delete</b></button></td>"
                    + "</tr>";    
            }
            output+="</tbody>";
            output+="</table>";
           if(position==0){
//             
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
            Logger.getLogger(LoadClasses.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoadClasses.class.getName()).log(Level.SEVERE, null, ex);
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
