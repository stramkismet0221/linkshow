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
    <c:if test="${type == 1}">
        <c:set var="title" value="实物商品"></c:set>
    </c:if>
    <c:if test="${type == 2}">
        <c:set var="title" value="虚拟商品"></c:set>
    </c:if>
    <c:if test="${type == 3}">
        <c:set var="title" value="卡包商品"></c:set>
    </c:if>
    <c:if test="${type == 4}">
        <c:set var="title" value="原材料"></c:set>
    </c:if>
    <title>${title}列表</title>
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
            <jsp:param value="/jumi/product/goods/getlist?type=${type}" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">商品列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品中心</a></li>
                        <li class="active">商品列表</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="row">
                            <form id="form1" onsubmit="search();return false;">
                                <div class="col-md-2" >
                                    <h5 class="m-t-30 m-b-10">商品名称</h5>
                                    <input type="text" class="form-control" id="keyword" maxlength="50" placeholder="请输入商品名称/条码" value="${fields.keyword}"/>
                                </div>
                                <div class="col-md-2" >
                                    <h5 class="m-t-30 m-b-10">商品分类</h5>
                                    <select id="goodsTypeId" class="form-control" data-placeholder="商品分类" tabindex="1">
                                        <c:forEach items="${jmGoodsTypes}" var="data">
                                            <c:if test="${data.level == 1}">
                                                <option value="${data.id}" <c:if test="${data.id == fields.goodsTypeId}"> selected </c:if>>${data.name}</option>
                                            </c:if>
                                            <c:if test="${data.level == 2}">
                                                <option value="${data.id}" <c:if test="${data.id == fields.goodsTypeId}"> selected </c:if>>---${data.name}</option>
                                            </c:if>
                                            <c:if test="${data.level == 3}">
                                                <option value="${data.id}" <c:if test="${data.id == fields.goodsTypeId}"> selected </c:if>>------${data.name}</option>
                                            </c:if>
                                            <c:if test="${data.level == 4}">
                                                <option value="${data.id}" <c:if test="${data.id == fields.goodsTypeId}"> selected </c:if>>---------${data.name}</option>
                                            </c:if>
                                            <c:if test="${data.level == 5}">
                                                <option value="${data.id}" <c:if test="${data.id == fields.goodsTypeId}"> selected </c:if>>------------${data.name}</option>
                                            </c:if>
                                            <c:set var="goods" value="${fields}" scope="request"></c:set>
                                            <%@include file="recursiveOptionsforlist.jsp"%>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <h5 class="m-t-30 m-b-10">价格区间</h5>
                                    <div class="col-md-3" style="padding-left: 0;">
                                        <select class="form-control" id="priceType" data-style="form-control">
                                            <option value="">全部</option>
                                            <option value="1" <c:if test="${fields.priceType == 1}">selected</c:if>>零售价</option>
                                            <option value="2" <c:if test="${fields.priceType == 2}">selected</c:if>>设备价</option>
                                            <option value="3" <c:if test="${fields.priceType == 3}">selected</c:if>>网店价</option>
                                            <option value="4" <c:if test="${fields.priceType == 4}">selected</c:if>>成本价</option>
                                            <option value="5" <c:if test="${fields.priceType == 5}">selected</c:if>>批发价</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4" style="">
                                        <input type="text" class="form-control" id="priceRangeStart" maxlength="10" value="${fields.priceRangeStart}" onkeyup="btnonkeyupDiscountfour(this,value)"/>
                                    </div>
                                    <div class="col-md-1" style="text-align: center;padding-left: 5px">
                                        <label class="m-t-10" >-</label>
                                    </div>
                                    <div class="col-md-4" style="float: left">
                                        <input type="text" class="form-control" id="priceRangeEnd" maxlength="10" value="${fields.priceRangeEnd}" onkeyup="btnonkeyupDiscountfour(this,value)"/>
                                    </div>
                                </div>
                                <%--<div class="col-md-3">--%>
                                    <%--<h5 class="m-t-30 m-b-10">总销量</h5>--%>
                                    <%--<div class="col-md-3" style="padding-left: 0;">--%>
                                        <%--<input type="text" class="form-control" id="salesRangeStart" maxlength="4" value="${fields.salesRangeStart}"/>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-1" style="text-align: center;padding-left: 5px">--%>
                                        <%--<label class="m-t-10" >到</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-3" style="padding-left: 0;">--%>
                                        <%--<input type="text" class="form-control" id="salesRangeEnd" maxlength="4" value="${fields.salesRangeEnd}"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="col-md-1">
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="submit" class="btn btn-block btn-info" value="查询"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/jumi/product/goods/getlist" id="form">
                <input type="hidden" name="type" value="${type}">
                <input type="hidden" name="keyword" value="${fields.keyword}">
                <input type="hidden" name="goodsTypeId" value="${fields.goodsTypeId}">
                <input type="hidden" name="status" value="${fields.status}">
                <input type="hidden" name="priceType" value="${fields.priceType}">
                <input type="hidden" name="priceRangeStart" value="${fields.priceRangeStart}">
                <input type="hidden" name="priceRangeEnd" value="${fields.priceRangeEnd}">
                <input type="hidden" name="salesRangeStart" value="${fields.salesRangeStart}">
                <input type="hidden" name="salesRangeEnd" value="${fields.salesRangeEnd}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4">
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/jumi/product/goods/addgoods?type=${type}'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>商品ID</th>
                                            <th>图片</th>
                                            <th>名称/条码</th>
                                            <c:if test="${type != 4}">
                                                <th>零售价</th>
                                                <th>设备价</th>
                                                <th>网店价</th>
                                                <th>批发价</th>
                                            </c:if>
                                            <th>分类</th>
                                            <c:if test="${type != 4}">
                                                <th>规格</th>
                                            </c:if>
                                            <c:if test="${type != 2}">
                                                <th>库存</th>
                                            </c:if>
                                            <th>成本价</th>
                                            <c:if test="${type != 4}">
                                                <th>设备毛利率</th>
                                                <th>网店毛利率</th>
                                                <th>销量</th>
                                                <th>状态</th>
                                            </c:if>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${goodsList}" var="gg">
                                            <tr>
                                                <td>${gg.id}</td>
                                                <td>
                                                    <a href="${gg.icons}" target="_blank">
                                                        <img src="${gg.icons}" style="width: 40px; height: 60px"  alt="" />
                                                    </a>
                                                </td>
                                                <td title="${gg.name}">
                                                    <c:set var="str" value="${gg.name}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 15}">
                                                            <c:out value="${fn:substring(str, 0, 15)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <h5>${gg.barCode}</h5>
                                                </td>
                                                <c:if test="${type != 4}">
                                                    <td>${gg.priceStr==null?no:gg.priceStr}</td>
                                                    <td>${gg.terminalPriceStr}</td>
                                                    <td>${gg.onlinePriceStr}</td>
                                                    <td>${gg.tradePriceStr}</td>
                                                </c:if>
                                                <td>${gg.goodsTypeName}</td>
                                                <c:if test="${type != 4}">
                                                    <td>${gg.specs}</td>
                                                </c:if>
                                                <c:if test="${type != 2}">
                                                    <td>${gg.stock}</td>
                                                </c:if>
                                                <td>${gg.costPriceStr}</td>
                                                <c:if test="${type != 4}">
                                                    <td>${gg.terminalGrossRate}</td>
                                                    <td>${gg.onlineGrossRate}</td>
                                                    <td>${gg.sales}</td>
                                                    <td>${gg.salesStatus}</td>
                                                </c:if>
                                                <td>
                                                    <div>
                                                        <div style="display: inline-block; width:27%">
                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情"
                                                                   onclick="location.href='${pageContext.request.contextPath}/jumi/product/goods/editgoods?id=${gg.id}&oType=0'" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:27%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改"
                                                                   onclick="location.href='${pageContext.request.contextPath}/jumi/product/goods/editgoods?id=${gg.id}&oType=1'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:27%">
                                                            <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${gg.id});" />
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="16">
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
                    url: "${pageContext.request.contextPath}/jumi/product/goods/deletegoods",
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
                                location.href = "${pageContext.request.contextPath}/jumi/product/goods/getlist";
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
        var keyword = $("#keyword").val();
        $("input[name='keyword']").val(keyword);
        var goodsTypeId = $("#goodsTypeId").val();
        $("input[name='goodsTypeId']").val(goodsTypeId);
        var priceType = $("#priceType").val();
        $("input[name='priceType']").val(priceType);
        if (!checkStr(priceType)) {
            var priceRangeEnd = $("#priceRangeEnd").val();
            var priceRangeStart = $("#priceRangeStart").val();
            if (checkStr(priceRangeEnd) && checkStr(priceRangeStart)) {
                $("input[name='priceType']").val(priceType);
            }
            $("input[name='priceRangeStart']").val(priceRangeStart);
            $("input[name='priceRangeEnd']").val(priceRangeEnd);
        }
        // var salesRangeStart = $("#salesRangeStart").val();
        // $("input[name='salesRangeStart']").val(salesRangeStart);
        // var salesRangeEnd = $("#salesRangeEnd").val();
        // $("input[name='salesRangeEnd']").val(salesRangeEnd);
        $("#form").submit();
    }
</script>
</html>
