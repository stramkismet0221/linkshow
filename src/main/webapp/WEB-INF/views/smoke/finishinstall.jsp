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
    <title>电子烟设备安装</title>
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
            <jsp:param value="/smoke/getuserlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">设备安装</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">电子烟设备管理</a></li>
                        <li class="active">设备安装</li>
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
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeAgent.name}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;渠道商联系人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeAgent.contacts}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;渠道商联系方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeAgent.mobile}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;预约安装时间</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="<fmt:formatDate value="${sellSmokeInstall.appointTime}" pattern="yyyy-MM-dd"/>" disabled/>
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
                                            <textarea id="applyRemark" name="applyRemark" maxlength="100" class="form-control" rows="5" disabled>${sellSmokeInstall.applyRemark}</textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;处理人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeInstall.auditUserName}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;处理人联系方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeInstall.auditUserMobile}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;处理时间</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="<fmt:formatDate value="${sellSmokeInstall.auditTime}" pattern="yyyy-MM-dd"/>" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;安装人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeInstall.installUserName}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;安装人联系方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="agentId" required="required" class="form-control" value="${sellSmokeInstall.installUserMobile}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;处理备注</label>
                                        <div class="col-md-12">
                                            <textarea  name="applyRemark" maxlength="100" class="form-control" rows="5" disabled>${sellSmokeInstall.auditRemark}</textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;设备类型</label>
                                        <div class="col-md-12">
                                            <input type="text" name="store" required="required" maxlength="50"
                                                   class="form-control" value="${dic.name}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;设备数量</label>
                                        <div class="col-md-12">
                                            <input type="text" name="store" required="required" maxlength="50"
                                                   class="form-control" value="${sellSmokeInstall.tNumber}" disabled/>
                                        </div>
                                    </div>


                                    <c:forEach var="i" begin="1" end="${sellSmokeInstall.tNumber}" step="1">
                                    <div class="form-group">
                                        <label class="col-md-6"><label style="color: #f05b4f">*</label>&nbsp;安装设备型号${i}</label>
                                        <label class="col-md-6"><label style="color: #f05b4f">*</label>&nbsp;安装设备名称${i}</label>
                                        <div class="col-md-6">
                                            <input type="text" name="terminalCode${i}" required="required" maxlength="50"
                                                   class="form-control" placeholder="请填写设备型号${i}"/>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" name="terminalName${i}" required="required" maxlength="50"
                                                   class="form-control" placeholder="请填写设备名称${i}"/>
                                        </div>
                                    </div>
                                    </c:forEach>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;&nbsp;安装意见</label>
                                        <div class="col-md-12">
                                            <textarea id="installRemark" name="installRemark" maxlength="100" class="form-control" rows="5" placeholder="请输入安装意见"></textarea>
                                        </div>
                                    </div>

                                    <c:if test="${sellSmokeInstall.remark != null && sellSmokeInstall.remark != ''}">
                                        <div class="form-group">
                                            <label class="col-md-12">&nbsp;&nbsp;撤回原因</label>
                                            <div class="col-md-12">
                                                <textarea id="remark" name="remark" maxlength="100" class="form-control" rows="5" disabled>${sellSmokeInstall.remark}</textarea>
                                            </div>
                                        </div>
                                    </c:if>

                                    <div class="row">
                                        <div class="col-lg-3"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit"
                                                    value="安装">
                                                <span style="vertical-align: inherit;">安装</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="button"
                                                    value="取消安装" onclick="cancel('${sellSmokeInstall.id}');">
                                                <span style="vertical-align: inherit;">取消安装</span>
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
        var tNumber = ${sellSmokeInstall.tNumber};
        var terminals = '';
        for (var i = 1; i <= tNumber; i++) {
            if ($("input[name='terminalCode"+i+"']").val() == "") {
                swal({
                    title: "请填写设备型号"+i,
                    confirmButtonText: "确定"
                })
                $("input[name='terminalCode"+i+"']").focus();
                return false;
            }
            if ($("input[name='terminalName"+i+"']").val() == "") {
                swal({
                    title: "请填写设备名称"+i,
                    confirmButtonText: "确定"
                })
                $("input[name='terminalName"+i+"']").focus();
                return false;
            }
            terminals += $("input[name='terminalCode"+i+"']").val() + ";" + $("input[name='terminalName"+i+"']").val() + ",";
        }
        terminals = (terminals.substring(terminals.length - 1) == ',') ? terminals.substring(0, terminals.length - 1) : terminals;

        var id = $("input[name='id']").val();
        var installUserId = $("input[name='installUserId']").val();
        var installUserName = $("input[name='installUserName']").val();
        var installUserMobile = $("input[name='installUserMobile']").val();
        var installRemark = $("#installRemark").val();
        var remark = $("#remark").val();
        swal({
            title: "是否安装",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/smoke/installterminal",
                    type: "POST",
                    dataType: "json",
                    data: {id:id, installRemark:installRemark,terminals:terminals,remark:remark},
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "安装成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/smoke/getconmnginslist";
                            });
                        } else {
                            swal({
                                title: "处理失败",
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

    // 取消
    function cancel(id) {
        var installRemark = $("#installRemark").val();
        var type = 2;
        swal({
            title: "是否取消安装",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/smoke/cancelinstall",
                    type: "POST",
                    dataType: "json",
                    data: {id:id, type:type, installRemark:installRemark},
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "处理成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/smoke/getconmnginslist";
                            });
                        } else {
                            swal({
                                title: "处理失败",
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