<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>电子烟设备安装申请</title>
</head>
<body class="fix-sidebar">
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <%@include file="/WEB-INF/views/include/header.jsp" %>
    </nav>
    <div class="navbar-default sidebar" role="navigation">
        <jsp:include page="/WEB-INF/views/include/left.jsp">
            <jsp:param value="/smoke/${visitUrl}" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">申请安装</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">电子烟管理</a></li>
                        <li class="active">申请安装</li>
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
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material"
                                      data-toggle="validator" novalidate="true">
                                    <input type="hidden" name="status" value="0"/>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="store" required="required" maxlength="50"
                                                   placeholder="请输入商户名称" data-error="请填写商户名称" class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户联系人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="storeUser" required="required" maxlength="10"
                                                   placeholder="请输入商户联系人" data-error="请填写商户联系人" class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户联系方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="mobile" required="required" maxlength="11"
                                                   placeholder="请输入商户联系方式" data-error="请填写商户联系方式" class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户地址</label>
                                        <div class="col-md-12">
                                            <input type="text" name="address" required="required" maxlength="100"
                                                   placeholder="请输入地址" data-error="请填写地址" class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12" for="terminalType"><label style="color: #f05b4f">*</label>&nbsp;设备类型</label>
                                        <div class="col-sm-12">
                                            <select class="form-control" id="terminalType" name="terminalType">
                                                <c:forEach items="${dicList}" var="dic">
                                                    <option value="${dic.code}">${dic.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;设备数量</label>
                                        <div class="col-md-12">
                                            <input type="text" name="tNumber" required="required" maxlength="10"
                                                   placeholder="请输入设备数量" data-error="请填写数量" class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;预约安装时间</label>

                                        <div class="col-md-12">
                                            <div class="input-group">
                                                <input type="text" name="appointTimeStr" class="form-control" id="datepicker-autoclose" placeholder="选择日期">
                                                <span class="input-group-addon">
                                                <i class="icon-calender"></i></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">申请备注</label>
                                        <div class="col-md-12">
                                            <textarea name="applyRemark" class="form-control" maxlength="150" rows="5" placeholder="请输入申请备注"></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit"
                                                    value="申请">
                                                <span style="vertical-align: inherit;">申请</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-primary text-uppercase"
                                                    onclick="history.back()">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
                <%--<div class="col-md-1"></div>--%>
            </div>
            <div class="right-sidebar">
                <%@include file="/WEB-INF/views/include/right.jsp" %>
            </div>
        </div>
        <footer class="footer text-center">
            <%@include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
</div>

<script>
    jQuery(document).ready(function() {

        $('#datepicker-autoclose').datepicker({
            format:'yyyy-mm-dd',
        });
    })
</script>

<script type="text/javascript">
    function save() {

        var store = $("input[name='store']").val();
        if (checkStr(store)) {
            $("input[name='store']").focus();
            return false;
        }
        var storeUser = $("input[name='storeUser']").val();
        if (checkStr(storeUser)) {
            $("input[name='storeUser']").focus();
            return false;
        }

        var mobile = $("input[name='mobile']").val();
        if (checkStr(mobile)) {
            $("input[name='mobile']").focus();
            return false;
        }
        var reg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!reg.test(mobile)) {
            swal({
                title: "请输入正确的手机号码",
                confirmButtonText: "确定",
            });
            $("input[name='mobile']").focus();
            return false;
        }

        var address = $("input[name='address']").val();
        if (checkStr(address)) {
            $("input[name='address']").focus();
            return false;
        }

        var tNumber = $("input[name='tNumber']").val();
        if (checkStr(tNumber)) {
            $("input[name='tNumber']").focus();
            return false;
        }
        var regTNumber = /^[0-9]*$/;
        if (!regTNumber.test(tNumber)) {
            swal({
                title: "请输入正确的数量",
                confirmButtonText: "确定",
            });
            $("input[name='tNubmer']").focus();
            return false;
        }

        var appointTimeStr = $("input[name='appointTimeStr']").val();
        if (checkStr(appointTimeStr)) {
            $("input[name='appointTimeStr']").focus();
            swal({
                title: "请选择预约安装时间",
                confirmButtonText: "确定",
            });
            return false;
        }

        swal({
            title: "是否申请",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/smoke/saveapply",
                    type: "POST",
                    dataType: "json",
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "申请成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/smoke/${visitUrl}";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                cancelButtonText: "确定"
                            });
                        }
                    },
                    error: function () {
                        alert("申请失败");
                    }
                });
            }
        });
    }
</script>

</body>
</html>