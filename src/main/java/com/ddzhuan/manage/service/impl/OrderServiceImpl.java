package com.ddzhuan.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.OrderDao;
import com.ddzhuan.manage.dao.YpTerminalDao;
import com.ddzhuan.manage.dao.YpTerminalPlaceDao;
import com.ddzhuan.manage.model.*;
import com.ddzhuan.manage.service.OrderService;
import com.ddzhuan.manage.tool.yopoint.YoPointApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单
 *
 * @author likeke
 * @date 2019/09/10
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private YpTerminalDao ypTerminalDao;

    @Autowired
    private YpTerminalPlaceDao ypTerminalPlaceDao;

    @Override
    public void saveOrder(OrderInfo orderInfo) {
        orderDao.insertOrder(orderInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrderInfo> getOrderList(OrderInfo orderInfo, Pagination pagination) {
        String terminalName = orderInfo.gettName();
        String placeName = orderInfo.getPlaceName();
        List<String> terminalIds = new ArrayList<>();
        List<String> placeIds = new ArrayList<>();
        if (!StringUtils.isEmpty(terminalName)) {
            terminalIds = ypTerminalDao.queryTerminalIdsByName(terminalName);
            if (CollectionUtils.isEmpty(terminalIds)) {
                return new ArrayList<>();
            }
        }
        if (!StringUtils.isEmpty(placeName)) {
            placeIds = ypTerminalPlaceDao.queryYpTerminalPlaceIdsByName(placeName);
            if (CollectionUtils.isEmpty(placeIds)) {
                return new ArrayList<>();
            }
        }
        Integer count = orderDao.countOrderList(orderInfo, terminalIds, placeIds);
        pagination.setTotal(count);
        Integer start = pagination.getStart();
        Integer end = pagination.getEnd();
        List<OrderInfo> orders = orderDao.queryOrderList(orderInfo, terminalIds, placeIds, start, end);
        fillAttribute(orders);
        return orders;
    }

    @Override
    public OrderInfo getOrderInfoById(Long id) {
        if (id == null) {
            return null;
        }
        OrderInfo orderInfo = orderDao.queryOrderInfoById(id);
        if (orderInfo == null) {
            return null;
        }
        String terminalId = orderInfo.gettId();
        String placeId = orderInfo.getPlaceId();
        if (!StringUtils.isEmpty(terminalId)) {
            YpTerminal ypTerminal = ypTerminalDao.queryYpTerminalById(terminalId);
            orderInfo.settName(ypTerminal.getName());
        }
        if (!StringUtils.isEmpty(placeId)) {
            YpTerminalPlace ypTerminalPlace = ypTerminalPlaceDao.queryYpTerminalPlaceById(placeId);
            orderInfo.setPlaceName(ypTerminalPlace.getName());
        }
        fillUserNickName(orderInfo);
        return orderInfo;
    }

    @Override
    public List<OrderInfo> getOrderListByTime(OrderInfo orderInfo, String startTime, String endTime, Pagination pagination) {
        Integer total = orderDao.countOrderListByTime(orderInfo, startTime, endTime);
        pagination.setTotal(total);
        List<OrderInfo> list = orderDao.queryOrderListByTime(orderInfo, startTime, endTime, pagination.getStart(), pagination.getEnd());
        return list;
    }

    @Override
    public List<OrderTerminal> getOrderTerminalList(OrderTerminal orderTerminal, List<String> terminalIds, Pagination pagination) {
        if (CollectionUtils.isEmpty(terminalIds)) {
            return new ArrayList<>();
        }
        Integer count = orderDao.countOrderListByTerminals(orderTerminal, terminalIds);
        pagination.setTotal(count);
        List<OrderTerminal> orderTerminals = orderDao.queryOrderListByTerminals(orderTerminal, terminalIds,
                pagination.getStart(), pagination.getEnd());
        return orderTerminals;
    }

    @Override
    public void saveOrderTerminal(OrderTerminal orderTerminal) {
        if (orderTerminal == null && StringUtils.isEmpty(orderTerminal.gettId())) {
            return;
        }
        String tId = orderTerminal.gettId();
        OrderTerminal ot = orderDao.getOrderTerminalByTId(tId);
        if (ot == null || ot.getId() == null) {
            orderDao.insertOrderTerminal(orderTerminal);
        } else {
            orderDao.updateOrderTerminal(orderTerminal);
        }
    }

    @Override
    public List<OrderPlace> getOrderPlaceList(OrderPlace orderPlace, List<String> placeIds, Pagination pagination) {
        if (CollectionUtils.isEmpty(placeIds)) {
            return new ArrayList<>();
        }
        Integer count = orderDao.countOrderListByPlaces(orderPlace, placeIds);
        pagination.setTotal(count);
        List<OrderPlace> orderPlaces = orderDao.queryOrderListByPlaces(orderPlace, placeIds, pagination.getStart(),
                pagination.getEnd());
        return orderPlaces;
    }

    @Override
    public void saveOrderPlace(OrderPlace orderPlace) {
        if (orderPlace == null && StringUtils.isEmpty(orderPlace)) {
            return;
        }
        String pId = orderPlace.getPlaceId();
        OrderPlace op = orderDao.getOrderPlaceByPId(pId);
        if (op == null || op.getPlaceId() == null) {
            orderDao.insertOrderPlace(orderPlace);
        } else {
            orderDao.updateOrderPlace(orderPlace);
        }
    }

    /**
     * 填充列表属性
     * @param orders 订单列表
     */
    private void fillAttribute(List<OrderInfo> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return;
        }
        for (OrderInfo order : orders) {
            String oId = order.getoId();
            String terminalId = order.gettId();
            // 如果有设备信息，查数据库，没有设备信息，调用有朋接口
            YpTerminal ypTerminal = ypTerminalDao.queryYpTerminalById(terminalId);
            if (ypTerminal == null) {
                // 数据库中新增一条记录yp_terminal_t表，
                Optional<YoPointApi> api = YoPointApi.getYoPointByOID(oId);
                if (api.isPresent()) {
                    YoPointApi yoPointApi = api.get();
                    String jsonStr = yoPointApi.getTerminalByTid(terminalId);
                    JSONObject result = JSONObject.parseObject(jsonStr);
                    JSONObject data = JSONObject.parseObject(result.getString("data"));
                    JSONObject config = JSONObject.parseObject(data.getString("TerminalConfigVO"));
                    String terminalName = data.getString("Name");
                    String deviceCode = data.getString("DeviceCode");
                    Integer cabinetCount = data.getInteger("CabinetCount");
                    String placeId = config.getString("PlaceID");

                    ypTerminal = new YpTerminal();
                    ypTerminal.setId(terminalId);
                    ypTerminal.setName(terminalName);
                    ypTerminal.setDeviceCode(deviceCode);
                    ypTerminal.setCabinetCount(cabinetCount);
                    ypTerminal.setPlaceId(placeId);
                    ypTerminalDao.batchInsertTerminalInfo(Arrays.asList(ypTerminal));

                    // 根据placeId查询chainclub.yp_terminal_place_t表
                    YpTerminalPlace ypTerminalPlace = ypTerminalPlaceDao.queryYpTerminalPlaceById(placeId);
                    if (ypTerminalPlace == null) {
                        // 不存在，新增到数据库中
                        String address = config.getString("Address");
                        JSONObject placeVO = JSONObject.parseObject(config.getString("PlaceVO"));
                        String province = placeVO.getString("province");
                        String city = placeVO.getString("City");
                        String district = placeVO.getString("District");
                        String subject = placeVO.getString("Subject");

                        ypTerminalPlace = new YpTerminalPlace();
                        ypTerminalPlace.setId(placeId);
                        ypTerminalPlace.setName(address);
                        ypTerminalPlace.setProvince(province);
                        ypTerminalPlace.setCity(city);
                        ypTerminalPlace.setDistrict(district);
                        ypTerminalPlace.setSubject(subject);
                        ypTerminalPlaceDao.batchInsertYpTerminalPlace(Arrays.asList(ypTerminalPlace));
                    }
                }
            }
            // 查询数据库，填充address、terminalName
            ypTerminal = ypTerminalDao.queryYpTerminalById(terminalId);
            if (ypTerminal != null) {
                String placeId = ypTerminal.getPlaceId();
                YpTerminalPlace place = ypTerminalPlaceDao.queryYpTerminalPlaceById(placeId);
                String terminalName = ypTerminal.getName();
                String placeName = place.getName();
                order.settName(terminalName);
                order.setPlaceName(placeName);
            }
            order.setProfit(order.getPrice()-order.getCostPrice());
        }
    }


    /**
     * 填充购买用户名称
     * @param orderInfo 订单信息
     */
    private void fillUserNickName(OrderInfo orderInfo) {
        if (orderInfo == null) {
            return;
        }
        Long orderId = orderInfo.getId();
        OrderUser orderUser = orderDao.queryOrderUserByOrderId(orderId);
        if (orderUser == null) {
            String oId = orderInfo.getoId();
            String receiptNo =  orderInfo.getReceiptNo();
            Optional<YoPointApi> api = YoPointApi.getYoPointByOID(oId);
            if (api.isPresent()) {
                YoPointApi yoPointApi = api.get();
                String jsonStr = yoPointApi.getConsumerOrderByReceiptNo(receiptNo);
                JSONObject result = JSONObject.parseObject(jsonStr);
                JSONObject data = JSONObject.parseObject(result.getString("data"));
                JSONObject userVO = JSONObject.parseObject(data.getString("UserVO"));
                String openId = userVO.getString("OpenID");
                String nickName = userVO.getString("NickName");
                Integer sex = userVO.getInteger("Sex");
                String province = userVO.getString("Province");
                String city = userVO.getString("City");
                String imageUrl = userVO.getString("ImageUrl");
                orderInfo.setNickName(nickName);

                orderUser = new OrderUser();
                orderUser.setOrderId(orderId);
                orderUser.setOpenId(openId);
                orderUser.setNickName(nickName);
                orderUser.setSex(sex);
                orderUser.setProvince(province);
                orderUser.setCity(city);
                orderUser.setImageUrl(imageUrl);
                orderDao.insertOrderUser(orderUser);
            }
        } else {
            orderInfo.setNickName(orderUser.getNickName());
        }
    }

    public static void main(String[] args) throws ParseException {
//        String oId = "5c7755c07b78200011a9376e";
//        String receiptNo = "OD191217102122973998";
//        Optional<YoPointApi> api = YoPointApi.getYoPointByOID(oId);
//        YoPointApi yoPointApi = api.get();
//        String jsonStr = yoPointApi.getConsumerOrderByReceiptNo(receiptNo);
//        JSONObject result = JSONObject.parseObject(jsonStr);
//        JSONObject data = JSONObject.parseObject(result.getString("data"));
//        JSONObject userVO = JSONObject.parseObject(data.getString("UserVO"));
//        String openId = userVO.getString("OpenID");
//        String nickName = userVO.getString("NickName");
//        System.out.println(nickName);
        //

//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        String start = "2019-12-31";
//        Date startDate = ft.parse(start);
//        String end = "2019-04-07";
//        Date endDate = ft.parse(end);
//        int dates = longOfTwoDate(startDate, endDate);
//        System.out.println(dates);
    }

    public static int longOfTwoDate(Date first, Date second) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fDate = format.format(first);
        String sDate = format.format(second);
        Date date1 = format.parse(fDate);
        Date date2 = format.parse(sDate);
        int day = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
        return day;
    }

}
