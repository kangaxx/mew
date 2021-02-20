package com.cn.sh.lilac.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author gxx
 */
public class Diagnose {
    /**
     * PK
     */
    private Integer diagnoseId;
    private String illness;
    private Long employeeId;
    private String employeeName;
    /**
     * 是否已删除 0未删除 1已删除
     */
    private int isDeleted;
    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    public void setDiagnoseId(Integer diagnoseId) { this.diagnoseId = diagnoseId;}
    public Integer getDiagnoseId() { return this.diagnoseId; }

    public void setIllness(String illness) { this.illness = illness; }
    public String getIllness() { return this.illness; }

    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public Long getEmployeeId() { return this.employeeId; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public String getEmployeeName() { return this.employeeName; }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
    public int getIsDeleted() {
        return this.isDeleted;
    }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getCreateTime() { return this.createTime; }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return "diagnose{" +
                "diagnose_id = '" + diagnoseId + '\'' +
                "illness = '" + illness + '\'' +
                "is_deleted = '" + isDeleted + '\'' +
                "createTime = '" + createTime + '\'' +
                "}";
    }
}
