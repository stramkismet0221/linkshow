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
    <title>新增购货订单</title>
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
            <jsp:param value="/jumi/order/purchaseorder/getlist?type=${purchaseOrder.type}" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 新增购货订单</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 购货</a></li>
                        <li class="active"> 新增购货订单</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator" novalidate="true">
                                    <input type="hidden" name="type"  value="${purchaseOrder.type}">
                                    <div class="form-group">
                                        <label class="col-md-12">ID</label>
                                        <div class="col-md-12">
                                            <input name="poDate" class="form-control"  value="${purchaseOrder.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12" id="name"><label style="color: #f05b4f">*</label>&nbsp;类型</label>
                                        <div class="col-md-12">
                                            <div class="radio-list">
                                                <label class="radio-inline">
                                                    <div class="radio radio-info">
                                                        <input type="radio" name="businessType" id="r1" value="1" <c:if test="${purchaseOrder.businessType == 1}"> checked </c:if> disabled>
                                                        <label for="r1">购货</label>
                                                    </div>
                                                </label>
                                                <label class="radio-inline">
                                                    <div class="radio radio-info">
                                                        <input type="radio" name="businessType" id="r2" value="2" <c:if test="${purchaseOrder.businessType == 2}"> checked </c:if> disabled>
                                                        <label for="r2">退货</label>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;供应商</label>
                                        <div class="col-md-12">
                                            <select class="form-control" name="supplierId" disabled>
                                                <c:forEach var="su" items="${goodsSuppliers}" >
                                                    <option value="${su.id}" <c:if test="${purchaseOrder.supplierId == su.id}"> selected </c:if>>${su.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;单据日期</label>
                                        <div class="col-md-12">
                                            <input name="poDate" class="form-control"  value="<fmt:formatDate value="${purchaseOrder.poDate}" pattern="YYYY-MM-dd"/>" disabled/>
                                        </div>
                                    </div>
                                    <c:if test="${purchaseOrder.type != 3}">
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;交货日期日期</label>
                                            <div class="col-md-12">
                                                <input name="giveTime"  class="form-control"  value=" <fmt:formatDate value="${purchaseOrder.giveTime}" pattern="YYYY-MM-dd"/>" disabled/>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;优惠率</label>
                                        <div class="col-md-12">
                                            <input name="discountRate" maxlength="10" placeholder="请输入优惠率" class="form-control"  value="${purchaseOrder.discountRate}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;优惠金额</label>
                                        <div class="col-md-12">
                                            <input name="discountPrice" class="form-control" value="${purchaseOrder.discountPrice}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;优惠后金额</label>
                                        <div class="col-md-12">
                                            <input name="discountAfterPrice" class="form-control" value="${purchaseOrder.discountAfterPrice}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;备注</label>
                                        <div class="col-md-12">
                                            <textarea rows="5" name="remark" class="form-control" disabled>${purchaseOrder.remark}</textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()" type="button">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
                <%--<div class="col-md-1"></div>--%>
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