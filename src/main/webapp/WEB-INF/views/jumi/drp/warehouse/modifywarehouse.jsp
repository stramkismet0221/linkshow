<%@ page import="java.net.URLDecoder" %>
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
    <title>仓库修改</title>
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
            <jsp:param value="/jumi/drp/warehouse/list" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">仓库修改</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">仓库管理</a></li>
                        <li class="active">仓库修改</li>
                    </ol>
                </div>
            </div>
            <!-- .row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" onsubmit="update();return false;" class="form-material form-horizontal" data-toggle="validator">

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;仓库ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control"   value="${warehouse.id}" disabled />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;仓库名称</label>
                                        <div class="col-md-12">
                                            <input type="hidden" name="id" value="${warehouse.id}" />
                                            <input type="text" name="name" onkeyup="btnonkeyupspace(this, value);" maxlength="50" required="required" placeholder="请输入仓库名称" data-error="请输入仓库名称" class="form-control"  value="${warehouse.name}" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;仓库编号</label>
                                        <div class="col-md-12">
                                            <input type="text" name="code" onkeyup="btnonkeyupspace(this, value);" maxlength="20"  placeholder="请输入仓库编号"  class="form-control"  value="${warehouse.code}" />

                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;状态</label>
                                        <div class="radio radio-info" style="display: inline-block; margin-left: 15px; margin-right: 5%;">
                                            <input type="radio" name="status" id="radio1" <c:if test="${warehouse.status==1}">checked=""</c:if>  value="1">
                                            <label for="radio1"> 开启 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="status" id="radio2" <c:if test="${warehouse.status==2}">checked=""</c:if> value="2"  >
                                            <label for="radio2"> 关闭 </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;是否首选仓库</label>
                                        <div class="radio radio-info" style="display: inline-block; margin-left: 15px; margin-right: 5%;">
                                            <input type="radio" name="isFirst" id="radio3" <c:if test="${warehouse.isFirst==1}">checked=""</c:if>  value="1">
                                            <label for="radio3" style="padding-right:12px;"> 是 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="isFirst" id="radio4" <c:if test="${warehouse.isFirst==0}">checked=""</c:if> value="0"  >
                                            <label for="radio4"> 否 </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;存储条件</label>
                                        <div class="col-md-12">
                                            <input type="text" name="storage" onkeyup="btnonkeyupspace(this, value);" maxlength="50"  placeholder="请输入存储条件" data-error="请输入存储条件" class="form-control"  value="${warehouse.storage}" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;区域</label>
                                        <div class="col-md-12">
                                            <div class="col-md-2" style="padding-left:0px">
                                                <select id="provinceid" name="provinceId" onchange="setcity(this)" class="form-control">
                                                    <c:forEach items="${provinces}" var="province">
                                                        <option value="${province.provinceId}" <c:if test="${province.provinceId==warehouse.provinceId}">selected</c:if>  >${province.provinceName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="col-md-2">
                                                <select id="cityid" name="cityId" onchange="setdistrict(this)"  class="form-control">
                                                    <c:forEach items="${citys}" var="city">
                                                        <option value="${city.cityId}" <c:if test="${city.cityId==warehouse.cityId}">selected</c:if>  >${city.cityName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-2">
                                                <select id="districtid" name="districtId"  class="form-control">
                                                    <c:forEach items="${districts}" var="district">
                                                        <option value="${district.districtId}" <c:if test="${district.code==warehouse.districtId}">selected</c:if>  >${district.districtName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;详细地址</label>
                                        <div class="col-md-12" >
                                            <input type="text" name="detailAddress" onkeyup="btnonkeyupspace(this, value);" maxlength="100"  placeholder="请输入详细地址" data-error="请输入详细地址" class="form-control"  value="${warehouse.detailAddress}" />

                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="submit" >
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
        </div>
        <!-- /.row -->
        <!-- .right-sidebar -->
        <div class="right-sidebar">
            <%@include file="/WEB-INF/views/include/right.jsp"%>
        </div>
    </div>
    <footer class="footer text-center">
        <%@include file="/WEB-INF/views/include/footer.jsp"%>
    </footer>
</div>

<script type="text/javascript">

    //设置城市
    function setcity(obj){
        $("#cityid").empty();
        var pid = $("#provinceid").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/region/getcity",
            type: "POST",
            dataType: "json",
            async: false,
            data: {pid:pid},
            success: function (result) {
                $.each(result, function (i, item) {
                    if(i==0){
                        $("<option selected  value="+item.cityId+">"+item.cityName+"</option>").appendTo($("#cityid"));
                    }else{
                        $("<option  value="+item.cityId+">"+item.cityName+"</option>").appendTo($("#cityid"));
                    }

                });
            },
            error: function () {
            	swal({
                    title: "网络异常",
                    cancelButtonText: "确定"
                });
            }
        });

        $("#districtid").empty();
        var cid = $("#cityid").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/region/getdistrict",
            type: "POST",
            dataType: "json",
            async: false,
            data: {cid:cid},
            success: function (result) {
                $.each(result, function (i, item) {
                    $("<option></option>").val(item["districtId"]).text(item["districtName"]).appendTo($("#districtid"));
                });
            },
            error: function () {
                swal({
                    title: "保存失败",
                    cancelButtonText: "确定"
                });
            }
        });

    }
    function setdistrict(obj){
        $("#districtid").empty();
        var cid = $("#cityid").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/region/getdistrict",
            type: "POST",
            dataType: "json",
            async: true,
            data: {cid:cid},
            success: function (result) {
                $.each(result, function (i, item) {
                    $("<option></option>").val(item["districtId"]).text(item["districtName"]).appendTo($("#districtid"));
                });
            },
            error: function () {
            	swal({
                    title: "网络异常",
                    cancelButtonText: "确定"
                });
            }
        });
    }



    //只能输入数字
    function btnonkeyup(obj, value) {
        value = value.replace(/\D/g, '');
        $(obj).val(value);
    }
    function btnonkeyupspace(obj, value) {
        value = value.replace(/ /g, '');
        $(obj).val(value);
    }

    update = function(){
        // 校验
        if ($("input[name='name']").val() === '') {
            $("input[name='name']").focus();
            return false;
        }
        var id=$("input[name='id']").val();
        var name = $("input[name='name']").val();

        $.ajax({
            url: "${pageContext.request.contextPath}/jumi/drp/warehouse/checkname",
            type: "POST",
            dataType: "json",
            async: true,
            data: {name:name,id:id},
            success: function (result) {
                if (result.success) {
                    swal({
                        title: "该仓库名称已经存在",
                        confirmButtonText: "确定"
                    }, function (isConfirm) {

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
            text: "",
            showCancelButton: true,
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/jumi/drp/warehouse/editwarehouse",
                    type : "POST",
                    dataType : "json",
                    data : $("#form").serialize(),
                    success : function(result) {
                        if (result.success) {
                            swal({title : "保存成功",
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/jumi/drp/warehouse/list";
                            });
                        } else {
                        	swal({
                                title: "保存失败",
                                cancelButtonText: "确定"
                            });
                            
                        }
                    },
                    error : function() {
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
                location.href = "${pageContext.request.contextPath}/jumi/drp/warehouse/list";
            }
        });
    }
</script>
</body>
</html>