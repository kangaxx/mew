package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.Wastage;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;

import java.util.List;

/**
 * @author gxx
 */
public interface WastageService {
    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getWastagePage(PageUtil pageUtil);

    /**
     * 新增员工记录
     *
     * @return
     */


    int save(Wastage wastage);

    /**
     * 编辑字段
     *
     * @param wastage
     * @return
     */
    int update(Wastage wastage);



    /**
     * 停用（但不会删除）
     */
    int stopUse(Wastage wastage);
}
