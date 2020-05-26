package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.Power;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:36
 */
public interface PowerDao {


    /**
     * 新增权限
     *
     * @param power 权限
     * @return
     */
    int insertPower(@Param("power") Power power);

    /**
     * 查询所有权限
     *
     * @param power 条件
     * @param start 起始页
     * @param size  条数
     * @return
     */
    List<Power> queryAllPowerList(@Param("power") Power power,
                                  @Param("start") Integer start,
                                  @Param("size") Integer size);

    @Deprecated
    List<Power> queryPowerListByRoleId(@Param("roleId") Long roleId,
                                       @Param("powerId") Long powerId);


    /**
     * 查询权限总条数
     *
     * @param power 条件
     * @return
     */
    int queryPowerCountByConditions(@Param("power") Power power);

    /**
     * 根据ID查询权限信息
     *
     * @param id 权限id
     * @return
     */
    Power queryPowerById(Long id);

    /**
     * 更新权限
     *
     * @param power 权限
     * @return
     */
    int updatePower(Power power);

    /**
     * 通过roleIds、powerType获取powerIds
     *
     * @param roleIds   角色Ids
     * @param powerType 权限类型
     * @return 权限ids
     */
    List<Long> queryPowerIdsByRoleIds(@Param("roleIds") List<Long> roleIds,
                                      @Param("powerType") Integer powerType);

    /**
     * 根据角色id查询权限
     *
     * @param roleId 角色id
     * @return
     */
    List<Power> queryPowerByRoleId(Long roleId);

    /**
     * 删除角色权限
     *
     * @param roleId 角色id
     */
    void delRolePowerByRoleId(Long roleId);

    /**
     * 新增用户权限
     *
     * @param powerId 权限ID
     * @param userId  用户ID
     * @return
     */
    int insertUserPower(@Param("userId") Long userId,
                        @Param("powerId") Long powerId,
                        @Param("systemId") Long systemId);

    /**
     * 删除用户权限
     *
     * @param userId  用户ID
     * @param powerId 权限ID
     * @return
     */
    int delUserPowerByUserIdAndPowerId(@Param("userId") Long userId,
                                       @Param("powerId") Long powerId,
                                       @Param("systemId") Long systemId);

    /**
     * 添加角色权限
     *
     * @param powerId 权限ID
     * @param roleId  角色ID
     * @return
     */
    int insertRolePower(@Param("powerId") Long powerId,
                        @Param("roleId") Long roleId,
                        @Param("systemId") Long systemId);

    List<Long> queryPowerIdsByUserId(@Param("systemId") Long systemId,
                                     @Param("userId") Long userId,
                                     @Param("powerType") Integer powerType);

    /**
     * 删除角色权限
     *
     * @param powerId 权限ID
     * @param roleId  角色ID
     * @return
     */
    int delRolePowerByUserIdAndPowerId(@Param("powerId") Long powerId,
                                       @Param("roleId") Long roleId);

    /**
     * 删除权限
     *
     * @param id 权限ID
     * @return
     */
    int delPowerById(Long id);

    /**
     * 查询该角色拥有权限的数量
     *
     * @param roleId 角色
     * @return
     */
    int countPowerByRoleId(Long roleId);

    int countPowerByCode(String code);

    int delPowerMenusByPowerId(Long powerId);

    int insertPowerMenus(@Param("powerId") Long powerId, @Param("menuIds") List<Long> menuIds);

    /**
     * 查询改权限下有多少用户
     * @param powerId 权限ID
     * @return
     */
    int countUserByPowerId(Long powerId);

    /**
     * 查询改权限下有多少角色
     * @param id 权限ID
     * @return
     */
    int countRoleByPowerId(Long id);

    /**
     * 根据菜单id查询权限Id
     * @param menuId 菜单id
     * @return 权限数
     */
    int queryCountPowersByMenuId(@Param("menuId") Long menuId);

    /**
     * 根据系统id查询权限列表
     * @param systemId 系统id
     * @return 权限列表
     */
    List<Power> queryPowersBySystemId(@Param("systemId") Long systemId);
}
