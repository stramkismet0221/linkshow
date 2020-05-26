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
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>菜单修改</title>
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
            <jsp:param value="/menu/getmenulist" name="visitUrl"/>
        </jsp:include>
    </div>
    <!-- Left navbar-header end -->
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <!-- .page title -->
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">修改菜单</h4>
                </div>
                <!-- /.page title -->
                <!-- .breadcrumb -->
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">系统管理</a></li>
                        <li class="active">菜单管理</li>
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
                            <form id="form" onsubmit="save(); return false" class="form-material form-horizontal" data-toggle="validator">
                                <div class="col-md-8">
                                    <input type="hidden" name="id" value="${menu.id}"/>
                                    <input type="hidden" name="status" value="1"/>
                                    <input type="hidden" name="img" value='${menu.img}'/>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;系统名称</label>
                                        <div class="col-md-12">
                                            <input type="hidden" name="systemId" value="${system.id}"/>
                                            <input type="text" class="form-control" value="${system.name}" disabled/>
                                            <input type="hidden" class="form-control" readonly="readonly"
                                                   value="${system.name}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;父级菜单</label>
                                        <div class="col-md-12">
                                            <input type="hidden" name="pid" value="${pMenu.id}"/>
                                            <input type="text" class="form-control" value="${pMenu.name}" disabled/>
                                            <input type="hidden" class="form-control" readonly="readonly"
                                                   value="${pMenu.name}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;菜单名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" maxlength="50" name="name" data-error="请填写菜单名称" value="${menu.name}" required="required"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;唯一编码</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${menu.code}" disabled/>
                                            <input type="hidden" class="form-control" name="code" maxlength="50" data-error="请填写菜单唯一编码"  value="${menu.code}" required="required" readonly="readonly"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;菜单序号</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" maxlength="10" name="sno" data-error="请填写菜单序号" value="${menu.sno}" required="required"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;访问路径</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" maxlength="50" name="visitUrl" data-error="请填写菜单访问路径" value="${menu.visitUrl}" required="required"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;菜单图标&nbsp;&nbsp;
                                            <i class="icon-plus" style="color: #2cabe3" onclick="updateIcons('${dictionary.code}');"></i>
                                        </label>
                                        <div class="col-md-12">
                                            <div class="form-control" id="menuIcon">
                                                <i ${dictionary.value}></i><code>${dictionary.code}</code><span>${dictionary.name}</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">菜单描述</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="200" class="form-control"
                                                      rows="5">${menu.description}</textarea>
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
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="cancel()"  type="button">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="modal fade" id="updateIconsModal" tabindex="-1" role="dialog" aria-labelledby="updateIconsModal">
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
                <%@include file="/WEB-INF/views/include/right.jsp" %>
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
        if ($("input[name='name']").val() == '') {
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
                title: "菜单唯一编码仅支持大小写英文字母和数字",
                confirmButtonText: "确定",
            });
            $("input[name='code']").focus();
            return false;
        }
        if ($("input[name='visitUrl']").val() == '') {
            $("input[name='visitUrl']").focus();
            return false;
        }
        var reg= /^[\u2E80-\u9FFF]+$/ ; //Unicode编码中的汉字范围
        if (reg.test($("input[name='visitUrl']").val())) {
            swal({
                title: "路径不支持汉字",
                confirmButtonText: "确定",
            });
            $("input[name='visitUrl']").focus();
            return false;
        }
        if ($("input[name='sno']").val() == '') {
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

        var img = $("input[name='img']").val();
        if (isNull(img)) {
            swal({
                title: "请选择菜单图标",
                confirmButtonText: "确定",
            });
            $("input[name='img']").focus();
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
                // ajax提交
                $.ajax({
                    url: "${pageContext.request.contextPath}/menu/updatemenu",
                    type: "POST",
                    dataType: "json",
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/menu/getmenulist?systemId=${system.id}";
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

    // 修改菜单图标
    function updateIcons(code) {
        $("#updateIconsModal").modal({
            remote:'${pageContext.request.contextPath}/menu/modifyiconsmodal?code=${dictionary.code}',
        })
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
        }, function (isConfirm) {
            if (isConfirm) {
                location.href = "${pageContext.request.contextPath}/menu/getmenulist?systemId=${system.id}";
            }
        });
    }
</script>

</body>
</html>