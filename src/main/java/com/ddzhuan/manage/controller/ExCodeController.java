package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.enums.SysLogInfoEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.ExCode;
import com.ddzhuan.manage.model.YpProduct;
import com.ddzhuan.manage.model.YpTerminal;
import com.ddzhuan.manage.service.ExCodeService;
import com.ddzhuan.manage.service.SysLogInfoService;
import com.ddzhuan.manage.service.YpProductService;
import com.ddzhuan.manage.service.YpTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/8/1 10:04
 */
@Controller
@RequestMapping("/excode/")
public class ExCodeController extends BaseController{

    @Autowired
    private ExCodeService exCodeService;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    @Autowired
    private YpTerminalService ypTerminalService;

    @Autowired
    private YpProductService ypProductService;


    /**
     * 获取兑换码库列表
     * @param exCode 条件
     * @param pagination 分页信息
     * @return
     */
    @RequestMapping("getexcodelist")
    public String getuserlist(Model model, ExCode exCode, Pagination pagination, @RequestParam(value = "type",defaultValue = "1")Integer type){

        if (OperateTypeEnum.HISTORY.code.equals(type)){
            exCode.setStatus(0);
        }else {
            exCode.setStatus(1);
        }
        List<ExCode> exCodeList = exCodeService.queryExCodeListByConditions(exCode,pagination);
        model.addAttribute("exCodeList",exCodeList);
        model.addAttribute("exCode",exCode);
        model.addAttribute("type",type);
        if (OperateTypeEnum.HISTORY.code.equals(type)){
            return "excode/history";
        }else {
            return "excode/excodelist";
        }
    }


    /**
     * 跳转新增页面
     */
    @RequestMapping("toadd")
    public String toAdd(){
        return "excode/addexcode";
    }

    /**
     * 跳转新增页面
     */
    @RequestMapping("toexcodelist")
    public String toExCodeList(Model model,@RequestParam(name = "type",defaultValue = "1",required = false) Integer type){
        model.addAttribute("type",type);
        return "excode/mainlist";
    }


    /**
     * 跳转设备列表模态页面
     */
    @RequestMapping("getmachinelist")
    public String getMachineList(Model model,
                                 String keyword,
                                 Pagination pagination,
                                 String ids){
        pagination.setRows(5);
        List<YpTerminal> terminalList = ypTerminalService.getYpTerminalListByConditions(null,keyword,pagination);
        model.addAttribute("terminalList",terminalList);
        model.addAttribute("ids",ids);
        model.addAttribute("keyword",keyword);
        return "excode/machinelist";
    }



    /**
     * 跳转商品列表
     */
    @RequestMapping("getproductlist")
    public String getProductList(Model model, Pagination pagination,
                                 @RequestParam(required = false) String keyword,
                                 String barCodes){
        pagination.setRows(5);
        List<YpProduct> products = ypProductService.getYpProductListByConditions(null, keyword, pagination);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("barCodes", barCodes);
        return "excode/productlist";
    }


    /**
     * 获取一个兑换码库详情
     * @param exCodeId 兑换码库id
     * @return
     */
    @RequestMapping("getexcodebyid")
    public String getExCodeById(Model model, Long exCodeId, Integer type){
        ExCode exCode = exCodeService.queryExCodeById(exCodeId);
        model.addAttribute("exCode",exCode);
        model.addAttribute("exCodeId",exCodeId);
        if (OperateTypeEnum.EDIT.code.equals(type)){
            return "excode/modifyexcode";
        }else {
            return "excode/excodedetail";
        }
    }

    /**
     * 新增兑换码库
     * @param exCode 兑换码库
     * @return
     */
    @RequestMapping("insertexcode")
    @ResponseBody
    public BaseResultInfo insertExCode(ExCode exCode){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result = exCodeService.insertExCode(exCode);
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+exCode.getActName()+" 新增活动数据为："+exCode.toString());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 更新兑换码库
     * @param exCode 兑换码库
     * @return
     */
    @RequestMapping("modifyexcode")
    @ResponseBody
    public BaseResultInfo modifyExCode(ExCode exCode,Integer type){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(exCodeService.modifyExCode(exCode,type));
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+exCode.getActName()+" 更新兑换码库数据为："+exCode.toString());
            result.setMsg("保存成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }


}
