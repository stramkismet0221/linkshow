package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据字典Dao
 *
 * @author likeke
 * @date 2019/08/14
 */
public interface DictionaryDao {

    /**
     * 新增
     * @param dictionary 数据字典
     */
    void insertDictionary(Dictionary dictionary);

    /**
     * 分页获取字典数据
     * @param type 字典数据类型
     * @param page 当前项
     * @param rows 长度
     * @return 字典数据
     */
    List<Dictionary> queryDictionaryListByType(@Param("type") Integer type,
                                               @Param("page") Integer page, @Param("rows") Integer rows);

    /**
     * 根据数据字典类型查询总数量
     * @param type 类型
     * @return 总数
     */
    Integer countDictionaryListByType(@Param("type") Integer type);

    /**
     * 修改
     * @param dictionary 字典属性
     */
    void updateDictionary(@Param("dictionary") Dictionary dictionary);

    /**
     * 通过字典类型，value查询字典数据
     * @param dictionary code、value等唯一属性
     * @return 字典数据
     */
    Dictionary queryDictionaryByValue(@Param("dictionary") Dictionary dictionary);




    /**
     * 根据
     * @param dictionary
     * @return
     */
    String queryDictionaryValueByCodeAndType(Dictionary dictionary);
}
