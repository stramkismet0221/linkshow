package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.YpTerminal;

import java.util.List;

/**
 * 友朋设备接口
 *
 * @author likeke
 * @date 2019/06/20
 */
public interface YpTerminalService {

    /**
     * 批量添加设备接口
     * @param ypTerminals 设备列表
     */
    void batchSaveYpTerminal(List<YpTerminal> ypTerminals);

    /**
     * 分页查询设备列表
     * @param terminal  设备信息
     * @param keyword 关键字搜索
     * @param pagination  分页
     * @return          友朋设备列表
     */
    List<YpTerminal> getYpTerminalListByConditions(YpTerminal terminal, String keyword, Pagination pagination);

    /**
     * 获取所有设备列表
     * @return
     */
    List<YpTerminal> getAllYpTerminalList();

    /**
     * 根据ids获取设备列表
     * @param terminalIds
     * @return
     */
    List<YpTerminal> getYpTerminalListByIds(List<String> terminalIds);

    /**
     * 根据userId获取已选和未选设备列表
     *
     * @param ypTerminal 设备查询信息
     * @param pagination 分页参数
     * @param userId 用户id
     * @param type 1：已选 2：未选
     * @return
     */
    List<YpTerminal> getYpTerminalListByUserId(YpTerminal ypTerminal, Long userId, int type, Pagination pagination);

    /**
     * 新增用户设备关系
     * @param userId 用户id
     * @param terminalId 设备id
     */
    void addUserTerminal(Long userId, String terminalId);

    /**
     * 删除用户设备关系
     * @param userId 用户id
     * @param terminalId 设备id
     */
    void delUserTerminal(Long userId, String terminalId);

    /**
     * 根据userId获取所拥有设备code列表
     * @param userId 用户id
     * @return userId 列表
     */
    List<String> getTerminalIdsByUserId(Long userId);
}
