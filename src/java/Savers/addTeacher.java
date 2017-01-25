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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.w3c.dom.css.Counter;

/**
 *
 * @author mwamb
 */
public class addTeacher extends HttpServlet {
String output;
dbConn conn;
HttpSession session;
String tsc_no,first_name,middle_name,last_name,phone_no,national_id,year_joined,responsibility_id;
String stream_id;
String subject_ids[],stream_ids[];
int current_year;
String teacher_id,ts_id,gender;
    IDGenerator rand;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           conn = new dbConn();
           session = request.getSession();
           
           current_year=Calendar.getInstance().get(Calendar.YEAR);
           rand = new IDGenerator();
           
           tsc_no=request.getParameter("tsc_no");
           first_name=request.getParameter("first_name");
           middle_name=request.getParameter("middle_name");
           last_name=request.getParameter("last_name");
           phone_no=request.getParameter("phone_no");
           national_id=request.getParameter("national_id");
           responsibility_id=request.getParameter("responsibility_id");
           subject_ids=request.getParameter("subject_ids").split(",");
           stream_ids=request.getParameter("stream_ids").split(",");
           gender=request.getParameter("gender");
            System.out.println("phone_no="+phone_no+"&&first_name="+first_name+"&&middle_name="+middle_name+"&&last_name="+last_name+"&&gender="+gender+"&&national_id="+national_id+"&&tsc_no="+tsc_no+"&&responsibility_id="+responsibility_id+"&&subject_ids="+subject_ids.toString()+"&&stream_ids="+stream_ids.toString());
           teacher_id=rand.current_id();
           if(!checkTeacher(conn,tsc_no,phone_no,national_id)){
               String addteacher="INSERT INTO teacher_details"
               + "(tsc_no,first_name,middle_name,last_name,phone_no,national_id,year_joined,responsibility_id,teacher_id,gender) "
                       + "VALUES(?,?,?,?,?,?,?,?,?,?)";
               conn.pst=conn.conn.prepareStatement(addteacher);
               conn.pst.setString(1, tsc_no);
               conn.pst.setString(2, first_name);
               conn.pst.setString(3, middle_name);
               conn.pst.setString(4, last_name);
               conn.pst.setString(5, phone_no);
               conn.pst.setString(6, national_id);
               conn.pst.setInt(7, current_year);
               conn.pst.setString(8, responsibility_id);
               conn.pst.setString(9, teacher_id);
               conn.pst.setString(10, gender);
               
               conn.pst.executeUpdate();
               int subCounter=0;
               System.out.println("here");
             for(String subject_id : subject_ids) {
                 stream_id=stream_ids[subCounter];
                 if(!subject_id.equals("")){
                if(!(tsExist(conn,subject_id,teacher_id)>0)){
                IDGenerator rand2 = new IDGenerator();
                ts_id=rand2.current_id();
                String subjectTeacher="INSERT INTO teacher_subject (teacher_id,subject_id,ts_id) VALUES(?,?,?)";
                conn.pst=conn.conn.prepareStatement(subjectTeacher);
                conn.pst.setString(1, teacher_id);
                conn.pst.setString(2, subject_id);
                conn.pst.setString(3, ts_id);
                conn.pst.executeUpdate();
                 subCounter ++;
               addTCRecord(conn, stream_id, ts_id);
            } 
                else{
                    addTCRecord(conn, stream_id, ts_id);
                }
            
                }
             }
             output="1";
           }
           else{
               output="0";
           }
           out.println(output);
        }
    }
    
    private void addTCRecord(dbConn conn, String stream_id, String ts_id) throws SQLException{
        if(!(tcExist(conn, ts_id, stream_id)>0)){
            IDGenerator rand3 = new IDGenerator();
            String tc_id=rand3.current_id();
            
      String inserter = "INSERT INTO teacher_classes (tc_id,ts_id,stream_id) VALUES(?,?,?)";
conn.pst3 = conn.conn.prepareStatement(inserter);
conn.pst3.setString(1, tc_id);
conn.pst3.setString(2, ts_id);
conn.pst3.setString(3, stream_id);
conn.pst3.executeUpdate();
        }
    }
    private int tcExist(dbConn conn,String ts_id,String stream_id) throws SQLException{
    int exist=0;
      String checker="SELECT tc_id FROM teacher_classes WHERE ts_id=? && stream_id=?";
      conn.pst2=conn.conn.prepareStatement(checker);
      conn.pst2.setString(1, ts_id);
      conn.pst2.setString(2, stream_id);
      conn.rs2=conn.pst2.executeQuery();
      if(conn.rs2.next()==true){
          exist=conn.rs2.getInt(1);
      }
      
      return exist;     
    }
    
    private int tsExist(dbConn conn,String subject_id,String teacher_id) throws SQLException{
      int exist=0;
      String checker="SELECT ts_id FROM teacher_subject WHERE teacher_id=? && subject_id=?";
      conn.pst2=conn.conn.prepareStatement(checker);
      conn.pst2.setString(1, teacher_id);
      conn.pst2.setString(2, subject_id);
      conn.rs2=conn.pst2.executeQuery();
      if(conn.rs2.next()==true){
          exist=conn.rs2.getInt(1);
      }
      
      return exist;
    }
    private boolean checkTeacher(dbConn conn,String tsc_no,String phone,String national_id) throws SQLException{
        boolean t_id=true;
        
        String checker="SELECT COUNT(teacher_id) FROM teacher_details WHERE (tsc_no=?  OR national_id=?) && phone_no=?";
      conn.pst=conn.conn.prepareStatement(checker);
      conn.pst.setString(1, tsc_no);
      conn.pst.setString(2, phone);
      conn.pst.setString(3, national_id);
      conn.rs=conn.pst.executeQuery();
      if(conn.rs.next()){
          t_id=conn.rs.getInt(1)>0;
      }
      else{
      t_id=false;    
      }
        return t_id;
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
        Logger.getLogger(addTeacher.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(addTeacher.class.getName()).log(Level.SEVERE, null, ex);
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
