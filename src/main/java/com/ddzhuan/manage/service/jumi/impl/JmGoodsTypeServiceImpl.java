package com.ddzhuan.manage.service.jumi.impl;

import com.ddzhuan.manage.common.Pagination;
import com.ddzhuan.manage.common.param.BaseResultInfo;
import com.ddzhuan.manage.dao.jumi.JmGoodsTypeDao;
import com.ddzhuan.manage.model.jumi.product.JmGoodsType;
import com.ddzhuan.manage.service.jumi.JmGoodsTypeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author jiang yong tao
 * @date 2019/12/26 13:46
 */
@Service
public class JmGoodsTypeServiceImpl implements JmGoodsTypeService {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private JmGoodsTypeDao jmGoodsTypeDao;

    @Override
    public List<JmGoodsType> pageJmGoodsType(JmGoodsType jmGoodsType, Pagination pagination) {
        // 查询全部记录
        List<JmGoodsType> jmGoodsTypes = jmGoodsTypeDao.pageJmGoodsType(new JmGoodsType(), pagination.getStart(), pagination.getEnd());
        // 转化为父子级关系结构
        List<JmGoodsType> jmGoodsTypeList = formatJmGoodsTypeList(jmGoodsTypes);
        return jmGoodsTypeList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResultInfo insertJmGoodsType(JmGoodsType jmGoodsType) {
        BaseResultInfo resultInfo = new BaseResultInfo();
        try {
            int count = countJmGoodsTypeByCode(jmGoodsType.getCode());
            if (count > 0) {
                resultInfo.setSuccess(false);
                resultInfo.setMsg("商品分类编码已存在");
                return resultInfo;
            }
            //获取当前父级下所有的子分类
            List<JmGoodsType> jmGoodsTypes = jmGoodsTypeDao.queryJmGoodsTypeByPid(jmGoodsType.getPid());
            jmGoodsTypes.stream().mapToInt(JmGoodsType::getSno).max().ifPresent(v -> {
                // 如果输入的序号大于当前父级相同的子分类序号,将当前序号+1;
                if (v <= jmGoodsType.getSno()) {
                    jmGoodsType.setSno(v+1);
                }
                // 大于1并且小于最大序号时，数据库中当前输入的序号及以后序号全部+1，保证序号有序、不重复
                if (jmGoodsType.getSno() >= 1 && jmGoodsType.getSno() < v) {
                    jmGoodsTypes.stream().filter(a -> a.getSno() >= jmGoodsType.getSno()).forEach(b -> {
                        b.setSno(b.getSno()+1);
                        jmGoodsTypeDao.updateJmGoodsTypeById(b);
                    });
                }
            });
            if (CollectionUtils.isEmpty(jmGoodsTypes)) {
                jmGoodsType.setSno(1);
            }
            resultInfo.setSuccess(jmGoodsTypeDao.insertJmGoodsType(jmGoodsType) > 0);
            resultInfo.setMsg("新增商品分类成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            resultInfo.setSuccess(false);
            resultInfo.setMsg("新增商品分类异常");
        }
        return resultInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJmGoodsTypeById(JmGoodsType jmGoodsType) {
        Assert.isTrue(jmGoodsType.getId() != null,"ID不能为空");
        JmGoodsType old = jmGoodsTypeDao.queryJmGoodsTypeById(jmGoodsType.getId());
        if (jmGoodsType.getSno() != null) {
            //修改前的序号 A1, 修改后的序号 A2;
            if (jmGoodsType.getSno() > old.getSno()) {
                //获取当前父级下所有的子分类
                jmGoodsTypeDao.queryJmGoodsTypeByPid(jmGoodsType.getPid()).stream().mapToInt(JmGoodsType::getSno).max().ifPresent(v -> {
                    // 注: 如果A1大于当前父级下max Sno 则设置为max sno
                    if (v <= jmGoodsType.getSno()) {
                        jmGoodsType.setSno(v);
                    }
                });
                //如果A2 > A1,则将 A1到A2中间的所有序号 -1, 再将A1修改为A2
                jmGoodsTypeDao.updateJmGoodsTypeSnoByPidAnSno(jmGoodsType.getSno(),old.getSno(),0,jmGoodsType.getPid());
            }

            if (jmGoodsType.getSno() < old.getSno()) {
                //如果A2 < A1,则将 A1到A2中间的所有序号 +1, 再将A1修改为A2,
                jmGoodsTypeDao.updateJmGoodsTypeSnoByPidAnSno(jmGoodsType.getSno(),old.getSno(),1,jmGoodsType.getPid());
            }
        }
        jmGoodsTypeDao.updateJmGoodsTypeById(jmGoodsType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delJmGoodsTypeByIds(Long jmGoodsTypeId) {

        JmGoodsType jmGoodsType = jmGoodsTypeDao.queryJmGoodsTypeById(jmGoodsTypeId);
        List<JmGoodsType> jmGoodsTypes = jmGoodsTypeDao.queryJmGoodsTypeByPid(jmGoodsType.getPid());
        jmGoodsTypes = jmGoodsTypes.stream().filter(v -> v.getSno() > jmGoodsType.getSno()).collect(toList());
        jmGoodsTypes.forEach(b->{
            b.setSno(b.getSno()-1);
            jmGoodsTypeDao.updateJmGoodsTypeById(b);
        });
        jmGoodsTypeDao.delJmGoodsTypeById(jmGoodsTypeId);
        return true;
    }

    @Override
    public List<JmGoodsType> getJmGoodsTypeListByPid(Long pid) {
        return jmGoodsTypeDao.queryJmGoodsTypeListByPid(pid);
    }

    @Override
    public JmGoodsType getJmGoodsTypeById(Long jmgoodsTypeId) {
        return jmGoodsTypeDao.queryJmGoodsTypeById(jmgoodsTypeId);
    }

    @Override
    public int countJmGoodsTypeByCode(String code) {
        return jmGoodsTypeDao.countJmGoodsTypeByCode(code);
    }

    @Override
    public int countJmGoodsTypeById(Long jmGoodsTypeId) {
        return jmGoodsTypeDao.countJmGoodsTypeByPId(jmGoodsTypeId);
    }

    private List<JmGoodsType> formatJmGoodsTypeList(List<JmGoodsType> jmGoodsTypes) {
        if (CollectionUtils.isEmpty(jmGoodsTypes)) {
            return new ArrayList<>();
        }
        JmGoodsType current = null;
        List<JmGoodsType> nextList = new ArrayList<>();
        int level = 1;
        for (int i = 0; i < jmGoodsTypes.size(); i++) {
            if (0 == jmGoodsTypes.get(i).getId() && 0 == jmGoodsTypes.get(i).getPid()) {
                // 树形顶端节点，只有一条记录
                current = jmGoodsTypes.get(i);
                continue;
            }
            if (jmGoodsTypes.get(i).getPid() == 0) {
                jmGoodsTypes.get(i).setLevel(level);
                nextList.add(jmGoodsTypes.get(i));
            }
        }
        level++;
        recursiveSubJmGoodsType(current, nextList, jmGoodsTypes);
        return nextList;
    }

    private void recursiveSubJmGoodsType(JmGoodsType current, List<JmGoodsType> nextList, List<JmGoodsType> jmGoodsTypes) {
        for (JmGoodsType next : nextList) {
            if (null != current) {
                next.setParent(current);
            }
            Long parentId = next.getId();
            if (CollectionUtils.isEmpty(next.getChildren())) {
                next.setChildren(new ArrayList<>());
            }
            for (JmGoodsType jmGoodsType : jmGoodsTypes) {
                if (parentId == jmGoodsType.getPid().intValue()) {
                    jmGoodsType.setLevel(next.getLevel() + 1);
                    jmGoodsType.setParent(next);
                    next.getChildren().add(jmGoodsType);
                }
            }
            recursiveSubJmGoodsType(null, next.getChildren(), jmGoodsTypes);
        }
    }

}
