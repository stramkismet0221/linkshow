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
    <title>字段信息</title>
</head>

<body class="fix-sidebar">
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <%@ include file="/WEB-INF/views/include/header.jsp" %>
    </nav>
    <div class="navbar-default sidebar" role="navigation">
        <jsp:include page="/WEB-INF/views/include/left.jsp">
            <jsp:param value="/jumi/product/goodsextend/getlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">自定义字段信息</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品管理</a></li>
                        <li class="active">自定义字段信息</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" class="form-material form-horizontal" data-toggle="validator">
                                    <div class="form-group">
                                        <label class="col-md-12">字段ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${jmGoodsExtend.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">字段名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${jmGoodsExtend.name}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="radio-list">
                                            <label class="radio-inline ">
                                                <div class="">
                                                    <label>是否必填</label>
                                                </div>
                                            </label>
                                            <label class="radio-inline">
                                                <div class="radio radio-info">
                                                    <input type="radio" name="isNotNull" id="r1" value="1" <c:if test="${jmGoodsExtend.isNotNull == 1}">checked </c:if> disabled />
                                                    <label for="r1">是</label>
                                                </div>
                                            </label>
                                            <label class="radio-inline">
                                                <div class="radio radio-info">
                                                    <input type="radio" name="isNotNull" id="r2" value="0" <c:if test="${jmGoodsExtend.isNotNull == 0}">checked </c:if> disabled/>
                                                    <label for="r2">否</label>
                                                </div>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="radio-list">
                                            <label class="radio-inline ">
                                                <div class="">
                                                    <label>应用商品类型</label>
                                                </div>
                                            </label>
                                            <label class="radio-inline">
                                                <div class="radio radio-info">
                                                    <input type="radio" name="goodsType" id="g1" value="1" <c:if test="${jmGoodsExtend.goodsType == 1}">checked </c:if> disabled/>
                                                    <label for="g1">实物</label>
                                                </div>
                                            </label>
                                            <label class="radio-inline">
                                                <div class="radio radio-info">
                                                    <input type="radio" name="goodsType" id="g2" value="2" <c:if test="${jmGoodsExtend.goodsType == 2}">checked </c:if> disabled/>
                                                    <label for="g2">虚拟 </label>
                                                </div>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">字段类型</label>
                                        <div class="col-sm-4">
                                            <select class="form-control" name="fieldType" id="fieldType" disabled>
                                                <option value="1" <c:if test="${jmGoodsExtend.fieldType == 1}">selected </c:if>>单行文本框</option>
                                                <option value="2" <c:if test="${jmGoodsExtend.fieldType == 2}">selected </c:if>>下拉选项</option>
                                                <option value="3" <c:if test="${jmGoodsExtend.fieldType == 3}">selected </c:if>>单选</option>
                                                <option value="4" <c:if test="${jmGoodsExtend.fieldType == 4}">selected </c:if>>多选</option>
                                                <option value="5" <c:if test="${jmGoodsExtend.fieldType == 5}">selected </c:if>>日历</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group"
                                            <c:if test="${jmGoodsExtend.fieldType == 2}">
                                                id="selectiondiv"
                                            </c:if>
                                            <c:if test="${jmGoodsExtend.fieldType == 3}">
                                                id="radiodiv"
                                            </c:if>
                                            <c:if test="${jmGoodsExtend.fieldType == 4}">
                                                id="checkboxdiv"
                                            </c:if>
                                    >
                                        <c:forEach items="${jmGoodsExtend.items}" var="ex">
                                            <div class="col-md-8 m-b-30" style='display: inline-flex'>
                                                <input type="text"
                                                        <c:if test="${jmGoodsExtend.fieldType == 2}">
                                                            name="selectionItem"
                                                        </c:if>
                                                        <c:if test="${jmGoodsExtend.fieldType == 3}">
                                                            name="radioItem"
                                                        </c:if>
                                                        <c:if test="${jmGoodsExtend.fieldType == 4}">
                                                            name="checkboxItem"
                                                        </c:if>
                                                       value="${ex}" disabled
                                                      class="form-control" aria-describedby="basic-addon2"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-5"></div>
                                    <div class="col-lg-2 col-sm-4 col-xs-12">
                                        <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
                                            <span style="vertical-align: inherit;">返回</span>
                                        </button>
                                    </div>
                                </div>
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