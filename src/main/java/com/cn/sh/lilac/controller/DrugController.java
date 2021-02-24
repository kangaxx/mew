package com.cn.sh.lilac.controller;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Drug;
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

    private final int drugStartRowRum = 3;
    private final int nameColumnNum = 1;
    private final int drugTradeNameColumnNum = 2;
    private final int drugPriceColumnNum = 4;
    private final int drugUnitColumnNum = 3;
    //无效列数量
    private final int unusedRow = 3;
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
        String drugName = "";
        BigDecimal drugPrice = BigDecimal.ZERO;
        String drugUnit = "";
        String drugTradeName = "";
        try {
            //分析excel文件
            maxRowNum = ExcelUtils.getMaxRowNum(file, 0) - unusedRow;
            //最后三行是统计数据
            List<List<Object>> list = ExcelUtils.getCourseListByExcel(file);

            for (int i = drugStartRowRum; i <= maxRowNum; i++) {
                try {
                    drugName = ExcelUtils.getStringValue(file, 0, i, nameColumnNum);
                    drugPrice = ExcelUtils.getBigDecimalValue(file, 0, i, drugPriceColumnNum);
                    drugUnit = ExcelUtils.getStringValue(file, 0, i, drugUnitColumnNum);
                    drugTradeName = ExcelUtils.getStringValueSafe(file, 0, i, drugTradeNameColumnNum, drugName);

                    //不允许添加同名的员工，目前规定如此
                    Drug drug = new Drug();
                    drug.setDrugName(drugName);
                    drug.setDrugPrice(drugPrice);
                    drug.setDrugUnit(drugUnit);
                    drug.setDrugTradeName(drugTradeName);

                    Drug temp = drugService.selectDrugByName(drug.getDrugName());
                    if (temp != null) {
                        continue; //不能添加重复
                    }
                    if (drugService.save(drug) > 0) {
                        uploadNum++;
                    }
                } catch (Exception e) {

                }

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
