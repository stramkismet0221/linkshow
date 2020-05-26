<%@ page import="com.ddzhuan.manage.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%
    User user = (User) request.getSession().getAttribute("sysuser");
    String userName = user == null ? "" : user.getUserName();
    String realName = user == null ? "" : user.getRealName();
    String email = user == null ? "" : user.getEmail();
    String mobile = user == null ? "" : user.getMobile();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath}/common/adm/plugins/images/favicon.png">
    <title>暂无数据</title>
    <link href="${pageContext.request.contextPath}/common/adm/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/common/adm/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/common/adm/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/common/adm/css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/jquery/dist/jquery.min.js"></script>
</head>

<body class="fix-sidebar">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
        </svg>
    </div>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header">
                <div class="top-left-part">
                    <a class="logo" href="javascript:void(0);">
                        <b>
                            <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-logo.png"
                                 alt="home" class="dark-logo"/>
                            <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-logo-dark.png"
                                 alt="home" class="light-logo"/>
                        </b>
                        <span class="hidden-xs">
                    <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-text.png" alt="home"
                         class="dark-logo"/>
                    <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-text-dark.png" alt="home"
                         class="light-logo"/>
                </span>
                    </a>
                </div>
                <ul class="nav navbar-top-links navbar-right pull-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img
                                src="${pageContext.request.contextPath}/common/adm/plugins/images/users/varun.jpg"
                                alt="user-img" width="36" class="img-circle"><b class="hidden-xs"><%=userName%>
                        </b><span class="caret"></span> </a>
                        <ul class="dropdown-menu dropdown-user animated flipInY">
                            <li>
                                <div class="dw-user-box">
                                    <div class="u-img"><img
                                            src="${pageContext.request.contextPath}/common/adm/plugins/images/users/varun.jpg"
                                            alt="user"/></div>
                                    <div class="u-text">
                                        <h4><%=realName==null?"":realName%>
                                        </h4>
                                        <p class="text-muted">Email：<%=email==null?"":email%>
                                        </p>
                                        <p class="text-muted">Phone：<%=mobile==null?"":mobile%>
                                        </p>
                                    </div>
                                </div>
                            </li>
                            <li>
                            <a href="${pageContext.request.contextPath}/user/updatepwd"><i class="ti-settings"></i>&nbsp;修改密码</a>
                            <a href="${pageContext.request.contextPath}/syslogout"><i class="fa fa-power-off"></i>&nbsp;退出登录</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="navbar-default sidebar" role="navigation">
            <%@include file="/WEB-INF/views/include/left.jsp" %>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title"></h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#"></a></li>
                            <li class="active"></li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title m-b-20"></h3>
                            <div class="panel">
                                <div class="table-responsive">
                                    暂无数据。。。
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="right-sidebar">
                    <%@include file="/WEB-INF/views/include/right.jsp" %>
                </div>
            </div>
            <footer class="footer text-center">
                <%@include file="/WEB-INF/views/include/footer.jsp" %>
            </footer>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/common/adm/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <script src="${pageContext.request.contextPath}/common/adm/js/jquery.slimscroll.js"></script>
    <script src="${pageContext.request.contextPath}/common/adm/js/waves.js"></script>
    <script src="${pageContext.request.contextPath}/common/adm/js/custom.js"></script>
</body>
</html>