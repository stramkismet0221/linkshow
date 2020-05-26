package com.ddzhuan.manage.dao.jumi;

import com.ddzhuan.manage.model.jumi.product.JmGoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/12/26 11:18
 */
public interface JmGoodsTypeDao {

    /**
     * 分页查询商品分类
     *
     * @param jmGoodsType 查询条件
     * @param start       起始页
     * @param end         结束
     * @return 商品分类列表
     */
    List<JmGoodsType> pageJmGoodsType(@Param("jmGoodsType") JmGoodsType jmGoodsType
            , @Param("start") Integer start
            , @Param("end") Integer end);

    /**
     * 根据条件查询商品分类条数
     *
     * @param jmGoodsType 查询条件
     * @return 总条数
     */
    int countJmGoodsType(@Param("jmGoodsType") JmGoodsType jmGoodsType);

    /**
     * 新增巨米商品分类
     *
     * @param jmGoodsType 商品分类
     */
    int insertJmGoodsType(JmGoodsType jmGoodsType);

    /**
     * 根据主键ID更新巨米商品分类
     *
     * @param jmGoodsType 商品分类
     */
    int updateJmGoodsTypeById(JmGoodsType jmGoodsType);

    /**
     * 根据主键ID删除巨米商品分类
     *
     * @param jmGoodsTypeId 商品分类Id数组
     */
    int delJmGoodsTypeById(Long jmGoodsTypeId);

    /**
     * 根据父ID查找分类列表
     *
     * @param pid 父级ID
     */
    List<JmGoodsType> queryJmGoodsTypeListByPid(Long pid);

    /**
     * 根据父ID查找分类
     *
     * @param jmgoodsTypeId id
     */
    JmGoodsType queryJmGoodsTypeById(Long jmgoodsTypeId);

    /**
     * 查询code是否已存在
     *
     * @param code code
     * @return 数量
     */
    int countJmGoodsTypeByCode(@Param("code") String code);

    /**
     * 根据父级ID查询当前最大sno
     *
     * @param pid 父级ID
     * @return 数量
     */
    List<JmGoodsType> queryJmGoodsTypeByPid(Long pid);

    /**
     * 根据父级ID查询当前最大sno
     *
     * @param pid 父级ID
     * @return 数量
     */
    int countJmGoodsTypeByPId(Long pid);

    /**
     * 根据ID和sno范围修改sno
     *
     * @param newSno 修改后的值
     * @param oldSno 修改前的值
     * @param type   类型, 1 加, 0 减
     */
    int updateJmGoodsTypeSnoByPidAnSno(@Param("newSno")Integer newSno,
                                      @Param("oldSno")Integer oldSno,
                                      @Param("type")Integer type,
                                      @Param("pid")Long pid);

    /**
     * 通过ids批量查询商品分类数据
     *
     * @param ids 商品ids
     * @return 商品分类列表
     */
    List<JmGoodsType> queryGoodsTypesByIds(@Param("ids") List<Long> ids);
}
