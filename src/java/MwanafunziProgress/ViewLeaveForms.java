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
import java.text.ParseException;
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
public class ViewLeaveForms extends HttpServlet {
String student_id;
    HttpSession session;
    String stream,date_out,date_in,expected_return_date,reasons_cat,reason_description,year,term;
    int position,is_back,days_out,pos;
    String id,label,output;
    IDGenerator RGenerator;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          session = request.getSession();
            dbConn conn = new dbConn();
            position=pos=0;
           
            RGenerator = new IDGenerator();
            student_id=session.getAttribute("student_id").toString();
//            student_id="1";
            
            output="<table id=\"example1\" class=\"table table-bordered table-striped\">";
output+="<thead>"
        + "<tr>"
        + "<th>Position</th>"
        + "<th>Year</th>"
        + "<th>Stream</th>"
         + "<th>Term</th>"
        + "<th>Date Out</th>"
         + "<th>Expected return date</th>"
         + "<th>Return Date</th>"
        + "<th>Days Out</th>"
        + "<th>Reason</th>"
        + "<th>Description</th>"
        + "<th>Others</th>"
        + "</tr>"
        + "</thead>";
output+="<tbody>";


            String getStudent="SELECT streams.stream AS stream, absentee_sheet.date_out AS date_out,absentee_sheet.date_in AS date_in," +
"                    absentee_sheet.expected_return_date expected_return_date,days_out,reason_categories.reason AS reason_cat," +
"                    absentee_sheet.reason AS reason_description,absentee_sheet.is_back is_back,student_classes.year AS year,"
                    + "absentee_sheet.timestamp AS timestamp,school_terms.term_name AS term,absentee_sheet.id AS id " +
"                    FROM absentee_sheet " +
"                    LEFT JOIN student_classes ON absentee_sheet.sc_id=student_classes.id " +
"                    LEFT JOIN streams ON student_classes.stream_id=streams.stream_id " +
"                    LEFT JOIN reason_categories ON reason_categories.reason_id=absentee_sheet.reason_category_id "
                    + "LEFT JOIN school_terms ON absentee_sheet.term_id=school_terms.term_id " +
"                    WHERE student_classes.student_id=? ORDER BY timestamp DESC";
            conn.pst = conn.conn.prepareStatement(getStudent);
            conn.pst.setString(1, student_id);
            conn.rs=conn.pst.executeQuery();
            while(conn.rs.next()){
                position++;
              stream=conn.rs.getString(1);
              date_out=conn.rs.getString(2);
              date_in=conn.rs.getString(3);
              expected_return_date=conn.rs.getString(4);
              days_out=conn.rs.getInt(5);
              reasons_cat=conn.rs.getString(6);
              reason_description=conn.rs.getString(7);
              is_back=conn.rs.getInt(8);
              year = conn.rs.getString(9);
              term = conn.rs.getString(11);
              id=conn.rs.getString(12);
              if(date_in==null){
                  String today=RGenerator.toDay();
               days_out=RGenerator.getDays(date_out, today);
                  date_in="";
              }
              if(is_back==1){
                  label="<b> <img src=\"images/tick.png\" style=\"width:30px;height:30px; margin-left:35%;\" alt=\"Back\"/></b>";
              }
              else{
                  pos++;
                  label=""
                          + "<input type=\"hidden\" name=\"ival_"+pos+"\" id=\"ival_"+pos+"\" value=\""+id+"\">"
                          + "<input type=\"hidden\" name=\"dout_"+pos+"\" id=\"dout_"+pos+"\" value=\""+date_out+"\">"
                          + "<button type=\"button\" class=\"btn btn-block btn-warning\" id=\"update\" onclick=\"updator("+pos+");\" style=\"height:40px; width: 200px\"><b>Accept Student</b></button>\n" +
                            "";
                 
              }
              output+="<tr>"
                      + "<td>"+position+"</td>"
                      + "<td>"+year+"</td>"
                      + "<td>"+stream+"</td>"
                      + "<td>"+term+"</td>"
                      + "<td>"+date_out+"</td>"
                      + "<td>"+expected_return_date+"</td>"
                      + "<td>"+date_in+"</td>"
                      + "<td>"+days_out+"</td>"
                      + "<td>"+reasons_cat+"</td>"
                      + "<td>"+reason_description+"</td>"
                      + "<td>"+label+"</td>"
                      + "</tr>"
                      + "<br>";
            }
             output+="</tbody>";

 output+="</table>";
 
 if(position==0){
     output="<p><b style=\"margin-left:40%;margin-top:13%;\">No record to be shown</b></p>";
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
        Logger.getLogger(ViewLeaveForms.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(ViewLeaveForms.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ViewLeaveForms.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(ViewLeaveForms.class.getName()).log(Level.SEVERE, null, ex);
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
