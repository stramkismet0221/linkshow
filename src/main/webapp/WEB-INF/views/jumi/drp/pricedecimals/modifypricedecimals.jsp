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
    <title>单价小数位</title>
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
            <jsp:param value="/jumi/drp/otherset/toeditpricedecimals" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">单价小数位修改</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">单价小数位管理</a></li>
                        <li class="active">单价小数位修改</li>
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
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;单价小数位(设置商品价格的小数位数)</label>
                                        <div class="col-md-12">
                                            <input type="hidden" name="id" value="${dict.id}" />
                                            <input type="text" name="value" onkeyup="btnonkeyup(this, value);" maxlength="1" required="required" placeholder="请输入单价小数位" data-error="请输入单价小数位" class="form-control"  value="${dict.value}" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="submit" >
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
    //只能输入数字
    function btnonkeyup(obj, value) {
        value = value.replace(/\D/g, '');
        $(obj).val(value);
    }
    update = function(){
        // 校验
        if ($("input[name='value']").val() === '') {
            $("input[name='value']").focus();
            return false;
        }
        var id=$("input[name='id']").val();
        var value = $("input[name='value']").val();
        var pattern = /^[+]{0,1}(\d+)$/;
        if(!pattern.test(value)){
            swal({
                title: "请输入数字",
                confirmButtonText: "确定"
            }, function (isConfirm) {

            });
            return false;
        }
              
        if(Number(value)>=5){
            swal({
                title: "价格小数位位数应该在0至4位之间",
                confirmButtonText: "确定"
            }, function (isConfirm) {

            });
            return false;
        }
        swal({
            title: "确定要设置价格小数位为"+value+"位吗?",
            text: "",
            showCancelButton: true,
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/jumi/drp/otherset/unit",
                    type : "POST",
                    dataType : "json",
                    data : $("#form").serialize(),
                    success : function(result) {
                        if (result.success) {
                            swal({title : "保存成功",
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/jumi/drp/otherset/toeditpricedecimals";
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

</script>
</body>
</html>