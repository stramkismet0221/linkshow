<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<form id="unSelectform">
    <input name="userId" value="${userId}" type="hidden"/>
    <input name="id" value="" type="hidden"/>
    <input name="name" value="" type="hidden"/>
    <div class="white-box">
        <h3 class="box-title m-b-0">未选择设备</h3>
        <div class="row">
        </div>
        <div class="panel">
            <div class="table-responsive">
                <table class="table table-hover manage-u-table" style="white-space: nowrap">
                    <thead>
                    <tr>
                        <th>设备ID</th>
                        <th>设备名称</th>
                        <th width="100">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${unSelectTerminals}" var="data">
                        <tr>
                            <td>${data.id}</td>
                            <td title="${data.name}">
                                <c:set var="str" value="${data.name}"></c:set>
                                <c:choose>
                                    <c:when test="${fn:length(str) > 15}">
                                        <c:out value="${fn:substring(str, 0, 15)}..." />
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${str}" />
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <div>
                                    <div style="display: inline-block; width:50%">
                                        <input type="button" class="btn btn-block btn-info btn-xs" value="加入" onclick="add('${userId}', '${data.id}');"/>
                                    </div>
                                    &nbsp;
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="4">
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
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</form>
<script src="${pageContext.request.contextPath}/common/js/tool.js"></script>
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

        var url = "${pageContext.request.contextPath}/user/unselectterminals";
        var data = $("#unSelectform").serialize();
        $.ajax({
            type: "POST",
            async: false, //同步请求
            url: url,
            dataType: "html",
            data: data,
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 0.5, 200);
            },
            success: function (result) {
                // 要刷新的div
                $("#unselect").html(result);
                hide_loading(200);
            }
        })
    }

    //用户加入设备，拥有设备统计权限
    function add(userId, terminalId) {
        $.ajax({
            url : "${pageContext.request.contextPath}/user/adduserterminal",
            type : "POST",
            dataType : "json",
            data : {"userId":userId,"terminalId":terminalId},
            success : function(result) {
                if (result.success) {
                    swal({title : result.msg,
                        confirmButtonText: "确定"}, function(){
                        loadUserTerminals();
                    });
                }
            },
            error : function() {
                alert("加入失败");
            }
        });
    }
</script>