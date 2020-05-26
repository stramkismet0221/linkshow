<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>用户信息</title>
</head>
<body class="fix-sidebar">
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <%@ include file="/WEB-INF/views/include/header.jsp" %>
    </nav>
    <div class="navbar-default sidebar" role="navigation">
        <jsp:include page="/WEB-INF/views/include/left.jsp">
            <jsp:param value="/smoke/getuserlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">用户信息</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">用户管理</a></li>
                        <li class="active">用户信息</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" class="form-material form-horizontal" data-toggle="validator">

                                    <div class="form-group">
                                        <label class="col-md-12">渠道商名称</label>
                                        <div class="col-sm-12">
                                            <input type="text" class="form-control" value="${sellSmokeAgent.name}" disabled/>
                                            <input type="hidden" name="agentId" value="${sellSmokeAgent.id}"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">用户ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">用户名</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.userName}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">用户姓名</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.realName}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">手机号</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.mobile}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Email</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.email}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">用户锁状态</label>
                                        <div class="col-md-12">
                                            <c:if test="${user.locked == 0}">
                                                <input type="text" class="form-control" value="未锁定" disabled/>
                                            </c:if>
                                            <c:if test="${user.locked == 1}">
                                                <input type="text" class="form-control" value="已锁定" disabled/>
                                            </c:if>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-5"></div>
                                    <div class="col-lg-2 col-sm-4 col-xs-12">
                                        <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
                                            <span style="vertical-align: inherit;">返回</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-sidebar">
                <%@include file="/WEB-INF/views/include/right.jsp"%>
            </div>
        </div>
        <footer class="footer text-center">
            <%@include file="/WEB-INF/views/include/footer.jsp"%>
        </footer>
    </div>
</div>
</body>
</html>