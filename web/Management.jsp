<%-- 
    Document   : Management
    Created on : Oct 19, 2016, 3:35:00 PM
    Author     : mwamb
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>School Management</title>
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
   
   <!--for notifications-->
   <link rel="stylesheet" href="notify/css/notify.css"/> 
   <link rel="stylesheet" href="notify/css/prettify.css"/>
   <!--end of for notifications-->
   
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
       Management Module
      </h1>
   <br>
    </section>
   <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
     <table>
            <tr class="spaceMenu">
             <td>
                <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg" id="base_year">Base Year</button>
            </td> 
             <td>
                <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg" id="classes">Classes</button>
            </td> 
             <td>
                <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg" id="streams">Streams</button>
            </td> 
             <td>
                <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg" id="exam_titles">Exam Titles</button>
            </td> 
             <td>
                <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg" id="exams">Exams</button>
            </td> 
             <td>
                <button style="margin-left:0%; width: auto;" class="btn btn-success btn-lg" id="grades">Grades</button>
            </td> 
            </tr>
     </table>
        <div id="main_content">
            
        </div>
    </section>
    <!-- /.content -->
  </div>
  
      <!--MODAL BASE YEAR-->
   <div class="modal fade" id="form_base_year" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">Set Base Year.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 40px; margin-bottom: 40px;">
   
    <!--    data here-->
    <select name="set_year" id="set_year" required="true" class="form-control" style="width:300px; margin-left: 30%;"> 
    <option value="">Loading years</option>
    </select>
    <br><br>
    <button type="button" class="btn btn-block btn-primary" id="save_base_year" style="height:40px; width: 200px; margin-left: 40%;"><b>Update Year.</b></button>
     
    <!--end of data-->
   
    </form>
      </div>
      
    </div>
  </div>
</div>
  
  <!--MODAL ADD CLASS-->
  <div class="modal fade" id="new_class" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">ADD NEW CLASSES.</p></h4>
      </div>
      <div class="modal-body" id="">
             <p><b>Note: </b><i>Add classes in ascending order.</i></p>
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    <input type="text" name="class_name" placeholder="Class Name e.g form 1, Form two etc"  class="form-control" id="class_name" required="true" maxlength="50" style="width:300px; margin-left: 30%;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-primary" id="save_class" style="height:40px; width: 200px; margin-left: 40%;"><b>Add Class.</b></button>
     
    <!--end of data-->
   
    </form>
      </div>
      
    </div>
  </div>
</div>
  
  <!--MODAL EDIT CLASS-->
  <div class="modal fade" id="edit_class" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">EDIT CLASS NAME.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    <input type="text" name="edit_class_name" placeholder="Class Name e.g form 1, Form two etc"  class="form-control" id="edit_class_name" required="true" maxlength="50" style="width:300px; margin-left: 30%;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-warning" id="update_class" style="height:40px; width: 200px; margin-left: 40%;"><b>Update Class.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>
  
  <!--MODAL ADD STREAM-->
  <div class="modal fade" id="new_stream" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">ADD NEW STREAM.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 24%; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    <select name="class_stream" id="class_stream" class="form-control" required="true" style="width:300px;"> 
        <option value="">Loading classes</option>
    </select>
    <br><br>
    <input type="text" name="stream_name" placeholder="Stream Name e.g 1 B, 2 G etc"  class="form-control" id="stream_name" required="true" maxlength="50" style="width:300px;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-success" id="add_stream" style="height:40px; width: 200px; margin-left: 12%;"><b>Add Stream.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>

 
  
    <!--MODAL EDIT STREAMS-->
  <div class="modal fade" id="edit_stream" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">EDIT STREAM NAME.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 10px; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    <input type="text" name="edit_stream_name" placeholder="Stream Name e.g 1 G, 4 Green etc"  class="form-control" id="edit_stream_name" required="true" maxlength="50" style="width:300px; margin-left: 30%;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-warning" id="update_stream" style="height:40px; width: 200px; margin-left: 40%;"><b>Update Stream.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>
    
  
  <!--MODAL ADD EXAM TITLE-->
  <div class="modal fade" id="new_exam_title" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">ADD NEW EXAM TITLE.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 24%; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    
    <input type="text" name="exam_title_name" placeholder="Exam title like Mid Term, End Term etc etc"  class="form-control" id="exam_title_name" required="true" maxlength="50" style="width:300px;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-success" id="add_exam_title" style="height:40px; width: 200px; margin-left: 12%;"><b>Add Exam Title.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>

    <!--MODAL EDIT EXAM TITLES-->
  <div class="modal fade" id="edit_exam_title" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">EDIT EXAM TITLE.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 24%; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    
    <input type="text" name="edit_exam_title_name" placeholder="Exam title like Mid Term, End Term etc etc"  class="form-control" id="edit_exam_title_name" required="true" maxlength="50" style="width:300px;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-success" id="update_exam_title" style="height:40px; width: 200px; margin-left: 12%;"><b>Edit Exam Title.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>
    
    
    <!--MODAL EDIT EXAM TITLES-->
  <div class="modal fade" id="new_exam" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">ADD EXAM.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 16%; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    <select name="exam_year" id="exam_year" required="true" class="form-control" style="width:300px; margin-left: 10%;"> 
    <option value="">Loading Years</option>
    </select>
    <br><br>
    <select name="term_id" id="term_id" required="true" class="form-control" style="width:300px; margin-left: 10%;"> 
    <option value="">Loading Term</option>
    </select>
    <br><br>
    <select name="title_id" id="title_id" required="true" class="form-control" style="width:300px; margin-left: 10%;"> 
    <option value="">Loading Exam title</option>
    </select>
    <br><br>
    <button type="button" class="btn btn-block btn-success" id="save_exam" style="height:40px; width: 200px; margin-left: 20%;"><b>Save Exam.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>

      
  <!--MODAL ADD GRADE-->
  <div class="modal fade" id="new_grade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">ADD NEW GRADE.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 24%; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    
    <input type="text" name="points" placeholder="1 , 2, 4 ... etc"  class="form-control" id="points" required="true" maxlength="50" style="width:300px;">      
               
    <br><br>
    <input type="text" name="grade" placeholder="A, A-,B+... etc"  class="form-control" id="grade" required="true" maxlength="50" style="width:300px;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-success" id="add_grade" style="height:40px; width: 200px; margin-left: 12%;"><b>Add Grade.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>

    <!--MODAL EDIT GRADES-->
  <div class="modal fade" id="edit_grades" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><img src="images/close.png" width="30px" height="30px" alt="X"></button>
        <h4 class="modal-title" id=""><p style="text-align: center; color:blue; font-weight: bolder;">EDIT GRADE.</p></h4>
      </div>
      <div class="modal-body" id="">
    <form action="#" method="POST" style="margin-left: 24%; margin-right: 100px; margin-top: 20px; margin-bottom: 40px;">
    <!--    data here-->
    
    <input type="text" name="edit_points" placeholder="12, 11 ... etc"  class="form-control" id="edit_points" required="true" maxlength="50" style="width:300px;">      
               
    <br><br>
    <input type="text" name="edit_grade" placeholder="A etc"  class="form-control" id="edit_grade" required="true" maxlength="50" style="width:300px;">      
               
    <br><br>
    <button type="button" class="btn btn-block btn-success" id="update_grade" style="height:40px; width: 200px; margin-left: 12%;"><b>Edit Grade.</b></button>
     
    <!--end of data-->
    </form>
      </div>
    </div>
  </div>
</div>
    
  <!-- /.content-wrapper -->
  <input type="hidden" readonly="true" id="se_id" value="">
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

<!--for notification management-->
<script src="notify/js/notify.js"></script>
<script src="notify/js/prettify.js"></script>
<!--//end notification-->

<script type="text/javascript">
    $(function () {
    loadBaseYear();    
      
    
//    savers and updators
$("#save_base_year").click(function(){
  saveBaseYear();  
});
  $("#classes").click(function(){
   loadClasses();   
  });
$("#save_class").click(function(){
  saveClass();  
});
  $("#update_class").click(function(){
        updateClass();
  }); 
 $("#streams").click(function(){
   loadStreams();   
  });
  $("#add_stream").click(function(){
  saveStream();    
  });
  $("#update_stream").click(function(){
   updateStream();
  }); 
  $("#exam_titles").click(function(){
   loadExamTitles();
  }); 
  $("#add_exam_title").click(function(){
   saveExamTitle();
  }); 
  $("#update_exam_title").click(function(){
   updateExamTitle();
  }); 
  $("#exams").click(function(){
   loadExams();
  }); 
  $("#save_exam").click(function(){
   saveExams();
  }); 
    $("#grades").click(function(){
   loadGrades();
  }); 
  $("#add_grade").click(function(){
   saveGrade();
  }); 
  $("#update_grade").click(function(){
   updateGrade();
  });
    });
    
//  Base year+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++BASE YEAR  
    function loadBaseYear(){
       $.ajax({
        url:'LoadBaseYears',
        type:"post",
        dataType:"html",
        success:function(year){
        $("#set_year").html(year); 
        $("#set_year").select2(); 
        $("#exam_year").html(year); 
        $("#exam_year").select2(); 
        }    
    });
   }
   
   function  saveBaseYear(){
    var base_year=$("#set_year").val();
   $.ajax({
        url:'NewBaseYear?year='+base_year,
        type:"post",
        dataType:"html",
        success:function(output){
$('#form_base_year').modal('hide');
$.notify(output, {type:"info"});
        }    
    });
   }
//   base year manager++++++++++++++++++++++++++++++++++++++++++++++++++++++++++BASE YEAR
// 
 // classes manager+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++CLASSES
   function loadClasses(){
       $.ajax({
        url:'LoadClasses',
        type:"post",
        dataType:"html",
        success:function(classes){
        $("#main_content").html(classes); 
        $("#example1").DataTable();
        $("#add_class").click(function(){
//        alert("called");
     $('#new_class').modal();

    });
        }    
    });
   }
   
   function  saveClass(){
    var class_name=$("#class_name").val();
    class_name=class_name.replace(" ","%20");
   $.ajax({
        url:'NewClasses?class_name='+class_name,
        type:"post",
        dataType:"html",
        success:function(output){
$('#new_class').modal('hide');
$.notify(output, {type:"info"});
loadClasses();
        }    
    });
   }
   
   
//   UPDATE CLASS HERE

function updateClass(){
     var class_name=$("#edit_class_name").val();
     var class_id=$("#se_id").val();
    class_name=class_name.replace(" ","%20");
   $.ajax({
        url:'UpdateClass?class_name='+class_name+'&class_id='+class_id,
        type:"post",
        dataType:"html",
        success:function(output){
$('#edit_class').modal('hide');
$.notify(output, {type:"info"});
  loadClasses();
        }    
    });
}

//   UPDATE CLASS HERE

function updateClass(){
     var class_name=$("#edit_class_name").val();
     var class_id=$("#se_id").val();
    class_name=class_name.replace(" ","%20");
   $.ajax({
        url:'UpdateClass?class_name='+class_name+'&class_id='+class_id,
        type:"post",
        dataType:"html",
        success:function(output){
$('#edit_class').modal('hide');
$.notify(output, {type:"info"});
  loadClasses();
        }    
    });
}
   // end of classes manager++++++++++++++++++++++++++++++++++++++++++++++++++++CLASSES
   
//SAVE STREAM
   function  saveStream(){
    var class_id=$("#class_stream").val();
    var stream_name=$("#stream_name").val();
    stream_name=stream_name.replace(" ","%20");
   $.ajax({
        url:'NewStream?stream_name='+stream_name+'&class_id='+class_id,
        type:"post",
        dataType:"html",
        success:function(output){
$('#new_stream').modal('hide');
$.notify(output, {type:"info"});
loadStreams();
        }    
    });
   }
   function loadStreams(){
   $.ajax({
        url:'LoadStreams',
        type:"post",
        dataType:"html",
        success:function(streams){
        $("#main_content").html(streams);
        $("#example1").DataTable();
        $("#add_stream").click(function(){
        load_class_stream();    
     $('#new_stream').modal(); 
    });
        }    
    });    
   }
   
       function load_class_stream(){
     $.ajax({
        url:'loadClasses',
        type:"post",
        dataType:"html",
        success:function(data){
            //streams drop down
        $("#class_stream").html(data);  
        $('#class_stream').select2(); 
    }    
    });    
    }
    
    function updateStream(){
    var stream_id=$("#se_id").val();
    var stream_name=$("#edit_stream_name").val();
    stream_name=stream_name.replace(" ","%20");
   $.ajax({
        url:'UpdateStream?stream_name='+stream_name+'&&stream_id='+stream_id,
        type:"post",
        dataType:"html",
        success:function(output){
            $('#edit_stream').modal('hide');
            $.notify(output, {type:"info"});
            loadStreams();
        }    
    });    
    }
//  end of streams here  
    
//SAVE STREAM
   function  saveExamTitle(){
    var exam_title_name=$("#exam_title_name").val();
    exam_title_name=exam_title_name.replace(" ","%20");
   $.ajax({
        url:'NewExamTitle?exam_title_name='+exam_title_name,
        type:"post",
        dataType:"html",
        success:function(output){
$('#new_exam_title').modal('hide');
$.notify(output, {type:"info"});
loadExamTitles();
        }    
    });
   }
    
       function loadExamTitles(){
   $.ajax({
        url:'LoadExamTitles',
        type:"post",
        dataType:"html",
        success:function(examtitles){
        $("#main_content").html(examtitles);
        $("#example1").DataTable();
        $("#add_exam_title").click(function(){    
     $('#new_exam_title').modal(); 
    });
        }    
    });    
   }
   
    function updateExamTitle(){
    var exam_title_id=$("#se_id").val();
    var exam_title_name=$("#edit_exam_title_name").val();
    exam_title_name=exam_title_name.replace(" ","%20");
   $.ajax({
        url:'UpdateExamTitle?exam_title_name='+exam_title_name+'&&exam_title_id='+exam_title_id,
        type:"post",
        dataType:"html",
        success:function(output){
            $('#edit_exam_title').modal('hide');
            $.notify(output, {type:"info"});
            loadExamTitles();
        }    
    });    
    }
//    end of exam titles
//SAVE EXAMS
   function  saveExams(){
    var year=$("#exam_year").val();
    var term_id=$("#term_id").val();
    var title_id=$("#title_id").val();
   $.ajax({
        url:'NewExams?year='+year+'&&term_id='+term_id+'&&title_id='+title_id,
        type:"post",
        dataType:"html",
        success:function(output){
$('#new_exam').modal('hide');
$.notify(output, {type:"info"});
loadExams();
        }    
    });
   }
    function loadExams(){
   $.ajax({
        url:'LoadExams',
        type:"post",
        dataType:"html",
        success:function(exams){
        $("#main_content").html(exams);
        $("#example1").DataTable();
        $("#add_exam").click(function(){    
        $('#new_exam').modal(); 
        loadTerms();
        loadExamTitlesOnly();
    });
        }    
    });    
   }
   
   function loadTerms(){
   $.ajax({
        url:'loadTerms',
        type:"post",
        dataType:"html",
        success:function(terms){
        $("#term_id").html(terms); 
        $("#term_id").select2(); 
        
    }
    });    
    }
   function loadExamTitlesOnly(){
   $.ajax({
        url:'loadExamTitles',
        type:"post",
        dataType:"html",
        success:function(terms){
        $("#title_id").html(terms); 
        $("#title_id").select2(); 
        
    }
        });    
    }
    

//    end of exam titles

   
    function loadGrades(){
   $.ajax({
        url:'LoadGrades',
        type:"post",
        dataType:"html",
        success:function(grades){
        $("#main_content").html(grades);
        $("#example1").DataTable();
        $("#add_grade").click(function(){    
        $('#new_grade').modal(); 
    });
        }    
    });    
   }
   
   function  saveGrade(){
    var points=$("#points").val();
    var grade=$("#grade").val();
    grade=grade.replace(" ","%20");
    grade=grade.replace("+","_");
   $.ajax({
        url:'NewGrade?points='+points+'&&grade='+grade,
        type:"post",
        dataType:"html",
        success:function(output){
$('#new_grade').modal('hide');
$.notify(output, {type:"info"});
loadGrades();
        }    
    });
   }
          
    function updateGrade(){
    var points_id=$("#se_id").val();
    var points=$("#edit_points").val();
    var grade=$("#edit_grade").val();
    grade=grade.replace(" ","%20");
    grade=grade.replace("+","_");
   $.ajax({
        url:'UpdateGrade?points_id='+points_id+'&&points='+points+'&&grade='+grade,
        type:"post",
        dataType:"html",
        success:function(output){
            $('#edit_grades').modal('hide');
            $.notify(output, {type:"info"});
            loadGrades();
        }    
    });    
    }
 
    </script>
    <script type="text/javascript">
    $("#base_year").click(function(){
     $("#main_content").html("");    
     $('#form_base_year').modal(); 
    });
//    $("#add_class").click(function(){
//        alert("called");
////     $('#new_class').modal(); 
//    });
    </script>
    <script>
      $(function(){
          
      });  
      function updator_class(pos){
      var class_name=$("#"+pos).html();
      $("#edit_class_name").val(class_name);
      $("#se_id").val($("#ival_"+pos).val());
      $("#edit_class").modal();
      } 
      function deleter_class(pos){
      var class_id=$("#ival_"+pos).val();
      $.ajax({
        url:'DeleteClass?class_id='+class_id,
        type:"post",
        dataType:"html",
        success:function(output){
            $.notify(output, {type:"info"});
            loadClasses();
        }    
    });
      }
      
      function updator_stream(pos){
      var class_name=$("#"+pos).html();
      $("#edit_stream_name").val(class_name);
      $("#se_id").val($("#ival_"+pos).val());
      $("#edit_stream").modal();
      } 
      
      
      function deleter_stream(pos){
      var stream_id=$("#ival_"+pos).val();
      $.ajax({
        url:'DeleteStream?stream_id='+stream_id,
        type:"post",
        dataType:"html",
        success:function(output){
            $.notify(output, {type:"info"});
            loadStreams();
        }
    }
    );
      }   
      function updator_exam_titles(pos){
      var exam_title_name=$("#"+pos).html();
      $("#edit_exam_title_name").val(exam_title_name);
      $("#se_id").val($("#ival_"+pos).val());
      $("#edit_exam_title").modal();
      } 
      
      
      function deleter_exam_title(pos){
      var exam_title_id=$("#ival_"+pos).val();
      $.ajax({
        url:'DeleteExamTitle?exam_title_id='+exam_title_id,
        type:"post",
        dataType:"html",
        success:function(output){
            $.notify(output, {type:"info"});
            loadExamTitles();
        }    
    });
      }
      function deleter_exam(pos){
      var exam_id=$("#ival_"+pos).val();
      $.ajax({
        url:'DeleteExam?exam_id='+exam_id,
        type:"post",
        dataType:"html",
        success:function(output){
            $.notify(output, {type:"info"});
            loadExams();
        }    
    });
      }
      
     function updator_grade(pos){
      var points=$("#points_"+pos).html();
      var grade=$("#grade_"+pos).html();
      $("#edit_points").val(points);
      $("#edit_grade").val(grade);
      $("#se_id").val($("#ival_"+pos).val());
      $("#edit_grades").modal();
      } 
     function deleter_grade(pos){
      var points_id=$("#ival_"+pos).val();
      $.ajax({
        url:'DeleteGrade?points_id='+points_id,
        type:"post",
        dataType:"html",
        success:function(output){
            $.notify(output, {type:"info"});
            loadGrades();
        }    
    });
      }
      
    </script>
    
    
</body>
</html>

