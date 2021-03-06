package com.cn.sh.lilac.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gxx
 */
public class Wastage {
    /**
     * pk
     */
    private Long wastageId;

    private Long inputId;

    private Long drugId;
    private String drugName;

    private BigDecimal wastageNum;

    /**
     * 作废原因，可以不填
     */
    private String wastageReason;
    /**
     * 药物有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date duration;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 是否已删除 0未删除 1已删除
     */
    private int isDeleted;

    public void setWastageId(Long wastageId) { this.wastageId = wastageId; }
    public Long getWastageId() { return this.wastageId; }

    public void setInputId(Long inputId) { this.inputId = inputId; }
    public Long getInputId() { return this.inputId; }

    public void setDrugId(Long drugId) { this.drugId = drugId; }
    public Long getDrugId() { return this.drugId; }

    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getDrugName() { return this.drugName; }

    public void setWastageNum(BigDecimal wastageNum) { this.wastageNum = wastageNum; }
    public BigDecimal getWastageNum() { return this.wastageNum; }

    public void setWastageReason(String wastageReason) { this.wastageReason = wastageReason;}
    public String getWastageReason() { return this.wastageReason; }

    public void setDuration(Date duration) { this.duration = duration; }
    public Date getDuration() { return this.duration; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getCreateTime() {return this.createTime; }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return "wastage{" +
                "wastage_id = '" + wastageId + '\'' +
                "input_id = '" + inputId + '\'' +
                "wastage_num = '" + wastageNum + '\'' +
                "is_deleted = '" + isDeleted + '\'' +
                "createTime = '" + createTime + '\'' +
                "}";
    }
}
