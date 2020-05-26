<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<div id="goodsInfoList">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="goodsTypeModal">选择商品</h4>
</div>
<div class="modal-body" style="height: 530px;overflow:auto">
    <div>
        <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-4">
                <select id="goodsTypeId" class="form-control" data-placeholder="请选择商品分类" tabindex="1">
                    <option value="">请选择分类</option>
                    <c:forEach items="${goodsTypes}" var="data">
                        <option value="${data.id}" <c:if test="${data.id == goodsTypeId}"> selected </c:if>>
                            <c:forEach var="i" begin="2" end="${data.level}" step="1">
                                --
                            </c:forEach>
                            ${data.name}
                        </option>
                        <c:set var="goodsTypeId" value="${goodsTypeId}" scope="request"></c:set>
                        <%@include file="recursiveOptionsforlist.jsp"%>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control" id="keyword" maxlength="50" placeholder="请输入商品名/条码" value="${keyword}"/>
            </div>
            <div class="col-md-2" style="float: right">
                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
            </div>
        </div>
        <form id="goodsForm">
        <input type="hidden" id="totalPage" name="totalPage" value="${pagination.totalPage}" />
        <input type="hidden" id="page" name="page" value="${pagination.page}" />
        <input type="hidden" name="keyword" value="${keyword}"/>
        <input type="hidden" name="goodsTypeId" value="${goodsTypeId}"/>
        <div class="panel">
            <div class="table-responsive">
                <table class="table table-hover manage-u-table" style="white-space: nowrap; margin-bottom: -40px;">
                    <thead>
                    <tr>
                        <th>
                            <div class="checkbox checkbox-info" style="margin-top: 0px; margin-bottom: -7px">
                                <input id="all" onclick="checkAll();" type="checkbox">
                                <label for="all"></label>
                            </div>
                        </th>
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>分类</th>
                        <th>库存</th>
                        <th>单位</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${goodsList}" var="data">
                        <tr>
                            <input type="hidden" name="id" value="${data.id}"/>
                            <input type="hidden" name="name" value="${data.name}"/>
                            <td>
                                <div class="checkbox checkbox-info" style="margin-top: 10px; margin-bottom: -7px">
                                    <input name="goodsId" type="checkbox" onclick="checkOne();"} ${cur.checked?"checked":""}>
                                    <label > </label>
                                </div>
                            </td>
                            <td>
                                <a href="${data.icons.split(",")[0]}" target="_blank"><img src="${data.icons.split(",")[0]}" style="width: 25px; height: 35px"  alt="" /></a>
                            </td>
                            <td title="${data.name}">
                                ${data.barCode}
                                <br/>
                                <c:set var="str" value="${data.name}"></c:set>
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
                                <div style="margin-top: 10px;">${data.price}</div>
                            </td>
                            <td>
                                <div style="margin-top: 10px;">分类</div>
                            </td>
                            <td>
                                <div style="margin-top: 10px;">待开发</div>
                            </td>
                            <td>
                                <div style="margin-top: 10px;">${data.unitName}</div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="7">
                            <div class="text-right">
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
                            </div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        </form>
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-info" onclick="saveGoodsIds()">确定</button>
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
</div>

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
        // keyword 放入form表单中
        var keyword = $("#keyword").val();
        $("input[name='keyword']").val(keyword);
        // 商品分类
        var goodsTypeId = $("#goodsTypeId").val();
        $("input[name='goodsTypeId']").val(goodsTypeId);

        $.ajax({
            url:'${pageContext.request.contextPath}/jumi/common/getgoodslist',
            type:'post',
            dataType:'html',
            data:{"totalPage":totalPage, "page":page, keyword: keyword, goodsTypeId: goodsTypeId},
            success:function (data) {
                $("#goodsInfoList").html(data);
            }
        })
        getGoodsIds();
    }

    // 查询
    function search() {
        // keyword 放入form表单中
        var keyword = $("#keyword").val();
        $("input[name='keyword']").val(keyword);
        // 商品分类
        var goodsTypeId = $("#goodsTypeId").val();
        $("input[name='goodsTypeId']").val(goodsTypeId);
        $.ajax({
            url:'${pageContext.request.contextPath}/jumi/common/getgoodslist',
            type:'post',
            dataType:'html',
            data: $("#goodsForm").serialize(),
            success:function (data) {
                $("#goodsInfoList").html(data);
            }
        })
    }

    // 全选/取消全选
    var index = 0;
    function checkAll() {
        if (index === 0){
            $('input[name="goodsId"]').prop("checked","true");
            index = index+1;
            return;
        }
        if (index !== 0){
            $('input[name="goodsId"]').removeAttr('checked');
            index = 0;
        }
    }

    $(function () {
        checkOne();
//        saveGoodsIds();
    })

    // 单个选择
    function checkOne() {
        var total = $("input[name='goodsId']:checkbox").length;
        var selected = $("input[name='goodsId']:checkbox:checked").length;
        if (total == selected) {
            $("#all").prop("checked","true");
        } else {
            $("#all").removeAttr('checked');
        }
    }

    function getGoodsIds() {
        var goodsIds =  $("input[name='goodsIds']").val();
        var goodsNames =  $("input[name='goodsNames']").val();
        $("input[name='goodsId']:checkbox:checked").each(function(i, itwm){
            var id = $(this).parents("tr").find("input[name='id']").val();
            var name = $(this).parents("tr").find("input[name='name']").val();
            if (goodsIds != '' && goodsIds != null) {
                var flg = true;
                var goodsIdsArr = goodsIds.split(",");
                for (var i = 0; i < goodsIdsArr.length; i++) {
                    if (goodsIdsArr[i] == id) {
                        flg = false;
                        return;
                    }
                }
                if (flg) {
                    goodsIds += id + ',';
                    goodsNames += name + ";";
                }
            } else {
                goodsIds += id + ',';
                goodsNames += name + ";";
            }
        });
        $("input[name='goodsIds']").val(goodsIds);
        $("input[name='goodsNames']").val(goodsNames);
        $("#selectedGoods").val(goodsNames);
    }

    // 保存商品ids
    function saveGoodsIds() {
        getGoodsIds();
        $("#goodsModal").modal('hide');
    }

</script>
