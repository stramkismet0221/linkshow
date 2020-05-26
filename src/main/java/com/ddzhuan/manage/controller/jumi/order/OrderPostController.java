package com.ddzhuan.manage.controller.jumi.order;

import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.jumi.order.OrderPost;
import com.ddzhuan.manage.service.jumi.OrderPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

import static org.apache.xmlbeans.impl.common.ConcurrentReaderHashMap.DEFAULT_INITIAL_CAPACITY;

/**
 * @author jiang yong tao
 * @date 2020/1/17 14:35
 */
@Controller
@RequestMapping("/jumi/order/orderpost/")
public class OrderPostController extends BaseController {


    @Autowired
    private OrderPostService orderPostService;


    @RequestMapping("getlist")
    public String getOrderPostList(Model model, HttpServletRequest request, HttpServletResponse response){
        List<OrderPost> orderPostList = orderPostService.getOrderList();
        Integer count = orderPostService.countOrdConByDeaf();
        model.addAttribute("count",count);
        model.addAttribute("orderPostList",orderPostList);
        return "jumi/order/conf/postdeatil";
    }



    @RequestMapping("orderpostinfo")
    public String modifyOrderPostPage(Model model, Long id, Integer type){
        OrderPost orderPost = orderPostService.getOrderPostById(id);
        model.addAttribute("orderPost",orderPost);
        if (OperateTypeEnum.DETAIL.code.equals(type)) {
            return "jumi/order/conf/postdeatil";
        }else {
            return "jumi/order/conf/modifyorderpost";
        }
    }



    @RequestMapping("updateorderpost")
    @ResponseBody
    public BaseResultInfo updateOrderPost(OrderPost orderPost, HttpServletRequest request, HttpServletResponse response){
        BaseResultInfo result = new BaseResultInfo();
        try {
            if (null != orderPost.getDeafStatus()) {
                Integer count = orderPostService.countOrdConByDeaf();
                if (count > 0 && 1 == orderPost.getDeafStatus()) {
                    HashMap<String,Object> ms = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
                    result.setSuccess(false);
                    result.setMsg("默认配送方式只需要一个");
                    ms.put("deafcode",0);
                    result.setExtInfo(ms);
                    return result;
                }
            }
            orderPostService.updateOrderPost(orderPost);
            result.setSuccess(true);
            result.setMsg("修改成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("修改异常");
        }
        return result;
    }


}
