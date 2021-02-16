package com.cn.sh.lilac.controller;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Employee;
import com.cn.sh.lilac.service.DrugService;
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
@RequestMapping("/drugs")
public class DrugController {
    @Autowired
    private DrugService drugService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        PageResult drugPage = drugService.getDrugPage(pageUtil);
        return ResultGenerator.genSuccessResult(drugPage);
    }

    @PostMapping(value = "/getDrugByDrugId")
    public Drug getDrugByDrugId(@RequestBody Drug drug) {
        return drugService.selectDrugByDrugId(drug.getDrugId());
    }

    @PostMapping(path = "/save")
    public Result save(@RequestBody Drug drug) {
        if (!StringUtils.hasLength(drug.getDrugName())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写药品名称！");
        }

        if (!StringUtils.hasLength(drug.getDrugUnit())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写单位！");
        }

        if (!StringUtils.hasLength(drug.getDrugPrice().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写药品价格！");
        }

        //不允许添加同名的员工，目前规定如此
        Drug tmpDrug = drugService.selectDrugByName(drug.getDrugName());
        if (tmpDrug != null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "药品名重复，请不要重复添加，如果是重名，建议加序号！");
        }

        //插入数据库
        if (drugService.save(drug) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody Drug drug) {
        if (drug.getDrugId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        if (!StringUtils.hasLength(drug.getDrugName())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写药品名称！");
        }

        if (!StringUtils.hasLength(drug.getDrugUnit())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写单位！");
        }

        if (!StringUtils.hasLength(drug.getDrugPrice().toString())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请填写药品价格！");
        }

        //修改数据
        if (drugService.update(drug) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping(value = "/stopUse")
    public Result drugStopUse(@RequestBody Drug drug) {
        if (drug.getDrugId() == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常，缺少ID");
        }

        //修改数据
        if (drugService.stopUse(drug) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @GetMapping(value = "/findAll")
    public List<Drug> drugFindAll() {
        return drugService.findAll();
    }

}
