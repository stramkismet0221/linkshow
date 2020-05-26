package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.SellSmokeAgent;
import com.ddzhuan.manage.model.SellSmokeInstall;
import com.ddzhuan.manage.model.SellSmokeTerminal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 电子烟售货机Dao
 *
 * @author likeke
 * @date 2019/10/08
 */
public interface SellSmokeDao {

    /**
     * 查看运营商是否存在
     */
    SellSmokeAgent getAgentExist(@Param("id") Long id);

    /**
     * 新增运营商
     */
    void insertAgent(@Param("sellSmokeAgent") SellSmokeAgent sellSmokeAgent);

    /**
     * 编辑运营商
     */
    void updateAgent(@Param("sellSmokeAgent") SellSmokeAgent sellSmokeAgent);

    /**
     * 删除运营商
     */
    int delAgent(@Param("sellSmokeAgent") SellSmokeAgent sellSmokeAgent);

    /**
     * 运营商总数
     *
     * @param sellSmokeAgent 运营商总数
     * @return 记录数
     */
    int queryAgentCount(@Param("sellSmokeAgent") SellSmokeAgent sellSmokeAgent);

    /**
     * 运营商列表查询
     *
     * @param sellSmokeAgent 查询条件
     * @param start          分页开始
     * @param end            分页结束
     * @return
     */
    List<SellSmokeAgent> querySellSmokeAgentList(@Param("sellSmokeAgent") SellSmokeAgent sellSmokeAgent,
                                                 @Param("start") Integer start, @Param("end") Integer end);


    /**
     * 运营商 - 通过id查询
     * @param id id
     * @return 相信信息
     */
    SellSmokeAgent queryAgentById(@Param("id") Long id);

    /**
     * 通过id查询
     * @param id id
     * @return 相关信息
     */
    SellSmokeInstall querySellSmokeById(@Param("id") Long id);

    /**
     * 新增安装记录
     *
     * @param sellSmokeInstall 安装信息
     */
    void insertSellSmokeInstall(@Param("sellSmokeInstall") SellSmokeInstall sellSmokeInstall);

    /**
     * 分页查询电子烟售货机安装信息列表
     *
     * @param sellSmokeInstall 安装信息查询条件
     * @param start            分页开始
     * @param end              分页结束
     * @param userId 用户id
     * @param start 分页开始
     * @param end 分页结束
     * @return 安装列表
     */
    List<SellSmokeInstall> querySellSmokeInstallList(@Param("sellSmokeInstall") SellSmokeInstall sellSmokeInstall,
                                                     @Param("userId") Long userId,
                                                     @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 记录数
     *
     * @param sellSmokeInstall 安装信息查询条件
     * @param userId userId
     * @return 记录数
     */
    int countSellSmokeInstalls(@Param("sellSmokeInstall") SellSmokeInstall sellSmokeInstall,
                               @Param("userId") Long userId);

    /**
     * 更新安装记录
     *
     * @param sellSmokeInstall 安装记录信息
     */
    void updateSellSmokeInstall(@Param("sellSmokeInstall") SellSmokeInstall sellSmokeInstall);

    /**
     * 删除
     * @param id 安装信息id
     */
    void deleteSellSmokeInstall(@Param("id") Long id);

    /**
     * 增加售货机设备
     * @param sellSmokeTerminal 售货机设备列表
     */
    void insertTerminal(@Param("sellSmokeTerminal") SellSmokeTerminal sellSmokeTerminal);

    /**
     * 根据安装信息id，查询安装设备列表
     * @param installId 安装信息id
     * @return 安装设备列表
     */
    List<SellSmokeTerminal> querySellSmokeTerminalList(@Param("installId") Long installId);

    /**
     * 修改安装设备信息
     * @param sellSmokeTerminal 安装设备记录
     */
    void updateSellSmokeTerminal(@Param("sellSmokeTerminal") SellSmokeTerminal sellSmokeTerminal);

    /**
     * 删除安装设备信息
     * @param installId 安装信息id
     */
    void deleteSellSmokeTerminal(@Param("installId") Long installId);

    /**
     * 根据用户id查询安装记录，包括申请人、处理人、安装人
     * @param userId 用户id
     * @return 安装信列表
     */
    List<SellSmokeInstall> querySellSmokeInstallListByUserId(@Param("userId") Long userId);

}
