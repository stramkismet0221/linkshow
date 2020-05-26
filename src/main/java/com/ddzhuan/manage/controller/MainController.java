package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.MenuService;
import com.ddzhuan.manage.service.UserService;
import com.ddzhuan.manage.tool.MD5Tool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

/**
 * 登录
 *
 * @author likeke
 * @date 2019/07/06
 */
@Controller
@RequestMapping("/")
public class MainController extends BaseController {

    private static final long serialVersionUID = -4320778885924405051L;

    private Logger log = Logger.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /**
     * 登录页
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Model model, @RequestParam(required = false, defaultValue = "1") Long systemId) {
        model.addAttribute("systemId", systemId);
        return "index";
    }

    /**
     * 登录子系统
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response,
                        Model model, @RequestParam(required = false, defaultValue = "2") Long systemId) {
        model.addAttribute("systemId", systemId);
        return "index";
    }

    /**
     * 电子烟后台系统登录
     */
    @RequestMapping("eclogin")
    public String eCigarettesLogin(HttpServletRequest request, HttpServletResponse response,
                                   Model model, @RequestParam(required = false, defaultValue = "3") Long systemId) {
        model.addAttribute("systemId", systemId);
        return "index";
    }

    /**
     * 巨米后台系统登录
     */
    @RequestMapping("jumilogin")
    public String juMiLogin(HttpServletRequest request, HttpServletResponse response,
                                   Model model, @RequestParam(required = false, defaultValue = "4") Long systemId) {
        model.addAttribute("systemId", systemId);
        return "index";
    }

    @RequestMapping(value = "syslogin")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model,
                        @RequestParam(required = false) String username,
                        @RequestParam(required = false) String pwd,
                        @RequestParam(required = false) Long systemId) {
        if (username == null || "".equals(username) || pwd == null || "".equals(pwd)) {
            model.addAttribute("msg", "用户名或密码不可为空");
            return "index";
        }
        try {
            User loginUser = userService.login(username, MD5Tool.MD5Encode(pwd).toUpperCase(), systemId);
            if (loginUser != null) {
                if (loginUser.getLocked().intValue() == 1) {
                    model.addAttribute("msg", "账号被锁定，请联系管理员");
                    return "index";
                }
                List<Menu> menus = menuService.getMenusByUserId(loginUser.getId(), systemId);
                request.getSession().setAttribute("sysuser", loginUser);
                request.getSession().setAttribute("systemid", systemId);
                request.getSession().setAttribute("menus", menus);
                // 登录成功后，menus如果有数据，直接重定向到第一个有权限的页面
                // 如果没有数据，直接指向无数据页面
                if (CollectionUtils.isEmpty(menus)) {
                    return "nodata";
                }
                for (Menu menu : menus) {
                    if (menu.getVisitUrl().equals("/dashboard/home")) {
                        return "redirect:" + "/dashboard/home";
                    }
                }
                String visitUrl = "";
                for (Menu menu : menus) {
                    if (menu.getPid().intValue() != 0) {
                        visitUrl = menu.getVisitUrl();
                        break;
                    }
                }
                if (visitUrl == "") {
                    return "nodata";
                }
                return "redirect:" + visitUrl;
            } else {
                model.addAttribute("msg", "用户名或密码错误");
                model.addAttribute("systemId", systemId);
                return "index";
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            model.addAttribute("msg", "系统异常");
            return "index";
        }
    }

    /**
     * 退出登录
     *
     * @return 返回登录页
     */
    @RequestMapping(value = "syslogout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Long systemId  = (Long) request.getSession().getAttribute("systemid");
        initSession(request);
        if (systemId.intValue() == 2) {
            return "redirect:login";
        } else if (systemId.intValue() == 3) {
            return "redirect:eclogin";
        }
        return "redirect:index";
    }

    private void initSession(HttpServletRequest request) {
        Enumeration em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
    }


    public static void main(String[] args) {
        String str = MD5Tool.MD5Encode("123456");
        System.out.println("str = " + str.toUpperCase());
    }

}
