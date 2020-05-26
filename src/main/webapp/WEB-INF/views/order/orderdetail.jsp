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
    <link rel="icon" type="image/png" sizes="16x16" href="../common/adm/plugins/images/favicon.png">
    <title>订单信息</title>
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
                <jsp:param value="/jumi/order/orderpost/getlist" name="visitUrl"/>
            </jsp:include>
        </div>
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">订单信息</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="#">订单中心</a></li>
                            <li class="active">订单信息</li>
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
                                            <label class="col-md-12">ID</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.receiptNo}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">商品条形码</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.barCode}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">商品名称</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.name}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">所属售货机</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.tName}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">所在场地</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.placeName}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">购买用户ID</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.userId}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">购买用户昵称</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.nickName}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">订单金额（元）</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.originalPrice}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">实付金额（元）</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.price}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">折扣（元）</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.discount==null?0:orderInfo.discount}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">成本价（元）</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="${orderInfo.costPrice}" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">支付方式</label>
                                            <div class="col-md-12">
                                                <c:if test="${orderInfo.payType == 1}">
                                                    <input type="text" class="form-control" value="微信" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.payType == 2}">
                                                    <input type="text" class="form-control" value="支付宝" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.payType == 99 && orderInfo.thirdPayType == 1}">
                                                    <input type="text" class="form-control" value="TASK" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.payType == 99 && orderInfo.thirdPayType == 2}">
                                                    <input type="text" class="form-control" value="喵钻" disabled/>
                                                </c:if>
                                            </div>
                                        </div>
                                        <c:if test="${orderInfo.payType == 99 && (orderInfo.thirdPayType == 1 ||orderInfo.thirdPayType == 2)}">
                                            <div class="form-group">
                                                <label class="col-md-12">${orderInfo.thirdPayType==1?"其他支付金额（TASK）":"其他支付金额（喵钻）"}</label>
                                                <div class="col-md-12">
                                                    <input type="text" class="form-control" value="${orderInfo.taskPrice}" disabled/>
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="form-group">
                                            <label class="col-md-12">订单时间</label>
                                            <div class="col-md-12">
                                                <input type="text" class="form-control" value="<fmt:formatDate value="${orderInfo.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">支付状态</label>
                                            <div class="col-md-12">
                                                <c:if test="${orderInfo.payStatus == 0}">
                                                    <input type="text" class="form-control" value="未支付" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.payStatus == 1}">
                                                    <input type="text" class="form-control" value="已支付" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.payStatus == 2}">
                                                    <input type="text" class="form-control" value="支付失败" disabled/>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">出货状态</label>
                                            <div class="col-md-12">
                                                <c:if test="${orderInfo.outStatus == 0}">
                                                    <input type="text" class="form-control" value="等待出货" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.outStatus == 1}">
                                                    <input type="text" class="form-control" value="出货成功" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.outStatus == 2}">
                                                    <input type="text" class="form-control" value="出货失败" disabled/>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-12">退款状态</label>
                                            <div class="col-md-12">
                                                <c:if test="${orderInfo.refundStatus == 0}">
                                                    <input type="text" class="form-control" value="未退款" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.refundStatus == 1}">
                                                    <input type="text" class="form-control" value="退款成功" disabled/>
                                                </c:if>
                                                <c:if test="${orderInfo.refundStatus == 2}">
                                                    <input type="text" class="form-control" value="退款失败" disabled/>
                                                </c:if>
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
</body>
</html>