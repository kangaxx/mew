package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Drug;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author gxx
 * @date 20200201
 */

public interface DrugDao {
    Drug getDrugByDrugName(@Param("drugName") String drugName);
    Drug getDrugByDrugId(@Param("drugId") Long drugId);
    List<Drug> findDrugs(Map param);
    int getTotalDrug(Map param);
    /**
     * 新增记录
     *
     * @return
     */
    int addDrug(Drug drug);

    /**
     * 修改药品数据
     *
     * @return
     */
    int update(@Param("drugId") Long drugId, @Param("newDrugName") String newDrugName, @Param("newDrugTradeName") String newDrugTradeName, @Param("newDrugPackage") String newDrugPackage,
               @Param("newDrugNo") String newDrugNo, @Param("newDrugUnit") String drugUnit, @Param("newDrugPrice") BigDecimal newDrugPrice,@Param("newDrugDuration") Date drugDuration);

    /**
     * 删除药品信息(停用)
     */
    int stopUse(@Param("drugId") Long drugId);
}
