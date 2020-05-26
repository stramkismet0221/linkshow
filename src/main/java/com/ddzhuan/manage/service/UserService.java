package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.Menu;
import com.ddzhuan.manage.model.User;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:37
 */
public interface UserService {

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @param systemId 系统id
     * @return User
     */
    User login(String userName, String password, Long systemId);
    /**
     *  根据条件查询用户
     * @param user user对象
     * @param pagination 分页信息
     * @return 用户集合
     */
    List<User> queryUserListByConditions(User user, Pagination pagination);

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    User queryUserInfoById(Long id);

    /**
     * 新增用户
     * @param user user对象
     * @return
     */
    boolean  insertUser(User user);

    /**
     * 更新用户
     * @param user user对象
     * @return
     */
    boolean updateUserByConditions(User user);

    /**
     * 根据角色id查询用户列表
     * @param roleId 角色id
     * @param systemId 系统id
     * @return
     */
    List<User> getUserListByRoleId(User user,Long roleId,Long systemId,Pagination pagination);

    /**
     * 查询不属于该角色的所有用户
     * @param user 条件
     * @return
     */
    List<User> queryUnCheckUser(User user,Long roleId,Long systemId,Pagination pagination);

    /**
     * 给用户添加菜单
     * @param userId  用户ID
     * @param menuIds 菜单组
     * @return
     */
    boolean insertUserMenus(Long userId,List<Long> menuIds);

    /**
     * 查询系统用户
     * @param userName 用户名
     * @param realName 真实姓名
     * @param systemId 系统id
     * @param type 1：已选 2：未选
     * @param pagination 分页参数
     * @return 用户列表
     */
    List<User> getUserListBySystem(String userName, String realName, Long systemId, Integer type, Pagination pagination);

    /**
     * 根据系统ID和权限ID查询用户列表
     * @param systemId   系统ID
     * @param powerId     权限ID
     * @param user       条件
     * @param pagination 分页信息
     */
    List<User> queryUserListBySystemId(Long systemId,
                                       Long powerId,
                                       User user,
                                       Pagination pagination,
                                       int type);

    /**
     * 根据userId查询用户数量
     * @param userName 用户ID
     * @return
     */
    int queryUserCountById(String userName);

    /**
     * 通过系统id获取用户数量
     * @param systemId 系统id
     * @return 用户数
     */
    List<Long> getUserCountBySystemId(Long systemId);

    /**
     * 查询该用户下某个系统的菜单
     * @param userId 用户ID
     * @param systemId 系统ID
     * @param powerIds 权限id
     * @return
     */
    List<Menu> queryMenuListByUserId(Long userId,Long systemId,List<Long> powerIds);

    /**
     * 校验手机号是否存在
     * @param mobile 手机号
     * @return 是否存在
     */
    boolean isExistMobile(String mobile);

    /**
     * 根据agentId查询用户列表
     * @param agentId agentId
     * @return 用户列表
     */
    List<User> getUserListByAgentId(Long agentId);

    boolean editPwd(String pwd,Long userId);
    
    boolean checkPwd(Long id,String pwd);
    
    int editPwdByMobile(String mobile,String pwd);
    
}
