<%--
  Created by IntelliJ IDEA.
  User: jiang yong tao
  Date: 2019/12/27
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
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
    <title>用户修改</title>
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
            <jsp:param value="/jumi/product/goodsmanufatory/getlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">厂家修改</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品管理</a></li>
                        <li class="active">厂家修改</li>
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
                                            <label class="col-md-12">&nbsp;厂家ID</label>
                                            <div class="col-md-12">
                                                <input type="text" name="id" class="form-control" value="${jmGoodsManuFatory.id}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;厂家名称</label>
                                            <div class="col-md-12">
                                                <input type="text" name="name" required="required" maxlength="100" placeholder="请输入厂家名称"
                                                       data-error="请输入厂家名称" class="form-control" value="${jmGoodsManuFatory.name}" />
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;厂家地址</label>
                                            <div class="col-md-12">
                                                <input type="text" name="address" required="required" maxlength="150" placeholder="请输入厂家地址"
                                                       data-error="请输入厂家地址"  class="form-control" value="${jmGoodsManuFatory.address}"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">厂家介绍</label>
                                            <div class="col-md-12">
                                                <textarea name="description" rows="5" maxlength="300" placeholder="请输入厂家介绍" class="form-control">${jmGoodsManuFatory.description}</textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4"></div>
                                            <div class="col-lg-2 col-sm-4 col-xs-12">
                                                <button class="btn btn-block btn-info text-uppercase" type="submit" value="保存">
                                                    <span style="vertical-align: inherit;">保存</span>
                                                </button>
                                            </div>
                                            <div class="col-lg-2 col-sm-4 col-xs-12">
                                                <button class="btn btn-block btn-primary text-uppercase" onclick="cancel()" type="button">
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
<script type="text/javascript">
    function update() {
        var name = $("input[name='name']").val();
        // 校验
        if (checkStr(name)) {
            $("input[name='name']").focus();
            return false;
        }

        var realName = $("input[name='address']").val();
        if (checkStr(realName)) {
            $("input[name='address']").focus();
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
                    url: "${pageContext.request.contextPath}/jumi/product/goodsmanufatory/updatejmgoodsmanufatory",
                    type: "POST",
                    dataType: "json",
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/product/goodsmanufatory/getlist";
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
    function cancel() {
        swal({
            title: "确定返回吗?",
            text: "确定后将返回列表页!",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                location.href = "${pageContext.request.contextPath}/jumi/product/goodsmanufatory/getlist";
            }
        });
    }
</script>

</body>
</html>