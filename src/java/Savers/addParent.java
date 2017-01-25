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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mwamb
 */
public class addParent extends HttpServlet {
String output="";
String first_name,middle_name,last_name,gender,national_id,phone_number;
String physical_address,relationship_id,occupation_id,residence;
dbConn conn;
HttpSession session;
String student_ids[];
String parent_id,student_id;
    IDGenerator rand;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
        conn = new dbConn();
        session = request.getSession();
       rand = new IDGenerator();
      parent_id=rand.current_id();
      
      first_name=request.getParameter("first_name");
      middle_name=request.getParameter("middle_name");
      last_name=request.getParameter("last_name");
      gender=request.getParameter("gender");
      national_id=request.getParameter("national_id");
      phone_number=request.getParameter("phone_no");
      physical_address=request.getParameter("physical_address");
      relationship_id=request.getParameter("relationship_id");
      occupation_id=request.getParameter("occupation_id");
      residence=request.getParameter("residence");
     student_ids=request.getParameter("student_ids").split(",");
     
    if(!checkParent(conn,national_id,phone_number)){
      // add new parent to the system.
      String inserter="INSERT INTO parent_details(first_name,middle_name,last_name,gender,national_id,phone_number,physical_address,relationship_id,occupation_id,residence,parent_id)"
              + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
      conn.pst = conn.conn.prepareStatement(inserter);
      conn.pst.setString(1, first_name);
      conn.pst.setString(2, middle_name);
      conn.pst.setString(3, last_name);
      conn.pst.setString(4, gender);
      conn.pst.setString(5, national_id);
      conn.pst.setString(6, phone_number);
      conn.pst.setString(7, physical_address);
      conn.pst.setString(8, relationship_id);
      conn.pst.setString(9, occupation_id);
      conn.pst.setString(10, residence);
      conn.pst.setString(11, parent_id);
      conn.pst.executeUpdate();
      
       for (String s_id : student_ids){
          String updator="UPDATE student_details SET parent_id=? WHERE student_id=?";
          conn.pst1=conn.conn.prepareStatement(updator);
          conn.pst1.setString(1, parent_id);
          conn.pst1.setString(2, s_id);
          conn.pst1.executeUpdate();
      }
      
      
      
      output="1";
    }else{
    output="0";     
    }
    out.println(output);
    session.setAttribute("parent_added", output);
//    response.sendRedirect("addParent.jsp");
         }
    }
    
    private boolean checkParent(dbConn conn,String national_id,String phone_number) throws SQLException{
     boolean exist=true;
     String checkExistence="SELECT count(parent_id) FROM parent_details WHERE national_id=? OR phone_number=?";
     conn.pst=conn.conn.prepareStatement(checkExistence);
     conn.pst.setString(1, national_id);
     conn.pst.setString(2, phone_number);
     conn.rs=conn.pst.executeQuery();
     if(conn.rs.next()){
         exist=conn.rs.getInt(1)>0;
     }
     
     return exist;
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
        Logger.getLogger(addParent.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(addParent.class.getName()).log(Level.SEVERE, null, ex);
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
