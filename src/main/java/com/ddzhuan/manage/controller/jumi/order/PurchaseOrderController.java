package com.ddzhuan.manage.controller.jumi.order;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.enums.PurchaseEnums;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.model.jumi.order.PurchaseOrder;
import com.ddzhuan.manage.model.jumi.product.GoodsSupplier;
import com.ddzhuan.manage.service.jumi.GoodsSupplierService;
import com.ddzhuan.manage.service.jumi.PurchaseOrderService;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购货/入库/退货
 * @author jiang yong tao
 * @date 2020/2/3 17:49
 */
@Controller
@RequestMapping("/jumi/order/purchaseorder/")
public class PurchaseOrderController extends BaseController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private GoodsSupplierService goodsSupplierService;

    /**
     * 分页查询购货订单列表
     * @param model model
     * @param purchaseOrder 条件
     * @param pagination 列表
     * @param type 1购货订单(订货),2购货入库(购货),3退货
     * @return
     */
    @RequestMapping("getlist")
    public String getList(Model model, PurchaseOrder purchaseOrder, Pagination pagination,@RequestParam(name = "type",defaultValue = "1") Integer type){
        purchaseOrder.setType(type);
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.pagePurchaseList(purchaseOrder,pagination);
        model.addAttribute("type",type);
        model.addAttribute("purchaseOrder",purchaseOrder);
        model.addAttribute("purchaseOrders",purchaseOrders);
        return "jumi/order/purchaseorder/purchaseorderlist";
    }

    /**
     * 查询购货订单详情
     * @param model model
     * @param id id
     * @param type 操作类型
     * @param ptype 2 生成入库单据, 3 生成购货退货单
     * @return 详情/编辑 页面
     */
    @RequestMapping("purchaseorderinfo")
    public String getPurchaseOrderInfo(Model model, Long id, Integer type, Integer ptype){
        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        List<GoodsSupplier> goodsSuppliers = goodsSupplierService.getGoodsSupplierList(new GoodsSupplier(),new Pagination(1,Integer.MAX_VALUE));
        model.addAttribute("goodsSuppliers",goodsSuppliers);
        model.addAttribute("purchaseOrder",purchaseOrder);
        if (OperateTypeEnum.DETAIL.code.equals(type)) {
            return "jumi/order/purchaseorder/purchaseorderdetail";
        }else{
            model.addAttribute("ptype",ptype);
            return "jumi/order/purchaseorder/modifypurchaseorder";
        }
    }


    /**
     * 跳转新增页面
     * @param model model
     * @param type 1购货订单(订货),2购货入库(购货),3退货
     * @return
     */
    @RequestMapping("toaddpurchaseorder")
    public String toAddPurchaseOrder(Model model, Integer type){
        List<GoodsSupplier> goodsSuppliers = goodsSupplierService.getGoodsSupplierList(new GoodsSupplier(),new Pagination(1,Integer.MAX_VALUE));
        model.addAttribute("goodsSuppliers",goodsSuppliers);
        model.addAttribute("type",type);
        return "jumi/order/purchaseorder/addpurchaseorder";
    }




    /**
     * 新增购货订单
     * @param purchaseOrder 订单
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("addpurchaseorder")
    @ResponseBody
    public BaseResultInfo addPurchaseOrder(HttpServletRequest request, PurchaseOrder purchaseOrder){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            User user = (User)request.getSession().getAttribute("sysuser");
            purchaseOrder.setCreator(user.getUserName());
            boolean flag = purchaseOrderService.addPurchaseOrder(purchaseOrder);
            resultInfo.setSuccess(flag);
            resultInfo.setMsg("保存成功");
        }catch (Exception e){
            log.info(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("保存失败");
        }
        return resultInfo;
    }

    /**
     * 更新购货订单
     * @param purchaseOrder 订单
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("modifypurchaseorder")
    @ResponseBody
    public BaseResultInfo updatePurchaseOrder(PurchaseOrder purchaseOrder, Integer ptype){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            boolean flag = purchaseOrderService.updatePurchaseOrderById(purchaseOrder,ptype);
            resultInfo.setSuccess(flag);
            resultInfo.setMsg("修改成功");
        }catch (Exception e){
            log.info(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("修改失败");
        }
        return resultInfo;
    }


    /**
     * 删除购货订单
     * @param id id
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("delpurchaseorder")
    @ResponseBody
    public BaseResultInfo delPurchaseOrder(Long id){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
            if (PurchaseEnums.CHECK.code.equals(purchaseOrder.getCheckStatus())) {
                resultInfo.setSuccess(false);
                resultInfo.setMsg("已审核的单据不能删除");
                return resultInfo;
            }
            boolean flag = purchaseOrderService.delPurchaseOrderById(id);
            resultInfo.setSuccess(flag);
            resultInfo.setMsg("删除成功");
        }catch (Exception e){
            log.info(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("删除失败");
        }
        return resultInfo;
    }



    /**
     * 更新购货订单
     * @param purchaseOrder 订单
     * @return @see BaseResultInfo.class {@link BaseResultInfo}
     */
    @RequestMapping("updatestatus")
    @ResponseBody
    public BaseResultInfo updateStatus(PurchaseOrder purchaseOrder, Integer ptype){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            PurchaseOrder purchaseOrder1 = purchaseOrderService.getPurchaseOrderById(purchaseOrder.getId());
            if (PurchaseEnums.CHECK.code.equals(purchaseOrder.getType())) {
                purchaseOrder.setCheckStatus(PurchaseEnums.CHECK.code);
                if (PurchaseEnums.CHECK.code.equals(purchaseOrder1.getCheckStatus())) {
                    resultInfo.setSuccess(false);
                    resultInfo.setMsg("已经审核的单据不能重复审核");
                    return resultInfo;
                }
            }
            if (PurchaseEnums.UNCHECK.code.equals(purchaseOrder.getType())) {
                purchaseOrder.setCheckStatus(PurchaseEnums.DEAFSTATUS.code);
                if (PurchaseEnums.DEAFSTATUS.code.equals(purchaseOrder1.getCheckStatus())) {
                    resultInfo.setSuccess(false);
                    resultInfo.setMsg("未审核的单据不能反审核");
                    return resultInfo;
                }

            }
            boolean flag = purchaseOrderService.updatePurchaseOrderById(purchaseOrder,null);
            resultInfo.setSuccess(flag);
            resultInfo.setMsg("审核成功");
        }catch (Exception e){
            log.info(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("审核失败");
        }
        return resultInfo;
    }
}
