package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.enums.SysLogInfoEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Role;
import com.ddzhuan.manage.model.SystemInfo;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 17:45
 */
@Controller
@RequestMapping("/role/")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private SystemInfoService systemInfoService;

    /**
     * 角色列表
     * @param model     model 角色列表
     * @param role      role 条件
     * @param pagination 分页信息
     * @return
     */
    @RequestMapping("getrolelist")
    public String getrolelist(Model model, Role role, Pagination pagination,
                              @RequestParam(required = false) Long systemId){
        List<Role> roleList = roleService.queryRoleListByConditions(role,pagination);
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfoListByConditions(new SystemInfo(), null, null, new Pagination(1,2147483647));
        model.addAttribute("roleList",roleList);
        model.addAttribute("role",role);
        model.addAttribute("systems", systemInfos);
        model.addAttribute("systemId", systemId);
        return "role/rolelist";
    }

    /**
     * 角色信息
     * @param model     角色信息
     * @param roleId    角色id
     * @return
     */
    @RequestMapping("getroleinfo")
    public String getroleinfo(Model model, Long roleId,Long systemId, Integer type){
        Role role = roleService.queryRoleById(roleId,systemId);
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        model.addAttribute("role",role);
        model.addAttribute("system", systemInfo);
        if (type.equals(OperateTypeEnum.DETAIL.code)){
            return "role/roledetail";
        }else {
            return "role/modifyrole";
        }
    }

    /**
     * 跳转新增角色页面
     * @return
     */
    @RequestMapping("toaddrole")
    public String toAddRole(HttpServletRequest request, HttpServletResponse response,
                            Model model, @RequestParam(required = true) Long systemId){
        List<SystemInfo> systemInfos = systemInfoService.getSystemInfoListByConditions(new SystemInfo(), null, null, new Pagination(1, 2147483647));
        model.addAttribute("systems", systemInfos);
        model.addAttribute("systemId", systemId);
        return "role/addrole";
    }

    /**
     * 新增角色
     * @param role  角色
     * @return
     */
    @RequestMapping("insertrole")
    @ResponseBody
    public BaseResultInfo insertrole(HttpServletRequest request,Role role){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(roleService.inserRole(role));
            User user = (User) request.getSession().getAttribute("sysuser");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+" 新增角色数据为："+ role.toString());
            result.setMsg("保存成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 更新角色信息
     * @param role  角色信息
     * @return
     */
    @RequestMapping("updaterole")
    @ResponseBody
    public BaseResultInfo updateRole(HttpServletRequest request,Role role){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(roleService.updateRole(role));
            User user = (User) request.getSession().getAttribute("sysuser");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+" 修改角色数据为："+role.toString());
            result.setMsg("修改成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("修改异常");
            result.setSuccess(false);
        }
        return result;
    }


    /**
     *
     * 角色赋权
     * @param roleId    角色id
     * @param menuIds  菜单组
     * @return 是否成功赋权
     */
    @RequestMapping("addrolemenus")
    @ResponseBody
    public BaseResultInfo addRoleMenus(HttpServletRequest request,Long roleId, Long[] menuIds){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(roleService.addRoleMenus(roleId,Arrays.asList(menuIds)));
            User user = (User) request.getSession().getAttribute("sysuser");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+"修改角色"+roleId+"赋权菜单为："+ Arrays.toString(menuIds));
            result.setMsg("赋权成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("赋权异常");
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 角色加入用户
     * @param roleId    角色ID
     * @param userId   用户ID
     * @return
     */
    @RequestMapping("adduserrole")
    @ResponseBody
    public BaseResultInfo addUserRole(HttpServletRequest request,Long roleId, Long userId){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(roleService.addUserRole(roleId,userId));
            User user = (User) request.getSession().getAttribute("sysuser");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+"修改角色"+ roleId +"加入用户为：" + userId);
            result.setMsg("加入成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("加入异常");
            result.setSuccess(false);
            return result;
        }
        return result;
    }


    /**
     * 移除用户
     * @param roleId    角色ID
     * @param userId   用户ID
     * @return
     */
    @RequestMapping("deluserrole")
    @ResponseBody
    public BaseResultInfo delUserRole(HttpServletRequest request,Long roleId, Long userId){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(roleService.delUserRole(roleId,userId));
            User user = (User) request.getSession().getAttribute("sysuser");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+"修改角色"+ roleId +"删除用户为：" + userId);
            result.setMsg("移除成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("移除异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 根据角色编码查询角色是否存在
     * @param code 角色code
     * @return
     */
    @RequestMapping("getrolebyrolecode")
    @ResponseBody
    public BaseResultInfo getRoleByRoleCode(String code){
        BaseResultInfo base = new BaseResultInfo();
        try {
            base.setSuccess(roleService.getRoleByRoleCode(code));
            base.setMsg("保存成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            base.setSuccess(false);
            base.setMsg("保存异常");
        }
        return base;
    }

    /**
     * 删除角色
     * @param roleId 角色code
     * @return
     */
    @RequestMapping("delrolebyid")
    @ResponseBody
    public BaseResultInfo delRoleById(Long roleId){
        return roleService.delRoleById(roleId);
    }


    /**
     * 根据roleid获取用户列表
     * @param model 用户列表
     * @param roleId 角色id
     * @param user 条件
     * @return
     */
    @RequestMapping("getuserlistbyroleid")
    public String getUserListByRoleId(Model model,Long roleId,User user, Long systemId){
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        Role role = roleService.queryRoleById(roleId, systemId);
        model.addAttribute("roleId",roleId);
        model.addAttribute("roleName", role.getName());
        model.addAttribute("user",user);
        model.addAttribute("systemName", systemInfo.getName());
        model.addAttribute("systemId", systemInfo.getId());
        return "role/roleuserlist";
    }

    /**
     * 已选用户
     */
    @RequestMapping("selectuser")
    public String selectUser(Model model,Long roleId,User user, Long systemId, Pagination pagination) {
        List<User> checkedUsers = userService.getUserListByRoleId(user, roleId, systemId, pagination);
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        model.addAttribute("checkedUsers",checkedUsers);
        model.addAttribute("roleId",roleId);
        model.addAttribute("user",user);
        model.addAttribute("systemName", systemInfo.getName());
        model.addAttribute("systemId", systemInfo.getId());
        return "role/selectuser";
    }

    /**
     * 未选用户
     */
    @RequestMapping("unselectuser")
    public String unSelectUser(Model model,Long roleId,User user, Long systemId, Pagination pagination) {
        List<User> unCheckUsers = userService.queryUnCheckUser(user,roleId, systemId, pagination);
        SystemInfo systemInfo = systemInfoService.getSystemInfoById(systemId);
        model.addAttribute("unCheckUsers",unCheckUsers);
        model.addAttribute("roleId",roleId);
        model.addAttribute("user",user);
        model.addAttribute("systemName", systemInfo.getName());
        model.addAttribute("systemId", systemInfo.getId());
        return "role/unselectuser";
    }

}
