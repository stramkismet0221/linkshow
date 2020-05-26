package com.ddzhuan.manage.controller.jumi.product;

import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.jumi.product.Test;
import com.ddzhuan.manage.service.jumi.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @RequestMapping 命名规则--> 类："/jumi/一级菜单业务名/二级菜单业务名称/"
 *                 方法： "业务逻辑名"
 *
 */
@Controller
@RequestMapping("/jumi/product/test/")
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @RequestMapping("index")
    public String getIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Test> testList = testService.getTestList();
        model.addAttribute("testList", testList);
        return "jumi/product/test/index";
    }

    public static void main(String[] args) {
    }

}
