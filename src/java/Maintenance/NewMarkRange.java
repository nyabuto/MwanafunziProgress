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
public class NewMarkRange extends HttpServlet {
HttpSession session;
String range_id,subject_id,min_marks,max_marks,points_id;
String output;
String[] subject_ids;
int no_items,counter_exist,counter_added;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           session=request.getSession();
           dbConn conn = new dbConn();
           
           counter_exist=counter_added=0;
           subject_ids=request.getParameterValues("subject_ids");
           no_items=Integer.parseInt(request.getParameter("no_items"));
           
           
           
           for(int i=1;i<=no_items;i++){
           min_marks=request.getParameter("min_marks"+i);
           max_marks=request.getParameter("max_marks"+i);
           points_id=request.getParameter("points_id"+i);
           
               //loop through to get subjects chosen
               for (String sub_id : subject_ids) {
               if(!sub_id.equals("")){
                 subject_id=sub_id;
                 // check and save to database.
                String checker="SELECT range_id FROM mark_ranges WHERE subject_id=? AND points_id=?";
                conn.pst=conn.conn.prepareStatement(checker);
                conn.pst.setString(1, subject_id);
                conn.pst.setString(2, points_id);
                 
                conn.rs=conn.pst.executeQuery();
                if(conn.rs.next()){
//                    existing record
                     counter_exist++;
                }
                else{
                    IDGenerator rand = new IDGenerator();
                    range_id=rand.current_id();
                    
                    String inserter="INSERT INTO mark_ranges (range_id,min,max,points_id,subject_id) VALUES(?,?,?,?,?)";
                    conn.pst=conn.conn.prepareStatement(inserter);
                    conn.pst.setString(1, range_id);
                    conn.pst.setString(2, min_marks);
                    conn.pst.setString(3, max_marks);
                    conn.pst.setString(4, points_id);
                    conn.pst.setString(5, subject_id);
                    
                    conn.pst.executeUpdate();
                    counter_added++;
                }
               }
               }
           
           }
           output=counter_added+" Record(s) added and "+counter_exist+" record(s) exist.";
           
           
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
        Logger.getLogger(NewMarkRange.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(NewMarkRange.class.getName()).log(Level.SEVERE, null, ex);
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
