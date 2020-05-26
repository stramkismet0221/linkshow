package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.CommonStatusEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Power;
import com.ddzhuan.manage.model.SystemInfo;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统管理
 *
 * @author likeke
 * @date 2019/06/24
 */
@Controller
@RequestMapping("/system/")
public class SystemManageController extends BaseController {

    @Autowired
    private SystemInfoService systemInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;
    /**
     * 系统管理列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pagination 分页
     * @param systemInfo 系统信息查询参数
     * @return 页面
     */
    @RequestMapping("getsysteminfolist")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model,
                       @RequestParam(required = false) String startTime,
                       @RequestParam(required = false) String endTime,
                       Pagination pagination, SystemInfo systemInfo) {
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfoListByConditions(systemInfo, startTime, endTime, pagination);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("systemInfos", systemInfos);
        return "system/systeminfolist";
    }

    /**
     * 新增
     * @return 新增页面
     */
    @RequestMapping("addsysteminfo")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "system/addsysteminfo";
    }

    /**
     * 保存
     * @param systemInfo systemInfo
     * @return message
     */
    @RequestMapping("savesysteminfo")
    @ResponseBody
    public BaseResultInfo save(HttpServletRequest request, SystemInfo systemInfo) {
        User user = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            String name = systemInfo.getName();
            Integer count = systemInfoService.getSystemInfoCountByName(name);
            if (count != null && count > 0) {
                result.setMsg("系统名称已存在，请更换");
                return result;
            }
            systemInfoService.saveSystemInfo(systemInfo);
            result.setSuccess(true);
            result.setMsg("保存成功");
            sysLogInfoService.addSysLogInfoForTask(1,"操作员:"+user.getUserName()+" 添加系统数据为："+systemInfo.toString());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("保存异常");
        }
        return result;
    }

    /**
     * 编辑
     * @param id 系统id
     * @return 编辑页面
     */
    @RequestMapping("modifysysteminfo")
    public String modify(HttpServletRequest request, HttpServletResponse response, Model model,
                       @RequestParam(required = true) Long id) {
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(id);
        model.addAttribute("data", systemInfo);
        return "system/modifysysteminfo";
    }

    /**
     * 修改
     * @param systemInfo systemInfo
     * @return message
     */
    @RequestMapping("updatesysteminfo")
    @ResponseBody
    public BaseResultInfo update(HttpServletRequest request, SystemInfo systemInfo) {
        User user = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            SystemInfo s = systemInfoService.getSystemInfoById(systemInfo.getId());
            if (!s.getName().equals(systemInfo.getName())) {
                Integer count = systemInfoService.getSystemInfoCountByName(systemInfo.getName());
                if (count != null && count > 0) {
                    if (count != null && count > 0) {
                        result.setMsg("系统名称已存在，请更换");
                        return result;
                    }
                }
            }
            systemInfoService.modifySystemInfo(systemInfo);
            result.setSuccess(true);
            result.setMsg("修改成功");
            sysLogInfoService.addSysLogInfoForTask(1,"操作员:"+user.getUserName()+" 修改系统数据为："+systemInfo.toString());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("修改异常");
        }
        return result;
    }

    /**
     * 删除
     * @param id 系统id
     * @return message
     */
    @RequestMapping("removesysteminfo")
    @ResponseBody
    public BaseResultInfo remove(HttpServletRequest request, HttpServletResponse response, Model model,
                                 @RequestParam(required = true) Long id) {
        User user = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            List<Long> userIds = userService.getUserCountBySystemId(id);
            if (!CollectionUtils.isEmpty(userIds)) {
                result.setMsg("当前系统下存在用户，不可删除");
                return result;
            }
            List<Long> roleIds = roleService.getRoleIdsBySystemId(id);
            if (!CollectionUtils.isEmpty(roleIds)) {
                result.setMsg("当前系统下存在角色，不可删除");
                return result;
            }
            List<Power> powers = powerService.getPowersBySystemId(id);
            if (!CollectionUtils.isEmpty(powers)) {
                result.setMsg("当前系统下存在权限，不可删除");
                return result;
            }
            List<Long> menuIds = menuService.getMenuIdsBySystemId(id);
            if (!CollectionUtils.isEmpty(menuIds)) {
                result.setMsg("当前系统下存在菜单，不可删除");
                return result;
            }
            SystemInfo systemInfo = new SystemInfo();
            systemInfo.setId(id);
            systemInfo.setStatus(CommonStatusEnum.DELETED.getCode());
            systemInfoService.modifySystemInfo(systemInfo);
            result.setSuccess(true);
            result.setMsg("删除成功");
            sysLogInfoService.addSysLogInfoForTask(1,"操作员:"+user.getUserName()+" 删除系统数据为："+systemInfo.toString());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("删除异常");
        }
        return result;
    }

    /**
     * 详情
     * @param id 系统id
     * @return 详情页面
     */
    @RequestMapping("systeminfodetail")
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
                                      @RequestParam(required = true) Long id) {
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(id);
        model.addAttribute("data", systemInfo);
        return "system/systeminfodetail";
    }

    @RequestMapping("unselectuser")
    public String unSelectUser(HttpServletRequest request, HttpServletResponse response, Model model,
                               @RequestParam(required = true) Long systemId,
                               @RequestParam(required = false) String userName,
                               @RequestParam(required = false) String realName,
                               Pagination pagination) {
        List<User> unSelectUsers = userService.getUserListBySystem(userName, realName, systemId, 2, pagination);
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        model.addAttribute("systemId", systemId);
        model.addAttribute("userName", userName);
        model.addAttribute("realName", realName);
        model.addAttribute("systemName", systemInfo == null ? "" : systemInfo.getName());
        model.addAttribute("unSelectUsers", unSelectUsers);
        return "system/unselectuser";
    }

    @RequestMapping("selectuser")
    public String selectUser(HttpServletRequest request, HttpServletResponse response, Model model,
                               @RequestParam(required = true) Long systemId,
                               @RequestParam(required = false) String userName,
                               @RequestParam(required = false) String realName,
                               Pagination pagination) {
        List<User> selectUsers = userService.getUserListBySystem(userName, realName, systemId, 1, pagination);
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        model.addAttribute("systemId", systemId);
        model.addAttribute("userName", userName);
        model.addAttribute("realName", realName);
        model.addAttribute("systemName", systemInfo == null ? "" : systemInfo.getName());
        model.addAttribute("selectUsers", selectUsers);
        return "system/selectuser";
    }

    /**
     * 系统加入用户
     * @param systemId 系统id
     * @param userName 用户名 用于模糊查询
     * @return 选择用户页面
     */
    @RequestMapping("addsystemuser")
    public String addUser(HttpServletRequest request, HttpServletResponse response, Model model,
                          @RequestParam(required = true) Long systemId,
                          @RequestParam(required = false) String userName,
                          @RequestParam(required = false) String realName) {
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        model.addAttribute("systemId", systemId);
        model.addAttribute("userName", userName);
        model.addAttribute("realName", realName);
        model.addAttribute("systemName", systemInfo == null ? "" : systemInfo.getName());
        return "system/adduser";
    }

    /**
     * 添加系统用户
     * @param userId 用户id
     * @param systemId 系统id
     * @return json
     */
    @RequestMapping("savesystemuser")
    @ResponseBody
    public BaseResultInfo saveSystemUser(HttpServletRequest request, HttpServletResponse response, Model model,
                                         @RequestParam(required = true) Long userId,
                                         @RequestParam(required = true) Long systemId) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            systemInfoService.saveSystemUser(userId, systemId);
            result.setSuccess(true);
            result.setMsg("添加成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("添加失败");
        }
        return result;
    }

    @RequestMapping("removesystemuser")
    @ResponseBody
    public BaseResultInfo removeSystemUser(HttpServletRequest request, HttpServletResponse response, Model model,
                                         @RequestParam(required = true) Long userId,
                                         @RequestParam(required = true) Long systemId) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            List<Long> roleIds = roleService.getRoleIdsByUserId(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                result.setSuccess(true);
                result.setMsg("当前用户加入了角色，不可删除");
                return result;
            }
            List<Long> powerIds = powerService.getPowerIdsByUserId(userId, systemId);
            if (!CollectionUtils.isEmpty(powerIds)) {
                result.setSuccess(true);
                result.setMsg("当前用户加入了权限，不可删除");
                return result;
            }
            systemInfoService.removeSystemUser(userId, systemId);
            result.setSuccess(true);
            result.setMsg("移除成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("移除失败");
        }
        return result;
    }

    public static void main(String[] args) {//

    }

}
