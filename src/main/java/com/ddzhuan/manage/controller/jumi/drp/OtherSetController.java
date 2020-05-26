package com.ddzhuan.manage.controller.jumi.drp;

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
@RequestMapping("/jumi/drp/otherset/")
public class OtherSetController extends BaseController{

    @Autowired
    private DictionaryService dictionaryService;

   

    //编辑数量小数位数
    @RequestMapping("toeditnumdecimals")
    public String toEditNumDecimals(Model model){
        Dictionary dict = new Dictionary();
        dict.setType(3);
        Dictionary dictionary = dictionaryService.getDictionaryByValue(dict);
        model.addAttribute("dict",dictionary);
        return "jumi/drp/numdecimals/modifynumdecimals";
    }
    //编辑单价小数位数
    @RequestMapping("toeditpricedecimals")
    public String toEditPriceDecimals(Model model){
        Dictionary dict = new Dictionary();
        dict.setType(4);
        Dictionary dictionary = dictionaryService.getDictionaryByValue(dict);
        model.addAttribute("dict",dictionary);
        return "jumi/drp/pricedecimals/modifypricedecimals";
    }




    @RequestMapping("number")
    @ResponseBody
    public BaseResultInfo editNumDecimals(Dictionary dictionary) {
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
    @RequestMapping("unit")
    @ResponseBody
    public BaseResultInfo editUnitDecimals(Dictionary dictionary) {
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
