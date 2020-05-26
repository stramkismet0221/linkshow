package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.SystemInfoDao;
import com.ddzhuan.manage.model.SystemInfo;
import com.ddzhuan.manage.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * 系统管理实现类
 *
 * @author likeke
 * @date 2019/06/24
 */
@Service
public class SystemInfoServiceImpl implements SystemInfoService {

    @Autowired
    private SystemInfoDao systemInfoDao;

    @Override
    public void saveSystemInfo(SystemInfo systemInfo) {
        Assert.isTrue(systemInfo != null, "systemInfo不能为null");
        Assert.isTrue(!StringUtils.isEmpty(systemInfo.getName()), "systemInfo.name不能为null");
        systemInfoDao.insertSystemInfo(systemInfo);
    }

    @Override
    public void modifySystemInfo(SystemInfo systemInfo) {
        Assert.isTrue(systemInfo != null, "systemInfo不能null");
        systemInfoDao.updateSystemInfo(systemInfo);
    }

    @Override
    public List<SystemInfo> getSystemInfoListByConditions(SystemInfo systemInfo, String startTime, String endTime, Pagination pagination) {
        Assert.isTrue(pagination != null, "pagination不能为null");
        pagination.setTotal(systemInfoDao.querySystemInfoCountByConditions(systemInfo, startTime, endTime));
        List<SystemInfo> systemInfos = systemInfoDao.querySystemInfoListByConditions(systemInfo, startTime, endTime, pagination.getPage(), pagination.getRows());
        return systemInfos;
    }

    @Override
    public SystemInfo getSystemInfoById(Long id) {
        Assert.isTrue(id != null, "system.id不能为null");
        return systemInfoDao.findSystemInfoById(id);
    }

    @Override
    public Integer getSystemInfoCountByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return systemInfoDao.querySystemInfoCountByName(name);
    }

    @Override
    public List<SystemInfo> getSystemInfosByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        return systemInfoDao.querySystemInfosByIds(ids);
    }

    @Override
    public void saveSystemUser(Long userId, Long systemId) {
        Assert.isTrue(userId != null, "userId不能为null");
        Assert.isTrue(systemId != null, "systemId不能为null");
        systemInfoDao.insertSystemUser(userId, systemId);
    }

    @Override
    public void removeSystemUser(Long userId, Long systemId) {
        Assert.isTrue(userId != null, "userId不能为null");
        Assert.isTrue(systemId != null, "systemId不能为null");
        systemInfoDao.deleteSystemUser(userId, systemId);
    }

    @Override
    public Map<Long, String> querySystemList(SystemInfo systemInfo) {
        Map<Long,String> systemList = systemInfoDao.querySystemList(systemInfo).stream().collect(toMap(SystemInfo::getId,SystemInfo::getName));
        return systemList;
    }

}
