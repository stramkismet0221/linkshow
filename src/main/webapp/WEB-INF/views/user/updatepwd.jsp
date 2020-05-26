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
    <title>修改密码</title>
</head>
<body class="fix-sidebar">
	<div class="preloader">
		<svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none"
				stroke-width="2" stroke-miterlimit="10" />
    </svg>
	</div>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top m-b-0">
			<%@include file="/WEB-INF/views/include/header.jsp"%>
		</nav>
		<div class="navbar-default sidebar" role="navigation">
			<jsp:include page="/WEB-INF/views/include/left.jsp">
				<jsp:param value="/user/getuserlist" name="visitUrl" />
			</jsp:include>
		</div>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row bg-title">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title">修改密码</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="white-box p-l-20 p-r-20">
							<p class="text-muted m-b-30 font-13"></p>
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8">
									<form id="form" onsubmit="save();return false;"
										class="form-horizontal form-material" data-toggle="validator"
										novalidate="true">
										<div class="form-group">
											<label class="col-md-12" id="oldpwd"><label
												style="color: #f05b4f">*</label>&nbsp;旧密码</label>
											<div class="col-md-12">
												<input type="password" name="oldpwd" value=""
													required="required" id="oldpwds" maxlength="20"
													placeholder="请输入旧密码" data-error="请输入旧密码"
													class="form-control" />
												<div class="help-block with-errors"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-12"><label
												style="color: #f05b4f">*</label>&nbsp;新密码</label>
											<div class="col-md-12">
												<input type="password" name="newpwd" required="required"
													maxlength="20" placeholder="请输入新密码" data-error="请输入新密码"
													class="form-control" />
												<div class="help-block with-errors"></div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-12"><label
												style="color: #f05b4f">*</label>&nbsp;确认密码</label>
											<div class="col-md-12">
												<input type="password" name="okpwd" required="okpwd"
													maxlength="20" placeholder="请输入确认密码" data-error="请输入确认密码"
													class="form-control" />
												<div class="help-block with-errors"></div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-4"></div>
											<div class="col-lg-2 col-sm-4 col-xs-12">
												<button class="btn btn-block btn-info text-uppercase"
													type="submit" value="保存">
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
			var oldpwd = $("input[name='oldpwd']").val();
			var newpwd = $("input[name='newpwd']").val();
			var okpwd = $("input[name='okpwd']").val();
			var reg2 = /([a-zA-Z0-9`~!@#$%^&*()_\-+{}|:<>?\[\\\];,./｀～！＠＃＄％＾＆＊（）＿＋［］＼｛｝｜；＇：＂，。、《》？]){6,20}/;
			var reg3 = /[a-zA-Z]+/;
			var reg4 = /[0-9]+/;
			if(checkStr(oldpwd)){
				$("input[name='oldpwd']").focus();
		         return false;
			}
			if(checkStr(okpwd)){
				$("input[name='okpwd']").focus();
		         return false;
			}
			
			
			if (!checkStr(newpwd)) {
				if (!reg2.test(newpwd)) {
					swal({
						title : "请输入6-20位数字、字母、标点符号",
						confirmButtonText : "确定",
					});
					$("input[name='newpwd']").focus();
					return false;
				} else if (!reg3.test(newpwd)) {
					swal({
						title : "密码必须含有字母",
						confirmButtonText : "确定",
					});
					$("input[name='newpwd']").focus();
					return false;
				} else if (!reg4.test(newpwd)) {
					swal({
						title : "密码必须需含有数字",
						confirmButtonText : "确定",
					});
					$("input[name='newpwd']").focus();
					return false;
				}
			} else {
				$("input[name='newpwd']").focus();
				return false;
			}
			if (!checkStr(okpwd)) {
				if (okpwd != newpwd) {
					swal({
						title : "两次密码输入不一致",
						confirmButtonText : "确定",
					});
					$("input[name='okpwd']").focus();
					return false;
				}

			} else {
				$("input[name='okpwd']").focus();
				return false;
			}
			swal(
					{
						title : "是否修改",
						showCancelButton : true,
						confirmButtonText : "确定",
						cancelButtonText : "取消",
						closeOnConfirm : false,
						closeOnCancel : true
					},
					function(isConfirm) {
						if (isConfirm) {
							$
									.ajax({
										url : "${pageContext.request.contextPath}/user/checkpwd",
										type : "POST",
										dataType : "json",
										data : {
											"pwd" : oldpwd
										},
										success : function(result) {
											if (result.success) {
												$
														.ajax({
															url : "${pageContext.request.contextPath}/user/editpwd",
															type : "POST",
															dataType : "json",
															data : {
																"pwd" : okpwd
															},
															success : function(
																	result) {
																if (result.success) {
																	swal(
																			{
																				title : "修改成功",
																				confirmButtonText : "确定"
																			},
																			function() {
																				location.href = "${pageContext.request.contextPath}/syslogout";
																			});
																} else {
																	swal({
																		title : "修改失败",
																		confirmButtonText : "确定"
																	});
																	return false;
																}
															},
															error : function() {
																alert("修改失败");
															}
														});
											} else {
												swal({
													title : "旧密码不正确,请重新输入",
													confirmButtonText : "确定"
												});
												return false;
											}
										},
										error : function() {
											alert("修改失败");
										}
									});
						}
					});
		}
	</script>
</body>
</html>