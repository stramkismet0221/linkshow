package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.DateFormat;
import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.SellSmokeDao;
import com.ddzhuan.manage.dao.SystemInfoDao;
import com.ddzhuan.manage.dao.UserDao;
import com.ddzhuan.manage.model.SellSmokeAgent;
import com.ddzhuan.manage.model.SellSmokeInstall;
import com.ddzhuan.manage.model.SellSmokeTerminal;
import com.ddzhuan.manage.model.User;
import com.ddzhuan.manage.service.SellSmokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mr Liu
 * @date 2019/9/30
 * desc
 */
@Service
public class SellSmokeServiceImpl implements SellSmokeService {

    @Autowired
    private SellSmokeDao sellSmokeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SystemInfoDao systemInfoDao;

    @Override
    public void saveAgent(SellSmokeAgent sellSmokeAgent) {
        if (sellSmokeAgent == null && StringUtils.isEmpty(sellSmokeAgent)) {
            return;
        }
        Long id = sellSmokeAgent.getId();
        SellSmokeAgent agentExist = null;
        if (id != null) {
            agentExist = sellSmokeDao.getAgentExist(id);
        }
        sellSmokeAgent.setStatus(1);
        if (agentExist == null || agentExist.getId() == null) {
            sellSmokeDao.insertAgent(sellSmokeAgent);
        } else {
            sellSmokeDao.updateAgent(sellSmokeAgent);
        }
    }

    @Override
    public Boolean removeAgent(SellSmokeAgent sellSmokeAgent) {
        Assert.isTrue(sellSmokeAgent.getId() != null, "ID不可为空");
        sellSmokeDao.delAgent(sellSmokeAgent);
        return Boolean.TRUE;
    }

    @Override
    public List<SellSmokeAgent> getSellSmokeAgentList(SellSmokeAgent sellSmokeAgent, Pagination pagination) {
        int totalCount = sellSmokeDao.queryAgentCount(sellSmokeAgent);
        pagination.setTotal(totalCount);
        List<SellSmokeAgent> agentsList = sellSmokeDao.querySellSmokeAgentList(sellSmokeAgent, pagination.getStart(), pagination.getEnd());
        return agentsList;
    }

    @Override
    public SellSmokeAgent getSellAgentById(Long id) {
        Assert.isTrue(id != null, "id不能为null");
        return sellSmokeDao.queryAgentById(id);
    }

    @Override
    public SellSmokeInstall getSellSmokeById(Long id) {
        Assert.isTrue(id != null, "id不能为null");
        SellSmokeInstall sellSmokeInstall = sellSmokeDao.querySellSmokeById(id);
        return sellSmokeInstall;
    }

    @Override
    public void saveSellSmokeInstall(SellSmokeInstall sellSmokeInstall) {
        Assert.isTrue(sellSmokeInstall != null, "保存时对象不可为null");
        if (!StringUtils.isEmpty(sellSmokeInstall.getAppointTimeStr())) {
            sellSmokeInstall.setAppointTime(DateFormat.Stringdate(sellSmokeInstall.getAppointTimeStr()));
        }
        sellSmokeDao.insertSellSmokeInstall(sellSmokeInstall);
    }

    @Override
    public List<SellSmokeInstall> getSellSmokeInstallList(SellSmokeInstall sellSmokeInstall, Long userId, Pagination pagination) {
        Integer count = sellSmokeDao.countSellSmokeInstalls(sellSmokeInstall, userId);
        pagination.setTotal(count);
        List<SellSmokeInstall> sellSmokeInstalls = sellSmokeDao.querySellSmokeInstallList(sellSmokeInstall, userId, pagination.getStart(), pagination.getEnd());
        return sellSmokeInstalls;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSellSmokeInstall(SellSmokeInstall sellSmokeInstall) {
        Assert.isTrue(sellSmokeInstall != null, "更新时对象不可为null");
        if (!CollectionUtils.isEmpty(sellSmokeInstall.getSellSmokeTerminals())) {
            for (SellSmokeTerminal sellSmokeTerminal : sellSmokeInstall.getSellSmokeTerminals()) {
                sellSmokeDao.insertTerminal(sellSmokeTerminal);
            }
        }
        if (!StringUtils.isEmpty(sellSmokeInstall.getAppointTimeStr())) {
            sellSmokeInstall.setAppointTime(DateFormat.Stringdate(sellSmokeInstall.getAppointTimeStr()));
        }
        sellSmokeDao.updateSellSmokeInstall(sellSmokeInstall);
    }

    @Override
    public void removeSellSmokeInstall(Long id) {
        sellSmokeDao.deleteSellSmokeInstall(id);
    }

    @Override
    public List<SellSmokeTerminal> getSellSmokeTerminalList(Long installId) {
        Assert.isTrue(installId != null, "installId不能为空");
        return sellSmokeDao.querySellSmokeTerminalList(installId);
    }

    @Override
    public void updateSellSmokeTerminal(SellSmokeTerminal sellSmokeTerminal) {
        Assert.isTrue(sellSmokeTerminal != null, "sellSmokeTerminal不能为空");
        sellSmokeDao.updateSellSmokeTerminal(sellSmokeTerminal);
    }

    @Override
    public void removeSellSmokeTerminal(Long installId) {
        Assert.isTrue(installId != null, "installId不能为空");
        sellSmokeDao.deleteSellSmokeTerminal(installId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateInstallAndTerminal(SellSmokeInstall sellSmokeInstall, List<SellSmokeTerminal> sellSmokeTerminals) {
        if (sellSmokeInstall != null) {
            sellSmokeDao.updateSellSmokeInstall(sellSmokeInstall);
        }
        if (!CollectionUtils.isEmpty(sellSmokeTerminals)) {
            for (SellSmokeTerminal sst : sellSmokeTerminals) {
                sellSmokeDao.updateSellSmokeTerminal(sst);
            }
        }
    }

    @Override
    public void cancelSellSmokeInstall(SellSmokeInstall sellSmokeInstall) {
        if (sellSmokeInstall != null) {
            sellSmokeDao.updateSellSmokeInstall(sellSmokeInstall);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveAgentUser(User user, Long systemId) {
        Assert.isTrue(user != null, "user不能为null");
        Assert.isTrue(systemId != null, "systemId不能为null");
        userDao.insertUser(user);
        systemInfoDao.insertSystemUser(user.getId(), systemId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeAgentUser(User user, Long systemId) {
        Assert.isTrue(user != null, "user不能为null");
        Assert.isTrue(systemId != null, "systemId不能为null");
        userDao.updateUserByUser(user);
        systemInfoDao.deleteSystemUser(user.getId(), systemId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void revertSellSmokeInstall(SellSmokeInstall sellSmokeInstall) {
        Assert.isTrue(sellSmokeInstall != null, "sellSmokeInstall不能为空");
        sellSmokeDao.updateSellSmokeInstall(sellSmokeInstall);
        sellSmokeDao.deleteSellSmokeTerminal(sellSmokeInstall.getId());
    }

    @Override
    public List<SellSmokeInstall> getSellSmokeInstallListByUserId(Long userId) {
        Assert.isTrue(userId != null, "userId不能为空");
        return sellSmokeDao.querySellSmokeInstallListByUserId(userId);
    }

}
