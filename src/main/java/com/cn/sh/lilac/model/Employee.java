package com.cn.sh.lilac.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gxx
 */

public class Employee {
    /**
     * pk
     */
    private Long id;

    private String employeeName;

    private BigDecimal account;

    private BigDecimal income;

    /**
     * 是否已删除 0未删除 1已删除
     */
    private int isDeleted;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsDeleted() {
        return this.isDeleted;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getAccount() {
        return this.account;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getIncome(){
        return this.income;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return "employee{" +
                "id = '" + id + '\'' +
                "emp_name = '" + employeeName + '\'' +
                "account = '" + account + '\'' +
                "income = '" + income + '\'' +
                "createTime = '" + createTime + '\'' +
                "}";
    }
}
