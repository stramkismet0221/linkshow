<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<div id="ex">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <div class="row">
                    <div class="col-md-4" >
                        <h5 class="m-t-30 m-b-10">活动名称</h5>
                        <input type="text" class="form-control" id="actName" maxlength="50" value="${exCode.actName}" placeholder="请输入活动名称"/>
                    </div>
                    <div class="col-md-1" >
                        <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                        <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/excode/getexcodelist" id="form1">
        <input id="hisActName" type="hidden"/>
        <div class="row">
            <div class="col-md-12">
                <div class="white-box">
                    <div class="panel">
                        <div class="table-responsive">
                            <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                <thead>
                                <tr>
                                    <th>活动名称</th>
                                    <th>兑换码总数</th>
                                    <th>已领取数</th>
                                    <th>已兑换数</th>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${exCodeList}" var="exCode">
                                    <tr>
                                        <td title="${exCode.actName}">
                                            <c:set var="str" value="${exCode.actName}"></c:set>
                                            <c:choose>
                                                <c:when test="${fn:length(str) > 15}">
                                                    <c:out value="${fn:substring(str, 0, 15)}......" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${str}" />
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${exCode.exCodeCount}</td>
                                        <td>${exCode.receivedCount==null?0:exCode.receivedCount}</td>
                                        <td>${exCode.exchangedCount==null?0:exCode.exchangedCount}</td>
                                        <td>
                                            <div>
                                                &nbsp;
                                                <div style="display: inline-block; width:17%">
                                                    <input type="button" class="btn btn-block btn-info btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/excode/getexcodebyid?exCodeId=${exCode.id}&type=0'"/>
                                                </div>
                                                &nbsp;
                                                <div style="display: inline-block; width:40%">
                                                    <input type="button" class="btn btn-block btn-default btn-xs" disabled value="第三方调用信息" onclick="location.href='${pageContext.request.contextPath}/excode/getexcodebyid?exCodeId=${exCode.id}&type=0'"/>
                                                </div>
                                                &nbsp;
                                                <div style="display: inline-block; width:34%">
                                                    <input type="button" class="btn btn-block btn-default btn-xs" disabled value="兑换流水明细" onclick="location.href='${pageContext.request.contextPath}/excode/getexcodebyid?exCodeId=${exCode.id}&type=0'"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <div class="text-right">
                                            <input type="hidden" id="totalPage1" name="totalPage" value="${pagination.totalPage}" />
                                            <input type="hidden" id="page1" name="page" value="${pagination.page}" />
                                            <ul class="pagination pagination-split">
                                                <c:if test="${pagination.totalPage>1}">
                                                    <!-- 总页数大于1页显示分页 -->
                                                    <c:choose>
                                                        <c:when test="${pagination.page>1}">
                                                            <%--<li class="footable-page-arrow"><a data-page="first" href="javascript:skipToFirstPage();">«</a></li>--%>
                                                            <li><a href="javascript:skipToPrePage1();"><i class="fa fa-angle-left"></i></a></li>
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
                                                            <li><a href="javascript:skipPage1('${page}');">${page}</a></li>
                                                        </c:if>
                                                    </c:forEach>

                                                    <c:choose>
                                                        <c:when test="${pagination.page<pagination.totalPage}">
                                                            <li><a href="javascript:skipToNextPage1();"><i class="fa fa-angle-right"></i></a></li>
                                                            <%--<li class="footable-page-arrow"><a data-page="last" href="javascript:skipToLastPage1();">»</a></li>--%>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <li class="disabled"><a href="javascript:void(0)"><i class="fa fa-angle-right"></i></a></li>
                                                            <%--<li class="footable-page-arrow disabled"><a data-page="last" href="javascript:void(0);">»</a></li>--%>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </ul>
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
        var actName = $("#hisActName").val();
        var data = {actName: actName, type: ${type}, totalPage: totalPage, page: page};
        $.ajax({
            url:'${pageContext.request.contextPath}/excode/getexcodelist',
            type:'post',
            async:false,
            data:data,
            success:function (data) {
                $("#ex").parents("#myTabContent").children("#history").html(data)
            }
        });

    }

    // 查询
    function search() {
        var actName = $("#actName").val();
        $("#hisActName").val(actName);
        $.ajax({
            url:'${pageContext.request.contextPath}/excode/getexcodelist',
            type:'post',
            async:false,
            data:{"actName":actName,"type":${type}},
            success:function (data) {
                $("#ex").parents("#myTabContent").children("#history").html(data)
            }
        });
    }

</script>