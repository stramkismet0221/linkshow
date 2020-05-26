package com.ddzhuan.manage.controller.jumi.shop;

import com.ddzhuan.manage.common.Pager;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.jumi.shop.Shop;
import com.ddzhuan.manage.service.jumi.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/jumi/shop/shop/")
public class ShopController extends BaseController {
    @Autowired
    private ShopService shopService;

    @RequestMapping("toshoplist")
    public String toShopList(Shop shop, Pagination pagination, Model model){
        List<Shop> shops = shopService.getShopList(shop, pagination);
        model.addAttribute("shops",shops);
        model.addAttribute("shopname",shop.getShopName());
        return "jumi/shop/shop/shoplist";
    }



}
