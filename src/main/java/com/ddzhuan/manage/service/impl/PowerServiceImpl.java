package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.PowerDao;
import com.ddzhuan.manage.dao.UserDao;
import com.ddzhuan.manage.model.Power;
import com.ddzhuan.manage.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author jiang yong tao
 * @date 2019/7/1 17:30
 */
@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerDao powerDao;

    @Override
    public List<Power> getPowerList(Power power, Pagination pagination) {
        int powerCount = powerDao.queryPowerCountByConditions(power);
        pagination.setTotal(powerCount);
        List<Power> powers = powerDao.queryAllPowerList(power,pagination.getStart(),pagination.getEnd());
        return powers;
    }

    @Override
    public Power getPowerById(Long id) {
        return powerDao.queryPowerById(id);
    }

    @Override
    public boolean insertPower(Power power) {
        return powerDao.insertPower(power) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePower(Power power) {
        Assert.isTrue(power.getId() != null,"权限ID不能为空");
        return powerDao.updatePower(power) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResultInfo delPowerById(Long id) {
        BaseResultInfo result = new BaseResultInfo();
        int userCount = powerDao.countUserByPowerId(id);
        int roleCount = powerDao.countRoleByPowerId(id);

        if (0 < userCount){
            result.setMsg("该权限关联用户,暂不可删除");
            result.setSuccess(false);
            return result;
        }

        if (0 < roleCount){
            result.setMsg("该权限关联角色,暂不可删除");
            result.setSuccess(false);
            return result;
        }

        powerDao.delPowerById(id);
        result.setMsg("删除成功");
        result.setSuccess(true);
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUserPower(Long userId, Long powerId,Long systemId) {
        Assert.isTrue(powerId != null,"权限ID不能为空");
        Assert.isTrue(userId != null,"权限ID不能为空");
        Assert.isTrue(systemId != null,"权限ID不能为空");
        return powerDao.insertUserPower(userId,powerId,systemId) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRolePower(Long powerId, Long roleId, Long systemId) {
        return powerDao.insertRolePower(powerId,roleId,systemId) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delUserPowerByUserIdAndPowerId(Long userId, Long powerId,Long systemId) {
        Assert.isTrue(powerId != null,"权限ID不能为空");
        Assert.isTrue(userId != null,"权限ID不能为空");
        return powerDao.delUserPowerByUserIdAndPowerId(userId,powerId,systemId) == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delRolePowerByUserIdAndPowerId(Long roleId, Long powerId) {
        Assert.isTrue(powerId != null,"权限ID不能为空");
        Assert.isTrue(roleId != null,"权限ID不能为空");
        return powerDao.delRolePowerByUserIdAndPowerId(powerId,roleId) == 1;
    }

    @Override
    public int countPowerByCode(String code) {

        return powerDao.countPowerByCode(code);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertPowerMenus(Long powerId,Long[] menuIds) {
        boolean flag = false;
        Assert.isTrue(powerId!=null,"权限ID不能为空");
        powerDao.delPowerMenusByPowerId(powerId);
        if (menuIds == null || menuIds.length <= 0){
            return true;
        }
        return powerDao.insertPowerMenus(powerId, Arrays.asList(menuIds)) > 0;
    }

    @Override
    public int getCountPowersByMenuId(Long menuId) {
        return powerDao.queryCountPowersByMenuId(menuId);
    }

    @Override
    public List<Power> getPowersBySystemId(Long systemId) {
        return powerDao.queryPowersBySystemId(systemId);
    }

    @Override
    public List<Long> getPowerIdsByUserId(Long userId,Long systemId) {
        return powerDao.queryPowerIdsByUserId(systemId, userId, null);
    }

}
