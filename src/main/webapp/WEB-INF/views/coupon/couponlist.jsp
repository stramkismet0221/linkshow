<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>抵扣券</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
            <jsp:param value="/coupon/getcouponlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">抵扣券</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">营销活动</a></li>
                        <li class="active">抵扣券</li>
                    </ol>
                </div>
            </div>

            <ul id="myTab" class="nav nav-tabs">

                <li id="activityTab" class=" <c:if test="${type == 1}"> active </c:if>">
                    <a href="#section-flip-1" onclick="loadActivities(1, '');" class="nav-list" style="width: 100px" data-toggle="tab"><span>抵扣列表</span></a>
                </li>
                <li id="historyTab" class=" <c:if test="${type == 2}"> active </c:if>">
                    <a href="#section-flip-2" onclick="loadActivities(2, '');" class="nav-list" style="width: 100px" data-toggle="tab"><span>历史活动</span></a>
                </li>
            </ul>
            <div id="myTabContent" class="tab-content">

                <c:if test="${type == 1}">
                    <div class="tab-pane fade in active"   id="section-flip-1">

                    </div>
                </c:if>
                <c:if test="${type == 2}">
                    <div class="tab-pane fade"   id="section-flip-1">

                    </div>
                </c:if>

                <c:if test="${type == 1}">
                    <div class="tab-pane fade"   id="section-flip-2">

                    </div>
                </c:if>
                <c:if test="${type == 2}">
                    <div class="tab-pane fade in active"   id="section-flip-2">

                    </div>
                </c:if>
            </div>
        </div>
        <footer class="footer text-center">
            <%@ include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    (function() {
        [].slice.call(document.querySelectorAll('.sttabs')).forEach(function(el) {
            new CBPFWTabs(el);
        });
    })();
</script>
<script type="text/javascript">
    // 初始化加载活动列表
    $(document).ready(function () {
        var type = ${type};
        if (type == 1) {
            $("activityTab").addClass("tab-current");
            $("historyTab").removeClass("tab-current");
        } else if (type == 2) {
            $("historyTab").addClass("tab-current");
            $("activityTab").removeClass("tab-current");
        }
        loadActivities(type, '');
    });
    // 加载活动列表 type=1：活动列表 2：历史活动列表
    function loadActivities(type, name) {
        console.log(type);
        var data = {type: type, name: name};
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/coupon/couponactlist",
            dataType: "html",
            data: data,
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 1, 400);
            },
            success: function (result) {
                if (type == 1) {
                    $("#section-flip-1").html(result);
                } else if (type == 2) {
                    $("#section-flip-2").html(result);
                }
                hide_loading(200);
            }
        });
    }

    function updateStatus(id, status) {
        var title = '';
        if (status == 0) {
            title = '删除';
        }
        if (status == 1) {
            title = '启用';
        }
        if (status == 2) {
            title = '暂停';
        }
        swal({
            title: "确定"+title+"吗?",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/coupon/updatestatus",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id,"status":status},
                    success : function(result) {
                        if (result.success) {
                            swal({title : title+"成功",
                                confirmButtonText: "确定"}, function(){
                                loadActivities(1, '');
                                <%--location.href = "${pageContext.request.contextPath}/coupon/getcouponlist";--%>
                            });
                        }
                    },
                    error : function() {
                        alert(title+"失败");
                    }
                });
            }
        });

    }
    // 删除
    function remove(id) {
        swal({
            title: "确定删除吗?",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/coupon/updatestatus",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id,"status":0},
                    success : function(result) {
                        if (result.success) {
                            swal({title : result.msg,
                                confirmButtonText: "确定"}, function(){
                                loadActivities(1, '');
                                <%--location.href = "${pageContext.request.contextPath}/coupon/getcouponlist";--%>
                            });
                        }
                    },
                    error : function() {
                        alert("删除失败");
                    }
                });
            }
        });
    }

    function lock(id,locked) {

        swal({
            title: "是否锁定/解锁用户?",
            showCancelButton: true,
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/coupon/updatestatus",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id,"locked":locked},
                    success : function(result) {
                        var title;
                        if (locked === 1){
                            title = "锁定成功";
                        }
                        if (locked === 0){
                            title = "解锁成功";
                        }
                        if (result.success) {
                            swal({title : title,
                                confirmButtonText: "确定"}, function(){
                                loadActivities(1, '');
                                // location.href = "${pageContext.request.contextPath}/user/getuserlist";
                            });
                        }
                    },
                    error : function() {
                        if (locked === 1){
                            alert("锁定失败");
                        }
                        if (locked === 0) {
                            alert("解锁失败");
                        }

                    }
                });
            }
        });
    }
</script>
