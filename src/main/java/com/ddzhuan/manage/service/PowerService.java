package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Power;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 17:30
 */
public interface PowerService {

    /**
     * 权限列表
     * @param power 条件
     * @param pagination 分页信息
     * @return
     */
    List<Power> getPowerList(Power power, Pagination pagination);

    /**
     * 获取权限信息
     * @param id
     * @return
     */
    Power getPowerById(Long id);

    /**
     * 新增权限
     * @param power 权限
     * @return
     */
    boolean insertPower(Power power);

    /**
     * 更新权限
     * @param power 权限
     * @return
     */
    boolean updatePower(Power power);


    /**
     *  删除权限
     * @param id
     * @return
     */
    BaseResultInfo delPowerById(Long id);

    /**
     * 权限加入用户
     * @param powerId 权限ID
     * @param userId  用户ID
     * @return
     */
    boolean addUserPower(Long userId,Long powerId,Long systemId);

    /**
     * 角色添加权限
     * @param powerId   权限ID
     * @param roleId    角色ID
     * @param systemId  系统ID
     * @return
     */
    boolean addRolePower(Long powerId, Long roleId, Long systemId);

    /**
     * 删除用户权限
     * @param userId    用户id
     * @param powerId   权限id
     * @return
     */
    boolean delUserPowerByUserIdAndPowerId(Long userId,Long powerId,Long systemId);

    /**
     * 删除角色权限
     * @param roleId    用户id
     * @param powerId   权限id
     * @return
     */
    boolean delRolePowerByUserIdAndPowerId(Long roleId,Long powerId);

    /**
     * 根据权限code查询权限
     * @param code
     * @return
     */
    int countPowerByCode(String code);

    /**
     * 给用户添加菜单
     * @param powerId 权限ID
     * @param menuIds 菜单组
     * @return
     */
    boolean insertPowerMenus(Long powerId,Long[] menuIds);

    /**
     * 根据菜单id查询权限Id
     * @param menuId 菜单id
     * @return 权限数
     */
    int getCountPowersByMenuId(Long menuId);

    /**
     * 根据系统id查询权限列表
     * @param systemId 系统id
     * @return 权限列表
     */
    List<Power> getPowersBySystemId(Long systemId);

    List<Long> getPowerIdsByUserId(Long userId,Long systemId);
}
