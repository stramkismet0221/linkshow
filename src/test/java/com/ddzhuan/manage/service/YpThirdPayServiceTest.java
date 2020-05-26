package com.ddzhuan.manage.service;

import com.alibaba.fastjson.JSON;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.tool.HttpPostTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author jiang yong tao
 * @date 2019/7/22 10:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:conf/springMVC.xml")
@WebAppConfiguration("src/main/resources")
public class YpThirdPayServiceTest {

    @Autowired
    private YpThidPayService ypThidPayService;



    @Test
    public void testSetThirdPay(){
        String payUrl = "www.baidu.com";
        String serverUrl = "www.hao123.com";
        List<String> subType = Arrays.asList("1","2");
        String singKey = "123";

        BaseResultInfo result = ypThidPayService.setThirdPay(payUrl,serverUrl,singKey,subType,null);
        System.out.println(JSON.toJSONString(result));
    }


    public static void main(String[] args) {
        HttpPostTool httpPostTool = new HttpPostTool();
        String result = httpPostTool.get("http://www.ddzhuan.cn:88/fmi/webd/solution0620");
        System.out.println(result);
    }

    @Test
    public void testA(){

        TreeMap<String,Object> result = new TreeMap<>();

        System.out.println("");
    }

}
