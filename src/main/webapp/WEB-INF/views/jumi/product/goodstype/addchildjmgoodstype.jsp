<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="../../../common/adm/plugins/images/favicon.png">
    <title>新增子分类</title>
</head>

<body class="fix-sidebar">
<!-- Preloader -->
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<div id="wrapper">
    <!-- Top Navigation -->
    <nav class="navbar navbar-default navbar-static-top m-b-0">
        <%@include file="/WEB-INF/views/include/header.jsp" %>
    </nav>
    <!-- End Top Navigation -->
    <!-- Left navbar-header -->
    <div class="navbar-default sidebar" role="navigation">
        <jsp:include page="/WEB-INF/views/include/left.jsp">
            <jsp:param value="/jumi/product/goodstype/getlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <!-- Left navbar-header end -->
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <!-- .page title -->
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">新增子分类</h4>
                </div>
                <!-- /.page title -->
                <!-- .breadcrumb -->
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品管理</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/jumi/product/goodstype/getlist">商品分类</a></li>
                    </ol>
                </div>
                <!-- /.breadcrumb -->
            </div>
            <!-- .row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <form id="form" onsubmit="save();return false;" class="form-material form-horizontal" data-toggle="validator">
                                <input type="hidden" id="pid" value="${pid}">
                                <div class="col-md-8">
                                    <c:if test="${pid != 0}">
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;上级分类</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${jmGoodsType.name}" disabled/>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;分类名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" maxlength="50" name="name" data-error="请输入分类名称" placeholder="请输入分类名称" required="required"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;分类编码（不可重复）</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" name="code" maxlength="50"  data-error="请输入分类编码" placeholder="请输入分类编码" required="required"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12" style="margin-bottom: 15px;">展示情况（电商展示/实体展示）</label>
                                        <div class="col-sm-12">
                                            <div class="checkbox checkbox-info" style="display: inline-block; margin-right: 5%;">
                                                <input type="checkbox" name="showType" id="online" >
                                                <label for="online">电商展示</label>
                                            </div>
                                            <div class="checkbox checkbox-info" style="display: inline-block">
                                                <input type="checkbox" name="showType" id="outline">
                                                <label for="outline">实体展示</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;序号</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" maxlength="10" name="sno" data-error="请输入分类序号" placeholder="请输入分类序号" required="required"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">分类备注</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="200" rows="5" class="form-control"
                                                      placeholder="请输入分类备注"></textarea>
                                        </div>
                                    </div>

                                    <div class="row">
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
                                </div>
                            </form>

                            <div class="modal fade" id="iconsModal" tabindex="-1" role="dialog" aria-labelledby="iconsModal">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content" style="width: 1000px; right: 25px;">

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <!-- .right-sidebar -->
            <div class="right-sidebar">
                <%--<%@include file="/WEB-INF/views/include/right.jsp" %>--%>
            </div>
            <!-- /.right-sidebar -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center">
            <%@include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<script type="text/javascript">
    function save() {
        // 表单校验
        var name = $("input[name='name']").val();
        if (checkStr(name)) {
            $("input[name='name']").focus();
            return false;
        }

        var code = $("input[name='code']").val();
        var pattern = /^[a-zA-Z0-9]+$/;
        if ($("input[name='code']").val() == '') {
            $("input[name='code']").focus();
            return false;
        }
        if (!pattern.test(code)) {
            swal({
                title: "分类唯一编码仅支持大小写英文字母和数字",
                confirmButtonText: "确定",
            });
            $("input[name='code']").focus();
            return false;
        }

        var sno = $("input[name='sno']").val();
        if (checkStr(sno)) {
            $("input[name='sno']").focus();
            return false;
        }
        var reg3 = new RegExp("^[0-9]*$");
        if (!reg3.test($("input[name='sno']").val())) {
            swal({
                title: "分类序号仅支持数字",
                confirmButtonText: "确定",
            });
            $("input[name='sno']").focus();
            return false;
        }

        if (Number(sno)<1) {
            swal({
                title: "序号必须大于或等于1",
                confirmButtonText: "确定",
            });
            $("input[name='sno']").focus();
            return false;
        }
        var showType = [];
        if ($("#online").prop("checked")) {
            showType.push("1");
        }
        if ($("#outline").prop("checked")) {
            showType.push("2");
        }
        var data = {
            name:name,
            pid:$("#pid").val(),
            code:code,
            sno:sno,
            showType:showType.toString(),
            description:$("textarea[name='description']").val()
        };
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
                    url: "${pageContext.request.contextPath}/jumi/product/goodstype/insertjmgoodstype",
                    type: "POST",
                    dataType: "json",
                    data: data,
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: result.msg,
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/product/goodstype/getlist";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                closeOnCancel: "确定"
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