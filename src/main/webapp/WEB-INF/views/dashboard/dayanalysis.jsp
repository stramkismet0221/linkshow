<%@ page import="com.ddzhuan.manage.model.dashboard.BusAnalysis" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<%
    List<BusAnalysis> busAnalyses = (List<BusAnalysis>) request.getAttribute("busAnalyses");
%>

<ul class="list-inline text-right">
    <li>
        <h5><i class="fa fa-circle m-r-5 text-danger"></i>交易额</h5>
    </li>
    <li>
        <h5><i class="fa fa-circle m-r-5 text-info"></i>订单数</h5>
    </li>
</ul>
<div id="ct-visits" style="height: 285px;"></div>

<script type="text/javascript">

    $(document).ready(function () {
        var tip;
        new Chartist.Line('#ct-visits', {
            labels: [
                <% for (BusAnalysis busAnalysis : busAnalyses) {%>
                    <%out.print("'"+busAnalysis.getDayStr().split("-")[1]+"月"+busAnalysis.getDayStr().split("-")[2]+"日'");%>,
                <%}%>
//                '02', '03', '04', '05', '06', '07', '08', '09'
            ],
            series: [
                {
                    name: '交易额（元）',
                    data: [
                        <% for (BusAnalysis busAnalysis : busAnalyses) {%>
                            <%out.print(busAnalysis.getTransAmount());%>,
                        <%}%>
//                         5, 2, 7, 4, 5, 3, 5, 4
                    ],
                },
                {
                    name: '订单数（笔）',
                    data: [
                        <% for (BusAnalysis busAnalysis : busAnalyses) {%>
                            <%out.print(busAnalysis.getTransNum());%>,
                        <%}%>
                        // 2, 5, 2, 6, 2, 5, 2, 4
                    ]
                }
            ]
        }, {
            top: 0,
            low: 0,
            showPoint: true,
            chartPadding: {
                right: 40
            },
            fullWidth: true,

            plugins: [
                Chartist.plugins.tooltip({
                    tooltipFnc:function(index){
                        return (index+'<br/>'+tip);
                    },
                    transformTooltipTextFnc:function(e){
                        tip = e;
                        return tip;
                    },
                    anchorToPoint: false
                }),
            ],
            axisY: {
                labelInterpolationFnc: function (value) {
                    return (value / 1) + '';
                }
            },
            axisX: {
//                labelOffset: { x: -30, y: 0 },
                labelInterpolationFnc: function (value, index) {
                    return value;/*(value / 1) + '';*/
                },

            },
            showArea: true,
        });
    })
</script>
