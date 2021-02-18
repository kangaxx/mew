package com.cn.sh.lilac.controller;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Input;
import com.cn.sh.lilac.service.DrugService;
import com.cn.sh.lilac.service.InputService;
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
@RequestMapping("/inputs")
public class InputController {
    @Autowired
    InputService inputService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        PageResult inputPage = inputService.getInputPage(pageUtil);
        return ResultGenerator.genSuccessResult(inputPage);
    }

    @PostMapping(path = "/save")
    public Result save(@RequestBody Input input) {
        if (!StringUtils.hasLength(input.getDrugId().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写药品名称！");
        }

        if (!StringUtils.hasLength(input.getDuration().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写有效期！");
        }

        if (!StringUtils.hasLength(input.getPrice().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写药品价格！");
        }


        //插入数据库
        if (inputService.save(input) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping(value = "/stopUse")
    public Result drugStopUse(@RequestBody Input input) {
        if (input.getInputId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        //修改数据
        if (inputService.stopUse(input) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping(value = "/getInputByDrugIdAndDuration")
    public Input getDrugByDrugIdAndDuration(@RequestBody Input input) {
        return inputService.selectInputByDrugIdAndDuration(input.getDrugId(), input.getDuration());
    }

    @PostMapping(value = "/getInputsByDrugId")
    public List<Input> getInputsByDrugId(@RequestBody Input input) {
        return inputService.selectInputsByDrugId(input.getDrugId());
    }
}
