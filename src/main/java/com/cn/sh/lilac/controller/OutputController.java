package com.cn.sh.lilac.controller;


import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Output;
import com.cn.sh.lilac.service.OutputService;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author gu xinxin
 */

@RestController
@RequestMapping("/outputs")
public class OutputController {
    @Autowired
    OutputService outputService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        PageResult outputPage = outputService.getOutputPage(pageUtil);
        return ResultGenerator.genSuccessResult(outputPage);
    }

    @PostMapping(path = "/save")
    public Result save(@RequestBody Output output) {
        if (!StringUtils.hasLength(output.getDrugId().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写药品名称！");
        }

        if (!StringUtils.hasLength(output.getDuration().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写有效期！");
        }

        if (!StringUtils.hasLength(output.getEmployeeId().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请选择领药人员！");
        }

        if (!StringUtils.hasLength(output.getOutputNum().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请选择数量！");
        }
        //插入数据库
        if (outputService.save(output) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping(value = "/stopUse")
    public Result outputStopUse(@RequestBody Output output) {
        if (output.getOutputId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        //修改数据
        if (outputService.stopUse(output) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @GetMapping(value = "/findByInputId")
    public List<Output> findByInputId(@RequestBody Output output) {
        return outputService.findOutputsByInputId(output.getInputId());
    }
}
