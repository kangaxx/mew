package com.cn.sh.lilac.service;


import com.cn.sh.lilac.dao.UserDao;
import com.cn.sh.lilac.model.User;
import com.cn.sh.lilac.service.UserService;
import com.cn.sh.lilac.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageResult getUserPage(PageUtil pageUtil) {
        //当前页码中的数据列表
        List<User> userList = userDao.findUsers(pageUtil);
        //获取总条数 用于计算分页数据
        int totalAdminUser = userDao.getTotalUser(pageUtil);
        //封装成PageResult对象
        PageResult pageResult = new PageResult(userList, totalAdminUser, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public User login(String userName, String password) {
        //密码加密
        //调用dao方法查询用户
        User adminUser = userDao.getUserByUserNameAndPassword(userName, MD5Util.MD5Encode(password, "UTF-8"));
        if (adminUser != null) {
            //生成token
            String token = SystemUtil.genToken(System.currentTimeMillis() + "" + adminUser.getId() + NumberUtil.genRandomNum(4));
            //更新user表
            if (userDao.updateUserToken(adminUser.getId(), token) > 0) {
                //把token给设置进去
                adminUser.setUserToken(token);
                adminUser.setId(null);
                return adminUser;
            }

        }
        return null;
    }

    @Override
    public User selectByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public int save(User user) {
        //密码加密
        user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
        return userDao.addUser(user);
    }

    @Override
    public User selectByUserId(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public int updatePassword(User user) {
        return userDao.updateUserPassword(user.getId(), MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
    }
}
