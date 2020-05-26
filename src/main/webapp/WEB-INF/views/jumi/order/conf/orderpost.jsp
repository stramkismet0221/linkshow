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
    <title>新增</title>
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
            <jsp:param value="/jumi/product/goods/getlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">配送设置</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 订单管理</a></li>
                        <li class="active"> 配送设置</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <%--<form action="${pageContext.request.contextPath}/jumi/order/orderpost/updateorderpost" id="form">--%>
                            <form>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="white-box">
                                            <div class="panel">
                                                <div class="table-responsive">
                                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                                        <thead>
                                                        <tr>
                                                            <th>配送方式</th>
                                                            <th>开通状态</th>
                                                            <th>是否默认</th>
                                                            <th>配送价格</th>
                                                            <th>配送范围</th>
                                                            <th>序号</th>
                                                            <th>是否包邮</th>
                                                            <th>操作</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${orderPostList}" var="data">
                                                            <tr>
                                                                <td title="${data.postType}">
                                                                    <c:set var="str" value="${data.postType}"></c:set>
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
                                                                    <c:set var="num" value="${data.openStatus}"></c:set>
                                                                    <c:choose>
                                                                        <c:when test="${num == 1}">
                                                                            <c:out value="已开通"></c:out>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="openStatus${data.id}" id="openStatus${data.id}" value="1" checked="checked"  onclick="changeStatus(this,1,${data.id})" data-open="${num}" >--%>
                                                                                <%--<label for="openStatus${data.id}"> 已开通 </label>--%>
                                                                            <%--</div>--%>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input  type="radio" name="openStatus${data.id}" id="openStatus${data.id}" value="0"  onclick="changeStatus(this,1,${data.id})" data-open="${num}">--%>
                                                                                <%--<label for="openStatus${data.id}"> 未开通 </label>--%>
                                                                            <%--</div>--%>
                                                                        </c:when>
                                                                        <c:when test="${num == 0}">
                                                                            <c:out value="未开通"></c:out>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="openStatus${data.id}" id="openStatus${data.id}" value="1" onclick="changeStatus(this,1,${data.id})" data-open="${num}" >--%>
                                                                                <%--<label for="openStatus${data.id}"> 已开通 </label>--%>
                                                                            <%--</div>--%>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="openStatus${data.id}" id="openStatus${data.id}" value="0" onclick="changeStatus(this,1,${data.id})" data-open="${num}">--%>
                                                                                <%--<label for="openStatus${data.id}"> 未开通 </label>--%>
                                                                            <%--</div>--%>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td>
                                                                    <c:set var="deaf" value="${data.deafStatus}"></c:set>
                                                                    <c:choose>
                                                                        <c:when test="${deaf == 1}">
                                                                            <c:out value="是"></c:out>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="1" checked="checked"  onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 是 </label>--%>
                                                                            <%--</div>--%>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input  type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="0"  onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 否 </label>--%>
                                                                            <%--</div>--%>
                                                                        </c:when>
                                                                        <c:when test="${deaf == 0}">
                                                                            <c:out value="否"></c:out>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="1" onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 是2 </label>--%>
                                                                            <%--</div>--%>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="0" onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 否 </label>--%>
                                                                            <%--</div>--%>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td width="150">${data.postPrice}
                                                                    <%--<input type="text" value="${data.postPrice}" class="form-control" onkeyup="btnonkeyupDiscount(this, value);" >--%>
                                                                </td>
                                                                <td width="150">${data.postAround}
                                                                    <%--<input type="text" value="${data.postAround}" class="form-control" onkeyup="btnonkeyupDiscount(this, value);">--%>
                                                                </td>
                                                                <td width="150">${data.sno}
                                                                    <%--<input type="text" value="${data.sno}" class="form-control" onkeyup="btnonkeyupDiscount(this, value);">--%>
                                                                </td>
                                                                <td>
                                                                    <c:set var="ship" value="${data.freeShippingStatus}"></c:set>
                                                                    <c:choose>
                                                                        <c:when test="${ship == 1}">
                                                                            <c:out value="是"></c:out>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input  type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="1"  onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 否 </label>--%>
                                                                            <%--</div>--%>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input  type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="0"  onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 否 </label>--%>
                                                                            <%--</div>--%>
                                                                        </c:when>
                                                                        <c:when test="${ship == 0}">
                                                                            <c:out value="否"></c:out>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="1" onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 是 </label>--%>
                                                                            <%--</div>--%>
                                                                            <%--<div class="radio radio-info">--%>
                                                                                <%--<input type="radio" name="deafStatus${data.id}" id="deafStatus${data.id}" value="0" checked="checked" onclick="changeStatus(this,2,${data.id})" >--%>
                                                                                <%--<label for="deafStatus${data.id}"> 否 </label>--%>
                                                                            <%--</div>--%>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td>
                                                                    <div>
                                                                        <div style="display: inline-block; width:15%">
                                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/jumi/order/orderpost/orderpostinfo?id=${data.id}&type=0'" />
                                                                        </div>
                                                                        &nbsp;
                                                                        <div style="display: inline-block; width:15%">
                                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/jumi/order/orderpost/orderpostinfo?id=${data.id}&type=1'"/>
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