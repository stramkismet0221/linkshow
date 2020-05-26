<%@ page import="com.ddzhuan.manage.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User user = (User) request.getSession().getAttribute("sysuser");
    String userName = user == null ? "" : user.getUserName();
    String realName = user == null ? "" : user.getRealName();
    String email = user == null ? "" : user.getEmail();
    String mobile = user == null ? "" : user.getMobile();
%>
<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/common/adm/plugins/images/favicon.png">
<link href="${pageContext.request.contextPath}/common/adm/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/common/adm/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/dropzone-master/dist/dropzone.css" rel="stylesheet"  type="text/css" >
<link href="${pageContext.request.contextPath}/common/adm/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/css/colors/blue-dark.css" id="theme" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/nestable/nestable.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-select/bootstrap-select.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/footable/css/footable.core.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/clockpicker/dist/jquery-clockpicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-datepicker/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/multiselect/css/multi-select.css" rel="stylesheet"  type="text/css" >
<link href="${pageContext.request.contextPath}/common/css/doublebox-bootstrap.css" rel="stylesheet"  type="text/css" >
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/jquery-asColorPicker-master/css/asColorPicker.css" rel="stylesheet"  type="text/css" >
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/chartist-js/dist/chartist.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/switchery/dist/switchery.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />



<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/jquery.slimscroll.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sweetalert/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/sweetalert/jquery.sweet-alert.custom.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/waves.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/custom.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/validator.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/nestable/jquery.nestable.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/dropzone-master/dist/dropzone.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/custom-select/custom-select.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-select/bootstrap-select.min.js"></script>

<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/moment/moment.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/clockpicker/dist/jquery-clockpicker.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/jquery-asColorPicker-master/libs/jquery-asColor.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/jquery-asColorPicker-master/libs/jquery-asGradient.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/timepicker/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/multiselect/js/jquery.multi-select.js"></script>

<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-treeview-master/dist/bootstrap-treeview.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/bootstrap-treeview-master/dist/bootstrap-treeview-init.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/cbpFWTabs.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/js/jasny-bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/common/js/doublebox-bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/common/js/tool.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/chartist-js/dist/latest/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/common/adm/plugins/bower_components/switchery/dist/switchery.min.js"></script>

<div class="navbar-header">
    <div class="top-left-part">
        <a class="logo" href="javascript:void(0);">
            <b>
                <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-logo.png" alt="home" class="dark-logo" />
                <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-logo-dark.png" alt="home" class="light-logo" />
            </b>
            <span class="hidden-xs">
                <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-text.png" alt="home" class="dark-logo" />
                <img src="${pageContext.request.contextPath}/common/adm/plugins/images/admin-text-dark.png" alt="home" class="light-logo" />
            </span>
        </a>
    </div>
    <ul class="nav navbar-top-links navbar-left">
        <li><a href="javascript:void(0)" class="open-close waves-effect waves-light visible-xs"><i class="ti-close ti-menu"></i></a></li>
        <%--<li class="dropdown">--%>
            <%--<a class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown" href="#"> <i class="mdi mdi-gmail"></i>--%>
                <%--<div class="notify"><span class="heartbit"></span><span class="point"></span></div>--%>
            <%--</a>--%>
            <%--<ul class="dropdown-menu mailbox animated bounceInDown">--%>
                <%--<li>--%>
                    <%--<div class="drop-title">You have 4 new messages</div>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<div class="message-center">--%>
                        <%--<a href="#">--%>
                            <%--<div class="user-img"> <img src="${pageContext.request.contextPath}/common/adm/plugins/images/users/pawandeep.jpg" alt="user" class="img-circle"> <span class="profile-status online pull-right"></span> </div>--%>
                            <%--<div class="mail-contnet">--%>
                            <%--<h5>Pavan kumar</h5> <span class="mail-desc">Just see the my admin!</span> <span class="time">9:30 AM</span> </div>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a class="text-center" href="javascript:void(0);"> <strong>See all notifications</strong> <i class="fa fa-angle-right"></i> </a>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="dropdown">--%>
            <%--<a class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown" href="#"> <i class="mdi mdi-check-circle"></i>--%>
                <%--<div class="notify"><span class="heartbit"></span><span class="point"></span></div>--%>
            <%--</a>--%>
            <%--<ul class="dropdown-menu dropdown-tasks animated slideInUp">--%>
                <%--<li>--%>
                    <%--<a href="javascript:void(0)">--%>
                        <%--<div>--%>
                            <%--<p> <strong>Task 1</strong> <span class="pull-right text-muted">40% Complete</span> </p>--%>
                            <%--<div class="progress progress-striped active">--%>
                                <%--<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%"> <span class="sr-only">40% Complete (success)</span> </div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</a>--%>
                <%--</li>--%>
                <%--<li class="divider"></li>--%>
                <%--<li>--%>
                    <%--<a class="text-center" href="#"> <strong>See All Tasks</strong> <i class="fa fa-angle-right"></i> </a>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <%--<li class="mega-dropdown"> <a class="dropdown-toggle waves-effect waves-light" data-toggle="dropdown" href="#"><span class="hidden-xs">Mega</span> <i class="icon-options-vertical"></i></a>--%>
            <%--<ul class="dropdown-menu mega-dropdown-menu animated bounceInDown">--%>
                <%--<li class="col-sm-3">--%>
                    <%--<ul>--%>
                        <%--<li class="dropdown-header">Header Title</li>--%>
                        <%--<li><a href="javascript:void(0)">Link of page</a> </li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <%--<li class="col-sm-3">--%>
                    <%--<ul>--%>
                        <%--<li class="dropdown-header">Header Title</li>--%>
                        <%--<li><a href="javascript:void(0)">Link of page</a> </li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <%--<li class="col-sm-3">--%>
                    <%--<ul>--%>
                        <%--<li class="dropdown-header">Header Title</li>--%>
                        <%--<li><a href="javascript:void(0)">Link of page</a> </li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <%--<li class="col-sm-3">--%>
                    <%--<ul>--%>
                        <%--<li class="dropdown-header">Header Title</li>--%>
                        <%--<li> <a href="javascript:void(0)">Link of page</a> </li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</li>--%>
    </ul>
    <ul class="nav navbar-top-links navbar-right pull-right">
        <%--<li>--%>
            <%--<form role="search" class="app-search hidden-sm hidden-xs m-r-10">--%>
            <%--<input type="text" placeholder="Search${pageContext.request.contextPath}." class="form-control"> <a href=""><i class="fa fa-search"></i></a> </form>--%>
        <%--</li>--%>
        <li class="dropdown">
            <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> <img src="${pageContext.request.contextPath}/common/adm/plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle"><b class="hidden-xs"><%=userName%></b><span class="caret"></span> </a>
            <ul class="dropdown-menu dropdown-user animated flipInY">
                <li>
                    <div class="dw-user-box">
                        <div class="u-img"><img src="${pageContext.request.contextPath}/common/adm/plugins/images/users/varun.jpg" alt="user" /></div>
                        <div class="u-text">
                            <h4><%=realName%></h4>
                            <p class="text-muted">Email：<%=email==null?"":email%></p>
                            <p class="text-muted">Phone：<%=mobile%></p>
                            <%--<a href="profile.html" class="btn btn-rounded btn-danger btn-sm">View Profile</a>--%>
                        </div>
                    </div>
                </li>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a href="#"><i class="ti-user"></i> My Profile</a></li>--%>
                <%--<li><a href="#"><i class="ti-wallet"></i> My Balance</a></li>--%>
                <%--<li><a href="#"><i class="ti-email"></i> Inbox</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a href="#"><i class="ti-settings"></i> Account Setting</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <li>
                	<a href="${pageContext.request.contextPath}/user/updatepwd"><i class="ti-settings"></i>&nbsp;修改密码</a>
                	<a href="${pageContext.request.contextPath}/syslogout"><i class="fa fa-power-off"></i>&nbsp;退出登录</a>
                </li>
            </ul>
        </li>
    </ul>
</div>
<script type="text/javascript">
    function checkStr(value) {
        if (value === '' || value === undefined || value.length <= 0){
            return true;
        }else {
            return false;
        }
    }
    //只能输入数字
    function btnonkeyup(obj, value) {
        value = value.replace(/\D/g, '');
        $(obj).val(value);
    }

    //只能输入两位小数
    function btnonkeyupDiscount(dom, value) {
        if (dom.value) {
            var val = dom.value.replace(/[^\d\.]/g, "");
            if (dom.value.indexOf(".") != -1) {
                if (dom.value.indexOf(".") == 0) {
                    dom.value = 0 + dom.value;
                }
                val = dom.value.match(/\d+\.\d{0,2}/g)[0];
            }else{
                val=dom.value.match(/\d{0,7}/)[0];
            }
            $(dom).val(val);
        }
    }

    //只能输入两位小数
    function btnonkeyupDiscountfour(dom, value) {
        if (dom.value) {
            var val = dom.value.replace(/[^\d\.]/g, "");
            if (dom.value.indexOf(".") != -1) {
                if (dom.value.indexOf(".") == 0) {
                    dom.value = 0 + dom.value;
                }
                val = dom.value.match(/\d+\.\d{0,4}/g)[0];
            }else{
                val=dom.value.match(/\d{0,7}/)[0];
            }
            $(dom).val(val);
            if ($("#online").prop("checked")) {
                $("input[name='onlinePrice']").val(val);
            }
            if ($("#terminal").prop("checked")) {
                $("input[name='terminalPrice']").val(val);
            }
        }
    }
</script>