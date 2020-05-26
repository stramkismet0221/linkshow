<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>设备列表</title>
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
                <jsp:param value="/terminal/getterminallist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">设备列表</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">设备管理</a></li>
                            <li class="active">设备列表</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-md-4" >
                                    <h5 class="m-t-30 m-b-10">设备名称</h5>
                                    <input type="text" class="form-control" id="name" maxlength="50" placeholder="请输入设备名称" value="${terminal.name}"/>
                                </div>
                                <div class="col-md-4" >
                                    <h5 class="m-t-30 m-b-10">设备编号</h5>
                                    <input type="text" class="form-control" id="deviceCode" maxlength="50" placeholder="请输入设备编号" value="${terminal.deviceCode}"/>
                                </div>
                                <div class="col-md-1" >
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/terminal/getterminallist" id="form">
                    <input type="hidden" name="name" value=""/>
                    <input type="hidden" name="deviceCode" value=""/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                            <tr>
                                                <th class="text-center">ID</th>
                                                <th>设备名称/编号</th>
                                                <th>生产编号</th>
                                                <th>厂家</th>
                                                <th>型号</th>
                                                <th>设备类型</th>
                                                <th>货柜数</th>
                                                <th>授权品牌商</th>
                                                <%--<th width="250">售货状态</th>--%>
                                                <%--<th width="300">操作</th>--%>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${terminals}" var="terminal">
                                            <tr>
                                                <td class="text-center">${terminal.id}</td>
                                                <td>${terminal.name}
                                                    <br/><span class="text-muted">${terminal.deviceCode}</span></td>
                                                <td>${terminal.deviceCode}
                                                    <br/><span class="text-muted">-</span></td>
                                                <td>深圳华创智装备科技有限公司
                                                    <br/><span class="text-muted"></span></td>
                                                <td>VMAX A
                                                    <br/><span class="text-muted"></span></td>
                                                <td>竖屏 - 55寸
                                                    <br/><span class="text-muted"></span></td>
                                                <td>${terminal.cabinetCount}
                                                    <br/><span class="text-muted"></span></td>
                                                <td>-
                                                    <br/><span class="text-muted">-</span></td>
                                                <%--<td>--%>
                                                    <%--<select class="form-control">--%>
                                                        <%--<option>Modulator</option>--%>
                                                        <%--<option>Admin</option>--%>
                                                        <%--<option>User</option>--%>
                                                        <%--<option>Subscriber</option>--%>
                                                    <%--</select>--%>
                                                <%--</td>--%>
                                                <%--<td>--%>
                                                    <%--<button type="button" class="btn btn-info btn-outline btn-circle btn-lg m-r-5"><i class="ti-key"></i></button>--%>
                                                    <%--<button type="button" class="btn btn-info btn-outline btn-circle btn-lg m-r-5"><i class="ti-trash"></i></button>--%>
                                                    <%--<button type="button" class="btn btn-info btn-outline btn-circle btn-lg m-r-5"><i class="ti-pencil-alt"></i></button>--%>
                                                    <%--<button type="button" class="btn btn-info btn-outline btn-circle btn-lg m-r-20"><i class="ti-upload"></i></button>--%>
                                                <%--</td>--%>
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
                    <%@include file="/WEB-INF/views/include/right.jsp"%>
                </div>
            </div>
            <footer class="footer text-center">
                <%@include file="/WEB-INF/views/include/footer.jsp"%>
            </footer>
        </div>
    </div>
</body>
<script type="text/javascript">
    function search() {
        var name = $("#name").val();
        var deviceCode = $("#deviceCode").val();
        $("input[name='name']").val(name);
        $("input[name='deviceCode']").val(deviceCode);
        $("#form").submit();
    }
</script>
</html>