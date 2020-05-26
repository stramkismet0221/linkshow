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
    <title>兑换码详情</title>
    <base target="_self"/>
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
            <jsp:param value="/excode/toexcodelist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 兑换码详情</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 营销活动</a></li>
                        <li class="active"> 兑换码详情</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <input name="exCodeId" value="${exCodeId}" type="hidden">
                <div class="col-md-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator" novalidate="true">
                                    <input type="hidden" name="excodeId" value="${exCode.id}"/>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;活动名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="actName" value="${exCode.actName}" disabled required="required" maxlength="50" placeholder="请输入活动名称" data-error="请填写活动名称" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-right: 0;margin-left: 0;">
                                        <label style="right: 15px;" class="col-md-12" ><label style="color: #f05b4f">*</label>&nbsp;可兑换时间</label>
                                        <div style="right: 15px;margin-right: -70px;" class="col-sm-4">
                                            <select class="selectpicker m-b-20 m-r-10" disabled onchange="changeExchangeType(this.options[this.options.selectedIndex].value)" id="expireType" data-style="btn-info btn-outline">
                                                <option <c:if test="${exCode.expireType == 1}"> selected </c:if> value="1" >领取后</option>
                                                <option <c:if test="${exCode.expireType == 2}"> selected </c:if> value="2" >到期时间</option>
                                            </select>
                                        </div>
                                        <c:if test="${exCode.expireType == 1}">
                                            <div class="col-md-3"  id="afterReceive" style="display: block;">
                                                <div class="input-group m-b-30" style="width: 310px;">
                                                    <input type="number" class="form-control" disabled placeholder="请输入天数" style="width: 200px;"
                                                           name="expireDays" aria-describedby="basic-addon2" value="${exCode.expireDays}"><span style="padding-left: 5px">几天后过期</span>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${exCode.expireType == 2}"> <div class="col-md-3" id="cutoffTime" style="display: block;">
                                            <div class="input-group">

                                                <input type="text" disabled name="expireTime" class="form-control" id="datepicker-autoclose" value="<fmt:formatDate value="${exCode.createTime}" pattern="yyyy-MM-dd"/>"  placeholder="选择日期">
                                                <span class="input-group-addon">
                                                <i class="icon-calender"></i></span>
                                            </div>
                                        </div>
                                        </c:if>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12" id="machine"><label style="color: #f05b4f">*</label>&nbsp;设备列表</label>
                                        <input name="machineIds" type="hidden" value="">
                                        <div class="col-md-12">
                                            <table class="table table-hover manage-u-table" style="white-space: nowrap" id="machineTable">
                                                <thead>
                                                <tr>
                                                    <th>设备名称</th>
                                                    <th>设备编号</th>
                                                </tr>
                                                </thead>
                                                <tbody id="machine_tbody">
                                                <c:forEach items="${exCode.terminals}" var="data">
                                                    <tr>
                                                        <td hidden>${data.id}</td>
                                                        <td>${data.name}</td>
                                                        <td>${data.deviceCode}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12" ><label style="color: #f05b4f">*</label>&nbsp;商品列表</label>
                                        <input name="barCodes" type="hidden" value="">
                                        <div class="col-md-12">
                                            <table id="mainTable" class="table table-hover manage-u-table" style="white-space: nowrap">
                                                <thead>
                                                <tr>
                                                    <th>商品名称</th>
                                                    <th>条形码</th>
                                                    <th>数量</th>
                                                </tr>
                                                </thead>
                                                <tbody id="product_tbody">
                                                <c:forEach items="${exCode.products}" var="data">
                                                    <tr>
                                                        <td>${data.productName}</td>
                                                        <td>${data.barcode}</td>
                                                        <td>${data.amount}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button type="button" class="btn btn-block btn-primary text-uppercase" onclick="location.href='${pageContext.request.contextPath}/excode/toexcodelist?type=4'">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
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
</html>