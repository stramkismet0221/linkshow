package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.DictionaryDao;
import com.ddzhuan.manage.model.Dictionary;
import com.ddzhuan.manage.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据字典
 *
 * @author likeke
 * @date 2019/08/14
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    public void saveDictionary(Dictionary dictionary) {
        dictionaryDao.insertDictionary(dictionary);
    }

    @Override
    public List<Dictionary> getDictionaryListByType(Integer type, Pagination pagination) {
        Integer count = dictionaryDao.countDictionaryListByType(type);
        pagination.setTotal(count);
        List<Dictionary> dictionaries = dictionaryDao.queryDictionaryListByType(type, pagination.getPage(), pagination.getRows());
        return dictionaries;
    }

    @Override
    public void modifyDictionary(Dictionary dictionary) {
        dictionaryDao.updateDictionary(dictionary);
    }

    @Override
    public Dictionary getDictionaryByValue(Dictionary dictionary) {
        return dictionaryDao.queryDictionaryByValue(dictionary);
    }

}
