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
    <title>设备账单</title>
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
                <jsp:param value="/order/getterminalorders" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">设备账单</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">订单中心</a></li>
                            <li class="active">设备账单</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-md-4" >
                                <h5 class="m-t-30 m-b-10">设备编号</h5>
                                <input type="text" class="form-control" id="terminalId" maxlength="50" placeholder="请输入设备编号" value="${orderTerminal.tId}"/>
                            </div>
                            <div class="col-md-4" >
                                <h5 class="m-t-30 m-b-10">设备名称</h5>
                                <input type="text" class="form-control" id="terminalName" maxlength="50" placeholder="请输入设备编号" value="${orderTerminal.tName}"/>
                            </div>
                            <div class="col-md-1" >
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/order/getplaceorders" id="form">
                    <input type="hidden" name="tId" value=""/>
                    <input type="hidden" name="tName" value=""/>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="white-box">
                                <div class="panel">
                                    <div class="table-responsive">
                                        <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                            <thead>
                                                <tr>
                                                    <th>设备编码</th>
                                                    <th>设备名称</th>
                                                    <th>总销售额</th>
                                                    <th>总成本</th>
                                                    <th>总利润</th>
                                                    <th>已结算</th>
                                                    <th>未结算</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${orderTerminalList}" var="data">
                                                <tr>
                                                    <td>${data.tId}</td>
                                                    <td>${data.tName}</td>
                                                    <td>${data.sales}</td>
                                                    <td>${data.originalCost}</td>
                                                    <td>${data.profit}</td>
                                                    <td>${data.settled}</td>
                                                    <td>${data.profit-data.settled}</td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td colspan="7">
                                                        <div class="text-right">
                                                            <%@ include file="/WEB-INF/views/include/pagination.jsp" %>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="right-sidebar">
                    <%@include file="/WEB-INF/views/include/right.jsp"%>
                </div>
            </div>
            <footer class="footer text-center">
                <%@include file="/WEB-INF/views/include/footer.jsp"%>
            </footer>
        </div>
    </div>
</body>
<script type="text/javascript">
    function search() {
        var terminalId = $("#terminalId").val();
        var terminalName = $("#terminalName").val();
        $("input[name='tId']").val(terminalId);
        $("input[name='tName']").val(terminalName);
        $("#form").submit();
    }
</script>
</html>