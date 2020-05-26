<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<input type="hidden" name="oId" value=""/>
<input type="hidden" name="startTime" value="${startTime}"/>
<input type="hidden" name="endTime" value="${endTime}"/>

<div class="table-responsive">
    <table class="table table-hover manage-u-table" style="white-space: nowrap">
        <thead>
            <tr>
                <th>排名</th>
                <th>商品</th>
                <th>交易额</th>
                <th>成交额</th>
                <th>销量</th>
                <th>毛利</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${prodAnalyses}" var="data" varStatus="index">
            <tr>
                <td>${(pagination.page-1)*(pagination.rows)+index.index+1}</td>
                <td>${data.productName}</td>
                <td>￥${data.transAmount}</td>
                <td>￥${data.turnoverAmount}</td>
                <td>${data.salesVolume}</td>
                <td>￥${data.grossProfit}</td>
            </tr>
            </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="6">
                <div class="text-right">
                    <input type="hidden" id="totalPage1" name="totalPage" value="${pagination.totalPage}" />
                    <input type="hidden" id="page1" name="page" value="${pagination.page}" />
                    <ul class="pagination pagination-split">
                        <%--<c:if test="${pagination.totalPage>1}">--%>
                            <!-- 总页数大于1页显示上一页和下一页 -->
                            <c:choose>
                                <c:when test="${pagination.page>1}">
                                    <li><a href="javascript:skipToPrePage1();"><i class="fa fa-angle-left"></i></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="disabled"><a href="javascript:void(0)"><i class="fa fa-angle-left"></i></a></li>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach items="${pagination.pagesGroup}" var="page" varStatus="status">
                                <c:if test="${page == pagination.page}">
                                    <li class="active"><a href="javascript:void(0);">${pagination.page}</a></li>
                                </c:if>
                                <c:if test="${page != pagination.page}">
                                    <li><a href="javascript:skipPage1('${page}');">${page}</a></li>
                                </c:if>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${pagination.page<pagination.totalPage}">
                                    <li><a href="javascript:skipToNextPage1();"><i class="fa fa-angle-right"></i></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="disabled"><a href="javascript:void(0)"><i class="fa fa-angle-right"></i></a></li>
                                </c:otherwise>
                            </c:choose>
                        <%--</c:if>--%>
                    </ul>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</div>

<script type="text/javascript">
    function skipToPrePage1() {
        var page = document.getElementById("page1").value;
        var page = Number(page) - 1;
        skipPage1(page);
    }
    function skipToNextPage1() {
        var page = document.getElementById("page1").value;
        var page = Number(page) + 1;
        skipPage1(page);
    }
    function skipPage1(page) {
        var page = Number(page);
        var totalPage = document.getElementById("totalPage1").value;
        if (page > 2147483647) {
            page = 2147483647;
        } else if (page < 0) {
            page = 1;
        }else if(page>totalPage){
            page=totalPage;
        }
        document.getElementById("page1").value = page;

        var url = "${pageContext.request.contextPath}/dashboard/prodanalysis";
        var data = {/*totalPage: $("#totalPage1").val(), */page: $("#page1").val(), oId: "",
                    startTime: '${startTime}', endTime: '${endTime}'};
        $.ajax({
            type: "POST",
            async: false,
            url: url,
            dataType: "html",
            data: data,
            success: function (result) {
                $("#prodAnalysis").html(result);
            }
        })
    }
</script>