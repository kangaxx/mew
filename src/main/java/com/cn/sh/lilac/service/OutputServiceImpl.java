package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.EmployeeDao;
import com.cn.sh.lilac.dao.OutputDao;
import com.cn.sh.lilac.model.Output;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gu xinxin
 */
@Service
public class OutputServiceImpl implements OutputService {
    @Autowired
    OutputDao outputDao;

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public PageResult getOutputPage(PageUtilEx pageUtilEx) {
        //当前数据列表
        List<Output> outputList = outputDao.findOutputs(pageUtilEx);
        //获取总条数计算分页
        int total = outputDao.getTotalOutput(pageUtilEx);
        //封装为PageResult
        PageResult pageResult = new PageResult(outputList, total, pageUtilEx.getLimit(), pageUtilEx.getPage());
        return pageResult;
    }

    @Transactional
    @Override
    public int save(Output output) {
        int result = 0;
        BigDecimal drugCost = output.getPrice().multiply(output.getOutputNum());
        employeeDao.decAccountByOutput(drugCost, output.getEmployeeId());
        result = outputDao.addOutput(output);
        return result;
    }

    @Override
    public Output selectOutputByOutputId(Long outputId) {
        return outputDao.getOutputByOutputId(outputId);
    }


    @Override
    public List<Output> findOutputsByInputId(Long inputId) {
        return outputDao.findOutputsByInputId(inputId);
    }

    @Transactional
    @Override
    public int stopUse(Output output) {
        int result = 0;
        BigDecimal tmp = new BigDecimal(-1.0);
        BigDecimal totalPrice = output.getTotalPrice().multiply(tmp);
        employeeDao.decAccountByOutput(totalPrice, output.getEmployeeId());
        result = outputDao.stopUse(output.getOutputId());
        return result;
    }

    @Override
    public PageResult getOutputPageEx(PageUtilEx pageUtilEx) {
        //当前数据列表
        List<Output> outputList = outputDao.findOutputs(pageUtilEx);
        //获取总条数计算分页
        int total = outputDao.getTotalOutput(pageUtilEx);
        //封装为PageResult
        PageResult pageResult = new PageResult(outputList, total, pageUtilEx.getLimit(), pageUtilEx.getPage());
        return pageResult;
    }

    @Override
    public List<Output> getTotalPrice(Output output) {
        return outputDao.getTotalOutputPrice(output);
    }
}
