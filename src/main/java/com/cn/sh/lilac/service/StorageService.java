package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.Storage;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;
import org.springframework.data.domain.Page;

/**
 * @author gxx
 */
public interface StorageService {
    PageResult getStoragePage(PageUtil pageUtil);
}
