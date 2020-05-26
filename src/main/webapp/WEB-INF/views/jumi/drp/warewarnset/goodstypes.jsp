<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="goodsTypeModal">选择商品分类</h4>
</div>
<div class="modal-body" style="height: 400px;overflow:auto">
    <div class="dd myadmin-dd" id="nestable-menu">
        <div class="panel">
            <div class="table-responsive">
                <table class="table table-hover manage-u-table" style="white-space: nowrap">
                    <thead>
                    <tr>
                        <th>
                            <div class="checkbox checkbox-info" style="margin-top: 0px; margin-bottom: -7px">
                                <input id="all" onclick="checkAll();" type="checkbox">
                                <label for="all"></label>
                            </div>
                        </th>
                        <th>分类ID</th>
                        <th>分类编码</th>
                        <th>分类名称</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="index" value="0" scope="request" /><!-- 自增序号，注意scope-->
                    <c:set var="level" value="0" scope="request" /><!-- 记录树的层次，注意scope-->
                    <c:import url="recursiongoodstypes.jsp" />
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-info" onclick="saveGoodsTypeIds()">确定</button>
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>

<script type="text/javascript">

    // 全选/取消全选
    function checkAll() {
        if ($("#all:checked").val() == 'on') {
            $("input[name='goodsTypeId']:checkbox").each(function(i, itwm){
                $(this).prop("checked","true");
            })
        } else {
            $("input[name='goodsTypeId']:checkbox").each(function(i, itwm){
                $(this).removeAttr('checked');
            })
        }
    }


    $(function () {
        checkOne();
    })

    // 单个选择
    function checkOne() {
        var total = $("input[name='goodsTypeId']:checkbox").length;
        var selected = $("input[name='goodsTypeId']:checkbox:checked").length;
        if (total == selected) {
            $("#all").prop("checked","true");
        } else {
            $("#all").removeAttr('checked');
        }
    }

    // 保存商品类型ids
    function saveGoodsTypeIds() {
        var goodsTypeIds = '';
        var goodsTypeNames = '';
        $("input[name='goodsTypeId']:checkbox:checked").each(function(i, itwm){
            var length = $("input[name='goodsTypeId']:checkbox:checked").length;
            var id = $(this).parents("tr").find("input[name='typeId']").val();
            var name = $(this).parents("tr").find("input[name='typeName']").val();
            if (i == length-1) {
                goodsTypeIds += id;
                goodsTypeNames += name;
            } else {
                goodsTypeIds += id + ',';
                goodsTypeNames += name + ';';
            }
        });
        $("input[name='goodsTypeIds']").val(goodsTypeIds);
        $("#selectedGoods").attr('title', goodsTypeNames);
        var shortGoodsTypeNames = goodsTypeNames.length > 60 ? goodsTypeNames.substring(0,60)+'...' : goodsTypeNames;
        $("#selectedGoods").val(shortGoodsTypeNames);
        $("#goodsTypeModal").modal('hide');
    }
</script>
