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
    <title>兑换码库管理</title>
</head>
<body class="fix-sidebar">
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <%@ include file="/WEB-INF/views/include/header.jsp" %>
    </nav>
    <div class="navbar-default sidebar" role="navigation">
        <jsp:include page="/WEB-INF/views/include/left.jsp">
            <jsp:param value="/excode/toexcodelist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">兑换码库</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">营销活动</a></li>
                        <li class="active">兑换码库</li>
                    </ol>
                </div>
            </div>

                <ul id="myTab" class="nav nav-tabs">
                    <li <c:if test="${type == 1}">class="active" </c:if>>
                        <a href="#excodelist" onclick="exCodeList()" class="nav-list" data-toggle="tab">
                            活动列表
                        </a>
                    </li>
                    <li <c:if test="${type == 4}">class="active" </c:if>>
                        <a href="#history" onclick="history()" data-toggle="tab">
                            历史活动</a>
                    </li>
                </ul>
                    <div id="myTabContent" class="tab-content">

                        <c:if test="${type == 1}">
                            <div class="tab-pane fade in active"   id="excodelist">

                            </div>
                        </c:if>
                        <c:if test="${type == 4}">
                            <div class="tab-pane fade"   id="excodelist">

                            </div>
                        </c:if>

                        <c:if test="${type == 1}">
                            <div class="tab-pane fade"   id="history">

                            </div>
                        </c:if>
                        <c:if test="${type == 4}">
                            <div class="tab-pane fade in active"   id="history">

                            </div>
                        </c:if>
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
<script>
    jQuery(document).ready(function() {
        $.ajax({
            type: "POST",
            async: false,
            url: '${pageContext.request.contextPath}/excode/getexcodelist',
            dataType: "html",
            data: {"type":${type}},
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 1, 200);
            },
            success: function (result) {
                if (${type == 1}){
                    $("#excodelist").html(result);
                }

                if (${type == 4}){
                    $("#history").html(result);
                }
                hide_loading(200);
            }
        })
    });


    function history() {
        $.ajax({
            type: "POST",
            async: false,
            url: '${pageContext.request.contextPath}/excode/getexcodelist',
            dataType: "html",
            data: {"type":4},
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 1, 400);
            },
            success: function (result) {
                $("#history").html(result);
                hide_loading(200);
            }
        })
    }


    function exCodeList() {
        $.ajax({
            type: "POST",
            async: false,
            url: '${pageContext.request.contextPath}/excode/getexcodelist',
            dataType: "html",
            data: {"type":1},
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 1, 400);
            },
            success: function (result) {
                $("#excodelist").html(result);
                hide_loading(200);
            }
        })
    }
</script>