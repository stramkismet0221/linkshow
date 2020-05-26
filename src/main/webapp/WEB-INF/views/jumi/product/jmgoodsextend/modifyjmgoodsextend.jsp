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
    <title>自定义字段修改</title>
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
            <jsp:param value="/jumi/product/goodsextend/getlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">自定义字段修改</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品管理</a></li>
                        <li class="active">自定义字段修改</li>
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
                                    <form id="form" class="form-material form-horizontal" onsubmit="update();return false;" data-toggle="validator" novalidate="true">
                                        <div class="form-group">
                                            <label class="col-md-12">&nbsp;字段ID</label>
                                            <div class="col-md-12">
                                                <input type="text" name="id" class="form-control" value="${jmGoodsExtend.id}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;字段名称</label>
                                            <div class="col-md-12">
                                                <input type="text" name="name" required="required" maxlength="50" placeholder="请输入字段名称" data-error="请输入字段名称" class="form-control" value="${jmGoodsExtend.name}"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="radio-list">
                                                <label class="radio-inline">
                                                    <div class="">
                                                        <label>是否必填</label>
                                                    </div>
                                                </label>
                                                <label class="radio-inline">
                                                    <div class="radio radio-info">
                                                        <input type="radio" name="isNotNull" id="r1" value="1" <c:if test="${jmGoodsExtend.isNotNull == 1}">checked </c:if>  />
                                                        <label for="r1">是</label>
                                                    </div>
                                                </label>
                                                <label class="radio-inline">
                                                    <div class="radio radio-info">
                                                        <input type="radio" name="isNotNull" id="r2" value="0" <c:if test="${jmGoodsExtend.isNotNull == 0}">checked </c:if> />
                                                        <label for="r2">否</label>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="radio-list">
                                                <label class="radio-inline ">
                                                    <div class="">
                                                        <label>应用商品类型</label>
                                                    </div>
                                                </label>
                                                <label class="radio-inline">
                                                    <div class="radio radio-info">
                                                        <input type="radio" name="goodsType" id="g1" value="1" <c:if test="${jmGoodsExtend.goodsType == 1}">checked </c:if> />
                                                        <label for="g1">实物</label>
                                                    </div>
                                                </label>
                                                <label class="radio-inline">
                                                    <div class="radio radio-info">
                                                        <input type="radio" name="goodsType" id="g2" value="2" <c:if test="${jmGoodsExtend.goodsType == 2}">checked </c:if> />
                                                        <label for="g2">虚拟 </label>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">&nbsp;字段类型</label>
                                            <div class="col-sm-4">
                                                <select class="form-control" name="fieldType" id="fieldType" onchange="changeExchangeType(this.options[this.options.selectedIndex].value)">
                                                    <option value="1" <c:if test="${jmGoodsExtend.fieldType == 1}">selected </c:if>>单行文本框</option>
                                                    <option value="2" <c:if test="${jmGoodsExtend.fieldType == 2}">selected </c:if>>下拉选项</option>
                                                    <option value="3" <c:if test="${jmGoodsExtend.fieldType == 3}">selected </c:if>>单选</option>
                                                    <option value="4" <c:if test="${jmGoodsExtend.fieldType == 4}">selected </c:if>>多选</option>
                                                    <option value="5" <c:if test="${jmGoodsExtend.fieldType == 5}">selected </c:if>>日历</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group"
                                                <c:if test="${jmGoodsExtend.fieldType == 2}">
                                                    id="selectiondiv"
                                                </c:if>
                                                <c:if test="${jmGoodsExtend.fieldType == 3}">
                                                    id="radiodiv"
                                                </c:if>
                                                <c:if test="${jmGoodsExtend.fieldType == 4}">
                                                    id="checkboxdiv"
                                                </c:if>
                                        >
                                            <div class="col-md-12">
                                                <div class="col-md-8 m-b-30">
                                                    <button type="button" class="btn btn-info"
                                                            style="margin-top: 10px;
                                                    margin-bottom: 10px;"
                                                            data-toggle="modal"
                                                            <c:if test="${jmGoodsExtend.fieldType == 2}">
                                                                onclick="addSelection(1)"
                                                            </c:if>
                                                            <c:if test="${jmGoodsExtend.fieldType == 3}">
                                                                onclick="addSelection(2)"
                                                            </c:if>
                                                            <c:if test="${jmGoodsExtend.fieldType == 4}">
                                                                onclick="addSelection(3)"
                                                            </c:if>
                                                            data-whatever="@fat">增加选项</button>
                                                    </button>
                                                </div>
                                            </div>
                                                <c:forEach items="${jmGoodsExtend.items}" var="ex">
                                                <div class="col-md-8 m-b-30" style='display: inline-flex'>
                                                    <input type="text"
                                                            <c:if test="${jmGoodsExtend.fieldType == 2}">
                                                                name="selectionItem"
                                                            </c:if>
                                                            <c:if test="${jmGoodsExtend.fieldType == 3}">
                                                                name="radioItem"
                                                            </c:if>
                                                            <c:if test="${jmGoodsExtend.fieldType == 4}">
                                                                name="checkboxItem"
                                                            </c:if>
                                                            value="${ex}"
                                                            maxlength="7" placeholder="请输入选项内容" class="form-control" aria-describedby="basic-addon2"/>
                                                    <button type='button' class="btn btn-info" onclick='removeDiv(this)'> 删除 </button >
                                                </div>
                                                </c:forEach>
                                            </div>
                                        <br/>
                                        <br/>
                                        <br/>
                                        <br/>
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

        var name = $("input[name='name']").val();
        // 校验
        if (checkStr(name)) {
            $("input[name='name']").focus();
            return false;
        }
        var isnull = false;
        var fieldValue = [];
        $("input[name='selectionItem']").each(function () {
            var val = $(this).val();
            if (checkStr(val)) {
                isnull = true;
                return false;
            } else {
                isnull = false;
                fieldValue.push(val);
            }
        });
        $("input[name='radioItem']").each(function () {
            var val = $(this).val();
            if (checkStr(val)) {
                isnull = true;
                return false;
            } else {
                isnull = false;
                fieldValue.push(val);
            }
        });
        $("input[name='checkboxItem']").each(function () {
            var val = $(this).val();
            if (checkStr(val)) {
                isnull = true;
                return false;
            } else {
                isnull = false;
                fieldValue.push(val);
            }
        });

        if (isnull) {
            swal({
                title: "请输入选项值",
                cancelButtonText: "确定"
            });
            return false;
        }
        var isNotNull = $("input[name='isNotNull']:checked").val();
        var goodsType = $("input[name='goodsType']:checked").val();
        var fieldType = $("select[name='fieldType']").val();
        var date = {
            id:$("input[name='id']").val(),
            name:name,
            isNotNull:isNotNull,
            goodsType:goodsType,
            fieldType:fieldType,
            fieldValue:fieldValue.toString()
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
                    url: "${pageContext.request.contextPath}/jumi/product/goodsextend/updatejmgoodsextend",
                    type: "POST",
                    dataType: "json",
                    data: date,
                    success: function (result) {
                        if (result.success) {
                            swal({
                                title: "保存成功",
                                confirmButtonText: "确定"
                            }, function () {
                                location.href = "${pageContext.request.contextPath}/jumi/product/goodsextend/getlist";
                            });
                        } else {
                            swal({
                                title: result.msg,
                                cancelButtonText: "确定"
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
                location.href = "${pageContext.request.contextPath}/jumi/product/goodsextend/getlist";
            }
        });
    }


    function addSelection(val) {
        if (1 === val) {
            var selectionDiv = "<div class=\"col-md-8 m-b-30\" style='display: inline-flex'> \n"+
                " <input type=\"text\" name=\"selectionItem\" maxlength=\"7\" placeholder=\"请输入选项内容\" class=\"form-control\" aria-describedby=\"basic-addon2\"/>\n" +
                " <button type='button' class=\"btn btn-info\" onclick='removeDiv(this)'> 删除 </button > " +
                "</div>";
            $("#selectiondiv").append(selectionDiv);
        }
        if (2 === val) {
            var radioDiv = "<div class=\"col-md-8 m-b-30\" style='display: inline-flex'> \n"+
                " <input type=\"text\" name=\"radioItem\" maxlength=\"7\" placeholder=\"请输入选项内容\" class=\"form-control\" aria-describedby=\"basic-addon2\"/>\n" +
                " <button type='button' class=\"btn btn-info\" onclick='removeDiv(this)'> 删除 </button > " +
                "</div>";
            $("#radiodiv").append(radioDiv);
        }
        if (3 === val) {
            var div = "<div class=\"col-md-8 m-b-30\" style='display: inline-flex'> \n"+
                " <input type=\"text\" name=\"checkboxItem\" maxlength=\"7\" placeholder=\"请输入选项内容\" class=\"form-control\" aria-describedby=\"basic-addon2\"/>\n" +
                " <button type='button' class=\"btn btn-info\" onclick='removeDiv(this)'> 删除 </button > " +
                "</div>";
            $("#checkboxdiv").append(div);
        }
    }


    function removeDiv(obj) {
        var parent = $(obj).parent();
        parent.remove();
    }

    // TODO 完善自定义字段功能.
    function changeExchangeType(val) {
        if ('1' === val || '5' === val){
            if (${jmGoodsExtend.fieldType == 2}) {
                document.getElementById("selectiondiv").style.display = 'none';
            }
            if (${jmGoodsExtend.fieldType == 3}) {
                document.getElementById("radiodiv").style.display = 'none';
            }
            if (${jmGoodsExtend.fieldType == 4}) {
                document.getElementById("checkboxdiv").style.display = 'none';
            }
        }else {
            if (${jmGoodsExtend.fieldType == 2}) {
                document.getElementById("selectiondiv").style.display = '';
            }
            if (${jmGoodsExtend.fieldType == 3}) {
                document.getElementById("radiodiv").style.display = '';
            }
            if (${jmGoodsExtend.fieldType == 4}) {
                document.getElementById("checkboxdiv").style.display = '';
            }
        }
    }
</script>

</body>
</html>