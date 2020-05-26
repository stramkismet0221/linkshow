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
    <title>订单信息</title>
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
            <jsp:param value="/order/getorderlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">订单信息</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">订单中心</a></li>
                        <li class="active">订单信息</li>
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
                                        <label class="col-md-12">ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${orderPost.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">配送方式</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${orderPost.postType}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">开通状态</label>
                                        <div class="col-md-12">
                                            <select class="form-control" disabled>
                                                <option <c:if test="${orderPost.openStatus == 1}"> selected </c:if>>已开通</option>
                                                <option <c:if test="${orderPost.openStatus == 0}"> selected </c:if>>未开通</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">是否默认</label>
                                        <div class="col-md-12">
                                            <select class="form-control" disabled>
                                                <option <c:if test="${orderPost.deafStatus == 1}"> selected </c:if>>是</option>
                                                <option <c:if test="${orderPost.deafStatus == 0}"> selected </c:if>>否</option>
                                            </select>
                                        </div>
                                    </div>
                                    <c:if test="${orderPost.id != 2}">
                                        <div class="form-group">
                                            <label class="col-md-12">配送价格</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderPost.postPrice}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">配送范围</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderPost.postAround}" disabled/>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-12">序号</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${orderPost.sno}" disabled/>
                                        </div>
                                    </div>
                                    <c:if test="${orderPost.id != 2}">
                                        <div class="form-group">
                                            <label class="col-md-12">是否包邮</label>
                                            <div class="col-md-12">
                                                <select class="form-control" disabled>
                                                    <option <c:if test="${orderPost.freeShippingStatus == 1}"> selected </c:if>>是</option>
                                                    <option <c:if test="${orderPost.freeShippingStatus == 0}"> selected </c:if>>否</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group"  <c:if test="${orderPost.freeShippingStatus == 0}"> style="display: none;" </c:if>>
                                            <label class="col-md-12">订单满N元免费配送（包邮）</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderPost.enoughPrice}" disabled/>
                                            </div>
                                        </div>
                                    </c:if>
                                </form>
                                <div class="row">
                                    <div class="col-lg-5"></div>
                                    <div class="col-lg-2 col-sm-4 col-xs-12">
                                        <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()" type="button">
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