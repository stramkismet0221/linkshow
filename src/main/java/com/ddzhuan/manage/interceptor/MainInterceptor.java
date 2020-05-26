package com.ddzhuan.manage.interceptor;

import com.ddzhuan.manage.controller.MainController;
import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.tool.CacheTool;
import com.ddzhuan.manage.tool.IPTool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 拦截器
 * @author Acer
 */
public class MainInterceptor implements HandlerInterceptor {

    Logger log = Logger.getLogger(this.getClass());

    @Autowired
    protected CacheTool cache;

    // 不进行拦截的url
    private static String[] excludePaths = {"index", "login", "eclogin", "jumilogin", "syslogin", "syslogout", "statistics/datav", "menu/getmenutree","user/checkphone","t/phcode","user/forgetpwdedit","user/forgetpwd","dashboard/index", "jumi/product/test/index","region/getprovince","region/getcity","region/getdistrict"};
    // 需要拦截的JS字符关键字
    private static String[] safeless = {
            "<script",
            "</script", "<iframe", "</iframe", "<frame", "</frame",
            "set-cookie", "%3cscript", "%3c/script", "%3ciframe", "%3c/iframe",
            "%3cframe", "%3c/frame", "src=\"javascript:", "<body", "</body",
            "%3cbody", "%3c/body", "<", ">", "</", "/>", "%3c", "%3e", "%3c/",
            "/%3e"};

    private static boolean isSafe(String str) {
        if (str != null && !"".equals(str.trim())) {
            for (String s : safeless) {
                if (str.toLowerCase().contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean excludeUrl(String url) {
        if (excludePaths != null && excludePaths.length > 0) {
            for (String path : excludePaths) {
                if (Pattern.matches(".*/" + path + "$", url)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 过滤非正常提交
        String ip = IPTool.getIPAddr(request);
        String path = request.getServletPath();
        @SuppressWarnings("unchecked")
        Enumeration<String> params = request.getParameterNames();
        String requestUrl = request.getRequestURI();
        // datav相关数据不拦截
        if (requestUrl.indexOf("datav") > -1) {
            return true;
        }
        if (isSafe(requestUrl)) {
            requestUrl = path;
            if (!excludeUrl(requestUrl)) {
                while (params.hasMoreElements()) {
                    String cache = request.getParameter((String) params.nextElement());
                    if (cache != null && !"".equals(cache)) {
                        if (!isSafe(cache)) {
                            return false;
                        }
                    }
                }
            } else {
                return true;
            }
        } else {
            return false;
        }

        if(!(handler instanceof MainController)){
            User sysUser = (User)request.getSession().getAttribute("sysuser");
            if(sysUser == null){
                if (request.getRequestURI().contains("jumi")) {
                    response.sendRedirect(request.getContextPath()+"/jumilogin");
                } else {
                    response.sendRedirect(request.getContextPath()+"/index");
                }
                return false;
            }
        }
        // TODO 请求拦截一次，取session中menus的访问路径，是否和点击的菜单前缀相同，相同的话直接有权限
        // TODO 不相同则无权操作， 左侧菜单栏显示的需要修改，增加了系统关联角色，系统关联用户两重角色
        // TODO 后续的操作权限，再加上操作和角色、用户的关联表，直接控制按钮级别的操作权限
        // 检查是否有菜单权限
        if (!checkMenuPower(request) && request.getServletPath().indexOf("errorpage") == -1) {

        	if("/user/updatepwd".equals(path) || "/user/checkpwd".equals(path)  || "/user/editpwd".equals(path) ) {
        		return true;
        	}
            response.sendRedirect(request.getContextPath()+"/index");
            return false;
        }
        return true;
    }

    private boolean checkMenuPower(HttpServletRequest request) throws Exception {

        String servletPath = request.getServletPath();
        // 一层目录
        String pServletPath = servletPath.split("/")[1];
        List<Menu> menus = (List) request.getSession().getAttribute("menus");
        // 未登录，返回登录页面
        if (menus == null) {
            return false;
        }
        for (Menu menu : menus) {
            if (pServletPath.indexOf("errorpage") == -1
                    && menu.getVisitUrl() != null
                    && menu.getVisitUrl().indexOf(pServletPath) > -1) {
                return true;
            }
        }
        return false;
    }

    private PrintWriter getResponseOut(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        return response.getWriter();
    }

    public static void main(String[] args) {

    }

}
