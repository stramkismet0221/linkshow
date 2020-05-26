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
    <c:if test="${purchaseOrder.type == 1}">
        <c:set var="title" value="购货单"></c:set>
    </c:if>
    <c:if test="${purchaseOrder.type == 2}">
        <c:set var="title" value="购货入库单"></c:set>
    </c:if>
    <c:if test="${purchaseOrder.type == 3 || purchaseOrder.businessType == 2}">
        <c:set var="title" value="购货退单"></c:set>
    </c:if>
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
                    <h4 class="page-title">${title}</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 购货</a></li>
                        <li class="active"> ${title}</li>
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
                                <form id="form" onsubmit="update();return false;" class="form-horizontal form-material" data-toggle="validator" novalidate="true">
                                    <input type="hidden" name="ptype" value="${ptype}">
                                    <input type="hidden" name="type" value="${purchaseOrder.type}">
                                    <input type="hidden" name="status" value="${purchaseOrder.status}">
                                    <input type="hidden" name="poDate" value="${purchaseOrder.poDate}">
                                    <input type="hidden" name="businessType" value="${purchaseOrder.businessType}">
                                    <div class="form-group">
                                        <label class="col-md-12">订单ID</label>
                                        <div class="col-md-12">
                                            <input type="text" name="id"  class="form-control"  value="${purchaseOrder.id}" readonly/>
                                        </div>
                                    </div>
                                    <c:if test="${purchaseOrder.type == 1}">
                                        <div class="form-group">
                                            <label class="col-md-12" id="name"><label style="color: #f05b4f">*</label>&nbsp;类型</label>
                                            <div class="col-md-12">
                                                <div class="radio-list">
                                                    <label class="radio-inline">
                                                        <div class="radio radio-info">
                                                            <input type="radio" name="businessType" id="r1" value="1" <c:if test="${purchaseOrder.businessType == 1}"> checked </c:if>>
                                                            <label for="r1">购货</label>
                                                        </div>
                                                    </label>
                                                    <label class="radio-inline">
                                                        <div class="radio radio-info">
                                                            <input type="radio" name="businessType" id="r2" value="2" <c:if test="${purchaseOrder.businessType == 2}"> checked </c:if>>
                                                            <label for="r2">退货</label>
                                                        </div>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;供应商</label>
                                        <div class="col-md-12">
                                            <select class="form-control" name="supplierId">
                                                <option value="">请选择</option>
                                                <c:forEach var="su" items="${goodsSuppliers}" >
                                                    <option value="${su.id}" <c:if test="${purchaseOrder.supplierId == su.id}"> selected </c:if>>${su.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;单据日期</label>
                                        <div class="col-md-12">
                                            <input name="poDateStr" maxlength="50" placeholder="请输选择单据日期" class="form-control datepicker-autoclose" value="<fmt:formatDate value="${purchaseOrder.poDate}" pattern="YYYY-MM-dd"/>" />
                                        </div>
                                    </div>
                                    <c:if test="${purchaseOrder.type != 3}">
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;交货日期日期</label>
                                            <div class="col-md-12">
                                                <input name="giveTimeStr" maxlength="50" placeholder="请输选择交货日期日期" class="form-control datepicker-autoclose"  value="<fmt:formatDate value="${purchaseOrder.giveTime}" pattern="YYYY-MM-dd"/>"/>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;优惠率</label>
                                        <div class="col-md-12">
                                            <input name="discountRate" maxlength="10" placeholder="请输入优惠率" class="form-control"  value="${purchaseOrder.discountRate}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;优惠金额</label>
                                        <div class="col-md-12">
                                            <input name="discountPrice" maxlength="10" placeholder="请输入优惠金额" class="form-control"  value="${purchaseOrder.discountPrice}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;优惠后金额</label>
                                        <div class="col-md-12">
                                            <input name="discountAfterPrice" maxlength="10" placeholder="请输入优惠后金额" class="form-control"  value="${purchaseOrder.discountAfterPrice}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;备注</label>
                                        <div class="col-md-12">
                                            <textarea rows="5" name="remark" maxlength="300" placeholder="请输入备注" class="form-control">${purchaseOrder.remark}</textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit" value="保存" >
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
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

<script type="text/javascript">
    jQuery(document).ready(function() {
        $('.datepicker-autoclose').datepicker({
            format:'yyyy-mm-dd',
        });
    });
    function update() {

        var supplierId = $("select[name='supplierId']").val();
        // 校验
        if (checkStr(supplierId)) {
            swal({
                title: "请选择供应商",
                cancelButtonText: "确定"
            });
            return false;
        }

        var poDate = $("input[name='poDateStr']").val();
        if (checkStr(poDate)) {
            $("input[name='poDateStr']").focus();
            return false;
        }

        var giveTime = $("input[name='giveTimeStr']").val();
        if (checkStr(giveTime)) {
            $("input[name='giveTimeStr']").focus();
            return false;
        }

        swal({
            title: "是否保存",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/jumi/order/purchaseorder/modifypurchaseorder",
                    type: "POST",
                    dataType: "json",
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/order/purchaseorder/getlist?type=${purchaseOrder.type}";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                cancelButtonText: "确定"
                            });
                        }
                    },
                    error: function () {
                        swal({
                            title: "保存失败",
                            cancelButtonText: "确定"
                        });
                    }
                });
            }
        });
    }
</script>

</body>
</html>