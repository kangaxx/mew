package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Diagnose;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author gu xinxin
 */
public interface DiagnoseDao {
    List<Diagnose> findDiagnose(Map param);
    Integer getTotalDiagnose(Map param);

    /**
     * 新增记录
     *
     * @return
     */
    Integer addDiagnose(Diagnose diagnose);
    /**
     * 修改账户数据
     *
     * @return
     */
    Integer updateDiagnoseAccount(@Param("diagnoseId") Integer diagnoseId, @Param("newIllness") String newIllness);
    /**
     * 删除药品信息(停用)
     */
    Integer stopUse(@Param("diagnoseId") Integer drugId);
}
