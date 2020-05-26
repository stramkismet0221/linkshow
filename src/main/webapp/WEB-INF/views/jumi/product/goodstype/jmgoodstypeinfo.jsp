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
    <title>商品分类详情</title>
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
                <jsp:param value="/jumi/product/goodstype/getlist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">商品分类详情</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">商品管理</a></li>
                            <li class="active">商品分类详情</li>
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
                                    <div>
                                        <form id="form" class="form-material form-horizontal" onsubmit="update();return false;" data-toggle="validator" novalidate="true">
                                            <div class="form-group">
                                                <label class="col-md-12">分类ID</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="id" class="form-control" value="${jmGoodsType.id}" disabled/>
                                                </div>
                                            </div>
                                            <c:if test="${jmGoodsType.pid != 0}">
                                                <div class="form-group">
                                                    <label class="col-md-12">上级分类</label>
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control" value="${jmGoodsType.parent.name}" disabled/>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="form-group">
                                                <label class="col-md-12">分类名称</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="name"  class="form-control" value="${jmGoodsType.name}" disabled/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12">分类编码（不可重复）</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="code" class="form-control" value="${jmGoodsType.code}" disabled />
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12" style="margin-bottom: 15px;">展示情况（电商展示/实体展示）</label>
                                                <div class="col-sm-12">
                                                    <div class="checkbox checkbox-info" style="display: inline-block;margin-right: 5%;">
                                                        <input type="checkbox" name="showType" id="online"  <c:if test="${jmGoodsType.showType.contains('1')}"> checked </c:if>  disabled/>
                                                        <label for="online">电商展示</label>
                                                    </div>
                                                    <div class="checkbox checkbox-info" style="display: inline-block">
                                                        <input type="checkbox" name="showType" id="outline"  <c:if test="${jmGoodsType.showType.contains('2')}"> checked </c:if>  disabled/>
                                                        <label for="outline">实体展示</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12">序号</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="sno" class="form-control" value="${jmGoodsType.sno}" disabled />
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12">分类备注</label>
                                                <div class="col-md-12">
                                            <textarea name="description" maxlength="200" rows="5" class="form-control" disabled>${jmGoodsType.description}</textarea>
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