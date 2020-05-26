<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:forEach var="cur" items="${jmGoodsTypes}" varStatus="vs">
    <c:set var="index" value="${index + 1}" scope="request" /><!-- 每一次循环，index+1 -->
    <tr>
        <td>
            <div class="checkbox checkbox-info" style="margin-top: 0px; margin-bottom: -7px">
                <input name="goodsTypeId" type="checkbox" onclick="checkOne();"} ${cur.checked?"checked":""}>
                <label > </label>
            </div>
        </td>
        <input type="hidden" name="typeId" value="${cur.id}">
        <input type="hidden" name="typeName" value="${cur.name}">
        <td>
            <c:forEach var="i" begin="1" end="${level}" step="1">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
            <c:if test="${level > 0}">
                |-&nbsp;&nbsp;
            </c:if>
             ${cur.id}
        </td>
        <td title="${cur.code}">
            <c:set var="str" value="${cur.code}"></c:set>
            <c:choose>
                <c:when test="${fn:length(str) > 20}">
                    <c:out value="${fn:substring(str, 0, 20)}......" />
                </c:when>
                <c:otherwise>
                    <c:out value="${str}" />
                </c:otherwise>
            </c:choose>
        </td>
        <td title="${cur.name}">
            <c:set var="str" value="${cur.name}"></c:set>
            <c:choose>
                <c:when test="${fn:length(str) > 10}">
                    <c:out value="${fn:substring(str, 0, 10)}......" />
                </c:when>
                <c:otherwise>
                    <c:out value="${str}" />
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen -->
        <c:set var="level" value="${level + 1}" scope="request" /><!-- 循环一次子列表，level+1 -->
        <c:set var="jmGoodsTypes" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->
        <c:import url="recursiongoodstypes.jsp" /><!-- 这就是递归了 -->
    </c:if>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 -->
