<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>菜单管理</title>
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
            <jsp:param value="/menu/getmenulist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">菜单管理</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">系统管理</a></li>
                        <li class="active">菜单管理</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="row">
                            <div class="col-md-4 col-sm-6">
                                <h5 class="m-t-30 mb-10">所属系统<label  for="system"></label></h5>
                                <select class="form-control " id="system">
                                    <c:forEach items="${systems}" var="data">
                                        <option value="${data.id}" title="${data.name}" <c:if test="${data.id == systemId}">selected</c:if>>
                                            <c:set var="str" value="${data.name}"></c:set>
                                            <c:choose>
                                                <c:when test="${fn:length(str) > 20}">
                                                    <c:out value="${fn:substring(str, 0, 20)}......" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${str}" />
                                                </c:otherwise>
                                            </c:choose>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-1 ">
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/menu/getmenulist" id="form">
                <input name="systemId" value="" type="hidden"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-md-2" >
                                    <input type="button" value="新建一级菜单" class="btn btn-block btn-info"
                                           onclick="location.href='${pageContext.request.contextPath}/menu/addmenu?pid=0&systemId=${systemId}'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>菜单ID</th>
                                            <th>菜单名称</th>
                                            <th>所属系统</th>
                                            <th>菜单序号</th>
                                            <th>菜单唯一编码</th>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${menus}" var="data">
                                            <tr>
                                                <td>${data.id}</td>
                                                <td title="${data.name}">
                                                    <c:set var="str" value="${data.name}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td title="${data.systemName}">
                                                    <c:set var="str" value="${data.systemName}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 20}">
                                                            <c:out value="${fn:substring(str, 0, 20)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${data.sno}</td>
                                                <td title="${data.code}">
                                                    <c:set var="str" value="${data.code}"></c:set>
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
                                                    <div>
                                                        <div style="display: inline-block; width:45%">
                                                            <input type="button" class="btn btn-block btn-primary btn-xs"
                                                                   value="添加子菜单"
                                                                   onclick="location.href='${pageContext.request.contextPath}/menu/addmenu?systemId=${data.systemId}&pid=${data.id}'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:23%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs"
                                                                   value="修改"
                                                                   onclick="location.href='${pageContext.request.contextPath}/menu/modifymenu?id=${data.id}&systemId=${data.systemId}'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:23%">
                                                            <input type="button" class="btn btn-block btn-danger btn-xs"
                                                                   value="删除" onclick="remove(${data.id}, ${data.systemId});"/>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <c:forEach items="${data.childMenus}" var="cdata">
                                                <tr>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cdata.id}</td>
                                                    <td title="${cdata.name}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <c:set var="str" value="${cdata.name}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${fn:length(str) > 10}">
                                                                <c:out value="${fn:substring(str, 0, 10)}......" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${str}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td title="${data.systemName}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <c:set var="str" value="${data.systemName}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${fn:length(str) > 20}">
                                                                <c:out value="${fn:substring(str, 0, 20)}......" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${str}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cdata.sno}</td>
                                                    <td title="${cdata.code}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <c:set var="str" value="${cdata.code}"></c:set>
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
                                                        <div>
                                                            <div style="display: inline-block; width:45%">
                                                                <%--<input type="button"--%>
                                                                       <%--class="btn btn-block btn-info btn-xs" value="添加"--%>
                                                                       <%--onclick="location.href='${pageContext.request.contextPath}/menu/addmenu?systemId=${cdata.systemId}&pid=${cdata.id}'"/>--%>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:23%">
                                                                <input type="button"
                                                                       class="btn btn-block btn-info btn-xs"
                                                                       value="修改"
                                                                       onclick="location.href='${pageContext.request.contextPath}/menu/modifymenu?id=${cdata.id}&systemId=${cdata.systemId}'"/>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:23%">
                                                                <input type="button"
                                                                       class="btn btn-block btn-danger btn-xs"
                                                                       value="删除" onclick="remove(${cdata.id},${cdata.systemId});"/>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:forEach>
                                        </tbody>
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
    // 删除
    function remove(id, systemId) {
        swal({
            title: "确定删除吗?",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/menu/removemenu",
                    type: "POST",
                    dataType: "json",
                    data: {"id": id, "systemId":systemId},
                    success: function (result) {
                        console.log(result);
                        if (result.success) {
                            swal({
                                title: result.msg,
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/menu/getmenulist?systemId="+systemId;
                            });
                        }
                    },
                    error: function () {
                        alert("删除失败");
                    }
                });
            }
        });
    }
    // 查询
    function search() {
        var systemId = $("#system").val();
        $("input[name='systemId']").val(systemId);
        $("#form").submit();
    }
</script>
</body>
</html>