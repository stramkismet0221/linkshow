package com.ddzhuan.manage.service.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.dao.YpTerminalCargoDao;
import com.ddzhuan.manage.dao.YpTerminalConfigDao;
import com.ddzhuan.manage.dao.YpTerminalDao;
import com.ddzhuan.manage.dao.YpTerminalPlaceDao;
import com.ddzhuan.manage.model.YpTerminal;
import com.ddzhuan.manage.model.YpTerminalCargo;
import com.ddzhuan.manage.model.YpTerminalConfig;
import com.ddzhuan.manage.model.YpTerminalPlace;
import com.ddzhuan.manage.service.YpTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 友朋设备接口实现类
 *
 * @author likeke
 * @date 2019/06/20
 */
@Service
public class YpTerminalServiceImpl implements YpTerminalService {

    @Autowired
    private YpTerminalDao ypTerminalDao;

    @Autowired
    private YpTerminalConfigDao ypTerminalConfigDao;

    @Autowired
    private YpTerminalPlaceDao ypTerminalPlaceDao;

    @Autowired
    private YpTerminalCargoDao ypTerminalCargoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSaveYpTerminal(List<YpTerminal> ypTerminals) {

        List<String> terminalIds = ypTerminals.stream().map(YpTerminal::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(terminalIds)) {

        }
        List<YpTerminal> terminals = ypTerminalDao.queryYpTerminalListByIds(terminalIds);
        List<String> resultIds = terminals.stream().map(YpTerminal::getId).collect(Collectors.toList());
        for (int i = ypTerminals.size()-1; i >= 0; i--) {
            YpTerminal ypTerminal = ypTerminals.get(i);
            if (resultIds.contains(ypTerminal.getId())) {
                ypTerminals.remove(i);
            }
        }

        List<YpTerminal> ypTerminalList = new ArrayList<>();
        List<YpTerminalConfig> ypTerminalConfigList = new ArrayList<>();
        List<YpTerminalPlace> ypTerminalPlaceList = new ArrayList<>();
        List<YpTerminalCargo> ypTerminalCargoList = new ArrayList<>();

        for (YpTerminal ypTerminal : ypTerminals) {
            YpTerminalConfig config = ypTerminal.getYpTerminalConfig();
            YpTerminalPlace place = null;
            if (config != null) {
                place = config.getYpTerminalPlace();
                if (place != null) {
                    ypTerminalPlaceList.add(place);
                }
                config.setYpTerminalPlace(null);
                ypTerminalConfigList.add(config);
            }

            List<YpTerminalCargo> cargoes = ypTerminal.getYpTerminalCargoList();
            for (YpTerminalCargo cargo : cargoes) {
                ypTerminalCargoList.add(cargo);
            }

            ypTerminal.setYpTerminalCargoList(null);
            ypTerminal.setYpTerminalConfig(null);
            ypTerminalList.add(ypTerminal);
        }

        if (!CollectionUtils.isEmpty(ypTerminalList)) {
            ypTerminalDao.batchInsertTerminalInfo(ypTerminalList);
        }
        if (!CollectionUtils.isEmpty(ypTerminalConfigList)) {
            ypTerminalConfigDao.batchInsertYpTerminalConfig(ypTerminalConfigList);
        }
        if (!CollectionUtils.isEmpty(ypTerminalPlaceList)) {
            ypTerminalPlaceDao.batchInsertYpTerminalPlace(ypTerminalPlaceList);
        }
        if (!CollectionUtils.isEmpty(ypTerminalCargoList)) {
            ypTerminalCargoDao.batchInsertYpTerminalCargo(ypTerminalCargoList);
        }
    }

    @Override
    public List<YpTerminal> getYpTerminalListByConditions(YpTerminal terminal, String keyword, Pagination pagination) {
        Assert.isTrue(pagination != null, "pagination不能为null");
        if (pagination.getTotal() == 0) {
            pagination.setTotal(ypTerminalDao.queryYpTerminalCountByConditions(terminal, keyword));
        }
        List<YpTerminal> ypTerminals = ypTerminalDao.queryYpTerminalListByConditions(terminal, keyword, pagination.getPage(), pagination.getRows());
        return ypTerminals;
    }

    @Override
    public List<YpTerminal> getAllYpTerminalList() {
        return ypTerminalDao.getAllYpTerminalList();
    }

    @Override
    public List<YpTerminal> getYpTerminalListByIds(List<String> terminalIds) {
        return ypTerminalDao.getYpTerminalListByIds(terminalIds);
    }

    @Override
    public List<YpTerminal> getYpTerminalListByUserId(YpTerminal ypTerminal, Long userId, int type, Pagination pagination) {
        List<String> terminalIds = ypTerminalDao.queryTerminalIdsByUserId(userId);
        List<YpTerminal> ypTerminals = new ArrayList<>();
        if (type == 2) {
            // 已选择
            if (CollectionUtils.isEmpty(terminalIds)) {
                Integer count = ypTerminalDao.queryYpTerminalCountByConditions(ypTerminal, null);
                pagination.setTotal(count);
                ypTerminals = ypTerminalDao.queryYpTerminalListByConditions(ypTerminal, null, pagination.getPage(), pagination.getRows());
            } else {
                Integer count = ypTerminalDao.queryYpTerminalsByUserId(ypTerminal,terminalIds,type,0, 2147483647).size();
                pagination.setTotal(count);
                ypTerminals = ypTerminalDao.queryYpTerminalsByUserId(ypTerminal,terminalIds,type,pagination.getStart(),pagination.getEnd());
            }
        } else {
            if (!CollectionUtils.isEmpty(terminalIds)) {
                Integer count = ypTerminalDao.queryYpTerminalsByUserId(ypTerminal,terminalIds,type, 0,2147483647).size();
                pagination.setTotal(count);
                ypTerminals = ypTerminalDao.queryYpTerminalsByUserId(ypTerminal,terminalIds,type,pagination.getStart(),pagination.getEnd());
            }
        }
        return ypTerminals;
    }

    @Override
    public void addUserTerminal(Long userId, String terminalId) {
        ypTerminalDao.saveUserTerminal(userId, terminalId);
    }

    @Override
    public void delUserTerminal(Long userId, String terminalId) {
        ypTerminalDao.delUserTerminal(userId, terminalId);
    }

    @Override
    public List<String> getTerminalIdsByUserId(Long userId) {
        if (null == userId) {
            return new ArrayList<>();
        }
        return ypTerminalDao.queryTerminalIdsByUserId(userId);
    }

}
