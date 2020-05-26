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
    <title>电子烟设备安装列表</title>
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
            <jsp:param value="/smoke/getconmnginslist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">安装列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">售烟机管理</a></li>
                        <li class="active">安装列表</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="row">
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">ID</h5>
                                <input type="number" id="id" class="form-control"
                                       oninput="if(value.length>=10)value=value.slice(0,10)" placeholder="请输入ID"
                                       value="${id}"/>
                            </div>
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">渠道商</h5>
                                <input type="text" id="agentName" class="form-control" maxlength="50"
                                       placeholder="请输入渠道商" value="${agentName}"/>
                            </div>
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">申请人姓名</h5>
                                <input type="text" id="applyUserName" class="form-control" maxlength="50"
                                       placeholder="请输入申请人姓名" value="${applyUserName}"/>
                            </div>
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">处理人姓名</h5>
                                <input type="text" id="auditUserName" class="form-control" maxlength="50"
                                       placeholder="请输入处理人姓名" value="${auditUserName}"/>
                            </div>
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">安装人姓名</h5>
                                <input type="text" id="installUserName" class="form-control" maxlength="50"
                                       placeholder="请输入安装人姓名" value="${installUserName}"/>
                            </div>
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">安装状态</h5>
                                <select class="form-control" id="status">
                                    <option value="" title="全部">全部</option>
                                    <option value="0" title="待处理" <c:if test="${status == 0}">selected</c:if>>待处理
                                    </option>
                                    <option value="1" title="处理中" <c:if test="${status == 1}">selected</c:if>>处理中
                                    </option>
                                    <option value="2" title="安装完成" <c:if test="${status == 2}">selected</c:if>>安装完成
                                    </option>
                                    <option value="3" title="取消安装" <c:if test="${status == 3}">selected</c:if>>取消安装
                                    </option>
                                </select>
                            </div>
                            <div class="col-md-2 col-lg-1">
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/smoke/getconmnginslist" id="form">
                <input type="hidden" name="id" value=""/>
                <input type="hidden" name="agentName" value=""/>
                <input type="hidden" name="applyUserName" value=""/>
                <input type="hidden" name="auditUserName" value=""/>
                <input type="hidden" name="installUserName" value=""/>
                <input type="hidden" name="status" value=""/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>渠道商名称</th>
                                            <th>安装商户</th>
                                            <th>商户联系人</th>
                                            <th>商户地址</th>
                                            <th>商户联系方式</th>
                                            <th>预约时间</th>
                                            <th>申请人</th>
                                            <th>处理人</th>
                                            <th>安装人</th>
                                            <th>安装状态</th>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${sellSmokeInstalls}" var="data">
                                            <tr>
                                                <td>${data.id}</td>
                                                <td>${data.agentName}</td>
                                                <td title="${data.store}">
                                                    <c:set var="str" value="${data.store}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 15}">
                                                            <c:out value="${fn:substring(str, 0, 15)}..."/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${data.storeUser}</td>
                                                <td title="${data.address}">
                                                    <c:set var="str" value="${data.address}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 15}">
                                                            <c:out value="${fn:substring(str, 0, 15)}..."/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${data.mobile}</td>
                                                <td>
                                                    <fmt:formatDate value="${data.appointTime}" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                        ${data.applyUserName}
                                                </td>
                                                <td>
                                                        ${data.auditUserName}
                                                </td>

                                                <td>
                                                    <c:if test="${data.status >= 2}">
                                                        ${data.installUserName}
                                                    </c:if>
                                                </td>
                                                <c:if test="${data.status == 0}">
                                                    <td>待处理</td>
                                                </c:if>
                                                <c:if test="${data.status == 1}">
                                                    <td>处理中</td>
                                                </c:if>
                                                <c:if test="${data.status == 2}">
                                                    <td>已安装</td>
                                                </c:if>
                                                <c:if test="${data.status == 3}">
                                                    <td>取消安装</td>
                                                </c:if>
                                                <td>
                                                    <div>
                                                        <div style="display: inline-block; width:20%">
                                                            <c:if test="${data.status == 0 and !isInstallUser}">
                                                                <input type="button" style="background-color: green"
                                                                       data-toggle="modal"
                                                                       data-target=".bs-example-modal-lg"
                                                                       class="btn btn-block btn-primary btn-xs"
                                                                       value="处理"
                                                                       onclick="location.href='${pageContext.request.contextPath}/smoke/dealinstall?id=${data.id}'"/>
                                                            </c:if>
                                                            <c:if test="${data.status == 0 and isInstallUser}">
                                                                <input type="button" style="pointer-events: none;"
                                                                       class="btn btn-block btn-default btn-xs"
                                                                       value="处理"/>
                                                            </c:if>
                                                            <c:if test="${data.status == 1 && !isECMng}">
                                                                <input type="button" style="background-color: green"
                                                                       data-toggle="modal"
                                                                       data-target=".bs-example-modal-lg"
                                                                       class="btn btn-block btn-primary btn-xs"
                                                                       value="安装"
                                                                       onclick="location.href='${pageContext.request.contextPath}/smoke/finishinstall?id=${data.id}'"/>
                                                            </c:if>
                                                            <c:if test="${data.status == 2}">
                                                                <input type="button" data-toggle="modal"
                                                                       data-target=".bs-example-modal-lg"
                                                                       class="btn btn-block btn-primary btn-xs"
                                                                       value="撤回"
                                                                       onclick="location.href='${pageContext.request.contextPath}/smoke/revertinstall?id=${data.id}'"/>
                                                            </c:if>
                                                            <c:if test="${data.status == 3}">
                                                                <input type="button" style="pointer-events: none;"
                                                                       class="btn btn-block btn-default btn-xs"
                                                                       value="取消"/>
                                                            </c:if>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" style="background-color: #1ece77"
                                                                   class="btn btn-block btn-info btn-xs" value="详情"
                                                                   onclick="location.href='${pageContext.request.contextPath}/smoke/installdetail?id=${data.id}&type=1'"/>
                                                        </div>
                                                        &nbsp;
                                                            <%--只有待审核状态才可修改--%>
                                                        <c:if test="${data.status == 0 and !isInstallUser}">
                                                            <div style="display: inline-block; width:20%">
                                                                <input type="button"
                                                                       class="btn btn-block btn-info btn-xs" value="修改"
                                                                       onclick="location.href='${pageContext.request.contextPath}/smoke/editinstall?id=${data.id}'"/>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${data.status > 0 or isInstallUser}">
                                                            <div style="display: inline-block; width:20%">
                                                                <input type="button" style="pointer-events: none;"
                                                                       class="btn btn-block btn-default btn-xs"
                                                                       value="修改"/>
                                                            </div>
                                                        </c:if>
                                                        &nbsp;
                                                            <%--只有待审核状态才可以删除--%>
                                                        <c:if test="${data.status == 0 and !isInstallUser}">
                                                            <div style="display: inline-block; width:20%">
                                                                <input type="button"
                                                                       class="btn btn-block btn-danger btn-xs"
                                                                       value="删除" onclick="remove(${data.id});"/>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${data.status > 0 or isInstallUser}">
                                                            <div style="display: inline-block; width:20%">
                                                                <input type="button" style="pointer-events: none;"
                                                                       class="btn btn-block btn-default btn-xs"
                                                                       value="删除"/>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="12">
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
    // 查询
    function search() {
        var id = $("#id").val();
        var agentName = $("#agentName").val();
        var applyUserName = $("#applyUserName").val();
        var auditUserName = $("#auditUserName").val();
        var installUserName = $("#installUserName").val();
        var status = $("#status").val();
        $("input[name='id']").val(id);
        $("input[name='agentName']").val(agentName);
        $("input[name='applyUserName']").val(applyUserName);
        $("input[name='auditUserName']").val(auditUserName);
        $("input[name='installUserName']").val(installUserName);
        $("input[name='status']").val(status);
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
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/smoke/removeinstall",
                    type: "POST",
                    dataType: "json",
                    data: {"id": id},
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "删除成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/smoke/getconmnginslist";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                cancelButtonText: "确定"
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
</script>
</body>
</html>