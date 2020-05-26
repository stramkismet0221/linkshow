<%@ page import="com.ddzhuan.manage.common.enums.PowerTypeEnum" %>
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
    <title>权限信息</title>
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
            <jsp:param value="/power/getpowerlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">权限信息</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">权限管理</a></li>
                        <li class="active">权限信息</li>
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
                                        <label class="col-md-12">所属系统</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${system.name}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">权限ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${power.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">权限编码</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${power.code}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">权限名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${power.name}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">权限描述</label>
                                        <div class="col-md-12">
                                            <textarea type="text" class="form-control" disabled>${power.description}</textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">权限类型</label>
                                        <div class="col-md-12">
                                            <c:if test="${power.type == 1}">
                                                <input type="text" class="form-control" value="<%=PowerTypeEnum.SYSTEMLOGIN.getName()%>" disabled/>
                                            </c:if>
                                            <c:if test="${power.type == 2}">
                                                <input type="text" class="form-control" value="<%=PowerTypeEnum.MENU.getName()%>" disabled/>
                                            </c:if>
                                            <c:if test="${power.type == 3}">
                                                <input type="text" class="form-control" value="<%=PowerTypeEnum.OPERATE.getName()%>" disabled/>
                                            </c:if>
                                            <c:if test="${power.type == 4}">
                                                <input type="text" class="form-control" value="<%=PowerTypeEnum.SYSTEMMANAGE.getName()%>" disabled/>
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