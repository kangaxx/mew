package com.cn.sh.lilac.dao;

import com.cn.sh.lilac.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
/**
 * @author gxx
 */
public interface UserDao {

    User getUserByUserNameAndPassword(@Param("userName") String userName, @Param("passwordMD5") String passwordMD5);

    int updateUserToken(@Param("userId") Long userId, @Param("newToken") String newToken);

    List<User> findUsers(Map param);

    int getTotalUser(Map param);

    /**
     * 根据用户名获取用户记录
     *
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 新增用户记录
     *
     * @return
     */
    int addUser(User user);


    /**
     * 根据id获取用户记录
     *
     * @return
     */
    User getUserById(Long id);

    /**
     * 修改密码
     *
     * @return
     */
    int updateUserPassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);

}

