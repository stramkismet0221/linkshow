package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.PowerTypeEnum;
import com.ddzhuan.manage.dao.*;
import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.model.Role;
import com.ddzhuan.manage.model.SystemInfo;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.MenuService;
import com.ddzhuan.manage.service.UserService;
import com.ddzhuan.manage.tool.MD5Tool;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:37
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SystemInfoDao systemInfoDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PowerDao powerDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuService menuService;
    @Override
    public User login(String userName, String password, Long systemId) {
        User user = userDao.userLogin(userName, password);
        if (user != null && systemId != null) {
            int count = systemInfoDao.queryCountBySysIdAndUId(systemId, user.getId());
            if (count > 0) {
                return user;
            } else {
                return null;
            }
        }
        return user;
    }

    @Override
    public List<User> queryUserListByConditions(User user, Pagination pagination) {
        Integer userCount = userDao.queryUserCountByConditions(user);
        pagination.setTotal(userCount);
        List<User> users = userDao.queryUserListByConditions(user,pagination.getStart(),pagination.getEnd());
        return users;
    }

    @Override
    public boolean insertUser(User user) {
        Assert.isTrue(Objects.nonNull(user.getUserName()),"用户名不能为空");
        Assert.isTrue(Objects.nonNull(user.getRealName()),"用户姓名不能为空");
        Assert.isTrue(Objects.nonNull(user.getMobile()),"用户手机号不能为空");
        return userDao.insertUser(user) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserByConditions(User user) {
        Assert.isTrue(user.getId() != null,"用户id不能为空");
        return userDao.updateUserByUser(user) == 1;
    }

    @Override
    public List<User> getUserListByRoleId(User user, Long roleId, Long systemId, Pagination pagination) {
        pagination.setTotal(userDao.queryUserCountByRoleId(user,roleId,systemId,1));
        List<User> userList = userDao.getUserListByRoleId(user, roleId, systemId, pagination.getStart(), pagination.getEnd());
        return userList;
    }

    @Override
    public List<User> queryUnCheckUser(User user,Long roleId,Long systemId,Pagination pagination) {
        pagination.setTotal(userDao.queryUserCountByRoleId(user,roleId,systemId,2));
        List<User> userList = userDao.queryUnCheckUser(user,pagination.getStart(),pagination.getEnd(),roleId,systemId);
        return userList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUserMenus(Long userId, List<Long> menuIds) {
        Assert.isTrue(userId!=null,"用户ID不能为空");
        userDao.delUserMenusByUserId(userId);
        return userDao.insertUserMenus(userId,menuIds)>0;
    }

    @Override
    public User queryUserInfoById(Long userId) {
        //1.用户拥有哪些角色
        User user = userDao.queryUserInfoById(userId);
        List<Role> userRoles = roleDao.queryRoleListByUserId(userId);
        if (!CollectionUtils.isEmpty(userRoles)){
            user.setRoles(userRoles);
        }
        //2获取用户所属系统
        List<SystemInfo> systemInfos = systemInfoDao.querySystemByUserId(userId);
        if (!CollectionUtils.isEmpty(systemInfos)){
            user.setSystemInfos(systemInfos);
        }
        //3获取菜单
        List<Long> roleIds = roleDao.queryRolesIdsByUserId(userId);
        Map<String,List<Menu>> result = Maps.newHashMap();
        List<Long> menuIds = new ArrayList<>();
        List<Long> powerIds = Lists.newArrayList();
        List<Menu> menus = Lists.newArrayList();
        //获取角色下的权限
        if (!CollectionUtils.isEmpty(roleIds)){
            powerIds.addAll(powerDao.queryPowerIdsByRoleIds(roleIds, PowerTypeEnum.MENU.getCode()));
        }

        //根据userid获取权限id
        powerIds.addAll(powerDao.queryPowerIdsByUserId(null,userId,null));
        //获取每个系统下的菜单
        for (SystemInfo v : systemInfos) {
            List<Long> powerIdList = powerDao.queryPowerIdsByUserId(v.getId(), userId, PowerTypeEnum.SYSTEMMANAGE.getCode());
            // 如果是当前系统关系员权限,则直接获取该系统下的所有菜单.
            if (!CollectionUtils.isEmpty(powerIdList)) {
                menuIds = menuService.getMenuIdsBySystemId(v.getId());
                menus = queryMenuListByUserId(userId, v.getId(),powerIds);
                menus.sort(Comparator.comparing(Menu::getSno));
                //选中
                predicate(menus, menuIds);
                result.put(v.getName(), menus);
                menuIds.clear();
                continue;
            }
            //根据系统id获取菜单id集合
            if (!CollectionUtils.isEmpty(powerIds)) {
                menuIds = menuDao.queryMenuIdsByPowerIds(v.getId(), powerIds.stream().distinct().collect(toList()));
                //获取菜单
                menus = queryMenuListByUserId(userId, v.getId(),powerIds);
                menus.sort(Comparator.comparing(Menu::getSno));
            }
            if (CollectionUtils.isEmpty(menuIds)){
                continue;
            }
            //选中
            predicate(menus, menuIds);
            result.put(v.getName(), menus);
        }
        user.setMenus(result);
        return user;
    }

    @Override
    public List<User> getUserListBySystem(String userName, String realName, Long systemId, Integer type, Pagination pagination) {
        List<User> users = null;
        List<Long> userIds = userDao.queryUserIdsBySystem(systemId, userName, realName);

        if (type.equals(1)) {
            // 已选用户
            if (CollectionUtils.isEmpty(userIds)) {
                return new ArrayList<>();
            }
            pagination.setTotal(userIds.size());
            users = userDao.queryUserListByIds(userIds, userName, realName, type, pagination.getPage(), pagination.getRows());
        } else if (type.equals(2)) {
            List<Long> selectUserIds = userDao.queryUserIdsBySystem(systemId, null, null);
            // 未选用户
//            if (CollectionUtils.isEmpty(selectUserIds)) {
//                pagination.setTotal(userDao.queryUserCountByConditions(new User()));
//                System.out.println(pagination.getStart());
//                System.out.println(pagination.getEnd());
//                return userDao.queryUserListByConditions(new User(), pagination.getStart(), pagination.getEnd());
//            }
            int total = userDao.queryUserListByIds(selectUserIds, userName, realName, type, 1, 2147483647).size();
            pagination.setTotal(total);
            users = userDao.queryUserListByIds(selectUserIds, userName, realName, type, pagination.getPage(), pagination.getRows());
        }
       return users;
    }

    @Override
    public List<User> queryUserListBySystemId(Long systemId, Long powerId, User user, Pagination pagination, int type) {
        //1.先查询该角色下已经拥有的所有的用户。
        //2.在查询该系统下的所有的用户.
        //3.过滤掉已经拥有的用户.
        //4.根据剩下的用户ID查询该系统下未选择的所有用户.
        List<User> userList = new ArrayList<>();
        //已选择
        if (type == 1){
            userList = userDao.queryUserListBySystemId(powerId,systemId);
            if (CollectionUtils.isEmpty(userList)) {
                return new ArrayList<>();
            }
            List<Long> userIds = userList.stream().map(User::getId).collect(toList());
            int userCount =  userDao.queryUserCountByPowerId(user,systemId,userIds,type);
            pagination.setTotal(userCount);
            userList = userDao.queryUserListByPowerId(user,pagination.getStart(),pagination.getEnd(),systemId,userIds,type);
        }

        //未选择
        if (type == 2){
            List<Long> userIds = userDao.queryUserListBySystemId(powerId,systemId).stream().map(User::getId).collect(toList());
            int userCount =  userDao.queryUserCountByPowerId(user,systemId,userIds,type);
            pagination.setTotal(userCount);
            userList = userDao.queryUserListByPowerId(user,pagination.getStart(),pagination.getEnd(),systemId,userIds,type);
        }
        return userList;
    }

    @Override
    public int queryUserCountById(String userName) {
        return userDao.queryUserCountByUserId(userName);
    }

    @Override
    public List<Long> getUserCountBySystemId(Long systemId) {
        return userDao.queryUserIdsBySystem(systemId, null, null);
    }

    @Override
    public List<Menu> queryMenuListByUserId(Long userId, Long systemId,List<Long> powerIds) {
        List<Menu> menus = menuService.getMenuListByPidV2(0L,null,null,systemId);
        return menus;
    }

    @Override
    public boolean isExistMobile(String mobile) {
        int count = userDao.queryCountByMobile(mobile);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUserListByAgentId(Long agentId) {
        if (agentId == null) {
            return new ArrayList<>();
        }
        return userDao.queryUserListByAgentId(agentId);
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

	@Override
	public boolean editPwd(String pwd, Long userId) {
		int res = 0;
		String newPwd=MD5Tool.MD5Encode(pwd).toUpperCase();
		res = userDao.updatePwd(newPwd, userId);
		if(res>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPwd(Long id,String pwd) {
		String newPwd=MD5Tool.MD5Encode(pwd).toUpperCase();
		User user = userDao.checkPwd(id,newPwd);
		if(user!=null) {
			return true;
		}
		return false;
	}

	@Override
	public int editPwdByMobile(String mobile, String pwd) {
		int res = 0;
		res = userDao.updatePwdByMobile(mobile, pwd);
		return res;
	}
}
