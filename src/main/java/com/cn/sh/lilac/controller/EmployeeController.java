package com.cn.sh.lilac.controller;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Employee;
import com.cn.sh.lilac.service.EmployeeService;
import com.cn.sh.lilac.utils.ExcelUtils;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author gxx
 */

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private final int employeeStartRowNum = 6; //标准excel格式文档第七行开始
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        PageResult employeePage = employeeService.getEmployeePage(pageUtil);
        return ResultGenerator.genSuccessResult(employeePage);
    }

    @PostMapping(path = "/save")
    public Result save(@RequestBody Employee employee) {
        if (!StringUtils.hasLength(employee.getEmployeeName())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //不允许添加同名的员工，目前规定如此
        Employee tmpEmployee = employeeService.selectEmployeeByName(employee.getEmployeeName());
        if (tmpEmployee != null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "员工名重复，请不要重复添加，如果是重名，建议加序号！");
        }
        //插入数据库
        if (employeeService.save(employee) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping(value = "/update")
    public Result updateAccount(@RequestBody Employee employee) {
        if (employee.getId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        if (StringUtils.isEmpty(employee.getAccount())) {
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
    public Result employeeStopUse(@RequestBody Employee employee) {
        if (employee.getId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        //修改数据
        if (employeeService.stopUse(employee) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }


    /**
     * 通过excel文件，批量增加课程
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        //获取文件
        if (file.isEmpty()) {
            return "上传失败";
        }
        //生成新文件名，用于保存
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        //20201014_13021325.png
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        int maxRowNum = 0;
        int uploadNum = 0;
        String employeeName = "nothing";
        double account = 0.0;
        double income = 0;
        try {
            //分析excel文件
            maxRowNum = ExcelUtils.getMaxRowNum(file, 0) - 3; //最后三行是统计数据
            employeeName = ExcelUtils.getStringValue(file, 0, 6, 0);
            account = ExcelUtils.getNumValue(file, 0,6, 25);
            income = ExcelUtils.getNumValue(file, 0,6, 22);
            List<List<Object>> list = ExcelUtils.getCourseListByExcel(file);

            for (int i = employeeStartRowNum; i <= maxRowNum; i++) {
                employeeName = ExcelUtils.getStringValue(file, 0, i, 0);
                account = ExcelUtils.getNumValue(file, 0, i, 25);
                income = ExcelUtils.getNumValue(file, 0, i, 22);

                //不允许添加同名的员工，目前规定如此
                Employee employee = new Employee();
                employee.setEmployeeName(employeeName);
                employee.setAccount(new BigDecimal(account));
                employee.setIncome(new BigDecimal(income));


                Employee tmpEmployee = employeeService.selectEmployeeByName(employee.getEmployeeName());
                if (tmpEmployee != null) {
                    continue; //不能添加重复员工
                }
                if (employeeService.save(employee) > 0) { uploadNum++;}
            }


            // 保存文件
            byte[] bytes = file.getBytes();
            Path path = Paths.get("C:\\upload\\" + newFileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }

        return "导入完成， 总数" + maxRowNum + " 导入:" + uploadNum + " <a href=\"/employee.html\">返回</a>";
    }
}
