<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%
    String msg = request.getAttribute("msg") == null ? "" : (String) request.getAttribute("msg");
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
    <link rel="icon" type="image/png" sizes="16x16" href="common/adm/plugins/images/favicon.png">
    <title>用户登录</title>
    <!-- Bootstrap Core CSS -->
    <link href="common/adm/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="common/adm/plugins/bower_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
    <!-- animation CSS -->
    <link href="common/adm/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="common/adm/css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="common/adm/css/colors/blue.css" id="theme"  rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery -->
    <script src="common/adm/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="common/adm/plugins/bower_components/sweetalert/sweetalert.min.js"></script>
    <script src="common/adm/plugins/bower_components/sweetalert/jquery.sweet-alert.custom.js"></script>
    <script type="text/javascript">
        <%if(!"".equals(msg)){%>
            alert('<%=msg%>');
            <%if (systemId.intValue() == 1) {%>
                location.href = '${pageContext.request.contextPath}/index';
            <%} else if (systemId.intValue() == 2) {%>
                location.href = '${pageContext.request.contextPath}/login';
            <%} else if (systemId.intValue() == 3) {%>
                location.href = '${pageContext.request.contextPath}/eclogin';
            <%} else if (systemId.intValue() == 4) {%>
                location.href = '${pageContext.request.contextPath}/jumilogin';
            <%}%>
        <%}%>
        
        function qifn(){
        	//验证图片验证码

        	var phone =$("input[name='mobile']").val();
        	//var imgText = document.getElementById("imgtext");
        	//var type=param==1?1:2;
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
        	 		url:"user/checkphone",
        	 		type:"post",
        	 		data:date,
        	 		success:function(data){
        	 			if(data.success){
        	 				$.ajax({
            					url:"t/phcode?mobiles="+mobile,
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
            							//		 alert(result.msg);
            							//$("#ckimgtext").text("请输入正确的图片验证码");
            						}
            					}
            				})
            				
        	 			}else{
        	 				swal({
        	                    title: "手机号码不存在",
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
        	
        	//获取短信验证码
         	var mobileCode = $("#phcode").val();
         	
         	if(mobileCode.length!=6){
         		swal({
                    title: "手机验证码格式不正确",
                    confirmButtonText: "确定",
                });
         		$("#phcode").focus();
         		return false;
         	}
         	 var num= /^\d*$/;
         	 
        	 if(!num.test(mobileCode)){
        		 swal({
                     title: "手机验证码格式不正确",
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
        	
        	
            if (!reg.test(pwd1) || !reg3.test(pwd1) || !reg4.test(pwd1) || pwd1.length>20 || pwd1.length<6) {
            	swal({
                    title: "密码6-20位，数字、字母、符号（必须包含数字、字母）",
                    confirmButtonText: "确定",
                });
            	$("input[name='password']").focus();
           		return false;
            }
            //获取确认密码
            var okpwd=$("#pwd2").val();
            if(okpwd!=pwd1){
            	swal({
                    title: "两次密码输入不一致",
                    confirmButtonText: "确定",
                });
            	$("input[name='password2']").focus();
           		return false;
            }
            
            $("#mobilecode").val(mobileCode);
        		var date=$("#recoverform").serialize();
        		$.ajax({
        	 		url:"user/forgetpwdedit",
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
        	                		 top.location = "${pageContext.request.contextPath}/index";
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
        
        function forgetpwd(){
        	location.href = '${pageContext.request.contextPath}/user/forgetpwd?systemId=<%=systemId.intValue() %>';
        }
        
    </script>
</head>
<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel">
    </div>
</div>
<section id="wrapper" class="login-register" style="background:url(common/adm/plugins/images/login-register.jpg) center center/cover no-repeat!important;height:100%;position:fixed">
    <div class="login-box login-sidebar" style="overflow:scroll;">
        <div class="white-box">
            <form class="form-horizontal form-material" id="loginForm" method="post" action="syslogin">
                <%--登录的系统--%>
                <input type="hidden" name="systemId" value="${systemId}" />
                <a href="javascript:void(0)" class="text-center db"><img src="common/adm/plugins/images/admin-logo-dark.png" alt="Home" /><br/><img src="common/adm/plugins/images/admin-text-dark.png" alt="Home" /></a>
                <div class="form-group m-t-40">
                    <div class="col-xs-12">
                        <input class="form-control" style="padding-left:10px" name="username" type="text" required="" placeholder="&nbsp;用户名">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <input class="form-control" style="padding-left:10px" name="pwd" type="password" required="" placeholder="&nbsp;密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <div class="checkbox checkbox-primary pull-left p-t-0">
                            <input id="checkbox-signup" type="checkbox">
                            <label for="checkbox-signup"> 记住账号 </label>
                        </div>
                        <a href="javascript:void(0)" onclick="forgetpwd()" id="to-recover"  class="text-dark pull-right">
                            <i class="fa fa-lock m-r-5" >
                            </i> 忘记密码?
                        </a>
                    </div>
                </div>
                <div class="form-group text-center m-t-20">
                    <div class="col-xs-12">
                        <input class="btn btn-info btn-primary btn-lg" style="width: 100%" type="submit" value="登 录"/>
                    </div>
                </div>
                <!-- 
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 m-t-10 text-center">
                        <div class="social">
                        <%--
                            <p> 没有账号? <a href="javascript:void(0)" class="text-primary m-l-5"><b>注册</b></a></p>
                            <a href="javascript:void(0)" class="btn  btn-facebook" data-toggle="tooltip"  title="Login with Facebook">--%>
                                <%--<i aria-hidden="true" class="fa fa-facebook">--%>
                                <%--</i>--%>
                            <%--</a>--%>
                            <%--<a href="javascript:void(0)" class="btn btn-googleplus" data-toggle="tooltip"  title="Login with Google">--%>
                                <%--<i aria-hidden="true" class="fa fa-google-plus">--%>
                                <%--</i>--%>
                            <%--</a>--%>
                        </div>
                    </div>
                </div>
                 -->
                <!--  
                <div class="form-group m-b-0">
                    <div class="col-sm-12 text-center">
                        <%--<p>Don't have an account? <a href="javascript:void(0)" class="text-primary m-l-5"><b>Sign Up</b></a></p>--%>
                    </div>
                </div>
                -->
            </form>
            <!-- 
            <form class="form-horizontal" id="recoverformz" >
                <div class="form-group ">
                    <div class="col-xs-12">
                        <h3>忘记密码</h3>
                    </div>
                </div>
                <div class="form-group ">
                    <div class="col-xs-12">
                    	 <label class="col-md-12" id="phone"><label style="color: #f05b4f">*</label>&nbsp;手机号</label>
                               <div class="col-md-12">
                                   <input type="text" name="mobile"  required="required"   maxlength="11" placeholder="请输入手机号" data-error="请填写手机号" class="form-control" />
                                   <!--<div class="help-block with-errors"></div>   
                               </div>
                    </div>
                </div>
                <div class="form-group ">
                    <div class="col-xs-12">
                    <div class="col-xs-6">
                     <button class="btn btn-block btn-info" id="buttonphoneqi" onmousedown="qifn()" type="button" >获取手机验证码</button>
                     <img id="imgcode"   style="width:100px;display:none; height:30px;margin-top:5px;margin-bottom:5px;text-align:center;" />
                    </div>
                    </div>
                </div>
                  <div class="form-group ">
                    <div class="col-xs-12">
                    	 <div class="col-md-12">
                                   <input type="text" name="phcode"  id="phcode" required="required"  maxlength="6" placeholder="请输入手机验证码" data-error="请填写手机验证码" class="form-control" />
                                   <input type="hidden" id="mobilecode" name="mobilecode"/>
                                   <div class="help-block with-errors"></div>
                               </div>
                    </div>
                </div>
                
                <div class="form-group ">
                    <div class="col-xs-12">
                    	 <label class="col-md-12" id="newpwd"><label style="color: #f05b4f">*</label>&nbsp;新密码</label>
                               <div class="col-md-12">
                                   <input type="password" name="password"  id="pwd1"  required="required"  maxlength="20" placeholder="请输入新密码" data-error="请填写新密码" class="form-control" />
                                   <div class="help-block with-errors"></div>
                               </div>
                    </div>
                </div>
                
                
                <div class="form-group ">
                    <div class="col-xs-12">
                    	 <label class="col-md-12" id="affirmpwd"><label style="color: #f05b4f">*</label>&nbsp;确认密码</label>
                               <div class="col-md-12">
                                   <input type="password" name="password2"  id="pwd2"  required="required"  maxlength="20" placeholder="请输入确认密码" data-error="请填写确认密码" class="form-control" />
                                   <div class="help-block with-errors"></div>
                               </div>
                    </div>
                </div>
                <div class="form-group text-center m-t-20">
                    <div class="col-xs-12">
                        <button class="btn btn-info btn-primary btn-lg" type="button" style="width: 100%" onmousedown="savepwd()">确定</button>
                    </div>
                </div>
            </form>
             -->
            
        </div>
    </div>
</section>

<!-- Bootstrap Core JavaScript -->
<script src="common/adm/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>

<!--slimscroll JavaScript -->
<script src="common/adm/js/jquery.slimscroll.js"></script>
<!--Wave Effects -->

<!-- Custom Theme JavaScript -->
<script src="common/adm/js/custom.min.js"></script>
<!--Style Switcher -->
<script src="common/adm/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
</body>
</html>
