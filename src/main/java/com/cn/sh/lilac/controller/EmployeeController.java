package com.cn.sh.lilac.controller;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Employee;
import com.cn.sh.lilac.model.User;
import com.cn.sh.lilac.service.EmployeeService;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        PageResult employeePage = employeeService.getEmployeePage(pageUtil);
        return ResultGenerator.genSuccessResult(employeePage);
    }

    @PostMapping(path = "/save")
    public Result save(@RequestBody Employee employee){
        if (!StringUtils.hasLength(employee.getEmployeeName())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //不允许添加同名的员工，目前规定如此
        Employee tmpEmployee = employeeService.selectEmployeeByName(employee.getEmployeeName());
        if (tmpEmployee != null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "员工名重复，请不要重复添加，如果是重名，建议加序号！");
        }
        //插入数据库
        if (employeeService.save(employee) > 0){
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping(value = "/update")
    public Result updateAccount(@RequestBody Employee employee){
        if (employee.getId() == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        if (StringUtils.isEmpty(employee.getAccount())){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少余额");
        }

        //修改数据
        if (employeeService.update(employee) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping(value = "/stopUse")
    public Result employeeStopUse(@RequestBody Employee employee){
        if (employee.getId() == null){
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        //修改数据
        if (employeeService.stopUse(employee) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }
}
