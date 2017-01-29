<%-- 
    Document   : Fees
    Created on : Jan 21, 2017, 8:29:10 PM
    Author     : mwamb
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Students Fees</title>
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
       Fees Payment
      </h1>
   <br>
    </section>
   <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
        <table>
            <tr class="spaceMenu">
              <td>
                  <select name="year" id="year" required="true" class="form-control" style="width:200px;"> 
                       <option value="">Loading Years</option>
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
      
        <!--start of pop up-->
        
         <div class="modal fade" id="new_form" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <b id="notifier2" style="margin-left: 40%; margin-top: 25%;"></b>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
          <h4 class="modal-title" id="myModalLabel"><u><p style="text-align: center; color:blue; font-weight: bolder;">New Fees Payment.</p></u></h4>
        </div>
      <div class="modal-body" id="body">
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 10px; margin-bottom: 40px;">
         <p style="text-align: center; font-size: 20px;"><b>Student Name<b> <b class="student_name">student name</b></p>
      
                     <table style="margin-top: 10px;">
             <tr class="spaceUnder">
                <td>Payment Mode <font color="red">*</font></td>    
                <td>
                   <select name="mode_id" id="mode_id" required="true" class="form-control" style="width:300px;"> 
                       <option value="">Loading Modes</option>
                   </select>
                    
                </td>  
            </tr> 
            <tr class="spaceUnder">
                <td>Amount Paid<font color="red">*</font></td>    
                <td>
                    <input type="text" name="amount" placeholder="Amount paid"  class="form-control" id="amount" onkeypress="return numbers(event)" required="true" maxlength="7" style="width:300px;">      
                </td>  
            </tr>
            
            <tr class="spaceUnder">
                <td>Transaction Number<font color="red">*</font></td>    
                <td>
                    <input type="text" name="transaction_id" placeholder="Transaction Number"  class="form-control" id="transaction_id" required="true" maxlength="50" style="width:300px;">      
                </td>  
            </tr>
            
            <tr class="spaceUnder">
                <td></td>    
                <td>
                  <div class="modal-footer">
        <!--<button type="button" class="btn-danger" data-dismiss="modal" style="height:40px;" id="cancel">Cancel</button>-->
        <button type="button" class="btn btn-block btn-primary" id="save" style="height:40px; width: 200px"><b>Pay Fees.</b></button>
     
      </div> 
      </td>  
            </tr>
         <input type="hidden" name="clicked_pos" value="" id="clicked_pos">   
        </table>
        
      </form>
      </div>
      
    </div>
  </div>
</div>
        
        <!--END OF MAKE PAYMENT ##########################################################-->
        
         <div class="modal fade" id="history_form" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="min-width:900px; ">
    <div class="modal-content" style="min-width:900px; ">
      <div class="modal-header">
          <b id="notifier2" style="margin-left: 40%; margin-top: 25%;"></b>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
          <h4 class="modal-title" id="myModalLabel"><u><p style="text-align: center; color:blue; font-weight: bolder;">Payment History.</p></u></h4>
        </div>
        <div class="modal-body" id="body" style="min-width:800px; ">
   <p style="text-align: center; font-size: 20px;"><b>Student Name<b> <b class="student_name">student name</b></p>
      
           <div id="history_data">         
               <p style="text-align: center"><b>NO DATA.</b></p>
           </div>
      </div>
      
    </div>
  </div>
</div>
        <!--end pop up-->
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
    function saveData(){
        var position=$("#clicked_pos").val();
       var mode_id=$("#mode_id").val();
       var transaction_id=$("#transaction_id").val();
       var amount=$("#amount").val();
       
       if(amount>0 && mode_id!==""){

         $.ajax({
        url:'SaveFees?position='+position+"&&mode_id="+mode_id+"&&transaction_id="+transaction_id+"&&amount="+amount,
        type:"post",
        dataType:"html",
        success:function(data){
        $("#notifier").notify(data);
        $("#amount").val("");
        $("#transaction_id").val("");
        $("#paid_"+position).html("+"+amount);
        }    
  });
       }
       else{
//           alert enter all details

       }
    }
    </script>
<script>
  $(function () {
      $('#year').select2();
      $('#class_id').select2();
      $('#stream_id').select2();
      
loadYears(); 
loadPaymentModes();
loadClasses();

 $("#year").change(function(){
  loadClasses();   
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
      loadFeesData();      
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
            loadFeesData();
    }    
    });  
       });
       
       $("#save").click(function(){
           $('#new_form').modal('hide'); 
           saveData();
       });
  });
</script>
<script type="text/javascript">
    function loadFeesData(){

        var year=$("#year").val();
     
     $.ajax({
        url:'loadAddFees?year='+year,
        type:"post",
        dataType:"html",
        success:function(data){
    $("#exam_data").html(data);
    $("#example1").DataTable();
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
    function loadYears(){
     $.ajax({
        url:'loadYears',
        type:"post",
        dataType:"html",
        success:function(data){
        //streams drop down
       $("#year").html(data); 
       $('#year').select2(); 
    }    
    });    
    }
    function loadPaymentModes(){
     $.ajax({
        url:'loadPaymentModes',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#mode_id").html(data);  
        $('#mode_id').select2(); 
    }    
    });    
    }
    
   function loadStudentName(){
       var position=$("#clicked_pos").val();
     $.ajax({
        url:'loadStudentName?position='+position,
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $(".student_name").html(data);  
    }    
    });    
    }
    
    function loadPaymentHistory(){
    var position=$("#clicked_pos").val();
     $.ajax({
        url:'loadPaymentHistory?position='+position,
        type:"post",
        dataType:"html",
        success:function(data){

        $("#history_data").html(data);  
    }    
    });     
    }
    
   function pay(position){
    $("#clicked_pos").val(position);
    loadStudentName();
    $('#new_form').modal();
   }
   function history(position){
    $("#clicked_pos").val(position);
    loadStudentName();
    loadPaymentHistory();
    $('#history_form').modal();
   }
    </script>
</body>
</html>