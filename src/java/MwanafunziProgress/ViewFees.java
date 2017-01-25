/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MwanafunziProgress;

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
public class ViewFees extends HttpServlet {
dbConn conn;
HttpSession session;
String output="";
String c_id,strm_id,s_id;
String query,whereClause;
IDGenerator rand;
int current_year;
String student_id,fullname,admission_no,stream,gender,class_id,stream_id;
String getfinancialDetails,pastpayments,totals,addFinance;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          conn = new dbConn();
          session = request.getSession();
          rand = new IDGenerator();
          output=c_id=strm_id=s_id="";
          query=whereClause="";
          
          current_year=rand.getCurrentYear();
          
          if(session.getAttribute("class_id")!=null){
              c_id=session.getAttribute("class_id").toString();
          }
          if(session.getAttribute("stream_id")!=null){
          strm_id=session.getAttribute("stream_id").toString();
          }
          if(session.getAttribute("student_id")!=null){
              s_id=session.getAttribute("student_id").toString();
          }
          
          
          if(!s_id.equals("")){
          whereClause="WHERE student_classes.year=? AND student_details.student_id=? ";    
         }
          else if(s_id.equals("") && !strm_id.equals("")){
//              query for stream students
           whereClause="WHERE student_classes.year=? AND student_classes.stream_id=? ";        
          }
          else if(strm_id.equals("") && !c_id.equals("")){
//              query for class students
           whereClause="WHERE student_classes.year=? AND streams.class_id=? ";        
          }
         
          else{
          whereClause="WHERE student_classes.year=? ";     
          }
          
         query="SELECT student_details.student_id AS stid, student_details.first_name AS fname,"
              + "student_details.middle_name AS mname,student_details.last_name AS lname,"
              + "student_details.admission_no AS adm_no,student_details.gender AS gender,"
              + "classes.class_name,AS class_name,streams.stream AS stream "
              + "FROM student_details "
              + "LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id "
              + "LEFT JOIN streams ON student_classes.stream_id=streams.stream_id "
              + "LEFT JOIN classes ON streams.class_id=classes.class_id "+whereClause+" "
              + "ORDER BY stud"; 
          conn.pst=conn.conn.prepareStatement(query);
          conn.pst.setInt(1, current_year);
          
          if(!s_id.equals("")){conn.pst.setString(2, s_id); }
          else if(s_id.equals("") && !strm_id.equals("")){conn.pst.setString(2, strm_id); }
          else if(strm_id.equals("") && !c_id.equals("")){conn.pst.setString(2, c_id);  }
          else{}
          
          conn.rs=conn.pst.executeQuery();
          while(conn.rs.next()){
           student_id=conn.rs.getString(1);
           fullname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
           admission_no=conn.rs.getString(5);
           stream=conn.rs.getString(6);
           gender=conn.rs.getString(7); 
           class_id=conn.rs.getString(8); 
           stream_id=conn.rs.getString(9); 
           
           pastpayments=totals=addFinance="";
    // get each student financial records. and finally ballance
        getfinancialDetails="";   
           
           
           
//           at the end append totals paid, ballance, and allow entry of finacial data i.e paid amount
           
           
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
        Logger.getLogger(ViewFees.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ViewFees.class.getName()).log(Level.SEVERE, null, ex);
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
