package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.OrderInfo;
import com.ddzhuan.manage.model.OrderPlace;
import com.ddzhuan.manage.model.OrderTerminal;

import java.util.List;

/**
 * 订单接口
 *
 * @author likeke
 * @date 2019/09/10
 */
public interface OrderService {

    /**
     * 新增
     * @param orderInfo 订单信息
     */
    void saveOrder(OrderInfo orderInfo);

    /**
     * 分页统计订单
     *
     * @param orderInfo 查询条件
     * @param pagination 分页参数
     * @return 订单列表
     */
    List<OrderInfo> getOrderList(OrderInfo orderInfo, Pagination pagination);

    /**
     * 根据id查询订单详细信息
     *
     * @param id 订单主键id
     * @return 订单详细信息
     */
    OrderInfo getOrderInfoById(Long id);

    /**
     * 根据时间区间查询订单--可分页
     *
     * @param orderInfo 订单基本查询条件
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pagination 分页参数
     * @return 订单信息列表
     */
    List<OrderInfo> getOrderListByTime(OrderInfo orderInfo, String startTime, String endTime, Pagination pagination);

    /**
     * 按设备统计销售额等信息
     *
     * @param orderTerminal 查询条件
     * @param terminalIds 设备ids
     * @param pagination 分页参数
     * @return 设备销售额列表
     */
    List<OrderTerminal> getOrderTerminalList(OrderTerminal orderTerminal, List<String> terminalIds, Pagination pagination);

    /**
     * 新增设备订单记录
     * @param orderTerminal 设备订单信息
     */
    void saveOrderTerminal(OrderTerminal orderTerminal);

    /**
     * 按设备地点统计销售额等信息
     *
     * @param orderPlace 设备地点
     * @param placeIds 地点ids
     * @param pagination 分页参数
     * @return 场地销售额列表
     */
    List<OrderPlace> getOrderPlaceList(OrderPlace orderPlace, List<String> placeIds, Pagination pagination);

    /**
     * 新增设备地点订单记录
     * @param orderPlace 设备地点订单信息
     */
    void saveOrderPlace(OrderPlace orderPlace);

}
