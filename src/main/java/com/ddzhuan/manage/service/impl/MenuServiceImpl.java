package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.enums.PowerTypeEnum;
import com.ddzhuan.manage.dao.*;
import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单实现类
 *
 * @author likeke
 * @date 2019/07/02
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PowerDao powerDao;

    @Autowired
    private SystemInfoDao systemInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMenu(Menu menu) {
        if (menu.getId() != null && menu.getId().intValue() > 0) {
            // 修改
            Integer maxSno = menuDao.queryMaxSnoBySystemId(menu.getPid(), menu.getSystemId());
            if (maxSno == null) {
                maxSno = 0;
            }
            if (menu.getSno() != null && menu.getSno().intValue() > maxSno) {
                menu.setSno(maxSno);
            }
            Menu oMenu = menuDao.queryMenuById(menu.getId());
            if (menu.getSno().intValue() > oMenu.getSno().intValue()) {
                menuDao.updateSnoMoveDown(menu.getPid(), menu.getSno(), oMenu.getSno(), menu.getSystemId());
            } else if (menu.getSno().intValue() < oMenu.getSno().intValue()) {
                menuDao.updateSnoMoveUp(menu.getPid(), menu.getSno(), oMenu.getSno(), menu.getSystemId());
            }
            menuDao.updateMenu(menu);
        } else {
            // 新增
            Integer maxSno = menuDao.queryMaxSnoBySystemId(menu.getPid(), menu.getSystemId());
            if (maxSno == null) {
                maxSno = 0;
            }
            if (menu.getSno() != null && menu.getSno().intValue() > maxSno) {
                menu.setSno(maxSno + 1);
                menuDao.insertMenu(menu);
            } else if (menu.getSno() != null && menu.getSno().intValue() <= maxSno) {
                menuDao.updateSno(menu);
                menuDao.insertMenu(menu);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenuById(Long id) {
        Menu oMenu = menuDao.queryMenuById(id);
        menuDao.deleteSno(oMenu);
        menuDao.deleteMenu(id);
    }

    @Override
    public Menu getMenuById(Long id) {
        Assert.isTrue(id != null , "id不能为null");
        return menuDao.queryMenuById(id);
    }

    @Override
    public Integer getMenuCountByCode(Menu menu) {
        if (menu != null && menu.getCode() == null) {
            return null;
        }
        return menuDao.queryMenuCountByCode(menu);
    }

    @Override
    public List<Menu> getMenuListByPid(Long systemId, Long pid, Long userId) {
        if (userId == null) {
            return menuDao.queryMenuListByPidV2(pid, systemId);
        }
        List<Long> menuIds = new ArrayList<>();
        // 查询是否是系统用户
        int count = systemInfoDao.queryCountBySysIdAndUId(systemId, userId);
        if (count == 0) {
            return null;
        }
        // 如果拥有该系统的系统管理员权限，所有菜单全部获取
        List<Long> powerIdList = powerDao.queryPowerIdsByUserId(systemId, userId, PowerTypeEnum.SYSTEMMANAGE.getCode());
        if (!CollectionUtils.isEmpty(powerIdList)) {
            List<Long> ids = menuDao.queryMenuIdsBySystemId(systemId);
            List<Menu> menuList = null;
            if (!CollectionUtils.isEmpty(ids)) {
                menuList = menuDao.queryMenuListByPid(pid, ids);
                menuList.sort((o1, o2) -> o1.getSno().compareTo(o2.getSno()));
                return menuList;
            }
        }
        // 通过用户查找角色，通过角色查询出所有的菜单id
        List<Long> roleIds = roleDao.queryRolesIdsByUserId(userId);
//        if (!CollectionUtils.isEmpty(roleIds)) {
//            menuIds.addAll(menuDao.queryMenuIdsByRoleIds(systemId, roleIds));
//        }
//        // 通过用户查找菜单 查询出所有的菜单id
//        menuIds.addAll(menuDao.queryMenuIdsByUserId(systemId, userId));
        // 通过用户关联权限 权限关联菜单 查询出所有的菜单id
        List<Long> powerIds = powerDao.queryPowerIdsByUserId(systemId, userId, PowerTypeEnum.MENU.getCode());
        if (!CollectionUtils.isEmpty(powerIds)) {
            menuIds.addAll(menuDao.queryMenuIdsByPowerIds(systemId, powerIds));
        }
        // 通过用户关联角色 角色关联权限，权限关联菜单 查询出所有的菜单id
        if (!CollectionUtils.isEmpty(roleIds)) {
            powerIds = powerDao.queryPowerIdsByRoleIds(roleIds, PowerTypeEnum.MENU.getCode());
            if (!CollectionUtils.isEmpty(powerIds)) {
                menuIds.addAll(menuDao.queryMenuIdsByPowerIds(systemId, powerIds));
            }
        }
        if (CollectionUtils.isEmpty(menuIds)) {
            return null;
        }
        menuIds =menuIds.stream().distinct().collect(Collectors.toList());

        List<Menu> menus = menuDao.queryMenuListByPid(pid, menuIds);
        if (menus == null) {
            menus = new ArrayList<>();
        }
        menus.sort((o1, o2) -> o1.getSno().compareTo(o2.getSno()));
        return menus;
    }

    @Override
    public List<Menu> getMenuListByPidV2(Long pid,Long roleId,Long userId, Long systemId) {

        List<Long> ids = new ArrayList<>();

        if (Objects.nonNull(userId)){
            ids = userDao.getuserMenuIdListByUserId(userId);
        }

        List<Menu> menuList = getMenuList(pid, systemId);
        if (!CollectionUtils.isEmpty(menuList) && !CollectionUtils.isEmpty(ids)){
            //递归选中
            predicate(menuList,ids);
        }
        for (int i = menuList.size() - 1; i >= 0; i--) {
            if (menuList.get(i).getSystemId().intValue() != systemId.intValue()) {
                menuList.remove(i);
            }
        }
        return  menuList;
    }

    @Override
    public List<Menu> getMenuListByPower(Long pid, Long powerId, Long systemId) {

        List<Long> ids = new ArrayList<>();
        if (Objects.nonNull(powerId)){
            ids = menuDao.queryMenuIdListByPowerId(powerId, systemId);
        }
        List<Menu> menuList = getMenuList(pid, systemId);
        if (!CollectionUtils.isEmpty(menuList)){
            //递归选中
            predicate(menuList,ids);
        }
        return  menuList;
    }

    @Override
    public List<Menu> getMenusByUserId(Long userId, Long systemId) {
        List<Long> menuIds = new ArrayList<>();
        // 查询是否是系统用户
        int count = systemInfoDao.queryCountBySysIdAndUId(systemId, userId);
        if (count == 0) {
            return null;
        }
        // 如果拥有该系统的系统管理员权限，所有菜单全部获取
        List<Long> powerIdList = powerDao.queryPowerIdsByUserId(systemId, userId, PowerTypeEnum.SYSTEMMANAGE.getCode());
        if (!CollectionUtils.isEmpty(powerIdList)) {
            List<Long> ids = menuDao.queryMenuIdsBySystemId(systemId);
            List<Menu> menuList = null;
            if (!CollectionUtils.isEmpty(ids)) {
                menuList = menuDao.queryMenuListByPid(null, ids);
            }
            menuList.sort(Comparator.comparing(Menu::getSno));
            return menuList;
        }
        // 通过用户查找角色，通过角色查询出所有的菜单id
        List<Long> roleIds = roleDao.queryRolesIdsByUserId(userId);
//        if (!CollectionUtils.isEmpty(roleIds)) {
//            menuIds.addAll(menuDao.queryMenuIdsByRoleIds(systemId, roleIds));
//        }
//        // 通过用户查找菜单 查询出所有的菜单id
//        menuIds.addAll(menuDao.queryMenuIdsByUserId(systemId, userId));
        // 通过用户关联权限 权限关联菜单 查询出所有的菜单id
        List<Long> powerIds = powerDao.queryPowerIdsByUserId(systemId, userId, PowerTypeEnum.MENU.getCode());
        if (!CollectionUtils.isEmpty(powerIds)) {
            menuIds.addAll(menuDao.queryMenuIdsByPowerIds(systemId, powerIds));
        }
        // 通过用户关联角色 角色关联权限，权限关联菜单 查询出所有的菜单id
        if (!CollectionUtils.isEmpty(roleIds)) {
            powerIds = powerDao.queryPowerIdsByRoleIds(roleIds, PowerTypeEnum.MENU.getCode());
            if (!CollectionUtils.isEmpty(powerIds)) {
                menuIds.addAll(menuDao.queryMenuIdsByPowerIds(systemId, powerIds));
            }
        }
        if (CollectionUtils.isEmpty(menuIds)) {
            return null;
        }
        menuIds =menuIds.stream().distinct().collect(Collectors.toList());
        // 通过菜单ids 查询出菜单列表
        List<Menu> menus = menuDao.queryMenuListByPid(null, menuIds);
        if (menus == null) {
            return new ArrayList<>();
        }
        menus.sort(Comparator.comparing(Menu::getSno));
        return menus;
    }

    @Override
    public List<Long> getMenuListByPidAndPowerIdV3(Long powerId) {

        return menuDao.queryMenuListByPidV3(powerId);
    }

    @Override
    public List<Long> getMenuIdsBySystemId(Long systemId) {
        return menuDao.queryMenuIdsBySystemId(systemId);
    }

    @Override
    public List<Long> getMenuIdsByPowerId(Long powerId, Long systemId) {
        return menuDao.queryMenuIdsByPowerIds(powerId, Arrays.asList(systemId));
    }

    private List<Menu> getMenuList(Long pid, Long systemId){
        Menu parentMenu = getMenuById(pid);
        List<Menu> parentMenus = menuDao.queryMenuListByPidV2(pid, systemId);

        if (!CollectionUtils.isEmpty(parentMenus)){
            parentMenus.forEach(v->{
                List<Menu> childMenus = getMenuList(v.getId(), systemId);
                v.setChildMenus(childMenus);
            });
        }
        if (Objects.nonNull(parentMenu)){
            parentMenu.setChildMenus(parentMenus);
        }
        return parentMenus;
    }


    public void predicate(List<Menu> menuList , List<Long> checkMenus){

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
