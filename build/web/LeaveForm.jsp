<%-- 
    Document   : Students
    Created on : Oct 16, 2016, 1:15:29 PM
    Author     : mwamb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Leave Forms</title>
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
        <h1 style="margin-left: 40%" id="header">
       Student Details
      </h1>
   <br>
    </section>
   <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
        <table>
            <tr class="spaceUnder">
                
              <td>
                  <select name="class_id" id="class_id" required="true" class="form-control" style="width:200px;min-height: 50px;"> 
                       <option value="">Loading classes ...</option>
                   </select>
              </td>
              
              <td>
           <select name="stream_id" id="stream_id" required="true" class="form-control" style="width:200px;"> 
                       <option value="">No stream</option>
                   </select>       
                  </td>
                  <td>
                      <select name="student_id" id="student_id" required="true" class="form-control" style="width:200px;"> 
                        <option value="">No student</option>
                   </select>
                  </td>
                <td>
                 <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg" id="add_new">New Leave Form</button>
                  </td>  
            </tr>
        </table>
           <div class="box">
        
               <div>
                <div id="leave_data" style="min-height: 300px;">
                    <p style="margin-left: 40%; margin-top: 10%;"><b>No student selected.</b> </p>   
                </div>
            </div>
            <!-- /.box-body -->
          </div>
      
       <div class="modal fade" id="new_form" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <b id="notifier2" style="margin-left: 40%; margin-top: 25%;"></b>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:blue; font-weight: bolder;">Leave Sheet.</p></h4>
      </div>
      <div class="modal-body" id="body">
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 40px; margin-bottom: 40px;">
        <table>
<!--            <tr class="spaceUnder">
                <td>Date Out : <font color="red">*</font></td>    
                <td><input type="date" name="date_out" placeholder="Date Out" onkeypress="return numbers(event)"  class="form-control" id="date_out" required="true" maxlength="10" style="width:300px;"></td>  
            </tr>-->
            <tr class="spaceUnder">
                <td>Expected Return date : <font color="red">*</font></td>    
                <td>
                    <input type="date" name="expected_return_date" placeholder="Expected return date"  class="form-control" id="expected_return_date" readonly="true" required="true" maxlength="30" style="width:300px;">      
                </td>  
            </tr> 
            
             <tr class="spaceUnder">
                <td>Select Reason <font color="red">*</font></td>    
                <td>
                   <select name="reason_id" id="reason_id" required="true" class="form-control" style="width:300px;"> 
                       <option value="">Loading reasons</option>
                   </select>
                    
                </td>  
            </tr> 
            
            <tr class="spaceUnder">
                <td>Description <font color="red">*</font></td>
                <td><textarea name="description" class="form-control" id="description" required="true" style="width:300px;"></textarea></td>  
                </tr> 
             
            <tr class="spaceUnder">
                <td></td>    
                <td>
                  <div class="modal-footer">
        <!--<button type="button" class="btn-danger" data-dismiss="modal" style="height:40px;" id="cancel">Cancel</button>-->
        <button type="button" class="btn btn-block btn-primary" id="save" style="height:40px; width: 200px"><b>Generate Leave Form.</b></button>
     
      </div> 
                </td>  
            </tr>
            
        </table>
        
    </form>
      </div>
      
    </div>
  </div>
</div>
      <!-- /.example-modal -->  
      
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
<!--<script src="notify/notify.min.js"></script>-->
<script>
  $(function () {
$('#expected_return_date').datepicker(
        {
    format: 'yyyy-mm-dd',
    startDate: '0d'
}
                );
});
</script>

<script type="text/javascript">
    function saveForm(){
        var edate=$("#expected_return_date").val();
        var reason_id=$("#reason_id").val();
        var description=$("#description").val();
        
        if(edate!="" && reason_id!="" && description!=""){
         $.ajax({
        url:'saveLeaveForm?edate='+edate+'&&reason_id='+reason_id+'&&description='+description,
        type:"post",
        dataType:"html",
        success:function(status){
    if(status==0){
//        record already exist
$("#update").notify("Similar record already exist.","error");
$("#save").notify("Similar record already exist.","error");
//$('#new_form').modal('hide');
     }
       else if(status==1){
//        added 
$("#notifier").notify("Leave saved successfully.","success");
$('#new_form').modal('hide'); 
 studentDetails();
     }
     else if(status==2){
//        no student 
$("#notifier").notify("Error. Kindly select a student before adding a new record.","error");
$('#new_form').modal('hide'); 
     }
     else{
      // unknown error 
      $("#notifier").notify("Unknown error. Try again.","error");
      $('#new_form').modal('hide'); 
     }
    }    
    });   
        }
        else{
         $("#save").notify("Enter all required details.","error");   
        }
    }
    </script>
<script>
  $(function () {
      addNewManager(false);
      loadStudents();
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
    }    
    });  
       });
       
       $("#save").click(function(){
           saveForm();
       });
    loadClasses();
  });
</script>
<script type="text/javascript">
    $("#add_new").click(function(){
        $('#new_form').modal(); 
        loadReasons();
    });
    </script>
    
    <script>
  $(function () {
      loadStudents();
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
            loadStudents();
            addNewManager(false);
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
     loadStudents();
     addNewManager(false);
    }    
    });  
       });
       
         //  STREAM SELECTED
    $("#student_id").change(function(){
       studentDetails(); 
       addNewManager(true);
       });
      
  });
</script>

<script type="text/javascript">
    $(function(){
    loadStudents();
    loadClasses();
    });
    
    </script>
  <script type="text/javascript">  
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
    
    function loadStudents(){
     $.ajax({
        url:'loadStudents',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
       $("#student_id").html(data); 
       $('#student_id').select2(); 
    }    
    });    
    }
    function loadServerData(){
     $.ajax({
        url:'ViewLeaveForms',
        type:"post",
        dataType:"text",
        success:function(output){
    $("#leave_data").html(output);
    $("#example1").DataTable();
    }    
    }); 
     }
     
     function studentDetails(){
       //set sessions
           var student_id=$("#student_id").val();
         $.ajax({
        url:'SetStudentSession?student_id='+student_id,
        type:"post",
        dataType:"html",
        success:function(student_name){
            $("#header").html(student_name);
loadServerData();
    }    
    });     
     }
     
     function loadReasons(){
         $.ajax({
        url:'loadReasons',
        type:"post",
        dataType:"html",
        success:function(output){
            $("#reason_id").html(output);
           $("#reason_id").select2(); 
    }    
    });     
     }
     
    </script>  
<script type="text/javascript">
function updator(pos){
    var id=$("#ival_"+pos).val();
    var dout=$("#dout_"+pos).val();
   $.ajax({
        url:'isBack?id='+id+'&&dout='+dout,
        type:"post",
        dataType:"html",
        success:function(){
//function to reload form
loadServerData();
    }    
    });   
}
    function addNewManager(studentSelected){
        if(studentSelected==true){
            $("#add_new").show();
        }
        else{
            $("#add_new").hide();
        }
    }
    </script>
</body>
</html>

