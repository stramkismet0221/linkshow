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
    <title>电子烟设备安装安装信息修改</title>
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
            <jsp:param value="/smoke/getconmnginslist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">安装修改</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">售烟机管理</a></li>
                        <li class="active">安装修改</li>
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
                                    <input type="hidden" name="id" value="${sellSmokeInstall.id}"/>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;渠道商</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeInstall.agentName}" disabled/>
                                        </div>
                                    </div>

                                    <input type="hidden" name="status" value="0"/>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="store" required="required" maxlength="50"
                                                   placeholder="请输入商户名称" data-error="请填写商户名称" class="form-control" value="${sellSmokeInstall.store}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户联系人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="storeUser" required="required" maxlength="10"
                                                   placeholder="请输入商户联系人" data-error="请填写商户联系人" class="form-control" value="${sellSmokeInstall.storeUser}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户联系手机号码</label>
                                        <div class="col-md-12">
                                            <input type="text" name="mobile" required="required" maxlength="11"
                                                   placeholder="请输入商户联系人手机号码" data-error="请填写手机号" class="form-control" value="${sellSmokeInstall.mobile}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商户地址</label>
                                        <div class="col-md-12">
                                            <input type="text" name="address" required="required" maxlength="100"
                                                   placeholder="请输入地址" data-error="请填写地址" class="form-control" value="${sellSmokeInstall.address}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;申请人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeInstall.applyUserName}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;申请人联系方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeInstall.applyUserMobile}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;申请时间</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="<fmt:formatDate value="${sellSmokeInstall.applyTime}" pattern="yyyy-MM-dd"/>" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;申请备注</label>
                                        <div class="col-md-12">
                                            <textarea id="applyRemark" name="applyRemark" maxlength="100" class="form-control" rows="5" >${sellSmokeInstall.applyRemark}</textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12" for="terminalType"><label style="color: #f05b4f">*</label>&nbsp;设备类型</label>
                                        <div class="col-sm-12">
                                            <input type="hidden" name="terminalType" value=""/>
                                            <select class="form-control" id="terminalType">
                                                <c:forEach items="${dicList}" var="dic">
                                                    <option value="${dic.code}" ${dic.code==sellSmokeInstall.terminalType?"selected":""}>${dic.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;设备数量</label>
                                        <div class="col-md-12">
                                            <input type="text" name="tNumber" required="required" maxlength="10"
                                                   placeholder="请输入设备数量" data-error="请填写数量" class="form-control" value="${sellSmokeInstall.tNumber}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;预约安装时间</label>
                                        <div class="col-md-12">
                                            <div class="input-group">
                                                <input type="text" name="appointTimeStr" class="form-control" id="datepicker-autoclose" value="<fmt:formatDate value="${sellSmokeInstall.appointTime}" pattern="yyyy-MM-dd"/>">
                                                <span class="input-group-addon">
                                                <i class="icon-calender"></i></span>
                                            </div>
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

        var terminalType = $("#terminalType").val();

        $("input[name='terminalType']").val(terminalType);

        var tNumber = $("input[name='tNumber']").val();
        if (checkStr(tNumber)) {
            $("input[name='tNumber']").focus();
            return false;
        }
        var regTNumber = /^[0-9]*$/;
        if (!regTNumber.test(tNumber)) {
            swal({
                title: "请输入正确的安装数量",
                confirmButtonText: "确定",
            });
            $("input[name='tNubmer']").focus();
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
                    url: "${pageContext.request.contextPath}/smoke/updateinstall",
                    type: "POST",
                    dataType: "json",
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "修改成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/smoke/getconmnginslist";
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