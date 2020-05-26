package com.ddzhuan.manage.service;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.model.ExCode;

import java.util.List;

/**
 * @author jiang yong tao
 * @date 2019/8/1 9:57
 */
public interface ExCodeService {

    List<ExCode> queryExCodeListByConditions(ExCode exCode, Pagination pagination);

    BaseResultInfo insertExCode(ExCode exCode);

    Boolean modifyExCode(ExCode exCode,Integer type);

    ExCode queryExCodeById(Long exCodeId);

}
