<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:if test="${not empty data.children}">
    <c:forEach var="data" items="${data.children}">
        <option value="${data.id}" <c:if test="${goodsTypeId == data.id}">selected</c:if>>
            <c:forEach var="i" begin="2" end="${data.level}" step="1">
                --
            </c:forEach>
            ${data.name}
        </option>
        <c:set var="data" value="${data}" scope="request"/>
        <jsp:include page="recursiveOptionsforlist.jsp"/>
    </c:forEach>
</c:if>

