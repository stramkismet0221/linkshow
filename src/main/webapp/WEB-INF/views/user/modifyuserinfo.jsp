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
                <jsp:param value="/user/getuserlist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">用户修改</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">用户管理</a></li>
                            <li class="active">用户修改</li>
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
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;用户ID</label>
                                                <div class="col-md-12">
                                                    <input type="text" class="form-control" value="${user.id}" disabled/>
                                                    <input type="hidden" name="id"  class="form-control" value="${user.id}" readonly="readonly" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;用户名</label>
                                                <div class="col-md-12">
                                                    <input type="text" class="form-control" value="${user.userName}" disabled/>
                                                    <input type="hidden" name="userName" required="required" maxlength="50" placeholder="请输入用户名" data-error="请填写用户名"  class="form-control" value="${user.userName}" readonly="readonly" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;用户姓名</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="realName" required="required" maxlength="50" placeholder="请输入用户姓名" data-error="请填写用户姓名" class="form-control" value="${user.realName}" />
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;手机号</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="mobile" required="required" maxlength="11" placeholder="请输入手机号" data-error="请填写手机号" class="form-control" value="${user.mobile}" />
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12">Email</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="email" maxlength="50" class="form-control" value="${user.email}" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12" for="locked"><label style="color: #f05b4f">*</label>&nbsp;用户锁定状态</label>
                                                <div class="col-sm-12">
                                                    <select class="form-control" id="locked" name="locked">
                                                        <option value="0" <c:if test="${user.locked == 0}">selected = "selected"</c:if>>未锁定</option>
                                                        <option value="1" <c:if test="${user.locked == 1}">selected = "selected"</c:if>>已锁定</option>
                                                    </select>
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
            // 校验表单
            var userName = $("input[name='userName']").val();
            var pattern = /^[A-Za-z0-9]+$/;
            if ( !checkStr(userName)) {
                if (!pattern.test(userName)){
                    swal({
                        title: "用户名仅支持大小写英文字母和数字",
                        confirmButtonText: "确定",
                    });
                    $("input[name='userName']").focus();
                    return false;
                }
            }else {
                $("input[name='userName']").focus();
                return false;
            }

            if ($("input[name='realName']").val() === '') {
                $("input[name='realName']").focus();
                return false;
            }
            var mobile = $("input[name='mobile']").val();

            if (checkStr(mobile)) {
                $("input[name='mobile']").focus();
                return false;
            }
            var pattern = /^\d+$/;
            if (!checkStr(mobile)){
                if (!pattern.test(mobile) || mobile.length !== 11){
                    swal({
                        title: "请输入正确的手机号",
                        confirmButtonText: "确定",
                    });
                    return false;
                }
            }

            var locked = $("select[name='locked']").val();
            if (locked === '') {
                $("select[name='locked']").focus();
                return false;
            }

            $.ajax({
                url : "${pageContext.request.contextPath}/user/modifyuser",
                type : "POST",
                dataType : "json",
                data : $("#form").serialize(),
                success : function(result) {
                    if (result.success) {
                        swal({title : "保存成功",
                            confirmButtonText: "确定"}, function(){
                            location.href = "${pageContext.request.contextPath}/user/getuserlist";
                        });
                    } else {
                        swal({title : result.msg,
                            cancelButtonText: "确定"
                        });
                    }
                },
                error : function() {
                    alert("保存失败");
                }
            });

            <%----%>

            <%--$.ajax({--%>
                <%--url: "${pageContext.request.contextPath}/user/getusercountbyusername",--%>
                <%--type: "POST",--%>
                <%--dataType: "json",--%>
                <%--data: {"userName":userName},--%>
                <%--success: function (result) {--%>
                    <%--if (result <= 0) {--%>
                        <%----%>

                    <%--}else {--%>
                        <%--swal({--%>
                            <%--title: "该用户名已存在请重新输入",--%>
                            <%--confirmButtonText: "确定"--%>
                        <%--})--%>
                    <%--}--%>
                <%--},--%>
                <%--error: function () {--%>
                    <%--alert("保存失败");--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
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
                    location.href = "${pageContext.request.contextPath}/user/getuserlist";
                }
            });
        }
    </script>

</body>
</html>