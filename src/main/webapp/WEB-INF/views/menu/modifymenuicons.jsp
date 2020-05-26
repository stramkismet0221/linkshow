<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<form action="${pageContext.request.contextPath}/menu/modifyicons" id="modifyIconForm">
<div class="white-box clearfix">
    <div class="material-icon-list-demo">
        <div class="icons" id="icons">
            <c:forEach items="${dictionaryList}" var="data">
                <c:if test="${data.code==dictionary.code}">
                    <div class="node" id="node" style="background-color: #e4e7ea">
                </c:if>
                <c:if test="${data.code!=dictionary.code}">
                    <div class="node">
                </c:if>
                    <i class="mdi ${data.name}"></i>
                    <code>${data.code}</code>
                    <span title="${data.name}">
                        <c:set var="str" value="${data.name}"></c:set>
                        <c:choose>
                            <c:when test="${fn:length(str) > 20}">
                                <c:out value="${fn:substring(str, 0, 20)}..." />
                            </c:when>
                            <c:otherwise>
                                <c:out value="${str}" />
                            </c:otherwise>
                        </c:choose>

                    </span>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div class="text-right">
    <input type="hidden" id="totalPage" name="totalPage" value="${pagination.totalPage}" />
    <input type="hidden" id="page" name="page" value="${pagination.page}" />
    <ul class="pagination pagination-split">
        <c:if test="${pagination.totalPage>1}">
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

        $.ajax({
            type: "POST",
            async: false, //同步请求
            url: $("#modifyIconForm").attr("action"),
            dataType: "html",
            data: {"code": '${dictionary.code}',"page":page, "totalPage":totalPage},
            beforeSend:function () {
                var contentPath = "${pageContext.request.contextPath}";
                show_loading(contentPath, 1, 0.5, 200);
            },
            success: function (result) {
                $(".modal-body").html(result);
                hide_loading(200);
            }
        })
    }

    var index = $(".node").index($("#node"));
    $('.node').on('click', function(e) {
        $(".node:eq("+index+")").css({"background": ""});
        $(this).css({"background": "#e4e7ea"});
        index = $(this).index();
    });
    // 保存菜单图标
    function updateMenuIcon() {
        var code = $(".node:eq("+index+")").find("code").text();
        var value = $(".node:eq("+index+")").find("i").attr("class");
        if (index != -1) {
            $("input[name='img']").val(value);
            var imgHtml = '<i class="mdi '+value+'"></i><code>'+code+'</code><span>'+value+'</span>';
            $("#menuIcon").html(imgHtml);
        }
        $("#updateIconsModal").modal('hide');
    }

</script>

