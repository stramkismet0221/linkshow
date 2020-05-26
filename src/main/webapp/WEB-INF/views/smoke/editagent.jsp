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
    <title>渠道商修改</title>
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
            <jsp:param value="/smoke/getAgentList" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 渠道商修改</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 渠道商列表</a></li>
                        <li class="active"> 渠道商修改</li>
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
                                <input type="hidden" name="id" value="${sellSmokeAgent.id}"/>
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material"
                                      data-toggle="validator" novalidate="true">
                                    <div class="form-group">
                                        <label class="col-md-12" id="agentName"><label style="color: #f05b4f">*</label>&nbsp;渠道商名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentName" required="required" maxlength="50"
                                                   placeholder="请输入渠道商名称" data-error="请填写渠道商名称" class="form-control"
                                                   value="${sellSmokeAgent.name}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12" id="contacts"><label style="color: #f05b4f">*</label>&nbsp;联系人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="contacts" required="required" maxlength="50"
                                                   placeholder="请填写联系人" data-error="请填写联系人" class="form-control"
                                                   value="${sellSmokeAgent.contacts}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label
                                                style="color: #f05b4f">*</label>&nbsp;联系手机号码</label>
                                        <div class="col-md-12">
                                            <input type="text" name="mobile" required="required" maxlength="11"
                                                   placeholder="请输入手机号码" data-error="请填写手机号码" class="form-control"
                                                   value="${sellSmokeAgent.mobile}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12" id="address"><label style="color: #f05b4f">*</label>&nbsp;所在地址</label>
                                        <div class="col-md-12">
                                            <input type="text" name="address" required="required" maxlength="100"
                                                   placeholder="请输入地址" data-error="请填写地址" class="form-control"
                                                   value="${sellSmokeAgent.address}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;&nbsp;备注</label>
                                        <div class="col-md-12">
                                            <textarea id="remark" name="remark" maxlength="200" class="form-control" rows="5">${sellSmokeAgent.remark}</textarea>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit"
                                                    value="保存">
                                                <span style="vertical-align: inherit;">保存</span>
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

<script type="text/javascript">
    function save() {
        var id = $("input[name='id']").val();
        var agentName = $("input[name='agentName']").val();
        var contacts = $("input[name='contacts']").val().trim();
        var mobile = $("input[name='mobile']").val();
        var address = $("input[name='address']").val();
        var remark =  $("#remark").val().trim();

        if (checkStr(mobile)) {
            $("input[name='mobile']").focus();
            return false;
        }
        var pattern = /^\d+$/;
        if (!checkStr(mobile)) {
            if (!pattern.test(mobile) || mobile.length !== 11) {
                swal({
                    title: "请输入正确的手机号",
                    confirmButtonText: "确定",
                });
                return false;
            }
        }

        if (agentName.length < 1) {
            swal({
                title: "请输入渠道商名称",
                confirmButtonText: "确定",
            });
            return false;
        }

        if (contacts.length < 1) {
            swal({
                title: "请输入联系人",
                confirmButtonText: "确定",
            });
            return false;
        }

        if (address.length < 1) {
            swal({
                title: "请输入所在地址",
                confirmButtonText: "确定",
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
                var param = {};
                param.id = id
                param.contacts = contacts
                param.name = agentName
                param.mobile = mobile
                param.address = address
                param.remark = remark
                $.ajax({
                    url: "${pageContext.request.contextPath}/smoke/updateAgent",
                    type: "POST",
                    dataType: "json",
                    data: param,
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/smoke/getAgentList";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                cancelButtonText: "确定"
                            });
                        }
                    },
                    error: function () {
                        alert("保存失败");
                    }
                });
            }
        });
    }
</script>

</body>
</html>