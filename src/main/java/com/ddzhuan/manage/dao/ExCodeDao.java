package com.ddzhuan.manage.dao;

import com.ddzhuan.manage.model.ExCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/8/1 9:24
 */
public interface ExCodeDao {

    List<ExCode> queryExCodeByConditions(@Param("exCode") ExCode exCode, @Param("start") Integer start, @Param("size") Integer size);

    int countExCodeByConditions(ExCode exCode);

    int insertExCode(ExCode exCode);

    int modifySelectiveExCode(ExCode exCode);

    ExCode queryExCodeById(Long exCodeId);
}
