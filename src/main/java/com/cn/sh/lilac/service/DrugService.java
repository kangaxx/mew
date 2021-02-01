package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.dao.DrugDao;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;

/**
 * @author gxx
 */
public interface DrugService {
    /**
     * 获取分页列表
     * @param pageUtil
     * @return
     */
    PageResult getDrugPage(PageUtil pageUtil);

    /**
     * 新增药品记录
     *
     * @return
     */
    int save(Drug drug);

    /**
     * 更新药品数据
     *
     * @param drug
     * @return
     */
    int update(Drug drug);

    /**
     * 根据药品名称查找
     * @param drugName
     */
    Drug selectDrugByName(String drugName);

    /**
     * 停用药品
     */
    int stopUse(Drug drug);
}
