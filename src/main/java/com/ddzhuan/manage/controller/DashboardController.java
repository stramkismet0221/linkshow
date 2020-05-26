package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.OrderInfo;
import com.ddzhuan.manage.model.YpTerminal;
import com.ddzhuan.manage.model.dashboard.BusAnalysis;
import com.ddzhuan.manage.model.dashboard.ProdAnalysis;
import com.ddzhuan.manage.model.dashboard.TermAnalysis;
import com.ddzhuan.manage.model.dashboard.TermAvgAnalysis;
import com.ddzhuan.manage.service.OrderService;
import com.ddzhuan.manage.service.YpTerminalService;
import com.ddzhuan.manage.tool.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工作台、经营分析、设备分析、产品销售分析等统计
 *
 * @author likeke
 * @date 2019/12/04
 */
@Controller
@RequestMapping("/dashboard/")
public class DashboardController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private YpTerminalService ypTerminalService;

    /**
     * 首页
     */
    @RequestMapping("home")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "dashboard/home";
    }

    /**
     * 统计今天经营情况
     *
     * @param request  request
     * @param response response
     * @param oId      运营商id
     * @return 今天总体经营情况
     */
    @RequestMapping("todaystat")
    public String getTodayStat(HttpServletRequest request, HttpServletResponse response,
                               Model model, @RequestParam(required = false) String oId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setoId(oId);
        // String nowTime = DateUtil.localDateTime2Str(LocalDateTime.now());
        String tomTime = DateUtil.localDateTime2Str(LocalDateTime.now().plusDays(1L));
        String yesTime = DateUtil.localDateTime2Str(LocalDateTime.now().minusDays(1L));
        List<OrderInfo> orderInfos = orderService.getOrderListByTime(orderInfo, yesTime, tomTime, new Pagination(1, Integer.MAX_VALUE));
        BusAnalysis busAnalysis = convertOrders2BusAnalysis(orderInfos);
        model.addAttribute("busAnalysis", busAnalysis);
        return "dashboard/todaystat";
    }

    /**
     * 统计某个时间段总交易金额、成交额等
     *
     * @param oId       运营商id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 总交易额、成交额等信息
     */
    @RequestMapping("totalanalysis")
    public String getTotalAnalysis(HttpServletRequest request, HttpServletResponse response, Model model,
                                        @RequestParam(required = false) String oId,
                                        @RequestParam(required = false) String startTime,
                                        @RequestParam(required = false) String endTime) throws ParseException {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setoId(oId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = sdf.parse(endTime);
        endTime = DateUtil.localDateTime2Str(DateUtil.date2LocalDateTime(endDate).plusDays(1));
        List<OrderInfo> orderInfos = orderService.getOrderListByTime(orderInfo, startTime, endTime, new Pagination(1, Integer.MAX_VALUE));
        BusAnalysis busAnalysis = convertOrders2DateRange(orderInfos);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("busAnalysis", busAnalysis);
        return "dashboard/totalanalysis";
    }

    /**
     * 经营分析，统计某个时间段每天交易金额、成交额等信息
     *
     * @param oId       运营商id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 每天交易额、成交额等信息列表
     */
    @RequestMapping("dayanalysis")
    public String getDayAnalysisList(HttpServletRequest request, HttpServletResponse response, Model model,
                                                @RequestParam(required = false) String oId,
                                                @RequestParam(required = false) String startTime,
                                                @RequestParam(required = false) String endTime) throws ParseException {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setoId(oId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = sdf.parse(endTime);
        endTime = DateUtil.localDateTime2Str(DateUtil.date2LocalDateTime(endDate).plusDays(1));
        List<OrderInfo> orderInfos = orderService.getOrderListByTime(orderInfo, startTime, endTime, new Pagination(1, Integer.MAX_VALUE));
        List<BusAnalysis> busAnalyses = convertOrders2DayAnalysis(orderInfos, startTime, DateUtil.localDateTime2Str(DateUtil.date2LocalDateTime(endDate)));
        model.addAttribute("busAnalyses", busAnalyses);
        return "dashboard/dayanalysis";
    }

    /**
     * 统计商品在某个时间段的销量、毛利排名分页列表，每页10条记录
     * <pre>
     *     此接口使用前端分页
     * </pre>
     *
     * @param oId       运营商id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 商品销售排名列表
     */
    @RequestMapping("prodanalysis")
    public String getProdAnalysisList(HttpServletRequest request, HttpServletResponse response, Model model,
                                      @RequestParam(required = false) String oId,
                                      @RequestParam(required = false) String startTime,
                                      @RequestParam(required = false) String endTime,
                                      Pagination pagination) throws ParseException {
//        pagination.setRows(3);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setoId(oId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = sdf.parse(endTime);
        endTime = DateUtil.localDateTime2Str(DateUtil.date2LocalDateTime(endDate).plusDays(1));
        List<OrderInfo> orderInfos = orderService.getOrderListByTime(orderInfo, startTime, endTime, new Pagination(1, Integer.MAX_VALUE));
        List<ProdAnalysis> prodAnalyses = convertOrders2ProdAnalysis(orderInfos, pagination);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", DateUtil.localDateTime2Str(DateUtil.date2LocalDateTime(sdf.parse(endTime)).minusDays(1)));
        model.addAttribute("prodAnalyses", prodAnalyses);
        return "dashboard/prodanalysis";
    }

    /**
     * 设备分析，统计设备今日、昨日平均交易额
     *
     * @param oId 运营商id
     * @return 今日、昨日平均交易额
     */
    @RequestMapping("termavganalysis")
    public String getTermAvgAnalysis(HttpServletRequest request, HttpServletResponse response,
                                     Model model, @RequestParam(required = false) String oId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setoId(oId);
        String startTime = DateUtil.localDateTime2Str(LocalDateTime.now().minusDays(1));
        String endTime = DateUtil.localDateTime2Str(LocalDateTime.now().plusDays(1));
        List<OrderInfo> orderInfos = orderService.getOrderListByTime(orderInfo, startTime, endTime, new Pagination(1, Integer.MAX_VALUE));
        TermAvgAnalysis termAvgAnalysis = convertOrders2TermAvgAnalysis(orderInfos);
        model.addAttribute("termAvgAnalysis", termAvgAnalysis);
        return "dashboard/termavganalysis";
    }

    /**
     * 设备分析，统计设备今日、昨日、本月（交易额、毛利、订单量）信息列表
     *
     * @param oId 运营商id
     * @return 设备统计列表
     */
    @RequestMapping("termanalysis")
    public String getTermAnalysisList(HttpServletRequest request, HttpServletResponse response,
                                      Model model, @RequestParam(required = false) String oId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setoId(oId);
        String startTime = DateUtil.localDateTime2Str(DateUtil.date2LocalDateTime(DateUtil.getFirstDayOfMonth(new Date())));
        String endTime = DateUtil.localDateTime2Str(LocalDateTime.now().plusDays(1));
        List<OrderInfo> orderInfos = orderService.getOrderListByTime(orderInfo, startTime, endTime, new Pagination(1, Integer.MAX_VALUE));
        List<TermAnalysis> termAnalyses = convertOrders2TermAnalysis(orderInfos);
        model.addAttribute("termAnalyses", termAnalyses);
        return "dashboard/termanalysis";
    }

    /**
     * 订单列表转化为今日经营情况统计
     *
     * @param orderInfos 订单信息列表
     * @return 今日经营情况统计
     */
    private BusAnalysis convertOrders2BusAnalysis(List<OrderInfo> orderInfos) {
        if (CollectionUtils.isEmpty(orderInfos)) {
            return new BusAnalysis();
        }
        // 今日统计数据
        Double tTransAmount = 0d;
        Integer tTransNum = 0;
        Double tRefundAmount = 0d;
        Integer tRefundNum = 0;
        Double costPrice = 0d;
        // 昨日统计数据
        Double yTransAmount = 0d;
        Double yRefundAmount = 0d;
        // 同比
        Double compared = 100d;
        for (OrderInfo orderInfo : orderInfos) {
            Date payTime = orderInfo.getPayTime();
            if (payTime.after(DateUtil.getStartOfDay(new Date()))) {
                tTransAmount += orderInfo.getPrice();
                tTransNum += 1;
                // 退款成功
                if (orderInfo.getRefundStatus() == 1) {
                    tRefundAmount += orderInfo.getPrice();
                    tRefundNum += 1;
                } else {
                    costPrice += orderInfo.getCostPrice();
                }
            } else {
                yTransAmount += orderInfo.getPrice();
                if (orderInfo.getRefundStatus() == 1) {
                    yRefundAmount += orderInfo.getPrice();
                }
            }
        }
        Double tTurnoverAmount = (double) Math.round((tTransAmount - tRefundAmount) * 100) / 100;
        Double yTurnoverAmount = (double) Math.round((yTransAmount - yRefundAmount) * 100) / 100;
        Double grossProfit = (double) Math.round((tTurnoverAmount - costPrice) * 100) / 100;
        if (yTurnoverAmount != null && yTurnoverAmount > 0) {
            compared = (double) Math.round(((tTurnoverAmount - yTurnoverAmount) / yTurnoverAmount) * 100) / 100 * 100;
        }
        BusAnalysis busAnalysis = new BusAnalysis();
        busAnalysis.setTransAmount((double) Math.round(tTransAmount * 100) / 100);
        busAnalysis.setTransNum(tTransNum);
        busAnalysis.setRefundAmount((double) Math.round(tRefundAmount * 100) / 100);
        busAnalysis.setRefundNum(tRefundNum);
        busAnalysis.setTurnoverAmount(tTurnoverAmount);
        busAnalysis.setCompared(compared);
        busAnalysis.setGrossProfit(grossProfit);
        return busAnalysis;
    }

    /**
     * 统计某个时间段交易金额、交易笔数、退款金额、退款笔数、成交额、毛利
     *
     * @param orderInfos 订单信息列表
     * @return 某个时间段经营分析
     */
    private BusAnalysis convertOrders2DateRange(List<OrderInfo> orderInfos) {
        if (CollectionUtils.isEmpty(orderInfos)) {
            return new BusAnalysis();
        }
        // 统计数据
        Double transAmount = 0d;
        Integer transNum = 0;
        Double refundAmount = 0d;
        Integer refundNum = 0;
        Double costPrice = 0d;
        for (OrderInfo orderInfo : orderInfos) {
            transAmount += orderInfo.getPrice();
            transNum += 1;
            // 退款成功
            if (orderInfo.getRefundStatus() == 1) {
                refundAmount += orderInfo.getPrice();
                refundNum += 1;
            } else {
                costPrice += orderInfo.getCostPrice();
            }
        }
        Double turnoverAmount = (double) Math.round((transAmount - refundAmount) * 100) / 100;
        Double grossProfit = (double) Math.round((turnoverAmount - costPrice) * 100) / 100;
        BusAnalysis busAnalysis = new BusAnalysis();
        busAnalysis.setTransAmount((double) Math.round(transAmount * 100) / 100);
        busAnalysis.setTransNum(transNum);
        busAnalysis.setRefundAmount((double) Math.round(refundAmount * 100) / 100);
        busAnalysis.setRefundNum(refundNum);
        busAnalysis.setTurnoverAmount(turnoverAmount);
        busAnalysis.setGrossProfit(grossProfit);
        return busAnalysis;
    }

    /**
     * 订单列表转化为统计每天经营情况列表
     *
     * @param orderInfos 订单信息列表
     * @return 每天经营情况统计列表
     */
    private List<BusAnalysis> convertOrders2DayAnalysis(List<OrderInfo> orderInfos, String startTime, String endTime) {
        if (CollectionUtils.isEmpty(orderInfos)) {
            return new ArrayList<>();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, List<OrderInfo>> map = new HashMap<>();
        List<OrderInfo> orderInfoList = null;
        for (OrderInfo orderInfo : orderInfos) {
            String payTime = formatter.format(orderInfo.getPayTime());
            orderInfoList = map.get(payTime);
            if (CollectionUtils.isEmpty(orderInfoList)) {
                orderInfoList = new ArrayList<>();
                map.put(payTime, orderInfoList);
            }
            orderInfoList.add(orderInfo);
        }
        List<BusAnalysis> busAnalyses = new ArrayList<>();
        BusAnalysis busAnalysis = null;
        for (Map.Entry<String, List<OrderInfo>> entry : map.entrySet()) {
            String dayStr = entry.getKey();
            Double transAmount = 0d;
            Integer transNum = 0;
            Double refundAmount = 0d;
            Integer refundNum = 0;
            List<OrderInfo> orders = entry.getValue();
            for (OrderInfo order : orders) {
                // 交易额、订单数、退款金额、退款笔数、成交额
                transAmount += order.getPrice();
                transNum += 1;
                if (order.getRefundStatus() == 1) {
                    refundAmount += order.getPrice();
                    refundNum += 1;
                }
            }
            Double turnoverAmount = (double) Math.round((transAmount - refundAmount) * 100) / 100;
            busAnalysis = new BusAnalysis();
            busAnalysis.setTransAmount((double) Math.round(transAmount * 100) / 100);
            busAnalysis.setTransNum(transNum);
            busAnalysis.setRefundAmount((double) Math.round(refundAmount * 100) / 100);
            busAnalysis.setRefundNum(refundNum);
            busAnalysis.setTurnoverAmount(turnoverAmount);
            busAnalysis.setDayStr(dayStr);
            busAnalyses.add(busAnalysis);
        }
        Map<String, BusAnalysis> baMap = new HashMap<>();
        for (BusAnalysis ba : busAnalyses) {
            baMap.put(ba.getDayStr(), ba);
        }
        List<BusAnalysis> busAnalysisList = new ArrayList<>();
        BusAnalysis busA = null;
        List<String> dates = DateUtil.getBetweenDates(startTime, endTime);
        for (int i = 0; i < dates.size(); i++) {
            if (baMap.get(dates.get(i))==null) {
                busA = new BusAnalysis();
                busA.setTransAmount(0d);
                busA.setTransNum(0);
                busA.setRefundAmount(0d);
                busA.setRefundNum(0);
                busA.setTurnoverAmount(0d);
                busA.setDayStr(dates.get(i));
                busAnalysisList.add(busA);
            } else {
                busAnalysisList.add(baMap.get(dates.get(i)));
            }
        }
        busAnalysisList.sort((o1, o2) -> o1.getDayStr().compareTo(o2.getDayStr()));
        return busAnalysisList;
    }

    /**
     * 订单列表转化为统计产品销量、利润列表
     *
     * @param orderInfos 订单信息列表
     * @return 产品销售情况列表
     */
    private List<ProdAnalysis> convertOrders2ProdAnalysis(List<OrderInfo> orderInfos, Pagination pagination) {
        if (CollectionUtils.isEmpty(orderInfos)) {
            return new ArrayList<>();
        }
        Map<String, List<OrderInfo>> map = new HashMap<>();
        List<OrderInfo> orderInfoList = null;
        for (OrderInfo orderInfo : orderInfos) {
            String barCode = orderInfo.getBarCode();
            orderInfoList = map.get(barCode);
            if (CollectionUtils.isEmpty(orderInfoList)) {
                orderInfoList = new ArrayList<>();
                map.put(barCode, orderInfoList);
            }
            orderInfoList.add(orderInfo);
        }

        List<ProdAnalysis> prodAnalyses = new ArrayList<>();
        ProdAnalysis prodAnalysis = null;
        for (Map.Entry<String, List<OrderInfo>> entry : map.entrySet()) {
            List<OrderInfo> orders = entry.getValue();
            // prodAnalysis需要set的属性
            String barCode = entry.getKey();
            String productName = "";
            Integer salesVolume = 0;
            if (!CollectionUtils.isEmpty(orders)) {
                productName = orders.get(0).getName();
                salesVolume = orders.size();
            }
            // 其他属性
            Double transAmount = 0d;
            Double refundAmount = 0d;
            Double costAmount = 0d;
            for (OrderInfo order : orders) {
                transAmount += order.getPrice();
                if (order.getRefundStatus() == 1) {
                    refundAmount += order.getPrice();
                } else {
                    costAmount += order.getCostPrice();
                }
            }
            Double turnoverAmount = (double) Math.round((transAmount - refundAmount) * 100) / 100;
            Double grossProfit = (double) Math.round((transAmount - refundAmount - costAmount) * 100) / 100;
            prodAnalysis = new ProdAnalysis();
            prodAnalysis.setBarCode(barCode);
            prodAnalysis.setProductName(productName);
            prodAnalysis.setTurnoverAmount(turnoverAmount);
            prodAnalysis.setTransAmount((double) Math.round(transAmount*100)/100);
            prodAnalysis.setSalesVolume(salesVolume);
            prodAnalysis.setGrossProfit(grossProfit);
            prodAnalyses.add(prodAnalysis);
        }
        // 按销量排序
        prodAnalyses.sort((o1, o2) -> o2.getSalesVolume().compareTo(o1.getSalesVolume()));
        pagination.setTotal(prodAnalyses.size());
        Integer start = pagination.getStart();
        log.info("start:"+start);
        Integer end =  pagination.getEnd()>pagination.getTotal()?pagination.getTotal():pagination.getEnd();
        log.info("end:"+end);
        prodAnalyses = prodAnalyses.subList(start, end);
        return prodAnalyses;
    }

    /**
     * 订单列表转化为设备今日平均成交额、昨日平均成交额
     *
     * @param orderInfos 订单信息列表
     * @return 今日、昨日设备平均
     */
    private TermAvgAnalysis convertOrders2TermAvgAnalysis(List<OrderInfo> orderInfos) {
        if (CollectionUtils.isEmpty(orderInfos)) {
            return new TermAvgAnalysis();
        }
        // 今日设备总交易额
        Double todTransAmount = 0d;
        // 昨日设备总交易额
        Double yesTransAmount = 0d;
        // 设备id列表，用于计算设备数量
        List<String> terminalId = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfos) {
            Date payTime = orderInfo.getPayTime();
            if (payTime.after(DateUtil.getStartOfDay(new Date()))) {
                // 今日
                todTransAmount += orderInfo.getPrice();
            } else {
                // 昨日
                yesTransAmount += orderInfo.getPrice();
            }
            if (!terminalId.contains(orderInfo.gettId())) {
                terminalId.add(orderInfo.gettId());
            }
        }
        if (CollectionUtils.isEmpty(terminalId)) {
            return new TermAvgAnalysis();
        }
        Double todAvgTransAmount = (double) Math.round((todTransAmount / terminalId.size()) * 100) / 100;
        Double yesAvgTransAmount = (double) Math.round((yesTransAmount / terminalId.size()) * 100) / 100;
        TermAvgAnalysis termAvgAnalysis = new TermAvgAnalysis();
        termAvgAnalysis.setTodAvgTransAmount(todAvgTransAmount);
        termAvgAnalysis.setYesAvgTransAmount(yesAvgTransAmount);
        return termAvgAnalysis;
    }

    /**
     * 订单列表转化为设备今日、昨日、当月--成交额、毛利、订单量
     *
     * @param orderInfos 订单信息列表
     * @return 设备经营情况列表
     */
    private List<TermAnalysis> convertOrders2TermAnalysis(List<OrderInfo> orderInfos) {
        if (CollectionUtils.isEmpty(orderInfos)) {
            return new ArrayList<>();
        }
        // 转为Map key:terminalId  value:List<OrderInfo>
        Map<String, List<OrderInfo>> map = new HashMap<>();
        List<OrderInfo> orderInfoList = null;
        for (OrderInfo orderInfo : orderInfos) {
            String terminalId = orderInfo.gettId();
            orderInfoList = map.get(terminalId);
            if (CollectionUtils.isEmpty(orderInfoList)) {
                orderInfoList = new ArrayList<>();
                map.put(terminalId, orderInfoList);
            }
            orderInfoList.add(orderInfo);
        }
        // 计算相关属性
        List<TermAnalysis> termAnalyses = new ArrayList<>();
        TermAnalysis termAnalysis = null;
        for (Map.Entry<String, List<OrderInfo>> entry : map.entrySet()) {
            // 交易额（今日、昨日、当月）
            Double todTransAmount = 0d;
            Double yesTransAmount = 0d;
            Double monthTransAmount = 0d;
            // 退款金额（今日、昨日、当月）
            Double todRefundAmount = 0d;
            Double yesRefundAmount = 0d;
            Double monthRefundAmount = 0d;
            // 交易成功订单成本价 （今日、昨日、当月）
            Double todCostAmount = 0d;
            Double yesCostAmount = 0d;
            Double monthCostAmount = 0d;
            // 订单量（昨日、今日、当月）
            Integer todSalesVolume = 0;
            Integer yesSalesVolume = 0;
            Integer monthSalesVolume = 0;
            List<OrderInfo> orders = entry.getValue();
            for (OrderInfo order : orders) {
                Date payTime = order.getPayTime();
                if (payTime.after(DateUtil.getStartOfDay(new Date()))) {
                    todSalesVolume++;
                    todTransAmount += order.getPrice();
                    if (order.getRefundStatus() == 1) {
                        todRefundAmount += order.getPrice();
                    } else {
                        todCostAmount += order.getCostPrice();
                    }
                }
                if (payTime.after(DateUtil.getBeforeDayStart(-1)) && payTime.before(DateUtil.getBeforeDayEnd(-1))) {
                    yesSalesVolume++;
                    yesTransAmount += order.getPrice();
                    if (order.getRefundStatus() == 1) {
                        yesRefundAmount += order.getPrice();
                    } else {
                        yesCostAmount += order.getCostPrice();
                    }
                }
                if (payTime.after(DateUtil.getFirstDayOfMonth(new Date()))) {
                    monthSalesVolume++;
                    monthTransAmount += order.getPrice();
                    if (order.getRefundStatus() == 1) {
                        monthRefundAmount += order.getPrice();
                    } else {
                        monthCostAmount += order.getCostPrice();
                    }
                }
            }
            termAnalysis = new TermAnalysis();
            termAnalysis.setTerminalId(entry.getKey());
            termAnalysis.setTodTransAmount((double) Math.round(todTransAmount * 100) / 100);
            termAnalysis.setTodGrossProfit((double) Math.round((todTransAmount - todRefundAmount - todCostAmount) * 100) / 100);
            termAnalysis.setTodSalesVolume(todSalesVolume);
            termAnalysis.setYesTransAmount((double) Math.round(yesTransAmount * 100) / 100);
            termAnalysis.setYesGrossProfit((double) Math.round((yesTransAmount - yesRefundAmount - yesCostAmount) * 100) / 100);
            termAnalysis.setYesSalesVolume(yesSalesVolume);
            termAnalysis.setMonthTransAmount((double) Math.round(monthTransAmount * 100) / 100);
            termAnalysis.setMonthGrossProfit((double) Math.round((monthTransAmount - monthRefundAmount - monthCostAmount) * 100) / 100);
            termAnalysis.setMonthSalesVolume(monthSalesVolume);
            termAnalyses.add(termAnalysis);
        }
        List<String> terminalIds = termAnalyses.stream().map(TermAnalysis::getTerminalId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(terminalIds)) {
            List<YpTerminal> ypTerminals = ypTerminalService.getYpTerminalListByIds(terminalIds);
            // 转化为Map：key:terminalId  value:YpTerminal
            Map<String, YpTerminal> terminalMap = new HashMap<>();
            for (YpTerminal ypTerminal : ypTerminals) {
                terminalMap.put(ypTerminal.getId(), ypTerminal);
            }
            for (TermAnalysis t : termAnalyses) {
                if (terminalMap.get(t.getTerminalId()) != null) {
                    t.setTerminalName(terminalMap.get(t.getTerminalId()).getName());
                }
            }
        }
        return termAnalyses;
    }

    public static void main(String[] args) {

    }

}
