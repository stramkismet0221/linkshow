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
    <title>用户管理</title>
</head>
<body class="fix-header">
<nav class="navbar navbar-default navbar-static-top m-b-0">
    <%@ include file="/WEB-INF/views/include/header.jsp" %>
</nav>
<div class="navbar-default sidebar" role="navigation">
    <jsp:include page="/WEB-INF/views/include/left.jsp">
        <jsp:param value="/power/getpowerlist" name="visitUrl"/>
    </jsp:include>
</div>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">用户赋权</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="#"> 用户管理</a></li>
                    <li class="active">用户赋权</li>
                </ol>
            </div>
        </div>
        <!-- /row -->
        <div class="row">
            <div class="col-sm-12">
                <div class="white-box">
                    <div class="row">
                        <div class="col-md-4" style="width:30%">
                            <h5 style="width: 50%;display: inline">所属系统：<label style="font-weight: 600">${system.name}</label></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="white-box">
                    <div class="dd myadmin-dd" id="nestable-menu">
                        <input name="powerId" id="powerId" value="${powerId}" type="hidden">
                        <c:forEach var="menu" items="${menus}">
                            <ol class="dd-list" >
                                <li class="dd-item" data-id="${menu.id}">
                                    <div class="checkbox checkbox-info"
                                         <c:if test="${menu.childMenus.size() == 0}">style="left: 9%; padding-left: 0;"</c:if>
                                         <c:if test="${menu.childMenus.size() > 0}">style="left: 4%; padding-left: 0;"</c:if> >
                                        <div class="dd-item dd3-item" style="font-size: 16px;line-height: 18px;">
                                                    <input id="${menu.id}" onclick="checkChild(${menu.id})" <c:if test="${menu.selected == true}"> checked</c:if> type="checkbox">
                                                    <label for="${menu.id}" style="padding-top: 1%;"> </label>
                                                ${menu.name}
                                        </div>
                                    </div>

                                    <c:forEach var="secondMenu" items="${menu.childMenus}" >
                                        <ol class="dd-list" draggable="false" >
                                            <li class="dd-item" data-id="${secondMenu.id}">
                                                <div class="checkbox checkbox-info"  style="margin-left: 5%;">
                                                    <div class="dd-item dd3-item" style="padding-left: 5%;font-size: 16px;line-height: 18px;">
                                                        <input id="${secondMenu.id}" onclick="checkParent(${secondMenu.id},${menu.id})" name="parent${menu.id}" <c:if test="${secondMenu.selected == true}"> checked</c:if> type="checkbox">
                                                        <label for="${secondMenu.id}" style="padding-top: 1%;"> </label>
                                                            ${secondMenu.name}
                                                    </div>
                                                </div>

                                                <c:if test="${secondMenu.childMenus.size()>0}" >
                                                    <c:forEach var="thirdMenu" items="${secondMenu.childMenus}" >
                                                        <ol class="dd-list" draggable="false">
                                                            <li class="dd-item" data-id="${thirdMenu.id}">
                                                                <div class="checkbox checkbox-info">
                                                                    <div class="dd-item dd3-item" style="padding-left: 10%;font-size: 16px;line-height: 18px;">
                                                                        <input id="${thirdMenu.id}" onclick="checkParent(${thirdMenu.id},${secondMenu.id})" name="parent${menu.id}" <c:if test="${thirdMenu.selected == true}"> checked</c:if> type="checkbox">
                                                                        <label for="${thirdMenu.id}" style="padding-top: 1%;"> </label>
                                                                            ${thirdMenu.name}
                                                                    </div>
                                                                </div>
                                                            </li>
                                                        </ol>
                                                    </c:forEach>
                                                </c:if>
                                            </li>
                                        </ol>
                                    </c:forEach>
                                </li>
                            </ol>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-2 col-sm-4 col-xs-12" style="width: 10%;">
                <button class="btn btn-block btn-info text-uppercase waves-effect waves-light" type="button" onclick="save()">
                    <span style="vertical-align: inherit;">保存</span>
                </button>
            </div>
            <div class="col-lg-2 col-sm-4 col-xs-12" style="width: 10%;">
                <button class="btn btn-block btn-primary text-uppercase" onclick="history.back();">
                    <span style="vertical-align: inherit;">返回</span>
                </button>
            </div>
        </div>
        <div class="right-sidebar">
            <%@include file="/WEB-INF/views/include/right.jsp"%>
        </div>
    </div>
    <footer class="footer text-center">
        <%@include file="/WEB-INF/views/include/footer.jsp"%>
    </footer>
</div>
<script type="text/javascript" >
    $(document).ready(function() {

        // Nestable
        var updateOutput = function(e) {
            var list = e.length ? e : $(e.target),
                output = list.data('output');
            if (window.JSON) {
                // output.val(window.JSON.stringify(list.nestable('serialize'))); //, null, 2));
            } else {
                output.val('JSON browser support required for this demo.');
            }
        };
        $('#nestable').nestable({
            group: 1
        }).on('change', updateOutput);
        $('#nestable2').nestable({
            group: 1
        }).on('change', updateOutput);
        updateOutput($('#nestable').data('output', $('#nestable-output')));
        updateOutput($('#nestable2').data('output', $('#nestable2-output')));
        $('#nestable-menu').on('click', function(e) {
            var target = $(e.target),
                action = target.data('action');
            if (action === 'expand-all') {
                $('.dd').nestable('expandAll');
            }
            if (action === 'collapse-all') {
                $('.dd').nestable('collapseAll');
            }
        });
        $('#nestable-menu').nestable();


        /*$.ajax({
            url:'',
            type:'post',
            async:false,
            dataType: 'json',
            success:function (data) {
                $("#treeview4").treeview({
                    data:data
                })
            }
        })*/

    });

    //保存
    function save() {
        var powerId = $("#powerId").val();
        var menuIds = [];
        $("input:checked").each(function () {
            var id = $(this).attr("id");
            menuIds.push(id);
        });

        // ajax提交
        $.ajax({
            url : "${pageContext.request.contextPath}/power/addpowermenus",
            type : "POST",
            dataType : "json",
            traditional:true,
            data : {"powerId":powerId,"menuIds":menuIds},
            success : function(result) {
                if (result.success) {
                    swal({title : result.msg,
                        confirmButtonText: "确定"}, function(){
                        location.href = "${pageContext.request.contextPath}/power/getpowerlist";
                    });
                }else {
                    swal({title : result.msg,
                        confirmButtonText: "确定"});
                }
            },
            error : function() {
                swal({title : "保存失败",
                    confirmButtonText: "确定"});
            }
        });
    }

    function checkChild(id) {
        var ls = $("#"+id).prop("checked");
        var parent = "name='parent"+id+"'";
        if (ls){
            $("input["+parent+"]").each(function () {
                $(this).prop("checked","true")
            })
        }else {
            $("input["+parent+"]").each(function () {
                $(this).removeAttr("checked");
            })
        }
    }

    function checkParent(id,pid) {
        var ll = $("#"+id).prop("checked");
        var ls = $("#"+pid).prop("checked");
        if (ll){
            if (!ls){
                $("input[id="+pid+"]").each(function () {
                    $(this).prop("checked","true")
                })
            }
        }
    }
</script>
</body>
</html>
