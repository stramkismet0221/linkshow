package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.SysParam;
import com.ddzhuan.manage.service.ToolService;
import com.ddzhuan.manage.tool.CacheTool;
import com.ddzhuan.manage.tool.SysLogInfoTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BaseController
 */
public class BaseController implements Serializable {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    protected SysParam _p;

    @Autowired
    protected CacheTool cache;

    @Autowired
    protected SysLogInfoTool sysLog;

    @Autowired
    protected ToolService toolService;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1160362071875148381L;

    /**
     * 日期格式绑定
     *
     * @param binder
     */
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

}
