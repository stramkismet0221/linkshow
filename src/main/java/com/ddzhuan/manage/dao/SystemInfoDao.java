package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.SystemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统管理Dao
 *
 * @author likeke
 * @date 2019/06/24
 */
public interface SystemInfoDao {

    /**
     * 新增
     * @param systemInfo systemInfo
     */
    void insertSystemInfo(@Param("systemInfo") SystemInfo systemInfo);

    /**
     * 修改
     * @param systemInfo systemInfo
     */
    void updateSystemInfo(@Param("systemInfo") SystemInfo systemInfo);

    /**
     * 条件查询系统列表
     * @param systemInfo 查询条件
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param page 当前页
     * @param rows 每页数量
     * @return  列表
     * @throws Exception
     */
    List<SystemInfo> querySystemInfoListByConditions(@Param("systemInfo") SystemInfo systemInfo,
                                                     @Param("startTime") String startTime, @Param("endTime") String endTime,
                                                     @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * 总记录数
     * @param systemInfo systemInfo
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 总记录数
     * @throws Exception
     */
    Integer querySystemInfoCountByConditions(@Param("systemInfo") SystemInfo systemInfo, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 根据id查询system
     * @param id 系统id
     * @return system
     */
    SystemInfo findSystemInfoById(@Param("id") Long id);

    /**
     * 根据id查询system
     * @param name 系统名称
     * @return system
     */
    Integer querySystemInfoCountByName(@Param("name") String name);

    /**
     * 根据系统ids查询系统列表
     * @param ids 系统ids
     * @return systemInfos
     */
    List<SystemInfo> querySystemInfosByIds(@Param("ids") List<Long> ids);

    /**
     * 添加系统用户
     * @param userId 用户id
     * @param systemId 系统id
     */
    void insertSystemUser(@Param("userId") Long userId, @Param("systemId") Long systemId);

    /**
     * 移除系统用户
     * @param userId 用户id
     * @param systemId 系统id
     */
    void deleteSystemUser(@Param("userId") Long userId, @Param("systemId") Long systemId);

    /**
     * 查询是否是系统用户
     * @param systemId 系统id
     * @param userId 用户id
     * @return >0：是 ==0：否
     */
    int queryCountBySysIdAndUId(@Param("systemId") Long systemId, @Param("userId") Long userId);

    /**
     * 根据条件查询系统列表
     * @param systemInfo 条件
     * @return
     */
    List<SystemInfo> querySystemList(SystemInfo systemInfo);

    /**
     * 查询该用户属于哪个系统
     * @param userId
     */
    List<SystemInfo> querySystemByUserId(Long userId);
}
