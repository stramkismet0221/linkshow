package com.ddzhuan.manage.controller.jumi.product;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.JmGoodsExtend;
import com.ddzhuan.manage.service.jumi.JmGoodsExtendService;
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
@RequestMapping("/jumi/product/goodsextend/")
public class JmGoodsExtendController {

    protected final Log log = LogFactory.getLog(JmGoodsExtendController.class);

    @Autowired
    private JmGoodsExtendService jmGoodsExtendService;

    /**
     * 分页查询自定义字段列表
     * @param model model
     * @param jmGoodsExtend 自定义字段对象(条件)
     * @param pagination 分页参数
     * @return 自定义字段对象列表
     */
    @RequestMapping("getlist")
    public String getGoodsjmgoodsextendList(Model model, JmGoodsExtend jmGoodsExtend, Pagination pagination){
        List<JmGoodsExtend> jmGoodsExtends = jmGoodsExtendService.pageJmGoodsExtend(jmGoodsExtend,pagination);
        model.addAttribute("jmGoodsExtend",jmGoodsExtend);
        model.addAttribute("jmGoodsExtends",jmGoodsExtends);
        return "jumi/product/jmgoodsextend/jmgoodsextendlist";
    }

    /**
     * 新增自定义字段
     * @param jmGoodsExtend 自定义字段对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("insertjmgoodsextend")
    @ResponseBody
    public BaseResultInfo insertGoodsjmgoodsextend(JmGoodsExtend jmGoodsExtend){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsExtendService.insertJmGoodsExtend(jmGoodsExtend);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("新增自定义字段成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("新增自定义字段异常");
        }
        return resultInfo;

    }


    /**
     * 更新自定义字段
     * @param jmGoodsExtend 自定义字段对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("updatejmgoodsextend")
    @ResponseBody
    public BaseResultInfo updateJmGoodsExtend(JmGoodsExtend jmGoodsExtend){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            if (1 == jmGoodsExtend.getFieldType() || 5 == jmGoodsExtend.getFieldType()) {
                jmGoodsExtend.setFieldValue("");
            }
            jmGoodsExtendService.updateJmGoodsExtendById(jmGoodsExtend);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("更新自定义字段成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("更新自定义字段异常");
        }
        return resultInfo;
    }

    /**
     * 删除自定义字段
     * @param jmGoodsExtendId 自定义字段ID
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("deljmgoodsextend")
    @ResponseBody
    public BaseResultInfo delJmGoodsExtend(Long jmGoodsExtendId){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsExtendService.delJmGoodsExtendByIds(jmGoodsExtendId);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("删除自定义字段成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("删除自定义字段异常");
        }
        return resultInfo;
    }

    /**
     * 跳转新增厂家页面
     */
    @RequestMapping("toaddjmgoodsextend")
    public String toAddJmGoodsExtend() {
        return "jumi/product/jmgoodsextend/addjmgoodsextend";
    }

    /**
     * 跳转厂家编辑/详情页面
     * @param model model
     * @param id 自定义字段ID
     * @param type 类型 0详情, 1编辑
     */
    @RequestMapping("jmgoodsextendinfo")
    public String toUpdateJmGoodsExtend(Model model, @RequestParam(value = "id")Long id, Integer type) {
        JmGoodsExtend jmGoodsExtend = jmGoodsExtendService.getJmGoodsExtendById(id);
        model.addAttribute("jmGoodsExtend",jmGoodsExtend);
        String view = "";
        if (OperateTypeEnum.DETAIL.code.equals(type)) {
            return "jumi/product/jmgoodsextend/jmgoodsextendinfo";
        }
        if (OperateTypeEnum.EDIT.code.equals(type)) {
           return "jumi/product/jmgoodsextend/modifyjmgoodsextend";
        }
        return view;
    }
    
}
