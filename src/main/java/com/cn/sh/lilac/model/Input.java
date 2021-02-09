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
    private String drugName;
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
    /**
     * 药品期限
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 是否已删除 0未删除 1已删除
     */
    private int isDeleted;

    public void setInputId(Long inputId) { this.inputId = inputId; }
    public Long getInputId() { return this.inputId; }

    public void setDrugId(Long drugId) { this.drugId = drugId; }
    public Long getDrugId() { return this.drugId;}

    public void setDrugName(String drugName) { this.drugName = drugName;}
    public String getDrugName() { return this.drugName; }

    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getPrice() { return this.price; }

    public void setInputNum(BigDecimal inputNum) { this.inputNum = inputNum; }
    public BigDecimal getInputNum() { return this.inputNum; }

    public void setDuration(Date duration) { this.duration = duration; }
    public Date getDuration() { return this.duration; }

    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public String getBatchNo() { return this.batchNo; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getCreateTime() { return this.createTime; }

    public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
    public int getIsDeleted() { return this.isDeleted; }

    @Override
    public String toString() {
        return "input{" +
                "inputId=" + inputId +
                ", inputNum='" + inputNum + '\'' +
                ", duration=" + duration +
                ", createTime=" + createTime +
                '}';
    }
}
