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
    <title>供货商列表</title>
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
            <jsp:param value="/jumi/product/goodssupplier/list" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">供货商列表</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">商品管理</a></li>
                        <li class="active">供货商列表</li>
                    </ol>
                </div>
            </div>
            <!-- /row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="row">
                            <div class="col-md-3" >
                                <h5 class="m-t-30 m-b-10 col-md-12">状态</h5>
                                <select class="form-control" id="state">
                                    <option value="" title="全部" <c:if test="">selected</c:if>> 全部</option>
                                    <option value="1" title="启用" <c:if test="${goodssupplier.states==1}">selected</c:if>>启用 </option>
                                    <option value="0" title="禁用" <c:if test="${goodssupplier.states==0}">selected</c:if>>禁用 </option>
                                </select>
                                <%--<h5 class="m-t-30 m-b-10">品牌名称</h5>--%>
                            </div>
                            <div class="col-md-3" >
                                <h5 class="m-t-30 m-b-10">供货商名称</h5>
                                <input type="text" class="form-control" id="suppliername" maxlength="255" placeholder="请输入供货商名称" value="${goodssupplier.name}"/>
                            </div>

                            <div class="col-md-1" >
                                <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                <input type="button" class="btn btn-block btn-info" maxlength="50" value="查询" onclick="search();"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/jumi/product/goodssupplier/list" id="form">
                <input type="hidden" name="states" id="states" value="" />
                <input type="hidden" name="name" id="brandsname" value="" />
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4" >
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodssupplier/toadd'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                        <tr>
                                            <th>供货商名称</th>
                                            <th>联系人</th>
                                            <th>联系电话</th>
                                            <th>联系邮箱</th>
                                            <th>地址</th>
                                            <th width="100">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${goodssuppliers}" var="supplier">
                                            <tr>
                                                <td title="${supplier.name}">
                                                    <c:set var="str" value="${supplier.name}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td title="${supplier.contacts}">
                                                    <c:set var="str" value="${supplier.contacts}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${supplier.telephone}</td>
                                                <td title="${supplier.email}">
                                                    <c:set var="str" value="${supplier.email}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td title="${supplier.contactAddress}">
                                                    <c:set var="str" value="${supplier.contactAddress}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 10}">
                                                            <c:out value="${fn:substring(str, 0, 10)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <div>
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodssupplier/info?id=${supplier.id}&type=1'" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodssupplier/info?id=${supplier.id}&type=0'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:29%">
                                                            <input type="button" class="btn btn-block btn-danger btn-xs" value="删除"  onclick="remove(${supplier.id});" />
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="9">
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
                </div>
            </form>
            <div class="right-sidebar">
                <%@ include file="/WEB-INF/views/include/right.jsp" %>
            </div>
        </div>
        <footer class="footer text-center">
            <%@ include file="/WEB-INF/views/include/footer.jsp" %>
        </footer>
    </div>
</div>

<script type="text/javascript">
    // 查询
    function search() {
        var state = $("#state").val();
        $("#states").val(state);
        var name = $("#suppliername").val();
        $("#brandsname").val(name);
        $("#form").submit();
    }
    // 删除
    function remove(id) {
        swal({
            title: "是否删除品牌?",
            showCancelButton: true,
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/jumi/product/goodssupplier/deletegoodssupplierbyid",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id},
                    success : function(result) {
                        if (result.success) {
                            swal({title : "删除成功",
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/jumi/product/goodssupplier/list";
                            });
                        }
                    },
                    error : function() {
                        alert("删除失败");
                    }
                });
            }
        });
    }
</script>
</body>
</html>
