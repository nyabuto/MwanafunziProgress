/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class IndividualPerformanceCharts extends HttpServlet {
    dbConn conn;
    HttpSession session;
    String student_id;
    int current_year;
    int marks,points,term_counter;
    String term,grade;
    String term_name;

    ArrayList<Integer> marksArray = new ArrayList<>();
    ArrayList<Integer> pointsArray = new ArrayList<>();
String reason_id,reason;
    int marksCounter,current_termid;
    String titles,data,singleEntry;
    int daysCounter,days_absent,totalElements;
    ArrayList<Integer> reasonsData= new ArrayList<>();
    String output;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
          conn = new dbConn();
         session = request.getSession();
         titles=data=singleEntry="";
         marksArray.clear();marksCounter=0;
         pointsArray.clear();
         reasonsData.clear();
         totalElements=0;
         
         current_year=Calendar.getInstance().get(Calendar.YEAR);
         student_id=session.getAttribute("student_id").toString();
         //student_id="1";
         for (int i=1;i<=3;i++){
             marksArray.add(0);
             pointsArray.add(0);
         }
         String getExamResults="SELECT AVG(total_marks) AS total_marks,AVG(total_points) AS total_points,"
                 + "exams.term_id as term_id,school_terms.term_name AS term_name "
                 + "FROM exam_totals "
                 + "LEFT JOIN student_classes ON exam_totals.sc_id=student_classes.id "
                 + "LEFT JOIN exams ON exam_totals.exam_id=exams.exam_id "
                 + "LEFT JOIN school_terms ON exams.term_id=school_terms.term_id "
                 + "WHERE exams.year=? AND student_classes.student_id=? "
                 + "GROUP BY term_id ORDER BY term_id ASC";
         conn.pst = conn.conn.prepareStatement(getExamResults);
         conn.pst.setInt(1, current_year);
         conn.pst.setString(2, student_id);
         conn.rs=conn.pst.executeQuery();
            while(conn.rs.next()){
                marksCounter++;
                marks=conn.rs.getInt(1);
                points=conn.rs.getInt(2);
                term=conn.rs.getString(3);
                marksArray.set(marksCounter-1, marks);
                pointsArray.set(marksCounter-1, points);
                totalElements++;
                String getGrade="SELECT grade FROM marks_ranges WHERE ? BETWEEN min AND max";
                conn.pst1=conn.conn.prepareStatement(getGrade);
                conn.pst1.setInt(1, points);
                conn.rs1=conn.pst1.executeQuery();
                if(conn.rs1.next()){
                 grade=points+""+conn.rs1.getString(1);
                }
              
            }
            // get totals data and output the values

            for (int i=0;i<3;i++){
               singleEntry+=marksArray.get(i)+",";
         }
        singleEntry = singleEntry.substring(0, singleEntry.length()-1);
        data+=singleEntry+"##";
        titles+="Exams Results: [Total Marks]@@";
        
            String getReasons="SELECT reason_id,reason FROM reason_categories WHERE reason_id!=1";
            conn.rs1=conn.st.executeQuery(getReasons);
            while(conn.rs1.next()){
             reason_id=conn.rs1.getString(1);
             reason=conn.rs1.getString(2);
            daysCounter=0;singleEntry="";
            clearArray();
            String getDaysOut="SELECT SUM(absentee_sheet.days_out) AS days_absent,"
                    + "school_terms.term_id AS term_id,school_terms.term_name AS term_name "
                    + "FROM absentee_sheet LEFT JOIN student_classes ON absentee_sheet.sc_id=student_classes.id "
                    + "LEFT JOIN school_terms ON absentee_sheet.term_id=school_terms.term_id "
                    + "WHERE student_classes.student_id=? AND student_classes.year=? "
                    + "AND absentee_sheet.reason_category_id=? "
                    + "GROUP BY term_id ORDER BY term_id ASC";
            conn.pst=conn.conn.prepareStatement(getDaysOut);
            conn.pst.setString(1, student_id);
            conn.pst.setInt(2, 2016);
            conn.pst.setString(3,reason_id);
            conn.rs=conn.pst.executeQuery();            
            while(conn.rs.next()){
             daysCounter++;
             days_absent=conn.rs.getInt(1);
             term_name=conn.rs.getString(3);
             reasonsData.set(daysCounter-1, days_absent);
             totalElements++;
            }
        for (int i=0;i<3;i++){
        singleEntry+=reasonsData.get(i)+",";
         }
        singleEntry = singleEntry.substring(0, singleEntry.length()-1);
        data+=singleEntry+"##";
        titles+=reason+" : [Days Out]@@";
        
        }
            
            
        data = data.substring(0, data.length()-2);
        titles = titles.substring(0, titles.length()-2);
       output=titles+"^^"+data;     
            out.print(output);
            out.flush();
        }
    }
    private void clearArray(){
        reasonsData.clear();
        for(int i=0;i<=2;i++){
            reasonsData.add(0);
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
            Logger.getLogger(IndividualPerformanceCharts.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(IndividualPerformanceCharts.class.getName()).log(Level.SEVERE, null, ex);
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
