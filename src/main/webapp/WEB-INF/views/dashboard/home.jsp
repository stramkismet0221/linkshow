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
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>售货机后台管理系统</title>
</head>

<body class="fix-sidebar">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
        </svg>
    </div>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <%@include file="/WEB-INF/views/include/header.jsp"%>
        </nav>
        <div class="navbar-default sidebar" role="navigation">
            <jsp:include page="/WEB-INF/views/include/left.jsp">
                <jsp:param value="/dashboard/home" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">概况</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">工作台</a></li>
                            <li class="active">概况</li>
                        </ol>
                    </div>
                    <%--<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">--%>
                        <%--<a href="javascript:void(0)" target="_blank" class="&lt;%&ndash;btn btn-danger&ndash;%&gt; pull-right m-l-20 hidden-xs hidden-sm waves-effect waves-light">查看更多数据</a>--%>
                    <%--</div>--%>
                </div>
                <%-- 今日经营情况 --%>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="row row-in" id="todayStatisticsPage"></div>
                        </div>
                    </div>
                </div>
                <%-- 经营分析 --%>
                <div class="row">
                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">经营分析</h3>
                            <div class="stats-row col-md-8" id="totalAnalysis">

                            </div>
                            <div class="col-md-3">
                                <div class="example">
                                    <input class="form-control input-daterange-datepicker1" type="text" id="daterange" name="daterange" value="" />
                                </div>
                            </div>
                            <div class="col-md-1">
                                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                            </div>
                            <ul class="list-inline text-right">
                                &nbsp;
                            </ul>
                            <div col-md-12 col-lg-12 col-sm-12 col-xs-12 id="dayAnalysis">

                            </div>
                        </div>
                    </div>
                </div>
                <%-- 产品销量排行 --%>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <h3 class="box-title">产品销量排行</h3>
                            <div class="row">
                                <div class="col-md-8">
                                </div>
                                <div class="col-md-3">
                                    <div class="example">
                                        <input class="form-control input-daterange-datepicker" type="text" id="prodaterange" name="daterange" value="" />
                                    </div>
                                </div>
                                <div class="col-md-1">
                                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="prodSearch();"/>
                                </div>
                            </div>
                            <div class="panel" id="prodAnalysis" style="margin-bottom: -25px">

                            </div>
                        </div>
                    </div>
                </div>
                <%-- 设备分析 --%>
                <div class="row">
                    <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                        <div class="white-box">
                            <h3 class="box-title">设备分析</h3>
                            <div class="row" id="termAvgAnalysis">
                            </div>
                            <div class="panel" id="termAnalysis">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right-sidebar">
                <%@ include file="/WEB-INF/views/include/right.jsp" %>
            </div>
        </div>
        <footer class="footer text-center">
            <%@ include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
</body>

<script type="text/javascript">
    // 初始化产品排行榜栏默认时间
    $(function(){
        // 获取当前时间，一个月前时间
        var proStartTime = getDay(-30);
        var proEndTime = getDay(0);
        $("#prodaterange").val(proStartTime + " - " + proEndTime);
    });

    // 初始化经营分析时间选择器
    $(function(){
        // 获取当前时间，一周前时间
        var proStartTime = getDay(-7);
        var proEndTime = getDay(0);
        $("#daterange").val(proStartTime + " - " + proEndTime);
    });

    function getDay(day){
        var today = new Date();
        var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;
        today.setTime(targetday_milliseconds); //注意，这行是关键代码
        var tYear = today.getFullYear();
        var tMonth = today.getMonth();
        var tDate = today.getDate();
        tMonth = doHandleMonth(tMonth + 1);
        tDate = doHandleMonth(tDate);
        return tYear+"-"+tMonth+"-"+tDate;
    }

    function doHandleMonth(month){
        var m = month;
        if(month.toString().length == 1){
            m = "0" + month;
        }
        return m;
    }

    // 初始化各项统计页
    $(function(){
        todayStatisticsPage();
        totalAnalysisPage();
        dayAnalysisPage();
        prodAnalysisPage();
        termAvgAnalysisPage();
        termAnalysisPage();
    });

    // 加载今日经营情况统计页面
    function todayStatisticsPage() {
        var param = {oId : ""};
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/dashboard/todaystat",
            dataType: "html",
            data: param,
            success: function (result) {
                $("#todayStatisticsPage").html(result);
            }
        });
    }

    // 加载某个事件段销售额等信息
    function totalAnalysisPage() {
        var dateRange = $("#daterange").val();
        var startTime = dateRange.split(" - ")[0];
        var endTime = dateRange.split(" - ")[1];
        var param = {oId : "", startTime: startTime, endTime: endTime};
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/dashboard/totalanalysis",
            dataType: "html",
            data: param,
            success: function (result) {
                $("#totalAnalysis").html(result);
            }
        });
    }
    // 某段时间内Chartist图形
    function dayAnalysisPage() {
        var dateRange = $("#daterange").val();
        var startTime = dateRange.split(" - ")[0];
        var endTime = dateRange.split(" - ")[1];
        var param = {oId : "", startTime: startTime, endTime: endTime};
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/dashboard/dayanalysis",
            dataType: "html",
            data: param,
            success: function (result) {
                $("#dayAnalysis").html(result);
            }
        });
    }
    // 查询
    function search() {
        totalAnalysisPage();
        dayAnalysisPage();
    }

    // 加载产品销量排行页面
    function prodAnalysisPage() {
        var dateRange = $("#prodaterange").val();
        var startTime = dateRange.split(" - ")[0];
        var endTime = dateRange.split(" - ")[1];
        var param = {oId : "", startTime : startTime, endTime : endTime, totalPage: 1, page: 1};
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/dashboard/prodanalysis",
            dataType: "html",
            data: param,
            success: function (result) {
                $("#prodAnalysis").html(result);
            }
        });
    }
    // 查询产品销售排行页面
    function prodSearch() {
        prodAnalysisPage();
    }

    // 加载日设备平均销售情况
    function termAvgAnalysisPage() {
        var param = {oId : ""};
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/dashboard/termavganalysis",
            dataType: "html",
            data: param,
            success: function (result) {
                $("#termAvgAnalysis").html(result);
            }
        });
    }

    // 加载设备分析列表
    function termAnalysisPage() {
        var param = {oId : ""};
        // termAnalysis
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/dashboard/termanalysis",
            dataType: "html",
            data: param,
            success: function (result) {
                $("#termAnalysis").html(result);
            }
        });
    }

</script>
<script>
    // Clock pickers
    $('#single-input').clockpicker({
        placement: 'bottom',
        align: 'left',
        autoclose: true,
        'default': 'now'
    });
    $('.clockpicker').clockpicker({
        donetext: 'Done',
    }).find('input').change(function() {
        console.log(this.value);
    });
    $('#check-minutes').click(function(e) {
        // Have to stop propagation here
        e.stopPropagation();
        input.clockpicker('show').clockpicker('toggleView', 'minutes');
    });
    if (/mobile/i.test(navigator.userAgent)) {
        $('input').prop('readOnly', true);
    }
    // Colorpicker
    $(".colorpicker").asColorPicker();
    $(".complex-colorpicker").asColorPicker({
        mode: 'complex'
    });
    $(".gradient-colorpicker").asColorPicker({
        mode: 'gradient'
    });
    // Date Picker
    jQuery('.mydatepicker, #datepicker').datepicker();
    jQuery('#datepicker-autoclose').datepicker({
        autoclose: true,
        todayHighlight: true
    });
    jQuery('#date-range').datepicker({
        toggleActive: true
    });
    jQuery('#datepicker-inline').datepicker({
        todayHighlight: true
    });
    // Daterange picker
    $('.input-daterange-datepicker').daterangepicker({
        startDate: moment().subtract(1, 'month').subtract(-1, 'days'),
        endDate: moment(),
        maxDate: moment(), //最大时间
        opens: 'left',
        showDropdowns: true,
        buttonClasses: ['btn', 'btn-sm'],
        applyClass: 'btn-danger',
        cancelClass: 'btn-inverse',
        locale: {
            format: 'YYYY-MM-DD',
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '手动选择',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                '七月', '八月', '九月', '十月', '十一月', '十二月'
            ],
            firstDay: 1
        }
    });
    $('.input-daterange-datepicker1').daterangepicker({
        startDate: moment().subtract(7, 'days'),
        endDate: moment(),
        maxDate: moment(), //最大时间
        opens: 'left',
        showDropdowns: true,
        buttonClasses: ['btn', 'btn-sm'],
        applyClass: 'btn-danger',
        cancelClass: 'btn-inverse',
        locale: {
            format: 'YYYY-MM-DD',
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '手动选择',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                '七月', '八月', '九月', '十月', '十一月', '十二月'
            ],
            firstDay: 1
        }
    });
    $('.input-daterange-timepicker').daterangepicker({
        timePicker: true,
        format: 'MM/DD/YYYY h:mm A',
        timePickerIncrement: 30,
        timePicker12Hour: true,
        timePickerSeconds: false,
        buttonClasses: ['btn', 'btn-sm'],
        applyClass: 'btn-danger',
        cancelClass: 'btn-inverse'
    });
    $('.input-limit-datepicker').daterangepicker({
        format: 'MM/DD/YYYY',
        minDate: '06/01/2015',
        maxDate: '06/30/2015',
        buttonClasses: ['btn', 'btn-sm'],
        applyClass: 'btn-danger',
        cancelClass: 'btn-inverse',
        dateLimit: {
            days: 6
        }
    });
</script>
</html>