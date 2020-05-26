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
    <title>商品列表</title>
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/custom-select/custom-select.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/switchery/dist/switchery.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/multiselect/css/multi-select.css" rel="stylesheet" type="text/css" />
</head>
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
            <jsp:param value="/product/getproductlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">商品列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品管理</a></li>
                        <li class="active">商品列表</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <form id="search">
                            <div class="row">
                                <div class="col-md-3" >
                                    <h5 class="m-t-30 mb-10">商品类型<label ></label></h5>
                                    <select class="form-control" data-style="form-control" id="categoryId" name="categoryId">
                                        <option value="">全部</option>
                                        <c:forEach items="${categoryList}" var="data">
                                            ${data.id.equals(ypProduct.categoryId)?'selected=\"selected\"':''}
                                            <optgroup label="${data.name}">
                                                <c:forEach items="${data.child}" var="childData">
                                                    <c:if test="${childData.id.equals(ypProduct.categoryId)}">
                                                        <option value="${childData.id}" selected>${childData.name}</option>
                                                    </c:if>
                                                    <c:if test="${!childData.id.equals(ypProduct.categoryId)}">
                                                        <option value="${childData.id}">${childData.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </optgroup>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <h5 class="m-t-30 m-b-10">商品条形码</h5>
                                    <input type="text" class="form-control" id="barCode" maxlength="50" placeholder="请输入商品条形码" value="${ypProduct.barCode}"/>
                                </div>
                                <div class="col-md-3">
                                    <h5 class="m-t-30 m-b-10">商品名称</h5>
                                    <input type="text" class="form-control" id="name" maxlength="50" placeholder="请输入商品名称" value="${ypProduct.name}"/>
                                </div>
                                <div class="col-md-1" >
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="row">
                <form action="${pageContext.request.contextPath}/product/getproductlist" id="form">
                    <input name="name" value="" type="hidden" />
                    <input name="barCode" value="" type="hidden" />
                    <input name="categoryId" value="" type="hidden" />
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4" >
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/product/addproduct'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th >图片</th>
                                            <th >条形码</th>
                                            <th >商品名称</th>
                                            <th >分类名称</th>
                                            <%--<th >建议弹簧</th>--%>
                                            <th >添加时间</th>
                                            <th style="width: 10%">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${products}" var="data">
                                        <tr>
                                            <td>
                                                <a href="${data.imageUrl}" target="_blank">
                                                    <img src="${data.imageUrl}" style="width: 40px; height: 60px"  alt="" />
                                                </a>
                                            <td style="vertical-align: middle">${data.barCode}</td>
                                            <td title="${data.name}" style="vertical-align: middle">
                                                <c:set var="str" value="${data.name}"></c:set>
                                                <c:choose>
                                                    <c:when test="${fn:length(str) > 20}">
                                                        <c:out value="${fn:substring(str, 0, 20)}......" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${str}" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td style="vertical-align: middle">${data.categoryName}</td>
                                            <td style="vertical-align: middle">
                                                <fmt:formatDate value="${data.createTime}" pattern="yyyy-MM-dd"/>
                                            </td>
                                            <td style="vertical-align: middle">
                                                <div>
                                                    <div style="display: inline-block; width:50%">
                                                        <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove('${data.barCode}');" />
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="6">
                                                <div class="text-right">
                                                    <%@ include file="/WEB-INF/views/include/pagination.jsp" %>
                                                </div>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="right-sidebar">
                <%@ include file="/WEB-INF/views/include/right.jsp" %>
            </div>
        </div>
        <footer class="footer text-center">
            <%@ include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
</div>

<script src="${pageContext.request.contextPath}/common/adm/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/switchery/dist/switchery.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/custom-select/custom-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/multiselect/js/jquery.multi-select.js" type="text/javascript"></script>

<script>
    jQuery(document).ready(function() {
        // Switchery
        var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        $('.js-switch').each(function() {
            new Switchery($(this)[0], $(this).data());
        });
        // For select 2
        $(".select2").select2();
        $('.selectpicker').selectpicker();
        //Bootstrap-TouchSpin
        $(".vertical-spin").TouchSpin({
            verticalbuttons: true,
            verticalupclass: 'ti-plus',
            verticaldownclass: 'ti-minus'
        });
        var vspinTrue = $(".vertical-spin").TouchSpin({
            verticalbuttons: true
        });
        if (vspinTrue) {
            $('.vertical-spin').prev('.bootstrap-touchspin-prefix').remove();
        }
        $("input[name='tch1']").TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 10,
            postfix: '%'
        });
        $("input[name='tch2']").TouchSpin({
            min: -1000000000,
            max: 1000000000,
            stepinterval: 50,
            maxboostedstep: 10000000,
            prefix: '$'
        });
        $("input[name='tch3']").TouchSpin();
        $("input[name='tch3_22']").TouchSpin({
            initval: 40
        });
        $("input[name='tch5']").TouchSpin({
            prefix: "pre",
            postfix: "post"
        });
        // For multiselect
        $('#pre-selected-options').multiSelect();
        $('#optgroup').multiSelect({
            selectableOptgroup: true
        });
        $('#public-methods').multiSelect();
        $('#select-all').click(function() {
            $('#public-methods').multiSelect('select_all');
            return false;
        });
        $('#deselect-all').click(function() {
            $('#public-methods').multiSelect('deselect_all');
            return false;
        });
        $('#refresh').on('click', function() {
            $('#public-methods').multiSelect('refresh');
            return false;
        });
        $('#add-option').on('click', function() {
            $('#public-methods').multiSelect('addOption', {
                value: 42,
                text: 'test 42',
                index: 0
            });
            return false;
        });
    });
</script>

<script type="text/javascript">
    // 查询
    function search() {
        var categoryId = $("#categoryId").val();
        $("input[name='categoryId']").val(categoryId);
        var name = $("#name").val();
        $("input[name='name']").val(name);
        var barCode = $("#barCode").val();
        $("input[name='barCode']").val(barCode);
        $("#form").submit();
    }
    //删除权限
    function remove(barCode) {
        swal({
            title: "确定删除吗?",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/product/removeproduct",
                    type : "POST",
                    dataType : "json",
                    data : {"barCode":barCode, },
                    success : function(result) {
                        if (result.success) {
                            swal({title : result.msg,
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/product/getproductlist";
                            });
                        }else {
                            swal({title : result.msg,
                                confirmButtonText: "确定"});
                        }
                    },
                    error : function() {
                        swal({title : result.msg,
                            confirmButtonText: "删除失败"});
                    }
                });
            }
        });
    }
</script>
</body>
</html>