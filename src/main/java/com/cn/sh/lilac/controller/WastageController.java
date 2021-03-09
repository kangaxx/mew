package com.cn.sh.lilac.controller;


import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Wastage;
import com.cn.sh.lilac.service.WastageService;
import com.cn.sh.lilac.utils.ExcelUtils;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
@RequestMapping("/wastages")
public class WastageController {
    @Autowired
    private WastageService wastageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        PageResult wastagePage = wastageService.getWastagePage(pageUtil);
        return ResultGenerator.genSuccessResult(wastagePage);
    }


    @PostMapping(path = "/save")
    public Result save(@RequestBody Wastage wastage) {
        if (wastage.getInputId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少药品");
        }

        if (wastage.getWastageNum() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少数量");
        }

        //插入数据库
        if (wastageService.save(wastage) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping(value = "/update")
    public Result updateAccount(@RequestBody Wastage wastage) {
        if (wastage.getWastageId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        if (wastage.getInputId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少药品");
        }

        if (wastage.getWastageNum() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少数量");
        }

        //修改数据
        if (wastageService.update(wastage) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping(value = "/stopUse")
    public Result wastageStopUse(@RequestBody Wastage wastage) {
        if (wastage.getWastageId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        //修改数据
        if (wastageService.stopUse(wastage) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

}
