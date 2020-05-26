<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<!-- /row -->
<div class="row">
    <div class="col-sm-12">
        <div class="white-box">
            <div class="row">
                <div class="col-md-4" >
                    <h5 class="m-t-30 m-b-10">抵扣券名称</h5>
                    <input type="text" class="form-control" id="name1" maxlength="50" placeholder="请输入历史活动名称" value="${coupon.name}"/>
                </div>
                <div class="col-md-1" >
                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                </div>
            </div>
        </div>
    </div>
</div>
<form action="${pageContext.request.contextPath}/coupon/couponactlist" id="hisForm">
    <input type="hidden" name="name" id="hisName" value="${coupon.name}" />
    <input type="hidden" name="type" value="2" />
    <div class="row">
        <div class="col-md-12">
            <div class="white-box">
                <div class="panel">
                    <div class="table-responsive">
                        <table class="table table-hover manage-u-table" style="white-space: nowrap">
                            <thead>
                            <tr>
                                <th width="25%">抵扣券名称</th>
                                <th width="14%">抵扣券总数</th>
                                <th width="14%">已领取数</th>
                                <th width="14%">已兑换数</th>
                                <th width="13%">状态</th>
                                <th width="15%">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${couponList}" var="coupon">
                                <tr>
                                    <td title="${coupon.name}">
                                        <c:set var="str" value="${coupon.name}"></c:set>
                                        <c:choose>
                                            <c:when test="${fn:length(str) > 15}">
                                                <c:out value="${fn:substring(str, 0, 15)}......" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${str}" />
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${coupon.amount}</td>
                                    <td>${coupon.receivedNum==null?0:coupon.receivedNum}</td>
                                    <td>${coupon.exchangedNum==null?0:coupon.exchangedNum}</td>
                                    <td>
                                        <c:if test="${coupon.status == 0}">
                                            删除
                                        </c:if>
                                        <c:if test="${coupon.status == 1}">
                                            正常
                                        </c:if>
                                        <c:if test="${coupon.status == 2}">
                                            暂停
                                        </c:if>
                                    </td>
                                    <td>
                                        <div>
                                            <div style="display: inline-block; width:30%">
                                                <input type="button" class="btn btn-block btn-info btn-xs" value="详情"
                                                       onclick="location.href='${pageContext.request.contextPath}/coupon/coupondetail?couponId=${coupon.id}'"/>
                                            </div>
                                            &nbsp;
                                            <div style="display: inline-block; width:40%">
                                                <input type="button" class="btn btn-block btn-default btn-xs" value="领取明细" disabled/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="9">
                                    <div class="text-right">
                                        <div class="text-right">
                                            <input type="hidden" id="totalPage1" name="totalPage" value="${pagination.totalPage}" />
                                            <input type="hidden" id="page1" name="page" value="${pagination.page}" />
                                            <ul class="pagination pagination-split">
                                                <c:if test="${pagination.totalPage>1}">
                                                    <!-- 总页数大于1页显示分页 -->
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
                                                </c:if>
                                            </ul>
                                        </div>
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

<script type="text/javascript">
    function skipToPrePage1() {
        var page1 = document.getElementById("page1").value;
        var page1 = Number(page1) - 1;
        skipPage1(page1);
    }
    function skipToNextPage1() {
        var page1 = document.getElementById("page1").value;
        var page1 = Number(page1) + 1;
        skipPage1(page1);
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

        var url = $("#hisForm").attr("action");
        var data = $("#hisForm").serialize();
        $.ajax({
            type: "POST",
            async: false, //同步请求
            url: url,
            dataType: "html",
            data: data,
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 1, 200);
            },
            success: function (result) {
                $("#section-flip-2").html(result);
                hide_loading(200);
            }
        })
    }

    function search() {
        var type = ${type};
        var hisName = $("#name1").val();
        $("input[name='hisName']").val(hisName);
        loadActivities(type, hisName);
    }
</script>

