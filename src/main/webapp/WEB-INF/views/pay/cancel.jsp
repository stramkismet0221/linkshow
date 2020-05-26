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
String msg = request.getAttribute("msg") == null ? "订单已取消" : (String)request.getAttribute("msg");
String context = request.getContextPath();
%> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="referrer" content="no-referrer" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<link type="text/css" rel="stylesheet" href="<%=context %>/common/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=context %>/common/css/jquery.toast.css">
<link rel="stylesheet" type="text/css" href="<%=context %>/common/css/gdetails/cancel.css">
<script src="<%=context %>/common/js/jquery.min.js"  type="text/javascript" ></script>
</head>
<body>
<div class="cc"><span class="box"><img src="<%=imgurl%>" alt="" style="height: 70px;margin-top: 10px;" /></span><span class="name"><%=title%></span><br/><span  class="money">￥<%=price%>元</span></div>
<div style="text-align: center;margin-top: 30px;"><span class="s1">设备地点</span><span class="s2"><%=address%></span></div><br/>
<div style="text-align: center;margin-top: 30px;"><span class="s1">商家活动</span><span class="s2">暂无</span></div><br/>
<div class="last"><%=msg %></div>
</body>
</html>
