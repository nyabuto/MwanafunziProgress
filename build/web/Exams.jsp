<%-- 
    Document   : Exams
    Created on : Oct 19, 2016, 3:35:00 PM
    Author     : mwamb
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Students Examination</title>
 <link rel="shortcut icon" href="images/school_logo.png"> 
   <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- DataTables -->
  <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
  <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
  <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
  <link rel="stylesheet" href="dist/css/others.css">
   <link rel="stylesheet" href="select2/css/select2.css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <div>
       <%@include file="header.jsp" %>
    </div>

  <!-- MENU HERE -->
  
  <div>
  <%@include file="menu.jsp" %>
  </div>
  <!--END MENU-->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1 style="margin-left: 40%">
       Exams Register
      </h1>
   <br>
    </section>
   <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
        <table>
            <tr class="spaceMenu">
              <td>
                  <select name="exam_id" id="exam_id" required="true" class="form-control" style="width:200px;"> 
                       <option value="">Loading exams ...</option>
                   </select>
              </td>
              
                <td>
                  <select name="class_id" id="class_id" required="true" class="form-control" style="width:200px;"> 
                       <option value="">No Classes </option>
                   </select>
              </td>
              <td>
           <select name="stream_id" id="stream_id" required="true" class="form-control" style="width:200px;"> 
                       <option value="">No stream</option>
                   </select>       
                  </td>
                  
                  <td>
           <select name="subject_id" id="subject_id" required="true" class="form-control" style="width:200px;"> 
                       <option value="">No subject</option>
                   </select>       
                  </td>
                  
                  <td>
                  <select name="teacher_id" id="teacher_id" required="true" class="form-control" style="width:200px;"> 
                       <option value="">No teacher</option>
                   </select>
              </td>
            </tr>
        </table>
       <div class="box">
        
               <div>
                   <br>
                   <div id="exam_data" style="min-height: 300px;">
                    <p style="margin-left: 40%; margin-top: 5%;"><b>NO DATA</b> </p>   
                </div>
            </div>
            <!-- /.box-body -->
          </div> 
      
    </section>
    <!-- /.content -->
  </div>
  
      
      
  <!-- /.content-wrapper -->
  <%@include file="footer.jsp" %>
  <div class="control-sidebar-bg"></div>
</div>
    
    <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<script src="dist/js/demo.js"></script>
<script src="select2/js/select2.js"></script>

<script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
<script src="notify/notify.js"></script>

<script type="text/javascript">
    function saveData(position){
       var sc_id=$("#id_"+position).val();
       var subject_id=$("#subject_id").val();
       var teacher_id=$("#teacher_id").val();
       var exam_id=$("#exam_id").val();
       var marks=$("#exam_"+position).val();
       //send data to the database
       if(marks>=0 && marks<=100){

         $.ajax({
        url:'saveMark?sc_id='+sc_id+"&&subject_id="+subject_id+"&&teacher_id="+teacher_id+"&&marks="+marks+"&&exam_id="+exam_id,
        type:"post",
        dataType:"html",
        success:function(){
     $("#saved_"+position).html("<img src=\"images/saved.png\"/ alt=\"+\">");

    }    
  });
       }
       else{
//           error message, marks out of range
       }
    }
    </script>
<script>
  $(function () {
loadExams();
$("#exam_id").change(function(){
 var exam_id=$("#exam_id").val(); 
 if(exam_id!==""){
 loadClasses();  
 loadSubjects();
 }
});
       //CLASS SELECTED
       $("#class_id").change(function(){
           var class_id=$("#class_id").val();
           loadStreams(class_id);
           
           //set sessions
         $.ajax({
        url:'SetClassSession?class_id='+class_id,
        type:"post",
        dataType:"html",
        success:function(){
    }    
    });  
       }); 
        
        
        
      //  STREAM SELECTED
    $("#stream_id").change(function(){
           //set sessions
           var stream_id=$("#stream_id").val();
         $.ajax({
        url:'SetStreamSession?stream_id='+stream_id,
        type:"post",
        dataType:"html",
        success:function(){
            loadTeachers();
    }    
    });  
       });
       
        $("#subject_id").change(function(){
loadTeachers();
     });
      $("#teacher_id").change(function(){
loadExamData();
     });
     
        $("#exam_id").change(function(){
loadExamData();
     });
  });
</script>
<script type="text/javascript">
    function loadExamData(){

        var stream_id=$("#stream_id").val();
        var subject_id=$("#subject_id").val();
        var teacher_id=$("#teacher_id").val();
        var exam_id=$("#exam_id").val();
        
        if(stream_id!="" && subject_id!="" && teacher_id!="" && exam_id!=""){ 
     $.ajax({
        url:'loadAddExam?stream_id='+stream_id+"&&subject_id="+subject_id+"&&teacher_id="+teacher_id+"&&exam_id="+exam_id,
        type:"post",
        dataType:"html",
        success:function(data){
    $("#exam_data").html(data);
    $("#example1").DataTable();
    }    
    }); 
        }
        else{
        //select all parameters    
        }
    }
    function loadExams(){
     $.ajax({
        url:'loadExams',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#exam_id").html(data);  
        $('#exam_id').select2(); 
        
    }    
    });    
    }
    
    
    function loadClasses(){
     $.ajax({
        url:'loadClasses',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#class_id").html(data);  
        $('#class_id').select2(); 
    }    
    });    
    }
    
    function loadStreams(class_id){
     $.ajax({
        url:'loadStreams?class_id='+class_id,
        type:"post",
        dataType:"html",
        success:function(data){
        //streams drop down
       $("#stream_id").html(data); 
       $('#stream_id').select2(); 
    }    
    });    
    }
    function loadSubjects(){
     $.ajax({
        url:'loadSubjects',
        type:"post",
        dataType:"html",
        success:function(data){
        //streams drop down
       $("#subject_id").html(data); 
       $('#subject_id').select2(); 
    }    
    });    
    }
    function loadTeachers(){
        var stream_id=$("#stream_id").val();
        var subject_id=$("#subject_id").val();  
        if(stream_id!="" && subject_id!=""){
     $.ajax({
        url:'loadTeachers?subject_id='+subject_id+"&&stream_id="+stream_id,
        type:"post",
        dataType:"html",
        success:function(data){
        //streams drop down
       $("#teacher_id").html(data); 
       $('#teacher_id').select2(); 
                
     }    
    });
        }
    }
    </script>
</body>
</html>

