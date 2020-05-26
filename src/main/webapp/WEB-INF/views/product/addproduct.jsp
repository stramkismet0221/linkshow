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
    <title>新增商品</title>
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/common/adm/plugins/images/favicon.png">
    <!-- Bootstrap Core CSS -->
    <!-- page CSS -->
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/custom-select/custom-select.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/switchery/dist/switchery.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/multiselect/css/multi-select.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/dropify/dist/css/dropify.min.css">
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
            <jsp:param value="/product/getproductlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">商品添加</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品管理</a></li>
                        <li class="active">商品添加</li>
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
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator">
                                    <input type="hidden" name="imageUrl" id="imageUrl" value="" />
                                    <input type="hidden" name="imageFixWidthUrl" id="imageFixWidthUrl" value="" />
                                    <div class="form-group">
                                        <label class="col-md-12">商品条形码</label>
                                        <div class="col-md-12">
                                            <input type="text" name="barCode" maxlength="30" placeholder="请输入商品条形码" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商品名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="name" required="required" maxlength="50" placeholder="请输入商品名称" class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商品分类</label>
                                        <div class="col-sm-12" >
                                            <select class="form-control" data-style="form-control" name="categoryId">
                                                <c:forEach items="${categoryList}" var="data">
                                                    <optgroup label="${data.name}">
                                                        <c:forEach items="${data.child}" var="childData">
                                                            <option value="${childData.id}">${childData.name}</option>
                                                        </c:forEach>
                                                    </optgroup>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商品厂商</label>
                                        <div class="col-md-12">
                                            <input type="text" name="manufacturer" required="required" maxlength="50" placeholder="请输入商品厂商" class="form-control"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <%--商品占位 1 or 2--%>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;商品占位</label>
                                        <div class="col-sm-12">
                                            <select class="form-control" data-style="form-control" name="colSpan">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                            </select>
                                        </div>
                                    </div>
                                    <%-- 第三方支付商品扩展参数 --%>
                                    <div class="form-group">
                                        <label class="col-md-12">第三方商品支付扩展参数</label>
                                        <div class="col-md-12">
                                            <textarea name="payExtend" maxlength="200" class="form-control" rows="5" placeholder="请输入第三方商品支付扩展参数"></textarea>
                                        </div>
                                    </div>
                                    <div class="row" style="position: absolute;top:95%;width: 100%;">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit" value="保存" >
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
                                <h3 class="box-title m-b-0" style="font-size: 14px"><label style="color: #f05b4f">*</label>&nbsp;商品图片</h3>
                                <form enctype="multipart/form-data" id="dropzone1" class="dropzone" method="post" action="${pageContext.request.contextPath}/merchant/updateliceimg">
                                    <%--注意这里的action和下面的url要一直，如果不写的话就算浏览器上传成功了也会报404的错误--%>
                                    <div class="fallback">
                                        <input name="file" type="file"/>
                                    </div>
                                </form>
                                <br/>
                                <h3 class="box-title m-b-0" style="font-size: 14px"><label style="color: #f05b4f">*</label>&nbsp;商品固定宽图片</h3>
                                <form enctype="multipart/form-data" id="dropzone2" class="dropzone" method="post" action="${pageContext.request.contextPath}/merchant/updateliceimg">
                                    <%--注意这里的action和下面的url要一直，如果不写的话就算浏览器上传成功了也会报404的错误--%>
                                    <div class="fallback">
                                        <input name="file" type="file" />
                                    </div>
                                </form>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                            </div>
                        </div>
                    </div>
                </div>
                <%--<div class="col-md-1"></div>--%>
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

<!-- Custom Theme JavaScript -->

<script src="${pageContext.request.contextPath}/common/adm/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/switchery/dist/switchery.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/custom-select/custom-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/multiselect/js/jquery.multi-select.js"></script>

<script>
    jQuery(document).ready(function() {
        // Switchery
        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        $('.js-switch').each(function() {
            new Switchery($(this)[0], $(this).data());
        });
        // For select 2
        $(".select2").select2();
        $('.selectpicker').selectpicker();
        //Bootstrap-TouchSpin
        $(".vertical-spin").TouchSpin({
            verticalbuttons: true,
            verticalupclass: 'ti-plus',
            verticaldownclass: 'ti-minus'
        });
        var vspinTrue = $(".vertical-spin").TouchSpin({
            verticalbuttons: true
        });
        if (vspinTrue) {
            $('.vertical-spin').prev('.bootstrap-touchspin-prefix').remove();
        }
        $("input[name='tch1']").TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '%'
        });
        $("input[name='tch2']").TouchSpin({
            min: -1000000000,
            max: 1000000000,
            stepinterval: 50,
            maxboostedstep: 10000000,
            prefix: '$'
        });
        $("input[name='tch3']").TouchSpin();
        $("input[name='tch3_22']").TouchSpin({
            initval: 40
        });
        $("input[name='tch5']").TouchSpin({
            prefix: "pre",
            postfix: "post"
        });
        // For multiselect
        $('#pre-selected-options').multiSelect();
        $('#optgroup').multiSelect({
            selectableOptgroup: true
        });
        $('#public-methods').multiSelect();
        $('#select-all').click(function() {
            $('#public-methods').multiSelect('select_all');
            return false;
        });
        $('#deselect-all').click(function() {
            $('#public-methods').multiSelect('deselect_all');
            return false;
        });
        $('#refresh').on('click', function() {
            $('#public-methods').multiSelect('refresh');
            return false;
        });
        $('#add-option').on('click', function() {
            $('#public-methods').multiSelect('addOption', {
                value: 42,
                text: 'test 42',
                index: 0
            });
            return false;
        });
    });
</script>



<script type="text/javascript">
    // 设置只上传一张
    $(function(){
        $("#dropzone1").dropzone({
            url:'${pageContext.request.contextPath}/merchant/updateliceimg',//上传地址
            paramName: "file",
            maxFilesize: 2.0, // MB
            maxFiles:1,//一次性上传的文件数量上限
            acceptedFiles: ".jpg,.jpeg,.png,.gif",
            addRemoveLinks : true,//添加移除文件
            autoProcessQueue: true,//不自动上传
            thumbnailWidth:100,
            thumbnailHeight:100,
            dictCancelUploadConfirmation:'你确定要取消上传吗？',
            dictMaxFilesExceeded: "您一次最多只能上传{{maxFiles}}个文件,超过的只会保存前三个",
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
            //change the previewTemplate to use Bootstrap progress bars
            // previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-　　　　　　　　　　name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress 　　　　　　　　progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div 　　　　　　　　class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message　　　　　　　　\"><span data-dz-errormessage></span></div>\n</div>",
            init: function() {
                this.on("removedfile",function(file){
                    $("#imageUrl").val("");
                })
                var img = '';
                this.on("success", function(file,data) {
                    if(data.success==true){
                        $('#subModel').modal('hide');
                        img = data.extInfo.url;
                        $("#imageUrl").val(img);

                    }else{
                        alert("上传失败");
                    }
                });
                 this.on("error", function(file) {
                     this.on("maxfilesexceeded", function(file){
                         this.removeFile(file);
                         $("#imageUrl").val(img);
                         alert("只能上传单个图片");
                     })
                 });
            },


        });

        $("#dropzone2").dropzone({
            url:'${pageContext.request.contextPath}/merchant/updateliceimg',//上传地址
            paramName: "file",
            maxFilesize: 2.0, // MB
            maxFiles:1,//一次性上传的文件数量上限
            acceptedFiles: ".jpg,.jpeg,.png,.gif",
            addRemoveLinks : true,//添加移除文件
            autoProcessQueue: true,//不自动上传
            thumbnailWidth:100,
            thumbnailHeight:100,
            dictCancelUploadConfirmation:'你确定要取消上传吗？',
            dictMaxFilesExceeded: "您一次最多只能上传{{maxFiles}}个文件,超过的只会保存前三个",
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
            //change the previewTemplate to use Bootstrap progress bars
            // previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-　　　　　　　　　　name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress 　　　　　　　　progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div 　　　　　　　　class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message　　　　　　　　\"><span data-dz-errormessage></span></div>\n</div>",
            init: function() {
                this.on("removedfile",function(file){
                    $("#imageFixWidthUrl").val("");
                })
                var img = '';
                this.on("success", function(file,data) {
                    if(data.success==true){
                        $('#subModel').modal('hide');
                        img = data.extInfo.url;
                        $("#imageFixWidthUrl").val(img);
                    }else{
                        alert("上传失败");
                    }
                });
                this.on("error", function(file) {
                    this.on("maxfilesexceeded", function(file){
                        this.removeFile(file);
                        $("#imageFixWidthUrl").val(img);
                        alert("只能上传单个图片");
                    })
                });
            },
        });
    })

    function save() {
        // 校验
        var barCode = $("input[name='barCode']").val();
        if (barCode != '') {
            var regExp = /^[\u4e00-\u9fa5]+$/ ;    //    \u4e00-\u9fa5 是中文字符的unicode编码
            if (barCode.match(regExp)){
                swal({
                    title: "商品条形码不支持汉字",
                    confirmButtonText: "确定"
                })
                $("input[name='barCode']").focus();
                return false;
            }
        }
        var name = $("input[name='name']").val();
        if ($("input[name='name']").val() == '') {
            $("input[name='name']").focus();
            return false;
        }
        if ($("input[name='categoryId']").val() == '') {
            swal({
                title: "请选择商品分类",
                confirmButtonText: "确定"
            })
            $("input[name='categoryId']").focus();
            return false;
        }
        if ($("input[name='manufacturer']").val() == '') {
            $("input[name='manufacturer']").focus();
            return false;
        }
        if ($("input[name='colSpan']").val() == '') {
            swal({
                title: "请选择商品占位",
                confirmButtonText: "确定"
            })
            $("input[name='colSpan']").focus();
            return false;
        }
        if ($("input[name='imageUrl']").val() == '') {
            swal({
                title: "请上传商品图片",
                confirmButtonText: "确定"
            })
            $("input[name='imageUrl']").focus();
            return false;
        }
        if ($("input[name='imageFixWidthUrl']").val() == '') {
            swal({
                title: "请上传商品固定宽图片",
                confirmButtonText: "确定"
            })
            $("input[name='imageFixWidthUrl']").focus();
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
                    url: "${pageContext.request.contextPath}/product/saveproduct",
                    type: "POST",
                    dataType: "json",
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: result.msg,
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/product/getproductlist";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                cancelButtonText: "确定"
                            })
                        }
                    },
                    error: function () {
                        alert("保存失败");
                    }
                });
            }
        });
    }

</script>

</body>
</html>