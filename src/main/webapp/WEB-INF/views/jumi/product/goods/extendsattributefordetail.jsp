<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<div class="collapse" id="collapseExample1">
    <div class="panel-body">
        <div class="panel-body" style="padding: 10px;">

            <c:if test="${goods.type == 1}">
                <div class="form-group">
                    <label class="col-md-6">促销语</label>
                    <label class="col-md-6">存储条件</label>
                    <div class="col-md-6">
                        <input type="text" name="promote" class="form-control"  value="${goods.promote}" disabled/>
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="storage" class="form-control" value="${goods.storage}" disabled/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-6">有效期</label>
                    <label class="col-md-6">临近预警</label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" name="validityDay" onkeyup="btnonkeyup(this,value)" class="form-control" value="${goods.validityDay}" disabled/>
                            <span class="input-group-addon">天</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" name="advanceDay" class="form-control"
                                   placeholder="请输入提前天数" value="${goods.advanceDay}" disabled/>
                            <span class="input-group-addon">天</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-6">品牌</label>
                    <label class="col-md-6">供货商</label>
                    <div class="col-md-6">
                        <select class="form-control" name="brandId" disabled>
                            <c:forEach items="${goodsBrands}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.brandId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <select class="form-control" name="supplierId" disabled>
                            <c:forEach items="${goodsSuppliers}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.supplierId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-6">生产厂家</label>
                    <label class="col-md-6">分组</label>
                    <div class="col-md-6">
                        <select class="form-control" name="manufacturerId" disabled>
                            <c:forEach items="${jmGoodsManuFatories}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.manufacturerId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <select class="form-control" name="groupId" disabled>
                            <c:forEach items="${goodsGroups}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.groupId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:if>

            <c:if test="${goods.type == 2}">
                <div class="form-group">
                    <label class="col-md-6">促销语</label>
                    <label class="col-md-6">有效期限</label>
                    <div class="col-md-6">
                        <input type="text" name="promote" class="form-control"  value="${goods.promote}" disabled/>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" name="validityDay" onkeyup="btnonkeyup(this,value)" class="form-control" value="${goods.validityDay}" disabled/>
                            <span class="input-group-addon">天</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-6">分组</label>
                    <label class="col-md-6">&nbsp;</label>
                    <div class="col-md-6">
                        <select class="form-control" name="groupId" disabled>
                            <c:forEach items="${goodsGroups}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.groupId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6"></div>
                </div>
            </c:if>


            <c:if test="${goods.type == 4}">
                <div class="form-group">
                    <label class="col-md-6">存储条件</label>
                    <label class="col-md-6">&nbsp;</label>
                    <div class="col-md-6">
                        <input type="text" name="storage" class="form-control" value="${goods.storage}" disabled/>
                    </div>
                    <div class="col-md-6"></div>
                </div>
                <div class="form-group">
                    <label class="col-md-6">有效期</label>
                    <label class="col-md-6">临近预警</label>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" name="validityDay" onkeyup="btnonkeyup(this,value)" class="form-control" value="${goods.validityDay}" disabled/>
                            <span class="input-group-addon">天</span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" name="advanceDay" class="form-control"
                                   placeholder="请输入提前天数" value="${goods.advanceDay}" disabled/>
                            <span class="input-group-addon">天</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-6">品牌</label>
                    <label class="col-md-6">供货商</label>
                    <div class="col-md-6">
                        <select class="form-control" name="brandId" disabled>
                            <c:forEach items="${goodsBrands}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.brandId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <select class="form-control" name="supplierId" disabled>
                            <c:forEach items="${goodsSuppliers}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.supplierId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-6">生产厂家</label>
                    <label class="col-md-6">&nbsp;</label>
                    <div class="col-md-6">
                        <select class="form-control" name="manufacturerId" disabled>
                            <c:forEach items="${jmGoodsManuFatories}" var="data">
                                <option value="${data.id}" <c:if test="${data.id == goods.manufacturerId}"> selected </c:if> >${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6"></div>
                </div>
            </c:if>
        </div>
    </div>
</div>