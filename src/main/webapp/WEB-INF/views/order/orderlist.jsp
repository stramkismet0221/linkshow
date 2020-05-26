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
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>订单列表</title>
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
                <jsp:param value="/order/getorderlist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">订单列表</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">订单中心</a></li>
                            <li class="active">订单列表</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-md-2" >
                                    <h5 class="m-t-30 m-b-10">订单号</h5>
                                    <input type="text" class="form-control" id="receiptNo" maxlength="50" placeholder="请输入订单号" value="${orderInfo.receiptNo}"/>
                                </div>
                                <div class="col-md-2" >
                                    <h5 class="m-t-30 m-b-10">商品名称</h5>
                                    <input type="text" class="form-control" id="name" maxlength="50" placeholder="请输入商品名称" value="${orderInfo.name}"/>
                                </div>
                                <div class="col-md-2" >
                                    <h5 class="m-t-30 m-b-10">售货机名称</h5>
                                    <input type="text" class="form-control" id="tName" maxlength="50" placeholder="请输入售货机名称" value="${orderInfo.tName}"/>
                                </div>
                                <div class="col-md-2" >
                                    <h5 class="m-t-30 m-b-10">场地名称</h5>
                                    <input type="text" class="form-control" id="placeName" maxlength="50" placeholder="请输入场地名称" value="${orderInfo.placeName}"/>
                                </div>
                                <div class="col-md-1" >
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                                </div>
                                <div class="col-md-1" >
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <div id="advanced" class="btn-group m-r-10">
                                        <a class="collapsed btn btn-primary dropdown-toggle waves-effect waves-light" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" onclick="switchIcon();">
                                            高级查询 <span class="caret"></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="panel-group wiz-aco" id="accordion" role="tablist" aria-multiselectable="true">
                                    <div class="panel panel-default">
                                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                            <div class="panel-body" style="padding: 10px;">
                                                <div class="col-md-2">
                                                    <h5 class="m-b-10">支付方式</h5>
                                                    <select class="form-control " id="payType">
                                                        <option value="">全部</option>
                                                        <option value="1" ${orderInfo.payType==1?"selected":""}>微信</option>
                                                        <option value="2" ${orderInfo.payType==2?"selected":""}>支付宝</option>
                                                        <option value="991" ${orderInfo.payType==99&&orderInfo.thirdPayType==1?"selected":""}>TASK</option>
                                                        <option value="992" ${orderInfo.payType==99&&orderInfo.thirdPayType==2?"selected":""}>喵钻</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-2" >
                                                    <h5 class="m-b-10">支付状态</h5>
                                                    <select class="form-control " id="payStatus">
                                                        <option value="">全部</option>
                                                        <option value="0" ${orderInfo.payStatus==0?"selected":""}>未支付</option>
                                                        <option value="1" ${orderInfo.payStatus==1?"selected":""}>已支付</option>
                                                        <option value="2" ${orderInfo.payStatus==2?"selected":""}>支付失败</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-2" >
                                                    <h5 class="m-b-10">出货状态</h5>
                                                    <select class="form-control " id="outStatus">
                                                        <option value="">全部</option>
                                                        <option value="0" ${orderInfo.outStatus==0?"selected":""}>等待出货</option>
                                                        <option value="1" ${orderInfo.outStatus==1?"selected":""}>出货成功</option>
                                                        <option value="2" ${orderInfo.outStatus==2?"selected":""}>出货失败</option>
                                                    </select>
                                                </div>
                                                <div class="col-md-2" >
                                                    <h5 class="m-b-10">退款状态</h5>
                                                    <select class="form-control " id="refundStatus">
                                                        <option value="">全部</option>
                                                        <option value="0" ${orderInfo.refundStatus==0?"selected":""}>未退款</option>
                                                        <option value="1" ${orderInfo.refundStatus==1?"selected":""}>退款成功</option>
                                                        <option value="2" ${orderInfo.refundStatus==2?"selected":""}>退款失败</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/order/getorderlist" id="form">
                    <input type="hidden" name="receiptNo" value="${orderInfo.receiptNo}"/>
                    <input type="hidden" name="name" value="${orderInfo.name}"/>
                    <input type="hidden" name="tName" value="${orderInfo.tName}"/>
                    <input type="hidden" name="placeName" value="${orderInfo.placeName}"/>
                    <input type="hidden" name="payType" value="${orderInfo.payType}"/>
                    <input type="hidden" name="thirdPayType" value="${orderInfo.thirdPayType}"/>
                    <input type="hidden" name="payStatus" value="${orderInfo.payStatus}"/>
                    <input type="hidden" name="outStatus" value="${orderInfo.outStatus}"/>
                    <input type="hidden" name="refundStatus" value="${orderInfo.refundStatus}"/>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="white-box">
                                <div class="panel">
                                    <div class="table-responsive">
                                        <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                            <thead>
                                                <tr>
                                                    <th>订单号</th>
                                                    <th>商品名称</th>
                                                    <th>所属售货机</th>
                                                    <th>所在场地</th>
                                                    <th>支付方式</th>
                                                    <th>售价</th>
                                                    <th>订单时间</th>
                                                    <th width="100">操作</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${orderInfos}" var="orderInfo">
                                                <tr>
                                                    <td>${orderInfo.receiptNo}</td>
                                                    <td>${orderInfo.name}</td>
                                                    <td>${orderInfo.tName}</td>
                                                    <td>${orderInfo.placeName}</td>
                                                    <c:if test="${orderInfo.payType==1}">
                                                        <td>微信</td>
                                                    </c:if>
                                                    <c:if test="${orderInfo.payType==2}">
                                                        <td>支付宝</td>
                                                    </c:if>
                                                    <c:if test="${orderInfo.payType==99 && orderInfo.thirdPayType==1}">
                                                        <td>TASK</td>
                                                    </c:if>
                                                    <c:if test="${orderInfo.payType==99 && orderInfo.thirdPayType==2}">
                                                        <td>喵钻</td>
                                                    </c:if>
                                                    <c:if test="${orderInfo.payType==99}">
                                                        <td>${orderInfo.taskPrice}</td>
                                                    </c:if>
                                                    <c:if test="${orderInfo.payType!=99}">
                                                        <td>${orderInfo.price}</td>
                                                    </c:if>
                                                    <td>
                                                        <fmt:formatDate value="${orderInfo.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                    </td>
                                                    <td>
                                                        <div>
                                                            <div style="display: inline-block; width:80%">
                                                                <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="订单详情" onclick="location.href='${pageContext.request.contextPath}/order/orderdetail?id=${orderInfo.id}'" />
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

    $(function(){
        var payType = $("#payType").val();
        var payStatus = $("#payStatus").val();
        var outStatus = $("#outStatus").val();
        var refundStatus = $("#refundStatus").val();
        if (payType!=""||payStatus!=""||outStatus!=""||refundStatus!="") {
            $("#collapseTwo").addClass("in");
        }
    });

    // 切换dropup、dropdown
    function switchIcon() {
        if ($("#collapseTwo").attr("class").indexOf("collapse in") > -1) {
            $("#advanced").removeClass("dropup");
        } else {
            $("#advanced").addClass("dropup");
        }
    }
    // 查询
    function search() {
        // 基本查询
        var receiptNo = $("#receiptNo").val();
        var name = $("#name").val();
        var tName = $("#tName").val();
        var placeName = $("#placeName").val();
        $("input[name='receiptNo']").val(receiptNo);
        $("input[name='name']").val(name);
        $("input[name='tName']").val(tName);
        $("input[name='placeName']").val(placeName);
        // 高级查询
        var payType = $("#payType").val();
        var thirdPayType = null;
        if (payType == 991) {
            payType = 99;
            thirdPayType = 1;
        }
        if (payType == 992) {
            payType = 99;
            thirdPayType = 2;
        }
        var payStatus = $("#payStatus").val();
        var outStatus = $("#outStatus").val();
        var refundStatus = $("#refundStatus").val();
        $("input[name='payType']").val(payType);
        $("input[name='thirdPayType']").val(thirdPayType);
        $("input[name='payStatus']").val(payStatus);
        $("input[name='outStatus']").val(outStatus);
        $("input[name='refundStatus']").val(refundStatus);

        $("input[name='totalPage']").val(1);
        $("input[name='page']").val(1);
        $("#form").submit();
    }
</script>
</html>