package com.ddzhuan.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.OrderInfo;
import com.ddzhuan.manage.model.OrderPlace;
import com.ddzhuan.manage.model.OrderTerminal;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.OrderService;
import com.ddzhuan.manage.service.YpTerminalService;
import com.ddzhuan.manage.tool.yopoint.YoPointApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品订单记录
 *
 * @author likeke
 * @date 2019/09/10
 */
@Controller
@RequestMapping("/order/")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private YpTerminalService ypTerminalService;

    /**
     * 订单流水
     * <p>
     *     根据用户和售货机所属关系，订单明细分页列表
     *
     * @param pagination 分页参数
     * @param type 1（默认）、按商品 2、按设备 3、按设备地点
     * @return 订单流水
     */
    @RequestMapping("getorderlist")
    public String getOrderList(HttpServletRequest request, HttpServletResponse response, Pagination pagination,
                               Model model, @RequestParam(required = false, defaultValue = "1") Integer type,
                               OrderInfo orderInfo) {
        List<OrderInfo> orderInfos = orderService.getOrderList(orderInfo, pagination);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("orderInfos", orderInfos);
        return "order/orderlist";
    }

    /**
     * 订单详情
     * <p>
     *     根据订单id获取订单详情
     *
     * @param id 订单id
     * @return 订单详情页面
     */
    @RequestMapping("orderdetail")
    public String orderDetail(HttpServletRequest request, HttpServletResponse response,
                              Model model, Long id) {
        OrderInfo orderInfo = orderService.getOrderInfoById(id);
        model.addAttribute("orderInfo", orderInfo);
        return "order/orderdetail";
    }

    /**
     * 设备订单流水
     * <p>
     *      根据用户的可查询的设备，设备订单列表
     *
     * @param pagination 分页参数
     * @return 订单流水
     */
    @RequestMapping("getterminalorders")
    public String getTerminalOrderList(HttpServletRequest request, HttpServletResponse response, Pagination pagination,
                                       Model model, OrderTerminal orderTerminal) {
        // 根据userId查询拥有权限的设备列表
        // 用户所拥有查看设备订单流水权限
        User user = (User) request.getSession().getAttribute("sysuser");
        Long userId = user.getId();
        List<String> terminalIds = ypTerminalService.getTerminalIdsByUserId(userId);
        List<OrderTerminal> orderTerminalList = orderService.getOrderTerminalList(orderTerminal, terminalIds, pagination);
        model.addAttribute("orderTerminal", orderTerminal);
        model.addAttribute("orderTerminalList", orderTerminalList);
        return "order/orderterminallist";
    }

    /**
     * 场地账单流水
     * <p>
     *     根据用户的可查询的场地，场地账单列表
     *
     * @param pagination 分页参数
     * @param orderPlace 场地信息
     * @return 场地流水
     */
    @RequestMapping("getplaceorders")
    public String getPlaceOrderList(HttpServletRequest request, HttpServletResponse response, Pagination pagination,
                                    Model model, OrderPlace orderPlace) {
        // TODO 用户所拥有的设备地点查看订单权限
        User user = (User) request.getSession().getAttribute("sysuser");
        Long userId = user.getId();
        List<String> terminalIds = ypTerminalService.getTerminalIdsByUserId(userId);
        List<String> placeIds = this.terminalConvertPlace(terminalIds);
        List<OrderPlace> orderPlaces = orderService.getOrderPlaceList(orderPlace, placeIds, pagination);
        model.addAttribute("orderPlace", orderPlace);
        model.addAttribute("orderPlaceList", orderPlaces);
        return "order/orderplacelist";
    }

    /**
     * 设备ids转化为所在场地ids
     *  调用友朋接口设备
     * @param terminalIds
     * @return
     */
    private List<String> terminalConvertPlace(List<String> terminalIds) {
        List<String> placeIds = new ArrayList<>();
        for (String terminalId : terminalIds) {
            YoPointApi api = new YoPointApi();
            String jsonStr = api.getTerminalByTid(terminalId);
            JSONObject result = JSONObject.parseObject(jsonStr);
            JSONObject data = JSONObject.parseObject(result.getString("data"));
            JSONObject terminalJson = JSONObject.parseObject(data.getString("TerminalConfigVO"));
            String placeId = terminalJson.getString("PlaceID");
            // placeName
            // String placeName = terminalJson.getString("Address");
            placeIds.add(placeId);
        }
        if (!CollectionUtils.isEmpty(placeIds)) {
            placeIds = placeIds.stream().distinct().collect(Collectors.toList());
        }
        return placeIds;
    }

}
