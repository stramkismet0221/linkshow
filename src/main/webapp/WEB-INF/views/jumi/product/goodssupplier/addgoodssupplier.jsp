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
    <title>新建供货商</title>
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
            <jsp:param value="/jumi/product/goodssupplier/list" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 新建供货商</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 供货商管理</a></li>
                        <li class="active"> 新建供货商</li>
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
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator">
                                    <input type="hidden"  name="logo" id="logo"/>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;供货商名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="name" onkeyup="btnonkeyupspace(this, value);" maxlength="120" required="required" placeholder="请输入供货商名称" data-error="请输入供货商名称" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-md-12">&nbsp;状态</label>--%>
                                        <%--<div class="radio-list">--%>
                                            <%--<label class="radio-inline" style="margin-left: 15px;">--%>
                                                <%--<input type="radio" name="states" value="0">禁用</label>--%>
                                            <%--<label class="radio-inline">--%>
                                                <%--<input type="radio" name="states" value="1" checked="">启用</label>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;状态</label>
                                        <div class="radio radio-info" style="display: inline-block; margin-left: 15px; margin-right: 5%;">
                                            <input type="radio" name="states" id="radio1" value="0">
                                            <label for="radio1"> 禁用 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="states" id="radio2" value="1" checked="">
                                            <label for="radio2"> 启用 </label>
                                        </div>
                                    </div>












                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;联系人</label>
                                        <div class="col-md-12">
                                            <input type="text" name="contacts" maxlength="50" required="required" placeholder="请输入联系人" data-error="请输入联系人" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;联系电话</label>
                                        <div class="col-md-12">
                                            <input type="text" name="telephone" maxlength="11" required="required" onkeyup="btnonkeyup(this,value);" placeholder="请输入联系电话" data-error="请输入联系电话" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;邮箱</label>
                                        <div class="col-md-12">
                                            <input type="text" name="email" maxlength="24"  placeholder="请输入邮箱" data-error="请输入邮箱" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;经营模式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="mngmodel" maxlength="50"  placeholder="请输入经营模式" data-error="请输入经营模式" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;结款方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="paymentType" maxlength="50"  placeholder="请输入结款方式" data-error="请输入结款方式" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;送货方式</label>
                                        <div class="col-md-12">
                                            <input type="text" name="deliveryType" maxlength="50"  placeholder="请输入送货方式" data-error="请输入送货方式" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp; 配送费</label>
                                        <div class="col-md-12">
                                            <input type="text" name="deliveryMoney" maxlength="10" onkeyup="btnonkeyupDiscount(this, value);"  placeholder="请输入配送费" data-error="请输入配送费" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;固定返点</label>
                                        <div class="input-group col-md-12" style="padding-left: 15px;">
                                            <input type="text" name="returnPoint" class="form-control" placeholder="请输入固定返点"  data-error="请输入固定返点" required="required"  maxlength="8" onkeyup="btnonkeyupDiscount(this, value);" name="returnPoint" aria-describedby="basic-addon2">
                                            <span class="input-group-addon" id="basic-addon1">%</span>
                                        </div>
                                        <div class="help-block with-errors" style="padding-left:13px;"></div>
                                    </div>
                                    <div class="form-group">
                                    <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;配送返点</label>
                                        <div class="input-group" style="padding-left: 15px;">
                                            <input type="text" name="deliveryPoint" class="form-control" placeholder="请输入配送返点" onkeyup="btnonkeyupDiscount(this, value);"   data-error="请输入配送返点" required="required"  maxlength="8" name="discount" aria-describedby="basic-addon2">
                                            <span class="input-group-addon" id="basic-addon2">%</span>
                                        </div>
                                        <div class="help-block with-errors" style="padding-left:13px;"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;发票税点</label>
                                        <div class="input-group col-md-12" style="padding-left: 15px;">
                                            <input type="text" name="taxPoint" class="form-control" placeholder="请输入发票税点" data-error="请输入发票税点"   maxlength="8" onkeyup="btnonkeyupDiscount(this, value);" name="taxPoint" aria-describedby="basic-addon2">
                                            <span class="input-group-addon" id="basic-addon3">%</span>
                                        </div>
                                        <div class="help-block with-errors" style="padding-left:13px;"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">结算银行账户信息</label>
                                        <div class="col-md-12">
                                            <textarea name="bankInfo" maxlength="499" class="form-control" rows="5" placeholder="请输入结算银行账户信息" ></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">开票信息</label>
                                        <div class="col-md-12">
                                            <textarea name="invoiceInfo" maxlength="499" class="form-control" rows="5" placeholder="请输入开票信息" ></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">联系地址</label>
                                        <div class="col-md-12">
                                            <textarea name="contactAddress" maxlength="499" class="form-control" rows="5" placeholder="请输入联系地址" ></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">其他备注</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="240" class="form-control" rows="5" placeholder="请输入其他备注" ></textarea>
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
                                            <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
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
    //只能输入数字
    function btnonkeyup(obj, value) {
        value = value.replace(/\D/g, '');
        $(obj).val(value);
    }
    function btnonkeyupspace(obj, value) {
        value = value.replace(/ /g, '');
        $(obj).val(value);
    }
    //只能输入两位小数
    function btnonkeyupDiscount(dom, value) {
        if (dom.value) {
            var val = dom.value.replace(/[^\d\.]/g, "");
            if (dom.value.indexOf(".") != -1) {
                if (dom.value.indexOf(".") == 0) {
                    dom.value = 0 + dom.value;
                }
                val = dom.value.match(/\d+\.\d{0,2}/g)[0];
            }else{
                val=dom.value.match(/\d{0,7}/)[0];
            }
            $(dom).val(val);
        }
    }

    function save() {
        // 校验
        if ($("input[name='name']").val() === '') {
            $("input[name='name']").focus();
            return false;
        }
        var name = $("input[name='name']").val();
        if ($("input[name='contacts']").val() === '') {
            $("input[name='contacts']").focus();
            return false;
        }
        if ($("input[name='returnPoint']").val() === '') {
            $("input[name='returnPoint']").focus();
            return false;
        }
        if ($("input[name='deliveryPoint']").val() === '') {
            $("input[name='deliveryPoint']").focus();
            return false;
        }

        if ($("input[name='telephone']").val() === '') {
            $("input[name='telephone']").focus();
            return false;
        }
        var phone=$("input[name='telephone']").val();
        if(!/^((\+?86)|(\+86))?1[0-9]{10}$/.test(phone)){
            swal({
                title: "请输入正确的联系电话",
                confirmButtonText: "确定"
            }, function (isConfirm) {
            });
            $("input[name='telephone']").focus();
            return false;
        }
        var email=$("input[name='email']").val();
        if(email !=""){
            if(!/^([-_a-zA-Z0-9])+@([-_a-zA-Z0-9])+(\.[a-zA-Z]{2,5}){1,3}$/.test(email)){
                swal({
                    title: "请输入正确的邮箱",
                    confirmButtonText: "确定"
                }, function (isConfirm) {

                });
                $("input[name='email']").focus();
                return false;
            }
        }
        if($("input[name='returnPoint']").val() !==''){
            var deliverypoint = $("input[name='returnPoint']").val();
            var pattern = /(^(\d|[1-9]\d)(\.\d{1,2})?$)|(^100.00$)|(^100.0$)|(^100$)/;
            if(!pattern.test(deliverypoint)){
                swal({
                    title: "请输入正确的固定返点",
                    confirmButtonText: "确定"
                }, function (isConfirm) {

                });
                return false;
            }
        }
        if($("input[name='deliveryPoint']").val() !==''){
            var deliverypoint = $("input[name='deliveryPoint']").val();
            var pattern = /(^(\d|[1-9]\d)(\.\d{1,2})?$)|(^100.00$)|(^100.0$)|(^100$)/;
            if(!pattern.test(deliverypoint)){
                swal({
                    title: "请输入正确的配送返点",
                    confirmButtonText: "确定"
                }, function (isConfirm) {

                });
                return false;
            }
        }
        if($("input[name='taxPoint']").val() !==''){
            var deliverypoint = $("input[name='taxPoint']").val();
            var pattern = /(^(\d|[1-9]\d)(\.\d{1,2})?$)|(^100.00$)|(^100.0$)|(^100$)/;
            if(!pattern.test(deliverypoint)){
                swal({
                    title: "请输入正确的发票税点",
                    confirmButtonText: "确定"
                }, function (isConfirm) {

                });
                return false;
            }
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/jumi/product/goodssupplier/checkgoodssuppliername",
            type: "POST",
            dataType: "json",
            async: true,
            data: {name:name,id:null},
            success: function (result) {
                if (result.success) {
                    swal({
                        title: "该供货商名称已经存在",
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
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function (isConfirm) {
            if (isConfirm) {
                // ajax提交
                $.ajax({
                    url: "${pageContext.request.contextPath}/jumi/product/goodssupplier/addgoodssupplier",
                    type: "POST",
                    dataType: "json",
                    async: false,
                    data: $("#form").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function (isConfirm) {
                                if (isConfirm) {
                                    location.href = "${pageContext.request.contextPath}/jumi/product/goodssupplier/list";
                                }
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
</script>
</body>
</html>