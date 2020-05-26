<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<div id="data_content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="#productModal">商品列表</h4>
    </div>

    <input name="productIds" type="hidden" value="${barCodes}">
    <%--<input name="count" type="text" value="${count}">--%>
    <div class="modal-body" style="width: 900px;overflow:auto;padding-bottom: 0;margin-bottom: -15px;" >
        <div class="row">
            <div class="col-md-7">
                <%--已选择<span id="count"></span>项--%>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control" id="keyword" maxlength="50" placeholder="请输入查询条件" value="${keyword}"/>
            </div>
            <div class="col-md-2" style="float: right">
                <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
            </div>
        </div>
        <form id="productForm">
            <input type="hidden" id="totalPage" name="totalPage" value="${pagination.totalPage}" />
            <input type="hidden" id="page" name="page" value="${pagination.page}" />
            <input type="hidden" name="keyword" value="${keyword}"/>
            <div class="panel" style="margin-top: 6px;">
                <div class="table-responsive">
                    <table id="product" class="table table-hover manage-u-table" style="white-space: nowrap;margin: -1px;">
                        <thead>
                        <tr style="background-color: #e9eaec">
                            <th width="5"></th>
                            <th width="40" style="padding-bottom: 0;">
                                <div class="checkbox checkbox-info">
                                    <input id="checkbox4"
                                           onclick="checkAll()" type="checkbox">
                                    <label for="checkbox4"></label>
                                </div>
                            </th>
                            <th width="25%">图片</th>
                            <th width="25%">条形码</th>
                            <th width="25%">商品名称</th>
                            <th width="25%">分类名称</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${products}" var="data">
                            <tr class="advance-table-row ">
                                <td width="5"></td>
                                <td width="40">
                                    <c:choose>
                                        <c:when test="${barCodes.contains(data.barCode)}">
                                            <div class="checkbox checkbox-info">
                                                <input  name="cc" checked type="checkbox" onclick="chooseOne()">
                                                <label> </label>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="checkbox checkbox-info">
                                                <input id="" name="cc" type="checkbox" onclick="chooseOne()" >
                                                <label > </label>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="${data.imageUrl}" target="_blank"><img src="${data.imageUrl}" style="width: 25px; height: 35px"  alt="" /></a>
                                <td >${data.barCode}</td>
                                <td title="${data.name}">
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
                                <td >${data.categoryName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="6">
                                <div class="text-right">
                                    <ul class="pagination pagination-split">
                                        <c:if test="${pagination.totalPage>1}">
                                            <!-- 总页数大于1页显示上一页和下一页 -->
                                            <c:if test="${pagination.page>1 && pagination.totalPage>1}">
                                                <li><a href="javascript:skipToPrePage();"><i class="fa fa-angle-left"></i></a></li>
                                            </c:if>

                                            <c:forEach items="${pagination.pagesGroup}" var="page" varStatus="status">
                                                <c:if test="${page == pagination.page}">
                                                    <%--<input name="prePage" type="text" value="${pagination.page}"/>--%>
                                                    <li class="active"><a  href="javascript:void(0)">${pagination.page}</a></li>
                                                </c:if>
                                                <c:if test="${page != pagination.page}">
                                                    <li><a href="javascript:skipPage('${page}');">${page}</a></li>
                                                </c:if>
                                            </c:forEach>

                                            <c:if test="${pagination.totalPage > 1 && pagination.page<pagination.totalPage}">
                                                <li><a href="javascript:skipToNextPage();"><i class="fa fa-angle-right"></i></a></li>
                                            </c:if>
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
    <div class="modal-footer" style="text-align: right;">
        <button type="button" class="btn btn-info" onclick="saveProduct()">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</div>
<script type="text/javascript">

    jQuery(document).ready(function() {
        var checked = $(":checkbox:checked").not("input[id='checkbox4']").length;
        var count = $("#product tbody tr").length;
        if (count === checked){
            $("input[id='checkbox4']").prop("checked","true")
        }else {
            $("input[id='checkbox4']").removeAttr("checked")
        }
    });

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
        var barCodes = $("input[name='productIds']").val();
        $(":checkbox:checked").each(function(){
            var data = $(this).parents("tr").children("td").children("input[id='checkbox4']").context.id;
            if (data === "checkbox4"){
                return true;
            }
            var barCode = $(this).parents("tr").children("td")[3].innerText;
            if (barCodes.indexOf(barCode) === -1){
                barCodes += barCode + ",";
            }
        });

        var keyword = $("input[name='keyword']").val();
        $.ajax({
            url:'${pageContext.request.contextPath}/coupon/getproductlist',
            type:'post',
            dataType:'html',
            data:{"totalPage":$("#totalPage").val(), "page":$("#page").val(),"barCodes":barCodes,"keyword":keyword},
            success:function (data) {
                $("#data_content").html(data);
            }
        })
    }

    var index = 0;
    function checkAll() {
        console.log(index);
        if (index === 0){
            $('input[name="cc"]').prop("checked", "true");
            index = index + 1;
            return;
        }
        if (index !== 0){
            $('input[name="cc"]').removeAttr('checked');
            index = 0;
        }
    }

    function saveProduct() {
        var productids = $("input[name='productIds']").val();
        var tableDate = "";
        var tbody = $("#data_content").parents(".col-md-8").find("tbody[id='product_tbody']").html();
        $(":checkbox:checked").each(function(){
            var data = $(this).parents("tr").children("td").children("input[id='checkbox4']").context.id;
            if (data === "checkbox4"){
                return true;
            }
            var code = $(this).parents("tr").children("td")[3].innerText;
            var name = $(this).parents("tr").children("td")[4].innerText;
            if (tbody.indexOf(code) !== -1){
                return true;
            }
            productids += code +',';
            tableDate += "<tr>" +
                "<td>" + name +"</td>" +
                "<td>" + code +"</td>" +
                "<td><button onclick='deleteRow(this)' class=\"btn btn-info btn-outline btn-circle btn-lg m-r-5\" style='height: 20px;padding: 0;'>" +
                "<i class=\"ti-trash\"></i>" +
                "</button>" +
                "</td>" +
                "</tr>"
        });
        $("#data_content").parents(".col-md-8").find("tbody[id='product_tbody']").append(tableDate);
        $("#productmodal").modal('hide');
    }


    // 查询
    function search() {
        var keyword = $("#keyword").val();
        $("input[name='keyword']").val(keyword);

        var barCodes = $("input[name='productIds']").val();
        $(":checkbox:checked").each(function(){
            var data = $(this).parents("tr").children("td").children("input[id='checkbox4']").context.id;
            if (data === "checkbox4"){
                return true;
            }
            var barCode = $(this).parents("tr").children("td")[3].innerText;
            if (barCodes.indexOf(barCode) === -1){
                barCodes += barCode + ",";
            }
        });
        $("input[name='productIds']").val(barCodes);
        $.ajax({
            url:'${pageContext.request.contextPath}/coupon/getproductlist',
            type:'post',
            dataType:'html',
            data:$("#productForm").serialize(),
            success:function (data) {
                $("#data_content").html(data);
            }
        })
    }


    function chooseOne() {
        var checked = $(":checkbox:checked").not("input[id='checkbox4']").length;
        var count = $("#product tbody tr").length;
        if (count === checked){
            $("input[id='checkbox4']").prop("checked","true");
            index = 1;
        }else {
            $("input[id='checkbox4']").removeAttr("checked");
            index = 0;
        }
    }
</script>