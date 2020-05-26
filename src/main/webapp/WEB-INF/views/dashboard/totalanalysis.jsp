<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<div class="stat-item">
    <h6>交易金额 | 交易笔数</h6> <b>￥${busAnalysis.transAmount==null?0:busAnalysis.transAmount} | ${busAnalysis.transNum==null?0:busAnalysis.transNum}笔</b></div>
<div class="stat-item">
    <h6>退款金额 | 退款笔数</h6> <b>￥${busAnalysis.refundAmount==null?0:busAnalysis.refundAmount} | ${busAnalysis.refundNum==null?0:busAnalysis.refundNum}笔</b></div>
<div class="stat-item">
    <h6>成交额</h6> <b>￥${busAnalysis.turnoverAmount==null?0:busAnalysis.turnoverAmount}</b></div>
<div class="stat-item">
    <h6>毛利</h6> <b>￥${busAnalysis.grossProfit==null?0:busAnalysis.grossProfit}</b></div>