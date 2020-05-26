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
    <link rel="icon" type="image/png" sizes="16x16" href="../../../common/adm/plugins/images/favicon.png">
    <title>生产厂家列表</title>
</head>

<body class="fix-sidebar">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
        </svg>
    </div>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <%@include file="/WEB-INF/views/include/header.jsp"%>
        </nav>
        <div class="navbar-default sidebar" role="navigation">
            <jsp:include page="/WEB-INF/views/include/left.jsp">
                <jsp:param value="/jumi/product/goodsmanufatory/getlist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">生产厂家列表</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">生产厂家中心</a></li>
                            <li class="active">生产厂家列表</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-md-3" >
                                    <h5 class="m-t-30 m-b-10">厂家名称</h5>
                                    <input type="text" class="form-control" id="fname" maxlength="100" placeholder="请输入厂家名称" value="${jmGoodsManuFatory.name}"/>
                                </div>
                                <div class="col-md-1">
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/jumi/product/goodsmanufatory/getlist" id="form">
                    <input type="hidden" name="name">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="white-box">
                                <div class="row">
                                    <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4">
                                        <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodsmanufatory/toaddjmgoodsmanufactory'"/>
                                    </div>
                                </div>
                                <div class="panel">
                                    <div class="table-responsive">
                                        <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                            <thead>
                                                <tr>
                                                    <th>厂家ID</th>
                                                    <th>厂家名称</th>
                                                    <th>厂家地址</th>
                                                    <th>厂家介绍</th>
                                                    <th width="100">操作</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${jmGoodsManuFatorys}" var="data">
                                                <tr>
                                                    <td>${data.id}</td>
                                                    <td title="${data.name}">
                                                        <c:set var="str" value="${data.name}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${fn:length(str) > 15}">
                                                                <c:out value="${fn:substring(str, 0, 15)}......" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${str}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td title="${data.address}">
                                                        <c:set var="str" value="${data.address}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${fn:length(str) > 15}">
                                                                <c:out value="${fn:substring(str, 0, 15)}......" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${str}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td title="${data.description}">
                                                        <c:set var="str" value="${data.description}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${fn:length(str) > 15}">
                                                                <c:out value="${fn:substring(str, 0, 15)}......" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:out value="${str}" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <div>
                                                            <div style="display: inline-block; width:28%">
                                                                <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情"
                                                                       onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodsmanufatory/jmgoodsmanufatoryinfo?id=${data.id}&type=0'" />
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:28%">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="修改"
                                                                       onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodsmanufatory/jmgoodsmanufatoryinfo?id=${data.id}&type=1'"/>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:28%">
                                                                <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${data.id});" />
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td colspan="8">
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
                    <%@include file="/WEB-INF/views/include/right.jsp"%>
                </div>
            </div>
            <footer class="footer text-center">
                <%@include file="/WEB-INF/views/include/footer.jsp"%>
            </footer>
        </div>
    </div>
</body>
<script type="text/javascript">

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
                    url: "${pageContext.request.contextPath}/jumi/product/goodsmanufatory/deljmgoodsmanufatory",
                    type: "POST",
                    dataType: "json",
                    data: {"jmGoodsManuFatoryId": id},
                    success: function (result) {
                        console.log(result);
                        if (result.success) {
                            swal({
                                title: result.msg,
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/product/goodsmanufatory/getlist";
                            });
                        }else {
                            swal({
                                title: result.msg,
                                confirmButtonText: "确定"
                            });
                        }
                    },
                    error: function () {
                        swal({
                            title: result.msg,
                            confirmButtonText: "删除失败"
                        });
                    }
                });
            }
        });
    }

    // 查询
    function search() {
        var fname = $("#fname").val();
        $("input[name='name']").val(fname);
        $("#form").submit();
    }
</script>
</html>