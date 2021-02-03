package com.cn.sh.lilac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Map;

import com.cn.sh.lilac.common.Constants;
import com.cn.sh.lilac.common.Result;
import com.cn.sh.lilac.common.ResultGenerator;
import com.cn.sh.lilac.model.User;
import com.cn.sh.lilac.service.UserService;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author gxx
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        Result result = ResultGenerator.genFailResult("登录失败");
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            result.setMessage("请填写登录信息!");
            return result;
        }
        User loginUser = userService.login(user.getUserName(), user.getPassword());
        if (loginUser != null) {
            result = ResultGenerator.genSuccessResult(loginUser);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        PageUtil pageUtil = new PageUtil(params);
        //查询列表数据
        PageResult adminUserPage = userService.getUserPage(pageUtil);
        return ResultGenerator.genSuccessResult(adminUserPage);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody User user) {
        //验证参数
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询数据库 排除同名的可能
        User tempUser = userService.selectByUserName(user.getUserName());
        if (tempUser != null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "用户已存在勿重复添加！");
        }
        //向数据库中新增用户
        if (userService.save(user) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Result updatePassword(@RequestBody User user) {
        //验证参数
        if (user.getId() == null || StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        //查询数据库 排除无此用户的问题
        User tempUser = userService.selectByUserId(user.getId());
        if (tempUser == null) {
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此用户！");
        }
        //修改记录
        if (userService.updatePassword(user) > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }
}
