<%@page import="java.util.Enumeration"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin - Bootstrap Admin Template</title>
        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <!--<script src="${pageContext.request.contextPath}/js/jquery.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/morris/morris-data.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/morris/morris.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins/morris/raphael.min.js"></script>        
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <!--Table-->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>-->
        <script src="//cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
        <link href="//cdn.datatables.net/1.10.5/css/jquery.dataTables.css" rel="stylesheet"/>
        <script src="${pageContext.request.contextPath}/js/ADS_Script.js"></script>
        <!--pop up--> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popupreset.css"> <!-- CSS reset -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popupstyle.css"> <!-- Resource style -->
        <script src="${pageContext.request.contextPath}/js/modernizr.js"></script> <!-- Modernizr -->
        <script src="${pageContext.request.contextPath}/js/main.js"></script> <!-- Resource jQuery -->
    </head>

    <body>
        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/MSP/Home.jsp">Tabeebk</a>
                </div>
                <!-- Top Menu Items -->
                <ul class="nav navbar-right top-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                        <ul class="dropdown-menu message-dropdown">
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading">
                                                <strong>${account.getDisplayName()}</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading">
                                                <strong>${account.getDisplayName()}</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-preview">
                                <a href="#">
                                    <div class="media">
                                        <span class="pull-left">
                                            <img class="media-object" src="http://placehold.it/50x50" alt="">
                                        </span>
                                        <div class="media-body">
                                            <h5 class="media-heading">
                                                <strong>${account.getDisplayName()}</strong>
                                            </h5>
                                            <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="message-footer">
                                <a href="#">Read All New Messages</a>
                            </li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${account.getDisplayName()} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${pageContext.request.contextPath}/MSP/Profile.jsp"><i class="fa fa-fw fa-user"></i> Profile</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${pageContext.request.contextPath}/LogoutControler"><i class="fa fa-fw fa-power-off"></i> Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav side-nav">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/MSP/Home.jsp"><i class="fa fa-fw fa-home"></i> Home</a>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-group"></i> Users <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="demo" class="collapse">
                                <li>
                                    <a href="${pageContext.request.contextPath}/ViewUsers">View Users</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/MSP/ViewTopSearch.jsp">View Top Search</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#plansid"><i class="fa fa-fw fa-edit"></i> Plans <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="plansid" class="collapse">
                                <li>
                                    <a href="${pageContext.request.contextPath}/AllPlans">ALL Plans</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/MSP/AddPlan.jsp">Add Plan</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/EditPlan">Edit Plan</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:;" data-toggle="collapse" data-target="#mspid"><i class="fa fa-fw fa-medkit"></i> MSP <i class="fa fa-fw fa-caret-down"></i></a>
                            <ul id="mspid" class="collapse">
                                <li>
                                    <a href="${pageContext.request.contextPath}/AllMsps">All Medical Service Providers</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/MyMsp">My Medical Service Providers</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>

            <div id="page-wrapper">

                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Edit Plan

                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="fa fa-home"></i>  <a href="${pageContext.request.contextPath}/MSP/Home.jsp">Home</a>
                                </li>
                                <li class="active">
                                    <i class="fa fa-edit"></i> Edit Plan
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <table id="example" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Plan Name</th>
                                    <th>Plan Description</th>
                                    <th></th>
                                    <!--<th></th>-->
                                    <th></th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="plan" items="${requestScope.plans}">
                                    <tr>
                                        <td><c:out value="${plan.getPlanName()}"/></td>
                                        <td><c:out value="${plan.getPlanDescription()}"/></td>
                                        <td><button type="button" class="btn btn-success" onclick="EditPlan('GetPlanDetails',${plan.getPlanId()})">Edit</button></td>
                                        <!--<td><button type="button" class="btn btn-danger" onclick="EditPlan('DeletePlan',${plan.getPlanId()})">Delete</button></td>-->
                                        <td>
                                            <!-- /.container-fluid -->
                                            <a href="#0" class="btn cd-popup-trigger">View Pop-up</a>
                                            <div class="cd-popup" role="alert">
                                                <div class="cd-popup-container">
                                                    <p>Are you sure you want to delete this element?</p>
                                                    <ul class="cd-buttons">
                                                        <li><a href="javascript:EditPlan('DeletePlan',${plan.getPlanId()});">Yes</a></li>
                                                        <li><a href="">No</a></li>
                                                    </ul>
                                                    <a href="#0" class="cd-popup-close img-replace">Close</a>
                                                </div> <!-- cd-popup-container -->
                                            </div> <!-- cd-popup -->
                                        </td>
                                    </tr>
                                </c:forEach>	
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->
        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>

</html>
