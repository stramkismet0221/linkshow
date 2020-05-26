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
    <link rel="icon" type="image/png" sizes="16x16" href="../../../common/adm/plugins/images/favicon.png">
    <title>新增商品单位</title>
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
            <jsp:param value="/jumi/drp/goodsunit/getlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 新增商品单位</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 商品管理</a></li>
                        <li class="active"> 新增商品单位</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" onsubmit="update();return false;" class="form-horizontal form-material" data-toggle="validator" novalidate="true">
                                    <div class="form-group">
                                        <label class="col-md-12" id="id"><label style="color: #f05b4f">*</label>&nbsp;单位ID</label>
                                        <div class="col-md-12">
                                            <input type="text"  class="form-control" value="${jmGoodsUnit.id}" disabled/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12" id="name"><label style="color: #f05b4f">*</label>&nbsp;单位名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="name" required="required" maxlength="50" placeholder="请输入单位名称" data-error="请输入单位名称" class="form-control" value="${jmGoodsUnit.name}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12" id="sno"><label style="color: #f05b4f">*</label>&nbsp;序号</label>
                                        <div class="col-md-12">
                                            <input type="text" name="sno" required="required" onkeyup="btnonkeyup(this,value)" maxlength="4" placeholder="请输入排序" data-error="请输入排序" class="form-control" value="${jmGoodsUnit.sno}"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <input type="hidden" name="id" class="form-control" value="${jmGoodsUnit.id}"/>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit" value="保存">
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="cancel()" type="button">
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

<script type="text/javascript">


    function update() {
        var name = $("input[name='name']").val();
        // 校验
        if (checkStr(name)) {
            $("input[name='name']").focus();
            return false;
        }
        var sno = $("input[name='sno']").val();
        if (checkStr(sno)) {
            $("input[name='sno']").focus();
            return false;
        }
        var id = $("input[name='id']").val();
        var date = {
            name:name,
            sno:sno,
            id:id
        };

        $.ajax({
            url: "${pageContext.request.contextPath}/jumi/drp/goodsunit/checkunitname",
            type: "POST",
            dataType: "json",
            async: true,
            data: {name:name,id:id},
            success: function (result) {
                if (result.success) {
                    swal({
                        title: "该单位名称已存在",
                        confirmButtonText: "确定"
                    }, function () {

                    });
                }
            },
            error: function () {
            	swal({
                    title: "网络异常",
                    cancelButtonText: "确定"
                });
               
            }
        });
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
                    url: "${pageContext.request.contextPath}/jumi/drp/goodsunit/updatejmgoodsunit",
                    type: "POST",
                    dataType: "json",
                    data: date,
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/drp/goodsunit/getlist";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                cancelButtonText: "确定"
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
        });
    }
    function cancel() {
        swal({
            title: "确定返回吗?",
            text: "确定后将返回列表页!",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                location.href = "${pageContext.request.contextPath}/jumi/drp/goodsunit/getlist";
            }
        });
    }

    //只能输入数字
    function btnonkeyup(obj, value) {
        value = value.replace(/\D/g, '');
        $(obj).val(value);
    }
</script>

</body>
</html>