package com.cn.sh.lilac.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gxx
 */

public class Drug {
    /**
     * pk
     */
    private Long drugId;
    private String drugName;

    /**
     * 商品编号
     */
    private String drugNo;

    /**
     * 生产厂家编号
     */
    private Long manufactureId;

    /**
     * 药品规格
     */
    private String unit;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 药品有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date drugDuration;

    /**
     * 是否已删除 0未删除 1已删除
     */
    private int isDeleted;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    public void setDrugId(Long drugId) { this.drugId = drugId; }
    public Long getDrugId() { return this.drugId; }

    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getDrugName() { return this.drugName; }

    public void setDrugNo(String drugNo) { this.drugNo = drugNo; }
    public String getDrugNo() { return drugNo; }

    public void setManufactureId(Long manufactureId) { this.manufactureId = manufactureId; }
    public Long getManufactureId() { return this.manufactureId; }

    public void setUnit(String unit) { this.unit = unit; }
    public String getUnit() { return this.unit; }

    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getPrice() { return this.price; }

    public void setDrugDuration(Date drugDuration) { this.drugDuration = drugDuration; }
    public Date getDrugDuration() { return this.drugDuration; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getCreateTime() { return this.createTime; }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
    public int getIsDeleted() {
        return this.isDeleted;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return "drug{" +
                "drug_id = '" + drugId + '\'' +
                "drug_name = '" + drugName + '\'' +
                "drug_no = '" + drugNo + '\'' +
                "manufacture_id = '" + manufactureId + '\'' +
                "unit = '" + unit + '\'' +
                "drug_duration = '" + drugDuration + '\'' +
                "is_deleted = '" + isDeleted + '\'' +
                "createTime = '" + createTime + '\'' +
                "}";
    }
}
