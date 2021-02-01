package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.Employee;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;

/**
 * @author gxx
 */
public interface EmployeeService {
    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getEmployeePage(PageUtil pageUtil);

    /**
     * 新增员工记录
     *
     * @return
     */


    int save(Employee employee);

    /**
     * 更改员工字段
     *
     * @param employee
     * @return
     */
    int update(Employee employee);


    /**
     * 根据姓名查询用户
     *
     * @param name
     * @return
     */
    Employee selectEmployeeByName(String name);

    /**
     * 停用员工（但不会删除）
     */
    int stopUse(Employee employee);
}
