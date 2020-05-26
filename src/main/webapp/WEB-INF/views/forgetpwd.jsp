<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%
    Long systemId = request.getAttribute("systemId") == null ? 0L : (Long) request.getAttribute("systemId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/common/adm/plugins/images/favicon.png">
    <title>忘记密码</title>
    <link href="${pageContext.request.contextPath}/common/adm/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/common/adm/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/common/adm/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/common/adm/css/colors/blue.css" id="theme"  rel="stylesheet">
    <script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sweetalert/jquery.sweet-alert.custom.js"></script>
</head>
<body class="fix-sidebar">
	<div id="wrapper">
		<div id="page-wrapper" style="margin-left: 0px;">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<form class="form-horizontal" id="recoverforms">
							<div class="form-group ">
								<div class="col-xs-12" align="center">
									<h3>忘记密码</h3>
								</div>
							</div>
							<div class="form-group ">
								<div class="col-xs-12">
									<label class="col-md-12" id="phone"><label
										style="color: #f05b4f">*</label>&nbsp;手机号</label>
									<div class="col-md-12">
										<input type="text" name="mobile" id="mobile"
											required="required" maxlength="11" placeholder="请输入手机号"
											data-error="请输入手机号" class="form-control" />
									</div>
								</div>
							</div>
							<div class="form-group ">
								<div class="col-xs-12">
									<div class="col-xs-8">
										<input type="text" name="phcode" id="phcode"
											required="required" maxlength="6" placeholder="请输入验证码"
											data-error="请输入验证码" class="form-control" /> <input
											type="hidden" id="mobilecode" name="mobilecode" />
									</div>
									<div class="col-xs-4">
										<button class="btn btn-block btn-info"
											id="buttonphoneqi" 
											onmousedown="qifn()" type="button">获取验证码</button>
										<img id="imgcode"
											style="width: 100px; display: none; height: 30px; margin-top: 5px; margin-bottom: 5px; text-align: center;" />
									</div>
								</div>
							</div>
							<div class="form-group ">
								<div class="col-xs-12">
									<label class="col-md-12" id="newpwd"><label
										style="color: #f05b4f">*</label>&nbsp;新密码</label>
									<div class="col-md-12">
										<input type="password" name="password" id="pwd1"
											required="required" maxlength="20" placeholder="请输入新密码"
											data-error="请输入新密码" class="form-control" />
										<div class="help-block with-errors"></div>
									</div>
								</div>
							</div>
							<div class="form-group ">
								<div class="col-xs-12">
									<label class="col-md-12" id="affirmpwd"><label
										style="color: #f05b4f">*</label>&nbsp;确认密码</label>
									<div class="col-md-12">
										<input type="password" name="password2" id="pwd2"
											required="required" maxlength="20" placeholder="请输入确认密码"
											data-error="请输入确认密码" class="form-control" />
										<div class="help-block with-errors"></div>
									</div>
								</div>
							</div>
							<div class="text-center">
								<div class="col-xs-5">
									<button class="btn btn-block btn-info form-control"
										type="button" style="width: 100%; color: #fff7f6;"
										onmousedown="savepwd()">确定</button>
								</div>
								
								<div class="col-xs-2"></div>
								<div class="col-xs-5">
									<button class="btn btn-block btn-info form-control"
										type="button" style="width: 100%; color: #fff7f6;"
										onmousedown="login()">返回登录</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="${pageContext.request.contextPath}/common/adm/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/jquery.slimscroll.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
<script type="text/javascript">
function qifn(){
	var phone =$("input[name='mobile']").val();
	var s=true;
	if(phone.length <1){
		swal({
            title: "请输入手机号",
            confirmButtonText: "确定",
        });
		$("input[name='mobile']").focus();
		s=false;
		return false;
	}
	if(phone.length >1 && phone.length!=11){
		swal({
            title: "请输入正确的手机号",
            confirmButtonText: "确定",
        });
		$("input[name='mobile']").focus();
		s=false;
		return false;
	}
	var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
 	
 	if(!myreg.test(phone)){
 		swal({
            title: "请输入正确的手机号",
            confirmButtonText: "确定",
        });
 		$("input[name='mobile']").focus();
 		s=false;
		return false;
 	}
 	if(s){
 		var x=true;
 		var mobile=phone;
 		var date="phone="+mobile;
	 	$.ajax({
	 		url:"${pageContext.request.contextPath}/user/checkphone",
	 		type:"post",
	 		data:date,
	 		success:function(data){
	 			if(data.success){
	 				$.ajax({
    					url:"${pageContext.request.contextPath}/t/phcode?mobiles="+mobile,
    					async:false,
    					dataType:"json",
    					type:"POST",
    					success:function(result){
    						if(result.success){
    							var oBtn = document.getElementById('buttonphoneqi');
    							var time = 90;
    							var timer = null;
    							oBtn.innerHTML = time + '秒后重新发送';
    							oBtn.setAttribute('disabled', 'disabled');  
    							timer = setInterval(function(){
    								if(time == 1){
    									clearInterval(timer);
    									oBtn.innerHTML = '再次获取验证码';
    									oBtn.removeAttribute('disabled');
    								}else{
    									time--;
    									oBtn.innerHTML = time + '秒后重新发送';
    								}
    							}, 1000)
    						}else{
    							swal({
    					            title: "获取手机验证码失败",
    					            confirmButtonText: "确定",
    					        });
    						}
    					}
    				})
	 			}else{
	 				swal({
	                    title: "手机号不存在",
	                    confirmButtonText: "确定",
	                });
	 			}
	 		}
	 	});
 	}
}       
function savepwd(){
	var mobile= $("#mobile").val();
	var date="mobile="+mobile;       
	if(mobile.length <1){
		swal({
            title: "请输入手机号",
            confirmButtonText: "确定",
        });
		$("input[name='mobile']").focus();
		s=false;
		return false;
	}
	if(mobile.length >1 && mobile.length!=11){
		swal({
            title: "请输入正确的手机号",
            confirmButtonText: "确定",
        });
		$("input[name='mobile']").focus();
		s=false;
		return false;
	}
	var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
 	if(!myreg.test(mobile)){
 		swal({
            title: "请输入正确的手机号",
            confirmButtonText: "确定",
        });
 		$("input[name='mobile']").focus();
 		s=false;
		return false;
 	}
 	var mobileCode = $("#phcode").val();
 	if(mobileCode.length<1){
 		swal({
            title: "请输入验证码",
            confirmButtonText: "确定",
        });
 		$("#phcode").focus();
 		return false;
 	}
 	if(mobileCode.length!=6){
 		swal({
            title: "请输入正确的验证码",
            confirmButtonText: "确定",
        });
 		$("#phcode").focus();
 		return false;
 	}
 	 var num= /^\d*$/;
	 if(!num.test(mobileCode)){
		 swal({
             title: "请输入正确的验证码",
             confirmButtonText: "确定",
         });
		 $("#phcode").focus();
	 	 return false;
	 }
	var pwd1=$("#pwd1").val();
	var s=true;
	var reg = /([a-zA-Z0-9`~!@#$%^&*()_\-+{}|:<>?\[\\\];,./｀～！＠＃＄％＾＆＊（）＿＋［］＼｛｝｜；＇：＂，。、《》？]){6,20}/;
	var reg3 = /[a-zA-Z]+/;
	var reg4 = /[0-9]+/;
	if(pwd1.length<1){
 		swal({
            title: "请输入新密码",
            confirmButtonText: "确定",
        });
 		$("input[name='password']").focus();
 		return false;
 	}
    if (!reg.test(pwd1) || !reg3.test(pwd1) || !reg4.test(pwd1) || pwd1.length>20 || pwd1.length<6) {
    	swal({
            title: "密码6-20位，数字、字母、符号（必须包含数字、字母）",
            confirmButtonText: "确定",
        });
    	$("input[name='password']").focus();
   		return false;
    }
    var okpwd=$("#pwd2").val();
    if(okpwd.length<1){
 		swal({
            title: "请输入确认密码",
            confirmButtonText: "确定",
        });
 		$("input[name='password2']").focus();
 		return false;
 	}
    if(okpwd!=pwd1){
    	swal({
            title: "两次密码输入不一致",
            confirmButtonText: "确定",
        });
    	$("input[name='password2']").focus();
   		return false;
    }
    $("#mobilecode").val(mobileCode);
		var date=$("#recoverforms").serialize();
		$.ajax({
	 		url:"${pageContext.request.contextPath}/user/forgetpwdedit",
			async:false,
	 		type:"post",
	 		data:date,
	 		dataType:'JSON',
	 		success:function(data){
	 			if(data.res >0){
	 				swal({
	                    title: "修改密码成功!",
	                    confirmButtonText: "确定",
	                },function(isConfirm){
	                	 if (isConfirm) {
	                		 <%if (systemId.intValue() == 1) {%>
	                		 top.location = '${pageContext.request.contextPath}/index';
	                	 <%} else if (systemId.intValue() == 2) {%>
	                	 top.location = '${pageContext.request.contextPath}/login';
	                	 <%} else if (systemId.intValue() == 3) {%>
	                	 top.location = '${pageContext.request.contextPath}/eclogin';
	                	 <%} else {%>
	                	 top.location = '${pageContext.request.contextPath}/index';
	                	 <%}%>
	                		 
	                     }
	                });
	 			}
				if (data.res === -2) {
					swal({
	                    title: data.msg,
	                    confirmButtonText: "确定",
	                });
					$("input[name='phcode']").focus();
					return false;
				}
	 		}
	 	});
}    
function login(){
	 <%if (systemId.intValue() == 1) {%>
     location.href = '${pageContext.request.contextPath}/index';
 <%} else if (systemId.intValue() == 2) {%>
     location.href = '${pageContext.request.contextPath}/login';
 <%} else if (systemId.intValue() == 3) {%>
     location.href = '${pageContext.request.contextPath}/eclogin';
 <%} else {%>
     location.href = '${pageContext.request.contextPath}/index';
 <%}%>
}
</script>
</body>
</html>