package com.ddzhuan.manage.service;




import com.alibaba.fastjson.JSON;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 15:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/springMVC.xml")
@WebAppConfiguration("src/main/resources")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    private HttpServletRequest request;


    @Test
    public void getUserList(){
        Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setRows(10);
        List<User> userList = userService.queryUserListByConditions(new User(),pagination);
        System.out.println(JSON.toJSONString(userList));
    }


    @Test
    public void addUser(){
        User user = new User();
        user.setRealName("将如今");
        boolean flag = userService.insertUser(user);
        System.out.println(flag);
    }

}
