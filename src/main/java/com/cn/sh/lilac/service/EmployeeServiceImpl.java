package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.EmployeeDao;
import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Employee;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import com.cn.sh.lilac.utils.*;
import com.cn.sh.lilac.service.EmployeeService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author gxx
 */

@Service
public class EmployeeServiceImpl implements  EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getEmployeePage(PageUtil pageUtil){
        //当前数据列表
        List<Employee> employeeList = employeeDao.findEmployees(pageUtil);
        //获取总条数计算分页
        int total = employeeDao.getTotalEmployee(pageUtil);
        //封装为PageResult
        PageResult pageResult = new PageResult(employeeList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;

    }

    /**
     * 新增员工记录
     *
     * @return
     */

    @Override
    public int save(Employee employee){
        return employeeDao.addEmployee(employee);
    }

    /**
     * 更改密码字段
     *
     * @param employee
     * @return
     */
    @Override
    public int update(Employee employee){
        return employeeDao.update(employee.getId(), employee.getEmployeeName(), employee.getAccount(), employee.getIncome());
    }



    /**
     * 根据姓名查询员工
     *
     * @param name
     * @return
     */
    @Override
    public Employee selectEmployeeByName(String name) {
        return employeeDao.getEmployeeByEmployeeName(name);
    }

    @Override
    public Employee selectEmployeeByEmployeeId(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    /**
     * 停用员工
     *
     * @param employee
     * @return
     */
    @Override
    public int stopUse(Employee employee) {
        return employeeDao.stopUse(employee.getId());
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

}
