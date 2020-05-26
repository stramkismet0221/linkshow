package com.ddzhuan.manage.controller.datav;

import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.datav.member.*;
import com.ddzhuan.manage.service.datav.MemberService;
import com.ddzhuan.manage.tool.yopoint.NingLianYoPointConfig;
import com.ddzhuan.manage.tool.yopoint.YoPointApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * datav 数据大屏，可视化界面统计api--会员数据统计
 *
 * @author likeke
 * @date 2019/09/03
 */
@Controller
@RequestMapping("/datav/")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    /**
     * 供应商相关视图，根据method不同，调用不同接口
     * @param method 接口名
     * @return 重定向
     */
    @RequestMapping("member")
    public String member(HttpServletRequest request, HttpServletResponse response,
                         Model model, String method, @RequestParam(required = false) Integer type) {
        String redirect = type==null?String.format("redirect:%s", method):String.format("redirect:%s?type=%s", method, type);
        return redirect;
    }

    /**
     * 会员数/新增会员
     */
    @RequestMapping("mtotal")
    @ResponseBody
    public Object getTotal(HttpServletRequest request, HttpServletResponse response, Model model,
                           @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<MemberTotal> memberTotals1 = memberService.getMemberTotalList(1);
            List<MemberTotal> memberTotals2 = memberService.getMemberTotalList(2);
            return fillTotalList(memberTotals1, memberTotals2);
        }
        List<MemberTotal> memberTotals3 = memberService.getMemberTotalList(3);
        Double value = 0d;
        if (!CollectionUtils.isEmpty(memberTotals3) && memberTotals3.size() == 1) {
            value = memberTotals3.get(0).getMemberNum();
        }
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setName("");
        memberInfo.setValue(value);
        List<MemberInfo> list = new ArrayList<>();
        list.add(memberInfo);
        return list;
    }

    /**
     * 新增会员/增长率
     *
     * @param type 1、折线图 2、新增会员数 3、增长率
     * @return
     */
    @RequestMapping("mgrowth")
    @ResponseBody
    public Object getGrowth(HttpServletRequest request, HttpServletResponse response, Model model,
                            @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<MemberGrowth> memberGrowths1 = memberService.getMemberGrowthList(1);
            List<MemberGrowth> memberGrowths2 = memberService.getMemberGrowthList(2);
            return fillGrowthList(memberGrowths1, memberGrowths2);
        }
        MemberInfo memberInfo = new MemberInfo();
        List<MemberInfo> list = new ArrayList<>();
        if (type != null && type == 2) {
            List<MemberGrowth> memberGrowths3 = memberService.getMemberGrowthList(3);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberGrowths3) && memberGrowths3.size()==1) {
                value = memberGrowths3.get(0).getNewMember();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        if (type != null && type == 3) {
            List<MemberGrowth> memberGrowths4 = memberService.getMemberGrowthList(4);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberGrowths4) && memberGrowths4.size()==1) {
                value = memberGrowths4.get(0).getGrowth();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        return list;
    }

    /**
     * LinkBay新增会员/增长率
     *
     * @param type 1、折线图 2、LinkBay新增会员数 3、LinkBay新增会员增长率
     * @return
     */
    @RequestMapping("mlinkbaygrowth")
    @ResponseBody
    public Object getLinkBayGrowth(HttpServletRequest request, HttpServletResponse response, Model model,
                                   @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<MemberLBGrowth> memberLBGrowths1 = memberService.getMemberLBGrowthList(1);
            List<MemberLBGrowth> memberLBGrowths2 = memberService.getMemberLBGrowthList(2);
            return fillLinkBayGrowthList(memberLBGrowths1, memberLBGrowths2);
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo memberInfo = new MemberInfo();
        if (type != null && type == 2) {
            List<MemberLBGrowth> memberLBGrowths3 = memberService.getMemberLBGrowthList(3);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberLBGrowths3) && memberLBGrowths3.size()==1) {
                value = memberLBGrowths3.get(0).getNewMember();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        if (type != null && type == 3) {
            List<MemberLBGrowth> memberLBGrowths4 = memberService.getMemberLBGrowthList(4);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberLBGrowths4) && memberLBGrowths4.size()==1) {
                value = memberLBGrowths4.get(0).getGrowth();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        return list;
    }

    /**
     * 会员消费额/占比
     *
     * @param type 1、折线图 2、会员消费额 3、占比
     * @return
     */
    @RequestMapping("mconsume")
    @ResponseBody
    public Object getConsume(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<MemberConsume> memberConsumes1 = memberService.getMemberConsumeList(1);
            List<MemberConsume> memberConsumes2 = memberService.getMemberConsumeList(2);
            return fillConsumeList(memberConsumes1, memberConsumes2);
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo memberInfo = new MemberInfo();
        if (type != null && type == 2) {
            List<MemberConsume> memberConsumes3 = memberService.getMemberConsumeList(3);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberConsumes3) && memberConsumes3.size()==1) {
                value = memberConsumes3.get(0).getSales();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        if (type != null && type == 3) {
            List<MemberConsume> memberConsumes4 = memberService.getMemberConsumeList(4);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberConsumes4) && memberConsumes4.size()==1) {
                value = memberConsumes4.get(0).getProportion();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        return list;
    }

    /**
     * 由linkBay带来的会员销售额
     *
     * @param type 1、折线图 2、总额 3、同比 4、环比
     * @return
     */
    @RequestMapping("mlinkbaysales")
    @ResponseBody
    public Object LinkBaySales(HttpServletRequest request, HttpServletResponse response, Model model,
                               @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<MemberLBConsume> memberLBConsumes1 = memberService.getMemberLBConsumeList(1);
            List<MemberLBConsume> memberLBConsumes2 = memberService.getMemberLBConsumeList(2);
            return fillLinkBaySalesList(memberLBConsumes1, memberLBConsumes2);
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo memberInfo = new MemberInfo();
        if (type != null && type == 2) {
            List<MemberLBConsume> memberLBConsumes3 = memberService.getMemberLBConsumeList(3);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberLBConsumes3) && memberLBConsumes3.size()==1) {
                value = memberLBConsumes3.get(0).getSales();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        if (type != null && type == 3) {
            List<MemberLBConsume> memberLBConsumes4 = memberService.getMemberLBConsumeList(4);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberLBConsumes4) && memberLBConsumes4.size()==1) {
                value = memberLBConsumes4.get(0).gettCompare();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        if (type != null && type == 4) {
            List<MemberLBConsume> memberLBConsumes5 = memberService.getMemberLBConsumeList(5);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberLBConsumes5) && memberLBConsumes5.size()==1) {
                value = memberLBConsumes5.get(0).gethCompare();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        return list;
    }

    /**
     * LinkBay会员销售额月度占比
     *
     * @param type 1、月度销售额比例 2、累计占比
     * @return
     */
    @RequestMapping("mmonthsales")
    @ResponseBody
    public Object getMonthSales(HttpServletRequest request, HttpServletResponse response, Model model,
                                @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<MemberMConsume> memberMConsumes = memberService.getMemberMConsumeList(1);
            return fillMonthSalesList(memberMConsumes);
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo memberInfo = new MemberInfo();
        if (type != null && type == 2) {
            List<MemberMConsume> memberMConsumes = memberService.getMemberMConsumeList(2);
            Double total = 0d;
            Double proportion = 0d;
            if (!CollectionUtils.isEmpty(memberMConsumes) && memberMConsumes.size()==1) {
                total = memberMConsumes.get(0).getTotal();
                proportion = memberMConsumes.get(0).getProportion();
            }
            DecimalFormat df = new DecimalFormat("#");
            memberInfo.setName(df.format(total));
            memberInfo.setS(proportion.toString());
            list.add(memberInfo);
        }
        return list;
    }

    /**
     * linkBay会员带来利润：当月/累计
     *
     * @param type 1、柱状图 2、当月 3、累计
     * @return
     */
    @RequestMapping("mlinkbayprofit")
    @ResponseBody
    public Object getLinkBayProfit(HttpServletRequest request, HttpServletResponse response, Model model,
                                   @RequestParam(required = false) Integer type) {
        if (type != null && type == 1) {
            List<MemberLBProfit> memberLBProfits = memberService.getMemberLBProfitList(1);
            return fillLinkBayProfitList(memberLBProfits);
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo memberInfo = new MemberInfo();
        if (type != null && type == 2) {
            List<MemberLBProfit> memberLBProfits = memberService.getMemberLBProfitList(2);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberLBProfits) && memberLBProfits.size()==1) {
                value = memberLBProfits.get(0).getMonthProfit();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        if (type != null && type == 3) {
            List<MemberLBProfit> memberLBProfits = memberService.getMemberLBProfitList(3);
            Double value = 0d;
            if (!CollectionUtils.isEmpty(memberLBProfits) && memberLBProfits.size()==1) {
                value = memberLBProfits.get(0).getTotalProfit();
            }
            memberInfo.setName("");
            memberInfo.setValue(value);
            list.add(memberInfo);
        }
        return list;
    }

    /**
     * 区域会员销售情况
     *
     * @return
     */
    @RequestMapping("mareasales")
    @ResponseBody
    public Object getAreaSales(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<MemberAreaSales> memberAreaSalesList = memberService.getMemberAreaSalesList();
        return fillAreaSaleList(memberAreaSalesList);
    }

    /**
     * 店铺会员销售情况
     * @return
     */
    @RequestMapping("mstoresales")
    @ResponseBody
    public Object getStoreSales(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<MemberStoreSales> memberStoreSalesList = memberService.getMemberStoreSalesList();
        return fillStoreSaleList(memberStoreSalesList);
    }

    /**
     * 店铺会员销售占比
     * @return
     */
    @RequestMapping("mstoresalespro")
    @ResponseBody
    public Object getStoreSalesProportion(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<MemberStoreSalesPro> memberStoreSalesProList = memberService.getMemberStoreSalesProList();
        return fillStoreSaleProList(memberStoreSalesProList);
    }

    private List<StoreMember> fillStoreSaleProList(List<MemberStoreSalesPro> memberStoreSalesProList) {
        if (CollectionUtils.isEmpty(memberStoreSalesProList)) {
            return new ArrayList<>();
        }
        List<StoreMember> list = new ArrayList<>();
        StoreMember s = null;
        for (MemberStoreSalesPro mssp : memberStoreSalesProList) {
            s = new StoreMember();
            s.setArea(mssp.getStoreName());
            s.setPv(mssp.getTotalSales());
            s.setSeries1(mssp.getProportion());
            s.setSeries2(mssp.getIconUrl());
            s.setAttribute(mssp.getMemberSales());
            list.add(s);
        }
        return list;
    }

    private List<StoreMember> fillStoreSaleList(List<MemberStoreSales> memberStoreSalesList) {
        if (CollectionUtils.isEmpty(memberStoreSalesList)) {
            return new ArrayList<>();
        }
        List<StoreMember> list = new ArrayList<>();
        StoreMember s = null;
        for (MemberStoreSales mss : memberStoreSalesList) {
            s = new StoreMember();
            s.setArea(mss.getStoreName());
            s.setPv(mss.getLastYearSales());
            s.setSeries1(mss.getGrowth());
            s.setSeries2(mss.getIconUrl());
            s.setAttribute(mss.getNowYearSales());
            list.add(s);
        }
        return list;
    }

    private List<MemberInfo> fillAreaSaleList(List<MemberAreaSales> memberAreaSalesList) {
        if (CollectionUtils.isEmpty(memberAreaSalesList)) {
            return new ArrayList<>();
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo m = null;
        for (MemberAreaSales memberAreaSales : memberAreaSalesList) {
            m = new MemberInfo();
            m.setName(memberAreaSales.getAreaName());
            m.setValue(memberAreaSales.getSales());
            list.add(m);
        }
        return list;
    }

    private List<MonthData> fillLinkBayProfitList(List<MemberLBProfit> memberLBProfits) {
        if (CollectionUtils.isEmpty(memberLBProfits)) {
            return new ArrayList<>();
        }
        List<MonthData> list = new ArrayList<>();
        MonthData m = null;
        for (MemberLBProfit memberLBProfit : memberLBProfits) {
            m = new MonthData();
            m.setMonth(memberLBProfit.getMonth());
            m.setSales(String.valueOf(memberLBProfit.getProfit()));
            DecimalFormat df = new DecimalFormat("#");
            m.setValue(df.format(memberLBProfit.getValue()));
            list.add(m);
        }
        return list;
    }

    private List<MemberInfo> fillMonthSalesList(List<MemberMConsume> memberMConsumes) {
        if (CollectionUtils.isEmpty(memberMConsumes)) {
            return new ArrayList<>();
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo m = null;
        for (MemberMConsume memberMConsume : memberMConsumes) {
            m = new MemberInfo();
            m.setName(memberMConsume.getMonth());
            m.setValue(memberMConsume.getSales());
            list.add(m);
        }
        return list;
    }

    private List<MemberInfo> fillLinkBaySalesList(List<MemberLBConsume> memberLBConsumes1, List<MemberLBConsume> memberLBConsumes2) {
        if (CollectionUtils.isEmpty(memberLBConsumes1)) {
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(memberLBConsumes2)) {
            return new ArrayList<>();
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo m = null;
        for (MemberLBConsume memberLBConsume : memberLBConsumes1) {
            m = new MemberInfo();
            m.setName(memberLBConsume.getTimeStr());
            m.setValue(memberLBConsume.getSales());
            m.setS("1");
            list.add(m);
        }
        for (MemberLBConsume memberLBConsume : memberLBConsumes2) {
            m = new MemberInfo();
            m.setName(memberLBConsume.getTimeStr());
            m.setValue(memberLBConsume.getSales());
            m.setS("2");
            list.add(m);
        }
        return list;
    }

    private List<MemberInfo> fillConsumeList(List<MemberConsume> memberConsumes1, List<MemberConsume> memberConsumes2) {
        if (CollectionUtils.isEmpty(memberConsumes1)) {
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(memberConsumes2)) {
            return new ArrayList<>();
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo m = null;
        for (MemberConsume consume : memberConsumes1) {
            m = new MemberInfo();
            m.setName(consume.getTimeStr());
            m.setValue(consume.getSales());
            m.setS("1");
            list.add(m);
        }
        for (MemberConsume consume : memberConsumes2) {
            m = new MemberInfo();
            m.setName(consume.getTimeStr());
            m.setValue(consume.getSales());
            m.setS("2");
            list.add(m);
        }
        return list;
    }

    private List<MemberInfo> fillLinkBayGrowthList(List<MemberLBGrowth> memberLBGrowths1, List<MemberLBGrowth> memberLBGrowths2) {
        if (CollectionUtils.isEmpty(memberLBGrowths1)) {
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(memberLBGrowths2)) {
            return new ArrayList<>();
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo m = null;
        for (MemberLBGrowth lbGrowth : memberLBGrowths1) {
            m = new MemberInfo();
            m.setName(lbGrowth.getTimeStr());
            m.setValue(lbGrowth.getNewMember());
            m.setS("1");
            list.add(m);
        }
        for (MemberLBGrowth lbGrowth : memberLBGrowths2) {
            m = new MemberInfo();
            m.setName(lbGrowth.getTimeStr());
            m.setValue(lbGrowth.getNewMember());
            m.setS("2");
            list.add(m);
        }
        return list;
    }

    private List<MemberInfo> fillGrowthList(List<MemberGrowth> memberGrowths1, List<MemberGrowth> memberGrowths2) {
        if (CollectionUtils.isEmpty(memberGrowths1)) {
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(memberGrowths2)) {
            return new ArrayList<>();
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo m = null;
        for (MemberGrowth growth : memberGrowths1) {
            m = new MemberInfo();
            m.setName(growth.getTimeStr());
            m.setValue(growth.getNewMember());
            m.setS("1");
            list.add(m);
        }
        for (MemberGrowth growth : memberGrowths2) {
            m = new MemberInfo();
            m.setName(growth.getTimeStr());
            m.setValue(growth.getNewMember());
            m.setS("2");
            list.add(m);
        }
        return list;
    }

    private List<MemberInfo> fillTotalList(List<MemberTotal> memberTotals1, List<MemberTotal> memberTotals2) {
        if (CollectionUtils.isEmpty(memberTotals1)) {
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(memberTotals2)) {
            return new ArrayList<>();
        }
        List<MemberInfo> list = new ArrayList<>();
        MemberInfo m = null;
        for (MemberTotal total : memberTotals1) {
            m = new MemberInfo();
            m.setName(total.getTimeStr());
            m.setValue(total.getMemberNum());
            m.setS("1");
            list.add(m);
        }
        for (MemberTotal total : memberTotals2) {
            m = new MemberInfo();
            m.setName(total.getTimeStr());
            m.setValue(total.getMemberNum());
            m.setS("2");
            list.add(m);
        }
        return list;
    }


    /**
     * double类型如果小数点后为零显示整数否则保留 返回String
     * @param num
     * @return
     */
    public static String doubleTrans(double num){
        return String.valueOf(new DecimalFormat("#0.000000").format(num));
    }
}
