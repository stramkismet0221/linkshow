<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ddzhuan.manage.tool.yopoint.YoPointApi" %>
<%@ page import="com.alibaba.fastjson.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.ddzhuan.manage.tool.HttpPostTool" %>
<%
String title = (String)request.getAttribute("title");
String price = (String)request.getAttribute("price");
String imgurl = (String)request.getAttribute("imgurl");
String receipt_no=(String)request.getAttribute("receipt_no");
String address=(String)request.getAttribute("address");
String return_url=(String)request.getAttribute("return_url");
String notify_url=(String)request.getAttribute("notify_url");
String sign=(String)request.getAttribute("sign");
Integer timestamp=(Integer)request.getAttribute("timestamp");   
%> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="referrer" content="no-referrer" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<link type="text/css" rel="stylesheet" href="../common/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="../common/css/jquery.toast.css">
<link rel="stylesheet" type="text/css" href="../common/css/gdetails/gdetails.css">
<script src="../common/js/jquery.min.js"  type="text/javascript" ></script>
<script>

function pay(receipt_no,return_url,notify_url,sign,timestamp){
	window.location.href="vendpay?receipt_no="+receipt_no+"&return_url="+return_url+"&notify_url="+notify_url+"&sign="+sign+"&timestamp="+timestamp+"";
}
</script>
</head>
<body>
<div class="cc"><span class="box"><img src="<%=imgurl%>" alt="" style="height: 70px;margin-top: 10px;" /></span><span class="name"><%=title%></span><br/><span  class="money">￥<%=price%>元</span></div>
<!-- <div style="text-align: center; margin-top: 30px; width: 100%;"><span class="s1">设备</span><span class="s2">上海市乐山路22号</span></div><br/> -->
<div style="text-align: center;margin-top: 30px;"><span class="s1">设备地点</span><span class="s2"><%=address%></span></div><br/>
<div style="text-align: center;margin-top: 30px;"><span class="s1">商家活动</span><span class="s2">暂无</span></div><br/>
<div class="last"><span class="sf">实付金额：</span><span class="sfm">￥<%=price%>元</span><img src="../common/images/gdetails/jiesuan.png" alt="" style=" height:55px;width:125px;float: right;" onclick="pay('<%=receipt_no %>','<%=return_url%>','<%=notify_url%>','<%=sign%>','<%=timestamp%>')"/></div>
</body>
</html>
