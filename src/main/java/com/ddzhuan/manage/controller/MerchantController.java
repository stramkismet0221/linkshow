package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.CommonStatusEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Merchant;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.MerchantService;
import com.ddzhuan.manage.service.SysLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/merchant/")
public class MerchantController extends BaseController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    /**
     * 获取商户列表
     */
    @RequestMapping("getmerchantlist")
    public String getmerchantlist(Model model, Merchant merchant, Pagination pagination){

        List<Merchant> merchantList = merchantService.queryMerchantByMerchant(merchant,pagination);
        model.addAttribute("merchantList",merchantList);
        model.addAttribute("merchant",merchant);
        return "merchant/merchantlist";
    }

    //锁定或者解锁用户
    @RequestMapping( value = "updatelock",method = RequestMethod.POST)
    @ResponseBody
    public BaseResultInfo lock(Merchant merchant){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        try {
            baseResultInfo.setSuccess(merchantService.updateMerchant(merchant));
            baseResultInfo.setMsg("修改成功");
        }catch (Exception  ex){
            baseResultInfo.setSuccess(false);
            baseResultInfo.setMsg("修改失败");
            log.error(ex.getMessage(), ex);
        }
        return baseResultInfo;
    }
    //修改商户
    @RequestMapping(value = "editmerchant",method = RequestMethod.POST)
    @ResponseBody
    public BaseResultInfo editMerchant(Merchant merchant,HttpServletRequest request){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setSuccess(false);
        User user  = (User) request.getSession().getAttribute("sysuser");
        if(user != null){
            try {
                if (merchant.getLicenseImg().indexOf("null,") > -1) {
                    merchant.setLicenseImg(merchant.getLicenseImg().replace("null,", ""));
                }
                baseResultInfo.setSuccess(merchantService.updateMerchant(merchant));
                sysLogInfoService.addSysLogInfoForTask(1,"操作员："+user.getUserName()+" 修改商户数据为："+merchant.toString());
                baseResultInfo.setMsg("修改成功");
            }catch (Exception  ex){
                baseResultInfo.setSuccess(false);
                baseResultInfo.setMsg("修改失败");
                log.error(ex.getMessage(), ex);
            }
        }

        return baseResultInfo;
    }
    @RequestMapping("deletemerchantbyid")
    @ResponseBody
    public BaseResultInfo deleteMerchantbyid(Merchant merchant,HttpServletRequest request){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        merchant.setStatus(CommonStatusEnum.DELETED.getCode());
        User user  = (User) request.getSession().getAttribute("sysuser");
        if(user != null){
            try {
                baseResultInfo.setSuccess(merchantService.updateMerchant(merchant));
                sysLogInfoService.addSysLogInfoForTask(1,"操作员："+user.getUserName()+"删除商户数据为："+merchant.getCompany());
                baseResultInfo.setMsg("删除成功");
            }catch (Exception ex){
                baseResultInfo.setSuccess(false);
                baseResultInfo.setMsg("删除失败");
                log.error(ex.getMessage(), ex);
            }
        }

        return baseResultInfo;
    }



    @RequestMapping("getmerchantinfobyid")
    public String getMerchantInfo(Model model, Merchant merchant,Integer type){
        Merchant merchant1 = merchantService.getMerchantInfo(merchant);
        if(1==type){
            model.addAttribute("merchant",merchant1);
            return "merchant/merchantdetail";
        }else{
            if(null== merchant1.getLicenseImg()||  ""==merchant1.getLicenseImg()){
                merchant1.setLicenseImg("null");
            }
            model.addAttribute("merchant",merchant1);
            return "merchant/editmerchant";
        }
    }

    @RequestMapping("addmerchant")
    public String addMerchant(){
        return "merchant/addmerchant";
    }


    @RequestMapping("updateliceimg")
    @ResponseBody
    public BaseResultInfo updateLiceimg(@RequestParam(value = "file", required = false)MultipartFile file){
        BaseResultInfo baseResultInfo=new BaseResultInfo();

        try {
            List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("filename", file.getOriginalFilename());
            map.put("stream", file.getInputStream());
            fileList.add(map);
            baseResultInfo = merchantService.updateLicenImg(fileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseResultInfo;
    }

    @RequestMapping("savemerchant")
    @ResponseBody
    public BaseResultInfo saveMerchant(Merchant merchant, HttpServletRequest request){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setSuccess(false);
        User user  = (User) request.getSession().getAttribute("sysuser");
        if(user != null){
            try {
                baseResultInfo.setSuccess(merchantService.insertMerchant(merchant));
                sysLogInfoService.addSysLogInfoForTask(1,"操作员:"+user.getUserName()+" 添加商户数据为："+merchant.toString());
                baseResultInfo.setMsg("保存成功");
            }catch (Exception ex){
                baseResultInfo.setSuccess(false);
                baseResultInfo.setMsg("保存失败");
                log.error(ex.getMessage(), ex);
            }
        }
        return baseResultInfo;
    }


    @RequestMapping("/checkmerchantbycompany")
    @ResponseBody
    public BaseResultInfo getMerchantByCompany(String company,HttpServletRequest request,Long id){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        baseResultInfo.setSuccess(false);
        User user  = (User) request.getSession().getAttribute("sysuser");
        if(user != null){
            try {
                baseResultInfo.setSuccess(merchantService.MerchantByCompany(company,id));
                baseResultInfo.setMsg("该商户名称已经存在");
            }catch (Exception ex){
                baseResultInfo.setSuccess(false);
                baseResultInfo.setMsg("网络异常");
                log.error(ex.getMessage(), ex);
            }
        }
        return baseResultInfo;
    }

    @RequestMapping("deleteimg")
    @ResponseBody
    public BaseResultInfo deleteImg(String url){
        return merchantService.deleteLiceImg(url);
    }


}
