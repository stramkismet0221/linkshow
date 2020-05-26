<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="icon" type="image/png" sizes="16x16" href="../../../common/adm/plugins/images/favicon.png">
    <title>门店列表</title>
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
            <jsp:param value="/jumi/drp/warehouse/list" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">门店列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">门店管理</a></li>
                        <li class="active">门店列表</li>
                    </ol>
                </div>
            </div>
            <!-- /row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="row">
                            <div class="col-md-3" >
                                <h5 class="m-t-30 m-b-10">店铺名称</h5>
                                <input type="text" class="form-control" id="warename" maxlength="50" placeholder="请输入店铺名称" value="${names}"/>
                            </div>

                            <div class="col-md-1" >
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" maxlength="50" value="查询" onclick="search();"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/jumi/drp/warehouse/list" id="form">
                <input type="hidden" name="name" id="warehousename" value="${names}" />
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4" >
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toadd'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>仓库编号</th>
                                            <th>所属商户</th>
                                            <th>店铺名称</th>
                                            <th>状态</th>
                                            <th>设备数</th>
                                            <th>店铺类型</th>
                                            <th>地址</th>
                                            <th>联系电话</th>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${shops}" var="shop">
                                            <tr>
                                                <td>${shop.shopno}</td>
                                                <td>${shop.tenantid}</td>
                                                <td title="${shop.shopName}">
                                                    <c:set var="str" value="${shop.shopName}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>

                                                <td>
                                                    <c:if test="${shop.state==1}">正常营业</c:if>
                                                    <c:if test="${shop.state==0}">暂停营业</c:if>
                                                </td>

                                                <td>${shop.facilityNumber} </td>
                                                <td>${shop.shopType} </td>

                                                <td title="${shop.address}">
                                                    <c:set var="str" value="${shop.address}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${shop.phone} </td>
                                                <%--<td title="${warehouse.status}">--%>
                                                    <%--<c:set var="str" value="${warehouse.status}"></c:set>--%>
                                                    <%--<c:choose>--%>
                                                        <%--<c:when test="${str==1}">--%>
                                                            <%--<c:out value="开启" />--%>
                                                        <%--</c:when>--%>
                                                        <%--<c:otherwise>--%>
                                                            <%--<c:out value="关闭" />--%>
                                                        <%--</c:otherwise>--%>
                                                    <%--</c:choose>--%>
                                                <%--</td>--%>
                                                <td>
                                                    <div>
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toedit?id=${warehouse.id}&type=1'" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toedit?id=${warehouse.id}&type=0'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="重置密码" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toedit?id=${warehouse.id}&type=0'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="正常营业" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toedit?id=${warehouse.id}&type=0'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="暂停营业" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toedit?id=${warehouse.id}&type=0'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="云端程序" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toedit?id=${warehouse.id}&type=0'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="小程序端权限" onclick="location.href='${pageContext.request.contextPath}/jumi/drp/warehouse/toedit?id=${warehouse.id}&type=0'"/>
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
        var warename = $("#warename").val();
        $("#warehousename").val(warename);
        $("#form").submit();
    }
    // 删除
    function remove(id) {
        swal({
            title: "是否删除仓库?",
            showCancelButton: true,
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/jumi/drp/warehouse/removewarehouse",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id},
                    success : function(result) {
                        if (result.success) {
                            swal({title : "删除成功",
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/jumi/drp/warehouse/list";
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
</script>
</body>
</html>
