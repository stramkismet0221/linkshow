<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>

<div class="form-group">
    <label class="col-md-12"><label style="color: #f05b4f">*</label>&nbsp;分仓预警</label>
    <div class="col-md-12">
        <table class="table table-hover manage-u-table" style="white-space: nowrap">
            <thead style="background-color: #f2f2f2">
            <tr>
                <th>仓库</th>
                <th>最低库存预警值</th>
                <th>最高库存预警值</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${wareHouses}" var="data">
            <tr>
                <td style="padding-top: 25px;" title="${data.name}">
                    <c:set var="str" value="${data.name}"></c:set>
                    <c:choose>
                        <c:when test="${fn:length(str) > 12}">
                            <c:out value="${fn:substring(str, 0, 12)}......" />
                        </c:when>
                        <c:otherwise>
                            <c:out value="${str}" />
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <div class="form-group" style="margin-bottom: -10px">
                        <div class="col-md-12">
                            <input type="text" name="minStock" wareid="${data.id}" warename="${data.name}" value="${data.minStock}" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入最低库存预警值" data-error="请输入最低库存预警值" class="form-control" required="required"/>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group" style="margin-bottom: -10px">
                        <div class="col-md-12">
                            <input type="text" name="maxStock" wareid="${data.id}" warename="${data.name}" value="${data.maxStock}" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入最高库存预警值" data-error="请输入最高库存预警值" class="form-control" required="required"/>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>