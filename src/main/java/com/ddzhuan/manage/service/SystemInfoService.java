package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.SystemInfo;

import java.util.List;
import java.util.Map;

/**
 * 系统管理接口
 *
 * @author likeke
 * @date 2019/06/24
 */
public interface SystemInfoService {

    /**
     * 新增
     * @param systemInfo systemInfo
     */
    void saveSystemInfo(SystemInfo systemInfo);

    /**
     * 修改
     * @param systemInfo systemInfo
     */
    void modifySystemInfo(SystemInfo systemInfo);

    /**
     * 条件查询系统列表
     * @param systemInfo 系统信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pagination 分页
     * @return  列表
     */
    List<SystemInfo> getSystemInfoListByConditions(SystemInfo systemInfo, String startTime, String endTime, Pagination pagination);

    /**
     * 根据id获取system
     * @param id 系统id
     * @return system
     */
    SystemInfo getSystemInfoById(Long id);

    /**
     * 根据name获取system
     * @param name 系统名称
     * @return system
     */
    Integer getSystemInfoCountByName(String name);

    /**
     * 通过系统ids，批量查询系统信息
     * @param ids 系统ids
     * @return systemInfos
     */
    List<SystemInfo> getSystemInfosByIds(List<Long> ids);

    /**
     * 添加系统用户
     * @param userId 用户id
     * @param systemId 系统id
     */
    void saveSystemUser(Long userId, Long systemId);

    /**
     * 移除系统用户
     * @param userId 用户id
     * @param systemId 系统id
     */
    void removeSystemUser(Long userId, Long systemId);

    /**
     * 根据条件查询系统列表
     * @param systemInfo 条件
     * @return 系统列表
     */
    Map<Long,String> querySystemList(SystemInfo systemInfo);

}
