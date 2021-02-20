package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.DiagnoseDao;
import com.cn.sh.lilac.model.Diagnose;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gu xinxin
 */
@Service
public class DiagnoseServiceImpl implements  DiagnoseService{
    @Autowired
    DiagnoseDao diagnoseDao;

    @Override
    public PageResult getDiagnosePage(PageUtil pageUtil) {
        List<Diagnose> diagnoseList= diagnoseDao.findDiagnose(pageUtil);
        int total = diagnoseDao.getTotalDiagnose(pageUtil);
        PageResult result = new PageResult(diagnoseList, total, pageUtil.getLimit(), pageUtil.getPage());
        return result;
    }

    @Override
    public int save(Diagnose diagnose) {
        return diagnoseDao.addDiagnose(diagnose);
    }

    @Override
    public int update(Diagnose diagnose) {
        return diagnoseDao.updateDiagnoseAccount(diagnose.getDiagnoseId(), diagnose.getIllness());
    }

    @Override
    public int stopUse(Diagnose diagnose) {
        return diagnoseDao.stopUse(diagnose.getDiagnoseId());
    }
}
