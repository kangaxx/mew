package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.Input;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;

import java.util.Date;
import java.util.List;

/**
 * @author gu xinxin
 */
public interface InputService {
    /**
     * 获取分页列表
     * @param pageUtil
     * @return
     */
    PageResult getInputPage(PageUtil pageUtil);

    /**
     * 新增药品记录
     *
     * @return
     */
    int save(Input input);

    /**
     * 根据id查找
     * @param inputId
     */
    Input selectInputByInputId(Long inputId);

    /**
     * 根据药品id查找
     * @param drugId
     */
    Input selectInputByDrugIdAndDuration(Long drugId, Date duration);

    /**
     * 删除入库信息
     */
    int stopUse(Input input);

    /**
     * 根据药品筛选入库药品
     * @param drugId
     * @return
     */
    List<Input> selectInputsByDrugId(Long drugId);
}
