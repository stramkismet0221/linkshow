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
    <link rel="icon" type="image/png" sizes="16x16" href="../../../common/adm/plugins/images/favicon.png">
    <title>供应商详情</title>
</head>
<script src="../common/adm/plugins/bower_components/jquery/dist/jquery.min.js"></script>
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
            <jsp:param value="/jumi/product/goodssupplier/list" name="visitUrl"/>
        </jsp:include>
    </div>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">供应商详情</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">供应商管理</a></li>
                        <li class="active">供应商详情</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box p-l-20 p-r-20">
                        <p class="text-muted m-b-30 font-13"></p>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-8">
                                <form id="form" class="form-material form-horizontal" data-toggle="validator">
                                    <div class="form-group">
                                        <label class="col-md-12">供货商ID</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${goodssupplier.id}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">供货商名称</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${goodssupplier.name}" disabled/>
                                        </div>
                                    </div>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-md-12">状态</label>--%>
                                        <%--<div class="radio-list">--%>
                                            <%--<label class="radio-inline" style="margin-left: 15px;">--%>
                                                <%--<input type="radio" name="states" value="0" <c:if test="${goodssupplier.states==0}">checked=""</c:if> disabled="">禁用</label>--%>
                                            <%--<label class="radio-inline">--%>
                                                <%--<input type="radio" name="states" value="1" <c:if test="${goodssupplier.states==1}">checked=""</c:if> disabled="">启用</label>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>

                                    <div class="form-group">
                                        <label class="col-md-12">&nbsp;状态</label>
                                        <div class="radio radio-info" style="display: inline-block; margin-left: 15px; margin-right: 5%;">
                                            <input type="radio" name="states" id="radio1" <c:if test="${goodssupplier.states==0}">checked=""</c:if> disabled="" value="0">
                                            <label for="radio1"> 禁用 </label>
                                        </div>
                                        <div class="radio radio-info" style="display: inline-block; margin-right: 5%;">
                                            <input type="radio" name="states" id="radio2" value="1" <c:if test="${goodssupplier.states==1}">checked=""</c:if> disabled="">
                                            <label for="radio2"> 启用 </label>
                                        </div>
                                    </div>












                                    <div class="form-group">
                                        <label class="col-md-12">联系人</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${goodssupplier.contacts}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">联系电话</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${goodssupplier.telephone}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">邮箱</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${goodssupplier.email}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group" >
                                        <label class="col-md-12">经营模式</label>
                                        <div class="col-md-12" >
                                            <input type="text" class="form-control" value="${goodssupplier.mngmodel}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">结款方式</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${goodssupplier.paymentType}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">送货方式</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${goodssupplier.deliveryType}" disabled/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-12">配送费</label>
                                        <div class="col-md-12">
                                            <input type="text" class="form-control" value="${deliveryMoney}" disabled/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">固定返点</label>
                                        <div class="input-group" style="padding-left: 15px;">
                                            <input type="text"  value="${returnPoint}" class="form-control"       aria-describedby="basic-addon3" disabled>
                                            <span class="input-group-addon" id="basic-addon3">%</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">配送返点</label>
                                        <div class="input-group" style="padding-left: 15px;">
                                            <input type="text" name="deliveryPoint" value="${deliveryPoint}" class="form-control"       aria-describedby="basic-addon2" disabled>
                                            <span class="input-group-addon" id="basic-addon2">%</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">发票税点</label>
                                        <div class="input-group" style="padding-left: 15px;">
                                            <input type="text"   value="${taxPoint}" class="form-control"       aria-describedby="basic-addon4" disabled>
                                            <span class="input-group-addon" id="basic-addon4">%</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">结算银行账户信息</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="1000" class="form-control" rows="5"  disabled>${goodssupplier.bankInfo}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">开票信息</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="1000" class="form-control" rows="5"  disabled>${goodssupplier.invoiceInfo}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">联系地址</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="1000" class="form-control" rows="5"  disabled>${goodssupplier.contactAddress}</textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">其他备注</label>
                                        <div class="col-md-12">
                                            <textarea name="description" maxlength="1000" class="form-control" rows="5"  disabled>${goodssupplier.description}</textarea>
                                        </div>
                                    </div>

                                </form>
                                <div class="row">
                                    <div class="col-lg-5"></div>
                                    <div class="col-lg-2 col-sm-4 col-xs-12">
                                        <button class="btn btn-block btn-primary text-uppercase" onclick="history.back()">
                                            <span style="vertical-align: inherit;">返回</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
</div>

<script type="text/javascript">
    function enlarge(parm) {
        var width = $(parm).width();
        if(width == 100){
            $(parm).css("width", "300px");
        }else{
            $(parm).css("width", "100px");
        }
    }

</script>
</body>
</html>
