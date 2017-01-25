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
public class loadAddFees extends HttpServlet {
    HttpSession session;
    String output;
    String year,class_id,stream_id;
    
    String student_name,admn_no,class_name,stream,gender,year_joined,sc_id;
    int current_target,fees_paid,balance;
    int term_id;
    String WHERE, balance_status,balance_button,history_button;
    int position;
    String sc_ids="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           session=request.getSession();
            IDGenerator rand = new IDGenerator();
            dbConn conn = new dbConn();
            term_id=rand.getCurrentTerm();
            
            year=class_id=stream_id=output=balance_button=history_button="";
            position=1;
            
            sc_ids="";
           year=request.getParameter("year");
           
           if(session.getAttribute("class_id")!=null){
           class_id=session.getAttribute("class_id").toString();
           }
           if(session.getAttribute("stream_id")!=null){
           stream_id=session.getAttribute("stream_id").toString();
           }
           
           WHERE="";
            
        output="<table id=\"example1\" class=\"table table-bordered table-striped\">";
        output+="<thead>"
              + "<tr>"
              + "<th>Position</th>"
              + "<th>Student Name</th>"
              + "<th>Adm No</th>"
              + "<th>Gender</th>"
              + "<th>Year Joined</th>"
              + "<th>Class</th>"
              + "<th>Stream</th>"
              + "<th>Target</th>"
              + "<th>Paid</th>"
              + "<th>Balance</th>"
              + "<th>Pay</th>"
              + "<th>History</th>";
        output+="</tr></thead><tbody>";  
           
           
           
           
           
           if(year.equals("")){
               year=""+rand.getCurrentYear();
               System.out.println("no year selected :"+year);
           }
           
          if(Integer.parseInt(year)<rand.getCurrentYear()){
              term_id=3;
          }
          
           String getTotal="SELECT SUM(amount) AS current_target FROM fees_set WHERE year=? AND class_id=? AND term_id <=?";
           conn.pst=conn.conn.prepareStatement(getTotal);
           conn.pst.setString(1, year);
           conn.pst.setString(2, class_id);
           conn.pst.setInt(3, term_id);
           
           conn.rs=conn.pst.executeQuery();
           while(conn.rs.next()){
               current_target=conn.rs.getInt(1);
           }
           
           if(!stream_id.equals("")){
            WHERE="student_classes.year=? AND student_classes.stream_id=?";
           }
           else if(!class_id.equals("")){
            WHERE="student_classes.year=? AND streams.class_id=?"; 
           }
           else{
               WHERE="student_classes.year=?";
           }
           String getStudentDetails="SELECT student_details.admission_no AS adm, student_details.first_name AS fname, "
                   + "student_details.middle_name AS mname, student_details.last_name AS lname,student_details.gender AS gender,"
                   + "student_details. year_joined AS year_joined,student_classes.id AS sc_id, streams.stream AS stream, classes.name AS class_name "
                   + "FROM student_details "
                   + "LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id "
                   + "LEFT JOIN streams ON student_classes.stream_id=streams.stream_id "
                   + "LEFT JOIN classes ON streams.class_id=classes.id "
                   + "WHERE "+WHERE+" ORDER BY class_name,stream, fname,mname,lname ASC ";
           conn.pst=conn.conn.prepareStatement(getStudentDetails);
           conn.pst.setString(1, year);
           if(!stream_id.equals("")){
            conn.pst.setString(2, stream_id);
           }
           else if(!class_id.equals("")){
            conn.pst.setString(2, class_id);
           }
           
          conn.rs=conn.pst.executeQuery();
          
          while(conn.rs.next()){
            admn_no=conn.rs.getString(1);
            student_name=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
            gender=conn.rs.getString(5);
            year_joined=conn.rs.getString(6);
            sc_id=conn.rs.getString(7);
            stream=conn.rs.getString(8);
            class_name=conn.rs.getString(9);
            sc_ids+=sc_id+",";
            // get total fees paid this year
            fees_paid=0;
            
            String getPaid="SELECT SUM(amount) FROM fees_paid WHERE sc_id='"+sc_id+"'";
            conn.rs1=conn.st1.executeQuery(getPaid);
            if(conn.rs1.next()){
                fees_paid=conn.rs1.getInt(1);
            }
            
            balance=current_target-fees_paid;
            if(balance>0){
            balance_status="<font color=\"red\">"+balance+"</font>";
            balance_button="<button class=\"btn btn-block btn-success\" onclick=\"return pay("+position+")\">Receive Fees</button>";
        }
        else if(balance<0){
            balance_status="<font color=\"blue\">"+balance+"</font>";
            balance_button="<b>Over Paid</b>";
        }  
        else if(balance==0){
            balance_status="<font color=\"green\">"+balance+"</font>";
            balance_button="<b>Done!</b>";
        }
        else{}
            
        if(fees_paid==0){
            history_button="<b style=\"color:red\">No Payment History</b>";    
        }
        else{
            history_button="<button class=\"btn btn-block btn-info\" onclick=\"return history("+position+")\">Payment history</button>";
        }
        
       output+="<tr>"
        + "<td style=\"align-text:center;\">"+position+"</td>"
        + "<td style=\"align-text:center;\">"+student_name+"</td>"
        + "<td style=\"align-text:center;\">"+admn_no+"</td>"
        + "<td style=\"align-text:center;\">"+gender+"</td>"
        + "<td style=\"align-text:center;\">"+year_joined+"</td>"
        + "<td style=\"align-text:center;\">"+class_name+"</td>"
        + "<td style=\"align-text:center;\">"+stream+"</td>"
        + "<td style=\"align-text:center;\">"+current_target+"</td>"
        + "<td style=\"align-text:center;\">"+fees_paid+" <p id=\"paid_"+position+"\" style=\"color:green;font-size 9px;\"></p></td>"
        + "<td style=\"align-text:center;\">"+balance_status+"</td>"
        + "<td style=\"align-text:center;\">"+balance_button+"</td>"
        + "<td style=\"align-text:center;\">"+history_button+"</td>"
        
        + "</tr>";
         
       position++;
          }
          session.setAttribute("sc_ids", sc_ids);
          
          output+="<tbody>"; 
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
            Logger.getLogger(loadAddFees.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadAddFees.class.getName()).log(Level.SEVERE, null, ex);
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
