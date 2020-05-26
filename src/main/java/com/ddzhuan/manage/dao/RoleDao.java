package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:36
 */
public interface RoleDao {

    /**
     * 新增角色
     *
     * @param role 角色
     * @return
     */
    int insertRole(@Param("role") Role role);

    /**
     * 分页查询角色列表
     *
     * @param role  条件
     * @param start 起始页
     * @param size  每页条数
     * @return
     */
    List<Role> queryRoleListByConditions(@Param("role") Role role,
                                         @Param("start") Integer start,
                                         @Param("size") Integer size);

    int queryRoleCountByConditions(@Param("role") Role role);

    int updateRole(@Param("role") Role role);

    Role queryRoleById(Long roleId);

    void insertRoleMenus(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);

    int updateRolePowerByIds(@Param("ids") Long ids, @Param("powerIds") List<Long> powerIds);

    void insertUserRole(@Param("roleId") Long roleId, @Param("userId") Long userId);

    void delUserRole(@Param("roleId") Long roleId, @Param("userId") Long userId);

    /**
     * 通过用户id获取角色ids
     *
     * @param userId 用户id
     * @return 角色ids
     */
    List<Long> queryRolesIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据系统id和权限id查询角色列表
     *
     * @param systemId 系统id
     * @param powerId  权限id
     * @return
     */
    List<Role> queryRoleListBySystemId(@Param("systemId") Long systemId, @Param("powerId") Long powerId);

    /**
     * 查询没有该权限的角色列表
     * @param role
     * @param systemId
     * @param checkedRoleIds
     * @param type 1、已选 2、未选
     * @return
     */
    List<Role> queryRoleListByRoleAndSystemId(@Param("role") Role role,
                                              @Param("systemId") Long systemId,
                                              @Param("checkedRoleIds") List<Long> checkedRoleIds,
                                              @Param("start") Integer start,
                                              @Param("size") Integer size,
                                              @Param("type") Integer type);

    int queryRoleCountByRoleAndSystemId(@Param("role") Role role,
                                        @Param("systemId") Long systemId,
                                        @Param("checkedRoleIds") List<Long> checkedRoleIds);

    /**
     * 根据角色编码查询角色信息
     * @param code 角色唯一编码
     * @return
     */
    Role queryRoleByRoleCode(String code);


    /**
     * 删除角色
     * @param roleId 角色ID
     * @return
     */
    int delRoleById(Long roleId);

    /**
     * 根据角色id查询该角色下有多少用户
     * @param roleId 角色id
     * @return
     */
    int countUserByRoleId(Long roleId);

    /**
     * 查询该用户拥有哪些角色
     * @param userId 用户ID
     * @return
     */
    List<Role> queryRoleListByUserId(Long userId);

    /**
     * 根据角色系统id获取角色ids
     * @param systemId 系统ids
     * @return 角色ids
     */
    List<Long> queryRoleIdsBySystemId(Long systemId);

    /**
     * 删除角色与权限的关联关系
     * @param roleId 角色ID
     * @return
     */
    int delRolePower(Long roleId);
}
