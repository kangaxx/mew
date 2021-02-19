package com.cn.sh.lilac.controller;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Diagnose;
import com.cn.sh.lilac.service.DiagnoseService;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author gu xinxin
 */
@RestController
@RequestMapping("/diagnoses")
public class DiagnoseController {
    @Autowired
    DiagnoseService diagnoseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        PageResult diagnosePage = diagnoseService.getDiagnosePage(pageUtil);
        return ResultGenerator.genSuccessResult(diagnosePage);
    }

    @PostMapping(path = "/save")
    public Result save(@RequestBody Diagnose diagnose) {
        if (!StringUtils.hasLength(diagnose.getEmployeeId().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请选择员工！");
        }

        //插入数据库
        if (diagnoseService.save(diagnose) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody Diagnose diagnose) {
        if (!StringUtils.hasLength(diagnose.getDiagnoseId().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        //修改数据
        if (diagnoseService.update(diagnose) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }
}
