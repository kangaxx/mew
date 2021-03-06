package com.cn.sh.lilac.service;


import com.cn.sh.lilac.dao.WastageDao;
import com.cn.sh.lilac.model.Wastage;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gxx
 */

@Service
public class WastageServiceImpl implements  WastageService {
    @Autowired
    private WastageDao wastageDao;

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getWastagePage(PageUtil pageUtil){
        //当前数据列表
        List<Wastage> wastageList = wastageDao.findWastages(pageUtil);
        //获取总条数计算分页
        int total = wastageDao.getTotalWastage(pageUtil);
        //封装为PageResult
        PageResult pageResult = new PageResult(wastageList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;

    }

    /**
     * 新增员工记录
     *
     * @return
     */

    @Override
    public int save(Wastage wastage){
        return wastageDao.addWastage(wastage);
    }

    /**
     *
     * @param wastage
     * @return
     */
    @Override
    public int update(Wastage wastage){
        return wastageDao.update(wastage.getWastageId(), wastage.getInputId(), wastage.getDrugId(), wastage.getWastageNum(), wastage.getWastageReason());
    }

    /**
     * 停用员工
     *
     * @param wastage
     * @return
     */
    @Override
    public int stopUse(Wastage wastage) {
        return wastageDao.stopUse(wastage.getWastageId());
    }
}
