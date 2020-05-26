<%@ page import="com.ddzhuan.manage.model.Menu" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%
    String visitUrl = request.getParameter("visitUrl") == null ? "" : request.getParameter("visitUrl");
    List<Menu> menus = (List) request.getSession().getAttribute("menus");
    // 一级目录
    List<Menu> menuList1 = new ArrayList<Menu>();
    // 二级目录
    List<Menu> menuList2 = new ArrayList<Menu>();
    // 所有有权限的目录
    if (menus != null && menus.size() > 0) {
        for (Menu menu : menus) {
            // 一级目录
            if (menu.getPid().intValue() == 0) {
                menuList1.add(menu);
            } else if (menu.getPid().intValue() > 0) {
                menuList2.add(menu);
            }
        }
    }
    Map<Long, Boolean> menu1Map = new HashMap<Long, Boolean>();
    Map<Long, Boolean> menu2Map = new HashMap<Long, Boolean>();
    if (menuList2 != null && menuList2.size() > 0) {
        for (Menu menu2 : menuList2) {
            if (menu2.getVisitUrl().equals(visitUrl)) {
                menu2Map.put(menu2.getId(), true);
            } else {
                menu2Map.put(menu2.getId(), false);
            }
        }
    }

    if (menuList1 != null && menuList1.size() > 0) {
        for (Menu menu1 : menuList1) {
            menu1Map.put(menu1.getId(), false);
        }
    }

    if (menuList2 != null && menuList2.size() > 0) {
        for (Menu menu2 : menuList2) {
            if (menu2.getVisitUrl().equals(visitUrl)) {
                menu1Map.put(menu2.getPid(), true);
            }
        }
    }
%>


<div class="sidebar-nav slimscrollsidebar">
    <div class="sidebar-head">
        <h3>
            <span class="fa-fw open-close">
                <i class="ti-menu hidden-xs"></i>
                <i class="ti-close visible-xs"></i>
            </span>
            <span class="hide-menu">管理平台</span>
        </h3>
    </div>
    <ul class="nav" id="side-menu">
        <% if (menuList1 != null && menuList1.size() > 0) {
            for(int i = 0 ; i < menuList1.size(); i++) {%>
            <%if (menu1Map.get(menuList1.get(i).getId())) {%>
            <li class="active">
                <a href="javascript:void(0)" class="waves-effect active">
            <%} else {%>
            <li>
                <a href="javascript:void(0)" class="waves-effect">
            <%}%>
                    <i <%=menuList1.get(i).getImg()%>></i><span class="hide-menu"><%=menuList1.get(i).getName()%><span
                        class="fa arrow"></span></span>
                </a>
                <ul class="nav nav-second-level">
                    <% if (menuList2 != null && menuList2.size() > 0) {
                        for (int j = 0 ; j < menuList2.size(); j++) {
                        if (menuList2.get(j).getPid().intValue() == menuList1.get(i).getId()) {%>
                            <li>
                            <%if (menu2Map.get(menuList2.get(j).getId())) {%>
                                <a href="${pageContext.request.contextPath}<%=menuList2.get(j).getVisitUrl()%>" class="active">
                            <%} else {%>
                                <a href="${pageContext.request.contextPath}<%=menuList2.get(j).getVisitUrl()%>">
                            <%}%>
                            <i data-icon="&#xe019;" <%=menuList2.get(j).getImg()%>></i><span
                                class="hide-menu"><%=menuList2.get(j).getName()%></span>
                                </a></li>
                    <%}
                    }
                    }%>
                </ul>
            </li>
        <%}}%>
    </ul>
</div>

