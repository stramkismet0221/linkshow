package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.*;
import com.ddzhuan.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author jiang yong tao
 * @date 2019/7/2 10:36
 */
@Controller
@RequestMapping("/power/")
public class PowerController extends BaseController{

    @Autowired
    private PowerService powerService;

    @Autowired
    private SystemInfoService systemInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;


    /**
     * 权限列表
     * @param model 权限列表
     * @param power 条件
     * @param pagination 分页信息
     * @return
     */
    @RequestMapping("getpowerlist")
    public String getPowerList(Model model, Power power, Pagination pagination,
                               @RequestParam(required = false) Long systemId){

        List<Power> allPowers = powerService.getPowerList(power,pagination);
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfoListByConditions(new SystemInfo(), null, null, new Pagination(1, 2147483647));
        model.addAttribute("allPowers",allPowers);
        model.addAttribute("power",power);
        model.addAttribute("systems", systemInfos);
        return "power/powerlist";
    }

    /**
     * 跳转新增权限页面
     * @return
     */
    @RequestMapping("toaddpower")
    public String toAddPower(Model model, @RequestParam(required = true) Long systemId){
        Map<Long, String> systemList = systemInfoService.querySystemList(null);
        model.addAttribute("systemList",systemList);
        model.addAttribute("systemId", systemId);
        return "power/addpower";
    }


    /**
     * 新增权限
     * @param power 权限
     * @return
     */
    @RequestMapping("addpower")
    @ResponseBody
    public BaseResultInfo addPower(Power power){
        BaseResultInfo result = new BaseResultInfo();
        try {
            powerService.insertPower(power);
            result.setMsg("保存成功");
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }




    /**
     * 获取权限信息
     * @param id 权限
     * @return
     */
    @RequestMapping("getpowerbyid")
    public String getPowerById(Model model,Long id,Integer type){
        Power power = powerService.getPowerById(id);
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(power.getSystemId());
        model.addAttribute("power",power);
        model.addAttribute("system", systemInfo);
        if (type.equals(OperateTypeEnum.DETAIL.code)){
            return "power/powerdetail";
        }else{
            return "power/modifypower";
        }
    }


    /**
     * 更新权限
     * @param power 权限
     * @return
     */
    @RequestMapping("updatepowerbyid")
    @ResponseBody
    public BaseResultInfo updatePowerById(Power power){
        BaseResultInfo result = new BaseResultInfo();
        boolean flag = false;
        try {
            flag = powerService.updatePower(power);
            result.setMsg("保存成功");
            result.setSuccess(flag);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(flag);
        }
        return result;
    }


    /**
     * 删除权限权限
     * @param id 权限id
     * @return
     */
    @RequestMapping("delpowerbyid")
    @ResponseBody
    public BaseResultInfo delPowerById(Long id, Long systemId){
        BaseResultInfo baseResultInfo = new BaseResultInfo();
        List<Long> menuIds = menuService.getMenuIdsByPowerId(systemId, id);
        if (!CollectionUtils.isEmpty(menuIds)) {
            baseResultInfo.setSuccess(false);
            baseResultInfo.setMsg("当前权限下存在菜单，不可删除");
            return baseResultInfo;
        }
        BaseResultInfo result = powerService.delPowerById(id);
        return result;
    }



    /**
     * 用户添加权限
     * @param userId 用户ID
     * @param powerId 权限
     * @return
     */
    @RequestMapping("adduserpower")
    @ResponseBody
    public BaseResultInfo addUserPower(Long userId,Long powerId,Long systemId){
        BaseResultInfo result = new BaseResultInfo();
        boolean flag = false;
        try {
            flag = powerService.addUserPower(userId,powerId,systemId);
            result.setMsg("加入成功");
            result.setSuccess(flag);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("加入异常");
            result.setSuccess(flag);
        }
        return result;
    }


    /**
     * 删除用户权限
     * @param userId 用户ID
     * @param powerId 权限
     * @return
     */
    @RequestMapping("deluserpower")
    @ResponseBody
    public BaseResultInfo delUserPower(Long userId,Long powerId,Long systemId){
        BaseResultInfo result = new BaseResultInfo();
        boolean flag = false;
        try {
            flag = powerService.delUserPowerByUserIdAndPowerId(userId,powerId,systemId);
            result.setMsg("移除成功");
            result.setSuccess(flag);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("移除异常");
            result.setSuccess(flag);
        }
        return result;
    }


    /**
     * 角色添加权限
     * @param roleId 角色ID
     * @param powerId 权限ID
     * @return
     */
    @RequestMapping("addrolepower")
    @ResponseBody
    public BaseResultInfo addRolePower(Long roleId,Long powerId,Long systemId){
        BaseResultInfo result = new BaseResultInfo();
        boolean flag = false;
        try {
            flag = powerService.addRolePower(powerId,roleId,systemId);
            result.setMsg("加入成功");
            result.setSuccess(flag);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("加入异常");
            result.setSuccess(flag);
        }
        return result;
    }


    /**
     * 删除角色权限
     * @param roleId 用户ID
     * @param powerId 权限
     * @return
     */
    @RequestMapping("delrolepower")
    @ResponseBody
    public BaseResultInfo delRolePower(Long roleId,Long powerId){
        BaseResultInfo result = new BaseResultInfo();
        boolean flag = false;
        try {
            flag = powerService.delRolePowerByUserIdAndPowerId(roleId,powerId);
            result.setMsg("移除成功");
            result.setSuccess(flag);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("移除异常");
            result.setSuccess(flag);
        }
        return result;
    }


    /**
     * 校验权限唯一编码是否存在
     * @param code 权限唯一编码
     * @return
     */
    @RequestMapping("getpowerbycode")
    @ResponseBody
    public int getpowerbycode(String code){
        int count = powerService.countPowerByCode(code);
        return count;
    }

    /**
     * 根据系统id查询用户列表
     * @param systemId 用户ID
     * @return
     */
    @RequestMapping("getuserlistbysystemid")
    public String getUserListBySystemId(Model model, User user, Long systemId, Long powerId){
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        Power power = powerService.getPowerById(powerId);
        model.addAttribute("user",user);
        model.addAttribute("systemId",systemId);
        model.addAttribute("powerId",powerId);
        model.addAttribute("powerName", power.getName());
        model.addAttribute("systemInfo", systemInfo);
        return "power/systemuserlist";
    }

    /**
     * 已选用户
     */
    @RequestMapping("selectuser")
    public String selectUser(Model model, User user, Pagination pagination, Long systemId, Long powerId) {
        List<User> checkedUser = userService.queryUserListBySystemId(systemId,powerId,user,pagination,1);
        model.addAttribute("checkedUser",checkedUser);
        model.addAttribute("user",user);
        model.addAttribute("systemId",systemId);
        model.addAttribute("powerId",powerId);
        return "power/selectuser";
    }

    /**
     * 未选用户
     */
    @RequestMapping("unselectuser")
    public String unSelectUser(Model model, User user, Pagination pagination, Long systemId, Long powerId) {
        List<User> unCheckedUser = userService.queryUserListBySystemId(systemId,powerId,user,pagination,2);
        model.addAttribute("unCheckedUser",unCheckedUser);
        model.addAttribute("user",user);
        model.addAttribute("systemId",systemId);
        model.addAttribute("powerId",powerId);
        return "power/unselectuser";
    }

    @RequestMapping("getrolelistbysystemid")
    public String getRoleListBySystemId(Model model, Role role, Long systemId, Long powerId){
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        Power power = powerService.getPowerById(powerId);
        model.addAttribute("role",role);
        model.addAttribute("systemId",systemId);
        model.addAttribute("powerId",powerId);
        model.addAttribute("powerName", power.getName());
        model.addAttribute("systemInfo", systemInfo);
        return "power/systemrolelist";
    }

    /**
     * 已选角色
     */
    @RequestMapping("selectrole")
    public String selectRole(Model model, Role role, Pagination pagination, Long systemId, Long powerId) {
        List<Role> checkedRoles = roleService.queryRoleListBySystemId(role,pagination,systemId,powerId,1);
        model.addAttribute("role",role);
        model.addAttribute("systemId",systemId);
        model.addAttribute("powerId",powerId);
        model.addAttribute("checkedRoles",checkedRoles);
        return "power/selectrole";
    }

    /**
     * 未选角色
     */
    @RequestMapping("unselectrole")
    public String unSelectRole(Model model, Role role, Pagination pagination, Long systemId, Long powerId) {
        List<Role> unCheckedRoles = roleService.queryRoleListBySystemId(role,pagination,systemId,powerId,2);
        model.addAttribute("role",role);
        model.addAttribute("systemId",systemId);
        model.addAttribute("powerId",powerId);
        model.addAttribute("unCheckedRoles",unCheckedRoles);
        return "power/unselectrole";
    }

    /**
     * 获取菜单信息，该用户拥的菜单
     * @param model model
     * @param powerId 权限id
     * @return
     */
    @RequestMapping("getmenutreeforpower")
    public String getMenuTreeForUser(Model model,Long powerId, Long systemId){
        List<Menu> menus = menuService.getMenuListByPower(0L,powerId, systemId);
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        model.addAttribute("menus",menus);
        model.addAttribute("powerId",powerId);
        model.addAttribute("system", systemInfo);
        return "power/powermenulist";
    }

    /**
     * 权限添加菜单
     * @param powerId 权限ID
     * @param menuIds 菜单ID集合
     * @return
     */
    @RequestMapping("addpowermenus")
    @ResponseBody
    public BaseResultInfo addPowerMenus(Long powerId, Long[] menuIds){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(powerService.insertPowerMenus(powerId, menuIds));
            result.setMsg("保存成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;


    }

}
