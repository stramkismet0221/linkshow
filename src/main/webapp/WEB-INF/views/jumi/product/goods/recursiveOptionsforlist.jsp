<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
    <c:if test="${not empty data.children}">
        <c:forEach var="data" items="${data.children}">
                <c:if test="${data.level == 1}">
                    <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>${data.name}</option>
                </c:if>
                <c:if test="${data.level == 2}">
                    <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>---${data.name}</option>
                </c:if>
                <c:if test="${data.level == 3}">
                    <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>------${data.name}</option>
                </c:if>
                <c:if test="${data.level == 4}">
                    <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>---------${data.name}</option>
                </c:if>
                <c:if test="${data.level == 5}">
                    <option value="${data.id}" <c:if test="${goods.goodsTypeId == data.id}">selected</c:if>>------------${data.name}</option>
                </c:if>
            <c:set var="data" value="${data}" scope="request"/>
            <jsp:include page="recursiveOptionsforlist.jsp"/>
        </c:forEach>
    </c:if>

