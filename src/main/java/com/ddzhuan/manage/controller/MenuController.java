package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Dictionary;
import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.model.SystemInfo;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 菜单管理
 *
 * @author likeke
 * @date 2019/07/02
 */
@Controller
@RequestMapping("/menu/")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private SystemInfoService systemInfoService;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 菜单管理页面
     */
    @RequestMapping("getmenulist")
    public String getMenuList(HttpServletRequest request, HttpServletResponse response, Model model,
                              @RequestParam(required = false, defaultValue = "1") Long systemId) {
            Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setRows(2147483647);
        List<SystemInfo> systems = systemInfoService.getSystemInfoListByConditions(new SystemInfo(), null, null, pagination);
        List<Menu> menus = menuService.getMenuListByPidV2(0L,null, null, systemId);
        List<Long> systemIds = Arrays.asList(systemId);
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfosByIds(systemIds);
        Map<Long, SystemInfo> map = new HashMap<>();
        for (SystemInfo system : systemInfos) {
            map.put(system.getId(), system);
        }
        for (Menu menu : menus) {
            SystemInfo systemInfo = map.get(menu.getSystemId());
            if (systemInfo != null) {
                menu.setSystemName(systemInfo.getName());
            }
        }
        model.addAttribute("menus",menus);
        model.addAttribute("systems",systems);
        model.addAttribute("systemId",systemId);
        return "menu/menulist";
    }

    @RequestMapping("addmenu")
    public String addMenu(HttpServletRequest request, HttpServletResponse response, Model model,
                          @RequestParam(required = false) Long systemId,
                          @RequestParam(required = true) Long pid) {
        List<SystemInfo> systems = systemInfoService.getSystemInfoListByConditions(new SystemInfo(), null, null, new Pagination(0,2147483647));
        Menu menu = menuService.getMenuById(pid);
        SystemInfo systemInfo = null;
        if (systemId != null) {
            systemInfo = systemInfoService.getSystemInfoById(systemId);
        }
        model.addAttribute("systems", systems);
        model.addAttribute("systemId", systemId);
        model.addAttribute("systemName", systemInfo == null ? "" : systemInfo.getName());
        model.addAttribute("pid", pid);
        model.addAttribute("menu", menu);
        return "menu/addmenu";
    }

    /**
     * 保存菜单
     *
     * @param menu 菜单等相关属性
     * @return json
     */
    @RequestMapping("savemenu")
    @ResponseBody
    public BaseResultInfo saveMenu(HttpServletRequest request, HttpServletResponse response, Model model,
                        Menu menu) {
        User user = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            Integer count = menuService.getMenuCountByCode(menu);
            if (count != null && count > 0) {
                result.setMsg("菜单唯一编码重复，请重新输入");
                return result;
            }
            String icon = String.format("%s%s%s", "class=\"", menu.getImg(), " fa-fw\"");
            menu.setImg(icon);
            menuService.saveMenu(menu);
            result.setSuccess(true);
            result.setMsg("保存成功");
            sysLogInfoService.addSysLogInfoForTask(1,"操作员:"+user.getUserName()+" 删除菜单数据为："+menu.toString());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("保存失败");
        }
        return result;
    }

    @RequestMapping("modifymenu")
    public String modify(HttpServletRequest request, HttpServletResponse response, Model model,
                         @RequestParam(required = true) Long id) {
        Menu menu = menuService.getMenuById(id);
        SystemInfo system = systemInfoService.getSystemInfoById(menu.getSystemId());
        Menu pMenu = menuService.getMenuById(menu.getPid());
        Dictionary dictionary = new Dictionary();
        String iconValue = menu.getImg();
        if (!StringUtils.isEmpty(iconValue)) {
            Dictionary dic = new Dictionary();
            dic.setType(1);
            dic.setValue(menu.getImg());
            dictionary = dictionaryService.getDictionaryByValue(dic);
        }
        model.addAttribute("menu", menu);
        model.addAttribute("system",system);
        model.addAttribute("pMenu", pMenu);
        model.addAttribute("dictionary", dictionary);
        return "menu/modifymenu";
    }

    /**
     * 更新
     * @param menu 菜单相关属性
     * @return json
     */
    @RequestMapping("updatemenu")
    @ResponseBody
    public BaseResultInfo updateMenu(HttpServletRequest request, HttpServletResponse response, Model model,
                                     Menu menu) {
        User user = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            if (menu.getImg().indexOf("class=") == -1) {
                menu.setImg(String.format("%s%s%s", "class=\"", menu.getImg(), " fa-fw\""));
            }
            menuService.saveMenu(menu);
            result.setSuccess(true);
            result.setMsg("修改成功");
            sysLogInfoService.addSysLogInfoForTask(1,"操作员:"+user.getUserName()+" 修改菜单数据为："+menu.toString());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("修改失败");
        }
        return result;
    }

    /**
     * 删除菜单
     *
     * @param id 菜单id
     * @return json
     */
    @RequestMapping("removemenu")
    @ResponseBody
    public BaseResultInfo removeMenu(HttpServletRequest request, HttpServletResponse response, Model model,
                                     @RequestParam(required = true) Long id, Long systemId) {
        User user = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            int count = powerService.getCountPowersByMenuId(id);
            if (count > 0) {
                result.setSuccess(true);
                result.setMsg("有权限关联菜单，不可删除");
                return result;
            }
            List<Menu> menus = menuService.getMenuListByPid(systemId, id, null);
            if (!CollectionUtils.isEmpty(menus)) {
                result.setSuccess(true);
                result.setMsg("存在下一级子菜单，不可删除");
            } else {
                menuService.deleteMenuById(id);
                result.setSuccess(true);
                result.setMsg("删除成功");
                sysLogInfoService.addSysLogInfoForTask(1,"操作员:"+user.getUserName()+" 修改菜单id为："+id);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 菜单图标列表
     */
    @RequestMapping("iconsmodal")
    public String iconsModal(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "menu/iconsmodal";
    }

    /**
     * 菜单图标列表
     */
    @RequestMapping("icons")
    public String chooseIcon(HttpServletRequest request, HttpServletResponse response, Model model,
                             Pagination pagination, @RequestParam(required = false, defaultValue = "1") Integer type) {
        pagination.setRows(120);
        List<Dictionary> dictionaryList = dictionaryService.getDictionaryListByType(type, pagination);
        model.addAttribute("dictionaryList", dictionaryList);
        return "menu/menuicons";
    }

    @RequestMapping("modifyiconsmodal")
    public String modifyIconsModal(HttpServletRequest request, HttpServletResponse response, Model model,
                                   String code) {
        model.addAttribute("code", code);
        return "menu/modifyiconsmodal";
    }

    @RequestMapping("modifyicons")
    public String modifyIcons(HttpServletRequest request, HttpServletResponse response, Model model,
                             Pagination pagination, @RequestParam(required = false, defaultValue = "1") Integer type,
                              @RequestParam(required = false) String code) {
        pagination.setRows(120);
        List<Dictionary> dictionaryList = dictionaryService.getDictionaryListByType(type, pagination);
        Dictionary dic = new Dictionary();
        dic.setType(1);
        dic.setCode(code);
        if (!StringUtils.isEmpty(code)) {
            dic = dictionaryService.getDictionaryByValue(dic);
        }
        model.addAttribute("dictionaryList", dictionaryList);
        model.addAttribute("dictionary", dic);
        return "menu/modifymenuicons";
    }

}
