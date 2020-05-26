<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<div id="data_content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="#machineModal">设备列表</h4>
    </div>

    <input name="machineIds1" type="hidden" value="${ids}">
    <div class="modal-body" style="width: 900px;overflow:auto;padding-bottom: 0;margin-bottom: -15px;" >
            <div class="row">
                <div class="col-md-7">
                    <%--已选择<span id="count"></span>项--%>
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" name="keyword" maxlength="50" placeholder="请输入查询条件" value="${keyword}"/>
                </div>
                <div class="col-md-2" style="float: right">
                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                </div>
            </div>
        <form id="machineForm">
            <input type="hidden" id="totalPage" name="totalPage" value="${pagination.totalPage}" />
            <input type="hidden" id="page" name="page" value="${pagination.page}" />
            <input name="keyword"  type="hidden" />
        <div class="panel" style="margin-top: 6px;">
            <div class="table-responsive">
                <table id="machine" class="table table-hover manage-u-table" style="white-space: nowrap;margin: -1px;">
                    <thead>
                    <tr style="background-color: #e9eaec">
                        <th width="5"></th>
                        <th width="40" style="padding-bottom: 0;">
                            <div class="checkbox checkbox-info">
                                <input id="checkbox5" onclick="checkAll()" type="checkbox">
                                <label for="checkbox5"></label>
                            </div>
                        </th>
                        <th>设备名称</th>
                        <th>设备编号</th>
                        <th>生产编号</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${terminalList}" var="data">
                        <tr class="advance-table-row ">
                            <td width="5"><input value="${data.id}" name="machineid" type="hidden"></td>
                            <td width="40">
                                <c:choose>
                                    <c:when test="${ids.contains(data.id)}">
                                        <div class="checkbox checkbox-info">
                                            <input  name="aa" checked type="checkbox" onclick="chooseOne()">
                                            <label> </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="checkbox checkbox-info">
                                            <input id="" name="aa" type="checkbox" onclick="chooseOne()">
                                            <label > </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${data.name}</td>
                            <td>${data.deviceCode}</td>
                            <td>${data.cabinetCount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="5">
                            <div class="text-right">
                                <ul class="pagination pagination-split">
                                    <c:if test="${pagination.totalPage>1}">
                                        <!-- 总页数大于1页显示上一页和下一页 -->
                                        <c:if test="${pagination.page>1 && pagination.totalPage>1}">
                                            <li><a href="javascript:skipToPrePage();"><i class="fa fa-angle-left"></i></a></li>
                                        </c:if>

                                        <c:forEach items="${pagination.pagesGroup}" var="page" varStatus="status">
                                            <c:if test="${page == pagination.page}">
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
        <button type="button" class="btn btn-info" onclick="saveMachine()">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</div>
<script type="text/javascript">
    jQuery(document).ready(function() {

        var checked = $("input[name='aa']:checkbox:checked").not("input[id='checkbox5']").length;
        var count = $("#machine tbody tr").length;
        if (count === checked && count !== 0 && checked !== 0){
            $("input[id='checkbox5']").prop("checked","true")
        }else {
            $("input[id='checkbox5']").removeAttr("checked")
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
        var ids = $("input[name='machineIds1']").val();
        $("input[name='aa']:checkbox:checked").each(function(){
            var data = $(this).parents("tr").children("td").children("input[id='checkbox5']").context.id;
            if (data === "checkbox5"){
                return true;
            }
            var id = $(this).parents("tr").children("td").find("input[name='machineid']").val();
            if (ids.indexOf(id) === -1){
                ids += id + ",";
            }
        });

        var keyword = $("input[name='keyword']").val();

        $.ajax({
            url:'${pageContext.request.contextPath}/excode/getmachinelist',
            type:'post',
            dataType:'html',
            data:{"totalPage":$("#totalPage").val(), "page":$("#page").val(),"ids":ids,"keyword":keyword},
            success:function (data) {
                $("#data_content").html(data);
            }
        })
    }

    var index = 0;
    function checkAll() {
        if (index === 0){
            $('input[name="aa"]').prop("checked","true");
            index = index+1;
            return;
        }

        if (index !== 0){
            $('input[name="aa"]').removeAttr('checked');
            index = 0;
        }
    }

    function saveMachine() {

        //获取模态框 隐藏域 中的设备id
        var machineIds = $("input[name='machineIds1']").val();
        var tableData = "";
        //获取父级中的设备列表table body
        var tbody = $("#data_content").parents(".col-md-8").find("tbody[id='machine_tbody']").html();
        $("input[name='aa']:checkbox:checked").each(function(){
            var data = $(this).parents("tr").children("td").children("input[id='checkbox5']").context.id;
            if (data === "checkbox5"){
                return true;
            }
            var id = $(this).parents("tr").children("td").find("input[name='machineid']").val();
            var name = $(this).parents("tr").children("td")[2].innerText;
            var code = $(this).parents("tr").children("td")[3].innerText;
            if (tbody.indexOf(code) !== -1){
                return true;
            }else {
                machineIds += id + ",";
            }
            tableData += "<tr>" +
                "<td>" + "<input type=\"hidden\" name=\"machineId\" value=\""+id+"\">" +"</td>" +
                "<td>" + name +"</td>" +
                "<td>" + code +"</td>" +
                "<td><button onclick='deleteRow(this)' class=\"btn btn-info btn-outline btn-circle btn-lg m-r-5\" style='height: 20px;padding: 0;'>" +
                "<i class=\"ti-trash\"></i>" +
                "</button >" +
                "</td>" +
                "</tr>"
        });
        $("#data_content").parents().find("input[name='machineIds']").val(machineIds);
        $("#data_content").parents(".col-md-8").find("tbody[id='machine_tbody']").append(tableData);
        $("#machineModal").modal('hide');
    }

    function search() {
        var keyword = $("input[name='keyword']").val();
        var machineIds = $("input[name='machineIds']").val();
        $("input[name='aa']:checkbox:checked").each(function(){
            var data = $(this).parents("tr").children("td").children("input[id='checkbox5']").context.id;
            if (data === "checkbox5"){
                return true;
            }
            var machineId = $(this).parents("tr").children("td").find("input[name='machineid']").val();
            if (machineIds.indexOf(machineId) === -1){
                machineIds += machineId + ",";
            }
        });
        $("input[name='machineIds']").val(machineIds);
        $.ajax({
            url:'${pageContext.request.contextPath}/excode/getmachinelist',
            type:'post',
            dataType:'html',
            data:{"keyword":keyword,"ids":machineIds},
            success:function (data) {
                $("#data_content").html(data);
            }
        })
    }


    function chooseOne() {
        var checked = $("input[name='aa']:checkbox:checked").not("input[id='checkbox5']").length;
        var count = $("#machine tbody tr").length;
        if (count === checked){
            $("input[id='checkbox5']").prop("checked","true");
            index = 1;
        }else {
            $("input[id='checkbox5']").removeAttr("checked");
            index = 0;
        }
    }
</script>