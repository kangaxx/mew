package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.InputDao;
import com.cn.sh.lilac.model.Drug;
import com.cn.sh.lilac.model.Input;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gu xinxin
 */
@Service
public class InputServiceImpl implements  InputService{
    @Autowired
    InputDao inputDao;

    @Override
    public PageResult getInputPage(PageUtil pageUtil) {
        //当前数据列表
        List<Input> inputList = inputDao.findInputs(pageUtil);
        //获取总条数计算分页
        int total = inputDao.getTotalInput(pageUtil);
        //封装为PageResult
        PageResult pageResult = new PageResult(inputList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int save(Input input) {
        return inputDao.addInput(input);
    }


    @Override
    public Input selectInputByInputId(Long inputId) {
        return inputDao.getInputByInputId(inputId);
    }

    @Override
    public Input selectInputByDrugId(Long drugId) {
        return inputDao.getInputByDrugId(drugId);
    }

    @Override
    public int stopUse(Input input) {
        return 0;
    }
}
