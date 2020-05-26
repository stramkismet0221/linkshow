package com.ddzhuan.manage.service;


import com.ddzhuan.manage.model.Menu;

import java.util.List;

/**
 * 菜单接口
 *
 * @author likeke
 * @date 2019/07/01
 */
public interface MenuService {

    /**
     * 保存
     * @param menu 菜单
     */
    void saveMenu(Menu menu);

    /**
     * 通过id删除菜单
     * @param id 菜单id
     */
    void deleteMenuById(Long id);

    /**
     * 通过id获取菜单
     * @param id 菜单id
     * @return 菜单
     */
    Menu getMenuById(Long id);

    /**
     * 通过菜单属性获取数据库中已存在的菜单个数
     * @param menu menu
     * @return count
     */
    Integer getMenuCountByCode(Menu menu);

    /**
     * 通过pid和roleId获取有权限的父级菜单
     * @param systemId 系统id
     * @param pid 父id
     * @param userId 用户id
     * @return 菜单列表
     */
    List<Menu> getMenuListByPid(Long systemId, Long pid, Long userId);

    /**
     * 递归查询菜单树
     * @param pid 父级id
     * @param roleId 角色id 判断该角色是否拥有该某个菜单
     * @param userId 用户Id 判断该用户是否拥有某个菜单
     * @param systemId 系统id
     * @return
     */
    List<Menu> getMenuListByPidV2(Long pid,Long roleId,Long userId, Long systemId);

    /**
     * 递归查询菜单树
     * @param pid 父级id
     * @param powerId 权限id 判断该角色是否拥有该某个菜单
     * @return
     */
    List<Menu> getMenuListByPower(Long pid, Long powerId, Long systemId);

    /**
     * 通过userId、systemId查询所有有权限的菜单列表
     * @param userId 用户id
     * @param systemId 系统id
     * @return 菜单列表
     */
    List<Menu> getMenusByUserId(Long userId, Long systemId);

    /**
     * 查询改权限下有哪些菜单
     * @param powerId 权限id
     * @return
     */
    List<Long> getMenuListByPidAndPowerIdV3(Long powerId);

    /**
     *
     * @param systemId 系统id
     * @return 菜单ids
     */
    List<Long> getMenuIdsBySystemId(Long systemId);

    /**
     * @param systemId 系统id
     * @param powerId 权限id
     * @return 菜单ids
     */
    List<Long> getMenuIdsByPowerId(Long systemId, Long powerId);

}
