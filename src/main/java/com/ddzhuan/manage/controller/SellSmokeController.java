package com.ddzhuan.manage.controller;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.CommonStatusEnum;
import com.ddzhuan.manage.common.enums.PowerTypeEnum;
import com.ddzhuan.manage.common.enums.SysLogInfoEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.*;
import com.ddzhuan.manage.service.*;
import com.ddzhuan.manage.tool.MD5Tool;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr Liu
 * @date 2019/9/30
 * desc
 */

@Controller
@RequestMapping("/smoke/")
public class SellSmokeController extends BaseController {

    @Autowired
    private SellSmokeService sellSmokeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PowerService powerService;

    @Autowired
    private SysLogInfoService sysLogInfoService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 运营商管理
     */
    @RequestMapping("addAgent")
    @ResponseBody
    public BaseResultInfo addAgent(HttpServletRequest request, HttpServletResponse response,
                                   SellSmokeAgent agentModel) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            sellSmokeService.saveAgent(agentModel);
            result.setMsg("新增成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("新增异常");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("removeAgent")
    @ResponseBody
    public BaseResultInfo removeAgent(SellSmokeAgent agentModel) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            Long agentId = agentModel.getId();
            List<User> userList = userService.getUserListByAgentId(agentId);
            if (!CollectionUtils.isEmpty(userList)) {
                result.setMsg("渠道商下存在用户，不可删除");
                return result;
            }
            sellSmokeService.removeAgent(agentModel);
            result.setMsg("删除成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("删除异常");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("updateAgent")
    @ResponseBody
    public BaseResultInfo updateAgent(HttpServletRequest request, HttpServletResponse response,
                                      SellSmokeAgent sellSmokeAgent) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            sellSmokeService.saveAgent(sellSmokeAgent);
            result.setMsg("修改成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg("修改异常");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("getAgentList")
    public String getAgentList(HttpServletRequest request, HttpServletResponse response,
                               Model model, SellSmokeAgent agentModel, Pagination pagination,
                               @RequestParam(required = false) Long id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String contacts) {
        List<SellSmokeAgent> sellSmokeAgentList = null;
        try {
            SellSmokeAgent info = new SellSmokeAgent();
            info.setId(id);
            info.setName(name);
            info.setContacts(contacts);
            sellSmokeAgentList = sellSmokeService.getSellSmokeAgentList(info, pagination);
        } catch (Exception e) {

        }
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("contacts", contacts);
        model.addAttribute("agentInfos", sellSmokeAgentList);
        return "smoke/agentlist";
    }

    @RequestMapping("goAgentDetail")
    public String goAgentDetail(HttpServletRequest request, HttpServletResponse response,
                              Model model, @RequestParam(required = true) Long id) {
        SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(id);
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        return "smoke/agentdetail";
    }

    @RequestMapping("goAddAgent")
    public String goAddAgent(HttpServletRequest request, HttpServletResponse response,
                             Model model, SellSmokeAgent agentModel, Pagination pagination) {
        return "smoke/addagent";
    }

    @RequestMapping("goEditAgent")
    public String goEditAgent(HttpServletRequest request, HttpServletResponse response,
                              Model model, @RequestParam(required = true) Long id) {
        SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(id);
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        return "smoke/editagent";
    }

    /**
     * 电子烟设备安装LinkShow管理员查看列表
     *
     * @param id              id
     * @param agentName       渠道商名称
     * @param pagination      分页参数
     * @param applyUserName   申请人姓名（模糊查询）
     * @param auditUserName   审核人姓名（模糊查询）
     * @param installUserName 安装人姓名（模糊查询）
     * @param status          安装状态 0、待审核 1、处理中 2、已安装 3、取消安装
     * @return 设备安装列表
     */
    @RequestMapping("getconmnginslist")
    public String getConsoleMngInstallList(HttpServletRequest request, HttpServletResponse response,
                                           Model model, Pagination pagination,
                                           @RequestParam(required = false) Long id,
                                           @RequestParam(required = false) String agentName,
                                           @RequestParam(required = false) String applyUserName,
                                           @RequestParam(required = false) String auditUserName,
                                           @RequestParam(required = false) String installUserName,
                                           @RequestParam(required = false) Integer status) {
        // 已安装按钮开放给安装人员操作，其他按钮均为管理员权限
        User loginUser = (User) request.getSession().getAttribute("sysuser");
        boolean isInstallUser = this.isRoleUser(loginUser.getId(), "DZYSHJAZY");
        boolean isECMng = this.isRoleUser(loginUser.getId(), "DZYSHJHTGLY");
        SellSmokeInstall ssi = new SellSmokeInstall();
        ssi.setId(id);
        ssi.setAgentName(agentName);
        ssi.setApplyUserName(applyUserName);
        ssi.setAuditUserName(auditUserName);
        ssi.setInstallUserName(installUserName);
        ssi.setStatus(status);
        Long userId = null;
        if (!isECMng && isInstallUser) {
            userId = loginUser.getId();
        }
        List<SellSmokeInstall> sellSmokeInstalls = sellSmokeService.getSellSmokeInstallList(ssi, userId, pagination);
        model.addAttribute("id", id);
        model.addAttribute("agentName", agentName);
        model.addAttribute("applyUserName", applyUserName);
        model.addAttribute("auditUserName", auditUserName);
        model.addAttribute("installUserName", installUserName);
        model.addAttribute("status", status);
        model.addAttribute("sellSmokeInstalls", sellSmokeInstalls);
        model.addAttribute("isInstallUser", isInstallUser);
        model.addAttribute("isECMng", isECMng);
        return "smoke/conmnginslist";
    }

    /**
     * 代理商管理员查看安装列表
     *
     * @param pagination      分页参数
     * @param applyUserName   申请人（模糊查询）
     * @param auditUserName   审核人（模糊查询）
     * @param installUserName 安装人（模糊查询）
     * @param status          安装状态 0、待审核 1、处理中 2、已安装 3、取消安装
     * @return 设备安装列表
     */
    @RequestMapping("getagentmnginslist")
    public String getAgentMngInstallList(HttpServletRequest request, HttpServletResponse response,
                                         Model model, Pagination pagination,
                                         @RequestParam(required = false) String applyUserName,
                                         @RequestParam(required = false) String auditUserName,
                                         @RequestParam(required = false) String installUserName,
                                         @RequestParam(required = false) Integer status) {
        User user = (User) request.getSession().getAttribute("sysuser");
        Long agentId = user.getAgentId();
        List<SellSmokeInstall> sellSmokeInstalls = null;
        if (agentId == null || agentId <= 0) {
            sellSmokeInstalls = new ArrayList<>();
        } else {
            SellSmokeInstall ssi = new SellSmokeInstall();
            ssi.setAgentId(agentId);
            ssi.setApplyUserName(applyUserName);
            ssi.setAuditUserName(auditUserName);
            ssi.setInstallUserName(installUserName);
            ssi.setStatus(status);
            sellSmokeInstalls = sellSmokeService.getSellSmokeInstallList(ssi, null, pagination);
        }
        model.addAttribute("applyUserName", applyUserName);
        model.addAttribute("auditUserName", auditUserName);
        model.addAttribute("installUserName", installUserName);
        model.addAttribute("sellSmokeInstalls", sellSmokeInstalls);
        return "smoke/agentmnginslist";
    }

    /**
     * 代理商地推员查询安装列表
     *
     * @param pagination 分页参数
     * @param status     状态
     * @return 安装信息列表
     */
    @RequestMapping("getagentuserinslist")
    public String getAgentUserInstallList(HttpServletRequest request, HttpServletResponse response,
                                          Model model, Pagination pagination,
                                          @RequestParam(required = false) Integer status) {
        User user = (User) request.getSession().getAttribute("sysuser");
        Long userId = user.getId();
        SellSmokeInstall ssi = new SellSmokeInstall();
        ssi.setStatus(status);
        List<SellSmokeInstall> sellSmokeInstalls = sellSmokeService.getSellSmokeInstallList(ssi, userId, pagination);
        model.addAttribute("status", status);
        model.addAttribute("sellSmokeInstalls", sellSmokeInstalls);
        return "smoke/agentuserinslist";
    }

    /**
     * 申请安装 1、代理商管理员申请 2、代理人员申请
     */
    @RequestMapping("installapply")
    public String mngApplyInstall(HttpServletRequest request, HttpServletResponse response,
                                  Model model, @RequestParam(required = false, defaultValue = "1") Integer type) {
        String visitUrl = "";
        if (type.intValue() == 1) {
            visitUrl = "getagentmnginslist";
        } else if (type.intValue() == 2) {
            visitUrl = "getagentuserinslist";
        }
        List<Dictionary> dicList = dictionaryService.getDictionaryListByType(2, new Pagination(1, 2147483647));
        model.addAttribute("visitUrl", visitUrl);
        model.addAttribute("dicList", dicList);
        return "smoke/installapply";
    }

    /**
     * 保存申请
     *
     * @param sellSmokeInstall 安装相关信息
     * @return result
     */
    @RequestMapping("saveapply")
    @ResponseBody
    public BaseResultInfo saveApply(HttpServletRequest request, HttpServletResponse response,
                                    Model model, SellSmokeInstall sellSmokeInstall) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            // set申请人相关属性
            User user = (User) request.getSession().getAttribute("sysuser");
            Long userId = user.getId();
            Long userMobile = Long.parseLong(user.getMobile());
            String userName = user.getRealName();
            Long agentId = user.getAgentId();
            if (agentId == null) {
                result.setMsg("不是渠道商用户，不可申请安装");
                return result;
            }
            SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(agentId);
            String agentName = "";
            if (sellSmokeAgent != null) {
                agentName = sellSmokeAgent.getName();
            }
            sellSmokeInstall.setApplyUserId(userId);
            sellSmokeInstall.setApplyUserName(userName);
            sellSmokeInstall.setApplyUserMobile(userMobile);
            sellSmokeInstall.setAgentId(agentId);
            sellSmokeInstall.setAgentName(agentName);
            sellSmokeService.saveSellSmokeInstall(sellSmokeInstall);
            result.setSuccess(true);
            result.setMsg("保存成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("保存失败");
        }
        return result;
    }

    /**
     * 更新
     *
     * @param id 安装记录id
     * @return
     */
    @RequestMapping("editinstall")
    public String editInstall(HttpServletRequest request, HttpServletResponse response,
                              Model model, @RequestParam(required = true) Long id) {
        SellSmokeInstall sellSmokeInstall = sellSmokeService.getSellSmokeById(id);
        List<Dictionary> dicList = dictionaryService.getDictionaryListByType(2, new Pagination(1, 2147483647));
        model.addAttribute("sellSmokeInstall", sellSmokeInstall);
        model.addAttribute("dicList", dicList);
        return "smoke/editinstall";
    }

    /**
     * 更新
     *
     * @param sellSmokeInstall 安装信息
     * @return
     */
    @RequestMapping("updateinstall")
    @ResponseBody
    public BaseResultInfo updateInstall(HttpServletRequest request, HttpServletResponse response,
                                        Model model, SellSmokeInstall sellSmokeInstall) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            SellSmokeInstall ssi = sellSmokeService.getSellSmokeById(sellSmokeInstall.getId());
            Integer status = ssi.getStatus();
            if (status != 0) {
                result.setMsg("安装中，不可修改");
                return result;
            }
            sellSmokeService.updateSellSmokeInstall(sellSmokeInstall);
            result.setSuccess(true);
            result.setMsg("修改成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("修改失败");
        }
        return result;
    }

    @RequestMapping("removeinstall")
    @ResponseBody
    public BaseResultInfo removeInstall(HttpServletRequest request, HttpServletResponse response,
                                        Model model, @RequestParam(required = true) Long id) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            SellSmokeInstall sellSmokeInstall = sellSmokeService.getSellSmokeById(id);
            Integer status = sellSmokeInstall.getStatus();
            if (status != 0) {
                result.setMsg("安装中，不可删除");
                return result;
            }
            sellSmokeService.removeSellSmokeInstall(id);
            result.setSuccess(true);
            result.setMsg("删除成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 处理分配安装人员
     * @param id 安装信息id
     * @return 审核页面
     */
    @RequestMapping("dealinstall")
    public String dealInstall(HttpServletRequest request, HttpServletResponse response,
                               Model model, @RequestParam(required = true) Long id) {
        User user = (User) request.getSession().getAttribute("sysuser");
        Long userId = user.getId();
        boolean isMng =  isConsoleManager(userId);
        boolean isECMng = isRoleUser(userId, "DZYSHJHTGLY");
        if (!isMng && !isECMng) {
            try {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                String msg = "alert('无权处理');history.go(-1)";
                writer.print("<script type='text/javascript'>" + msg + "</script>");
                writer.flush();
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        SellSmokeInstall sellSmokeInstall = sellSmokeService.getSellSmokeById(id);
        Long agentId = sellSmokeInstall.getAgentId();
        SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(agentId);
        // 已经创建好的角色，固定值
        String roleCode = "DZYSHJAZY";
        Role role = new Role();
        role.setCode(roleCode);
        List<Role> roles = roleService.queryRoleListByConditions(role, new Pagination(1, 2147483647));
        List<User> installUserList = new ArrayList<>();
        Long roleId = null;
        if (!CollectionUtils.isEmpty(roles) && roles.size()==1) {
            roleId = roles.get(0).getId();
        }
        if (roleId != null && roleId > 0L) {
            installUserList = userService.getUserListByRoleId(new User(),roleId,3L,new Pagination(1,2147483647));
        }
        Dictionary dictionary = new Dictionary();
        dictionary.setType(2);
        dictionary.setCode(sellSmokeInstall.getTerminalType());
        Dictionary dic = dictionaryService.getDictionaryByValue(dictionary);
        model.addAttribute("user", user);
        model.addAttribute("sellSmokeInstall", sellSmokeInstall);
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        model.addAttribute("installUserList", installUserList);
        model.addAttribute("dic", dic);
        return "smoke/dealinstall";
    }

    @RequestMapping("finishinstall")
    public String finishInstall(HttpServletRequest request, HttpServletResponse response,
                                Model model, @RequestParam(required = true) Long id) {
        SellSmokeInstall sellSmokeInstall = sellSmokeService.getSellSmokeById(id);
        Long agentId = sellSmokeInstall.getAgentId();
        SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(agentId);
        Dictionary dictionary = new Dictionary();
        dictionary.setType(2);
        dictionary.setCode(sellSmokeInstall.getTerminalType());
        Dictionary dic = dictionaryService.getDictionaryByValue(dictionary);
        model.addAttribute("sellSmokeInstall", sellSmokeInstall);
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        model.addAttribute("dic", dic);
        return "smoke/finishinstall";
    }

    @RequestMapping("revertinstall")
    public String revertInstall(HttpServletRequest request, HttpServletResponse response,
                                Model model, @RequestParam(required = true) Long id) {
        SellSmokeInstall sellSmokeInstall = sellSmokeService.getSellSmokeById(id);
        Long agentId = sellSmokeInstall.getAgentId();
        SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(agentId);
        Dictionary dictionary = new Dictionary();
        dictionary.setType(2);
        dictionary.setCode(sellSmokeInstall.getTerminalType());
        Dictionary dic = dictionaryService.getDictionaryByValue(dictionary);
        List<SellSmokeTerminal> sellSmokeTerminals = sellSmokeService.getSellSmokeTerminalList(id);
        model.addAttribute("sellSmokeInstall", sellSmokeInstall);
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        model.addAttribute("dic", dic);
        model.addAttribute("sellSmokeTerminals", sellSmokeTerminals);
        return "smoke/revertinstall";
    }

    @RequestMapping("cancelinstall")
    @ResponseBody
    public BaseResultInfo cancelInstall(HttpServletRequest request, HttpServletResponse response,
                                        Model model, @RequestParam(required = true) Long id,
                                        @RequestParam(required = false) Integer type,
                                        @RequestParam(required = false) String auditRemark,
                                        @RequestParam(required = false) String installRemark) {
        User loginUser = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
             SellSmokeInstall ssi = new SellSmokeInstall();
             if (type != null && type == 1) {
                 ssi.setAuditUserId(loginUser.getId());
                 ssi.setAuditUserName(loginUser.getRealName());
                 ssi.setAuditRemark(auditRemark);
                 ssi.setAuditUserMobile(Long.parseLong(loginUser.getMobile()));
             }
             if (type != null && type == 2) {
                 ssi.setInstallRemark(installRemark);
             }
             ssi.setId(id);
             ssi.setStatus(3);
             sellSmokeService.cancelSellSmokeInstall(ssi);
             result.setSuccess(true);
             result.setMsg("取消成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("取消失败");
        }
        return result;
    }

    /**
     * 详情
     * @param id 安装信息id
     * @return
     */
    @RequestMapping("installdetail")
    public String installDetail(HttpServletRequest request, HttpServletResponse response,
                                Model model, @RequestParam(required = true) Long id,
                                @RequestParam(required = false) Integer type) {
        SellSmokeInstall sellSmokeInstall = sellSmokeService.getSellSmokeById(id);
        Long agentId = sellSmokeInstall.getAgentId();
        SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(agentId);
        Dictionary dictionary = new Dictionary();
        dictionary.setType(2);
        dictionary.setCode(sellSmokeInstall.getTerminalType());
        Dictionary dic = dictionaryService.getDictionaryByValue(dictionary);
        List<SellSmokeTerminal> sellSmokeTerminals = sellSmokeService.getSellSmokeTerminalList(id);
        String visitUrl = "";
        if (type.intValue() == 1) {
            visitUrl = "getconmnginslist";
        } else if (type.intValue() == 2) {
            visitUrl = "getagentmnginslist";
        } else if (type.intValue() == 3) {
            visitUrl = "getagentuserinslist";
        }
        model.addAttribute("sellSmokeInstall", sellSmokeInstall);
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        model.addAttribute("dic", dic);
        model.addAttribute("sellSmokeTerminals", sellSmokeTerminals);
        model.addAttribute("visitUrl", visitUrl);
        return "smoke/installdetail";
    }

    @RequestMapping("dealterminal")
    @ResponseBody
    public BaseResultInfo dealTerminal(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(required = true) Long id,
                                       @RequestParam(required = true) Long installUserId,
                                       @RequestParam(required = false) String auditRemark,
                                       @RequestParam(required = false) String remark) {
        User loginUser = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            User userInfo = userService.queryUserInfoById(installUserId);
            SellSmokeInstall ssi = new SellSmokeInstall();
            ssi.setId(id);
            ssi.setAuditUserId(loginUser.getId());
            ssi.setAuditUserName(loginUser.getRealName());
            ssi.setAuditUserMobile(Long.parseLong(loginUser.getMobile()));
            ssi.setAuditRemark(auditRemark);
            ssi.setInstallUserId(userInfo.getId());
            ssi.setInstallUserName(userInfo.getRealName());
            ssi.setInstallUserMobile(Long.parseLong(userInfo.getMobile()));
            ssi.setRemark(remark);
            ssi.setStatus(1);
            sellSmokeService.updateSellSmokeInstall(ssi);
            result.setSuccess(true);
            result.setMsg("处理成功");
            return result;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("处理失败");
        }
        return result;
    }

    @RequestMapping("installterminal")
    @ResponseBody
    public BaseResultInfo installTerminal(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(required = true) Long id,
                                          @RequestParam(required = false) String installRemark,
                                          @RequestParam(required = true) String terminals,
                                          @RequestParam(required = false) String remark) {
        User loginUser = (User) request.getSession().getAttribute("sysuser");
        BaseResultInfo result = new BaseResultInfo();
        try {
            SellSmokeInstall ssi = new SellSmokeInstall();
            ssi.setId(id);
            ssi.setInstallUserId(loginUser.getId());
            ssi.setInstallUserName(loginUser.getRealName());
            ssi.setInstallUserMobile(Long.parseLong(loginUser.getMobile()));
            ssi.setInstallRemark(installRemark);
            ssi.setRemark(remark);
            ssi.setStatus(2);

            SellSmokeInstall sellSmokeInstall = sellSmokeService.getSellSmokeById(id);
            String terminalType = sellSmokeInstall.getTerminalType();
            Dictionary dictionary = new Dictionary();
            dictionary.setCode(terminalType);
            dictionary.setType(2);
            Dictionary dic = dictionaryService.getDictionaryByValue(dictionary);
            String terminalTypeName = dic.getName();

            List<SellSmokeTerminal> list = new ArrayList<>();
            SellSmokeTerminal sst = null;
            String[] terminalArr = terminals.split(",");
            for (int i = 0; i < terminalArr.length; i++) {
                sst = new SellSmokeTerminal();
                sst.setInstallId(id);
                sst.setTerminalType(terminalType);
                sst.setTerminalTypeName(terminalTypeName);
                sst.setTerminalCode(terminalArr[i].split(";")[0]);
                sst.setTerminalName(terminalArr[i].split(";")[1]);
                list.add(sst);
            }
            ssi.setSellSmokeTerminals(list);
            sellSmokeService.updateSellSmokeInstall(ssi);
            result.setSuccess(true);
            result.setMsg("安装成功");
        } catch (Exception ex) {
            result.setMsg("安装失败");
            log.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * 撤回
     */
    @RequestMapping("revertterminal")
    @ResponseBody
    public BaseResultInfo revertTerminal(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(required = true) Long id,
                                         @RequestParam(required = false) String remark) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            SellSmokeInstall ssi = new SellSmokeInstall();
            ssi.setId(id);
            ssi.setAuditUserId(-1L);
            ssi.setAuditUserName("");
            ssi.setAuditUserMobile(-1L);
            ssi.setAuditRemark("");
            ssi.setInstallUserId(-1L);
            ssi.setInstallUserName("");
            ssi.setInstallUserMobile(-1L);
            ssi.setInstallRemark("");
            ssi.setStatus(0);
            ssi.setRemark(remark);
            sellSmokeService.revertSellSmokeInstall(ssi);
            result.setSuccess(true);
            result.setMsg("撤回成功");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            result.setMsg("撤回失败");
        }
        return result;
    }

    /**
     * 获取用户列表
     *
     * @param model      用户列表
     * @param user       条件
     * @param pagination 分页信息
     * @return
     */
    @RequestMapping("getuserlist")
    public String getuserlist(HttpServletRequest request, HttpServletResponse response,
                              Model model, User user, Pagination pagination,
                              @RequestParam(required = false) Long agentId) {
        // 代理商管理员能看到自己代理商下面的人员
        // 后台管理员能看到所有的代理商下人员
        User loginUser = (User) request.getSession().getAttribute("sysuser");
        boolean isMng = isConsoleManager(loginUser.getId());
        boolean isECMng = isRoleUser(loginUser.getId(), "DZYSHJHTGLY");
        // 管理员查看全部
        if (agentId == null && (isMng || isECMng)) {
            agentId = 0L;
        }
        // 代理商管理员查看自己代理商下的人员
        if (agentId == null && !(isECMng || isMng)) {
            agentId = loginUser.getAgentId();
        }
        user.setAgentId(agentId);
        List<User> userList = userService.queryUserListByConditions(user, pagination);
        List<SellSmokeAgent> sellSmokeAgents = sellSmokeService.getSellSmokeAgentList(new SellSmokeAgent(), new Pagination(1, 2147483647));
        this.fillAgentName(userList, sellSmokeAgents);
        model.addAttribute("userList", userList);
        model.addAttribute("user", user);
        model.addAttribute("isECMng", isECMng);
        model.addAttribute("isMng", isMng);
        model.addAttribute("sellSmokeAgents", sellSmokeAgents);
        model.addAttribute("agentId", agentId);
        return "smoke/userlist";
    }

    /**
     * 跳新增页面
     *
     * @return
     */
    @RequestMapping("adduser")
    public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
        User loginUser = (User) request.getSession().getAttribute("sysuser");
        Long agentId = 0L;
        if (loginUser != null) {
            agentId = loginUser.getAgentId();
        }
        SellSmokeAgent sellSmokeAgent = null;
        if (agentId != null && agentId > 0L) {
            sellSmokeAgent = sellSmokeService.getSellAgentById(agentId);
        }
        List<SellSmokeAgent> sellSmokeAgents = sellSmokeService.getSellSmokeAgentList(new SellSmokeAgent(), new Pagination(1, 2147483647));
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        model.addAttribute("sellSmokeAgents", sellSmokeAgents);
        return "smoke/adduser";
    }

    /**
     * 根据用户id查询用户
     *
     * @param userName 用户ID
     * @return 该用户是否存在
     */
    @RequestMapping("getusercountbyusername")
    @ResponseBody
    public int getUserCountByUserId(String userName) {
        int userCount = userService.queryUserCountById(userName);
        return userCount;
    }

    /**
     * 获取用户信息
     *
     * @param model  用户信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping("getuserinfobyid")
    public String userInfo(Model model, Long userId, Integer type) {
        User user = userService.queryUserInfoById(userId);
        Long agentId = user.getAgentId();
        SellSmokeAgent sellSmokeAgent = sellSmokeService.getSellAgentById(agentId);
        model.addAttribute("user", user);
        model.addAttribute("sellSmokeAgent", sellSmokeAgent);
        if (1 == type) {
            return "smoke/userdetail";
        } else {
            return "smoke/modifyuserinfo";
        }
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return
     */
    @RequestMapping("insertuser")
    @ResponseBody
    public BaseResultInfo insertUser(HttpServletRequest request, HttpServletResponse response, User user) {
        // 登录用户
        User loginUser = (User) request.getSession().getAttribute("sysuser");
        Long systemId = (Long) request.getSession().getAttribute("systemid");
        BaseResultInfo result = new BaseResultInfo();
        try {
            // 校验手机号码是否存在
            boolean isExist = userService.isExistMobile(user.getMobile());
            if (isExist) {
                result.setSuccess(false);
                result.setMsg("手机号码重复，请重新输入");
                return result;
            }
            // TODO 暂定为默认密码
            String psw = "123456";
            user.setPassword(MD5Tool.MD5Encode(psw).toUpperCase());
            user.setStatus(CommonStatusEnum.NORMAL.getCode());
//            user.setAgentId(agentId);
            sellSmokeService.saveAgentUser(user, systemId);
            result.setSuccess(true);
            result.setMsg("保存成功");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员：" + loginUser.getUserName() + " 新增用戶数据为：" + user.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return
     */
    @RequestMapping("modifyuser")
    @ResponseBody
    public BaseResultInfo updateUser(User user) {
        BaseResultInfo result = new BaseResultInfo();
        try {
            // 校验手机号码是否重复
            User oldUser = userService.queryUserInfoById(user.getId());
            String oldMobile = oldUser.getMobile();
            String newMobile = user.getMobile();
            if (newMobile != null && !oldMobile.equals(newMobile)) {
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
                    "操作员：" + user.getUserName() + " 修改用户数据为：" + user.toString());
            result.setMsg("保存成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg("保存异常");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param user 用户
     * @return
     */
    @RequestMapping("deleteuser")
    @ResponseBody
    public BaseResultInfo deleteUser(HttpServletRequest request, HttpServletResponse response, User user) {
        Long systemId = (Long) request.getSession().getAttribute("systemid");
        BaseResultInfo result = new BaseResultInfo();
        try {
            Long userId = user.getId();
            List<SellSmokeInstall> sellSmokeInstalls = sellSmokeService.getSellSmokeInstallListByUserId(userId);
            if (!CollectionUtils.isEmpty(sellSmokeInstalls)) {
                result.setMsg("用户有申请、处理、安装记录，不可删除");
                return result;
            }
            sellSmokeService.removeAgentUser(user, systemId);
            result.setSuccess(true);
            result.setMsg("删除成功");
            sysLogInfoService.addSysLogInfoForTask(SysLogInfoEnum.LOG_OPERATOR_TYPE_SYSTEM.getValue(),
                    "操作员：" + user.getUserName() + " 刪除用戶数据为：" + user.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg("删除异常");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("installstat")
    public String installStatistics(HttpServletRequest request, HttpServletResponse response,
                                    Model model, @RequestParam(required = false) String userName,
                                    @RequestParam(required = false) String startTime,
                                    @RequestParam(required = false) String endTime) {
        return "";
    }

    /**
     * 查询是否是LinkShow管理员
     * @param userId 用户id
     * @return 是否为管理员
     */
    private boolean isConsoleManager(Long userId) {
        if (userId == null) {
            return false;
        }
        List<Long> powerIds = powerService.getPowerIdsByUserId(userId, 1L);
        if (CollectionUtils.isEmpty(powerIds)) {
            return false;
        }
        for (int i = 0; i < powerIds.size(); i++) {
            Long powerId = powerIds.get(i);
            Power power = powerService.getPowerById(powerId);
            if (power != null && power.getType() == PowerTypeEnum.SYSTEMMANAGE.getCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据userId和roleCode判断是否拥有该角色
     * @param userId 用户id
     * @return 是否为角色用户
     */
    private boolean isRoleUser(Long userId, String roleCode) {
        // 已经创建好的角色，固定值
        Role role = new Role();
        role.setCode(roleCode);
        List<Role> roles = roleService.queryRoleListByConditions(role, new Pagination(1, 2147483647));
        List<User> installUserList = new ArrayList<>();
        Long roleId = null;
        if (!CollectionUtils.isEmpty(roles) && roles.size()==1) {
            roleId = roles.get(0).getId();
        }
        if (roleId != null && roleId > 0L) {
            installUserList = userService.getUserListByRoleId(new User(),roleId,3L,new Pagination(1,2147483647));
        }
        for (User user : installUserList) {
            if (user.getId().intValue() == userId.intValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 填充agentName
     */
    private void fillAgentName(List<User> users, List<SellSmokeAgent> sellSmokeAgents) {
        for (User user : users) {
            for (SellSmokeAgent sellSmokeAgent : sellSmokeAgents) {
                if (user.getAgentId() != null && user.getAgentId().intValue() == sellSmokeAgent.getId()) {
                    user.setAgentName(sellSmokeAgent.getName());
                }
            }
        }
    }

}
