<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<div class="stats-row col-md-12 col-lg-12 col-sm-12 col-xs-12">
    <div class="stat-item">
        <h6>今日设备日均交易额</h6> <b>￥${termAvgAnalysis.todAvgTransAmount==null?0.0:termAvgAnalysis.todAvgTransAmount}</b></div>
    <div class="stat-item">
        <h6>昨日设备日均交易额</h6> <b>￥${termAvgAnalysis.yesAvgTransAmount==null?0.0:termAvgAnalysis.yesAvgTransAmount}</b></div>
</div>