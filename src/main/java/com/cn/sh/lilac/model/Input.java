package com.cn.sh.lilac.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gxx
 * 药品入库表
 */
public class Input {
    /**
     * pk
     */
    private Long inputId;
    /**
     * input drug id
     */
    private Long drugId;
    /**
     * 入库售价
     */
    private BigDecimal price;
    /**
     * 入库数量
     */
    private BigDecimal inputNum;
    /**
     * 药品期限
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date duration;
    /**
     * 批号
     */
    private String batchNo;
    

}
