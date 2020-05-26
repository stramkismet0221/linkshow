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
    <title>权限管理</title>
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
                    <h4 class="page-title">权限列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">权限管理</a></li>
                        <li class="active">权限列表</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <form id="search">
                            <div class="row">
                                <div class="col-md-3" >
                                    <h5 class="m-t-30 m-b-10">权限名称</h5>
                                    <input type="text" class="form-control" id="name" maxlength="50" placeholder="请输入权限名称" value="${power.name}"/>
                                </div>
                                <div class="col-md-3" >
                                    <h5 class="m-t-30 mb-10">权限类型<label  for="type"></label></h5>
                                    <select class="form-control" id="type">
                                        <option value="" >全部</option>
                                        <option value="1" <c:if test="${1 == power.type}">selected</c:if>>系统登录权限</option>
                                        <option value="2" <c:if test="${2 == power.type}">selected</c:if>>菜单权限</option>
                                        <option value="3" <c:if test="${3 == power.type}">selected</c:if>>操作权限</option>
                                        <option value="4" <c:if test="${4 == power.type}">selected</c:if>>系统管理员权限</option>
                                    </select>
                                </div>
                                <div class="col-md-3" >
                                    <h5 class="m-t-30 mb-10">所属系统<label  for="system"></label></h5>
                                    <select class="form-control" id="system">
                                        <option value="" >全部</option>
                                        <c:forEach items="${systems}" var="data">
                                            <option value="${data.id}"
                                                    <c:if test="${data.id == power.systemId}">selected</c:if>>
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
                                <div class="col-md-1">
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                                </div>
                                <%--<div class="col-md-4" style=" width:10%">
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="button" class="btn btn-block btn-info" value="清空" onclick="clean();"/>
                                </div>--%>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="row">
                <form action="${pageContext.request.contextPath}/power/getpowerlist" id="form">
                    <input name="name" value="" type="hidden" />
                    <input name="type" value="" type="hidden" />
                    <input name="systemId" value="" type="hidden"/>
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4" >
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/power/toaddpower?systemId=${power.systemId}'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th width="8%">权限ID</th>
                                            <th width="15%">系统名称</th>
                                            <th width="17%">权限名称</th>
                                            <th width="17%">权限编码</th>
                                            <%--<th>权限描述</th>--%>
                                            <th width="15%">权限类型</th>
                                            <%--<th>创建时间</th>--%>
                                            <th width="28%">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${allPowers}" var="power">
                                            <tr>
                                                <td>${power.id}</td>
                                                <td >
                                                    <c:forEach items="${systems}" var="data">
                                                        <c:if test="${data.id == power.systemId}">
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
                                                <td title="${power.name}">
                                                    <c:set var="str" value="${power.name}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td title="${power.code}">
                                                    <c:set var="str" value="${power.code}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 20}">
                                                            <c:out value="${fn:substring(str, 0, 20)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <%--<td title="${power.description}">--%>
                                                    <%--<c:set var="str" value="${power.description}"></c:set>--%>
                                                    <%--<c:choose>--%>
                                                        <%--<c:when test="${fn:length(str) > 15}">--%>
                                                            <%--<c:out value="${fn:substring(str, 0, 15)}......" />--%>
                                                        <%--</c:when>--%>
                                                        <%--<c:otherwise>--%>
                                                            <%--<c:out value="${str}" />--%>
                                                        <%--</c:otherwise>--%>
                                                    <%--</c:choose>--%>
                                                <%--</td>--%>
                                                <c:if test="${power.type == 1}">
                                                <td>系统登录权限</td>
                                                </c:if>
                                                <c:if test="${power.type == 2}">
                                                <td>菜单权限</td>
                                                </c:if>
                                                <c:if test="${power.type == 3}">
                                                <td>操作权限</td>
                                                </c:if>
                                                <c:if test="${power.type == 4}">
                                                <td>系统管理员权限</td>
                                                </c:if>
                                                <%--<td><fmt:formatDate value="${power.createTime}" pattern="yyyy-MM-dd"/></td>--%>
                                                <td>
                                                    <div style="width: 100%">
                                                        <div style="display: inline-block; width:14%">
                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/power/getpowerbyid?id=${power.id}&type=0'" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:14%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/power/getpowerbyid?id=${power.id}&type=1'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:14%">
                                                            <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${power.id}, ${power.systemId});" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:14%">
                                                            <c:if test="${power.type == 2}">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="赋权" onclick="location.href='${pageContext.request.contextPath}/power/getmenutreeforpower?powerId=${power.id}&systemId=${power.systemId}'"/>
                                                            </c:if>
                                                            <c:if test="${power.type != 2}">
                                                            <input type="button" style="pointer-events: none;" class="btn btn-block btn-default btn-xs" value="赋权" />
                                                            </c:if>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:14%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="用户"
                                                                   onclick="location.href='${pageContext.request.contextPath}/power/getuserlistbysystemid?systemId=${power.systemId}&powerId=${power.id}'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:14%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="角色" onclick="location.href='${pageContext.request.contextPath}/power/getrolelistbysystemid?systemId=${power.systemId}&powerId=${power.id}'"/>
                                                        </div>
                                                        &nbsp;
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
                </form>
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
        var name = $("#name").val();
        $("input[name='name']").val(name);
        var type = $("#type").val();
        $("input[name='type']").val(type);
        var systemId = $("#system").val();
        $("input[name='systemId']").val(systemId);
        $("#form").submit();
    }

    //删除权限
    function remove(id, systemId) {
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
                    url : "${pageContext.request.contextPath}/power/delpowerbyid",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id, "systemId":systemId},
                    success : function(result) {
                        if (result.success) {
                            swal({title : result.msg,
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/power/getpowerlist?systemId=${power.systemId}";
                            });
                        }else {
                            swal({title : result.msg,
                                confirmButtonText: "确定"});
                        }
                    },
                    error : function() {
                        swal({title : result.msg,
                            confirmButtonText: "删除失败"});
                    }
                });
            }
        });
    }
    function clean() {
        document.getElementById("search").reset();
    }
</script>
</body>
</html>