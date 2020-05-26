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
    <title>角色管理</title>
</head>

<body class="fix-sidebar">
    <!-- Preloader -->
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
                        <h4 class="page-title">角色列表</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">角色管理</a></li>
                            <li class="active">角色列表</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-md-3">
                                    <h5 class="m-t-30 m-b-10">角色名称</h5>
                                    <input type="text" class="form-control" id="name" maxlength="50" placeholder="请输入角色名称" value="${role.name}"/>
                                </div>
                                <div class="col-md-3">
                                    <h5 class="m-t-30 m-b-10">角色唯一编码</h5>
                                    <input type="text" class="form-control" id="code" maxlength="50" placeholder="请输入角色唯一编码" value="${role.code}"/>
                                </div>
                                <div class="col-md-3" >
                                    <h5 class="m-t-30 mb-10">所属系统<label  for="system"></label></h5>
                                    <select class="form-control" id="system">
                                        <option value="" >全部</option>
                                        <c:forEach items="${systems}" var="data">
                                            <option value="${data.id}"
                                                    <c:if test="${data.id == role.systemId}">selected</c:if>>
                                                <c:set var="str" value="${data.name}"></c:set>
                                                <c:choose>
                                                    <c:when test="${fn:length(str) > 10}">
                                                        <c:out value="${fn:substring(str, 0, 10)}..." />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${str}" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </option>
                                        </c:forEach>
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
                <form action="${pageContext.request.contextPath}/role/getrolelist" id="form">
                    <input name="name" type="hidden" value="" />
                    <input name="code" type="hidden" value="" />
                    <input name="systemId" type="hidden" value=""/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4">
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/role/toaddrole?systemId=${role.systemId}'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                            <tr>
                                                <th >角色ID</th>
                                                <th >角色名称</th>
                                                <th >角色唯一编码</th>
                                                <th >所属系统</th>
                                                <th >创建时间</th>
                                                <th >修改时间</th>
                                                <th style=" width: 18%;">操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${roleList}" var="role">
                                            <tr>
                                                <td>${role.id}</td>
                                                <td title="${role.name}">
                                                    <c:set var="str" value="${role.name}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 15}">
                                                            <c:out value="${fn:substring(str, 0, 15)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <td title="${role.code}">
                                                    <c:set var="str" value="${role.code}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 20}">
                                                            <c:out value="${fn:substring(str, 0, 20)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <c:forEach items="${systems}" var="data">
                                                        <c:if test="${data.id == role.systemId}">
                                                            <c:set var="str" value="${data.name}"></c:set>
                                                            <c:choose>
                                                                <c:when test="${fn:length(str) > 10}">
                                                                    <c:out value="${fn:substring(str, 0, 10)}......" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:out value="${str}" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${role.createTime}" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${role.modifyTime}" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                    <div>
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/role/getroleinfo?roleId=${role.id}&type=0&systemId=${role.systemId}'" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/role/getroleinfo?roleId=${role.id}&type=1&systemId=${role.systemId}'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${role.id});" />
                                                        </div>
                                                        <%--&nbsp;--%>
                                                        <%--<div style="display: inline-block; width:18%">--%>
                                                            <%--<input type="button" class="btn btn-block btn-info btn-xs" value="赋权" onclick="location.href='${pageContext.request.contextPath}/role/getmenutreev2?roleId=${role.id}'" />--%>
                                                        <%--</div>--%>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="用户" onclick="location.href='${pageContext.request.contextPath}/role/getuserlistbyroleid?roleId=${role.id}&systemId=${role.systemId}'" />
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td colspan="7">
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
</body>
<script type="text/javascript">
    // 查询
    function search() {
        var userName = $("#name").val();
        $("input[name='name']").val(userName);
        var realName = $("#code").val();
        $("input[name='code']").val(realName);
        var systemId = $("#system").val();
        $("input[name='systemId']").val(systemId);
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
                    url : "${pageContext.request.contextPath}/role/delrolebyid",
                    type : "POST",
                    dataType : "json",
                    data : {"roleId":id},
                    success : function(result) {
                        if (result.success) {
                            swal({title : result.msg,
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/role/getrolelist?systemId=${systemId}";
                            });
                        }else {
                            swal({title : result.msg,
                                confirmButtonText: "确定"});
                        }
                    },
                    error : function() {
                        swal({title : "删除失败",
                            confirmButtonText: "确定"});
                    }
                });
            }
        });
    }
</script>
</html>