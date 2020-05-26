<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<div class="table-responsive">
    <table class="table table-hover manage-u-table" style="white-space: nowrap">
        <thead>
            <tr>
                <th>设备名称</th>
                <th>今日交易额 / 毛利 / 订单量</th>
                <th>昨日交易额 / 毛利 / 订单量</th>
                <th>本月交易额 / 毛利 / 订单量</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${termAnalyses}" var="data">
            <tr>
                <td>${data.terminalName}</td>
                <td>￥${data.todTransAmount} / ￥${data.todGrossProfit} / ${data.todSalesVolume}笔</td>
                <td>￥${data.yesTransAmount} / ￥${data.yesGrossProfit} / ${data.yesSalesVolume}笔</td>
                <td>￥${data.monthTransAmount} / ￥${data.monthGrossProfit} / ${data.monthSalesVolume}笔</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
