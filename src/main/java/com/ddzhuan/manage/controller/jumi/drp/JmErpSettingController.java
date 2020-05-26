package com.ddzhuan.manage.controller.jumi.drp;


import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.jumi.drp.JmErpSetting;
import com.ddzhuan.manage.service.jumi.JmErpSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/jumi/drp/erpsetting")
public class JmErpSettingController {

    @Autowired
    private JmErpSettingService erpSetingService;

    @RequestMapping("toerpsetting")
    public String toErpSettingPages(Model model) {
        List<JmErpSetting> config = erpSetingService.getErpConfigList();
        model.addAttribute("erpset", (JmErpSetting)config.get(0));
        return "/jumi/drp/jmerpsetting/jmerpsetting";
    }

    @RequestMapping("updatesetting")
    @ResponseBody
    public BaseResultInfo updateSetting(JmErpSetting setting) {
        BaseResultInfo resultInfo = new BaseResultInfo();
        Integer res = erpSetingService.updateErpConfig(setting);
        if (res > 0) {
            resultInfo.setMsg("更新成功");
            resultInfo.setSuccess(true);
        } else {
            resultInfo.setSuccess(false);
            resultInfo.setMsg("更新失败");
        }
        return resultInfo;
    }



}
