package com.cn.sh.lilac.service;

import com.cn.sh.lilac.model.User;
import com.cn.sh.lilac.utils.PageResult;
import com.cn.sh.lilac.utils.PageUtil;

/**
 * @author gxx
 */
public interface UserService {

    /**
     * 分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getUserPage(PageUtil pageUtil);

    User login(String userName, String password);

    /**
     * 根据用户名获取用户记录
     *
     * @return
     */
    User selectByUserName(String userName);

    /**
     * 新增用户记录
     *
     * @return
     */
    int save(User user);

    /**
     * 根据主键查询用户
     *
     * @param id
     * @return
     */
    User selectByUserId(Long id);

    /**
     * 更改密码字段
     *
     * @param user
     * @return
     */
    int updatePassword(User user);
}
