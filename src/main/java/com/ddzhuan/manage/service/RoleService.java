package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.Role;
import com.ddzhuan.manage.model.User;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:44
 */
public interface RoleService {

    /**
     * 分页查询角色列表
     * @param role 查询条件，role对象
     * @param pagination 分页信息
     * @return 角色列表
     */
    List<Role> queryRoleListByConditions(Role role, Pagination pagination);

    /**
     * 根据id查询角色信息
     * @param roleId 角色id
     * @return 角色信息
     */
    Role  queryRoleById(Long roleId,Long systemId);

    /**
     * 新增角色
     * @param role 角色对象
     * @return 是否新增成功
     */
    boolean inserRole(Role role);

    /**
     * 更新角色信息
     * @param role 角色对象
     * @return 是否更新成功
     */
    boolean updateRole(Role role);

    /**
     * 角色赋权
     * @param roleId 角色id
     * @param powerIds 权限id集合
     * @return
     */
    Boolean addRoleMenus(Long roleId, List<Long> powerIds);


    /**
     * 角色加入用户
     * @param roleId 角色ID
     * @param userId 用户ID集合
     * @return
     */
    Boolean addUserRole(Long roleId ,Long userId);

    /**
     * 删除用户角色
     * @param roleId 角色id
     * @param userId 角色id
     * @return
     */
    Boolean delUserRole(Long roleId ,Long userId);


    /**
     * 根据系统id和权限id查询角色列表
     * @param role 条件
     * @param pagination 分页信息
     * @param systemId 系统id
     * @param powerId   权限id
     * @return
     */
    List<Role> queryRoleListBySystemId(Role role, Pagination pagination, Long systemId, Long powerId, int type);


    /**
     * 根据角色Code查询角色是否存在
     * @param code 角色唯一编码
     * @return
     */
    Boolean getRoleByRoleCode(String code);

    /**
     * 删除角色
     * @param roleId 角色ID
     * @return
     */
    BaseResultInfo delRoleById(Long roleId);

    /**
     * 根据userId查询角色id列表
     * @param userId 用户id
     * @return 角色ids
     */
    List<Long> getRoleIdsByUserId(Long userId);

    /**
     * 根据角色系统id获取角色ids
     * @param systemId 系统ids
     * @return 角色ids
     */
    List<Long> getRoleIdsBySystemId(Long systemId);

}
