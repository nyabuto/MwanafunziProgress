/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loaders;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class loadAddExam extends HttpServlet {
dbConn conn;
String output;
String stream_id,subject_id;
int current_year;
HttpSession session;
String sc_id,first_name,middle_name,last_name,admn_no,fullname,header;
int exam_limit,current_exams,marks,position;
String exam_id,existing_exam_id,term,title,exam_labels,addhead;
int alreadyMarked;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            session=request.getSession();
            conn = new dbConn();
            
            
           output="";current_exams=1;position=0;addhead="";
           current_year=Calendar.getInstance().get(Calendar.YEAR);
           
           stream_id=request.getParameter("stream_id");
           subject_id=request.getParameter("subject_id");
           exam_id=request.getParameter("exam_id");
           
//           stream_id="1";
//           subject_id="1";
           
           if(!stream_id.equals("") && !subject_id.equals("")){
           String getMaxExamsPerTerm="SELECT COUNT(exam_id) FROM exams WHERE year='"+current_year+"'";
           conn.rs=conn.st.executeQuery(getMaxExamsPerTerm);
           if(conn.rs.next()){
          exam_limit=conn.rs.getInt(1);
           }
           else{
          exam_limit=1;     
           }
        
           //header 
         header="<table id=\"example1\" class=\"table table-bordered table-striped\">";
header+="<thead>"
        + "<tr>"
        + "<th>Position</th>"
        + "<th>Student Name</th>"
        + "<th>Admission Number</th>";
         
        
           //end of header
           
           
        String getStudents="SELECT student_classes.id AS sc_id, student_details.first_name AS first_name, student_details.middle_name AS middle_name,"
        + "student_details.last_name AS last_name, student_details.admission_no AS adm_no "
        + "FROM student_details LEFT JOIN student_classes ON student_details.student_id=student_classes.student_id "
        + "WHERE student_classes.stream_id=? AND student_classes.year=?";
conn.pst=conn.conn.prepareStatement(getStudents);
conn.pst.setString(1, stream_id);
conn.pst.setInt(2, current_year);

conn.rs=conn.pst.executeQuery();
while(conn.rs.next()){
sc_id = conn.rs.getString(1);
first_name = conn.rs.getString(2);
middle_name = conn.rs.getString(3);
last_name = conn.rs.getString(4);
admn_no = conn.rs.getString(5);  
 current_exams=1;position++;
 alreadyMarked=0;exam_labels="";
 
 fullname=first_name+" "+middle_name+" "+last_name;
 output+="<tr>"
         + "<td style=\"align-text:center;\">"+position+"</td>"
         + "<td style=\"align-text:center;\">"+fullname+"</td>"
         + "<td style=\"align-text:center;\">"+admn_no+"</td>";
 
   String getEnteredMarks="SELECT marks,student_exams.exam_id AS exam_id,exams.timestamp AS timestamp,"
           + "exams.term_id AS term_id,"
           + "school_terms.term_name AS term ,exam_titles.title_name AS title "
           + "FROM student_exams LEFT JOIN exams ON student_exams.exam_id=exams.exam_id "
           + "LEFT JOIN exam_titles ON exams.title_id=exam_titles.title_id "
           + "LEFT JOIN school_terms ON exams.term_id=school_terms.term_id "
           + "WHERE sc_id=? && subject_id=?  ORDER BY "
           + "timestamp ASC";
   conn.pst1=conn.conn.prepareStatement(getEnteredMarks);
   conn.pst1.setString(1, sc_id);
   conn.pst1.setString(2, subject_id);
   
   conn.rs1=conn.pst1.executeQuery();
   while(conn.rs1.next()){
   current_exams++;
   marks=conn.rs1.getInt(1);
   existing_exam_id=conn.rs1.getString(2);
   term=conn.rs1.getString(5);
   title=conn.rs1.getString(6);
   exam_labels+="<th><b style=\"align-text:center;\">"+term+" "+title+"</b></th>";
   if(existing_exam_id.equals(exam_id)){
  alreadyMarked=1;     
   }
   output+="<td><b style=\"align-text:center;\">"+marks+"</b></td>";
   }
   if(current_exams==1 || alreadyMarked==0){
       output+="<td>"
               + "<input type=\"hidden\" id=\"id_"+position+"\" value=\""+sc_id+"\">"
               + ""
               + "<input type=\"text\" name=\"exam_"+position+"\"  onkeypress=\"return numbers(event)\"  class=\"form-control\""
               + " id=\"exam_"+position+"\" required=\"true\" maxlength=\"3\" onkeyup=\"saveData("+position+")\" style=\"width:100px;\">"
               + "<b id=\"saved_"+position+"\" style=\"margin-left:20%;\"></b>"
               + ""
               + "</td>";
   }
   else{
   //output+="<td></td>";    
   }
 output+="</tr>";   
}

output+="</tbody>";
output+="</table>";
header+=exam_labels;

if(alreadyMarked>0){

}
else{
header+="<th>Exam "+current_exams+"</th>";    
}


header+="</tr></thead><tbody>";

output=header+output;
           }
           else{
output="<p><b style=\"margin-left:40%;margin-top:13%;\">Select all parameters marked <font color=\"red\">*</font></b></p>";
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
        Logger.getLogger(loadAddExam.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadAddExam.class.getName()).log(Level.SEVERE, null, ex);
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
