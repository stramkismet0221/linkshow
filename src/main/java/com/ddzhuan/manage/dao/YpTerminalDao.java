package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.YpTerminal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友朋设备Dao
 *
 * @author likeke
 * @date 2019/06/20
 */
public interface YpTerminalDao {

    /**
     * 批量添加设备
     * @param ypTerminals 设备列表
     */
    void batchInsertTerminalInfo(@Param("ypTerminals") List<YpTerminal> ypTerminals);

    /**
     * 批量获取友朋设备
     * @param terminalIds  设备ids
     * @return  友朋设备列表
     */
    List<YpTerminal> queryYpTerminalListByIds(@Param("terminalIds") List<String> terminalIds);

    /**
     * 条件查询友朋设备
     * @param ypTerminal 设备信息
     * @param keyword 关键字搜索
     * @param page 当前页
     * @param rows 每页数量
     * @return  友朋设备列表
     */
    List<YpTerminal> queryYpTerminalListByConditions(@Param("ypTerminal") YpTerminal ypTerminal,
                                                     @Param("keyword") String keyword,
                                                     @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * 条件查询友朋设备总记录数
     * @param ypTerminal 设备信息
     * @param keyword 关键字搜索
     * @return 总记录数
     */
    Integer queryYpTerminalCountByConditions(@Param("ypTerminal") YpTerminal ypTerminal,
                                             @Param("keyword") String keyword);

    List<YpTerminal> getAllYpTerminalList();

    List<YpTerminal> getYpTerminalListByIds(@Param("terminalIds") List<String> terminalIds);

    /**
     * 根据设备id获取设备信息
     * @param terminalId 设备id
     * @return 设备信息
     */
    YpTerminal queryYpTerminalById(@Param("terminalId") String terminalId);

    /**
     * 条件查询
     * @param ypTerminal 查询信息
     * @param terminalIds 设备ids
     * @param type 1：已选 2：未选
     * @param start 分页开始
     * @param end 分页结束
     * @return 设备列表
     */
    List<YpTerminal> queryYpTerminalsByUserId(@Param("ypTerminal") YpTerminal ypTerminal,
                                              @Param("terminalIds") List<String> terminalIds,
                                              @Param("type") Integer type,
                                              @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 根据userId查询用户关联的设备
     * @param userId 用户id
     * @return 设备ids
     */
    List<String> queryTerminalIdsByUserId(@Param("userId") Long userId);

    /**
     * 保存用户设备关系
     * @param userId 用户id
     * @param terminalId 设备id
     */
    void saveUserTerminal(@Param("userId") Long userId, @Param("terminalId") String terminalId);

    /**
     * 删除用户设备关系
     * @param userId 用户id
     * @param terminalId 设备id
     */
    void delUserTerminal(@Param("userId") Long userId, @Param("terminalId") String terminalId);

    /**
     * 根据设备名称模糊查询设备ids
     * @param terminalName 设备名称
     * @return 设备ids
     */
    List<String> queryTerminalIdsByName(@Param("terminalName") String terminalName);

}
