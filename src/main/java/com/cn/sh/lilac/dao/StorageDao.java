package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.Storage;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

/**
 * @author gxx
 */

public interface StorageDao extends PagingAndSortingRepository<Storage, Integer> {

}
