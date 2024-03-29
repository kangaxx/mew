package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Input;
import com.cn.sh.lilac.model.Output;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gu xinxin
 */
public interface InputDao {
    Input getInputByInputId(@Param("inputId") Long inputId);
    Input getInputByDrugId(@Param("drugId") Long drugId);
    List<Input> findInputs(Map param);
    int getTotalInput(Map param);
    /**
     * 新增记录
     *
     * @return
     */
    int addInput(Input input);
    /**
     * 删除入库信息(停用)
     */
    int stopUse(@Param("inputId") Long inputId);

    Input getInputByDrugIdAndDuration(Long drugId, Date duration);

    List<Input> getInputsByDrugId(Long drugId);

}
