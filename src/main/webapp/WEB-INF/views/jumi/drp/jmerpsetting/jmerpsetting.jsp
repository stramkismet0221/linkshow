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
    <title>进销存设置</title>
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
            <jsp:param value="/jumi/drp/erpsetting/toerpsetting" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper" >
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 进销存设置</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 进销存</a></li>
                        <li class="active"> 进销存设置</li>
                    </ol>
                </div>
            </div>
            <div class="row col-md-12">
                <div class="white-box p-l-20 p-r-20 col-md-12">
                    <div class="col-md-5"></div>
                    <div class="col-md-7">
                        <form action="${pageContext.request.contextPath}/jumi/drp/erpsetting/toerpsetting">
                            <div class="form-group" >
                                <label class="control-label">是否开启商品批次管理</label>
                                <div class="radio-list ">
                                    <label class="radio-inline p-0">
                                        <div class="radio radio-info">
                                            <input type="radio" name="pno" value="1" id="radio0" <c:if test="${erpset.batchmng==1}">checked=""</c:if>>
                                            <label for="radio0">开启 </label>
                                        </div>

                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio radio-info">
                                            <input type="radio" name="pno" value="0" id="radio1" <c:if test="${erpset.batchmng==0}">checked=""</c:if>>
                                            <label for="radio1"> 不开启 </label>
                                        </div>
                                    </label>

                                </div>

                            </div>
                            <div class="form-group" >
                                <label class="control-label">进销存生产日期、有效期是否必填</label>
                                <div class="radio-list ">
                                    <label class="radio-inline p-0">
                                        <div class="radio radio-info">
                                            <input type="radio" name="pdate" value="1" id="radio2" <c:if test="${erpset.fillValidityDay==1}">checked=""</c:if>>
                                            <label for="radio2"> 是 </label>
                                        </div>

                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio radio-info">
                                            <input type="radio" name="pdate" value="0" id="radio3" <c:if test="${erpset.fillValidityDay==0}">checked=""</c:if>>
                                            <label for="radio3"> 否 </label>
                                        </div>
                                    </label>

                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="control-label">进销存单据是否需要审核</label>
                                <div class="radio-list">
                                    <label class="radio-inline p-0">
                                        <div class="radio radio-info">
                                            <input type="radio" name="transaction" value="1" id="radio4" <c:if test="${erpset.needAudit==1}">checked=""</c:if>>
                                            <label for="radio4">开启 </label>
                                        </div>

                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio radio-info">
                                            <input type="radio" name="transaction" value="0" id="radio5" <c:if test="${erpset.needAudit==0}">checked=""</c:if>>
                                            <label for="radio5"> 不开启 </label>
                                        </div>
                                    </label>

                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="control-label">商品进价取值方式</label>
                                <div class="radio-list">
                                    <label class="radio-inline p-0">
                                        <div class="radio radio-info">
                                            <input type="radio" name="price" value="1" id="radio6" <c:if test="${erpset.priceValueType==1}">checked=""</c:if>>
                                            <label for="radio6">进销存加权算 </label>
                                        </div>

                                    </label>
                                    <label class="radio-inline">
                                        <div class="radio radio-info">
                                            <input type="radio" name="price" value="0" id="radio7" <c:if test="${erpset.priceValueType==0}">checked=""</c:if>>
                                            <label for="radio7">修改商品时修改</label>
                                        </div>
                                    </label>

                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>


        // $(document).ready(function () {
        //     $("[name]").change(
        //     function () {
        //         var pnoval = $("[name=pno]").attr("checked");
        //         var pdate = $("[name=pdate]").attr("checked");
        //         var transaction = $("[name=transaction]").attr("checked");
        //         var price = $("[name=price]").attr("checked");
        //         console.log("我点击了" + $(this).attr("name"));
        //         swal({
        //             title: "数量小数位位数应该在0至4位之间",
        //             confirmButtonText: "确定",
        //             showCancelButton: true,
        //             cancelButtonText: "取消",
        //         }, function (isConfirm) {
        //             if (isConfirm) {
        //                 if (name == "pno") pnoval = $("[name=pno]").attr("checked");
        //                 else if (name == "pdate") pdate = $("[name=pdate]").attr("checked");
        //                 else if (name == "transaction") transaction = $("[name=transaction]").attr("checked");
        //                 else if (name == "price") price = $("[name=price]").attr("checked");
        //
        //                 console.log('pno' + pnoval + 'pdate' + pdate);
        //             } else {
        //                 window.location.reload()
        //             }
        //         });
        //     }
        // );
        // });






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
                    	//alert("成功");
                    	/*
                        swal({title : "保存成功",
                            confirmButtonText: "确定"}, function(){
                            location.href = "${pageContext.request.contextPath}/jumi/drp/erpsetting/toerpsetting";
                        });
                    	*/
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
            $("[name=pdate]").change(
                function () {
                    var title = "您确认要开启进销存生产日期、有效期是否必填";
                    var newVal = $(this).val();
                    var oldvalue = "0";
                    if (newVal == "0") {
                        title = "您确定要关闭进销存生产日期、有效期是否必填";
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
                        if (isConfirm) {
                            updateInfo({"fillValidityDay": newVal});
                        } else {
                            window.location.reload();
                        }

                    });
                }
            );
            $("[name=transaction]").change(
                function () {
                    var title = "您确认要开启进销存单据是否需要审核";
                    var newVal = $(this).val();
                    var oldvalue = "0";
                    if (newVal == "0") {
                        title = "您确定要关闭进销存单据是否需要审核";
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
                        if (isConfirm) {
                            updateInfo({"needAudit": newVal});
                        } else {
                            window.location.reload();
                        }

                    });
                }
            );
            $("[name=price]").change(
                function () {
                    var title = "确定要修改商品进价取值方式吗?";
                    var newVal = $(this).val();
                    var oldvalue = "0";
                    if (newVal == "0") {
                        title = "确定要修改商品进价取值方式吗?";
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
                        if (isConfirm) {
                            updateInfo({"priceValueType": newVal});
                        } else {
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
