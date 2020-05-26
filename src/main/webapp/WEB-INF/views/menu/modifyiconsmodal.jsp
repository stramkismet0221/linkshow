<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<div id="menu_icons">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="#updateIconsModal">选择菜单图标</h4>
    </div>
    <div class="modal-body" id="modifyModal" style="height:500px;overflow:auto;padding-bottom: 0;margin-bottom: -15px;" >

    </div>
    <div class="modal-footer" style="text-align: right;">
        <button type="button" class="btn btn-info" onclick="updateMenuIcon()">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $.ajax({
            type: "POST",
            async: false, //同步请求
            url: "${pageContext.request.contextPath}/menu/modifyicons",
            dataType: "html",
            data: {"code": '${code}'},
            success: function (result) {
                $("#modifyModal").html(result);
            }
        })
    })
</script>
