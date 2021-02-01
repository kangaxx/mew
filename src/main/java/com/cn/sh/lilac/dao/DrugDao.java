package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author gxx
 * @date 20200201
 */

public interface DrugDao {
    Drug getDrugByDrugName(@Param("drugName") String drugName);
    Drug getDrugByDrugId(@Param("drugId") Long drugId);
    /**
     * 新增记录
     *
     * @return
     */
    int addDrug(Drug drug);
}
