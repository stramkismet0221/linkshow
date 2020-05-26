package com.ddzhuan.manage.controller.jumi.product;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.JmGoodsManuFatory;
import com.ddzhuan.manage.service.jumi.JmGoodsManuFatoryService;
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
@RequestMapping("/jumi/product/goodsmanufatory")
public class JmGoodsManuFatoryController {

    protected final Log log = LogFactory.getLog(JmGoodsManuFatoryController.class);

    @Autowired
    private JmGoodsManuFatoryService jmGoodsManuFatoryService;

    /**
     * 分页查询生产厂家列表
     * @param model model
     * @param jmGoodsManuFatory 生产厂家对象(条件)
     * @param pagination 分页参数
     * @return 生产厂家对象列表
     */
    @RequestMapping("getlist")
    public String getGoodsManuFatoryList(Model model, JmGoodsManuFatory jmGoodsManuFatory, Pagination pagination){
        List<JmGoodsManuFatory> jmGoodsManuFatorys = jmGoodsManuFatoryService.pageJmGoodsManuFatory(jmGoodsManuFatory,pagination);
        model.addAttribute("jmGoodsManuFatory",jmGoodsManuFatory);
        model.addAttribute("jmGoodsManuFatorys",jmGoodsManuFatorys);
        return "jumi/product/goodsmanufatory/goodsmanufactorylist";
    }

    /**
     * 新增生产厂家
     * @param jmGoodsManuFatory 生产厂家对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("insertjmgoodsmanufatory")
    @ResponseBody
    public BaseResultInfo insertGoodsManuFatory(JmGoodsManuFatory jmGoodsManuFatory){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsManuFatoryService.insertJmGoodsManuFatory(jmGoodsManuFatory);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("新增生产厂家成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("新增生产厂家异常");
        }
        return resultInfo;

    }


    /**
     * 更新生产厂家
     * @param jmGoodsManuFatory 生产厂家对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("updatejmgoodsmanufatory")
    @ResponseBody
    public BaseResultInfo updateJmGoodsManuFatory(JmGoodsManuFatory jmGoodsManuFatory){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsManuFatoryService.updateJmGoodsManuFatoryById(jmGoodsManuFatory);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("更新生产厂家成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("更新生产厂家异常");
        }
        return resultInfo;
    }

    /**
     * 删除生产厂家
     * @param jmGoodsManuFatoryId 生产厂家ID
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("deljmgoodsmanufatory")
    @ResponseBody
    public BaseResultInfo delJmGoodsManuFatory(Long jmGoodsManuFatoryId){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsManuFatoryService.delJmGoodsManuFatoryByIds(jmGoodsManuFatoryId);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("删除生产厂家成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("删除生产厂家异常");
        }
        return resultInfo;
    }

    /**
     * 跳转新增厂家页面
     */
    @RequestMapping("toaddjmgoodsmanufactory")
    public String toAddJmGoodsManuFactory() {
        return "jumi/product/goodsmanufatory/addjmgoodsmanufactory";
    }

    /**
     * 跳转厂家编辑/详情页面
     * @param model model
     * @param id 厂家ID
     * @param type 类型 0详情, 1编辑
     */
    @RequestMapping("jmgoodsmanufatoryinfo")
    public String toUpdateJmGoodsManuFatory(Model model, @RequestParam(value = "id")Long id, Integer type) {
        JmGoodsManuFatory jmGoodsManuFatory = jmGoodsManuFatoryService.getJmGoodsManuFatoryById(id);
        model.addAttribute("jmGoodsManuFatory",jmGoodsManuFatory);
        String view = "";
        if (OperateTypeEnum.DETAIL.code.equals(type)) {
            view = "jumi/product/goodsmanufatory/jmgoodsmanufactoryinfo";
        }
        if (OperateTypeEnum.EDIT.code.equals(type)) {
            view = "jumi/product/goodsmanufatory/modifyjmgoodsmanufatory";
        }
        return view;
    }
    
}
