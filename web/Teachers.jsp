<%-- 
    Document   : Teachers
    Created on : Oct 13, 2016, 10:15:58 PM
    Author     : mwamb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Teachers Module</title>
  <link rel="shortcut icon" href="images/Teacher.png"> 
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
       Teacher's Details
      </h1>
   <br>
    </section>
 <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
    <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg"id="add_new">New Teacher</button>
         <div class="box">
        
               <div>
                   <br>
                <div id="teachers_data">
                    <p style="margin-left: 40%"><b>Loading Data .................</b> </p>   
                </div>
            </div>
            <!-- /.box-body -->
          </div>
      
       
    <div class="modal fade" id="new_teacher" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: auto;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
          <h4 class="modal-title" id="myModalLabel"><p style="text-align: center; color:blue; font-weight: bolder;">Add New Teacher.</p></h4>
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
                <td>TSC Number : <font color="red"></font></td>    
                <td><input type="text" name="tsc_no"  onkeypress="return numbers(event)"  class="form-control" id="tsc_no" required="true" maxlength="10" style="width:300px;"></td>  
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
                <td>Responsibility<font color="red"></font></td>    
                <td>
                    <select name="responsibility_id" id="responsibility_id" class="form-control" style="width:300px;"> 
                       <option value="">Loading responsibilities</option>
                   </select>   
                 
                </td>  
            </tr> 
            <tr>
                <td colspan="2">
        <table cellspacing="0" id='class_subject'>
      <tr class="spaceUnder">
        <th><input class='check_all' type='checkbox' onclick="select_all()"/></th>
        <th>S. No</th>
        <th>Class</th>
        <th>Stream</th>
        <th>Subject</th>
        
      </tr>
      <tr class="spaceSubject">
        <td><input type='checkbox' class='case' style='margin-right: 5px;'/></td>
        <td><b style='margin-right: 5px;'>1.</b></td>
        <td>
        <select name="class_1"  id="class_1" onchange="classSelected(1)" required="true" class="form-control " style="width:150px; margin-right:5px;"> 
       <option value="">Loading classes</option>
        </select>
        </td>
        <td>
        <select name="stream_1" id="stream_1" required="true" class="form-control" style="width:150px; margin-right:5px;"> 
        <option value="">No stream</option>
        </select>
        </td>
        <td>
        <select name="subject_1" id="subject_1" required="true" class="form-control" style="width:150px; margin-right:5px;"> 
        <option value="">No Subject</option>
        </select>
        </td>
        
      </tr>
    </table>

    <button type="button" class='delete'>- Delete</button>
    <button type="button" class='addmore'>+ Add More</button>
    <p>    
                </td>
                </tr>
             <tr class="spaceUnder">
                 <td>
                     <input type="hidden" id='no_classes' name='no_classes' value="1">         
                     
                 </td>    
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
 
 <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>
 <script>
  var i=2;
  $(".addmore").on('click',function(){
     numberClicked(i); 
     $("#no_classes").val(i);
    var data="<tr class=\"spaceSubject\" style=\"margin-bottom: 5px;\"><td><input type='checkbox' class='case'/></td><td>"+i+".</td>";
    data +="<td>"+
        "<select name=\"gender_"+i+"\" id=\"class_"+i+"\" onchange=\"classSelected("+i+")\" required=\"true\" class=\"form-control\" style=\"width:150px; margin-right:5px;\"> "+
        "<option value=\"\">Loading classes..</option>"+
        "</select>"+
        "</td>"+
        "<td>"+
        "<select name=\"stream_"+i+"\" id=\"stream_"+i+"\" required=\"true\" class=\"form-control\" style=\"width:150px; margin-right:5px;\"> "+
        "<option value=\"\">No stream</option>"+
        "</select>"+
        "</td>"+
        "<td>"+
        "<select name=\"subject_"+i+"\" id=\"subject_"+i+"\" required=\"true\" class=\"form-control\" style=\"width:150px; margin-right:5px;\">"+
        "<option value=\"\">No Subject</option>"+
        "</select>"+
        "</td> </tr>";
        $('#class_subject').append(data);
        
        i++;
});

function select_all() {
	$('input[class=case]:checkbox').each(function(){ 
		if($('input[class=check_all]:checkbox:checked').length == 0){ 
			$(this).prop("checked", false); 
		} else {
			$(this).prop("checked", true); 
		} 
	});
}

function check(){
	obj=$('#class_subject tr').find('span');
	$.each( obj, function( key, value ) {
	id=value.id;
	$('#'+id).html(key+1);
	});
	}
        
$(".delete").on('click', function() {
$('.case:checkbox:checked').parents("tr").remove();
$('.check_all').prop("checked", false); 
	check();

});
</script>

<script>
  $(function () {
    loadTeachers();
    loadDetails();
    
    $("#add_new").click(function(){
        $('#new_teacher').modal(); 
        numberClicked(1);
    });

  });
</script>
<script type="text/javascript">
    function loadTeachers(){
            $.ajax({
        url:'ViewTeachers',
        type:"post",
        dataType:"html",
        success:function(data){
    $("#teachers_data").html(data);
    $("#example1").DataTable();
    }    
    });  
    }
    
    function loadDetails(){
    loadResponsibilities();
    loadGender();
    }
    
   function loadResponsibilities(){
    $.ajax({
        url:'loadResponsibilities',
        type:"post",
        dataType:"html",
        success:function(data){
    $("#responsibility_id").html(data);
    $('#responsibility_id').select2(); 
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
  function  classSelected(position){

    var class_id=$("#class_"+position).val();   
    loadStreams(class_id,position); 
    }
    
    function numberClicked(position){
    
     loadClasses(position); 
     loadSubjects(position);
    }
    function loadClasses(position){
     $.ajax({
        url:'loadClasses',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#class_"+position).html(data);  
        $('#class_'+position).select2(); 
    }    
    });
    
    }
    
    function loadStreams(class_id,position){
     $.ajax({
        url:'loadStreams?class_id='+class_id,
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
       $("#stream_"+position).html(data); 
       $('#stream_'+position).select2(); 
    }    
    });    
    }
    
    function loadSubjects(position){
     $.ajax({
        url:'loadSubjects',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#subject_"+position).html(data);  
        $('#subject_'+position).select2(); 
    }    
    });
    
    }
    </script>
    
    <script type="text/javascript">
        $(function(){
           // save button clicked
    $("#save").click(function(){
//     alert("clicked save");
    var phone_no,first_name,middle_name,last_name,gender,national_id,tsc_no,responsibility_id,no_classes;    
    first_name=$("#first_name").val().replace(" ","%20");
    middle_name=$("#middle_name").val().replace(" ","%20");
    last_name=$("#last_name").val().replace(" ","%20");
    gender=$("#gender").val().replace(" ","%20");
    national_id=$("#national_id").val().replace(" ","%20");
    phone_no=$("#phone_no").val().replace(" ","%20");
    tsc_no=$("#tsc_no").val().replace(" ","%20");
    responsibility_id=$("#responsibility_id").val().replace(" ","%20");
    
no_classes=$("#no_classes").val();

var i=1;
var class_id,stream_id,subject_id;
var classes="",streams="",subjects="";
while(i<=no_classes){
  class_id=$("#class_"+i).val();
  stream_id=$("#stream_"+i).val();
  subject_id=$("#subject_"+i).val();
  classes+=class_id+",";
  streams+=stream_id+",";
  subjects+=subject_id+",";
  
  i++;
}
    // validate data
    if(phone_no!=="" && first_name!=="" && last_name!=="" && gender!=="" && national_id!=="" && streams!=="" ){
//        send to backend
var myLink='addTeacher?phone_no='+phone_no+'&&first_name='+first_name+'&&middle_name='+middle_name+'&&last_name='+last_name+'&&gender='+gender+'&&national_id='+national_id+'&&tsc_no='+tsc_no+'&&responsibility_id='+responsibility_id+"&&subject_ids="+subjects+"&&stream_ids="+streams;
//  alert(myLink);
            $.ajax({
        url:myLink,
        type:"post",
        dataType:"html",
        success:function(data){
//     alert(data);       
         data=$.trim(data);
         if(data=="1"){
         $('#new_teacher').modal('hide');
         $("#notifier").notify("Teacher Added Successfully.","success");
         
    $("#phone_no").val("");
    $("#first_name").val("");
    $("#middle_name").val("");
    $("#last_name").val("");
    $("#national_id").val("");
    $("#tsc_no").val("");
    loadDetails();
         }
         else{
         $("#notifier").notify("Teacher details already exist.","error");
         $('#new_teacher').modal('hide');
         }
         
         
           loadTeachers();
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
      var teacher_id=$("#teacher_"+position).val();  
        $.ajax({
        url:'loadEditParent.php?teacher_id='+teacher_id,
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

