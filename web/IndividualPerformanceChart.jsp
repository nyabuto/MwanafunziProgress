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
  <title>Students Performance Report</title>
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
       Student Performance Report
      </h1>
   <br>
    </section>
   <b id="notifier" style="margin-left: 40%; margin-top: 25%;"></b>
    <!-- CONTENTS HERE------------------------------------ -->
    <section class="content">
        <table>
            <tr class="spaceUnder">
              <td>
                  <select name="class_id" id="class_id" required="true" class="form-control" style="width:200px;"> 
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
                      <select name="chart_type" id="chart_type" required="true" class="form-control" style="width:200px;"> 
                          <option value="line" selected="true">Line Graph</option>
                        <option value="column">Column Chart</option>
                        <option value="bar">Bar Chart</option>
                   </select>
                  </td>
            </tr>
        </table>
        <br>
        <div class="box">
        
               <div>
                   <br>
                <div id="parents_data">
                    <div id="graphArea" style="min-width: 1000px; max-width: 1300px; height: 600px; margin: 0 auto">
                    <p style="margin-left: 40%"><b>Data Here</b> </p>        
                    </div>   
                    
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

  <script src="highcharts/js/highcharts.js"></script>
   <!--USE OFLINE SERVER TO EXPORT THE DATA-->
<!--https://github.com/A----/highcharts-export-clientside-->
<!--http://jsfiddle.net/pscjzhe4/1/-->

<script src="canvas/exporting.js"></script>
<script src="canvas/canvas-tools.js"></script>
<script src="canvas/export-csv.js"></script>
<script src="canvas/jspdf.min.js"></script>

<!-- This module, after the dependencies -->
<script src="canvas/highcharts-export-clientside.js"></script>




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
    }    
    });  
       });
       
         //  STREAM SELECTED
    $("#student_id").change(function(){
       studentDetails();   
       });
        $("#chart_type").change(function(){
       studentDetails();   
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
    function loadServerData(student_name){
     $.ajax({
        url:'IndividualPerformanceCharts',
        type:"post",
        dataType:"text",
        success:function(output){
          drawGraph(student_name,output); 
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
loadServerData(student_name);
    }    
    });     
     }
    </script>
    		<script type="text/javascript">
function drawGraph(student_name,output) {
var outputArray=output.split("^^");
var titles=outputArray[0].split("@@");
var data=outputArray[1].split("##");
var dataSize=titles.length;

var chart_type=$("#chart_type").val();
         var options={
            chart: {
            type: chart_type //column,line,bar
        },
        title: {
            text: 'Report for : '+student_name
        },
        subtitle: {
            text: 'Graph of performance against days of school. : '
        },
        
        xAxis: {
            categories: ['TERM 1', 'TERM 2', 'TERM 3'],
                title: {
                text: "SCHOOL TERMS"
            }
        },
        yAxis: {
            min: 1,
            title: {
                text: 'Total Marks/ Days off school',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ''
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
//        
//        legend: {
//            layout: 'horizontal',
//            align: 'right',
//            verticalAlign: 'top',
//            x: -40,
//            y: 70,
//            floating: true,
//            borderWidth: 1,
//            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
//            shadow: true
//        },
        credits: {
            enabled: false
        },
         series: []
    };
    
    for(var a=0;a<dataSize;a++){
        var jsonobj=data[a];
var variableName=titles[a];
var variableValues=JSON.parse("["+jsonobj+"]");
//alert(variableName+":"+variableValues);
    options.series.push({
    name: variableName,
    data: variableValues
});
            
        }  
   $('#graphArea').highcharts(options); 
}
		</script>
    <script type="text/javascript">         
        </script>
       
</body>
</html>

