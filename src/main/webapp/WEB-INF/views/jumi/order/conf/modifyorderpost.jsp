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
    <title>订单信息</title>
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
            <jsp:param value="/order/getorderlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">订单信息</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">订单中心</a></li>
                        <li class="active">订单信息</li>
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
                                <form id="form" action="${pageContext.request.contextPath}/jumi/order/orderpost/updateorderpost" onsubmit="update();return false;" class="form-material form-horizontal" data-toggle="validator">
                                    <div class="form-group">
                                        <label class="col-md-12">ID</label>
                                        <div class="col-md-12">
                                            <input type="text" name="id" class="form-control" value="${orderPost.id}" readonly />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;配送方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="postType" class="form-control" value="${orderPost.postType}" placeholder="请输入配送方式" data-error="请输入配送方式" required="required" maxlength="50"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;开通状态</label>
                                        <div class="col-md-12">
                                            <select class="form-control" name="openStatus">
                                                <option value="1" <c:if test="${orderPost.openStatus == 1}"> selected </c:if>>已开通</option>
                                                <option value="0" <c:if test="${orderPost.openStatus == 0}"> selected </c:if>>未开通</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;是否默认</label>
                                        <div class="col-md-12">
                                            <select class="form-control" name="deafStatus" >
                                                <option value="1" <c:if test="${orderPost.deafStatus == 1}"> selected </c:if>>是</option>
                                                <option value="0" <c:if test="${orderPost.deafStatus == 0}"> selected </c:if>>否</option>
                                            </select>
                                        </div>
                                    </div>
                                    <c:if test="${orderPost.id != 2}">
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;配送价格</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" maxlength="10" name="postPrice" value="${orderPost.postPrice}" placeholder="请输入配送价格" data-error="请输入配送价格" required="required" onkeyup="btnonkeyupDiscount(this,value)"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;配送范围</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" maxlength="10" name="postAround" value="${orderPost.postAround}"  placeholder="请输入配送范围" data-error="请输入配送范围" required="required" onkeyup="btnonkeyupDiscount(this,value)" />
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-12">序号</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" maxlength="10" name="sno" value="${orderPost.sno}"  placeholder="请输入序号" data-error="请输入序号" required="required" onkeyup="btnonkeyup(this,value)" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <c:if test="${orderPost.id != 2}">
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;是否包邮</label>
                                            <div class="col-md-12">
                                                <select class="form-control" name="freeShippingStatus" onchange="changeExchangeType(this.options[this.options.selectedIndex].value)" >
                                                    <option value="1" <c:if test="${orderPost.freeShippingStatus == 1}"> selected </c:if>>是</option>
                                                    <option value="0" <c:if test="${orderPost.freeShippingStatus == 0}"> selected </c:if>>否</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group" id="o1" <c:if test="${orderPost.freeShippingStatus == 0}"> style="display: none;" </c:if> >
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;订单满N元免费配送（包邮）</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" maxlength="10" name="enoughPrice" value="${orderPost.enoughPrice}"  placeholder="请输入满包邮费用" data-error="请输入满包邮费用" required="required" onkeyup="btnonkeyupDiscount(this,value)"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="submit" >
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()" type="button">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
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
    function update() {

        var postType = $("input[name='postType']").val();
        if (checkStr(postType)) {
            $("input[name='postType']").focus();
            return false;
        }

        if (${orderPost.id != 2}) {
            var postPrice = $("input[name='postPrice']").val();
            if (checkStr(postPrice)) {
                $("input[name='postPrice']").focus();
                return false;
            }

            var postAround = $("input[name='postAround']").val();
            if (checkStr(postAround)) {
                $("input[name='postType']").focus();
                return false;
            }

            var enoughPrice = $("input[name='enoughPrice']").val();
            if (checkStr(enoughPrice)) {
                $("input[name='enoughPrice']").focus();
                return false;
            }
        }

        swal({
            title: "是否保存",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    url:'${pageContext.request.contextPath}/jumi/order/orderpost/updateorderpost',
                    type:'post',
                    dataType:'json',
                    data:$("#form").serialize(),
                    success:function (data) {
                        if (data.success) {
                            swal({
                                title: data.msg,
                                confirmButtonText: "确定",
                            },function () {
                                location.href = '${pageContext.request.contextPath}/jumi/order/orderpost/getlist';
                            });
                        }else {
                            swal({
                                title: data.msg,
                                confirmButtonText: "确定",
                            });
                        }

                    },
                    error:function () {
                        swal({
                            title: "操作失败",
                            confirmButtonText: "确定",
                        });
                    }
                })
            }
        });
    }

    function changeExchangeType(val) {
        if (1 == val) {
            $("#o1").show();
        }
        if (0 == val) {
            $("#o1").hide();
        }
    }
</script>
</html>