<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<div class="col-lg-3 col-sm-6 row-in-br  b-r-none">
    <ul class="col-in">
        <li>
            <span class="circle circle-md bg-info"><i class="ti-wallet"></i></span>
        </li>
        <li class="col-last">
            <h3 class="counter text-right m-t-15">￥${busAnalysis.transAmount==null?0:busAnalysis.transAmount}</h3>
        </li>
        <li class="col-middle">
            <h4>今日交易额</h4>
            <div class="progress">
                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                    <span class="sr-only">40% Complete (success)</span>
                </div>
            </div>
        </li>
    </ul>
</div>
<div class="col-lg-3 col-sm-6 row-in-br">
    <ul class="col-in">
        <li>
            <span class="circle circle-md bg-danger"><i class="ti-clipboard"></i></span>
        </li>
        <li class="col-last">
            <h3 class="counter text-right m-t-15">￥${busAnalysis.refundAmount==null?0:busAnalysis.refundAmount}</h3>
        </li>
        <li class="col-middle">
            <h4>今日退款额</h4>
            <div class="progress">
                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                    <span class="sr-only">40% Complete (success)</span>
                </div>
            </div>
        </li>
    </ul>
</div>
<div class="col-lg-3 col-sm-6 row-in-br">
    <ul class="col-in">
        <li>
            <span class="circle circle-md bg-success"><i class=" ti-shopping-cart"></i></span>
        </li>
        <li class="col-last">
            <h3 class="counter text-right m-t-15">￥${busAnalysis.turnoverAmount==null?0:busAnalysis.turnoverAmount}</h3>
        </li>
        <li class="col-middle">
            <h4>今日成交额</h4>
            <div class="progress">
                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                    <span class="sr-only">40% Complete (success)</span>
                </div>
            </div>
        </li>
    </ul>
</div>
<div class="col-lg-3 col-sm-6  b-0">
    <ul class="col-in">
        <li>
            <span class="circle circle-md bg-warning"><i class="fa fa-dollar"></i></span>
        </li>
        <li class="col-last">
            <h3 class="counter text-right m-t-15">￥${busAnalysis.grossProfit==null?0:busAnalysis.grossProfit}</h3>
        </li>
        <li class="col-middle">
            <h4>今日毛利润</h4>
            <div class="progress">
                <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                    <span class="sr-only">40% Complete (success)</span>
                </div>
            </div>
        </li>
    </ul>
</div>