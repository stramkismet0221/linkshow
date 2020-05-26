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
                        <input type="text" class="form-control" name="actName" maxlength="50" value="${exCode.actName}" placeholder="请输入活动名称"/>
                    </div>
                    <div class="col-md-1" >
                        <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                        <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/excode/getexcodelist" id="form">
        <input id="hisActName1" type="hidden"/>
        <div class="row">
            <div class="col-md-12">
                <div class="white-box">
                    <div class="row">
                        <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4" >
                            <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/excode/toadd'"/>
                        </div>
                    </div>
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
                                                <div style="display: inline-block; width:10%">
                                                    <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/excode/getexcodebyid?exCodeId=${exCode.id}&type=1'"/>
                                                </div>
                                                &nbsp;
                                                <div style="display: inline-block; width:10%">
                                                    <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${exCode.id});" />
                                                </div>
                                                &nbsp;
                                                <div style="display: inline-block; width:10%">
                                                    <c:if test="${exCode.locked == 1}">
                                                        <input type="button" class="btn btn-block btn-danger btn-xs" value="解锁" onclick="lock(${exCode.id},0);" />
                                                    </c:if>
                                                    <c:if test="${exCode.locked == 0}">
                                                        <input type="button" class="btn btn-block btn-danger btn-xs" style="background: #53e69d;color: #fff" value="锁定" onclick="lock(${exCode.id},1);" />
                                                    </c:if>
                                                </div>
                                                &nbsp;
                                                <div style="display: inline-block; width:28%">
                                                    <input type="button" class="btn btn-block btn-default btn-xs" disabled value="第三方调用信息" onclick="location.href='${pageContext.request.contextPath}/excode/getexcodebyid?exCodeId=${exCode.id}&type=0'"/>
                                                </div>
                                                &nbsp;
                                                <div style="display: inline-block; width:25%">
                                                    <input type="button" class="btn btn-block btn-default btn-xs" disabled value="兑换流水明细" onclick="location.href='${pageContext.request.contextPath}/excode/getexcodebyid?exCodeId=${exCode.id}&type=0'"/>
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


    function skipToFirstPage() {
        skipPage(1);
    }
    function skipToPrePage() {
        var page = document.getElementById("page").value;
        var page = Number(page) - 1;
        skipPage1(page);
    }
    function skipToNextPage() {
        var page = document.getElementById("page").value;
        var page = Number(page) + 1;
        skipPage(page);
    }
    function skipToLastPage() {
        var page = document.getElementById("totalPage").value;
        skipPage1(page);
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
        var actName = $("#hisActName1").val();
        var data = {actName: actName, type: ${type}, totalPage: totalPage, page: page};
        $.ajax({
            url:'${pageContext.request.contextPath}/excode/getexcodelist',
            type:'post',
            async:false,
            data:data,
            success:function (data) {
                $("#ex").parents("#myTabContent").children("#excodelist").html(data)
            }
        });
    }

    // 查询
    function search() {
        var actName = $("input[name='actName']").val();
        $.ajax({
            url:'${pageContext.request.contextPath}/excode/getexcodelist',
            type:'post',
            async:false,
            data:{"actName":actName,"type":${type}},
            success:function (data) {
                $("#ex").parent().html(data);
            }
        });
    }
    // 删除
    function remove(id) {
        swal({
            title: "确定删除吗?",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/excode/modifyexcode",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id,"status":0,"type":2},
                    success : function(result) {
                        if (result.success) {
                            swal({title : "删除成功",
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/excode/toexcodelist?type=1";
                            });
                        }
                    },
                    error : function() {
                        swal({title : "删除失败",
                            confirmButtonText: "确定"
                        });
                    }
                });
            }
        });
    }

    function lock(id,locked) {

        swal({
            title: "是否锁定/解锁活动?",
            showCancelButton: true,
            confirmButtonText: "是",
            cancelButtonText: "否",
            closeOnConfirm: false,
            closeOnCancel: true
        }, function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url : "${pageContext.request.contextPath}/excode/modifyexcode",
                    type : "POST",
                    dataType : "json",
                    data : {"id":id,"locked":locked,"type":3},
                    success : function(result) {
                        var title;
                        if (locked === 1){
                            title = "锁定成功";
                        }
                        if (locked === 0){
                            title = "解锁成功";
                        }
                        if (result.success) {
                            swal({title : title,
                                confirmButtonText: "确定"}, function(){
                                location.href = "${pageContext.request.contextPath}/excode/toexcodelist?type=1";
                            });
                        }
                    },
                    error : function() {
                        if (locked === 1){
                            swal({title : "锁定失败",
                                confirmButtonText: "确定"
                            });
                        }
                        if (locked === 0) {
                            swal({title : "解锁失败",
                                confirmButtonText: "确定"
                            });
                        }

                    }
                });
            }
        });
    }
</script>