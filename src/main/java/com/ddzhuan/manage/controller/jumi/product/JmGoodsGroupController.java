package com.ddzhuan.manage.controller.jumi.product;

import com.alipay.api.domain.BaseInfo;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.jumi.product.GoodsGroup;
import com.ddzhuan.manage.service.jumi.JmGoodsGroupService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jumi/product/goodsgroup/")
public class JmGoodsGroupController extends BaseController {

//    protected final Log log = LogFactory.getLog(JmGoodsGroupController.class);

    @Autowired
    private JmGoodsGroupService jmGoodsGroupService;

    @RequestMapping("getlist")
    public String getGoodsGroup(Model model, String name, Pagination pagination) {

        List<GoodsGroup> goodsGroups = jmGoodsGroupService.queryGoodsGroupBy(name, pagination);
        model.addAttribute("goodsgroup", goodsGroups);
        model.addAttribute("names", name);
        return "jumi/product/goodsgroup/grouplist";
    }

    @RequestMapping("toadd")
    public String toAddPages() {
        return "jumi/product/goodsgroup/groupadd";
    }

    @RequestMapping("add")
    @ResponseBody
    public BaseResultInfo addGoddsGroup(GoodsGroup goodsGroup) {
        BaseResultInfo resInfo = new BaseResultInfo();
        Integer res = jmGoodsGroupService.addGoodsGroup(goodsGroup);
        if (res > 0) {
            resInfo.setSuccess(true);
            resInfo.setMsg("添加成功");
        } else {
            resInfo.setSuccess(false);
            resInfo.setMsg("添加失败");
        }
        return resInfo;
    }

    @RequestMapping("delete")
    @ResponseBody
    public BaseResultInfo deleGroupById(Long id) {
        BaseResultInfo resInfo = new BaseResultInfo();
        Integer res = jmGoodsGroupService.deleGroupsById(id);
        if (res > 0) {
            resInfo.setSuccess(true);
            resInfo.setMsg("删除成功");
        } else {
            resInfo.setSuccess(false);
            resInfo.setMsg("删除失败");
        }
        return resInfo;
    }

    /**
     * 上传图片
     */
    @RequestMapping("editimg")
    @ResponseBody
    public BaseResultInfo editImg(@RequestParam(value = "file", required = false)MultipartFile file) {
        BaseResultInfo res =  new BaseResultInfo();
        try {
            List<Map<String, Object>> fileList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("filename", file.getOriginalFilename());
            map.put("stream", file.getInputStream());
            fileList.add(map);
            res = jmGoodsGroupService.updateIconImg(fileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据分组名检测重复
     * @param name
     * @param id
     * @return
     */
    @RequestMapping("checkgoodsgroupbyname")
    @ResponseBody
    public BaseResultInfo getGoodsBrandByName(String name,Long id){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setSuccess(false);
        try {
            baseResultInfo.setSuccess(jmGoodsGroupService.getGoodsGroupByName(name,id));
            baseResultInfo.setMsg("该品牌名称已经存在");
        }catch (Exception e){
            baseResultInfo.setMsg("网络异常");
            baseResultInfo.setSuccess(false);
            log.error(e.getMessage(),e);
        }
        return baseResultInfo;
    }

    @RequestMapping("detail")
    public String getGoodsDetail(Long id, Model model, Integer type) {
        GoodsGroup goodsGroup = jmGoodsGroupService.getGroupsById(id);
        if (1 == type) {
            model.addAttribute("goodsgroup", goodsGroup);
            return "jumi/product/goodsgroup/groupdetail";
        } else {
            if (null == goodsGroup.getLogo() || " "==goodsGroup.getLogo()) {
                goodsGroup.setLogo("null");
            }
            model.addAttribute("goodsgroup", goodsGroup);
            return "jumi/product/goodsgroup/groupedit";
        }
    }

    @RequestMapping("edit")
    @ResponseBody
    public BaseResultInfo editGoodsGroup(GoodsGroup goodsGroup) {
        BaseResultInfo resInfo = new BaseResultInfo();
        resInfo.setSuccess(false);
        resInfo.setMsg("修改失败");
        goodsGroup.setLogo(goodsGroup.getLogo().replace("null", "").replace("null,",""));
        Integer res = jmGoodsGroupService.editGoodsBrand(goodsGroup);
        if (res > 0) {
            resInfo.setSuccess(true);
            resInfo.setMsg("修改成功");
        }
        return resInfo;
    }


}
