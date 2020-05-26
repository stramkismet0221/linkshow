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
    <title>用户管理</title>
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
            <jsp:param value="/user/getuserlist" name="visitUrl"/>
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
                        <li><a href="#">用户管理</a></li>
                        <li class="active">用户列表</li>
                    </ol>
                </div>
            </div>
            <!-- /row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="row">
                            <div class="col-md-4">
                                <h5 class="m-t-30 m-b-10">用户名</h5>
                                <input type="text" class="form-control" id="userName" maxlength="50" placeholder="请输入用户名" value="${user.userName}"/>
                            </div>
                            <div class="col-md-4">
                                <h5 class="m-t-30 m-b-10">用户姓名</h5>
                                <input type="text" class="form-control" id="realName" maxlength="50" placeholder="请输入用户姓名" value="${user.realName}"/>
                            </div>
                            <div class="col-md-4">
                                <h5 class="m-t-30 m-b-10">手机号</h5>
                                <input type="text" class="form-control" id="mobile" maxlength="50" placeholder="请输入手机号" value="${user.mobile}" />
                            </div>
                            <div class="col-md-4">
                                <h5 class="m-t-30 m-b-10">Email</h5>
                                <input type="text" class="form-control" id="email" maxlength="50" placeholder="请输入Email" value="${user.email}" />
                            </div>
                            <div class="col-md-4">
                                 <h5 class="m-t-30 mb-10">用户锁定状态<label  for="locked"></label></h5>
                                <select class="form-control" id="locked">
                                    <option value="" <c:if test="${user.locked == null}">selected</c:if>>全部</option>
                                    <option value="0" <c:if test="${user.locked == 0}">selected</c:if>>未锁定</option>
                                    <option value="1" <c:if test="${user.locked == 1}">selected</c:if>>已锁定</option>
                                </select>
                            </div>
                            <div class="col-md-1" >
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/user/getuserlist" id="form">
                <input type="hidden" name="userName" value="" />
                <input type="hidden" name="realName" value="" />
                <input type="hidden" name="mobile" value="" />
                <input type="hidden" name="email" value="" />
                <input type="hidden" name="locked" value="" />
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4">
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/user/adduser'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>用户ID</th>
                                            <th>用户名</th>
                                            <th>用户姓名</th>
                                            <th>手机号</th>
                                            <th>Email</th>
                                            <th>用户锁状态</th>
                                            <th>创建时间</th>
                                            <th>修改时间</th>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${userList}" var="user">
                                            <tr>
                                                <td>${user.id}</td>
                                                <td title="${user.userName}">
                                                    <c:set var="str" value="${user.userName}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 15}">
                                                            <c:out value="${fn:substring(str, 0, 15)}..." />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td title="${user.realName}">
                                                    <c:set var="str" value="${user.realName}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 15}">
                                                            <c:out value="${fn:substring(str, 0, 15)}..." />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${user.mobile}</td>
                                                <td title="${user.email}">
                                                    <c:set var="str" value="${user.email}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 20}">
                                                            <c:out value="${fn:substring(str, 0, 20)}..." />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <c:if test="${user.locked == 0}">
                                                    <td>未锁定</td>
                                                </c:if>
                                                <c:if test="${user.locked == 1}">
                                                    <td>已锁定</td>
                                                </c:if>
                                                <td>
                                                    <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${user.modifyTime}" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                    <div>
                                                        <div style="display: inline-block; width:15%">
                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/user/getuserinfobyid?userId=${user.id}&type=1'" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:15%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/user/getuserinfobyid?userId=${user.id}&type=0'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:15%">
                                                            <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${user.id});" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:15%">
                                                            <c:if test="${user.locked == 1}">
                                                                <input type="button" class="btn btn-block btn-danger btn-xs" value="解锁" onclick="lock(${user.id},0);" />
                                                            </c:if>
                                                            <c:if test="${user.locked == 0}">
                                                                <input type="button" class="btn btn-block btn-xs" style="background: #53e69d;color: #fff" value="锁定" onclick="lock(${user.id},1);" />
                                                            </c:if>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:24%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="设备列表" onclick="location.href='${pageContext.request.contextPath}/user/getterminalsbyuserid?userId=${user.id}'" />
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="9">
                                                <div class="text-right">
                                                    <%@ include file="/WEB-INF/views/include/pagination.jsp" %>
                                                </div>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="right-sidebar">
                <%@ include file="/WEB-INF/views/include/right.jsp" %>
            </div>
        </div>
        <footer class="footer text-center">
            <%@ include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var startTime = "${startTime}";
        var endTime = "${endTime}";
        if (startTime != "" && endTime != "") {
            $("input[name='daterange']").val(startTime + " - " + endTime);
        } else {
            $("input[name='daterange']").val("");
        }

    });
    // 查询
    function search() {
        var userName = $("#userName").val();
        $("input[name='userName']").val(userName);
        var realName = $("#realName").val();
        $("input[name='realName']").val(realName);
        var mobile = $("#mobile").val();
        $("input[name='mobile']").val(mobile);
        var email = $("#email").val();
        $("input[name='email']").val(email);
        var locked = $("#locked").val();
        $("input[name='locked']").val(locked);
        $("#form").submit();
    }
    // 删除
    function remove(id) {
        swal({
            title: "确定删除吗?",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/user/deleteuser",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id,"status":0},
                    success : function(result) {
                        if (result.success) {
                            swal({title : "删除成功",
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/user/getuserlist";
                            });
                        }
                    },
                    error : function() {
                        alert("删除失败");
                    }
                });
            }
        });
    }

    function lock(id,locked) {

        swal({
            title: "是否锁定/解锁用户?",
            showCancelButton: true,
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/user/modifyuser",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id,"locked":locked},
                    success : function(result) {
                        var title;
                        if (locked === 1){
                            title = "锁定成功";
                        }
                        if (locked === 0){
                            title = "解锁成功";
                        }
                        if (result.success) {
                            swal({title : title,
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/user/getuserlist";
                            });
                        }
                    },
                    error : function() {
                        if (locked === 1){
                            alert("锁定失败");
                        }
                        if (locked === 0) {
                            alert("解锁失败");
                        }

                    }
                });
            }
        });
    }
</script>
</body>
</html>