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
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>用户信息</title>
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
            <jsp:param value="/user/getuserlist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">用户信息</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">用户管理</a></li>
                        <li class="active">用户信息</li>
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
                                <form id="form" class="form-material form-horizontal" data-toggle="validator">
                                    <div class="form-group">
                                        <label class="col-md-12">用户ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">用户名</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.userName}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">用户姓名</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.realName}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">手机号</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.mobile}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Email</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${user.email}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">用户锁状态</label>
                                        <div class="col-md-12">
                                            <c:if test="${user.locked == 0}">
                                                <input type="text" class="form-control" value="未锁定" disabled/>
                                            </c:if>
                                            <c:if test="${user.locked == 1}">
                                                <input type="text" class="form-control" value="已锁定" disabled/>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">用户权限&nbsp;&nbsp;&nbsp;<label style="color: #f05b4f; font-size: 12px">*</label><label style="font-size: 12px">（点击按钮查看）</label></label>
                                        <div class="col-md-12">
                                                <ul class="nav nav-tabs" role="tablist">
                                                    <c:forEach items="${user.systemInfos}" var="system" varStatus="status" begin="0">
                                                        <li role="presentation" <c:if test="${status.index == 0}"> class="active" </c:if>>
                                                            <a href="#${system.name}" aria-controls="${system.name}" role="tab" data-toggle="tab"  <c:if test="${status.index == 0}"> aria-expanded="true"</c:if> ><span class="visible-xs">
                                                                <i class="ti-home"></i></span><span class="hidden-xs">${system.name}</span>
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                                <div class="tab-content">
                                                    <c:forEach items="${user.systemInfos}" var="system" varStatus="status1" begin="0">
                                                        <div role="tabpanel"  <c:if test="${status1.index == 0}">class="tab-pane active "</c:if>
                                                                <c:if test="${status1.index != 0}"> class="tab-pane " </c:if>
                                                             id="${system.name}" >
                                                            <div class="col-md-12">
                                                                <button style="width: 12%" type="button" class="btn btn-default" data-toggle="modal" data-target="#systemmodal" data-whatever="@fat">系统权限</button>
                                                                &nbsp;&nbsp;
                                                                <button style="width: 12%" type="button" class="btn btn-info" data-toggle="modal" data-target="#powermodal${system.id}" data-whatever="@fat">菜单权限</button>
                                                                &nbsp;&nbsp;
                                                                <button style="width: 12%;pointer-events: none;" type="button" class="btn btn-default" >操作权限</button>
                                                            </div>
                                                        <div class="col-md-6">
                                                            <div class="modal fade" id="powermodal${system.id}" tabindex="-1" role="dialog" aria-labelledby="powermodal">
                                                                <div class="modal-dialog modal-lg" role="document">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                            <h4 class="modal-title" id="menuModalLabel1">菜单权限</h4> </div>
                                                                        <div class="modal-body" style="height: 400px;overflow:auto">
                                                                            <div class="dd myadmin-dd" id="nestable-menu">
                                                                                <c:forEach var="menu" items="${user.menus.get(system.name)}">
                                                                                    <ol class="dd-list" >
                                                                                        <li class="dd-item" data-id="${menu.id}">
                                                                                            <div class="checkbox checkbox-info" style="left: 4%; padding-left: 0;">
                                                                                                <div class="dd-item dd3-item" style="font-size: 16px;line-height: 18px;">
                                                                                                    <input id="${menu.id}"
                                                                                                    <c:if test="${menu.selected == true}"> checked disabled</c:if>
                                                                                                           type="checkbox">
                                                                                                    <label for="${menu.id}"> </label>
                                                                                                        ${menu.name}
                                                                                                </div>
                                                                                            </div>

                                                                                            <c:forEach var="secondMenu" items="${menu.childMenus}" >
                                                                                                <ol class="dd-list" draggable="false">
                                                                                                    <li class="dd-item" data-id="${secondMenu.id}">
                                                                                                        <div class="checkbox checkbox-info"  style="margin-left: 5%;">
                                                                                                            <div class="dd-item dd3-item" style="padding-left: 5%;font-size: 16px;line-height: 18px;">
                                                                                                                <input id="${secondMenu.id}" name="parent${menu.id}"
                                                                                                                <c:if test="${secondMenu.selected == true}"> checked disabled</c:if>
                                                                                                                       type="checkbox">
                                                                                                                <label for="${secondMenu.id}"> </label>
                                                                                                                    ${secondMenu.name}
                                                                                                            </div>
                                                                                                        </div>

                                                                                                        <c:if test="${secondMenu.childMenus.size()>0}" >
                                                                                                            <c:forEach var="thirdMenu" items="${secondMenu.childMenus}" >
                                                                                                                <ol class="dd-list" draggable="false">
                                                                                                                    <li class="dd-item" data-id="${thirdMenu.id}">
                                                                                                                        <div class="checkbox checkbox-info">
                                                                                                                            <div class="dd-item dd3-item" style="padding-left: 10%;font-size: 16px;line-height: 18px;">
                                                                                                                                <input id="${thirdMenu.id}" name="parent${menu.id}"
                                                                                                                                <c:if test="${thirdMenu.selected == true}"> checked disabled</c:if>
                                                                                                                                       type="checkbox">
                                                                                                                                <label for="${thirdMenu.id}"> </label>
                                                                                                                                    ${thirdMenu.name}
                                                                                                                            </div>
                                                                                                                        </div>
                                                                                                                    </li>
                                                                                                                </ol>
                                                                                                            </c:forEach>
                                                                                                        </c:if>
                                                                                                    </li>
                                                                                                </ol>
                                                                                            </c:forEach>
                                                                                        </li>
                                                                                    </ol>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                                        </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="clearfix"></div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            <div class="col-md-6">
                                                <div class="modal fade" id="systemmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1">
                                                    <div class="modal-dialog modal-lg" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                <h4 class="modal-title" id="systemModalLabel1">系统列表</h4> </div>
                                                            <div class="modal-body" style="height: 400px;overflow:auto">
                                                                <div class="panel">
                                                                    <div class="table-responsive">
                                                                        <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                                                            <thead>
                                                                            <tr>
                                                                                <th>系统ID</th>
                                                                                <th>系统名称</th>
                                                                                <th>系统描述</th>
                                                                                <th>创建时间</th>
                                                                                <th>修改时间</th>
                                                                            </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                            <c:forEach items="${user.systemInfos}" var="system" >
                                                                                <tr>
                                                                                    <td>${system.id}</td>
                                                                                    <td title="${system.name}">
                                                                                        <c:set var="str" value="${system.name}"></c:set>
                                                                                        <c:choose>
                                                                                            <c:when test="${fn:length(str) > 15}">
                                                                                                <c:out value="${fn:substring(str, 0, 15)}......" />
                                                                                            </c:when>
                                                                                            <c:otherwise>
                                                                                                <c:out value="${str}" />
                                                                                            </c:otherwise>
                                                                                        </c:choose>
                                                                                    </td>
                                                                                    <td title="${system.description}">
                                                                                        <c:set var="str" value="${system.description}"></c:set>
                                                                                        <c:choose>
                                                                                            <c:when test="${fn:length(str) > 15}">
                                                                                                <c:out value="${fn:substring(str, 0, 15)}......" />
                                                                                            </c:when>
                                                                                            <c:otherwise>
                                                                                                <c:out value="${str}" />
                                                                                            </c:otherwise>
                                                                                        </c:choose>
                                                                                    </td>
                                                                                    <td>
                                                                                        <fmt:formatDate value="${system.createTime}" pattern="yyyy-MM-dd"/>
                                                                                    </td>
                                                                                    <td>
                                                                                        <fmt:formatDate value="${system.modifyTime}" pattern="yyyy-MM-dd"/>
                                                                                    </td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-lg-5"></div>
                                    <div class="col-lg-2 col-sm-4 col-xs-12">
                                        <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
                                            <span style="vertical-align: inherit;">返回</span>
                                        </button>
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
</body>
</html>
<script type="text/javascript">
    function getUserPower(userId,systemId) {
        // document.getElementById("userpower").style.display='block';
        // $("#userpower").show()
    }
</script>