package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.OrderInfo;
import com.ddzhuan.manage.model.OrderPlace;
import com.ddzhuan.manage.model.OrderTerminal;
import com.ddzhuan.manage.model.OrderUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Dao
 *
 * @author likeke
 * @date 2019/09/10
 */
public interface OrderDao {

    /**
     * 新增
     *
     * @param orderInfo 订单信息
     */
    void insertOrder(@Param("orderInfo") OrderInfo orderInfo);

    /**
     * 条件查询订单列表
     *
     * @param orderInfo 订单查询条件
     * @param terminalIds 设备ids
     * @param placeIds 设备地点ids
     * @param start 分页开始
     * @param end 分页结束
     * @return 订单列表
     */
    List<OrderInfo> queryOrderList(@Param("orderInfo") OrderInfo orderInfo, @Param("terminalIds") List<String> terminalIds,
                                   @Param("placeIds") List<String> placeIds,
                                   @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 条件查询记录数
     *
     * @param orderInfo 查询条件
     * @param terminalIds 设备ids
     * @param placeIds 设备地点ids
     * @return 记录数
     */
    Integer countOrderList(@Param("orderInfo") OrderInfo orderInfo, @Param("terminalIds") List<String> terminalIds,
                           @Param("placeIds") List<String> placeIds);

    /**
     * 根据id查询订单详细信息
     *
     * @param id 订单主键id
     * @return 订单详细信息
     */
    OrderInfo queryOrderInfoById(@Param("id") Long id);

    /**
     * 根据时间区间查询订单
     *
     * @param orderInfo 订单基本查询条件
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param start 分页开始
     * @param end 分页结束
     * @return 订单列表
     */
    List<OrderInfo> queryOrderListByTime(@Param("orderInfo") OrderInfo orderInfo,
                                         @Param("startTime") String startTime, @Param("endTime") String endTime,
                                         @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 根据时间区间查询订单记录数
     *
     * @param orderInfo 订单基本查询条件
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 记录数
     */
    Integer countOrderListByTime(@Param("orderInfo") OrderInfo orderInfo,
                                 @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 按设备统计销售额等信息
     *
     * @param orderTerminal 查询条件
     * @param terminalIds 设备ids
     * @param start 分页开始
     * @param end 分页结束
     * @return 设备销售额列表
     *
     */
    List<OrderTerminal> queryOrderListByTerminals(@Param("orderTerminal") OrderTerminal orderTerminal,
                                               @Param("terminalIds") List<String> terminalIds,
                                               @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 按设备统计记录数
     *
     * @param orderTerminal 查询条件
     * @param terminalIds 设备ids
     * @return 记录数
     */
    Integer countOrderListByTerminals(@Param("orderTerminal") OrderTerminal orderTerminal,
                                      @Param("terminalIds") List<String> terminalIds);

    /**
     * 根据tid查询设备订单信息
     * @param tId 设备id
     * @return 订单信息
     */
    OrderTerminal getOrderTerminalByTId(@Param("tId") String tId);

    /**
     * 保存
     * @param orderTerminal 设备订单信息
     */
    void insertOrderTerminal(@Param("orderTerminal") OrderTerminal orderTerminal);

    /**
     * 更新
     * @param orderTerminal 设备订单信息
     */
    void updateOrderTerminal(@Param("orderTerminal") OrderTerminal orderTerminal);

    /**
     * 按设备地点统计销售额等信息
     *
     * @param orderPlace 设备地点信息
     * @param placeIds 地点ids
     * @param start 分页开始
     * @param end 分页结束
     * @return 地点销售额等 列表
     */
    List<OrderPlace> queryOrderListByPlaces(@Param("orderPlace") OrderPlace orderPlace,
                                            @Param("placeIds") List<String> placeIds,
                                            @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 设备地点记录数
     *
     * @param orderPlace 设备地点信息
     * @param placeIds 设备ids
     * @return 记录数
     */
    Integer countOrderListByPlaces(@Param("orderPlace") OrderPlace orderPlace,
                                   @Param("placeIds") List<String> placeIds);

    /**
     * 根据placeId获取设备场地订单信息
     * @param placeId 设备场地id
     * @return 设备场地订单信息
     */
    OrderPlace getOrderPlaceByPId(@Param("placeId") String placeId);

    /**
     * 保存
     * @param orderPlace 设备场地订单信息
     */
    void insertOrderPlace(@Param("orderPlace") OrderPlace orderPlace);

    /**
     * 更新
     * @param orderPlace 设备场地订单信息
     */
    void updateOrderPlace(@Param("orderPlace") OrderPlace orderPlace);

    /**
     * 新增订单用户信息
     * @param orderUser 用户信息
     */
    void insertOrderUser(@Param("orderUser") OrderUser orderUser);

    /**
     * 根据订单id查询订单用户信息
     * @param orderId 订单id
     * @return 用户基本信息
     */
    OrderUser queryOrderUserByOrderId(@Param("orderId") Long orderId);

}
