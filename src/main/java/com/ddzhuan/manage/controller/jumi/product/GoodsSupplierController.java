package com.ddzhuan.manage.controller.jumi.product;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.product.GoodsSupplier;
import com.ddzhuan.manage.service.jumi.GoodsSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping("/jumi/product/goodssupplier/")
public class GoodsSupplierController {

    @Autowired
    private GoodsSupplierService goodsSupplierService;

    @RequestMapping("list")
    public String getGoodsSupplierList(Model model, GoodsSupplier goodsSupplier, Pagination pagination){
        List<GoodsSupplier> goodsSuppliers = goodsSupplierService.getGoodsSupplierList(goodsSupplier,pagination);
        model.addAttribute("goodssuppliers",goodsSuppliers);
        model.addAttribute("goodssupplier",goodsSupplier);
        return "jumi/product/goodssupplier/goodssupplierlist";

    }

    //新建供货商
    @RequestMapping("toadd")
    public String toAdd(){
        return "jumi/product/goodssupplier/addgoodssupplier";
    }

    //校验供货商名称
    @RequestMapping("checkgoodssuppliername")
    @ResponseBody
    public BaseResultInfo checkGoodsSupplier(String name,Long id){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        boolean res=goodsSupplierService.checkGoodsSupplier(name,id);
        baseResultInfo.setSuccess(res);
        return baseResultInfo;
    }

    //添加
    @RequestMapping("addgoodssupplier")
    @ResponseBody
    public BaseResultInfo addGoodsSupplier(GoodsSupplier goodsSupplier){
        BaseResultInfo resultInfo = new BaseResultInfo();
        Integer res = goodsSupplierService.addGoodsSupplier(goodsSupplier);
        if(res>0){
            resultInfo.setSuccess(true);
            resultInfo.setMsg("保存成功");
            return resultInfo;
        }
        resultInfo.setSuccess(false);
        resultInfo.setMsg("保存失败");
        return resultInfo;
    }

    @RequestMapping("info")
    public String toGoodsSupplierInfo(Long id,Model model,Integer type){
        GoodsSupplier goodsSupplier = goodsSupplierService.getGoodsSupplierById(id);
        DecimalFormat df = new DecimalFormat("0.00");
        if(goodsSupplier.getDeliveryMoney() != null) {
        	 String deliveryMoney=df.format(goodsSupplier.getDeliveryMoney());
        	 model.addAttribute("deliveryMoney",deliveryMoney);
        }
        if(goodsSupplier.getReturnPoint() != null) {
       	 String returnPoint=df.format(goodsSupplier.getReturnPoint());
       	 model.addAttribute("returnPoint",returnPoint);
       }
        if(goodsSupplier.getDeliveryPoint() != null) {
       	 String deliveryPoint=df.format(goodsSupplier.getDeliveryPoint());
       	 model.addAttribute("deliveryPoint",deliveryPoint);
       }
        if(goodsSupplier.getTaxPoint() != null) {
       	 String taxPoint=df.format(goodsSupplier.getTaxPoint());
       	 model.addAttribute("taxPoint",taxPoint);
       }
       
        model.addAttribute("goodssupplier",goodsSupplier);
        if(type==1){
            return "jumi/product/goodssupplier/goodssupplierinfo";
        }
        return "jumi/product/goodssupplier/modifygoodssupplier";
    }

    @RequestMapping("deletegoodssupplierbyid")
    @ResponseBody
    public BaseResultInfo deleteGoodsSupplier(Long id){
        BaseResultInfo resultInfo = new BaseResultInfo();
        boolean res = goodsSupplierService.deleteGoodsSupplier(id);
        resultInfo.setSuccess(res);
        return resultInfo;
    }

    @RequestMapping("editgoodssupplierbyid")
    @ResponseBody
    public BaseResultInfo editGoodsSupplier(GoodsSupplier goodsSupplier){
        BaseResultInfo resultInfo = new BaseResultInfo();
        boolean res = goodsSupplierService.editGoodsSupplierById(goodsSupplier);
        resultInfo.setSuccess(res);
        return resultInfo;
    }
}
