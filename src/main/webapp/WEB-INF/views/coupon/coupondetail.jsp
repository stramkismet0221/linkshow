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
    <title>抵扣券详情</title>
</head>
<style>
    .ue-container {
        margin: 0 auto;
        margin-top: 3%;
        padding: 20px 40px;
        border: 1px solid #ddd;
        background: #fff;
    }
</style>
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
            <jsp:param value="/coupon/getcouponlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">抵扣券详情</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">营销活动</a></li>
                        <li class="active">抵扣券详情</li>
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
                                <form  class="form-horizontal form-material">
                                    <input type="hidden" name="id" value="${coupon.id}"/>
                                    <div class="form-group">
                                        <label class="col-md-12" id="name"><label style="color: #f05b4f">*</label>&nbsp;抵扣券名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${coupon.name}" disabled/>
                                        </div>
                                    </div>

                                    <label style="right: 15px;" class="col-md-12" for="couponType"><label style="color: #f05b4f">*</label>&nbsp;类型</label>
                                    <div class="col-sm-4" style="width:40%;right:15px;margin-right:-70px;">
                                        <select class="selectpicker" id="couponType" name="couponType" style="width: 15px" disabled>
                                            <option value="1" ${coupon.couponType == 1 ? selected : ''}>抵扣券</option>
                                            <option value="2" ${coupon.couponType == 2 ? selected : ''}>折扣券</option>
                                        </select>
                                    </div>

                                    <div class="input-group m-b-30" style="width: 25%;">
                                        <input type="text" class="form-control" name="discount" aria-describedby="basic-addon2" value="${coupon.discount}" disabled>
                                        <span class="input-group-addon" id="basic-addon2">${coupon.couponType==1?'元':'折'}</span>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;数量</label>
                                        <div class="col-md-3" style="width: 20%">
                                            <input style="padding: 7px;" id="tch3_22" type="text" required="required" placeholder="请输入数量" value="${coupon.amount}"
                                                   name="tch3_22" data-bts-button-down-class="btn btn-default btn-outline"
                                                   data-bts-button-up-class="btn btn-default btn-outline" disabled>
                                            <input name="amount" type="hidden"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <label style="right: 15px;" class="col-md-12" for="couponType"><label style="color: #f05b4f">*</label>&nbsp;可兑换时间</label>
                                    <div class="col-sm-4" style="width:40%;right:15px;margin-right:-70px;">
                                        <select class="selectpicker" id="exTimType" style="width: 15px" disabled>
                                            <option value="1" ${coupon.exTimeType == 1 ? 'selected' : ''}>领取后</option>
                                            <option value="2" ${coupon.exTimeType == 2 ? 'selected' : ''}>截止日期</option>
                                        </select>
                                    </div>

                                    <c:if test="${coupon.exTimeType == 1}">
                                        <div class="input-group m-b-30" id="afterReceive" style="width: 25%;">
                                            <input type="text" class="form-control" aria-describedby="basic-addon2" value="${coupon.afterReceive}" disabled>
                                            <span class="input-group-addon">几天内有效</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${coupon.exTimeType == 2}">
                                        <div class="input-group m-b-30" id="cutoffTime" style="width: 25%;">
                                            <input type="text" class="form-control mydatepicker"
                                                    value="<fmt:formatDate value="${coupon.cutoffTime}" pattern="yyyy-MM-dd"/>" disabled> <span class="input-group-addon">
                                        <i class="icon-calender"></i></span>
                                        </div>
                                    </c:if>

                                    <div class="form-group">
                                        <label class="col-md-12" id="exchangeProduct"><label style="color: #f05b4f">*</label>&nbsp;可兑换商品</label>
                                        <div style="display: inline-flex;">
                                            <div class="radio radio-info" style="margin-left: 10px;">
                                                <input type="radio" name="barCodes" id="ALL" value="ALL" ${coupon.barCodes=='ALL'?'checked':''} disabled>
                                                <label for="ALL">全部商品</label>
                                            </div>
                                            <div class="radio radio-info" style="margin-left: 15px;">
                                                <input type="radio" name="barCodes" id="partBarCodes" value="ALL" ${coupon.barCodes=='ALL'?'':'checked'} disabled>
                                                <label for="partBarCodes">部分商品</label>
                                            </div>
                                        </div>
                                    </div>

                                    <table id="mainTable" class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>商品名称</th>
                                            <th>条形码</th>
                                        </tr>
                                        </thead>
                                        <tbody id="product_tbody">
                                        <c:forEach var="product" items="${coupon.ypProducts}">
                                            <tr>
                                                <td title="${product.name}">
                                                    <c:set var="str" value="${product.name}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 30}">
                                                            <c:out value="${fn:substring(str, 0, 30)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${product.barCode}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <div class="form-group">
                                        <label class="col-md-12" id="exchangeTerminal"><label style="color: #f05b4f">*</label>&nbsp;可兑换设备</label>
                                        <div style="display: inline-flex;">
                                            <div class="radio radio-info" style="margin-left: 10px;">
                                                <input type="radio" name="terminals" id="machineAll" value="ALL" ${coupon.terminals=='ALL'?'checked':''} disabled>
                                                <label for="machineAll">全部设备</label>
                                            </div>
                                            <div class="radio radio-info" style="margin-left: 15px;">
                                                <input type="radio" name="terminals" id="machinePART" value="${coupon.terminals}"
                                                       onclick="loadDoubleBox('${coupon.terminals}');" ${coupon.terminals=='ALL'?'':'checked'} disabled>
                                                <label for="machinePART">部分设备</label>
                                            </div>
                                        </div>
                                        <div class="ue-container" id="machine" style="width:100%;display: none;">
                                            <select multiple="multiple" size="10" name="doublebox" class="machine" style="height: 250px;" disabled>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-2 col-sm-4 col-xs-12" style="margin-left: 38%">
                                        <button type="button" class="btn btn-block btn-primary text-uppercase"
                                                onclick="location.href='${pageContext.request.contextPath}/coupon/getcouponlist?type=2'" >
                                            <span style="vertical-align: inherit;">返回</span>
                                        </button>
                                    </div>
                                </div>

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
<script>
    jQuery(document).ready(function() {
        // Switchery
        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        $('.js-switch').each(function() {
            new Switchery($(this)[0], $(this).data());
        });

        $("input[name='tch3_22']").TouchSpin({
            initval: 40,
            min: 1,
        });
        $("input[name='tch3_23']").TouchSpin({
            initval: 40,
            min: 1,
        });
    });
</script>
<script type="text/javascript">

    $(document).ready(function(){

        var barCodes = '${coupon.barCodes}';
        if (barCodes!='' && barCodes=='ALL') {
            document.getElementById("mainTable").style.display="none";
        }

        var terminalIds = '${coupon.terminals}';
        var radios = $("input[name='terminals']");
        if (radios[1].checked) {
            $("#machine").show();
            loadDoubleBox(terminalIds);
        }

    })

    $("#machineAll").on("click", function() {
        $("#machine").hide();
    })
    $("#machinePART").on("click", function() {
        $("#machine").show();
    })

    // 加载双边选择器
    function loadDoubleBox(terminalIds) {
        $.ajax({
            url:'${pageContext.request.contextPath}/coupon/getmachinelist',
            type:'post',
            dataType:'json',
            data:{terminalIds: terminalIds},
            success:function (data) {
                $('.machine').doublebox({
                    nonSelectedListLabel: '未选择设备',
                    selectedListLabel: '已选择设备',
                    filterPlaceHolder: '请输入设备名称',
                    moveSelectedLabel: '加入',
                    moveAllLabel: '全部加入',
                    removeSelectedLabel: '移除',
                    removeAllLabel: '全部移除',
                    preserveSelectionOnMove: 'moved',
                    moveOnSelect: false,
                    nonSelectedList: data.extInfo.unSelect,
                    selectedList: data.extInfo.select,
                    optionValue:"id",
                    optionText:"name",
                    doubleMove:true,
                });
            }
        })
    }

</script>

</body>
</html>