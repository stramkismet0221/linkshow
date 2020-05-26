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
    <c:set var="type" value="${goods.type}"></c:set>
    <c:if test="${type == 1}">
        <c:set var="title" value="实物"></c:set>
    </c:if>
    <c:if test="${type == 2}">
        <c:set var="title" value="虚拟"></c:set>
    </c:if>
    <c:if test="${type == 3}">
        <c:set var="title" value="卡包"></c:set>
    </c:if>
    <c:if test="${type == 4}">
        <c:set var="title" value="原材料"></c:set>
    </c:if>
    <c:choose>
        <c:when test="${type == 4}">
            <title>${title}</title>
        </c:when>
        <c:otherwise>
            <title>${title}商品详情</title>
        </c:otherwise>
    </c:choose>
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
            <jsp:param value="/jumi/product/goods/getlist?type=${goods.type}" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 商品详情</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 商品管理</a></li>
                        <li class="active"> 商品详情</li>
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
                                    <input type="hidden" name="id" value="${goods.id}">
                                    <input type="hidden" name="extendFields">
                                    <input type="hidden" name="type" value="${goods.type}">
                                    <input type="hidden" name="icons" value="${goods.icons}">
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;${title}ID</label>
                                        <div class="col-md-12">
                                            <input type="text"  class="form-control" value="${goods.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6">条码</label>
                                        <label class="col-md-6">商家自编码</label>
                                        <div class="col-md-6">
                                            <input type="text" name="barCode" class="form-control" value="${goods.barCode}" disabled/>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" name="code" class="form-control" value="${goods.code}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <c:if test="${goods.type == 1 || goods.type == 2}">
                                            <label class="col-md-6" id="name">商品名称</label>
                                        </c:if>
                                        <c:if test="${goods.type == 4}">
                                            <label class="col-md-6" id="name">材料名称</label>
                                        </c:if>
                                        <label class="col-md-6" id="shortCode">拼音简码</label>

                                        <div class="col-md-6">
                                            <input type="text" name="name" class="form-control" value="${goods.name}" disabled/>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" name="shortCode" class="form-control" value="${goods.shortCode}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6">商品分类</label>
                                        <label class="col-md-6">规格型号</label>
                                        <div class="col-md-6">
                                            <select name="goodsTypeId" class="form-control" tabindex="1" disabled>
                                                <c:forEach items="${jmGoodsTypes}" var="data">
                                                    <c:if test="${data.level == 1}">
                                                        <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 2}">
                                                        <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>---${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 3}">
                                                        <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>------${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 4}">
                                                        <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>---------${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 5}">
                                                        <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>------------${data.name}</option>
                                                    </c:if>
                                                    <%@include file="recursiveOptions.jsp"%>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" name="specs" required="required" maxlength="10"
                                                   class="form-control" value="${goods.specs}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <c:if test="${goods.type == 1 || goods.type == 4}">
                                            <label class="col-md-6">单位</label>
                                        </c:if>
                                        <c:if test="${goods.type == 2}">
                                            <label class="col-md-12">单位</label>
                                        </c:if>
                                        <c:if test="${goods.type == 1 || goods.type == 4}">
                                            <label class="col-md-6">首选仓库</label>
                                        </c:if>

                                        <c:if test="${goods.type == 1 || goods.type == 4}">
                                            <div class="col-md-6">
                                                <select name="unitId" class="form-control" tabindex="1" disabled>
                                                    <c:forEach items="${jmGoodsUnits}" var="data">
                                                        <option value="${data.id}" <c:if test="${goods.unitId == data.id}">selected</c:if>>${data.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${goods.type == 2}">
                                            <div class="col-md-6">
                                                <select name="unitId" class="form-control" tabindex="1" disabled>
                                                    <c:forEach items="${jmGoodsUnits}" var="data">
                                                        <option value="${data.id}" <c:if test="${goods.unitId == data.id}">selected</c:if>>${data.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${goods.type == 1 || goods.type == 4}">
                                            <div class="col-md-6">
                                                <select name="wareHouseId" class="form-control" tabindex="1" disabled>
                                                    <c:forEach items="${warehouses}" var="data">
                                                        <option value="${data.id}" <c:if test="${goods.wareHouseId == data.id}">selected</c:if>>${data.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>
                                    </div>
                                    <c:if test="${goods.type == 1 || goods.type == 2}">
                                        <div class="form-group">
                                            <label class="col-md-6">零售价</label>
                                            <label class="col-md-6">批发价</label>
                                            <div class="col-md-6">
                                                <input type="text" name="price" class="form-control"  value="${goods.priceStr==null?no:goods.priceStr}" disabled/>
                                            </div>
                                            <div class="col-md-6">
                                                <input type="text" name="tradePrice" class="form-control"  value="${goods.tradePriceStr==null?no:goods.tradePriceStr}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6">网店价</label>
                                            <label class="col-md-6">设备价</label>
                                            <div class="col-md-6">
                                                <input type="text" name="onlinePrice" class="form-control"  value="${goods.onlinePriceStr==null?no:goods.onlinePriceStr}" disabled/>
                                            </div>
                                            <div class="col-md-6">
                                                <input type="text" name="terminalPrice" class="form-control" value="${goods.terminalPriceStr==null?no:goods.terminalPriceStr}" disabled/>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-6">成本价</label>
                                        <label class="col-md-6">&nbsp;</label>
                                        <div class="col-md-6">
                                            <input type="text" name="onlinePrice" class="form-control"  value="${goods.costPriceStr==null?no:goods.costPriceStr}" disabled/>
                                        </div>
                                        <div class="col-md-6"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">图片</label>
                                        <div class="col-md-12">
                                            <c:forTokens items="${goods.icons}" delims="," var="liceimg">
                                                <img src="<c:out value="${liceimg}"/>" id="<c:out value="${liceimg}"/>" style="width: 100px;" onclick="enlarge(this)"/>&nbsp;&nbsp;&nbsp;
                                            </c:forTokens>
                                        </div>
                                    </div>
                                    <c:if test="${goods.type == 1 || goods.type == 2}">
                                        <div class="form-group">
                                            <div class="col-md-2">
                                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                                    自定义字段
                                                </button>
                                            </div>
                                        </div>
                                        <%--自定义字段--%>
                                        <c:set var="checkAns" value="${goods.checkBoxAns}"></c:set>
                                        <c:set var="jmGoodsExtends" value="${jmGoodsExtends}"></c:set>
                                        <c:set var="arr" value="${goods.extednsFieldsArr}"></c:set>
                                        <%@include file="extendfiledsfordetail.jsp"%>
                                    </c:if>
                                    <%--扩展属性--%>
                                    <div class="form-group">
                                        <div class="col-md-2">
                                            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample1" aria-expanded="false" aria-controls="collapseExample1">
                                                扩展属性
                                            </button>
                                        </div>
                                    </div>
                                    <c:set var="goods" value="${goods}"></c:set>
                                    <%@include file="extendsattributefordetail.jsp"%>
                                    <c:if test="${type == 1 || type == 4}">
                                        <%--预警--%>
                                        <div class="form-group">
                                            <div class="col-md-2">
                                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample2" aria-expanded="false" aria-controls="collapseExample2">
                                                    库存预警
                                                </button>
                                            </div>
                                        </div>
                                        <div class="collapse" id="collapseExample2">
                                            <div class="panel-body">
                                                <div class="panel-body" style="padding: 10px;">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <label class="col-md-6"><label style="color: #f05b4f"></label>&nbsp;库存值低于</label>
                                                            <label class="col-md-6"><label style="color: #f05b4f"></label>&nbsp;库存值高于</label>
                                                            <div class="col-md-6">
                                                                <input type="text" name="minStock" disabled
                                                                       class="form-control" placeholder="库存数" value="${goods.minStock==null?0:goods.minStock}"/>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <input type="text" name="maxStock" disabled
                                                                       class="form-control"  placeholder="库存数" value="${goods.maxStock==null?0:goods.maxStock}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row">
                                        <div class="col-lg-5"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()" type="button">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
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
<script type="text/javascript">
    $(function () { $('#collapseExample').collapse('show')});
    $(function () { $('#collapseExample1').collapse('show')});
    $(function () { $('#collapseExample2').collapse('show')});
    function enlarge(parm) {
        var width = $(parm).width();
        if(width == 100){
            $(parm).css("width", "300px");
        }else{
            $(parm).css("width", "100px");
        }
    }
</script>
</html>