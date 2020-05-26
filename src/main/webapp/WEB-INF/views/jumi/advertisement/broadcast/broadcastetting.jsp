<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="../../../common/adm/plugins/images/favicon.png">
    <title>广告</title>
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
            <jsp:param value="/jumi/advertisement/broadcast/tobroadcast" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper" >
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 播控设置</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 广告</a></li>
                        <li class="active"> 播控设置</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator">
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;播放或展示中途被打断后</label>
                                        <div class="radio radio-info" style="display: inline-block; margin-left: 15px; margin-right: 5%;">
                                            <input type="hidden" name="id" value="${dict.id}" />
                                            <input type="radio" name="value" id="radio1"   value="1" <c:if test="${dict.value==1}">checked=""</c:if> >
                                            <label for="radio1"> 播放下一个广告内容 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="value" id="radio2" value="2" <c:if test="${dict.value==2}">checked=""</c:if> >
                                            <label for="radio2"> 继续播放当前广告内容 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="value" id="radio3" value="3" <c:if test="${dict.value==3}">checked=""</c:if> >
                                            <label for="radio3"> 回到第一个广告开始播放 </label>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row" >
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="submit"  >
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        function save() {
            // 校验
            //var name = $("input[name='broadcast']").val();
            swal({
                title: "是否保存",
                showCancelButton: true,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: true
            }, function (isConfirm) {
                if (isConfirm) {
                    // ajax提交
                    $.ajax({
                        url: "${pageContext.request.contextPath}/jumi/advertisement/broadcast/editbroadcast",
                        type: "POST",
                        dataType: "json",
                        async: false,
                        data: $("#form").serialize(),
                        success: function (result) {
                            console.log(result);
                            if (result.success) {
                                swal({
                                    title: "保存成功",
                                    confirmButtonText: "确定"
                                }, function (isConfirm) {
                                    if (isConfirm) {
                                        location.href = "${pageContext.request.contextPath}/jumi/advertisement/broadcast/tobroadcast";
                                    }
                                });
                            }
                        },
                        error: function () {
                            swal({
                                title: "保存失败",
                                cancelButtonText: "确定"
                            });

                        }
                    });
                }else{
                    location.href = "${pageContext.request.contextPath}/jumi/advertisement/broadcast/tobroadcast";
                }
            });
        }

















        var updateInfo = function(obj) {
            $.ajax({
                url: "${pageContext.request.contextPath}/jumi/drp/erpsetting/updatesetting",
                type: "POST",
                dataType: "json",
                async:false,
                data: obj,
                success: function (result) {
                    if (result.success) {
                    	 swal({
                             title: "保存成功",
                             confirmButtonText: "确定"
                         }, function () {

                         });
                    } else {
                        swal({
                            title: result.msg,
                            cancelButtonText: "确定"}, function(){
                            location.href = "${pageContext.request.contextPath}/jumi/drp/erpsetting/toerpsetting";
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "保存失败",
                        cancelButtonText: "确定"
                    });
                }
            });
        }

        $(document).ready(function () {
           $("[name=pno]").change(
               function () {
                   var title = "您确认要开启商品批次管理";
                   var newVal = $(this).val();
                   var oldvalue = "0";
                   if (newVal == "0") {
                       title = "您确定要关闭商品批次管理";
                       oldvalue = "1";
                   }
                   swal({
                       title: title,
                       confirmButtonText: "确定",
                       showCancelButton: true,
                       cancelButtonText: "取消",
                       closeOnConfirm: false,
                       closeOnCancel: true
                   }, function (isConfirm) {
                	   if(isConfirm){
                		   updateInfo({"batchmng": newVal});
                	   }else{
                		   window.location.reload();
                	   }
                	  
                   });
               }
           );

        });

    </script>
</div>
</body>
</html>
