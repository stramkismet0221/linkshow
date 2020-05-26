<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ddzhuan.manage.tool.yopoint.YoPointApi" %>
<%@ page import="com.alibaba.fastjson.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ddzhuan.manage.tool.HttpPostTool" %>
<%
	Map<String, String> payData = (Map<String, String>)request.getAttribute("paydata");
	String appid = payData.get("appId");
	String timeStamp = payData.get("timeStamp");
	String nonceStr = payData.get("nonceStr");
	String prepayId = payData.get("package");
	String signType = payData.get("signType");
	String sign = payData.get("sign");
	
	String ptitle = (String)request.getAttribute("title");
	String pprice = (String)request.getAttribute("price");
	String returnUrl = (String)request.getAttribute("returnurl");
	String cancelUrl = (String)request.getAttribute("cancelurl");
	String receipt_no=(String)request.getAttribute("receipt_no");
	String imgurl = (String)request.getAttribute("imgurl");
	String address=(String)request.getAttribute("address");
	cancelUrl=cancelUrl+"?receipt_no="+receipt_no;
	
%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta name="referrer" content="no-referrer" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="../common/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="../common/css/jquery.toast.css">
<link rel="stylesheet" type="text/css" href="../common/css/gdetails/cancel.css">
<script src="../common/js/jquery.min.js"  type="text/javascript" ></script>
<head>
<script>

function onBridgeReady(){
	   WeixinJSBridge.invoke(
	      "getBrandWCPayRequest", {
	         "appId":"<%=appid%>",     //公众号名称，由商户传入     
	         "timeStamp":"<%=timeStamp%>",         //时间戳，自1970年以来的秒数     
	         "nonceStr":"<%=nonceStr%>", //随机串     
	         "package":"<%=prepayId%>",     
	         "signType":"<%=signType%>",         //微信签名方式：     
	         "paySign":"<%=sign%>" //微信签名 
	      },
	      function(res){
	    	 // alert(res.err_msg);
	    	  //alert(JSON.stringify(res));
	      if(res.err_msg == "get_brand_wcpay_request:ok" ){
	      // 使用以上方式判断前端返回,微信团队郑重提示：
	            //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
	    		window.location.href="<%=returnUrl%>";
	      }  if(res.err_msg == "get_brand_wcpay_request:cancel" ){
	    	  window.location.href="<%=cancelUrl%>";
	      } if(res.err_msg == "get_brand_wcpay_request:fail" ){
	    	  //alert("交易失败")
	    	  window.location.href="<%=cancelUrl%>";
	      }
	   }); 
	}
	if (typeof WeixinJSBridge == "undefined"){
	   if( document.addEventListener ){
	       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	   }else if (document.attachEvent){
	       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	   }
	}else{
	   onBridgeReady();
	}
</script>
</head>
<body>
<div class="cc"><span class="box"><img src="<%=imgurl%>" alt="" style="height: 70px;margin-top: 10px;" /></span><span class="name"><%=ptitle%></span><br/><span  class="money">￥<%=pprice%>元</span></div>
<div style="text-align: center;margin-top: 30px;"><span class="s1">设备地点</span><span class="s2"><%=address%></span></div><br/>
<div style="text-align: center;margin-top: 30px;"><span class="s1">商家活动</span><span class="s2">暂无</span></div><br/>
</body>
</html>
