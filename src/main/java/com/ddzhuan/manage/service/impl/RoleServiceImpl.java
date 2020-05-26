package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.PowerTypeEnum;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.MenuDao;
import com.ddzhuan.manage.dao.PowerDao;
import com.ddzhuan.manage.dao.RoleDao;
import com.ddzhuan.manage.dao.UserDao;
import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.model.Power;
import com.ddzhuan.manage.model.Role;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.MenuService;
import com.ddzhuan.manage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:43
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PowerDao powerDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MenuService menuService;


    @Override
    public List<Role> queryRoleListByConditions(Role role, Pagination pagination) {
        int roleCount = roleDao.queryRoleCountByConditions(role);
        pagination.setTotal(roleCount);
        List<Role> roleList = roleDao.queryRoleListByConditions(role,pagination.getStart(),pagination.getEnd());
        return roleList;
    }

    @Override
    public Role queryRoleById(Long roleId,Long systemId) {
        List<Long> menuIds = new ArrayList<>();
        List<Long> powerIds = powerDao.queryPowerIdsByRoleIds(Arrays.asList(roleId), PowerTypeEnum.MENU.getCode());
        if (!CollectionUtils.isEmpty(powerIds)) {
            menuIds.addAll(menuDao.queryMenuIdsByPowerIds(systemId, powerIds));
        }
//        List<Menu> menus = null;
        if (!CollectionUtils.isEmpty(menuIds)) {
            menuIds =menuIds.stream().distinct().collect(Collectors.toList());
        }

        Role role = roleDao.queryRoleById(roleId);
        List<User> userList = userDao.getUserListByRoleIdV1(roleId);
        if (!CollectionUtils.isEmpty(userList)){
            role.setUsers(userList);
        }else {
            role.setUsers(new ArrayList<>());
        }

        List<Menu> menus = menuService.getMenuListByPidV2(0L,null,null,systemId);
        menus.sort((o1, o2) -> o1.getSno().compareTo(o2.getSno()));
        predicate(menus,menuIds);
        if (!CollectionUtils.isEmpty(menus)){
            role.setMenus(menus);
        }else {
            role.setMenus(new ArrayList<>());
        }


        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean inserRole(Role role) {
        return roleDao.insertRole(role) == 1;
    }

    @Override
    public boolean updateRole(Role role) {
        Assert.isTrue(role.getId() != null,"角色ID不能为空");
        return roleDao.updateRole(role) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addRoleMenus(Long roleId, List<Long> menuIds) {
        Assert.isTrue(roleId != null,"角色ID不能为空");
        menuDao.delRoleMenusByRoleId(roleId);
        roleDao.insertRoleMenus(roleId,menuIds);
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean addUserRole(Long roleId, Long userId) {
        Assert.isTrue(roleId != null,"角色ID不能为空");
        roleDao.insertUserRole(roleId,userId);
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delUserRole(Long roleId, Long userId) {
        Assert.isTrue(roleId != null,"角色ID不能为空");
        roleDao.delUserRole(roleId,userId);
        return Boolean.TRUE;
    }

    @Override
    public List<Role> queryRoleListBySystemId(Role role, Pagination pagination, Long systemId, Long powerId, int type) {
        List<Long> checkRoleIds = roleDao.queryRoleListBySystemId(systemId,powerId).stream().map(Role::getId).collect(Collectors.toList());
        List<Role> roleList = new ArrayList<>();
        if (type == 1) {
            if (CollectionUtils.isEmpty(checkRoleIds)) {
                return new ArrayList<>();
            }
            pagination.setTotal(checkRoleIds.size());
            roleList = roleDao.queryRoleListByRoleAndSystemId(role,systemId,checkRoleIds,pagination.getStart(),pagination.getEnd(),type);
        } else if (type == 2) {
            int total = roleDao.queryRoleCountByRoleAndSystemId(role, systemId, checkRoleIds);
            pagination.setTotal(total);
            roleList = roleDao.queryRoleListByRoleAndSystemId(role,systemId,checkRoleIds,pagination.getStart(),pagination.getEnd(),type);
        }
        return roleList;
    }

    @Override
    public Boolean getRoleByRoleCode(String code) {
        Role role = roleDao.queryRoleByRoleCode(code);
        if (Objects.isNull(role)){
            return Boolean.FALSE;
        }else {
            return Boolean.TRUE;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResultInfo delRoleById(Long roleId) {
        BaseResultInfo base = new BaseResultInfo();
        try {
            int userCount = roleDao.countUserByRoleId(roleId);
            if (0 < userCount){
                base.setMsg("该角色关联用户,暂不可删除");
                base.setSuccess(false);
                return base;
            }
            roleDao.delRolePower(roleId);
            base.setSuccess(roleDao.delRoleById(roleId)==1);
            base.setMsg("删除成功");
        }catch (Exception e){
            base.setSuccess(false);
            base.setMsg("删除失败");
        }
        return base;
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        return roleDao.queryRolesIdsByUserId(userId);
    }

    @Override
    public List<Long> getRoleIdsBySystemId(Long systemId) {
        return roleDao.queryRoleIdsBySystemId(systemId);
    }

    private void predicate(List<Menu> menuList , List<Long> checkMenus){

        menuList.forEach(v->{
            if (checkMenus.contains(v.getId())){
                v.setSelected(true);
            }else{
                v.setSelected(false);
            }
            if (!CollectionUtils.isEmpty(v.getChildMenus())){
                predicate(v.getChildMenus(),checkMenus);
            }
        });
    }
}
