package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理Dao
 *
 * @author likeke
 * @date 2019/07/02
 */
public interface MenuDao {

    /**
     * 通过id查询菜单
     * @param id 菜单id
     * @return 菜单
     */
    Menu queryMenuById(@Param("id") Long id);

    /**
     * 获取在数据库中存在的个数，校验唯一验证码
     * @param menu menu
     * @return count
     */
    Integer queryMenuCountByCode(@Param("menu") Menu menu);

    /**
     * 增加菜单
     * @param menu 菜单
     */
    void insertMenu(Menu menu);

    /**
     * 修改序号
     * @param menu 菜单
     */
    void updateSno(Menu menu);

    /**
     * 修改菜单
     * @param menu 菜单
     */
    void updateMenu(Menu menu);

    /**
     * 删除序号
     * @param menu 菜单
     */
    void deleteSno(Menu menu);

    /**
     * 删除菜单（逻辑删除）
     * @param id 菜单id
     */
    void deleteMenu(Long id);

    /**
     * 修改当前父级目录下子目录序号+1
     * @param pid 父级id
     * @param newSno 新序号
     * @param oldSno 旧序号
     * @param systemId 系统id
     */
    void updateSnoMoveUp(@Param(value = "pid") Long pid, @Param(value = "newSno") Integer newSno,
                         @Param(value = "oldSno") Integer oldSno, @Param("systemId") Long systemId);

    /**
     * 修改当前父级目录下子目录序号-1
     * @param pid 父级id
     * @param newSno 新序号
     * @param oldSno 旧序号
     * @param systemId 系统id
     */
    void updateSnoMoveDown(@Param(value = "pid") Long pid, @Param(value = "newSno") Integer newSno,
                           @Param(value = "oldSno") Integer oldSno, @Param(value = "systemId") Long systemId);

    /**
     * 通过pid和ids获取有有权限的父级菜单
     * @param pid 父id
     * @param ids 角色id
     * @return 菜单列表
     */
    List<Menu> queryMenuListByPid(@Param("pid") Long pid, @Param("ids") List<Long> ids);

    /**
     * 通过Pid查询菜单树
     * @param pid 父级id
     * @param systemId 系统id
     * @return
     */
    List<Menu> queryMenuListByPidV2(@Param("pid") Long pid, @Param("systemId") Long systemId);

    /**
     * 根据权限ID查询菜单
     * @param powerId 权限id
     * @param systemId 系统id
     * @return
     */
    List<Long> queryMenuIdListByPowerId(@Param("powerId") Long powerId, @Param("systemId") Long systemId);

    /**
     * 根据角色id 删除该角色下的菜单
     * @param roleId 角色id
     * @return
     */
    int delRoleMenusByRoleId(Long roleId);

    /**
     * 删除角色菜单关联表记录
     * @param menuId 菜单id
     */
    void deleteRoleMenu(@Param("menuId") Long menuId);

    /**
     * 删除用户菜单关联表记录
     * @param menuId 菜单id
     */
    void deleteUserMenu(@Param("menuId") Long menuId);

    /**
     * 通过权限ids获取所有的菜单ids
     * @param systemId 系统id
     * @param powerIds 权限ids
     * @return 菜单ids
     */
    List<Long> queryMenuIdsByPowerIds(@Param("systemId") Long systemId, @Param("powerIds") List<Long> powerIds);

    /**
     * 通过系统查询出该系统下所有的菜单
     * @param systemId 系统id
     * @return 菜单ids
     */
    List<Long> queryMenuIdsBySystemId(@Param("systemId") Long systemId);


    /**
     * 通过Pid查询菜单树
     * @param powerId 父级id
     * @return
     */
    List<Long> queryMenuListByPidV3(Long powerId);

    /**
     * 查询菜单序号最大值
     * @param pid 父级菜单id
     * @param systemId 系统id
     * @return 菜单序号最大值
     */
    Integer queryMaxSnoBySystemId(@Param("pid") Long pid, @Param("systemId") Long systemId);
}
