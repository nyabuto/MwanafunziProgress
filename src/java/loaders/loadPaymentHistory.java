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
public class loadPaymentHistory extends HttpServlet {
    HttpSession session;
    String[] sc_ids;
    int position,counter;
    String sc_id,mode_name,amount,transaction_id,output,timestamp;
    int total_paid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          session=request.getSession();
            dbConn conn = new dbConn();
            
            output="<table class=\"table\">"
                    + "<thead>"
                    + "<tr class=\"spaceUnder\">"
                    + "<th>No.</th>"
                    + "<th>Payment Mode</th>"
                    + "<th>Transaction Number</th>"
                    + "<th>Amount Paid</th>"
                    + "<th>Date Paid</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>";
            counter=total_paid=0;
            position=Integer.parseInt(request.getParameter("position"));
            
            sc_id="";
            if(session.getAttribute("sc_ids")!=null){
             sc_ids=session.getAttribute("sc_ids").toString().split(",");
              if(position<=sc_ids.length){
             sc_id=sc_ids[position-1];
             
             String getPaymentDetails="SELECT amount,payment_modes.mode AS payment_mode, transaction_id, timestamp "
                     + "FROM fees_paid LEFT JOIN payment_modes ON fees_paid.mode_id=payment_modes.id "
                     + "WHERE fees_paid.sc_id='"+sc_id+"' ORDER BY timestamp ";
             conn.rs=conn.st.executeQuery(getPaymentDetails);
             while(conn.rs.next()){
              counter++;
              amount=conn.rs.getString(1);
              mode_name=conn.rs.getString(2);
              transaction_id=conn.rs.getString(3);
              timestamp=conn.rs.getString(4);
              
              output+="<tr class=\"spaceUnder\">"
                    + "<td>"+counter+"</td>"
                    + "<td>"+mode_name+"</td>"
                    + "<td>"+transaction_id+"</td>"
                    + "<td>"+amount+"</td>"
                    + "<td>"+timestamp+"</td>"
                    + "</tr>";
              
             total_paid+=Integer.parseInt(amount);
             }
             }
            }
            output+="<tr>"
                    + "<td colspan=\"5\" style=\"background-color:gray;\"></td>"
                    + "</tr>"
                    
                    + "<tr>"
                    + "<td colspan=\"3\"><td>"
                    + "<td><b style=\"font-size:18px;\">Total Paid</b><td>"
                    + "<td ><b style=\"font-size:18px;\">"+total_paid+"</b><td>"
                    + "</tr>";
            
            output+="</tbody>";
            
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
            Logger.getLogger(loadPaymentHistory.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loadPaymentHistory.class.getName()).log(Level.SEVERE, null, ex);
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
