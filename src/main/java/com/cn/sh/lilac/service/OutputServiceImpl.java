package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.OutputDao;
import com.cn.sh.lilac.model.Output;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtilEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gu xinxin
 */
@Service
public class OutputServiceImpl implements OutputService{
    @Autowired
    OutputDao outputDao;

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

    @Override
    public int save(Output output) {
        return outputDao.addOutput(output);
    }

    @Override
    public Output selectOutputByOutputId(Long outputId) {
        return outputDao.getOutputByOutputId(outputId);
    }


    @Override
    public List<Output> findOutputsByInputId(Long inputId) {
        return outputDao.findOutputsByInputId(inputId);
    }

    @Override
    public int stopUse(Output output) {
        return outputDao.stopUse(output.getOutputId());
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
}
