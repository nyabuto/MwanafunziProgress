<%-- 
    Document   : Parents
    Created on : Oct 13, 2016, 10:15:58 PM
    Author     : mwamb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>All Parents</title>
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
       Parent Details
      </h1>
   <br>
    </section>
 <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
    <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg"id="add_new">New Parent</button>
         <div class="box">
        
               <div>
                   <br>
                <div id="parents_data">
                    <p style="margin-left: 40%"><b>Loading Data .................</b> </p>   
                </div>
            </div>
            <!-- /.box-body -->
          </div>
      
       
       <div class="modal fade" id="new_parent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
          <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:blue; font-weight: bolder;">Add New Parent.</p></h4>
      </div>
      <div class="modal-body" id="body">
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 5px; margin-bottom: 5px;">
        <table>
           
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
                <td>National ID Number : <font color="red">*</font></td>    
                <td><input type="text" name="national_id"  onkeypress="return numbers(event)"  class="form-control" id="national_id" required="true" maxlength="10" style="width:300px;"></td>  
            </tr>
            <tr class="spaceUnder">
                <td>Phone Number : <font color="red">*</font></td>    
                <td><input type="text" name="phone_no"  onkeypress="return numbers(event)"  class="form-control" id="phone_no" required="true" maxlength="10" style="width:300px;"></td>  
            </tr>
            
             <tr class="spaceUnder">
                <td>Physical Address : <font color="red"></font></td>    
                <td><input type="text" name="physical_address"  onkeypress="return numbers(event)"  class="form-control" id="physical_address" required="true" maxlength="10" style="width:300px;"></td>  
            </tr>
            
            <tr class="spaceUnder">
                <td>Residence : <font color="red">*</font></td>    
                <td><input type="text" name="residence"  onkeypress="return numbers(event)"  class="form-control" id="residence" required="true" maxlength="10" style="width:300px;"></td>  
            </tr>
             <tr class="spaceUnder">
                <td>Student<font color="red"></font></td>    
                <td>
                    <select name="student_ids" id="student_ids" multiple="true" class="form-control" style="width:300px;"> 
                       <option value="">Loading students</option>
                   </select>   
                 
                </td>  
            </tr> 
            
             <tr class="spaceUnder">
                <td>Relationship : <font color="red">*</font> </td>    
                <td>
                    <select name="relationship_id" id="relationship_id" required="true" class="form-control" style="width:300px;"> 
                       <option value="">Loading relationships</option>
                   </select>
                </td>  
            </tr> 
            <tr class="spaceUnder">
                <td>Choose Occupation <font color="red">*</font></td>    
                <td>
                    <select name="occupation_id" id="occupation_id" required="true" class="form-control" style="width:300px;"> 
                       <option value="">Loading occupations</option>
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
 <script src="notify/notify.js"></script>
<script>
  $(function () {
    loadParents();
    loadDetails();
    $("#add_new").click(function(){
        $('#new_parent').modal();    
    });
  });
</script>
<script type="text/javascript">
    function loadParents(){
            $.ajax({
        url:'ViewParents',
        type:"post",
        dataType:"html",
        success:function(data){
    $("#parents_data").html(data);
    $("#example1").DataTable();
    }    
    });  
    }
    
    function loadDetails(){
    loadOccupations();
    loadRelationships();
    loadStudents();
    loadGender();
    }
    function loadOccupations(){
     $.ajax({
        url:'loadOccupations',
        type:"post",
        dataType:"html",
        success:function(data){
    $("#occupation_id").html(data);
    $('#occupation_id').select2(); 
    }    
    });   
    }
   function loadStudents(){
    $.ajax({
        url:'loadStudents',
        type:"post",
        dataType:"html",
        success:function(data){
    $("#student_ids").html(data);
    $('#student_ids').select2(); 
    }    
    });     
    } 
    
    function loadRelationships(){
   $.ajax({
        url:'loadRelationships',
        type:"post",
        dataType:"html",
        success:function(data){
    $("#relationship_id").html(data);
    $('#relationship_id').select2(); 
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
    </script>
    
    <script type="text/javascript">
        $(function(){
           // save button clicked
    $("#save").click(function(){
//     alert("clicked save");
    var phone_no,first_name,middle_name,last_name,gender,national_id,physical_address,residence,student_ids,relationship_id,occupation_id;    
    first_name=$("#first_name").val().replace(" ","%20");
    middle_name=$("#middle_name").val().replace(" ","%20");
    last_name=$("#last_name").val().replace(" ","%20");
    gender=$("#gender").val().replace(" ","%20");
    national_id=$("#national_id").val().replace(" ","%20");
    phone_no=$("#phone_no").val().replace(" ","%20");
    physical_address=$("#physical_address").val().replace(" ","%20");
    residence=$("#residence").val().replace(" ","%20");
    relationship_id=$("#relationship_id").val().replace(" ","%20");
    occupation_id=$("#occupation_id").val().replace(" ","%20");
    //student_ids=$("#student_ids").val().replace(" ","%20");
    var student_ids = []; 
$('#student_ids :selected').each(function(i, selected){ 
  student_ids[i] = $(selected).val(); 
});
//        alert(student_ids);
    // validate data
    if(phone_no!=="" && first_name!=="" && last_name!=="" && gender!=="" && national_id!=="" && physical_address!=="" && residence!=="" && relationship_id!=="" && occupation_id!==""){
//        send to backend
var link='addParent?phone_no='+phone_no+'&&first_name='+first_name+'&&middle_name='+middle_name+'&&last_name='+last_name+'&&gender='+gender+'&&national_id='+national_id+'&&physical_address='+physical_address+'&&residence='+residence+'&&relationship_id='+relationship_id+'&&occupation_id='+occupation_id+'&&student_ids='+student_ids;
//  alert(link);
            $.ajax({
        url:link,
        type:"post",
        dataType:"html",
        success:function(data){
//     alert(data);       
         data=$.trim(data);
         if(data=="1"){
         $('#new_parent').modal('hide');
         $("#notifier").notify("Parent Added Successfully.","success");
         
    $("#phone_no").val("");
    $("#first_name").val("");
    $("#middle_name").val("");
    $("#last_name").val("");
    $("#national_id").val("");
    $("#physical_address").val("");
    $("#residence").val("");
    loadDetails();
         }
         else{
         $("#notifier").notify("Parent details already exist.","error");
         $('#new_parent').modal('hide');
         }
         
         
           loadParents();
    }    
    });
    }
    else{
//        error message
  $("#save").notify("Enter all the details.", "error");
     }
    
    }); 
        
    });
        </script>

<script type="text/javascript">
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

