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
    <title>角色信息</title>
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
            <jsp:param value="/role/getrolelist" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">角色信息</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">角色管理</a></li>
                        <li class="active">角色信息</li>
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
                                        <label class="col-md-12">所属系统</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${system.name}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">角色ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${role.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">角色名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${role.name}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">角色唯一编码</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${role.code}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">角色描述</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${role.description}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">角色用户&nbsp;&nbsp;&nbsp;<label style="color: #f05b4f; font-size: 12px">*</label><label style="font-size: 12px">（点击按钮查看）</label></label>
                                        <div class="col-md-12">
                                            <button style="width: 12%" type="button" class="btn btn-info" data-toggle="modal" data-target="#usermodal" data-whatever="@mdo">用户列表</button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">角色权限&nbsp;&nbsp;&nbsp;<label style="color: #f05b4f; font-size: 12px">*</label><label style="font-size: 12px">（点击按钮查看）</label></label>
                                        <div class="col-md-12">
                                            <button style="width: 12%" type="button" class="btn btn-info" data-toggle="modal" data-target="#systemmodal" data-whatever="@fat">系统权限</button>
                                            &nbsp;&nbsp;
                                            <button style="width: 12%" type="button" class="btn btn-info" data-toggle="modal" data-target="#menumodal" data-whatever="@fat">菜单权限</button>
                                            &nbsp;&nbsp;
                                            <button style="width: 12%" type="button" class="btn btn-default" style="pointer-events: none;" >操作权限</button>
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
                    <%--用户列表--%>
                    <div class="col-md-6" style="height: 400px;">
                        <div class="modal fade" id="usermodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="exampleModalLabel1">用户列表</h4> </div>
                                    <div class="modal-body" style="height: 400px;overflow:auto">
                                        <div class="panel">
                                            <div class="table-responsive">
                                                <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                                    <thead>
                                                    <tr>
                                                        <th>用户ID</th>
                                                        <th>用户名</th>
                                                        <th>用户姓名</th>
                                                        <th>手机号</th>
                                                        <th>Email</th>
                                                        <th>用户锁状态</th>
                                                        <th>创建时间</th>
                                                        <th>修改时间</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${role.users}" var="user">
                                                        <tr>
                                                            <td>${user.id}</td>
                                                            <td title="${user.userName}">
                                                                <c:set var="str" value="${user.userName}"></c:set>
                                                                <c:choose>
                                                                    <c:when test="${fn:length(str) > 15}">
                                                                        <c:out value="${fn:substring(str, 0, 15)}......" />
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:out value="${str}" />
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td title="${user.realName}">
                                                                <c:set var="str" value="${user.realName}"></c:set>
                                                                <c:choose>
                                                                    <c:when test="${fn:length(str) > 15}">
                                                                        <c:out value="${fn:substring(str, 0, 15)}......" />
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:out value="${str}" />
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>${user.mobile}</td>
                                                            <td>${user.email}</td>
                                                            <c:if test="${user.locked == 0}">
                                                                <td>未锁定</td>
                                                            </c:if>
                                                            <c:if test="${user.locked == 1}">
                                                                <td>已锁定</td>
                                                            </c:if>
                                                            <td>
                                                                <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/>
                                                            </td>
                                                            <td>
                                                                <fmt:formatDate value="${user.modifyTime}" pattern="yyyy-MM-dd"/>
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
                    <%-- 菜单树 --%>
                    <div class="col-md-6">
                        <div class="modal fade" id="menumodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="menuModalLabel1">菜单树</h4> </div>
                                    <div class="modal-body" style="height: 400px;overflow:auto">
                                        <div class="dd myadmin-dd" id="nestable-menu">
                                            <c:forEach var="menu" items="${role.menus}">
                                                <ol class="dd-list" >
                                                    <li class="dd-item" data-id="${menu.id}">
                                                        <div class="checkbox checkbox-info" style="left: 4%; padding-left: 0;">
                                                            <div class="dd-item dd3-item" style="font-size: 16px;line-height: 18px;">
                                                                <input id="${menu.id}"
                                                                <c:if test="${menu.selected == true}"> checked</c:if>
                                                                <c:if test="${menu.selected == false}"> disabled</c:if>
                                                                       type="checkbox">
                                                                <label for="${menu.id}" style="padding-top: 1%;"> </label>
                                                                    ${menu.name}
                                                            </div>
                                                        </div>

                                                        <c:forEach var="secondMenu" items="${menu.childMenus}" >
                                                            <ol class="dd-list" draggable="false">
                                                                <li class="dd-item" data-id="${secondMenu.id}">
                                                                    <div class="checkbox checkbox-info"  style="margin-left: 5%;">
                                                                        <div class="dd-item dd3-item" style="padding-left: 5%;font-size: 16px;line-height: 18px;">
                                                                            <input id="${secondMenu.id}" name="parent${menu.id}"
                                                                            <c:if test="${secondMenu.selected == true}"> checked</c:if>
                                                                            <c:if test="${secondMenu.selected == false}"> disabled</c:if>
                                                                                   type="checkbox">
                                                                            <label for="${secondMenu.id}" style="padding-top: 1%;"> </label>
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
                                                                                            <c:if test="${thirdMenu.selected == true}"> checked</c:if>
                                                                                            <c:if test="${thirdMenu.selected == false}"> disabled</c:if>
                                                                                                   type="checkbox">
                                                                                            <label for="${thirdMenu.id}" style="padding-top: 1%;"> </label>
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
                    <%--系统--%>
                    <div class="col-md-6" style="height: 400px;">
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