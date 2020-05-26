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
    <title>预警设置</title>
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
            <jsp:param value="/jumi/drp/warnset/addwarnset" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title"> 预警设置</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#"> 进销存</a></li>
                        <li class="active"> 预警设置</li>
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
                                    <input type="hidden" name="id" value="${warnSet.id}" />
                                    <%-- 所选商品 --%>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;所选商品</label>
                                        <div class="radio radio-info" style="display: inline-block; margin-left: 2%; margin-right: 5%;">
                                            <input type="radio" name="selType" id="radio1" value="1" ${warnSet.id==null||warnSet.selType==1?"checked":""}>
                                            <label for="radio1"> 全部 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="selType" id="radio2" value="2" ${warnSet.selType==2?"checked":""}>
                                            <label for="radio2"> 部分分类 &nbsp;&nbsp;&nbsp;&nbsp;<a onclick="selectGoodsType();">选择分类</a></label>
                                            <input type="hidden" name="goodsTypeIds" value="${warnSet.goodsTypeIds}"/>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="selType" id="radio3" value="3" ${warnSet.selType==3?"checked":""}>
                                            <label for="radio3"> 部分商品 &nbsp;&nbsp;&nbsp;&nbsp;<a onclick="selectGoods();">选择商品</a></label>
                                            <input type="hidden" name="goodsIds" value="${warnSet.goodsIds}"/>
                                            <input type="hidden" name="goodsNames" value=""/>
                                        </div>
                                    </div>
                                    <%-- 已选 --%>
                                    <div class="form-group">
                                        <label class="col-md-12">已选</label>
                                        <div class="col-md-12">
                                            <c:if test="${warnSet.id==null||warnSet.selType==1}">
                                                <input type="text" id="selectedGoods" value="全部" title="全部" class="form-control" disabled/>
                                            </c:if>
                                            <c:if test="${warnSet.selType==2}">
                                                <input type="text" id="selectedGoods" value="${getGoodsTypeNames.length()>60?getGoodsTypeNames.substring(0,60).concat('...'):getGoodsTypeNames}" title="${getGoodsTypeNames}" class="form-control" disabled/>
                                            </c:if>
                                            <c:if test="${warnSet.selType==3}">
                                                <input type="text" id="selectedGoods" value="${goodsNames.length()>60?goodsNames.substring(0,60).concat('...'):goodsNames}" title="${goodsNames}" class="form-control" disabled/>
                                            </c:if>
                                        </div>
                                    </div>
                                    <%-- 临期预警__天 --%>
                                    <div class="form-group">
                                        <label class="col-md-12">临期预警（天）</label>
                                        <div class="input-group col-md-12" style="padding-left: 15px;">
                                            <input type="text" class="form-control" name="beforeWarnDay" value="${warnSet.beforeWarnDay}" placeholder="请输入临期预警天数" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'')" aria-describedby="basic-addon2">
                                            <span class="input-group-addon" id="basic-addon1">天</span>
                                        </div>
                                    </div>
                                    <%-- 预警类型 --%>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;预警类型</label>
                                        <div class="radio radio-info" style="display: inline-block; margin-left: 2%; margin-right: 5%;">
                                            <input type="radio" name="warnType" id="radio4" value="1" ${warnSet.id==null||warnSet.warnType==1?"checked":""}>
                                            <label for="radio4"> 统一预警 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="warnType" id="radio5" value="2" ${warnSet.warnType==2?"checked":""}>
                                            <label for="radio5"> 分仓预警 </label>
                                        </div>
                                    </div>

                                    <div id="warnType">
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;库存下线预警数</label>
                                        <div class="col-md-12">
                                            <input type="text" name="minStock" value="${warnSet.warnType==1?warnSet.minStock:''}" required="required" placeholder="请输入库存下线预警数" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'')" data-error="请输入库存下线预警数" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;库存上线预警数</label>
                                        <div class="col-md-12">
                                            <input type="text" name="maxStock" value="${warnSet.warnType==1?warnSet.maxStock:''}" required="required" placeholder="请输入库存上线预警数" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'')" data-error="请输入库存上线预警数" class="form-control" />
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                    </div>

                                    <div class="row" >
                                        <div class="col-lg-5"></div>
                                        <div class="col-lg-2 col-sm-4 col-xs-12">
                                            <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="submit"  >
                                                <span style="vertical-align: inherit;">保存</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <%-- 商品类型modal --%>
                                <div class="modal fade" id="goodsTypeModal" tabindex="-1" role="dialog" aria-labelledby="goodsTypeModal">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content" style="width: 600px; left: 180px;">

                                        </div>
                                    </div>
                                </div>
                                <%-- 商品modal --%>
                                <div class="modal fade" id="goodsModal" tabindex="-1" role="dialog" aria-labelledby="goodsModal">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content" style="width: 1000px; left: -20px;">

                                        </div>
                                    </div>
                                </div>
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

<script type="text/javascript">

    var unifyWarnHtml;
    $(function(){
        unifyWarnHtml = $("#warnType").html();

        var warnType = $("input[name='warnType']:checked").val();
        var warnSetId = $("input[name='id']").val();
        if (warnType == 2) {
            separateWarn(warnSetId);
        }
    })

    // 获取商品类型选择方式的radio点击事件
    $("input[name='selType']").on("click", function () {
        var value = $("input[name='selType']:checked").val();
        if (value == 1) {
            selectAllGoods();
        }
        if (value == 2) {
            selectGoodsType();
        }
        if (value == 3) {
            selectGoods();
        }
    })

    // 全部商品
    function selectAllGoods() {
        $("#selectedGoods").val('全部');
    }

    // 选择商品分类
    function selectGoodsType() {
        $("#goodsTypeModal").modal({
            remote:'${pageContext.request.contextPath}/jumi/drp/warnset/getgoodstypes',
        })
    }

    // 选择商品
    function selectGoods() {
        $("#goodsModal").modal({
            remote:'${pageContext.request.contextPath}/jumi/common/getgoodslist',
        })
    }

    // 预警类型：统一预警、分仓预警
    $("input[name='warnType']").on("click", function () {
        var value = $("input[name='warnType']:checked").val();
        var id = $("input[name='id']").val();
        if (value == 1) {
            unifyWarn();
        }
        if (value == 2) {
            separateWarn(id);
        }
    })

    // 统一预警
    function unifyWarn() {
        $("#warnType").html(unifyWarnHtml);
    }

    // 分仓预警
    function separateWarn(warnSetId) {
        $.ajax({
            type: "POST",
            async: false,
            url: "${pageContext.request.contextPath}/jumi/drp/warnset/getwarehouses?warnSetId="+warnSetId,
            dataType: "html",
            success: function (result) {
                $("#warnType").html(result);
            }
        })
    }

    // 保存
    function save() {

        if ($("#selectedGoods").val() == '' || $("#selectedGoods").val() == null) {
            swal({
                title: "请选择商品",
                confirmButtonText: "确定"
            }, function (isConfirm) {

            });
            return false;
        }

        var minStockFlag = false;
        var maxStockFlag = false;
        $("input[name='minStock']").each(function(){
            if ($(this).val() == '' || $(this).val() == null) {
                minStockFlag = true;
                return false;
            }
        })
        $("input[name='maxStock']").each(function(){
            if ($(this).val() == '' || $(this).val() == null) {
                maxStockFlag = true;
                return false;
            }
        })
        if (minStockFlag) {
            return;
        }
        if (maxStockFlag) {
            return;
        }

        var minMap = new Map();
        var wareWarnMap = new Map();
        var warnType = $("input[name='warnType']:checked").val();
        if (warnType == 2) {
            $("input[name='minStock']").each(function(){
                var wareId = $(this).attr("wareid");
                var wareName = $(this).attr("warename");
                var key = wareId+'-'+wareName;
                minMap.set(key, $(this).val());
            })
            $("input[name='maxStock']").each(function(){
                var wareId = $(this).attr("wareid");
                var wareName = $(this).attr("warename");
                var key = wareId+'-'+wareName;
                wareWarnMap.set(key, minMap.get(key)+'-'+$(this).val());
            })
        }

        var wareParams='';
        wareWarnMap.forEach(function(value,key){
            wareParams += key+','+value+';';
        });
        wareParams=(wareParams.substring(wareParams.length-1)==';')?wareParams.substring(0,wareParams.length-1):wareParams;


        // 表单数据
        var id = $("input[name='id']").val();
        var selType = $("input[name='selType']:checked").val();
        var goodsTypeIds = $("input[name='goodsTypeIds']").val();
        var goodsIds = $("input[name='goodsIds']").val();
        var beforeWarnDay = $("input[name='beforeWarnDay']").val();
        var minStock;
        var maxStock;
        if (warnType != 2) {
            minStock = $("input[name='minStock']").val();
            maxStock = $("input[name='maxStock']").val();
        }

        var data = {
            id: id,
            selType: selType,
            goodsTypeIds: goodsTypeIds,
            goodsIds: goodsIds,
            beforeWarnDay: beforeWarnDay,
            warnType: warnType,
            minStock: minStock,
            maxStock: maxStock,
            wareParams: wareParams
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
                    url: "${pageContext.request.contextPath}/jumi/drp/warnset/savewarnset",
                    type: "POST",
                    dataType: "json",
                    async: false,
                    data: data,
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function (isConfirm) {
                                if (isConfirm) {
                                    location.href = "${pageContext.request.contextPath}/jumi/drp/warnset/addwarnset";
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