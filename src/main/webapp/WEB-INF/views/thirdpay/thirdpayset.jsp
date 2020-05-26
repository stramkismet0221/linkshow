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
    <title>支付配置</title>
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
            <jsp:param value="/pay/tosetthirdpay" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"></h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">支付配置</a></li>
                        <li class="active">收款配置</li>
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
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;支付跳转地址</label>
                                        <div class="col-md-12">
                                            <input type="text" name="payUrl" required="required" placeholder="请输入支付跳转地址" data-error="请输入支付跳转地址"
                                                   class="form-control" value="http://test.ddzhuan.cn/wdapp/pay/orderdetails" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;第三方服务端API地址</label>
                                        <div class="col-md-12">
                                            <input type="text" name="serverUrl" required="required" placeholder="请输入第三方服务端API地址" data-error="请输入第三方服务端API地址"
                                                   class="form-control" value="http://test.ddzhuan.cn/wdapp/pay/finish"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;签名密钥</label>
                                        <div class="col-md-12">
                                            <input type="text" name="signKey" required="required"  maxlength="100" placeholder="请输入密钥"  data-error="请输入密钥"
                                                   class="form-control" value="f0615d54a22e8c0d5f6a6c7041693b8c"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;支付渠道</label>
                                        <div class="col-md-12" style="display: inline-flex;">
                                            <c:forEach items="${thirdPays}" var="third">
                                                <div class="checkbox checkbox-info" style="margin-right: 15px;">
                                                    <input  type="checkbox" name="payType" id="${third.id}">
                                                    <label for="${third.id}">
                                                        <img src="../common/images/${third.icon}" style="width: 24px;height: 24px">
                                                            ${third.name}</label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit" value="保存" >
                                                <span style="vertical-align: inherit;">保存</span>
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
</body>
</html>
<script type="text/javascript">
    function save() {

        var payUrl = $("input[name='payUrl']").val();
        if (checkStr(payUrl)){
            $("input[name='payUrl']").focus();
            return false;
        }
        var serverUrl = $("input[name='serverUrl']").val();
        if (checkStr(serverUrl)){
            $("input[name='serverUrl']").focus();
            return false;
        }

        var signKey = $("input[name='signKey']").val();
        if (checkStr(signKey)){
            $("input[name='signKey']").focus();
            return false;
        }

        var payTypes = [];
        $("input:checked").each(function () {
            var id = $(this).attr("id");
            payTypes.push(id);
        });

        if (payTypes.length <= 0){
            swal({
                title: "请选择支付方式",
                confirmButtonText: "确定"
            });
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
                            url: "${pageContext.request.contextPath}/pay/setthirdpaytype",
                            type: "POST",
                            dataType: "json",
                            traditional:true,
                            data: {"payUrl":payUrl,"serverUrl":serverUrl,"signKey":signKey,"payTypes":payTypes},
                            success: function (result) {
                                swal({
                                    title: result.msg,
                                    confirmButtonText: "确定"
                                });
                            },
                            error: function () {
                                swal({
                                    title: "保存失败",
                                    confirmButtonText: "确定"
                                });
                            }
                        });
                    }
            }
        );
    }
</script>