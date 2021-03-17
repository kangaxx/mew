package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.Input;
import com.cn.sh.lilac.model.Output;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtilEx;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gu xinxin
 */
public interface OutputService {
    /**
     * 获取分页列表
     * @param pageUtilEx
     * @return
     */
    PageResult getOutputPage(PageUtilEx pageUtilEx);

    /**
     * 新增药品记录
     *
     * @return
     */
    int save(Output output);

    /**
     * 根据id查找
     * @param outputId
     */
    Output selectOutputByOutputId(Long outputId);

    /**
     * 按照inputId查找Output
     */
    List<Output> findOutputsByInputId(Long inputId);

    /**
     * 删除领药信息
     */
    int stopUse(Output output);

    PageResult getOutputPageEx(PageUtilEx pageUtilEx);
    List<Output> getTotalPrice(Output output);
}
