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
  <title>Students</title>
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
       Student Details
      </h1>
   <br>
    </section>
   <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
        <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg"id="add_new">New Student</button>
           <div class="box">
        
               <div>
                   <br>
                <div id="parents_data">
                    <p style="margin-left: 40%"><b>Loading Data .................</b> </p>   
                </div>
            </div>
            <!-- /.box-body -->
          </div>
      
       <div class="modal fade" id="new_student" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <b id="notifier2" style="margin-left: 40%; margin-top: 25%;"></b>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:blue; font-weight: bolder;">Add New Student.</p></h4>
      </div>
      <div class="modal-body" id="body">
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 40px; margin-bottom: 40px;">
        <table>
            <tr class="spaceUnder">
                <td>Admission number <font color="red">*</font></td>    
                <td><input type="text" name="admission_number"  onkeypress="return numbers(event)"  class="form-control" id="admission_number" required="true" maxlength="10" style="width:300px;"></td>  
            </tr>
            <tr class="spaceUnder">
                <td>Full Name <font color="red">*</font></td>    
                <td>
                 <table>  
                     <tr class="spaceInName">
                         <td>
                             <input type="text" name="first_name" placeholder="first name"  class="form-control" id="first_name" required="true" maxlength="30" style="width:90px;">      
                         </td>
                         <td>
                             <input type="text" name="middle_name" placeholder="middle name" class="form-control" id="middle_name" required="true" maxlength="30" style="width:90px;">     
                         </td>
                         <td>
                            <input type="text" name="last_name" placeholder="last name" class="form-control" id="last_name" required="true" maxlength="30" style="width:90px;">   
                         </td>
                    </tr>
                 </table>
                </td>  
            </tr> 
            
             <tr class="spaceUnder">
                <td>Gender <font color="red">*</font></td>    
                <td>
                   <select name="gender" id="gender" required="true" class="form-control" style="width:300px;"> 
                       <option value="Female">Female</option>
                       <option value="Male">Male</option>
                   </select>
                    
                </td>  
            </tr> 
            
             <tr class="spaceUnder">
                <td>Date of birth <font color="red">*</font></td>    
                <td><input type="text" name="date_of_birth" class="form-control" id="date_of_birth" required="true" maxlength="30" style="width:300px;"></td>  
            </tr> 
            
             <tr class="spaceUnder">
                <td>Parent/Guardian<font color="red">*</font></td>    
                <td>
                 <select name="parent_id" id="parent_id" required="true" class="form-control" style="width:300px;"> 
                       <option value="">Loading parents</option>
                   </select>   
                    
                </td>  
            </tr> 
            
             <tr class="spaceUnder">
                <td>Physically Challenged<font color="red">*</font> </td>    
                <td>
                    <select name="is_physically_challenged" id="is_physically_challenged" required="true" class="form-control" style="width:300px;"> 
                       <option value="No">No</option>
                       <option value="Yes">Yes</option>
                   </select>
                </td>  
            </tr> 
            <tr class="spaceUnder">
                <td>Class <font color="red">*</font></td>    
                <td>
                    <select name="class_id" id="class_id" required="true" class="form-control" style="width:300px;"> 
                       <option value="">Loading classes ...</option>
                   </select>
                </td>  
            </tr>  
            
            <tr class="spaceUnder">
                <td>Stream <font color="red">*</font></td>    
                <td>
                    <select name="stream_id" id="stream_id" required="true" class="form-control" style="width:300px;"> 
                       <option value="">No stream</option>
                   </select>
                </td>  
            </tr>
           
            <tr class="spaceUnder">
                <td></td>    
                <td>
                  <div class="modal-footer">
        <!--<button type="button" class="btn-danger" data-dismiss="modal" style="height:40px;" id="cancel">Cancel</button>-->
        <button type="button" class="btn btn-block btn-primary" id="save" style="height:40px; width: 200px"><b>Save</b></button>
     
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
$("#date_of_birth").datepicker();
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
    
    loadGender();
    loadParents();
    loadClasses();
  });
</script>
<script type="text/javascript">
    $("#add_new").click(function(){
        $('#new_student').modal();    
    });
    // save button clicked
    $("#save").click(function(){
     //
    var admission_number,first_name,middle_name,last_name,gender,date_of_birth,parent_id,is_physically_challenged,class_id,stream_id;    
    admission_number=$("#admission_number").val().replace(" ","%20");
    first_name=$("#first_name").val().replace(" ","%20");
    middle_name=$("#middle_name").val().replace(" ","%20");
    last_name=$("#last_name").val().replace(" ","%20");
    gender=$("#gender").val().replace(" ","%20");
    date_of_birth=$("#date_of_birth").val().replace(" ","%20");
    parent_id=$("#parent_id").val().replace(" ","%20");
    is_physically_challenged=$("#is_physically_challenged").val().replace(" ","%20");
    class_id=$("#class_id").val().replace(" ","%20");
    stream_id=$("#stream_id").val().replace(" ","%20");
    
    // validate data
    if(admission_number!=="" && first_name!=="" && last_name!=="" && gender!=="" && date_of_birth!=="" && parent_id!=="" && class_id!=="" && stream_id!==""){
//        send to backend
var link='addStudent?admission_number='+admission_number+'&&first_name='+first_name+'&&middle_name='+middle_name+'&&last_name='+last_name+'&&gender='+gender+'&&date_of_birth='+date_of_birth+'&&parent_id='+parent_id+'&&is_physically_challenged='+is_physically_challenged+'&&stream_id='+stream_id;
var output="";
            $.ajax({
        url:link,
        type:"post",
        dataType:"html",
        success:function(data){
            
         data=$.trim(data);
         if(data==1){
         $('#new_student').modal('hide');
         $("#notifier").notify("Student Added Successfully.","success");
         
    $("#admission_number").val("");
    $("#first_name").val("");
    $("#middle_name").val("");
    $("#last_name").val("");
    $("#date_of_birth").val("");
    loadClasses();
    loadGender();
    loadParents();
         }
         else{
         $("#notifier").notify("Student details already exist.","error");
         $('#new_student').modal('hide');
         }
           loadStudents();
    }    
    });
    }
    else{
//        error message
  $("#notifier").notify("Enter all the details.", "error");
  
    }
    
    });
    </script>
<script type="text/javascript">
    function loadStudents(){
     $.ajax({
        url:'ViewStudents',
        type:"post",
        dataType:"html",
        success:function(data){
    $("#parents_data").html(data);
    $("#example1").DataTable();
    }    
    });   
    }
    function loadGender(){
    $.ajax({
        url:'loadGender',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#gender").html(data);  
        $('#gender').select2(); 
    }    
    });        
    }
    
    function loadParents(){
    $.ajax({
        url:'loadParents',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#parent_id").html(data);  
        $('#parent_id').select2(); 
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
    function editor(position){
      var parent_id=$("#parent_"+position).val();  
        $.ajax({
        url:'loadEditParent.php?parent_id='+parent_id,
        type:"post",
        dataType:"html",
        success:function(data){
  alert("response : "+data);
    }    
    });
    }
    
    function deleter(position){
        alert("deleter : "+position);
    }
    
    </script>
</body>
</html>

