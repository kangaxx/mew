package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author gxx
 * @date 20200201
 */

public interface EmployeeDao {
    Employee getEmployeeByEmployeeName(@Param("employeeName") String employeeName);
    Employee getEmployeeById(@Param("id") Long id);
    List<Employee> findEmployees(Map param);
    int getTotalEmployee(Map param);
    /**
     * 新增记录
     *
     * @return
     */
    int addEmployee(Employee employee);

    /**
     * 修改账户数据
     *
     * @return
     */
    int updateEmployeeAccount(@Param("employeeId") Long employeeId, @Param("newAccount") BigDecimal newAccount);

    /**
     * 修改账户数据
     * 定期执行，每月一次，将员工账户余额account累加收入income
     * @return
     */
    int updateEmployeeMonthly();
    /**
     * 修改账户数据
     *
     * @return
     */
    int update(@Param("employeeId") Long employeeId, @Param("newEmployeeName") String employeeName, @Param("newAccount") BigDecimal newAccount, @Param("newIncome") BigDecimal newIncome);

    /**
     * 领药扣钱
     */
    int decAccountByOutput(BigDecimal drugCost, Long employeeId);
    /**
     * 删除账户信息(停用)
     */
    int stopUse(@Param("employeeId") Long employeeId);

    List<Employee> findAll();
}
