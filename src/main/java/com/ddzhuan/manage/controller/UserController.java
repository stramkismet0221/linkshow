package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.CommonStatusEnum;
import com.ddzhuan.manage.common.enums.SysLogInfoEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.model.YpTerminal;
import com.ddzhuan.manage.service.SysLogInfoService;
import com.ddzhuan.manage.service.UserService;
import com.ddzhuan.manage.service.YpTerminalService;
import com.ddzhuan.manage.tool.MD5Tool;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * @author jiang yong tao
 * @date 2019/7/1 14:41
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private YpTerminalService ypTerminalService;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    /**
     * 获取用户列表
     * @param model 用户列表
     * @param user 条件
     * @param pagination 分页信息
     * @return
     */
    @RequestMapping("getuserlist")
    public String getuserlist(Model model, User user, Pagination pagination){
        List<User> userList = userService.queryUserListByConditions(user,pagination);
        model.addAttribute("userList",userList);
        model.addAttribute("user",user);
        return "user/userlist";
    }


    /**
     * 跳新增页面
     * @return
     */
    @RequestMapping("adduser")
    public String addUser(){
        return "user/adduser";
    }
    /**
     * 跳新增页面
     * @return
     */
    @RequestMapping("test")
    public String test(){
        return "user/test";
    }


    /**
     * 获取用户信息
     * @param model 用户信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping("getuserinfobyid")
    public String userInfo(Model model,Long userId,Integer type){
        User user = userService.queryUserInfoById(userId);
        model.addAttribute("user",user);
        if (1 == type){
            return "user/userdetail";
        }else {
            return "user/modifyuserinfo";
        }

    }

    /**
     * 新增用户
     * @param user 用户
     * @return
     */
    @RequestMapping("insertuser")
    @ResponseBody
    public BaseResultInfo insertUser(User user){
        BaseResultInfo result = new BaseResultInfo();
        try {
            // 校验手机号码是否存在
            boolean isExist = userService.isExistMobile(user.getMobile());
            if (isExist) {
                result.setSuccess(false);
                result.setMsg("手机号码重复，请重新输入");
                return result;
            }
            String psw = "123456";
            user.setPassword(MD5Tool.MD5Encode(psw).toUpperCase());
            user.setStatus(CommonStatusEnum.NORMAL.getCode());
            result.setSuccess(userService.insertUser(user));
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+" 新增用戶数据为："+user.toString());
            result.setMsg("保存成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 更新用户
     * @param user 用户
     * @return
     */
    @RequestMapping("modifyuser")
    @ResponseBody
    public BaseResultInfo updateUser(User user){
        BaseResultInfo result = new BaseResultInfo();
        try {
            // 校验手机号码是否重复
            User oldUser = userService.queryUserInfoById(user.getId());
            String oldMobile = oldUser.getMobile();
            String newMobile = user.getMobile();
            if (newMobile!=null && !oldMobile.equals(newMobile)) {
                boolean isExist = userService.isExistMobile(newMobile);
                if (isExist) {
                    result.setSuccess(false);
                    result.setMsg("手机号码重复，请重新输入");
                    return result;
                }
            }
            result.setSuccess(userService.updateUserByConditions(user));
            sysLogInfoService.addSysLogInfoForTask(
                    SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+" 修改用戶数据为："+user.toString());
            result.setMsg("保存成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除用户
     * @param user 用户
     * @return
     */
    @RequestMapping("deleteuser")
    @ResponseBody
    public BaseResultInfo deleteUser(User user){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(userService.updateUserByConditions(user));
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员："+user.getUserName()+" 刪除用戶数据为："+user.toString());
            result.setMsg("删除成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("删除异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 用户添加菜单
     * @param userId 用户ID
     * @param menuIds 菜单ID集合
     * @return
     */
    @RequestMapping("addusermenus")
    @ResponseBody
    public BaseResultInfo addRoleMenus(Long userId, Long[] menuIds){
        BaseResultInfo result = new BaseResultInfo();
        try {
            result.setSuccess(userService.insertUserMenus(userId,Arrays.asList(menuIds)));
            result.setMsg("保存成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 根据用户id查询用户
     * @param userName 用户ID
     * @return 该用户是否存在
     */
    @RequestMapping("getusercountbyusername")
    @ResponseBody
    public int getUserCountByUserId(String userName){
        int userCount = userService.queryUserCountById(userName);
        return userCount;
    }

    @RequestMapping("getterminalsbyuserid")
    public String getTerminalByUserId(HttpServletRequest request, HttpServletResponse response, Model model,
                                      Long userId, YpTerminal ypTerminal) {
        User user = userService.queryUserInfoById(userId);
        model.addAttribute("user", user);
        model.addAttribute("terminal", ypTerminal);
        return "user/userterminals";
    }

    @RequestMapping("selectterminals")
    public String selectTerminals(Model model, Pagination pagination, Long userId, YpTerminal ypTerminal) {
        pagination.setRows(10);
        List<YpTerminal> ypTerminals = ypTerminalService.getYpTerminalListByUserId(ypTerminal, userId, 1, pagination);
        model.addAttribute("selectTerminals", ypTerminals);
        model.addAttribute("userId", userId);
        return "user/selectterminals";
    }

    @RequestMapping("unselectterminals")
    public String unSelectTerminals(Model model, Pagination pagination, Long userId, YpTerminal ypTerminal) {
        pagination.setRows(10);
        List<YpTerminal> ypTerminals = ypTerminalService.getYpTerminalListByUserId(ypTerminal, userId, 2, pagination);
        model.addAttribute("unSelectTerminals", ypTerminals);
        model.addAttribute("userId", userId);
        return "user/unselectterminals";
    }

    @RequestMapping("deluserterminal")
    @ResponseBody
    public BaseResultInfo delUserTerminal(Long userId, String terminalId) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            ypTerminalService.delUserTerminal(userId, terminalId);
            result.setSuccess(true);
            result.setMsg("移除成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("移除失败");
        }
        return result;
    }
    @RequestMapping("adduserterminal")
    @ResponseBody
    public BaseResultInfo addUserTerminal(Long userId, String terminalId) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            ypTerminalService.addUserTerminal(userId, terminalId);
            result.setSuccess(true);
            result.setMsg("加入成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("加入失败");
        }
        return result;
    }
    @RequestMapping("updatepwd")
    public String toeditpwd() {
    	return "user/updatepwd";
    }
    @RequestMapping("forgetpwd")
    public String forgetpwd(HttpServletRequest request, HttpServletResponse response, Model model,@RequestParam(required = false) Long systemId) {
    	request.setAttribute("systemId", systemId);
    	return "forgetpwd";
    }
    @RequestMapping("editpwd")
    @ResponseBody
    public BaseResultInfo editPwd(HttpServletRequest request,String pwd) {
    	BaseResultInfo result = new BaseResultInfo();
    	HttpSession session = request.getSession();
    	User user =(User) session.getAttribute("sysuser");
		boolean res=userService.editPwd(pwd, user.getId());
		if(res) {
			result.setSuccess(res);
			result.setMsg("修改成功");
		}else {
			result.setSuccess(false);
			result.setMsg("修改失败");
		}
    	return result;
    }
    @RequestMapping("checkpwd")
    @ResponseBody
    public BaseResultInfo checkPwd(HttpServletRequest request,String pwd) {
    	BaseResultInfo result = new BaseResultInfo();
    	HttpSession session = request.getSession();
    	User user =(User) session.getAttribute("sysuser");
    	boolean res = userService.checkPwd(user.getId(),pwd);
    	result.setSuccess(res);
    	return result;
    }
    @RequestMapping("checkphone")
    @ResponseBody
    public BaseResultInfo checkPhone(String phone) {
    	BaseResultInfo resultInfo = new BaseResultInfo();
    	boolean res = userService.isExistMobile(phone);
    	if(res) {
    		resultInfo.setSuccess(true);
    		resultInfo.setMsg("手机号码已存在");
    		return resultInfo;
    	}
    	resultInfo.setSuccess(false);
    	resultInfo.setMsg("手机号码不存在");
    	return resultInfo;
    }
  	@RequestMapping(value="forgetpwdedit",method=RequestMethod.POST)
  	@ResponseBody
  	public HashMap<String, Object> forgetpwd(User user,String mobilecode){
  		HashMap<String,Object> resultMap = Maps.newHashMap();
  		int res = 0;
  		String phcode = "";
  		phcode = (String) cache.get("smsCache",user.getMobile());
  		if(StringUtils.isEmpty(phcode) || !StringUtils.equals(mobilecode, phcode)){
  				resultMap.put("msg", "请输入正确的短信验证码");
  				resultMap.put("res", -2);
  				return resultMap;
  		}
  		String pwd=MD5Tool.MD5Encode(user.getPassword()).toUpperCase();
  		if(phcode.equals(mobilecode)){
  			res = userService.editPwdByMobile(user.getMobile(), pwd);
  		}
  		cache.remove("smsCache",user.getMobile());
  		resultMap.put("res", res);
  		return resultMap;
  	}
}
