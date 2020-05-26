package com.ddzhuan.manage.controller.jumi.advertisement;

import com.ddzhuan.manage.common.enums.DictionaryEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.controller.BaseController;
import com.ddzhuan.manage.model.Dictionary;
import com.ddzhuan.manage.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jumi/advertisement/broadcast/")
public class BroadcastSetController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("tobroadcast")
    public String toBroadcastSet(Model model){
        Dictionary dict = new Dictionary();
        dict.setType(DictionaryEnum.BROADCAST_DICTIONARY_TYPE.getType());
        Dictionary dictionary = dictionaryService.getDictionaryByValue(dict);
        model.addAttribute("dict",dictionary);
        return "jumi/advertisement/broadcast/broadcastetting";
    }

    @RequestMapping("editbroadcast")
    @ResponseBody
    public BaseResultInfo editBroadcastSet(Dictionary dictionary){
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            dictionaryService.modifyDictionary(dictionary);
            resultInfo.setSuccess( true);
        } catch (Exception e) {
            log.error(e);
            resultInfo.setSuccess(false);
        }
        return resultInfo;
    }





}
