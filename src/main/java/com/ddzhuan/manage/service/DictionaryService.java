package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.model.Dictionary;

import java.util.List;

/**
 * 数据字典
 *
 * @author likeke
 * @date 2019/08/14
 */
public interface DictionaryService {

    /**
     * 添加字典数据接口
     * @param dictionary 字典数据
     */
    void saveDictionary(Dictionary dictionary);

    /**
     * 分页获取字典数据
     * @param type 字典数据类型
     * @param pagination 分页参数
     * @return 字典数据
     */
    List<Dictionary> getDictionaryListByType(Integer type, Pagination pagination);

    /**
     * 修改
     * @param dictionary 字典属性
     */
    void modifyDictionary(Dictionary dictionary);

    /**
     * 通过字典类型，value查询字典数据
     * @param dictionary code、value 唯一属性
     * @return 字典数据
     */
    Dictionary getDictionaryByValue(Dictionary dictionary);
}
