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
    <title>购货订单列表</title>
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
            <jsp:param value="/jumi/order/purchaseorder/getlist?type=${type}" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">购货订单列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">购货订单中心</a></li>
                        <li class="active">购货订单列表</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="row">
                            <div class="col-md-3" >
                                <h5 class="m-t-30 m-b-10">关键字</h5>
                                <input type="text" class="form-control" id="keyWord" maxlength="100" placeholder="请输入单号、供应商名称、备注" value="${purchaseOrder.keyWord}"/>
                            </div>
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">按创建时间</h5>
                                <input class="form-control input-daterange-datepicker" maxlength="50" type="text" onkeyup="getNullStr()" id="poDate" name="daterange" placeholder="请输入单据时间时间"/>
                            </div>
                            <div class="col-md-3">
                                <h5 class="m-t-30 m-b-10">入库状态</h5>
                                <select class="form-control" id="status">
                                    <option value="">请选择</option>
                                    <option value="0" <c:if test="${purchaseOrder.status == 0}"> selected </c:if>>待入库</option>
                                    <option value="1" <c:if test="${purchaseOrder.status == 1}"> selected </c:if>>已入库</option>
                                    <option value="2" <c:if test="${purchaseOrder.status == 2}"> selected </c:if>>部分入库</option>
                                    <option value="3" <c:if test="${purchaseOrder.status == 3}"> selected </c:if>>已关闭</option>
                                </select>
                            </div>
                            <div class="col-md-1">
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/jumi/order/purchaseorder/getlist" id="form">
                <input type="hidden" typeof="${type}">
                <input type="hidden" name="keyWord">
                <input type="hidden" name="startTime">
                <input type="hidden" name="endTime">
                <input type="hidden" name="status">
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4">
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/jumi/order/purchaseorder/toaddpurchaseorder?type=${type}'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>订单日期</th>
                                            <th>订单编号</th>
                                           <c:if test="${type == 1}">
                                               <th>业务类别	</th>
                                           </c:if>
                                            <th>门店	</th>
                                            <th>供应商</th>
                                            <c:if test="${type != 3}">
                                                <c:if test="${type == 1}">
                                                    <th>关联购货单号</th>
                                                </c:if>
                                                <c:if test="${type == 2}">
                                                    <th>关联订货单号</th>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${type == 3}">
                                                <th>关联购货单号</th>
                                                <th>关联订货单号</th>
                                            </c:if>
                                            <th>购货金额	</th>
                                            <c:if test="${type == 2 || type == 3}">
                                                <th>优惠后金额</th>
                                            </c:if>
                                            <c:if test="${type == 3}">
                                                <th>原购货数量</th>
                                            </c:if>
                                            <th>数量</th>
                                           <c:if test="${type == 1}">
                                               <th>订单状态	</th>
                                               <th>交货日期	</th>
                                           </c:if>
                                            <th>制单人</th>
                                            <th>备注	</th>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${purchaseOrders}" var="data">
                                            <tr <c:if test="${data.checkStatus == 1}">  style="color: #000000" </c:if>>
                                                <td><fmt:formatDate value="${data.poDate}" pattern="YYYY-MM-dd"/></td>
                                                <c:if test="${type == 1}">
                                                    <td>${data.ghNo}</td>
                                                </c:if>
                                                <c:if test="${type == 2}">
                                                    <td>${data.ghdNo}</td>
                                                </c:if>
                                                <c:if test="${type == 3}">
                                                    <td>${data.ghtNo}</td>
                                                </c:if>
                                                <c:if test="${type == 1}">
                                                    <td>
                                                        <c:set var="str" value="${data.businessType}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${str == 1}">
                                                                <c:out value="购货" />
                                                            </c:when>
                                                            <c:when test="${str == 2}">
                                                                <c:out value="退货" />
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                </c:if>
                                                <td>--</td>
                                                <td title="${data.supplierName}">${data.supplierName}</td>
                                                <c:if test="${type == 1}">
                                                    <td>${data.ghNo}</td>
                                                </c:if>
                                                <c:if test="${type == 2}">
                                                    <td>${data.ghdNo}</td>
                                                </c:if>
                                                <c:if test="${type == 3}">
                                                    <td>${data.ghNo}</td>
                                                    <td>${data.ghdNo}</td>
                                                </c:if>
                                                <td>${data.totalPrice}</td>
                                                <c:if test="${type == 2 || type == 3}">
                                                    <td>${data.discountAfterPrice}</td>
                                                </c:if>
                                                <c:if test="${type == 3}">
                                                    <td>${data.oldCount}</td>
                                                </c:if>
                                                <td>${data.totalCount}</td>
                                                <c:if test="${type == 1}">
                                                    <td>
                                                        <c:set var="str" value="${data.status}"></c:set>
                                                        <c:choose>
                                                            <c:when test="${str == 0}">
                                                                <c:out value="待入库" />
                                                            </c:when>
                                                            <c:when test="${str == 1}">
                                                                <c:out value="已入库" />
                                                            </c:when>
                                                            <c:when test="${str == 2}">
                                                                <c:out value="部分入库" />
                                                            </c:when>
                                                            <c:when test="${str == 3}">
                                                                <c:out value="已关闭" />
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <fmt:formatDate value="${data.giveTime}" pattern="yyyy-MM-dd"/>
                                                    </td>
                                                </c:if>
                                                <td>${data.creator}</td>
                                                <td>${data.remark}</td>
                                                <c:if test="${data.type == 1 || data.type == 2}">
                                                    <td>
                                                        <div>
                                                            <div style="display: inline-block; width:11%">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="审核" onclick="checkOrder(${data.id},1)"/>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:14%">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="反审核" onclick="checkOrder(${data.id},2)"/>
                                                            </div>
                                                            <c:if test="${type == 1}">
                                                                <div style="display: inline-block; width:26%">
                                                                    <input type="button" class="btn btn-block btn-info btn-xs" value="生成入库单据" onclick="location.href='${pageContext.request.contextPath}/jumi/order/purchaseorder/purchaseorderinfo?id=${data.id}&ptype=2'"/>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${type == 2}">
                                                                <div style="display: inline-block; width:28%">
                                                                    <input type="button" class="btn btn-block btn-info btn-xs" value="生成购货退货单" onclick="location.href='${pageContext.request.contextPath}/jumi/order/purchaseorder/purchaseorderinfo?id=${data.id}&ptype=3'"/>
                                                                </div>
                                                            </c:if>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:11%">
                                                                <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情"
                                                                       onclick="location.href='${pageContext.request.contextPath}/jumi/order/purchaseorder/purchaseorderinfo?id=${data.id}&type=0'" />
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:11%">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="修改"
                                                                       onclick="location.href='${pageContext.request.contextPath}/jumi/order/purchaseorder/purchaseorderinfo?id=${data.id}&type=1'"/>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:11%">
                                                                <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${data.id});" />
                                                            </div>
                                                        </div>
                                                    </td>
                                                </c:if>
                                                <c:if test="${data.type == 3}">
                                                    <td>
                                                        <div>
                                                            <div style="display: inline-block; width:15%">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="审核" onclick="checkOrder(${data.id},1)"/>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:20%">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="反审核" onclick="checkOrder(${data.id},2)"/>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:15%">
                                                                <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情"
                                                                       onclick="location.href='${pageContext.request.contextPath}/jumi/order/purchaseorder/purchaseorderinfo?id=${data.id}&type=0'" />
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:15%">
                                                                <input type="button" class="btn btn-block btn-info btn-xs" value="修改"
                                                                       onclick="location.href='${pageContext.request.contextPath}/jumi/order/purchaseorder/purchaseorderinfo?id=${data.id}&type=1'"/>
                                                            </div>
                                                            &nbsp;
                                                            <div style="display: inline-block; width:15%">
                                                                <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${data.id});" />
                                                            </div>
                                                        </div>
                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="15">
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
    $(document).ready(function () {
        var startTime = "${purchaseOrder.startTime}";
        var endTime = "${purchaseOrder.endTime}";
        if (startTime != "" && endTime != "") {
            $("input[name='daterange']").val(startTime + " - " + endTime);
        } else {
            $("input[name='daterange']").val("");
        }
        $('#single-input').clockpicker({
            placement: 'bottom',
            align: 'left',
            autoclose: true,
            'default': 'now'
        });
        if (/mobile/i.test(navigator.userAgent)) {
            $('input').prop('readOnly', true);
        }
        $('.input-daterange-datepicker').daterangepicker({

            maxDate: moment(), //最大时间
            opens: 'left',
            showDropdowns: true,
            buttonClasses: ['btn', 'btn-sm'],
            applyClass: 'btn-danger',
            cancelClass: 'btn-inverse',
            locale: {
                format: 'YYYY-MM-DD',
                applyLabel: '确定',
                cancelLabel: '取消',
                fromLabel: '起始时间',
                toLabel: '结束时间',
                customRangeLabel: '手动选择',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'
                ],
                firstDay: 1
            }
        });
    })
</script>
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
                    url: "${pageContext.request.contextPath}/jumi/order/purchaseorder/delpurchaseorder",
                    type: "POST",
                    dataType: "json",
                    data: {"id": id},
                    success: function (result) {
                        console.log(result);
                        if (result.success) {
                            swal({
                                title: result.msg,
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/order/purchaseorder/getlist?type=${purchaseOrder.type}";
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

    function checkOrder(id,status) {
        swal({
            title: "确定审核/反审核吗?",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/jumi/order/purchaseorder/updatestatus",
                    type: "POST",
                    dataType: "json",
                    data: {"id": id,status:status},
                    success: function (result) {
                        swal({
                            title: result.msg,
                            confirmButtonText: "确定"
                        }, function () {
                            location.href = "${pageContext.request.contextPath}/jumi/order/purchaseorder/getlist?type=${purchaseOrder.type}";
                        });
                    },
                    error: function () {
                        swal({
                            title: "审核失败",
                            confirmButtonText: "删除失败"
                        });
                    }
                });
            }
        })
    }

    // 查询
    function search() {
        var keyWord = $("#keyWord").val();
        $("input[name='keyWord']").val(keyWord);
        var poDate = $("#poDate").val();
        if (poDate != '' && poDate != null) {
            var dateArr = poDate.split(" - ");
            $("input[name='startTime']").val(dateArr[0]);
            $("input[name='endTime']").val(dateArr[1]);
        }
        var status = $("#status").val();
        $("input[name='status']").val(status);
        $("#form").submit();
    }
    function getNullStr() {
        $("#createTime").val("");
    }
</script>
</html>