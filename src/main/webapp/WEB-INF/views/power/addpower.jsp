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
    <title>新增权限</title>
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
            <jsp:param value="/power/getpowerlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">新增权限</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">权限管理</a></li>
                        <li class="active">新增权限</li>
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
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator">
                                    <div class="form-group">
                                        <label class="col-md-12" for="systemId"><label style="color: #f05b4f">*</label>&nbsp;所属系统</label>
                                        <div class="col-sm-12">
                                            <select class="form-control" id="systemId" name="systemId">
                                                <c:forEach var="system" items="${systemList}">
                                                    <option value="${system.key}" ${system.key==systemId?"selected":""}>${system.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;权限名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="name" maxlength="50" required="required" placeholder="请输入权限名称" data-error="请输入权限名称" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;权限编码</label>
                                        <div class="col-md-12">
                                            <input type="text" name="code"  maxlength="50" required="required" placeholder="请输入权限编码" data-error="请输入权限编码"  class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">权限描述</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="200" class="form-control" rows="5" placeholder="请输入权限描述"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12" for="type"><label style="color: #f05b4f">*</label>&nbsp;权限类型</label>
                                        <div class="col-sm-12">
                                            <select class="form-control" id="type" name="type">
                                                <%--权限类型：1、系统登录权限 2、菜单权限 3、操作权限 4、系统管理员权限--%>
                                                <option value="1">系统登录权限</option>
                                                <option value="2">菜单权限</option>
                                                <option value="3">操作权限</option>
                                                <option value="4">系统管理员权限</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="submit">
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
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
    function save() {

        // 校验
        var name = $("input[name='name']").val();
        if (name === '') {
            $("input[name='name']").focus();
            return false;
        }
        var code = $("input[name='code']").val();
        if (code === '') {
            $("input[name='code']").focus();
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
                    url: "${pageContext.request.contextPath}/power/getpowerbycode",
                    type: "POST",
                    dataType: "json",
                    data: {"code":code},
                    success: function (result) {
                        if (result>0) {
                            swal({
                                title: "权限唯一编码已存在,请重新输入",
                                confirmButtonText: "确定"
                            });
                        }else {
                            // ajax提交
                            $.ajax({
                                url: "${pageContext.request.contextPath}/power/addpower",
                                type: "POST",
                                dataType: "json",
                                data: $("#form").serialize(),
                                success: function (result) {
                                    if (result.success) {
                                        swal({
                                            title: "保存成功",
                                            confirmButtonText: "确定"
                                        }, function () {
                                            location.href = "${pageContext.request.contextPath}/power/getpowerlist?systemId=${systemId}";
                                        });
                                    }
                                },
                                error: function () {
                                    alert("保存失败");
                                }
                            });
                        }
                    }
                })
            }
        });
    }
</script>

</body>
</html>