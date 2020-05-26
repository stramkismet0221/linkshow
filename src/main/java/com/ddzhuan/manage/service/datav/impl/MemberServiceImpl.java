package com.ddzhuan.manage.service.datav.impl;

import com.ddzhuan.manage.dao.datav.MemberDao;
import com.ddzhuan.manage.model.datav.member.*;
import com.ddzhuan.manage.service.datav.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员
 *
 * @author likeke
 * @date 2019/10/28
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public List<MemberTotal> getMemberTotalList(Integer type) {
        return memberDao.queryMemberTotalList(type);
    }

    @Override
    public List<MemberGrowth> getMemberGrowthList(Integer type) {
        return memberDao.queryMemberGrowthList(type);
    }

    @Override
    public List<MemberLBGrowth> getMemberLBGrowthList(Integer type) {
        return memberDao.queryMemberLBGrowthList(type);
    }

    @Override
    public List<MemberConsume> getMemberConsumeList(Integer type) {
        return memberDao.queryMemberConsumeList(type);
    }

    @Override
    public List<MemberLBConsume> getMemberLBConsumeList(Integer type) {
        return memberDao.queryMemberLBConsumeList(type);
    }

    @Override
    public List<MemberMConsume> getMemberMConsumeList(Integer type) {
        return memberDao.queryMemberMConsumeList(type);
    }

    @Override
    public List<MemberLBProfit> getMemberLBProfitList(Integer type) {
        return memberDao.queryMemberLBProfitList(type);
    }

    @Override
    public List<MemberAreaSales> getMemberAreaSalesList() {
        return memberDao.queryMemberAreaSalesList();
    }

    @Override
    public List<MemberStoreSales> getMemberStoreSalesList() {
        return memberDao.queryMemberStoreSalesList();
    }

    @Override
    public List<MemberStoreSalesPro> getMemberStoreSalesProList() {
        return memberDao.queryMemberStoreSalesProList();
    }
}
