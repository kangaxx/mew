package com.cn.sh.lilac.controller;


import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.Storage;
import com.cn.sh.lilac.service.StorageService;
import com.cn.sh.lilac.service.UserService;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author gxx
 */

@RestController
@RequestMapping("/storages")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        //查询列表数据
        PageResult storagePage = storageService.getStoragePage(pageUtil);
        return ResultGenerator.genSuccessResult(storagePage);
    }
}
