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
    <title>商户详情</title>
    <link href="../common/adm/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <link href="../common/adm/css/animate.css" rel="stylesheet">
    <link href="../common/adm/css/style.css" rel="stylesheet">
    <link href="../common/adm/css/colors/blue-dark.css" id="theme" rel="stylesheet">
</head>
<script src="../common/adm/plugins/bower_components/jquery/dist/jquery.min.js"></script>
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
            <jsp:param value="/merchant/getmerchantlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">商户详情</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商户管理</a></li>
                        <li class="active">商户详情</li>
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
                                <form id="form" class="form-material form-horizontal" data-toggle="validator">
                                    <div class="form-group">
                                        <label class="col-md-12">商户ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${merchant.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">商户公司名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${merchant.company}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">商户描述</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${merchant.description}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">营业执照缩略图</label>
                                        <div class="col-md-12">
                                                    <c:forTokens items="${merchant.licenseImg}" delims="," var="liceimg">
                                                        <img src="<c:out value="${liceimg}"/>" id="<c:out value="${liceimg}"/>" style="width: 100px;" onclick="enlarge(this)"/>&nbsp;&nbsp;&nbsp;
                                                    </c:forTokens>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-5"></div>
                                    <div class="col-lg-2 col-sm-4 col-xs-12">
                                        <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
                                            <span style="vertical-align: inherit;">返回</span>
                                        </button>
                                    </div>
                                </div>
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
<script src="../common/adm/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<script src="../common/adm/js/jquery.slimscroll.js"></script>
<script src="../common/adm/plugins/bower_components/sweetalert/sweetalert.min.js"></script>
<script src="../common/adm/plugins/bower_components/sweetalert/jquery.sweet-alert.custom.js"></script>
<script src="../common/adm/js/waves.js"></script>
<script src="../common/adm/js/custom.js"></script>
<script type="text/javascript">
    function enlarge(parm) {
        var width = $(parm).width();
        if(width == 100){
            $(parm).css("width", "300px");
        }else{
            $(parm).css("width", "100px");
        }
    }
    function imgShow(outerdiv, innerdiv, bigimg, _this){
        var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
        $(bigimg).attr("src", src);//设置#bigimg元素的src属性
        /*获取当前点击图片的真实大小，并显示弹出层及大图*/
        $("<img/>").attr("src", src).load(function(){
            var windowW = $(window).width();//获取当前窗口宽度
            var windowH = $(window).height();//获取当前窗口高度
            var realWidth = this.width;//获取图片真实宽度
            var realHeight = this.height;//获取图片真实高度
            var imgWidth, imgHeight;
            var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
            if(realHeight>windowH*scale) {//判断图片高度
                imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
                imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
                if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                    imgWidth = windowW*scale;//再对宽度进行缩放
                }
            } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
                imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
                imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度
            } else {//如果图片真实高度和宽度都符合要求，高宽不变
                imgWidth = realWidth;
                imgHeight = realHeight;
            }
            $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
            var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
            var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
            $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
            $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
        });
        $(outerdiv).click(function(){//再次点击淡出消失弹出层
            $(this).fadeOut("fast");
        });
    }
</script>
</body>
</html>