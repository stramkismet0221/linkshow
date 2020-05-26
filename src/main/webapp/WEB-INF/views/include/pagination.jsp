<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<input type="hidden" id="totalPage" name="totalPage" value="${pagination.totalPage}" />
<input type="hidden" id="page" name="page" value="${pagination.page}" />
<ul class="pagination pagination-split">
<c:if test="${pagination.totalPage>1}">
	<!-- 总页数大于1页显示分页 -->
	<c:choose>
		<c:when test="${pagination.page>1}">
			<%--<li class="footable-page-arrow"><a data-page="first" href="javascript:skipToFirstPage();">«</a></li>--%>
			<li><a href="javascript:skipToPrePage();"><i class="fa fa-angle-left"></i></a></li>
		</c:when>
		<c:otherwise>
			<%--<li class="footable-page-arrow disabled"><a data-page="first" href="javascript:void(0);">«</a></li>--%>
			<li class="disabled"><a href="javascript:void(0)"><i class="fa fa-angle-left"></i></a></li>
		</c:otherwise>
	</c:choose>

    <c:forEach items="${pagination.pagesGroup}" var="page" varStatus="status">
		<c:if test="${page == pagination.page}">
			<li class="active"><a href="javascript:void(0);">${pagination.page}</a></li>
		</c:if>
		<c:if test="${page != pagination.page}">
			<li><a href="javascript:skipPage('${page}');">${page}</a></li>
		</c:if>
    </c:forEach>
   
	<c:choose>
		<c:when test="${pagination.page<pagination.totalPage}">
			<li><a href="javascript:skipToNextPage();"><i class="fa fa-angle-right"></i></a></li>
			<%--<li class="footable-page-arrow"><a data-page="last" href="javascript:skipToLastPage();">»</a></li>--%>
		</c:when>
		<c:otherwise>
			<li class="disabled"><a href="javascript:void(0)"><i class="fa fa-angle-right"></i></a></li>
			<%--<li class="footable-page-arrow disabled"><a data-page="last" href="javascript:void(0);">»</a></li>--%>
		</c:otherwise>
	</c:choose>
</c:if>
</ul>

<script type="text/javascript">
    function skipToFirstPage() {
        skipPage(1);
    }
	function skipToPrePage() {
		var page = document.getElementById("page").value;
		var page = Number(page) - 1;
		skipPage(page);
	}
	function skipToNextPage() {
		var page = document.getElementById("page").value;
		var page = Number(page) + 1;
		skipPage(page);
	}
    function skipToLastPage() {
        var page = document.getElementById("totalPage").value;
        skipPage(page);
    }
	function skipPage(page) {
		var page = Number(page);
	    var totalPage = document.getElementById("totalPage").value;
		if (page > 2147483647) {
			page = 2147483647;
		} else if (page < 0) {
			page = 1;
		}else if(page>totalPage){
	        page=totalPage;
		}
		document.getElementById("page").value = page;
		$('#form').submit();
	}
</script>
