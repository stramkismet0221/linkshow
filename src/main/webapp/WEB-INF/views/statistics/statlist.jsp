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
    <title>统计列表</title>
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
                <jsp:param value="/statistics/getstatlist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">统计列表</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">数据中心</a></li>
                            <li class="active">统计列表</li>
                        </ol>
                    </div>
                </div>

                <ul id="statTab" class="nav nav-tabs">
                    <c:forEach items="${statistics}" var="data" varStatus="idxData">
                        <c:choose>
                            <c:when test="${idxData.index==0}">
                                <li class="active">
                                    <a href="#stat${idxData.index}" onclick="showIframe('${idxData.index}', '${data.url}');" class="nav-list" data-toggle="tab">${data.name}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                     <a href="#stat${idxData.index}" onclick="showIframe('${idxData.index}', '${data.url}');" data-toggle="tab">${data.name}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>

                <div class="tab-content" id="iframe">
                    <div class="tab-pane fade in active" id="stat0">
                        <div class="row">
                            <div class="col-sm-12">
                                <iframe id="statframe0" width="100%" height="860" frameborder="0" scrolling="auto"
                                        src="http://datav.aliyuncs.com/share/c0bb94cbc51b84a2cddba0de68c74ee9"></iframe>
                            </div>
                        </div>
                    </div>
                </div>

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
    // http://datav.aliyuncs.com/share/c0bb94cbc51b84a2cddba0de68c74ee9
    // 展示iframe
    function showIframe(index, url) {
        var innerIframe = '<div class="tab-pane fade in active" id="stat'+index+'">\n' +
            '                        <div class="row">\n' +
            '                            <div class="col-sm-12">\n' +
            '                                <iframe id="statframe'+index+'" width="100%" height="700" frameborder="0" scrolling="auto"\n' +
            '                                        src="'+url+'"></iframe>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </div>';
        $("#iframe").html(innerIframe);
    }
</script>

</html>