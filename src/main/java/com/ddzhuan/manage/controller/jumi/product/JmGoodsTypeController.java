package com.ddzhuan.manage.controller.jumi.product;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;
import com.ddzhuan.manage.service.jumi.JmGoodsTypeService;
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
 * @date 2019/12/26 13:49
 */
@Controller
@RequestMapping("/jumi/product/goodstype/")
public class JmGoodsTypeController {

    protected final Log log = LogFactory.getLog(JmGoodsTypeController.class);


    @Autowired
    private JmGoodsTypeService jmGoodsTypeService;


    /**
     * 分页查询商品分类列表
     * @param model model
     * @param jmGoodsType 商品分类对象(条件)
     * @param pagination 分页参数
     * @return 商品分类对象列表
     */
    @RequestMapping("getlist")
    public String getGoodsTypeList(Model model, JmGoodsType jmGoodsType, Pagination pagination){
        List<JmGoodsType> jmGoodsTypes = jmGoodsTypeService.pageJmGoodsType(jmGoodsType,new Pagination(1, Integer.MAX_VALUE));
        model.addAttribute("jmGoodsTypes",jmGoodsTypes);
        return "jumi/product/goodstype/goodstypelist";
    }

    /**
     * 新增商品分类
     * @param jmGoodsType 商品分类对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("insertjmgoodstype")
    @ResponseBody
    public BaseResultInfo insertGoodsType(JmGoodsType jmGoodsType){
        BaseResultInfo resultInfo = jmGoodsTypeService.insertJmGoodsType(jmGoodsType);
        return resultInfo;
    }


    /**
     * 更新商品分类
     * @param jmGoodsType 商品分类对象
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("updatejmgoodstype")
    @ResponseBody
    public BaseResultInfo updateJmGoodsType(JmGoodsType jmGoodsType){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            jmGoodsTypeService.updateJmGoodsTypeById(jmGoodsType);
            resultInfo.setSuccess(true);
            resultInfo.setMsg("更新商品分类成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("更新商品分类异常");
        }
        return resultInfo;
    }

    /**
     * 删除商品分类
     * @param jmGoodsTypeId 商品分类ID集合
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("deljmgoodstype")
    @ResponseBody
    public BaseResultInfo delJmGoodsType(Long jmGoodsTypeId){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            int count = jmGoodsTypeService.countJmGoodsTypeById(jmGoodsTypeId);
            if (count > 0) {
                resultInfo.setSuccess(false);
                resultInfo.setMsg("该分类下含有子分类,暂不能删除");
                return resultInfo;
            }
            resultInfo.setSuccess(jmGoodsTypeService.delJmGoodsTypeByIds(jmGoodsTypeId));
            resultInfo.setMsg("删除商品分类成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("删除商品分类异常");
        }
        return resultInfo;
    }

    /**
     * 跳转新增子分类页面
     * @param model model
     * @param pid 父级分类ID
     */
    @RequestMapping("addchildjmgoodstype")
    public String addMenu(Model model, @RequestParam(value = "pid")Long pid) {
        JmGoodsType jmGoodsType = jmGoodsTypeService.getJmGoodsTypeById(pid);
        model.addAttribute("jmGoodsType",jmGoodsType);
        model.addAttribute("pid",pid);
        return "jumi/product/goodstype/addchildjmgoodstype";
    }

    /**
     * 跳转编辑分类页面
     * @param model model
     * @param id 分类ID
     */
    @RequestMapping("jmgoodstypeinfo")
    public String toUpdateJmGoodsType(Model model, @RequestParam(value = "id")Long id,Integer type) {
        JmGoodsType jmGoodsType = jmGoodsTypeService.getJmGoodsTypeById(id);
        JmGoodsType parent = jmGoodsTypeService.getJmGoodsTypeById(jmGoodsType.getPid());
        jmGoodsType.setParent(parent);
        String pname = getJmGoodsTypePname(jmGoodsType,new StringBuilder()).toString();
        jmGoodsType.getParent().setName(pname);
        model.addAttribute("jmGoodsType",jmGoodsType);
        String view = "";
        if (OperateTypeEnum.DETAIL.code.equals(type)) {
            view = "jumi/product/goodstype/jmgoodstypeinfo";
        }
        if (OperateTypeEnum.EDIT.code.equals(type)) {
            view = "jumi/product/goodstype/modifyjmgoodstype";
        }
        return view;
    }

    private StringBuilder getJmGoodsTypePname(JmGoodsType jmGoodsType, StringBuilder pname){
        if (0 != jmGoodsType.getPid()) {
            JmGoodsType p = jmGoodsTypeService.getJmGoodsTypeById(jmGoodsType.getPid());
            if (0 != p.getPid()) {
                pname.append(p.getName()).append(" —> ");
                getJmGoodsTypePname(p,pname);
            }else {
                pname.append(p.getName());
            }
        }
        return pname;
    }
}
