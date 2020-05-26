<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>系统管理</title>
</head>
<body class="fix-sidebar">
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
        </svg>
    </div>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <%@ include file="/WEB-INF/views/include/header.jsp" %>
        </nav>
        <div class="navbar-default sidebar" role="navigation">
            <jsp:include page="/WEB-INF/views/include/left.jsp">
                <jsp:param value="/system/getsysteminfolist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">系统列表</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">系统管理</a></li>
                            <li class="active">系统列表</li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-md-3">
                                    <h5 class="m-t-30 m-b-10">按系统ID</h5>
                                    <input type="number" id="systemId" class="form-control" oninput="if(value.length>=10)value=value.slice(0,10)" placeholder="请输入系统ID" value="${systemInfo.id}"/>
                                </div>
                                <div class="col-md-3">
                                    <h5 class="m-t-30 m-b-10">按系统名称</h5>
                                    <input type="text" id="systemName" class="form-control" maxlength="50" placeholder="请输入系统名称" value="${systemInfo.name}"/>
                                </div>
                                <div class="col-md-3">
                                    <h5 class="m-t-30 m-b-10">按创建时间</h5>
                                    <input class="form-control input-daterange-datepicker" maxlength="50" type="text" onkeyup="getNullStr()" id="createTime" name="daterange" placeholder="请输入系统创建时间"/>
                                </div>
                                <div class="col-md-1">
                                    <h5 class="m-t-30 m-b-10">&nbsp;</h5>
                                    <input type="button" class="btn btn-block btn-info" value="查询" onclick="search();"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/system/getsysteminfolist" id="form">
                <input type="hidden" name="id" value="" />
                <input type="hidden" name="name" value="" />
                <input type="hidden" name="startTime" value="" />
                <input type="hidden" name="endTime" value="" />
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-lg-1 col-md-2 col-sm-4 col-xs-4" >
                                    <input type="button" value="新建" class="btn btn-block btn-info" onclick="location.href='${pageContext.request.contextPath}/system/addsysteminfo'"/>
                                </div>
                            </div>
                            <div class="panel">
                                <div class="table-responsive">
                                    <table class="table table-hover manage-u-table" style="white-space: nowrap">
                                        <thead>
                                            <tr>
                                                <th>系统ID</th>
                                                <th>系统名称</th>
                                                <th>系统描述</th>
                                                <th>创建时间</th>
                                                <th>修改时间</th>
                                                <th width="100">操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${systemInfos}" var="data">
                                            <tr>
                                                <td>${data.id}</td>
                                                <td title="${data.name}">
                                                    <c:set var="str" value="${data.name}"></c:set>
                                                    <c:choose>
                                                        <c:when test="${fn:length(str) > 15}">
                                                            <c:out value="${fn:substring(str, 0, 15)}......" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:out value="${str}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td title="${data.description}">
                                                    <c:set var="str" value="${data.description}"></c:set>
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
                                                    <fmt:formatDate value="${data.createTime}" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                    <fmt:formatDate value="${data.modifyTime}" pattern="yyyy-MM-dd"/>
                                                </td>
                                                <td>
                                                    <div>
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" style="background-color: green" data-toggle="modal" data-target=".bs-example-modal-lg" class="btn btn-block btn-primary btn-xs" value="详情" onclick="location.href='${pageContext.request.contextPath}/system/systeminfodetail?id=${data.id}'" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="修改" onclick="location.href='${pageContext.request.contextPath}/system/modifysysteminfo?id=${data.id}'"/>
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" class="btn btn-block btn-danger btn-xs" value="删除" onclick="remove(${data.id});" />
                                                        </div>
                                                        &nbsp;
                                                        <div style="display: inline-block; width:20%">
                                                            <input type="button" class="btn btn-block btn-info btn-xs" value="用户" onclick="location.href='${pageContext.request.contextPath}/system/addsystemuser?systemId=${data.id}'" />
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td colspan="6">
                                                    <div class="text-right">
                                                        <%@ include file="/WEB-INF/views/include/pagination.jsp" %>
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
                <div class="right-sidebar">
                    <%@ include file="/WEB-INF/views/include/right.jsp" %>
                </div>
            </div>
            <footer class="footer text-center">
                <%@ include file="/WEB-INF/views/include/footer.jsp" %>
            </footer>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            var startTime = "${startTime}";
            var endTime = "${endTime}";
            if (startTime != "" && endTime != "") {
                $("input[name='daterange']").val(startTime + " - " + endTime);
            } else {
                $("input[name='daterange']").val("");
            }

        })
        // 查询
        function search() {
            var id = $("#systemId").val();
            $("input[name='id']").val(id);
            var name = $("#systemName").val();
            $("input[name='name']").val(name);
            var dateRange = $("#createTime").val();
            if (dateRange != '' && dateRange != null) {
                var dateArr = dateRange.split(" - ");
                $("input[name='startTime']").val(dateArr[0]);
                $("input[name='endTime']").val(dateArr[1]);
            }
            $("#form").submit();
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
                        url : "${pageContext.request.contextPath}/system/removesysteminfo",
                        type : "POST",
                        dataType : "json",
                        data : {"id":id},
                        success : function(result) {
                            if (result.success) {
                                swal({title : "删除成功",
                                    confirmButtonText: "确定"}, function(){
                                    location.href = "${pageContext.request.contextPath}/system/getsysteminfolist";
                                });
                            } else {
                                swal({title : result.msg,
                                    cancelButtonText: "确定"});
                            }
                        },
                        error : function() {
                            alert("删除失败");
                        }
                    });
                }
            });
        }
    </script>
    <script>
        // Clock pickers
        $('#single-input').clockpicker({
            placement: 'bottom',
            align: 'left',
            autoclose: true,
            'default': 'now'
        });
        $('.clockpicker').clockpicker({
            donetext: 'Done',
        }).find('input').change(function() {
            console.log(this.value);
        });
        $('#check-minutes').click(function(e) {
            // Have to stop propagation here
            e.stopPropagation();
            input.clockpicker('show').clockpicker('toggleView', 'minutes');
        });
        if (/mobile/i.test(navigator.userAgent)) {
            $('input').prop('readOnly', true);
        }
        // Colorpicker
        $(".colorpicker").asColorPicker();
        $(".complex-colorpicker").asColorPicker({
            mode: 'complex'
        });
        $(".gradient-colorpicker").asColorPicker({
            mode: 'gradient'
        });
        // Date Picker
        jQuery('.mydatepicker, #datepicker').datepicker();
        jQuery('#datepicker-autoclose').datepicker({
            autoclose: true,
            todayHighlight: true
        });
        jQuery('#date-range').datepicker({
            toggleActive: true
        });
        jQuery('#datepicker-inline').datepicker({
            todayHighlight: true
        });
        // Daterange picker
        $('.input-daterange-datepicker').daterangepicker({

            maxDate: moment(), //最大时间
            opens: 'left',
            showDropdowns: true,
            buttonClasses: ['btn', 'btn-sm'],
            applyClass: 'btn-danger',
            cancelClass: 'btn-inverse',
            locale: {
                format: 'YYYY-MM-DD',
                applyLabel: '确定',
                cancelLabel: '取消',
                fromLabel: '起始时间',
                toLabel: '结束时间',
                customRangeLabel: '手动选择',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'
                ],
                firstDay: 1
            }
        });
        $('.input-daterange-timepicker').daterangepicker({
            timePicker: true,
            format: 'MM/DD/YYYY h:mm A',
            timePickerIncrement: 30,
            timePicker12Hour: true,
            timePickerSeconds: false,
            buttonClasses: ['btn', 'btn-sm'],
            applyClass: 'btn-danger',
            cancelClass: 'btn-inverse'
        });
        $('.input-limit-datepicker').daterangepicker({
            format: 'MM/DD/YYYY',
            minDate: '06/01/2015',
            maxDate: '06/30/2015',
            buttonClasses: ['btn', 'btn-sm'],
            applyClass: 'btn-danger',
            cancelClass: 'btn-inverse',
            dateLimit: {
                days: 6
            }
        });
    </script>
</body>
</html>
<script type="text/javascript">
    function getNullStr() {
        $("#createTime").val("");
    }
</script>