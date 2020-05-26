package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.SellSmokeAgent;
import com.ddzhuan.manage.model.SellSmokeInstall;
import com.ddzhuan.manage.model.SellSmokeTerminal;
import com.ddzhuan.manage.model.User;

import java.util.List;

/**
 * @author Mr Liu
 * @date 2019/9/30
 * desc
 */
public interface SellSmokeService {

    /**
     * 新增/编辑 运营商 (二合一)
     * @param sellSmokeAgent 运营商
     */
    void saveAgent(SellSmokeAgent sellSmokeAgent);

    /**
     * 删除运营商
     * @param sellSmokeAgent 运营商
     * @return
     */
    Boolean removeAgent(SellSmokeAgent sellSmokeAgent);

    /**
     * 运营商列表查询
     * @param sellSmokeAgent 查询条件
     * @param pagination 分页属性
     * @return
     */
    List<SellSmokeAgent> getSellSmokeAgentList(SellSmokeAgent sellSmokeAgent, Pagination pagination);

    /**
     * 运营商 - 通过id查询记录
     * @param id id
     * @return
     */
    SellSmokeAgent getSellAgentById(Long id);


    /**
     * 通过id查询记录
     * @param id id
     * @return
     */
    SellSmokeInstall getSellSmokeById(Long id);

    /**
     * 电子烟设备安装保存记录
     *
     * @param sellSmokeInstall 电子烟设备安装信息
     */
    void saveSellSmokeInstall(SellSmokeInstall sellSmokeInstall);

    /**
     * 电子烟设备安装记录
     *
     * @param sellSmokeInstall 查询条件
     * @param pagination       分页属性
     * @return 安装记录列表
     */
    List<SellSmokeInstall> getSellSmokeInstallList(SellSmokeInstall sellSmokeInstall, Long userId, Pagination pagination);

    /**
     * 更新
     *
     * @param sellSmokeInstall 设备安装信息
     */
    void updateSellSmokeInstall(SellSmokeInstall sellSmokeInstall);

    /**
     * 删除
     * @param id 安装信息id
     */
    void removeSellSmokeInstall(Long id);

    /**
     * 根据安装信息id查询安装设备列表
     * @param installId 安装信息id
     * @return 列表
     */
    List<SellSmokeTerminal> getSellSmokeTerminalList(Long installId);

    /**
     * 修改安装设备信息
     * @param sellSmokeTerminal 安装设备信息
     */
    void updateSellSmokeTerminal(SellSmokeTerminal sellSmokeTerminal);

    /**
     * 删除安装设备信息
     * @param installId 安装id
     */
    void removeSellSmokeTerminal(Long installId);

    /**
     * 更新安装信息和安装设备
     * @param sellSmokeInstall 安装信息
     * @param sellSmokeTerminals 安装设备
     */
    void updateInstallAndTerminal(SellSmokeInstall sellSmokeInstall, List<SellSmokeTerminal> sellSmokeTerminals);

    /**
     * 取消安装
     * @param sellSmokeInstall 安装信息
     */
    void cancelSellSmokeInstall(SellSmokeInstall sellSmokeInstall);

    /**
     * 保存代理商人员
     * @param user user
     * @param systemId systemId
     */
    void saveAgentUser(User user, Long systemId);

    /**
     * 删除代理商人员
     * @param user user
     * @param systemId systemId
     */
    void removeAgentUser(User user, Long systemId);

    /**
     * 撤回安装
     * @param sellSmokeInstall 安装信息
     */
    void revertSellSmokeInstall(SellSmokeInstall sellSmokeInstall);

    /**
     * 根据用户id查询安装记录，包括申请人、处理人、安装人
     * @param userId 用户id
     * @return 安装信列表
     */
    List<SellSmokeInstall> getSellSmokeInstallListByUserId(Long userId);

}
