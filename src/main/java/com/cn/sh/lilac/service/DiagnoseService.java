package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.Diagnose;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;

import java.util.List;

/**
 * @author gu xinxin
 */
public interface DiagnoseService {
    /**
     * 获取分页列表
     * @param pageUtil
     * @return
     */
    PageResult getDiagnosePage(PageUtil pageUtil);

    /**
     * 新增诊断记录
     *
     * @return
     */
    int save(Diagnose diagnose);
    /**
     * 更改诊断字段
     *
     * @param diagnose
     * @return
     */
    int update(Diagnose diagnose);
    /**
     * 删除数据
     * @param diagnose
     * @return
     */
    int stopUse(Diagnose diagnose);
}
