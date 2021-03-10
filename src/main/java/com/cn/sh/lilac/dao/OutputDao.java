package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Output;
import com.cn.sh.lilac.utils.PageUtilEx;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author gu xinxin
 */
public interface OutputDao {
    Output getOutputByOutputId(@Param("outputId") Long outputId);
    List<Output> findOutputs(Map param);
    int getTotalOutput(Map param);
    /**
     * 新增记录
     *
     * @return
     */
    int addOutput(Output output);
    /**
     * 删除入库信息(停用)
     */
    int stopUse(@Param("outputId") Long outputId);
    /**
     * 通过inputId查询所有output数据（累计出库数量用
     */
    List<Output> findOutputsByInputId(Long inputId);

}
