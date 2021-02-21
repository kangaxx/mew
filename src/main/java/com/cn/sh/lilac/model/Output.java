package com.cn.sh.lilac.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gu xinxin
 * 领药信息
 */
public class Output {
    /**
     * 主键
     */
    private Long outputId;
    /**
     *领药员工编号
     */
    private Long employeeId;
    /**
     *领药员工姓名
     */
    private String employeeName;
    /**
     *入库id
     */
    private Long inputId;
    /**
     *药品名称
     */
    private Long drugId;
    private String drugName;
    /**
     * 问诊编号
     */
    private int diagnoseId;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     *
     */
    private BigDecimal outputNum;
    /**
     * 领药日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date outputTime;
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

    public void setOutputId(Long outputId) { this.outputId = outputId; }
    public Long getOutputId() { return this.outputId; }

    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public Long getEmployeeId() { return this.employeeId; }

    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getEmployeeName() { return this.employeeName; }

    public void setInputId(Long inputId) { this.inputId = inputId; }
    public Long getInputId() { return this.inputId; }

    public void setDrugId(Long drugId) { this.drugId = drugId; }
    public Long getDrugId() { return this.drugId; }

    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getDrugName() { return this.drugName; }

    public void setDiagnoseId(int diagnoseId) { this.diagnoseId = diagnoseId; }
    public int getDiagnoseId() { return this.diagnoseId; }

    public void setPrice(BigDecimal price) { this.price = price;}
    public BigDecimal getPrice() { return this.price; }

    public void setOutputNum(BigDecimal outputNum) { this.outputNum = outputNum; }
    public BigDecimal getOutputNum() { return this.outputNum; }

    public void setOutputTime(Date outputTime) { this.outputTime = outputTime; }
    public Date getOutputTime() { return this.outputTime; }

    public void setDuration(Date duration) { this.duration = duration; }
    public Date getDuration() {
        return duration;
    }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getCreateTime() { return this.createTime; }

    public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
    public int getIsDeleted() {
        return isDeleted;
    }

    @Override
    public String toString() {
        return "output{" +
                "outputId=" + outputId +
                "inputId=" + inputId +
                ", outputNum='" + outputNum + '\'' +
                ", duration=" + duration +
                ", createTime=" + createTime +
                '}';
    }
}
