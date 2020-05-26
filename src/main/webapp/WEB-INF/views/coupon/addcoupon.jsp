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
    <title>抵扣券添加</title>
</head>
<style>
    .ue-container {
        margin: 0 auto;
        margin-top: 3%;
        padding: 20px 40px;
        border: 1px solid #ddd;
        background: #fff;
    }
</style>
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
            <jsp:param value="/coupon/getcouponlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 抵扣券添加</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 营销活动</a></li>
                        <li class="active"> 抵扣券添加</li>
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
                                <form id="saveForm" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator">
                                    <input type="hidden" name="status" value="1"/>
                                    <%--<input type="hidden" name=""/>--%>
                                    <div class="form-group">
                                        <label class="col-md-12" id="name"><label style="color: #f05b4f">*</label>&nbsp;抵扣券名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="name" required="required" maxlength="50" placeholder="请输入抵扣券名称" data-error="请填写抵扣券名称" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <label style="right: 15px;" class="col-md-12" for="couponType"><label style="color: #f05b4f">*</label>&nbsp;类型</label>
                                    <div class="col-sm-4" style="width:40%;right:15px;margin-right:-70px;">
                                        <select class="selectpicker" id="couponType" name="couponType" style="width: 15px"
                                                onchange="changeUnit(this.options[this.options.selectedIndex].value)">
                                            <option value="1" selected>抵扣券</option>
                                            <option value="2">折扣券</option>
                                        </select>
                                    </div>

                                    <div class="input-group m-b-30" style="width: 25%;">
                                        <input type="text" class="form-control" placeholder="请输入值" onkeyup="checkNum()" maxlength="5" name="discount" aria-describedby="basic-addon2">
                                        <span class="input-group-addon" id="basic-addon2">元</span>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;数量</label>
                                        <div class="col-md-3" style="width: 20%">
                                            <input style="padding: 7px;" id="tch3_22" type="text" required="required" placeholder="请输入数量" value="1"
                                                   name="tch3_22" data-bts-button-down-class="btn btn-default btn-outline"
                                                   data-bts-button-up-class="btn btn-default btn-outline">
                                            <input name="amount" type="hidden"/>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>

                                    <label style="right: 15px;" class="col-md-12" for="couponType"><label style="color: #f05b4f">*</label>&nbsp;可兑换时间</label>
                                    <div class="col-sm-4" style="width:40%;right:15px;margin-right:-70px;">
                                        <select class="selectpicker" id="exTimType" style="width: 15px" name="exTimeType"
                                                onchange="changeExchangeType(this.options[this.options.selectedIndex].value)">
                                            <option value="1" selected>领取后</option>
                                            <option value="2">固定时间</option>
                                        </select>
                                    </div>

                                    <div class="input-group m-b-30" id="afterReceive" style="width: 25%;">
                                        <input type="text" class="form-control" maxlength="4"  placeholder="请输入天数"
                                               name="afterReceive" aria-describedby="basic-addon2" value="1">
                                        <span class="input-group-addon">天内有效</span>
                                    </div>

                                    <div class="input-group m-b-30" id="cutoffTime" style="width: 25%;display: none">
                                        <input type="text" class="form-control mydatepicker"
                                               name="cutoffTimeStr" placeholder="请选择日期"> <span class="input-group-addon">
                                        <i class="icon-calender"></i></span>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12" id="exchangeProduct"><label style="color: #f05b4f">*</label>&nbsp;可兑换商品</label>
                                        <div style="display: inline-flex;">
                                            <div class="radio radio-info" style="margin-left: 10px;">
                                                <input type="radio" name="product" id="ALL" value="ALL" onclick="hideProducatTable()" checked>
                                                <label for="ALL">全部商品</label>
                                            </div>
                                            <div class="radio radio-info" style="margin-left: 15px;">
                                                <input type="radio" name="product" id="partBarCodes" onclick="showProductTable()" value="">
                                                <label for="partBarCodes">部分商品
                                                </label>
                                            </div>
                                            <div class="radio radio-info" id="plus-icon" style="margin-top: -2px; margin-left: -13px; display: none;">
                                                <i class="mdi mdi-plus-circle-outline" onclick="showProductModal();" style="color: #2cabe3; margin-top: 100px"></i>
                                            </div>
                                            <input type="hidden" name="barCodes" value=""/>
                                        </div>
                                    </div>
                                    <table id="mainTable" class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>商品名称</th>
                                            <th>条形码</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="product_tbody">

                                        </tbody>
                                    </table>

                                    <div class="form-group">
                                        <input name="terminals" type="hidden"/>
                                        <label class="col-md-12" id="exchangeTerminal"><label style="color: #f05b4f">*</label>&nbsp;可兑换设备</label>
                                        <div style="display: inline-flex;width: 100%;">
                                            <div class="radio radio-info" style="padding-left: 35px;">
                                                <input type="radio" name="ternimal" id="machineAll" checked>
                                                <label for="machineAll">全部设备</label>
                                            </div>
                                            <div class="radio radio-info" style="margin-left: 15px;">
                                                <input type="radio" name="ternimal" id="machinePART">
                                                <label for="machinePART">部分设备</label>
                                            </div>
                                        </div>
                                        <div class="ue-container" id="machine" style="width:100%;display: none;">
                                            <select multiple="multiple" size="10" name="doublebox" class="machine" style="height: 250px;">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-lg-4"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase" type="submit" value="保存" >
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button type="button" class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>

                                <div class="modal fade" id="productmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">

                                        </div>
                                    </div>
                                </div>
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
<script>
    jQuery(document).ready(function() {
        // Switchery
        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        $('.js-switch').each(function() {
            new Switchery($(this)[0], $(this).data());
        });

        $("input[name='tch3_22']").TouchSpin({
            initval: 40,
            min: 1,
            max: 99999,
        });
        $("input[name='tch3_23']").TouchSpin({
            initval: 40,
            min: 1,
        });

        // Clock pickers
        $('#single-input').clockpicker({
            placement: 'bottom',
            align: 'left',
            autoclose: true,
            'default': 'now'
        });
        $('.clockpicker').clockpicker({
            donetext: 'Done',
        }).find('input').change(function() {
            console.log(this.value);
        });
        $('#check-minutes').click(function(e) {
            // Have to stop propagation here
            e.stopPropagation();
            input.clockpicker('show').clockpicker('toggleView', 'minutes');
        });

        // Colorpicker
        $(".colorpicker").asColorPicker();
        $(".complex-colorpicker").asColorPicker({
            mode: 'complex'
        });
        $(".gradient-colorpicker").asColorPicker({
            mode: 'gradient'
        });

        // Daterange picker
        $(".mydatepicker").datepicker({
            autoclose: true,
            todayHighlight: true,
            format: 'yyyy-mm-dd'
        });

        document.getElementById("mainTable").style.display="none";

        $("#machineAll").on("click", function() {
            $("#machine").hide();
        })
        $("#machinePART").on("click", function() {
            $("#machine").show();
        })

        $.ajax({
            url:'${pageContext.request.contextPath}/coupon/getmachinelist',
            type:'post',
            dataType:'json',
            success:function (data) {
                $('.machine').doublebox({
                    nonSelectedListLabel: '未选择设备',
                    selectedListLabel: '已选择设备',
                    filterPlaceHolder: '请输入设备名称',
                    moveSelectedLabel: '加入',
                    moveAllLabel: '全部加入',
                    removeSelectedLabel: '移除',
                    removeAllLabel: '全部移除',
                    preserveSelectionOnMove: 'moved',
                    moveOnSelect: false,
                    nonSelectedList:data.extInfo.unSelect,
//                    selectedList:
                    optionValue:"id",
                    optionText:"name",
                    doubleMove:true,
                });
            }
        })
    });
    $(".machine").change(function(){
        // 右边选中的数据
        var valStr = '';
        $("#bootstrap-duallistbox-selected-list_doublebox option").each(function () {
            var val = $(this).val(); //获取option的内容
            valStr += val+',';
        })
        if (valStr != null && valStr != '') {
            valStr = valStr.substring(0, valStr.length-1);
        }
        console.log(valStr);
        $("input[name='terminals']").val(valStr);
    });


</script>
<script type="text/javascript">
    function save() {
        var name = $("input[name='name']").val();
        if (name=='' || name==null){
            $("input[name='name']").focus();
            return false;
        }

        var reg = /^(([^0][0-9]+|0)\.([0-9]{1,2})$)|^(([^0][0-9]+|0)$)|^(([1-9]+)\.([0-9]{1,2})$)|^(([1-9]+)$)/;
        var discount = $("input[name='discount']").val();
        if (isNull(discount)) {
            swal({
                title: "请输入折扣券/抵扣券值",
                confirmButtonText: "确定",
            });
            $("input[name='discount']").focus();
            return false;
        }
        if (!reg.test(discount)) {
            swal({
                title: "折扣券/抵扣券值请输入有效数字",
                confirmButtonText: "确定",
            });
            return false;
        }

        var afterReceive = $("input[name='afterReceive']").val();
        var cutoffTimeStr = $("input[name='cutoffTimeStr']").val();
        if (isNull(afterReceive) && isNull(cutoffTimeStr)) {
            swal({
                title: "请输入可兑换时间",
                confirmButtonText: "确定",
            });
            return false;
        }

        var pattern = /^(\+)?\d+(\.\d+)?$/;
        if (!isNull(afterReceive) && !pattern.test(afterReceive)) {
            swal({
                title: "领取天数请输入数字",
                confirmButtonText: "确定",
            });
            return false;
        }

        var amount = $("input[name='tch3_22']").val();
        if (!pattern.test(amount)) {
            swal({
                title: "请输入数字",
                confirmButtonText: "确定",
            });
            return false;
        }
        $("input[name='amount']").val(amount);
        var allBarCode = $("#ALL").prop("checked");
        if (allBarCode){
            $("input[name='barCodes']").val("ALL");
        }

        var parBarCode = $("#partBarCodes").prop("checked");
        if (parBarCode){
            var barCodes = '';
            $("#product_tbody").children().each(function () {
                var barCode = $(this).children("td")[1].innerText;
                barCodes += barCode+',';
            });

            if (isNull(barCodes)){
                swal({
                    title: "请选择商品",
                    confirmButtonText: "确定"
                });
                return false;
            }else {
                barCodes = barCodes.substring(0,barCodes.length-1);
                $("input[name='barCodes']").val(barCodes);
            }
        }


        var machineAll = $("#machineAll").prop("checked");
        if (machineAll){
            $("input[name='terminals']").val("ALL");
        }

        var machinePART = $("#machinePART").prop("checked");
        if (machinePART){
            var terminals = $("input[name='terminals']").val();
            console.log(terminals);
            if (isNull(terminals)){
                swal({
                    title: "请选择设备",
                    confirmButtonText: "确定"
                });
                return false;
            }
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
                    url: "${pageContext.request.contextPath}/coupon/insertcoupon",
                    type: "POST",
                    dataType: "json",
                    data: $("#saveForm").serialize(),
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/coupon/getcouponlist";
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

    function changeUnit(val) {
        if ('1' === val){
            document.getElementById("basic-addon2").innerHTML='元';
        }
        if ('2' === val){
            document.getElementById("basic-addon2").innerHTML='折';
        }
    }

    function changeExchangeType(val) {
        if ('1' === val){
            document.getElementById("afterReceive").style.display = '';
            document.getElementById("cutoffTime").style.display = 'none';
        }
        if ('2' === val){
            document.getElementById("cutoffTime").style.display = '';
            document.getElementById("afterReceive").style.display = 'none';
        }
    }

    $("#partBarCodes").on("click", function(){
        $("#productmodal").modal({
            remote: "${pageContext.request.contextPath}/coupon/getproductlist"
        });
        $("#addbtn").show();
    });

    //删除table中的某一行
    function deleteRow(obj) {
        console.log(obj.parentNode.parentNode);
        var tr = obj.parentNode.parentNode;
        $(tr).remove();
    }

    function showProductTable() {
        $("input[name='barCodes']").val('');
        document.getElementById("mainTable").style.display="";
        showProductModal();
        document.getElementById("plus-icon").style.display="";
    }

    function hideProducatTable() {
        $("input[name='barCodes']").val('ALL');
        document.getElementById("mainTable").style.display="none";
        document.getElementById("plus-icon").style.display="none";
    }

    $('#productmodal').on('hide.bs.modal', function () {
        $(this).removeData('bs.modal');
    });


    function checkNum() {
        var value = $("input[name='discount']").val();
        if (Number(value) <= 0){
            $("input[name='discount']").val("");
        }
    }

    function showProductModal() {
        $("#productmodal").modal({
            remote: "${pageContext.request.contextPath}/coupon/getproductlist"
        });
    }

    $('#addProduct').on('click', function (data) {
        $("#productmodal").modal({
            remote: "${pageContext.request.contextPath}/coupon/getproductlist"
        });
    });


</script>

</body>
</html>