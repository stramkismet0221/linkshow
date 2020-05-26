package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/7/1 13:35
 */
public interface UserDao {

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return User
     */
    User userLogin(@Param("userName") String userName, @Param("password") String password);

    int insertUser(User user);

    List<User> queryUserListByConditions(@Param("user") User user, @Param("start") Integer start, @Param("size") Integer size);

    Integer queryUserCountByConditions(@Param("user") User user);

    int updateUserByUser(User user);

    User queryUserInfoById(Long userId);

    List<User> getUserListByRoleId(@Param("user") User user, @Param("roleId") Long roleId,
                                   @Param("systemId") Long systemId, @Param("start") Integer start,
                                   @Param("size") Integer size);

    /**
     * 根据角色id查询该角色下所有的用户id
     *
     * @param roleId 角色id
     * @return
     */
    List<Long> getUserIdListByRoleId(@Param("roleId") Long roleId);

    int queryUserCountByRoleId(@Param("user") User user, @Param("roleId") Long roleId,
                               @Param("systemId") Long systemId,@Param("type") Integer type);

    List<User> getUserListByRoleIdV1(Long roleId);

    List<Long> queryUserIdsBySystem(@Param("systemId") Long systemId, @Param("userName") String userName,
                                    @Param("realName") String realName);

    List<User> queryUserListByIds(@Param("ids") List<Long> ids, @Param("userName") String userName,
                                  @Param("realName") String realName, @Param("type") Integer type,
                                  @Param("page") Integer page, @Param("rows") Integer rows);

    List<User> queryUnCheckUser(@Param("user") User user, @Param("start") Integer start, @Param("size") Integer size,
                                @Param("roleId") Long roleId, @Param("systemId") Long systemId);

    /**
     * 根据用户id查询该用户拥有的菜单
     *
     * @param userId 用户ID
     * @return
     */
    List<Long> getuserMenuIdListByUserId(Long userId);

    /**
     * 给用户添加菜单
     * @param userId  用户ID
     * @param menuIds 菜单组
     * @return
     */
    int insertUserMenus(@Param("userId") Long userId, @Param("menuIds") List<Long> menuIds);

    /**
     * 删除该用户拥有的菜单
     * @param userId 用户ID
     * @return
     */
    int delUserMenusByUserId(Long userId);

    List<User> queryUserListBySystemId(@Param("powerId")Long powerId,
                                       @Param("systemId")Long systemId);

    /**
     * 分页查询没有该权限的所有用户
     * @param user
     * @param start
     * @param size
     * @param systemId
     * @param userIds
     * @param type 1、已选 2、未选
     * @return
     */
    List<User> queryUserListByPowerId(@Param("user") User user,
                                      @Param("start") Integer start,
                                      @Param("size") Integer size,
                                      @Param("systemId") Long systemId,
                                      @Param("userIds") List<Long> userIds,
                                      @Param("type") Integer type);

    /**
     * 查询没有该权限的所有用户数量
     * @param user
     * @param userIds
     * @param type 1、已选择 2、未选择
     * @return
     */
    int queryUserCountByPowerId(@Param("user") User user, @Param("systemId") Long systemId,
                                @Param("userIds") List<Long> userIds, @Param("type") Integer type);

    /**
     * 根据用户ID查询用户数量
     * @param userName 用户ID
     * @return
     */
    int queryUserCountByUserId(String userName);

    /**
     * 根据手机号查询用户记录数
     * @param mobile 手机号码
     * @return 记录数
     */
    int queryCountByMobile(String mobile);

    /**
     * 根据agentId查询用户列表
     * @param agentId agentId
     * @return 用户列表
     */
    List<User> queryUserListByAgentId(@Param("agentId") Long agentId);
    
    int updatePwd(@Param("newPwd")String newPwd,@Param("userId")Long userId);
    
    User checkPwd(@Param("id")Long id,@Param("pwd")String pwd);
    
    int updatePwdByMobile(@Param("mobile")String mobile,@Param("pwd")String pwd);
    
}
