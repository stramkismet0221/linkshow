package com.ddzhuan.manage.controller.jumi.drp;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.drp.JmGoodsUnit;
import com.ddzhuan.manage.service.jumi.JmGoodsUnitService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/27 11:18
 */
@Controller
@RequestMapping("/jumi/drp/goodsunit/")
public class JmGoodsUnitController {

    protected final Log log = LogFactory.getLog(JmGoodsUnitController.class);

    @Autowired
    private JmGoodsUnitService jmGoodsUnitService;

    /**
     * 分页查询商品单位列表
     * @param model model
     * @param jmGoodsUnit 商品单位对象(条件)
     * @param pagination 分页参数
     * @return 商品单位对象列表
     */
    @RequestMapping("getlist")
    public String getGoodsJmGoodsUnitList(Model model, JmGoodsUnit jmGoodsUnit, Pagination pagination){
        List<JmGoodsUnit> jmGoodsUnits = jmGoodsUnitService.pageJmGoodsUnit(jmGoodsUnit,pagination);
        model.addAttribute("jmGoodsUnit",jmGoodsUnit);
        model.addAttribute("jmGoodsUnits",jmGoodsUnits);
        return "jumi/drp/jmgoodsunit/jmgoodsunitlist";
    }

    /**
     * 新增商品单位
     * @param jmGoodsUnit 商品单位对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("insertjmgoodsunit")
    @ResponseBody
    public BaseResultInfo insertGoodsJmGoodsUnit(JmGoodsUnit jmGoodsUnit){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsUnitService.insertJmGoodsUnit(jmGoodsUnit);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("新增商品单位成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("新增商品单位异常");
        }
        return resultInfo;

    }


    /**
     * 更新商品单位
     * @param jmGoodsUnitId 商品单位对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("updatejmgoodsunit")
    @ResponseBody
    public BaseResultInfo updateJmGoodsUnit(JmGoodsUnit jmGoodsUnitId){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsUnitService.updateJmGoodsUnitById(jmGoodsUnitId);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("更新商品单位成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("更新商品单位异常");
        }
        return resultInfo;
    }

    /**
     * 删除商品单位
     * @param jmGoodsUnitId 商品单位ID
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("deljmgoodsunit")
    @ResponseBody
    public BaseResultInfo delJmGoodsUnit(Long jmGoodsUnitId){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsUnitService.delJmGoodsUnitByIds(jmGoodsUnitId);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("删除商品单位成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("删除商品单位异常");
        }
        return resultInfo;
    }

    /**
     * 跳转新增厂家页面
     */
    @RequestMapping("toaddjmgoodsunit")
    public String toAddJmGoodsUnit() {
        return "jumi/drp/jmgoodsunit/addjmgoodsunit";
    }

    /**
     * 跳转厂家编辑/详情页面
     * @param model model
     * @param id 商品单位ID
     * @param type 类型 0详情, 1编辑
     */
    @RequestMapping("jmgoodsunitinfo")
    public String toUpdateJmGoodsUnit(Model model, @RequestParam(value = "id")Long id, Integer type) {
        JmGoodsUnit jmGoodsUnit = jmGoodsUnitService.getJmGoodsUnitById(id);
        model.addAttribute("jmGoodsUnit",jmGoodsUnit);
        String view = "";
        if (OperateTypeEnum.DETAIL.code.equals(type)) {
            return "jumi/drp/jmgoodsunit/jmgoodsunitinfo";
        }
        if (OperateTypeEnum.EDIT.code.equals(type)) {
           return "jumi/drp/jmgoodsunit/modifyjmgoodsunit";
        }
        return view;
    }


    @RequestMapping("checkunitname")
    @ResponseBody
    public BaseResultInfo checkUnitName(String name,Long id){
        BaseResultInfo resultInfo = new BaseResultInfo();
        boolean res = jmGoodsUnitService.getUnitByName(name,id);
        resultInfo.setSuccess(res);
        return resultInfo;
    }








}
