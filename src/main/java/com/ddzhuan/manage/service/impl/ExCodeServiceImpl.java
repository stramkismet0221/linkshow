package com.ddzhuan.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.ddzhuan.manage.common.DateFormat;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.enums.OperateTypeEnum;
import com.ddzhuan.manage.common.enums.TypeEnums;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.ExCodeDao;
import com.ddzhuan.manage.dao.ExCodeProductDao;
import com.ddzhuan.manage.dao.YpTerminalDao;
import com.ddzhuan.manage.model.ExCode;
import com.ddzhuan.manage.model.ExCodeProduct;
import com.ddzhuan.manage.model.YpTerminal;
import com.ddzhuan.manage.service.ExCodeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

/**
 * @author jiang yong tao
 * @date 2019/8/1 9:59
 */
@Service
public class ExCodeServiceImpl implements ExCodeService {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private ExCodeDao exCodeDao;

    @Autowired
    private ExCodeProductDao exCodeProductDao;

    @Autowired
    private YpTerminalDao ypTerminalDao;


    @Override
    public List<ExCode> queryExCodeListByConditions(ExCode exCode, Pagination pagination) {
        Integer exCodesCount = exCodeDao.countExCodeByConditions(exCode);
        pagination.setTotal(exCodesCount);
        List<ExCode> exCodes = exCodeDao.queryExCodeByConditions(exCode,pagination.getStart(),pagination.getEnd());
        return exCodes;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResultInfo insertExCode(ExCode exCode) {
        BaseResultInfo result = new BaseResultInfo();
        List<ExCodeProduct> ypProducts;
        boolean flag = (StringUtils.isNotEmpty(exCode.getMachineType()) && Objects.equals(exCode.getMachineType(),TypeEnums.MachineType.PART.code))
                && StringUtils.isEmpty(exCode.getMachines());
        if (flag){
            result.setSuccess(false);
            result.setMsg("请选择兑换码适用设备");
            return result;
        }

        if (StringUtils.isEmpty(exCode.getExCodeProducts())){
            result.setSuccess(false);
            result.setMsg("请选择兑换码适用商品");
            return result;
        }else {
            AtomicInteger ai = new AtomicInteger(0);
            ypProducts = JSON.parseArray(exCode.getExCodeProducts(),ExCodeProduct.class).stream().peek(v->{
                ai.getAndAdd(v.getAmount());
            }).collect(toList());
            exCode.setExCodeCount(ai.get());
        }

        if (StringUtils.isNotEmpty(exCode.getExpireTimeStr())){
            exCode.setExpireTime(DateFormat.Stringdate(exCode.getExpireTimeStr()));
        }

        // todo 活动ID需要调友朋接口返回活动ID, 暂时自己生成
        //保存活动信息.
        String uuid = UUID.randomUUID().toString();
        exCode.setActId(uuid);
        exCode.setLocked(1);
        exCode.setStatus(1);
        exCodeDao.insertExCode(exCode);

        //保存兑换码使用的商品列表
        exCodeProductDao.batchInsertExCodeProduct(ypProducts.stream().peek(v-> v.setExCodeId(exCode.getId())).collect(toList()));

        result.setSuccess(true);
        result.setMsg("保存成功");
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean modifyExCode(ExCode exCode,Integer type) {

        Assert.isTrue(Objects.nonNull(exCode.getId()),"业务ID不能为空");

        if (OperateTypeEnum.DELETE.code.equals(type) || OperateTypeEnum.LOCK.code.equals(type)){
            exCodeDao.modifySelectiveExCode(exCode);
        }

        if (OperateTypeEnum.EDIT.code.equals(type)){
            List<ExCodeProduct> exCodeProducts = JSON.parseArray(exCode.getExCodeProducts(),ExCodeProduct.class);
            exCode.setExCodeCount(exCodeProducts.stream().mapToInt(ExCodeProduct::getAmount).sum());
            exCodeDao.modifySelectiveExCode(exCode);

            exCodeProducts.forEach(v->{
                v.setExCodeId(exCode.getId());
                int count = exCodeProductDao.countExCodeProductByExCodeIdAndBarcode(v.getExCodeId(),v.getBarcode());
                if (count > 0){
                    exCodeProductDao.modifyExCodeProduct(v.getAmount(),v.getExCodeId(),v.getBarcode());
                }else {
                    exCodeProductDao.insertExCodeProduct(v);
                }
            });
//            exCodeProductDao.batchUpdateExCodeProduct(exCodeProducts);
        }
        return Boolean.TRUE;
    }

    @Override
    public ExCode queryExCodeById(Long exCodeId) {
        ExCode exCode = exCodeDao.queryExCodeById(exCodeId);
        Optional.ofNullable(exCode).ifPresent(v->{
            List<ExCodeProduct> products = exCodeProductDao.queryProductAmountByExCode(exCodeId);
            v.setProducts(products);
            Optional<String> type = Optional.ofNullable(v.getMachineType());
            Optional<String> machines = Optional.ofNullable(v.getMachines());
            type.ifPresent(s-> machines.ifPresent(a->{
                if (StringUtils.equals(s, TypeEnums.MachineType.PART.code)){
                    List<String> terminalIds = Arrays.asList(v.getMachines().split(","));
                    List<YpTerminal> terminalList = ypTerminalDao.queryYpTerminalListByIds(terminalIds);
                    v.setTerminals(terminalList);
                }
            }));
        });
        return exCode;
    }
}
