package com.ddzhuan.manage.controller.jumi.product;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.jumi.product.GoodsBrand;
import com.ddzhuan.manage.service.jumi.GoodsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/jumi/product/goodsbrands/")
public class GoodsBrandController extends BaseController {

	private static final long serialVersionUID = 3338892568164464538L;
	@Autowired
    private GoodsBrandService goodsBrandService;

    /**
     * 获取商品品牌列表
     * @param model
     * @param name
     * @param pagination
     * @return
     */
    @RequestMapping("getlist")
    public String getGoodsBrand(Model model, String name, Pagination pagination){
        List<GoodsBrand> goodsBrands = goodsBrandService.queryGoodsBrandByName(name,pagination);
        model.addAttribute("goodsbrands",goodsBrands);
        model.addAttribute("names",name);
        return "jumi/product/goodsbrand/goodsbrandlist";
    }

    @RequestMapping("toadd")
    public String toAddGoodsBrand(){
        return "jumi/product/goodsbrand/addgoodsbrand";
    }

    @RequestMapping("add")
    @ResponseBody
    public BaseResultInfo addGoodsBrand(GoodsBrand goodsBrand){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        Integer res = goodsBrandService.addGoodsBrand(goodsBrand);
        baseResultInfo.setSuccess(false);
        baseResultInfo.setMsg("添加失败");
        if(res>0){
            baseResultInfo.setSuccess(true);
            baseResultInfo.setMsg("添加成功");
        }
        return  baseResultInfo;
    }

    @RequestMapping("delete")
    @ResponseBody
    public BaseResultInfo deleteGoodsBrandById(Long id){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        Integer res = goodsBrandService.deleteGoodsBrandById(id);
        baseResultInfo.setSuccess(false);
        baseResultInfo.setMsg("删除失败");
        if(res>0){
            baseResultInfo.setSuccess(true);
            baseResultInfo.setMsg("删除成功");
        }
        return baseResultInfo;
    }

    @RequestMapping("checkgoodsbrandbyname")
    @ResponseBody
    public BaseResultInfo getGoodsBrandByName(String name,Long id){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setSuccess(false);
        try {
            baseResultInfo.setSuccess(goodsBrandService.getGoodsBrandByName(name,id));
            baseResultInfo.setMsg("该品牌名称已经存在");
        }catch (Exception e){
            baseResultInfo.setMsg("网络异常");
            baseResultInfo.setSuccess(false);
            log.error(e.getMessage(),e);
        }
        return baseResultInfo;
    }
    /**
     * 上传图片
     * @param file
     * @return
     */
    @RequestMapping("editimg")
    @ResponseBody
    public BaseResultInfo editImg(@RequestParam(value = "file", required = false) MultipartFile file){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        try{
            List<Map<String,Object>>  fileList = new ArrayList<Map<String,Object>>();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("filename",file.getOriginalFilename());
            map.put("stream",file.getInputStream());
            fileList.add(map);
            baseResultInfo = goodsBrandService.updateLicenImg(fileList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return  baseResultInfo;
    }

    @RequestMapping("deleteimg")
    @ResponseBody
    public BaseResultInfo deleteImg(String url){
        return goodsBrandService.deleteLiceImg(url);
    }
    //详情页面
    @RequestMapping("detail")
    public String getGoodsBrandDetail(Long id,Model model,Integer type){
        GoodsBrand goodsBrand = goodsBrandService.getGoodsBrandById(id);
        if(1==type){
            model.addAttribute("goodsbrand",goodsBrand);
            return "jumi/product/goodsbrand/goodsbranddetail";
        }else{
            if(null== goodsBrand.getLogo()||  ""==goodsBrand.getLogo()){
                goodsBrand.setLogo("null");
            }
            model.addAttribute("goodsbrand",goodsBrand);
            return "jumi/product/goodsbrand/editgoodsbrand";
        }
    }

    @RequestMapping("edit")
    @ResponseBody
    public BaseResultInfo editGoodsBrand(GoodsBrand goodsBrand){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setSuccess(false);
        baseResultInfo.setMsg("修改失败");
        goodsBrand.setLogo(goodsBrand.getLogo().replace("null,", "").replace("null", ""));
        Integer res = goodsBrandService.editGoodsBrandById(goodsBrand);
        if(res>0){
            baseResultInfo.setSuccess(true);
            baseResultInfo.setMsg("修改成功");
        }
        return  baseResultInfo;
    }
}
