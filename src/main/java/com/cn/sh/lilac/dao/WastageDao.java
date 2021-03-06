package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Wastage;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author gu xinxin
 */
public interface WastageDao {
    List<Wastage> findWastages(Map param);
    int getTotalWastage(Map param);
    /**
     * 新增记录
     *
     * @return
     */
    int addWastage(Wastage wastage);
    /**
     * 修改数据
     *
     * @return
     */
    int update(@Param("wastageId") Long wastageId, @Param("newInputId") Long newInputId, @Param("newDrugId") Long newDrugId, @Param("newWastageNum") BigDecimal newWastageNum, @Param("newWastageReason") String newWastageReason);

    /**
     * 删除入库信息(停用)
     */
    int stopUse(@Param("wastageId") Long wastageId);

}
