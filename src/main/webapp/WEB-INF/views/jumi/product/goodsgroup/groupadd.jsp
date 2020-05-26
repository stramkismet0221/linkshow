<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>新增分组</title>
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
            <jsp:param value="/jumi/product/goodsgroup/getlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 商品分组添加</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 商品分组管理</a></li>
                        <li class="active"> 商品分组添加</li>
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
                                <form id="form" onsubmit="save(); return false;" class="form-horizontal form-material" data-toggle="validator">
                                    <input type="hidden"  name="logo" id="logo"/>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;分组名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="name" maxlength="50" required="required" placeholder="请输入分组名称" data-error="请输入分组名称" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;排序</label>
                                        <div class="col-md-12">
                                            <input type="text" name="sno" maxlength="10" required="required" placeholder="请输入排序编号" onkeyup="btnonkeyup(this,value);"  data-error="请输入排序编号" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">分组链接</label>
                                        <div class="col-md-12">
                                            <input type="text" name="linkUrl" maxlength="100"  placeholder="请输分组链接" data-error="请输入分组链接" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">简介</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="200" class="form-control" rows="5" placeholder="请输入分组简介" ></textarea>
                                        </div>
                                    </div>
                                    <div class="row" style="position: absolute;top:95%;width: 100%;">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="submit">
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
                                <h3 class="box-title m-b-0">分组图标</h3>
                                <form enctype="multipart/form-data" class="dropzone" method="post" action="${pageContext.request.contextPath}/jumi/product/goodsgroup/editimg">
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
<script src="../../../common/adm/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<script src="../../../common/adm/js/jquery.slimscroll.js"></script>
<script src="../../../common/adm/plugins/bower_components/sweetalert/sweetalert.min.js"></script>
<script src="../../../common/adm/plugins/bower_components/sweetalert/jquery.sweet-alert.custom.js"></script>
<script src="../../../common/adm/js/waves.js"></script>
<script src="../../../common/adm/js/custom.js"></script>
<script src="../../../common/adm/js/custom.min.js"></script>
<script src="../../../common/adm/js/validator.js"></script>
<script src="../../../common/adm/plugins/bower_components/dropzone-master/dist/dropzone.js"></script>
<script src="../../../common/adm/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
<script type="text/javascript">
    //只能输入数字
    function btnonkeyup(obj, value) {
        value = value.replace(/\D/g, '');
        $(obj).val(value);
    }
    $(function(){

        $(".dropzone").dropzone({
            url:'${pageContext.request.contextPath}/jumi/product/goodsgroup/editimg',//上传地址
            paramName: "file",
            maxFilesize: 2.0, // MB
            maxFiles:1,
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
                this.on("removedfile",function(file){
                    var url=$("#logo").val();
                    $("#logo").val("");

                    $(".dz-message").css("display","block");
                    $(".dz-message").css("margin","1% 0");
                    $('.dropzone').addClass('dz-clickable');
                    $('.dropzone')[0].addEventListener('click', this.listeners[1].events.click);

                })
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
                    if(data.success==true){
                        var img = $("#logo").val();
                        if(img.length<1){
                            $("#logo").val(data.extInfo.url);
                        }else{
                            img+=","+data.extInfo.url;
                            $("#logo").val(img);
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


    function save() {
        // 校验
        if ($("input[name='name']").val() === '') {
            $("input[name='name']").focus();
            return false;
        }
        var name = $("input[name='name']").val();
        if ($("input[name='sno']").val() === '') {
            $("input[name='sno']").focus();
            return false;
        }
        var sno = $("input[name='sno']").val();
        var pattern = /^[+]{0,1}(\d+)$/;
        if(!pattern.test(sno)){
            swal({
                title: "序号必须为数字",
                confirmButtonText: "确定"
            }, function (isConfirm) {

            });
            return false;
        }
        if(sno<1){
            swal({
                title: "序号必须大于等于1",
                confirmButtonText: "确定"
            }, function (isConfirm) {

            });
            $("input[name='sno']").focus();
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/jumi/product/goodsgroup/checkgoodsgroupbyname",
            type: "POST",
            dataType: "json",
            async: true,
            data: {name:name,id:null},
            success: function (result) {
                if (!result.success) {
                    swal({
                        title: "该分组名称已经存在",
                        confirmButtonText: "确定"
                    }, function (isConfirm) {

                    });
                }
            },
            error: function () {
                alert("网络异常");
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
                // ajax提交
                $.ajax({
                    url: "${pageContext.request.contextPath}/jumi/product/goodsgroup/add",
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
                                    location.href = "${pageContext.request.contextPath}/jumi/product/goodsgroup/getlist";
                                }
                            });
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