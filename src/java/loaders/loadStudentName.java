/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loaders;

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
public class loadStudentName extends HttpServlet {
    HttpSession session;
    String[] sc_ids;
    int position;
    String sc_id,student_name;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session=request.getSession();
            dbConn conn = new dbConn();
            IDGenerator rand = new IDGenerator();

            
            student_name="";
            position=Integer.parseInt(request.getParameter("position"));
            
            sc_id="";
            if(session.getAttribute("sc_ids")!=null){
             sc_ids=session.getAttribute("sc_ids").toString().split(",");
             if(position<=sc_ids.length){
             sc_id=sc_ids[position-1];
                System.out.println("student class number : "+sc_id);
                
                String getStudent="SELECT first_name,middle_name,last_name,admission_no FROM student_details "
                        + "LEFT JOIN student_classes ON student_classes.student_id=student_details.student_id "
                        + "WHERE student_classes.id='"+sc_id+"'";
                conn.rs=conn.st.executeQuery(getStudent);
                if(conn.rs.next()){
                student_name="<font color=\"blue\">"+conn.rs.getString(1)+" "+conn.rs.getString(2)+" "+conn.rs.getString(3)+" ["+conn.rs.getString(4)+"] </font>";
                }else{
                 student_name="<font color=\"red\">Un-identified Student</font>";   
                }
             }
             else{
              student_name="<font color=\"red\">Un-identified Student</font>";     
             }
            }
            out.println(student_name);
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
            Logger.getLogger(loadStudentName.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadStudentName.class.getName()).log(Level.SEVERE, null, ex);
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
