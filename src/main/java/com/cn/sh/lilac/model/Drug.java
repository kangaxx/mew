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
    /**
     * 通用名
     */
    private String drugName;

    /**
     * 商品名
     */
    private String drugTradeName;


    /**
     * 药品规格
     */
    private String drugPackage;

    /**
     * 商品编号
     */
    private String drugNo;

    /**
     * 生产厂家编号
     */
    private Long manufactureId;
    /**
     * 库存数量
     */
    private BigDecimal reckonNum;
    /**
     * 药品期限
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date duration;
    /**
     * 药品规格
     */
    private String drugUnit;

    /**
     * 单价
     */
    private BigDecimal drugPrice;

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

    public void setDrugTradeName(String drugTradeName) { this.drugTradeName = drugTradeName; }
    public String getDrugTradeName(){ return this.drugTradeName; }

    public void setDrugPackage(String drugPackage) { this.drugPackage = drugPackage; }
    public String getDrugPackage() { return this.drugPackage; }

    public void setDrugNo(String drugNo) { this.drugNo = drugNo; }
    public String getDrugNo() { return drugNo; }

    public void setManufactureId(Long manufactureId) { this.manufactureId = manufactureId; }
    public Long getManufactureId() { return this.manufactureId; }

    public void setDrugUnit(String drugUnit) { this.drugUnit = drugUnit; }
    public String getDrugUnit() { return this.drugUnit; }

    public void setDrugPrice(BigDecimal drugPrice) { this.drugPrice = drugPrice; }
    public BigDecimal getDrugPrice() { return this.drugPrice; }

    public void setReckonNum(BigDecimal reckonNum) { this.reckonNum = reckonNum; }
    public BigDecimal getReckonNum() { return this.reckonNum; }

    public void setDuration(Date duration) { this.duration = duration; }
    public Date getDuration() { return this.duration; }

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
                "drug_name = '" + drugName + '\'' +
                "drug_no = '" + drugNo + '\'' +
                "manufacture_id = '" + manufactureId + '\'' +
                "drug_unit = '" + drugUnit + '\'' +
                "drug_price = " + drugPrice +
                "is_deleted = '" + isDeleted + '\'' +
                "createTime = '" + createTime + '\'' +
                "}";
    }
}
