package com.cn.sh.lilac.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author gxx
 * 仓库信息
 */

@Entity
@Table(name="tb_storage")
public class Storage implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long storageId;

    /**
     * 仓库名称
     */
    private String storageName;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    public void setStorageId(Long storageId) { this.storageId = storageId; }
    public Long getStorageId() { return this.storageId; }

    public void setStorageName(String storageName) { this.storageName = storageName; }
    public String getStorageName() { return this.storageName; }

    public void setCreateTime(Date createTime) {this.createTime = createTime; }
    public Date getCreateTime() { return this.createTime; }

    @Override
    public String toString() {
        return "Storage{" +
                "storageId=" + storageId +
                ", storageName='" + storageName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
