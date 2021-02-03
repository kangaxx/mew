package com.cn.sh.lilac.service;

import com.cn.sh.lilac.dao.EmployeeDao;
import com.cn.sh.lilac.dao.StorageDao;
import com.cn.sh.lilac.model.Employee;
import com.cn.sh.lilac.model.Storage;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author gxx
 */
@Service
public class StorageServiceImpl implements StorageService{
    @Autowired
    private StorageDao storageDao;

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getStoragePage(PageUtil pageUtil) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "storageId");
        Sort sort = Sort.by(Sort.Direction.DESC, "storageName");
        Pageable pageable = PageRequest.of(pageUtil.getPage() - 1, pageUtil.getLimit(), sort);
        Page<Storage> page = storageDao.findAll(pageable);
        List<Storage> list= page.getContent();
        //总数 = 总页数*每页条数（总条数不准确，但是后续处理中的总页数是准确的，实际只需要总页数准确即可 gxx
        int total = page.getTotalPages() * pageUtil.getLimit();
        PageResult pageResult = new PageResult(list, total, pageUtil.getLimit(), pageUtil.getPage() - 1);
        return pageResult;
    }
}
