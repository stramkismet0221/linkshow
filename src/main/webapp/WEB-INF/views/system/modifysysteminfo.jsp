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
    <title>系统修改</title>
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
                <jsp:param value="/system/getsysteminfolist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">系统修改</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">系统管理</a></li>
                            <li class="active">系统修改</li>
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
                                    <form id="form" onsubmit="update();return false;" class="form-material form-horizontal" data-toggle="validator">
                                        <input type="hidden" name="status" value="${data.status}" />
                                        <input type="hidden" name="createTime" value="${data.createTime}"/>
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;系统ID</label>
                                            <div class="col-md-12">
                                                <input type="text" value="${data.id}" class="form-control" disabled/>
                                                <input type="hidden" name="id" class="form-control" value="${data.id}" readonly="readonly"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;系统名称</label>
                                            <div class="col-md-12">
                                                <input type="text" name="name" maxlength="50" data-error="请输入系统名称" required="required" class="form-control" value="${data.name}" />
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">系统描述</label>
                                            <div class="col-md-12">
                                                <textarea name="description" maxlength="200" class="form-control" rows="5">${data.description}</textarea>
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
            // 校验表单
            if ($("input[name='name']").val() == '') {
                $("input[name='name']").focus();
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
                    // ajax提交
                    $.ajax({
                        url : "${pageContext.request.contextPath}/system/updatesysteminfo",
                        type : "POST",
                        dataType : "json",
                        data : $("#form").serialize(),
                        success : function(result) {
                            if (result.success) {
                                swal({title : result.msg,
                                    confirmButtonText: "确定"}, function(){
                                    location.href = "${pageContext.request.contextPath}/system/getsysteminfolist";
                                });
                            } else {
                                swal({title : result.msg,
                                    cancelButtonText: "确定"});
                            }
                        },
                        error : function() {
                            alert("保存失败");
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
                    location.href = "${pageContext.request.contextPath}/system/getsysteminfolist";
                }
            });
        }
    </script>

</body>
</html>