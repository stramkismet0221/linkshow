<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:forEach var="cur" items="${jmGoodsTypes}" varStatus="vs">
    <c:set var="index" value="${index + 1}" scope="request" /><!-- 每一次循环，index+1 -->
    <tr>
        <td>
            <c:forEach var="i" begin="1" end="${level}" step="1">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
            <c:if test="${level > 0}">
                ├&nbsp;&nbsp;
            </c:if>
            <%--<c:if test="${fn:length(cur.children) > 0}">--%>
                <%--&lt;%&ndash;mdi-minus-circle-outline&ndash;%&gt;--%>
                <%--<i class="mdi mdi-plus-circle-outline" onclick="switchTr()"></i>--%>
            <%--</c:if>--%>
            <%--<c:if test="${fn:length(cur.children) <= 0}">--%>
                <%--<i class="mdi" onclick="switchTr()"></i>--%>
            <%--</c:if>--%>
             ${cur.id}
        </td>
        <td>
            <c:forEach var="i" begin="1" end="${level}" step="1">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
                ${cur.sno}
        </td>
        <td title="${cur.name}">
            <c:forEach var="i" begin="1" end="${level}" step="1">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
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
        <td title="${cur.code}">
            <c:forEach var="i" begin="1" end="${level}" step="1">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
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
        <td>
            <c:forEach var="i" begin="1" end="${level}" step="1">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
            <c:set var="str" value="${cur.showType}"></c:set>
            <c:choose>
                <c:when test="${str.equals('1')}">
                    <c:out value="电商" />
                </c:when>
                <c:when test="${str.equals('2')}">
                    <c:out value="实体" />
                </c:when>
                <c:when test="${str.contains('1') && str.contains('2')}">
                    <c:out value="电商/实体" />
                </c:when>
            </c:choose>
        </td>
        <td>
            <div>
                <div style="display: inline-block; width:33%">
                    <input type="button" class="btn btn-block btn-primary btn-xs"
                           value="新增子分类"
                           onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodstype/addchildjmgoodstype?pid=${cur.id}'"/>
                </div>
                &nbsp;
                <div style="display: inline-block; width:18%">
                    <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodstype/jmgoodstypeinfo?id=${cur.id}&type=0'" />
                </div>
                &nbsp;
                <div style="display: inline-block; width:18%">
                    <input type="button" class="btn btn-block btn-info btn-xs"
                           value="修改"
                           onclick="location.href='${pageContext.request.contextPath}/jumi/product/goodstype/jmgoodstypeinfo?id=${cur.id}&type=1'"/>
                </div>
                &nbsp;
                <div style="display: inline-block; width:18%">
                    <input type="button" class="btn btn-block btn-danger btn-xs"
                           value="删除" onclick="remove(${cur.id});"/>
                </div>
            </div>
        </td>
    </tr>
    <c:if test="${fn:length(cur.children) > 0}"><!-- 如果有childen -->
        <c:set var="level" value="${level + 1}" scope="request" /><!-- 循环一次子列表，level+1 -->
        <c:set var="jmGoodsTypes" value="${cur.children}" scope="request" /><!-- 注意此处，子列表覆盖treeList，在request作用域 -->
        <c:import url="recursiongoodstypes.jsp" /><!-- 这就是递归了 -->
    </c:if>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" /><!-- 退出时，level-1 -->

<script type="text/javascript">
    function switchTr() {
        
    }
</script>
