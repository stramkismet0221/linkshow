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
    <title>商品分类修改</title>
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
                <jsp:param value="/jumi/product/goodstype/getlist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">商品分类修改</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">商品管理</a></li>
                            <li class="active">商品分类修改</li>
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
                                    <div>
                                        <form id="form" class="form-material form-horizontal" onsubmit="update();return false;" data-toggle="validator" novalidate="true">
                                            <input type="hidden" value="${jmGoodsType.id}" name="id">
                                            <input type="hidden" value="${jmGoodsType.pid}" name="pid">
                                            <div class="form-group">
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;分类ID</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="id" class="form-control" value="${jmGoodsType.id}" disabled/>
                                                </div>
                                            </div>
                                            <c:if test="${jmGoodsType.pid != 0}">
                                                <div class="form-group">
                                                    <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;上级分类</label>
                                                    <div class="col-md-12">
                                                        <input type="text" class="form-control" value="${jmGoodsType.parent.name}" disabled/>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="form-group">
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;分类名称</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="name" required="required" maxlength="50" placeholder="请输入分类名称" data-error="请输入分类名称"  class="form-control" value="${jmGoodsType.name}" />
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;分类编码（不可重复）</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="code" required="required" maxlength="11" class="form-control" value="${jmGoodsType.code}" disabled />
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12" style="margin-bottom: 15px;">展示情况（电商展示/实体展示）</label>
                                                <div class="col-sm-12">
                                                    <div class="checkbox checkbox-info" style="display: inline-block;margin-right: 5%;">
                                                        <input type="checkbox" name="showType" id="online"  <c:if test="${jmGoodsType.showType.contains('1')}"> checked </c:if>  />
                                                        <label for="online">电商展示</label>
                                                    </div>
                                                    <div class="checkbox checkbox-info" style="display: inline-block">
                                                        <input type="checkbox" name="showType" id="outline"  <c:if test="${jmGoodsType.showType.contains('2')}"> checked </c:if>  />
                                                        <label for="outline">实体展示</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;序号</label>
                                                <div class="col-md-12">
                                                    <input type="text" name="sno" required="required" maxlength="11" placeholder="请输入分类序号" data-error="请输入分类序号" class="form-control" value="${jmGoodsType.sno}" />
                                                    <div class="help-block with-errors"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12">分类备注</label>
                                                <div class="col-md-12">
                                            <textarea name="description" maxlength="200" rows="5" class="form-control"
                                                      placeholder="请输入分类备注">${jmGoodsType.description}</textarea>
                                                </div>
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
            // 校验表单
            // 表单校验
            var name = $("input[name='name']").val();
            if (checkStr(name)) {
                $("input[name='name']").focus();
                return false;
            }

            var code = $("input[name='code']").val();
            // var pattern = /^[a-zA-Z0-9]+$/;
            // if ($("input[name='code']").val() == '') {
            //     $("input[name='code']").focus();
            //     return false;
            // }
            // if (!pattern.test(code)) {
            //     swal({
            //         title: "菜单唯一编码仅支持大小写英文字母和数字",
            //         confirmButtonText: "确定",
            //     });
            //     $("input[name='code']").focus();
            //     return false;
            // }

            var sno = $("input[name='sno']").val();
            if (checkStr(sno)) {
                $("input[name='sno']").focus();
                return false;
            }
            var reg3 = new RegExp("^[0-9]*$");
            if (!reg3.test($("input[name='sno']").val())) {
                swal({
                    title: "菜单序号仅支持数字",
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
                id:$("input[name='id']").val(),
                name:name,
                code:code,
                pid:$("input[name='pid']").val(),
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
                        url: "${pageContext.request.contextPath}/jumi/product/goodstype/updatejmgoodstype",
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
                    location.href = "/linkshow/jumi/product/goodstype/getlist";
                }
            });
        }
    </script>

</body>
</html>