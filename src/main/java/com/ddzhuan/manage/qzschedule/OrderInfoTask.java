package com.ddzhuan.manage.qzschedule;

import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.OrderInfo;
import com.ddzhuan.manage.model.OrderPlace;
import com.ddzhuan.manage.model.OrderTerminal;
import com.ddzhuan.manage.service.OrderService;
import com.ddzhuan.manage.tool.DateUtil;
import com.ddzhuan.manage.tool.yopoint.YoPointApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单统计定时任务
 *
 * @author likeke
 * @date 2019/09/18
 */
public class OrderInfoTask {

    Logger log = Logger.getLogger(SysLogInfoTask.class);

    @Autowired
    private OrderService orderService;

    public void run() {
        // TODO 每天00：00分统计一次，
        // TODO 判断设备订单统计表是否有数据，没有数据就统计全部，有数据就按照时间查询每天的订单
        // TODO 放入设备订单统计表、设备场地订单统计表
        List<OrderTerminal> orderTerminals = orderService.getOrderTerminalList(new OrderTerminal(), null,
                new Pagination(1, 2147483647));
        List<OrderInfo> orderInfos = null;
        if (CollectionUtils.isEmpty(orderTerminals)) {
            orderInfos = orderService.getOrderList(new OrderInfo(), new Pagination(1, 2147483647));
        } else {
            LocalDateTime nowTime = LocalDateTime.now();
            LocalDateTime yesTime = LocalDateTime.now().minusDays(1L);
            String nowStr = DateUtil.localDateTime2Str(nowTime);
            String yesStr = DateUtil.localDateTime2Str(yesTime);
            orderInfos = orderService.getOrderListByTime(null, yesStr, nowStr, new Pagination(1, Integer.MAX_VALUE));
        }
        if (!CollectionUtils.isEmpty(orderInfos)) {
            // 按设备统计
            saveOrderTerminals(orderInfos);
            // 按设备地点统计
            saveOrderPlaces(orderInfos);
        }
    }

    /**
     * 按设备定时统计保存入库
     * @param orderInfos 订单列表
     */
    private void saveOrderTerminals(List<OrderInfo> orderInfos) {
        Map<String, List<OrderInfo>> tMap = tListConvertMap(orderInfos);
        List<OrderTerminal> orderTerminals = new ArrayList<>();
        OrderTerminal orderTerminal = null;
        for (Map.Entry<String, List<OrderInfo>> entry : tMap.entrySet()) {
            String tId = entry.getKey();
            List<OrderInfo> orders = entry.getValue();
            Double sales = 0d;
            Double originalCost = 0d;
            for (OrderInfo order : orders) {
                sales += order.getPrice()/100;
                originalCost += order.getOriginalPrice()/100;
            }
            Double profit = sales - originalCost;
            Double settled = 0d;
            orderTerminal = new OrderTerminal();
            orderTerminal.settId(tId);
            orderTerminal.setSales(sales);
            orderTerminal.setOriginalCost(originalCost);
            orderTerminal.setProfit(profit);
            orderTerminal.setSettled(settled);
            orderTerminals.add(orderTerminal);
        }
        // 保存、更新
        for (OrderTerminal ot : orderTerminals) {
            YoPointApi api = new YoPointApi();
            String jsonStr = api.getTerminalByTid(ot.gettId());
            JSONObject result = JSONObject.parseObject(jsonStr);
            JSONObject data = JSONObject.parseObject(result.getString("data"));
            String tName = data.getString("Name");
            ot.settName(tName);
            orderService.saveOrderTerminal(ot);
        }
    }

    /**
     * 按地点定时统计保存入库
     * @param orderInfos 订单列表
     */
    private void saveOrderPlaces(List<OrderInfo> orderInfos) {
        Map<String, List<OrderInfo>> tMap = pListConvertMap(orderInfos);
        List<OrderPlace> orderPlaces = new ArrayList<>();
        OrderPlace orderPlace = null;
        for (Map.Entry<String, List<OrderInfo>> entry : tMap.entrySet()) {
            String placeId = entry.getKey();
            String tId = null;
            List<OrderInfo> orders = entry.getValue();
            if (CollectionUtils.isEmpty(orders)) {
                tId = orders.get(0).gettId();
            }
            Double sales = 0d;
            Double originalCost = 0d;
            for (OrderInfo order : orders) {
                sales += order.getPrice()/100;
                originalCost += order.getOriginalPrice()/100;
            }
            Double profit = sales - originalCost;
            Double settled = 0d;
            orderPlace = new OrderPlace();
            orderPlace.setPlaceId(placeId);
            orderPlace.settId(tId);
            orderPlace.setSales(sales);
            orderPlace.setOriginalCost(originalCost);
            orderPlace.setProfit(profit);
            orderPlace.setSettled(settled);
            orderPlaces.add(orderPlace);
        }

        for (OrderPlace op : orderPlaces) {
            YoPointApi api = new YoPointApi();
            String jsonStr = api.getTerminalByTid(op.gettId());
            JSONObject result = JSONObject.parseObject(jsonStr);
            JSONObject data = JSONObject.parseObject(result.getString("data"));
            JSONObject configVO = JSONObject.parseObject(data.getString("TerminalConfigVO"));
            String placeName = configVO.getString("Address");
            op.setPlaceName(placeName);
            orderService.saveOrderPlace(op);
        }
    }

    /**
     * list 转化为 设备订单map
     * @param orderInfos 订单信息列表
     * @return map结构
     */
    private Map<String, List<OrderInfo>> tListConvertMap(List<OrderInfo> orderInfos) {
        Map<String, List<OrderInfo>> tMap = new HashMap<>();
        List<OrderInfo> orderInfoList = null;
        for (OrderInfo orderInfo : orderInfos) {
            orderInfoList = tMap.get(orderInfo.gettId());
            if (CollectionUtils.isEmpty(orderInfoList)) {
                orderInfoList = new ArrayList<>();
                tMap.put(orderInfo.gettId(), orderInfoList);
            }
            orderInfoList.add(orderInfo);
        }
        return tMap;
    }

    /**
     * list 转化为 设备场地订单map
     * @param orderInfos 订单信息列表
     * @return 设备场地订单map
     */
    private Map<String, List<OrderInfo>> pListConvertMap(List<OrderInfo> orderInfos) {
        Map<String, List<OrderInfo>> pMap = new HashMap<>();
        List<OrderInfo> orderInfoList = null;
        for (OrderInfo orderInfo : orderInfos) {
            orderInfoList = pMap.get(orderInfo.getPlaceId());
            if (CollectionUtils.isEmpty(orderInfoList)) {
                orderInfoList = new ArrayList<>();
                pMap.put(orderInfo.getPlaceId(), orderInfoList);
            }
            orderInfoList.add(orderInfo);
        }
        return pMap;
    }

}
