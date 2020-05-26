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
                    <input type="text" class="form-control" id="name" maxlength="50" placeholder="请输入活动名称" value="${coupon.name}"/>
                </div>
                <div class="col-md-1" >
                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                </div>
            </div>
        </div>
    </div>
</div>
<form action="${pageContext.request.contextPath}/coupon/couponactlist" id="form">
    <input type="hidden" name="name" value="${coupon.name}" />
    <input type="hidden" name="type" value="${type}" />
    <div class="row">
        <div class="col-md-12">
            <div class="white-box">
                <c:if test="${type == 1}">
                <div class="row">
                    <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4">
                        <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/coupon/toadd'"/>
                    </div>
                </div>
                </c:if>
                <div class="panel">
                    <div class="table-responsive">
                        <table class="table table-hover manage-u-table" style="white-space: nowrap">
                            <thead>
                            <tr>
                                <th width="17%">抵扣券名称</th>
                                <th width="12%">抵扣券总数</th>
                                <th width="12%">已领取数</th>
                                <th width="12%">已兑换数</th>
                                <th width="12%">状态</th>
                                <th width="35%">操作</th>
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
                                            <c:if test="${type == 1}">
                                            <div style="display: inline-block; width:12%">
                                                <input type="button" class="btn btn-block btn-info btn-xs" value="修改"
                                                       onclick="location.href='${pageContext.request.contextPath}/coupon/getcouponbyid?couponId=${coupon.id}&type=0'"/>
                                            </div>
                                            &nbsp;
                                            <div style="display: inline-block; width:12%">
                                                <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="updateStatus(${coupon.id}, 0);" />
                                            </div>
                                            &nbsp;
                                            <div style="display: inline-block; width:12%">
                                                <c:if test="${coupon.status == 2}">
                                                    <input type="button" class="btn btn-block btn-danger btn-xs" value="启用" onclick="updateStatus(${coupon.id},1);" />
                                                </c:if>
                                                <c:if test="${coupon.status == 1}">
                                                    <input type="button" class="btn btn-block btn-success btn-xs" value="暂停" onclick="updateStatus(${coupon.id},2);" />
                                                </c:if>
                                            </div>
                                            &nbsp;
                                            </c:if>
                                            <div style="display: inline-block; width:20%">
                                                <input type="button" class="btn btn-block btn-default btn-xs" value="领取明细" disabled/>
                                            </div>
                                            &nbsp;
                                            <div style="display: inline-block; width:30%">
                                                <input type="button" class="btn btn-block btn-default btn-xs" value="第三方调用信息" disabled/>
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
                                            <input type="hidden" id="totalPage" name="totalPage" value="${pagination.totalPage}" />
                                            <input type="hidden" id="page" name="page" value="${pagination.page}" />
                                            <ul class="pagination pagination-split">
                                                <c:if test="${pagination.totalPage>1}">
                                                    <!-- 总页数大于1页显示上一页和下一页 -->
                                                    <c:choose>
                                                        <c:when test="${pagination.page>1}">
                                                            <li><a href="javascript:skipToPrePage();"><i class="fa fa-angle-left"></i></a></li>
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
                                                            <li><a href="javascript:skipPage('${page}');">${page}</a></li>
                                                        </c:if>
                                                    </c:forEach>

                                                    <c:choose>
                                                        <c:when test="${pagination.page<pagination.totalPage}">
                                                            <li><a href="javascript:skipToNextPage();"><i class="fa fa-angle-right"></i></a></li>
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

        var name = $("input[name='name']").val();
        var type = ${type};
        var totalPage = $("#totalPage").val();
        var url = $("#form").attr("action");
        var data = {name: name, type: type, totalPage: totalPage, page: page};
        $.ajax({
            type: "POST",
            async: true, //同步请求
            url: url,
            dataType: "html",
            data: data,
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 1, 200);
            },
            success: function (result) {
                if (type == 1) {
                    $("#section-flip-1").html(result);
                } else if (type == 2) {
                    $("#section-flip-2").html(result);
                }
                hide_loading(200);
            }
        })
    }

    function search() {
        var type = ${type};
        var name = $("#name").val();
        $("input[name='name']").val(name);
        loadActivities(type, name);
    }
</script>

