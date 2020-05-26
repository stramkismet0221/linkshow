<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<div class="collapse" id="collapseExample">
    <div class="panel-body">
        <div class="panel-body" style="padding: 10px;">
            <c:forEach var="extend" items="${jmGoodsExtends}">
                <div class="form-group col-md-12">
                        <%-- 文本框 --%>
                    <c:if test="${extend.fieldType == 1}">
                        <label class="col-md-12">${extend.name}</label>
                        <div class="col-md-12">
                            <input type="text" name="${extend.name}" maxlength="10" must="${extend.isNotNull}"
                                    <c:set var="k1" value="${extend.name}"></c:set>
                                    <c:if test="${not empty arr}">
                                        <c:forEach items="${arr}" var="ex">
                                            <c:set var="key" value="${ex.key}"></c:set>
                                            <c:if test="${k1.equals(key)}">
                                                value="${ex.value}"
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                   class="extend-text form-control" disabled/>
                        </div>
                    </c:if>
                        <%-- 下拉选项 --%>
                    <c:if test="${extend.fieldType == 2}">
                        <label class="col-md-12">${extend.name}</label>
                        <div class="col-md-12">
                            <select class="extend-select form-control" name="${extend.name}" must="${extend.isNotNull}" disabled>
                                <c:set var="k2" value="${extend.name}"></c:set>
                                <c:forEach items="${arr}" var="ex">
                                    <c:if test="${k2.equals(ex.key)}">
                                        <c:set var="v2" value="${ex.value}"></c:set>
                                    </c:if>
                                </c:forEach>
                                <c:forEach items="${extend.items}" var="item">
                                    <option value="${item}" <c:if test="${v2.equals(item)}"> selected </c:if>>${item}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                        <%--单选--%>
                    <c:if test="${extend.fieldType == 3}">
                        <div class="radio-list" style="margin-left: -20px">
                            <label class="col-md-12  radio-inline ">
                                <label class="col-md-12">${extend.name}</label>
                            </label>
                            <div class="col-md-12">
                                <c:forEach items="${extend.items}" var="item">
                                    <c:set var="k3" value="${extend.name}"></c:set>
                                    <c:forEach items="${arr}" var="ex">
                                        <c:if test="${k3.equals(ex.key)}">
                                            <c:set var="v3" value="${ex.value}"></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <label class="radio-inline">
                                        <div class="radio radio-info">
                                            <input type="radio" name="${extend.name}" id="${item}" value="${item}" class="extend-radio" must="${extend.isNotNull}" disabled
                                            <c:if test="${v3.equals(item)}"> checked </c:if>/>
                                            <label for="${item}">${item}</label>
                                        </div>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                        <%--多选--%>
                    <c:if test="${extend.fieldType == 4}">
                        <label class="col-md-12" style="margin-bottom: 15px;">${extend.name}</label>
                        <div class="col-sm-12">
                            <c:forEach items="${extend.items}" var="check" >
                                <div class="checkbox checkbox-info" style="display: inline-block; margin-right: 5%;">
                                    <input class="extend-checkbox " type="checkbox" name="${extend.name}" value="${check}" must="${extend.isNotNull}" disabled
                                            <c:if test="${checkAns.contains(check)}"> checked </c:if>/>
                                    <label for="${extend.name}">${check}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                        <%--日期--%>
                    <c:if test="${extend.fieldType == 5}">
                        <label class="col-md-12" style="margin-bottom: 15px;">${extend.name}</label>
                        <div class="col-md-12" id="cutoffTime" style="margin-bottom: 40px;">
                            <div class="input-group">
                                <input type="text" name="${extend.name}" class="extend-text form-control" id="datepicker-autoclose" must="${extend.isNotNull}" disabled
                                    <c:set var="k5" value="${extend.name}"></c:set>
                                    <c:if test="${not empty arr}">
                                        <c:forEach items="${arr}" var="ex">
                                            <c:set var="key" value="${ex.key}"></c:set>
                                            <c:if test="${k5.equals(key)}">
                                                   value="${ex.value}"
                                            </c:if>
                                        </c:forEach>
                                    </c:if>/>
                                <span class="input-group-addon"><i class="icon-calender"></i></span>
                                <div class="help-block with-errors"></div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</div>