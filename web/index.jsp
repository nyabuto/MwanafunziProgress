<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Shule Application</title>
  <link rel="shortcut icon" href="images/school_logo.png">
   <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
  <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
  <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
  <link rel="stylesheet" href="dist/css/others.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
    <div class="box-body" style="background-color: #e7f1fe;">
  <!--END MENU-->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="margin-left: 300px; margin-right:400px; margin-bottom:0px;">
   
    <!-- CONTENTS HERE------------------------------------ -->
        <div id="data_content" style="margin-left: 150px; margin-top: 150px;">
        <form action="#" method="post" style="margin: 50px;">
             <div class="form-group has-feedback" style="margin-left: 0px;">
                 <b style="font-family: Papyrus, fantasy; font-size: 40px; text-align: center; margin-left: 0px;">Mwanafunzi Progress</b>
      </div>
            
            <div class="form-group has-feedback" style="margin-left: 150px;">
          <b> Enter Log in details to access system.</b>
      </div>
            
      <div class="form-group has-feedback">
          <input type="text" class="form-control" required="true" placeholder="Username" name="username" id="username">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
          <input type="password" class="form-control" required="true" placeholder="Password" name="password" id="password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
<!--            <label>
              <input type="checkbox"> Remember Me
            </label>-->
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" id="sign_in" class="btn btn-primary btn-block btn-flat">Sign In</button>
        </div>
        <!-- /.col -->
      </div>
    </form>
        </div>
  </div>
  <!-- /.content-wrapper -->
  <%@include file="footer.jsp" %>

  <div class="control-sidebar-bg"></div>
</div>
    
<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<script src="dist/js/demo.js"></script>
<script src="dist/js/notify.js"></script>
<script type="text/javascript">
$(function(){
 $("form").submit(function(){
     var return_value=false;
  var username=$("#username").val();
  var password=$("#password").val();
  $.ajax({
        url:'login?username='+username+"&password="+password,
        type:"post",
        dataType:"html",
        success:function(data){
            data=data.trim();
            if(data==1){
                //                redirect to another page
                window.location="login_redirect";
                return_value=true;
            }
            else{
                $("#password").notify("Wrong Username and Password Combination.");
                return_value=false;
            }
    }    
    });
 return return_value; 
 });
 
}) ;   
    
</script>
</body>
</html>
