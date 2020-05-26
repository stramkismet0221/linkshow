package com.ddzhuan.manage.service;

import com.alibaba.fastjson.JSON;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 16:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/springMVC.xml")
@WebAppConfiguration("src/main/resources")
public class RoleServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;


    @Test
    public void queryRoleList(){
        Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setRows(10);
        List<Role> roleList = roleService.queryRoleListByConditions(new Role(),pagination);
        System.out.println(JSON.toJSONString(roleList));
    }


    @Test
    public void insertRole(){
        Role role = new Role();
        role.setCode("admin");
        role.setDescription("超级管理员");
        role.setName("超级管理员");
        boolean flag = roleService.inserRole(role);
        System.out.println(JSON.toJSONString(flag));
    }

    @Test
    public void getMenuTree(){
        List<Menu> menus = menuService.getMenuListByPidV2(0L,5L,null,null);
        System.out.println(JSON.toJSONString(menus));
    }


    @Test
    public void testthirdpayset(){

    }


}
