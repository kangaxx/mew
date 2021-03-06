package com.cn.sh.lilac.controller;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Input;
import com.cn.sh.lilac.service.DrugService;
import com.cn.sh.lilac.service.InputService;
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
 * @author gu xinxin
 */

@RestController
@RequestMapping("/inputs")
public class InputController {
    @Autowired
    InputService inputService;
    @Autowired
    DrugService drugService;

    private final int inputStartRowRum = 1;
    private final int nameColumnNum = 2;
    private final int inputNumColumnNum = 4;
    private final int durationColumnNum = 5;
    private final int priceColumnNum = 6;
    //无效行数量
    private final int unusedRow = 0;

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
        BigDecimal inputNum = BigDecimal.ZERO;
        BigDecimal price = BigDecimal.ZERO;
        Long drugId = 0L;
        String drugName = "";
        Date duration;
        String uploadInfo = "以下药品名不存在无法导入，请配置药品基本信息:"; //回写信息
        try {
            //分析excel文件
            maxRowNum = ExcelUtils.getMaxRowNum(file, 0) - unusedRow - inputStartRowRum + 1;
            //最后三行是统计数据
            List<List<Object>> list = ExcelUtils.getCourseListByExcel(file);

            for (int i = inputStartRowRum; i <= maxRowNum; i++) {
                try {
                    drugName = ExcelUtils.getStringValue(file, 0, i, nameColumnNum);
                    Drug tmp = drugService.selectDrugByName(drugName);
                    if (tmp == null) {
                        uploadInfo += drugName + ";";
                        continue; //药品名不存在不能导入
                    }
                    drugId = tmp.getDrugId();
                    price = ExcelUtils.getBigDecimalValue(file, 0, i, priceColumnNum);
                    duration = ExcelUtils.getDateValue(file, 0, i, durationColumnNum);
                    inputNum = ExcelUtils.getBigDecimalValue(file, 0, i, inputNumColumnNum);

                    //不允许添加同名的员工，目前规定如此
                    Input input = new Input();
                    input.setDrugId(drugId);
                    input.setInputNum(inputNum);
                    input.setDuration(duration);
                    input.setPrice(price);

                    if (inputService.save(input) > 0) {
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

        return "导入完成， 总数" + maxRowNum + " 导入:" + uploadNum + "," + uploadInfo + "<a href=\"/input.html\">返回</a>";
    }
}
