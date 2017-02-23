<!DOCTYPE html>
<html>
<head>
</head>
<body class="hold-transition sidebar-mini skin-red-light">
   <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
<!--      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>User</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>-->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header"><b>MAIN ENTRIES</b></li>

        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Parent</span>
            <span class="label label-primary pull-right">1</span>
          </a>
          <ul class="treeview-menu">
              <li><a href="Parents.jsp"><i class="fa fa-circle-o"></i> Manage Parents</a></li>
             </ul>
        </li>
        <!--STUDENT MANAGEMENT-->
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Students</span>
            <span class="label label-primary pull-right">4</span>
          </a>
          <ul class="treeview-menu">
              <li><a href="Students.jsp"><i class="fa fa-circle-o"></i> Manage Students</a></li>
              <li><a href="Exams.jsp"><i class="fa fa-circle-o"></i> Student Exams</a></li>
              <li><a href="Fees.jsp"><i class="fa fa-circle-o"></i> Student Fees</a></li>
              <li><a href="LeaveForm.jsp"><i class="fa fa-circle-o"></i> Leave Form</a></li>
             </ul>
        </li>
       <!--STUDENT MANAGEMENT-->
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Teachers</span>
            <span class="label label-primary pull-right">1</span>
          </a>
          <ul class="treeview-menu">
              <li><a href="Teachers.jsp"><i class="fa fa-circle-o"></i> Manage Teachers</a></li>
             </ul>
        </li>

        <li class="header"><b>NOTIFICATIONS</b></li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Send SMS</span>
            <span class="label label-primary pull-right">3</span>
          </a>
          <ul class="treeview-menu">
              <li><a href="#"><i class="fa fa-circle"></i>Examination Results SMS</a></li>
             
             </ul>
            <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle"></i>Fee Balance SMS</a></li>
             
             </ul>
        </li>
        <li class="header"><b>MANAGEMENT</b></li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Manage Entries</span>
            <span class="label label-primary pull-right">3</span>
          </a>
          <ul class="treeview-menu">
              <li><a href="#"><i class="fa fa-circle"></i>Management</a></li>
             
             </ul>
            
        </li>
        
        <li class="header"><b>REPORTS MODULE</b></li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>System Reports</span>
            <span class="label label-primary pull-right">3</span>
          </a>
          <ul class="treeview-menu">
              <li><a href="IndividualPerformanceChart.jsp"><i class="fa fa-circle"></i>Performance Chart</a></li>
             
             </ul>
            <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle"></i>Progress Report</a></li>
             
             </ul>
            <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle"></i>Absentees Report</a></li>
             </ul>
        </li>
        
        <li class="header"><b><a href="logout">LOGOUT</a></b></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
</body>
</html>
