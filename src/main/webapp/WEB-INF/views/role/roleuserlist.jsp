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
    <title>角色管理</title>
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
            <jsp:param value="/role/getrolelist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">用户列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">角色管理</a></li>
                        <li class="active">用户列表</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <form id="search">
                        <div class="row">
                            <div class="col-md-12" style="width:100%; display: inline">
                                <h5 style="width: 50%;display: inline">所属系统：<label style="font-weight: 600">${systemName}</label></h5>
                                &nbsp;&nbsp;&nbsp;
                                <h5 style="width: 50%;display: inline">所属角色：<label style="font-weight: 600">${roleName}</label></h5>
                            </div>
                            <div class="col-md-4" style="width:17%">
                                <h5 class="m-t-30 m-b-10">用户名</h5>
                                <input type="text" class="form-control" id="userName" maxlength="50" placeholder="请输入用户名" value="${user.userName}"/>
                            </div>
                            <div class="col-md-4" style="width:17%">
                                <h5 class="m-t-30 m-b-10">用户姓名</h5>
                                <input type="text" class="form-control" id="realName" maxlength="50" placeholder="请输入用户姓名" value="${user.realName}"/>
                            </div>
                            <div class="col-md-4" style=" width:10%">
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                            </div>
                            <div class="col-md-4" style=" width:10%">
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" value="返回" onclick="location.href='${pageContext.request.contextPath}/role/getrolelist'"/>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6" style="height:500px;overflow:auto" id="unselect">

                </div>

                <div class="col-sm-6" style="height:500px;overflow:auto" id="select">

                </div>
            </div>
            <div class="right-sidebar">
                <%@ include file="/WEB-INF/views/include/right.jsp" %>
            </div>
        </div>
        <footer class="footer text-center">
            <%@ include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
</div>
<script src="${pageContext.request.contextPath}/common/js/tool.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        loadRoleUsers();
    });

    // 加载未选中用户、已选中用户列表
    function loadRoleUsers() {
        var systemId = ${systemId};
        var roleId = ${roleId};
        var userName = $("#userName").val();
        var realName = $("#realName").val();
        var data = {systemId:systemId,roleId:roleId,userName:userName,realName:realName};
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/role/unselectuser",
            dataType: "html",
            data: data,
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 0.5, 400);
            },
            success: function (result) {
                $("#unselect").html(result);
                hide_loading(200);
            }
        });
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/role/selectuser",
            dataType: "html",
            data: data,
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 1.5, 400);
            },
            success: function (result) {
                $("#select").html(result);
                hide_loading(200);
            }
        })
    }
    // 查询未加入该角色的用户
    function search() {
        var userName = $("#userName").val();
        $("input[name='userName']").val(userName);
        var realName = $("#realName").val();
        $("input[name='realName']").val(realName);
        loadRoleUsers();
    }

</script>
</body>
</html>