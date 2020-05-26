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
    <title>兑换码修改</title>
    <base target="_self"/>
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
            <jsp:param value="/excode/toexcodelist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 兑换码修改</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 营销活动</a></li>
                        <li class="active"> 兑换码修改</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <input name="exCodeId" value="${exCodeId}" type="hidden">
                <div class="col-md-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" onsubmit="save();return false;" class="form-horizontal form-material" data-toggle="validator" novalidate="true">
                                    <input type="hidden" name="excodeId" value="${exCode.id}"/>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;活动名称</label>
                                        <div class="col-md-12">
                                            <input type="text" name="actName" value="${exCode.actName}" required="required" maxlength="50" placeholder="请输入活动名称" data-error="请填写活动名称" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-right: 0;margin-left: 0;">
                                        <label style="right: 15px;" class="col-md-12" ><label style="color: #f05b4f">*</label>&nbsp;可兑换时间</label>
                                        <div style="right: 15px;margin-right: -70px;" class="col-sm-4">
                                            <select class="selectpicker" disabled onchange="changeExchangeType(this.options[this.options.selectedIndex].value)" id="expireType">
                                                <option <c:if test="${exCode.expireType == 1}"> selected </c:if> value="1" >领取后</option>
                                                <option <c:if test="${exCode.expireType == 2}"> selected </c:if> value="2" >固定时间</option>
                                            </select>
                                        </div>
                                        <c:if test="${exCode.expireType == 1}">
                                            <div class="col-md-3"  id="afterReceive" style="display: block;">
                                                <div class="input-group m-b-30" style="width: 310px;">
                                                    <input type="text" class="form-control" disabled placeholder="请输入天数" style="width: 200px;"
                                                           name="expireDays" aria-describedby="basic-addon2" value="${exCode.expireDays}"><span class="input-group-addon" id="basic-addon2" style="padding-left: 5px">天后过期</span>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${exCode.expireType == 2}"> <div class="col-md-3" id="cutoffTime" style="display: block;margin-bottom: 40px;">
                                            <div class="input-group">

                                                <input type="text" disabled name="expireTimeStr" class="form-control" id="datepicker-autoclose" value="<fmt:formatDate value="${exCode.expireTime}" pattern="yyyy-MM-dd"/>"  placeholder="选择日期">
                                            </div>
                                        </div>
                                        </c:if>
                                    </div>

                                    <div class="form-group">
                                        <input name="machineIds" type="hidden" value="">
                                        <label class="col-md-12" id="exchangeTerminal"><label style="color: #f05b4f">*</label>&nbsp;可兑换设备</label>
                                        <div style="display: inline-flex;">
                                            <div class="radio radio-info" style="margin-left: 10px;">
                                                <input type="radio" name="ternimal" id="machineAll" value="1" <c:if test="${exCode.machineType.equals('1')}"> checked </c:if>>
                                                <label for="machineAll">全部设备</label>
                                            </div>
                                            <div class="radio radio-info" style="margin-left: 15px;">
                                                <input type="radio" name="ternimal" id="machinePART" value="2" <c:if test="${exCode.machineType.equals('2')}"> checked </c:if>>
                                                <label for="machinePART">部分设备</label>
                                            </div>
                                        </div>
                                        <div class="col-md-12 machineTable"  style="margin-top: 22px;display:
                                        <c:if test="${exCode.machineType.equals('2')}"> block</c:if>
                                        <c:if test="${exCode.machineType.equals('1')}"> none;</c:if> ">
                                            <button type="button" class="btn btn-info"
                                                    data-toggle="modal"
                                                    onclick="openMachineModal()"
                                                    data-whatever="@fat">增加</button>

                                            </button>
                                            <table class="table table-hover manage-u-table" style="white-space: nowrap" id="machineTable">
                                                <thead>
                                                <tr>
                                                    <th width="1"></th>
                                                    <th>设备名称</th>
                                                    <th>设备编号</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="machine_tbody">
                                                    <c:forEach items="${exCode.terminals}" var="data">
                                                        <tr>
                                                            <td><input type="hidden" name="machineId" value="${data.id}"></td>
                                                            <td>${data.name}</td>
                                                            <td>${data.deviceCode}</td>
                                                            <td><button onclick='deleteRow(this)' disabled class="btn btn-info btn-outline btn-circle btn-lg m-r-5" style="height: 20px;padding: 0;">
                                                                <i class="ti-trash"></i>
                                                                </button >
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12" ><label style="color: #f05b4f">*</label>&nbsp;商品列表</label>
                                        <input name="barCodes" type="hidden" value="">
                                        <div class="col-md-12">
                                            <button type="button" class="btn btn-info"
                                                    data-toggle="modal"
                                                    onclick="openProductModal()"
                                                    data-whatever="@fat1">增加</button>

                                            </button>
                                            <table id="mainTable" class="table table-hover manage-u-table" style="white-space: nowrap">
                                                <thead>
                                                <tr>
                                                    <th>商品名称</th>
                                                    <th>条形码</th>
                                                    <th>数量</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="product_tbody">
                                                <c:forEach items="${exCode.products}" var="data" varStatus="index" begin="0">
                                                    <tr>
                                                        <td>${data.productName}</td>
                                                        <td>${data.barcode}</td>
                                                        <td><div style='margin: -9px;'>
                                                            <input type="number" required value="${data.amount}" onkeyup="checkNum(${data.amount},${index.begin})" min='${data.amount}'
                                                                   oninput="if(value.length>=6)value=value.slice(0,6)" max='999999'
                                                                   style='padding-left: 10px;' id="${index.begin}"  name="amount" class="form-control" placeholder="请输入数量">
                                                            </div>
                                                        </td>
                                                        <td><button onclick='deleteRow(this)' disabled class="btn btn-info btn-outline btn-circle btn-lg m-r-5" style="height: 20px;padding: 0;">
                                                            <i class="ti-trash"></i>
                                                        </button >
                                                        </td>
                                                        <td hidden>${data.id}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
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
                                            <button type="button" class="btn btn-block btn-primary text-uppercase" onclick="location.href='${pageContext.request.contextPath}/excode/toexcodelist?type=1'">
                                                <span style="vertical-align: inherit;">返回</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>

                                <div class="modal fade" id="machineModal" tabindex="-1" role="dialog" aria-labelledby="machineModal">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">

                                        </div>
                                    </div>
                                </div>

                                <div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="productModal">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">

                                        </div>
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
<script>
    jQuery(document).ready(function() {
        // Colorpicker
        $(".colorpicker").asColorPicker();
        $(".complex-colorpicker").asColorPicker({
            mode: 'complex'
        });
        $(".gradient-colorpicker").asColorPicker({
            mode: 'gradient'
        });
        jQuery('#datepicker-autoclose').datepicker({
            autoclose: true,
            todayHighlight: true
        });
    })
</script>

<script type="text/javascript">
    function save() {

        var actName = $("input[name='actName']").val();
        if (isNull(actName)){
            $("input[name='actName']").focus();
            return false;
        }

        var allMachine = $("#machineAll").prop("checked");
        var partMachine = $("#machinePART").prop("checked");
        var machineType = "";
        var machines = "";
        if (allMachine){
            machineType = '1';
        }
        if (partMachine){
            $("#machine_tbody").children().each(function () {
                var machineId = $(this).children("td").children("input[name='machineId']").val();
                machines += machineId+",";
            });
            machineType = '2';
            if (isNull(machines)){
                swal({
                    title: "请选择兑换码适用设备",
                    confirmButtonText: "确定",
                });
                return false;
            }
        }

        if (machines.endsWith(",")){
            machines = machines.substr(0,machines.lastIndexOf(","));
            console.log(machines);
        }

        var pattern = /^[0-9]*$/;
        var isNotNumber;
        var amountIsNull = false;
        var exCodeId = $("input[name='exCodeId']").val();
        var products = [];
        $("#product_tbody").children().each(function () {
            var productName = $(this).children("td")[0].innerText;
            var barcode = $(this).children("td")[1].innerText;
            // var exProId = $(this).children("td")[4].innerText;
            var amount = $(this).children("td").find("input[name='amount']").val();
            if (isNull(amount)){
                amountIsNull = true;
                return false;
            }
            if (!pattern.test(amount)){
                isNotNumber = true;
                return false;
            }
            products.push({"exCodeId":exCodeId,"productName":productName,"barcode":barcode,"amount":amount})
        });

        if (amountIsNull){
            swal({
                title: "请填写数量",
                confirmButtonText: "确定",
            });
            return false;
        }

        if (isNotNumber){
            swal({
                title: "数量限填写正整数",
                confirmButtonText: "确定",
            });
            return false;
        }
        if (isNull(products)){
            swal({
                title: "请选择兑换码适用商品",
                confirmButtonText: "确定",
            });
            return false;
        }
        var data = {
            "id":$("input[name='excodeId']").val(),
            "actName":actName,
            "machines":machines,
            "type":1,
            "exCodeProducts":JSON.stringify(products),
            "machineType":machineType
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
                $.ajax({
                    url: "${pageContext.request.contextPath}/excode/modifyexcode",
                    type: "POST",
                    dataType: "json",
                    traditional:true,
                    data: data,
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/excode/toexcodelist";
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

    //选择过期事件类型
    /*function changeExchangeType(val) {
        if ('1' === val){
            document.getElementById("afterReceive").style.display = 'block';
            document.getElementById("cutoffTime").style.display = 'none';
        }
        if ('2' === val){
            document.getElementById("cutoffTime").style.display = 'block';
            document.getElementById("afterReceive").style.display = 'none';
        }
    }*/

    //modal 调用接口
    function openMachineModal() {
        $("#machineModal").modal({
            remote:'${pageContext.request.contextPath}/excode/getmachinelist',
        })
    }

    //modal 调用接口
    function openProductModal() {
        $("#productModal").modal({
            remote:'${pageContext.request.contextPath}/excode/getproductlist',
        })
    }

    //删除table中的某一行
    function deleteRow(obj) {
        console.log(obj.parentNode.parentNode);
        var tr = obj.parentNode.parentNode;
        $(tr).remove();
    }

    function checkNum(val,i) {
        var value = $("#"+i).val();
        if (Number(value) <= Number(val)){
            $("#"+i).val(val);
        }
    }


    $('#machineModal').on('hide.bs.modal', function () {
        $(this).removeData('bs.modal');
    });

    $('#productModal').on('hide.bs.modal', function () {
        $(this).removeData('bs.modal');
    });

    $("#machinePART").on('click',function (data) {
        $(".machineTable").show();
    });

    $("#machineAll").on('click',function (data) {
        $(".machineTable").hide();
    });
</script>
</body>
</html>