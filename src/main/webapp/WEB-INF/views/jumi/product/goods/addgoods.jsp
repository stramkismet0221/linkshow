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
    <c:if test="${type == 1}">
        <c:set var="title" value="实物商品"></c:set>
    </c:if>
    <c:if test="${type == 2}">
        <c:set var="title" value="虚拟商品"></c:set>
    </c:if>
    <c:if test="${type == 3}">
        <c:set var="title" value="卡包商品"></c:set>
    </c:if>
    <c:if test="${type == 4}">
        <c:set var="title" value="原材料"></c:set>
    </c:if>
    <title>新增${title}</title>
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
            <jsp:param value="/jumi/product/goods/getlist?type=${type}" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 新增商品</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 商品管理</a></li>
                        <li class="active"> 新增商品</li>
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
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator" novalidate="true">
                                    <input type="hidden" name="extendFields">
                                    <input type="hidden" name="type" value="${type}">
                                    <input type="hidden" name="icons">
                                    <div class="form-group">
                                        <label class="col-md-6"><label style="color: #f05b4f">*</label>&nbsp;条码</label>
                                        <label class="col-md-6"><label style="color: #f05b4f">&nbsp;</label>&nbsp;商家自编码</label>
                                        <div class="col-md-6">
                                            <input type="text" name="barCode" maxlength="25" class="form-control" placeholder="请输入条码"/>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" name="code" maxlength="25"
                                                   class="form-control" placeholder="请输入商家自编码" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <c:if test="${type == 1 || type == 2}">
                                            <label class="col-md-6" id="name"><label style="color: #f05b4f">*</label>&nbsp;商品名称</label>
                                        </c:if>
                                        <c:if test="${type == 4}">
                                            <label class="col-md-6" id="name"><label style="color: #f05b4f">*</label>&nbsp;材料名称</label>
                                        </c:if>
                                        <label class="col-md-6" id="shortCode"><label style="color: #f05b4f">&nbsp;</label>&nbsp;拼音简码</label>
                                        <c:if test="${type == 1 || type == 2}">
                                            <div class="col-md-6">
                                                <input type="text" name="name" maxlength="50" placeholder="请输入商品名称" class="form-control" />
                                            </div>
                                        </c:if>
                                        <c:if test="${type == 4}">
                                            <div class="col-md-6">
                                                <input type="text" name="name" maxlength="50" placeholder="请输入材料名称" class="form-control" />
                                            </div>
                                        </c:if>
                                        <div class="col-md-6">
                                            <input type="text" name="shortCode" maxlength="25" placeholder="请输入拼音简码"  class="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6"><label style="color: #f05b4f">*</label>&nbsp;商品分类</label>
                                        <label class="col-md-6"><label style="color: #f05b4f">&nbsp;</label>&nbsp;规格型号</label>
                                        <div class="col-md-6">
                                            <select name="goodsTypeId" class="form-control" data-placeholder="商品分类" tabindex="1">
                                                <option value="">请选择商品分类</option>
                                                <c:forEach items="${jmGoodsTypes}" var="data">
                                                    <c:if test="${data.level == 1}">
                                                        <option value="${data.id}">${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 2}">
                                                        <option value="${data.id}">---${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 3}">
                                                        <option value="${data.id}">------${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 4}">
                                                        <option value="${data.id}">---------${data.name}</option>
                                                    </c:if>
                                                    <c:if test="${data.level == 5}">
                                                        <option value="${data.id}">------------${data.name}</option>
                                                    </c:if>
                                                        <%@include file="recursiveOptions.jsp"%>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <input type="text" name="specs"  maxlength="10"
                                                   class="form-control" placeholder="请输入规格型号" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <c:if test="${type == 1 || type == 4}">
                                            <label class="col-md-6"><label style="color: #f05b4f">*</label>&nbsp;单位</label>
                                        </c:if>
                                        <c:if test="${type == 2}">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;单位</label>
                                        </c:if>
                                        <c:if test="${type == 1 || type == 4}">
                                            <label class="col-md-6"><label style="color: #f05b4f">*</label>&nbsp;首选仓库</label>
                                        </c:if>

                                        <c:if test="${type == 1 || type == 4}">
                                            <div class="col-md-6">
                                                <select name="unitId" class="form-control" data-placeholder="单位" tabindex="1">
                                                    <option value="">请选择单位</option>
                                                    <c:forEach items="${jmGoodsUnits}" var="data">
                                                        <option value="${data.id}">${data.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${type == 2}">
                                                <div class="col-md-6">
                                                    <select name="unitId" class="form-control" data-placeholder="单位" tabindex="1">
                                                        <option value="">请选择单位</option>
                                                        <c:forEach items="${jmGoodsUnits}" var="data">
                                                            <option value="${data.id}">${data.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                        </c:if>
                                        <c:if test="${type == 1 || type == 4}">
                                            <div class="col-md-6">
                                                <select name="wareHouseId" class="form-control" data-placeholder="首选仓库" tabindex="1">
                                                    <option value="">请选择仓库</option>
                                                    <c:forEach items="${warehouses}" var="data">
                                                        <option value="${data.id}">${data.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </c:if>
                                    </div>
                                    <c:if test="${type == 1 || type == 2}">
                                        <div class="form-group">
                                            <label class="col-md-6"><label style="color: #f05b4f">*</label>&nbsp;零售价</label>
                                            <label class="col-md-6"><label style="color: #f05b4f">&nbsp;</label>批发价</label>
                                            <div class="col-md-6">
                                                <input type="text" name="price" maxlength="10"
                                                       class="form-control" placeholder="请输入零售价"  value="${no}" onkeyup="btnonkeyupDiscountfour(this,value)"/>
                                            </div>
                                            <div class="col-md-6">
                                                <input type="text" name="tradePrice" maxlength="10"
                                                       class="form-control" placeholder="请输入批发价" value="${no}" onkeyup="btnonkeyupDiscountfour(this,value)"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6">
                                                <label style="display: inline-block;">&nbsp;网店价</label>
                                                <div class="checkbox checkbox-info" style="display: inline-block;">
                                                    <input id="online" type="checkbox" onclick='syncNum(this,1)'>
                                                    <label for="online"> 同零售价 </label>
                                                </div>
                                            </label>
                                            <label class="col-md-6">
                                                <label style="display: inline-block;">&nbsp;设备价</label>
                                                <div class="checkbox checkbox-info" style="display: inline-block;">
                                                    <input id="terminal" type="checkbox" onclick='syncNum(this,2)'>
                                                    <label for="terminal"> 同零售价 </label>
                                                </div>
                                            </label>
                                            <div class="col-md-6">
                                                <input type="text" name="onlinePrice" maxlength="10"
                                                       class="form-control" placeholder="请输入网店价"  value="${no}" onkeyup="btnonkeyupDiscountfour(this,value)"/>
                                            </div>
                                            <div class="col-md-6">
                                                <input type="text" name="terminalPrice" maxlength="10"
                                                       class="form-control" placeholder="请输入设备价"  value="${no}" onkeyup="btnonkeyupDiscountfour(this,value)"/>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-6"><label style="color: #f05b4f">&nbsp;</label>成本价</label>
                                        <label class="col-md-6"><label style="color: #f05b4f">&nbsp;</label>&nbsp;</label>
                                        <div class="col-md-6">
                                            <input type="text" name="costPrice" maxlength="10"
                                                   class="form-control" placeholder="请输入成本价" value="${no}" onkeyup="btnonkeyupDiscountfour(this,value)"/>
                                        </div>
                                        <div class="col-md-6"></div>
                                    </div>
                                    <c:if test="${type == 1 || type == 2}">
                                        <div class="form-group">
                                            <div class="col-md-2">
                                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                                    自定义字段
                                                </button>
                                            </div>
                                        </div>
                                        <%--自定义字段--%>
                                        <%@include file="extendfileds.jsp"%>
                                    </c:if>
                                    <%--扩展属性--%>
                                    <div class="form-group">
                                        <div class="col-md-2">
                                            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample1" aria-expanded="false" aria-controls="collapseExample1">
                                                扩展属性
                                            </button>
                                        </div>
                                    </div>
                                    <c:set var="type" value="${type}"></c:set>
                                    <%@include file="extendsattribute.jsp"%>
                                    <c:if test="${type == 1 || type == 4}">
                                        <%--预警--%>
                                        <div class="form-group">
                                            <div class="col-md-2">
                                                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample2" aria-expanded="false" aria-controls="collapseExample2">
                                                    库存预警
                                                </button>
                                            </div>
                                        </div>
                                        <div class="collapse" id="collapseExample2">
                                            <div class="panel-body">
                                                <div class="panel-body" style="padding: 10px;">
                                                    <div class="form-group">
                                                        <div class="col-md-12">
                                                            <label class="col-md-6"><label style="color: #f05b4f"></label>&nbsp;库存值低于</label>
                                                            <label class="col-md-6"><label style="color: #f05b4f"></label>&nbsp;库存值高于</label>
                                                            <div class="col-md-6">
                                                                <input type="text" name="minStock" maxlength="7" onkeyup="btnonkeyup(this,value)"
                                                                       class="form-control" placeholder="库存数" value="0"/>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <input type="text" name="maxStock" maxlength="7" onkeyup="btnonkeyup(this,value)"
                                                                       class="form-control"  placeholder="库存数" value="0"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="row" style="position: absolute;top:95%;width: 100%;">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light">
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <br/>
                                <h3 class="box-title m-b-0">图片</h3>
                                <form enctype="multipart/form-data" class="dropzone" method="post" action="${pageContext.request.contextPath}/jumi/product/goods/uploadimg">
                                    <%--注意这里的action和下面的url要一直，如果不写的话就算浏览器上传成功了也会报404的错误--%>
                                    <div class="fallback">
                                        <input name="file" type="file" />
                                    </div>
                                </form>
                                <h6>最多可上传5张，建议尺寸 640 x 640px。</h6>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
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
<script>
    jQuery(document).ready(function() {
        $('#datepicker-autoclose').datepicker({
            format:'yyyy-mm-dd',
        });
        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        $('.js-switch').each(function() {
            new Switchery($(this)[0], $(this).data());
        });
    });
    var imgs = [];
    $(function(){
        $(".dropzone").dropzone({
            url:'${pageContext.request.contextPath}/jumi/product/goods/uploadimg',//上传地址
            paramName: "file",
            maxFilesize: 2.0, // MB
            maxFiles:5,
            acceptedFiles: ".jpg,.jpeg,.png,.gif",
            addRemoveLinks : true,//添加移除文件
            autoProcessQueue: true,//不自动上传
            thumbnailWidth:100,
            thumbnailHeight:100,
            dictCancelUploadConfirmation:'你确定要取消上传吗？',
            dictMaxFilesExceeded: "您一次最多只能上传{{maxFiles}}个文件",
            dictFileTooBig:"文件过大({{filesize}}MB). 上传文件最大支持: {{maxFilesize}}MB.",
            dictDefaultMessage :
                '<span class="bigger-150 bolder"><i class="icon-caret-right red"></i> 拖动文件至该处</span> \
                <span class="smaller-80 grey">(或点击此处)</span> <br /> \
                <i class="upload-icon icon-cloud-upload blue icon-3x"></i>',
            dictResponseError: '文件上传失败!',
            dictInvalidFileType: "你不能上传该类型文件,文件类型只能是*.jpg,*.jpeg,*.png,*.gif",
            dictCancelUpload: "取消上传",
            dictCancelUploadConfirmation: "你确定要取消上传吗?",
            dictRemoveFile: "移除文件",
            uploadMultiple:false,
            init: function() {
                $(".dz-message").css("margin-bottom","10px");
                $(".dz-message").css("margin-top","45px");
                this.on("removedfile",function(file){
                    var imgHtml = file.previewElement;
                    var src = new Array();
                    var img = imgHtml.getElementsByTagName('img');
                    for(var i in img) {
                        if (img[i].src) {
                            src.push(img[i].src);
                        }
                    }
                    if (src[0].indexOf('http') == -1) {
                        src[0] = JSON.parse(file.xhr.responseText).extInfo.url;
                    }
                    var img= $("input[name='icons']").val();
                    img = img.replace(decodeURI(src[0]),"");
                    if (img.substr(0,1)==',') {
                        img=img.substr(1);
                    }
                    if(img.endsWith(",")){
                        img = img.substring(0,img.length-1);
                    }
                    img = img.replace(",,",",");
                    $("input[name='icons']").val(img);
                    this.removeFile(file);
                });
                var myDropzone = this; // closure
                this.on("addedfile", function () {
                    $('#subModel').modal('show');
                    $(".dz-message").css("display","block");
                    $(".dz-message").css("margin","1% 0");
                    var num = $(".dz-image").length;
                    for(var i = 0;i<num;i++){
                        $(".dz-image").eq(i).find('img').css("width","120px");
                        $(".dz-image").eq(i).find('img').css("height","120px");
                    }
                });
                this.on("success", function(file,data) {
                    if(data.success == true){
                        var img = $("input[name='icons']").val();
                        if(img.length < 1){
                            $("input[name='icons']").val(data.extInfo.url);
                        }else{
                            img += "," + data.extInfo.url;
                            $("input[name='icons']").val(img);
                        }
                    }else{
                        alert("上传失败");
                    }
                });

                this.on("maxfilesreached",function(){
                    $(".dz-message").css("display","none");
                    $('.dropzone').removeClass('dz-clickable');
                    $('.dropzone')[0].removeEventListener('click', this.listeners[1].events.click);
                });

            },
        });
    })
</script>
<script type="text/javascript">

    var textMust;
    var radioMust;
    var checkboxMust;
    var selectMust;
    $(".extend-text").each(function () {
        var obj = $(this);
        var must = obj.attr('must');
        if (1 == must) {
            textMust = true;
            return;
        }
    });
    $(".extend-radio").each(function () {
        var obj = $(this);
        var must = obj.attr('must');
        if (1 == must) {
            radioMust = true;
            return;
        }
    });
    $(".extend-checkbox").each(function () {
        var obj = $(this);
        var must = obj.attr('must');
        if (1 == must) {
            checkboxMust = true;
            return;
        }
    });
    $(".extend-select").each(function () {
        var obj = $(this);
        var must = obj.attr('must');
        if (1 == must) {
            selectMust = true;
            return;
        }
    });
    // 切换dropup、dropdown
    function switchIcon() {
        if ($("#collapseTwo").attr("class").indexOf("collapse in") > -1) {
            $("#advanced").removeClass("dropup");
        } else {
            $("#advanced").addClass("dropup");
        }
    }
    function save() {
        // 校验
        var barCode = $("input[name='barCode']").val();
        if (checkStr(barCode)) {
            $("input[name='barCode']").focus();
            return false;
        }

        var name = $("input[name='name']").val();
        if (checkStr(name)) {
            $("input[name='name']").focus();
            return false;
        }

        var goodsTypeId = $("select[name='goodsTypeId']").val();
        if (checkStr(goodsTypeId)) {
            swal({
                title: "请选择商品分类",
                confirmButtonText: "确定"
            });
            return false;
        }

        // 实物商品 和 虚拟商品 有售价
        if (${type ==1 || type == 2}) {
            var price = $("input[name='price']").val();
            if (checkStr(price)) {
                $("input[name='price']").focus();
                return false;
            }
        }

        // 实物商品,虚拟商品,原材料有单位,卡包商品没有单位
        if (${type == 1 || type ==2 || type ==4}) {
            var onlinePrice = $("input[name='onlinePrice']").val();
            var terminalPrice = $("input[name='terminalPrice']").val();
            var unit = $("select[name='unitId']").val();
            if (checkStr(unit)) {
                swal({
                    title: "请选择单位",
                    confirmButtonText: "确定"
                });
                return false;
            }
            if (${type != 4}) {
                if (checkStr(onlinePrice)) {
                    $("input[name='onlinePrice']").focus();
                    return false;
                }
                if (checkStr(terminalPrice)) {
                    $("input[name='terminalPrice']").focus();
                    return false;
                }
            }
        }

        // 实物商品和原材料有仓库
        if (${type == 1 || type ==4}) {
            var wareHouseId = $("select[name='wareHouseId']").val();
            if (checkStr(wareHouseId)) {
                swal({
                    title: "请选择仓库",
                    confirmButtonText: "确定"
                });
                return false;
            }
        }

        var flag = checkExtendsFieldsMust();
        if (!flag) {
            return false;
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
                    url: "${pageContext.request.contextPath}/jumi/product/goods/savegoods",
                    type: "POST",
                    dataType: "json",
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/product/goods/getlist?type="+${type};
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

    function syncNum(obj,num) {
        var price = $("input[name='price']").val();
        if (checkStr(price)) {
            swal({
                title: "请先输入零售价",
                cancelButtonText: "确定"
            });
            $(obj).prop("checked",false);
            $("input[name='price']").focus();
            return false;
        }else {
            if (1 == num) {
                $("input[name='onlinePrice']").val(price);
                if ($(obj).prop("checked")) {
                    $("input[name='onlinePrice']").attr("readonly","true");
                }else {
                    $("input[name='onlinePrice']").removeAttr("readonly");
                }
            }
            if (2 == num) {
                $("input[name='terminalPrice']").val(price);
                if ($(obj).prop("checked")) {
                    $("input[name='terminalPrice']").attr("readonly","true");
                }else {
                    $("input[name='terminalPrice']").removeAttr("readonly");
                }
            }
        }
    }

    //检查自定义字段中必填/选项是否都已经输入或选择
    function checkExtendsFieldsMust() {
        var extendsFields = "";
        var textIsNull = false;
        var radioAnswerArr = [];
        var checkBoxAnswerArr = [];
        var selectIsNull = false;
        // 文本框
        $(".extend-text").each(function () {
            var obj = $(this);
            var must = obj.attr("must");
            var textVal = obj.val();
            if (1 == must && checkStr(textVal)) {
                textIsNull = true;
                return false;
            }else {
                extendsFields += obj.attr('name') + ':' + obj.val() +",";
            }
        });
        // 单选
        $(".extend-radio").each(function () {
            var obj = $(this);
            var must = obj.attr("must");
            var radioValue = "";
            if (obj.prop('checked')) {
                radioValue = obj.val();
                extendsFields += obj.attr('name') + ':' + obj.val() +",";
            }
            if (1 == must && 'undefined' != radioValue && radioValue.length > 0) {
                radioAnswerArr.push(radioValue);
            }
        });
        //多选
        $(".extend-checkbox").each(function () {
            var obj = $(this);
            var must = obj.attr("must");
            var checkedValue ="";
            if (obj.prop("checked")) {
                checkedValue = obj.val();
                extendsFields += obj.attr('name') + '┋' + obj.val() +",";
            }
            if (1 == must && 'undefined' != checkedValue && checkedValue.length > 0) {
                checkBoxAnswerArr.push(checkedValue);
            }
        });
        //下拉框
        $(".extend-select").each(function () {
            var obj = $(this);
            var selectName = obj.attr('name');
            var nn2 = "select[name='"+ selectName +"']";
            var selectValue = $(nn2).val();
            var must = obj.attr("must");
            if (1 == must && checkStr(selectValue)) {
                selectIsNull = true;
                return false;
            } else {
                extendsFields += obj.attr('name') + ':' + obj.val() +",";
            }
        });
        if (textMust && textIsNull) {
            swal({
                title: "请输入单行文本",
                confirmButtonText: "确定"
            }, function () {
                $('#collapseExample').collapse('show')
            });
            return false;
        }
        if (radioMust && radioAnswerArr.length <= 0) {
            swal({
                title: "请选择单选",
                confirmButtonText: "确定"
            }, function () {
                $('#collapseExample').collapse('show')
            });
            return false;
        }
        if (checkboxMust && checkBoxAnswerArr.length <= 0) {
            swal({
                title: "请选择多选",
                confirmButtonText: "确定"
            }, function () {
                $('#collapseExample').collapse('show')
            });
            return false;
        }
        if (selectMust && selectIsNull) {
            swal({
                title: "请选择下拉选",
                confirmButtonText: "确定"
            }, function () {
                $('#collapseExample').collapse('show')
            });
            return false;
        }
        $("input[name='extendFields']").val(extendsFields);
        return true;
    }
</script>

</body>
</html>