package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.OutputDao;
import com.cn.sh.lilac.model.Output;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
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
    public PageResult getOutputPage(PageUtil pageUtil) {
        //当前数据列表
        List<Output> outputList = outputDao.findOutputs(pageUtil);
        //获取总条数计算分页
        int total = outputDao.getTotalOutput(pageUtil);
        //封装为PageResult
        PageResult pageResult = new PageResult(outputList, total, pageUtil.getLimit(), pageUtil.getPage());
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
}
