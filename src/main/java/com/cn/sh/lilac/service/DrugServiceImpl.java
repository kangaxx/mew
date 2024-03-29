package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.DrugDao;
import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Employee;
import com.cn.sh.lilac.utils.PageUtil;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gxx
 */

@Service
public class DrugServiceImpl implements  DrugService{
    @Autowired
    private DrugDao drugDao;

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getDrugPage(PageUtil pageUtil){
        //当前数据列表
        List<Drug> drugList = drugDao.findDrugs(pageUtil);
        //获取总条数计算分页
        int total = drugDao.getTotalDrug(pageUtil);
        //封装为PageResult
        PageResult pageResult = new PageResult(drugList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;

    }

    @Override
    public PageResult reckonDrugsByDuration(PageUtil pageUtil) {
        //当前数据列表
        List<Drug> drugList = drugDao.reckonDrugsByDuration(pageUtil);
        //获取总条数计算分页
        int total = drugDao.getTotalReckonDrugsByDuration(pageUtil);
        //封装为PageResult
        PageResult pageResult = new PageResult(drugList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List<Drug> findAll() {
        return drugDao.findAll();
    }

    @Override
    public int save(Drug drug) {
        return drugDao.addDrug(drug);
    }

    @Override
    public int update(Drug drug) {
        return drugDao.update(drug.getDrugId(), drug.getDrugName(), drug.getDrugTradeName(), drug.getDrugPackage(), drug.getDrugNo(), drug.getDrugUnit(),
                                drug.getDrugPrice());
    }

    @Override
    public Drug selectDrugByName(String drugName) {
        return drugDao.getDrugByDrugName(drugName);
    }

    @Override
    public Drug selectDrugByDrugId(Long drugId) {
        return drugDao.getDrugByDrugId(drugId);
    }

    @Override
    public int stopUse(Drug drug) {
        return drugDao.stopUse(drug.getDrugId());
    }
}
