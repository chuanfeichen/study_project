package com.tulingxyuan.service.Impl;

import com.tulingxyuan.dao.UserDao;
import com.tulingxyuan.entity.User;
import com.tulingxyuan.service.UserService;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenCF on 2021/10/8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) throws Exception {
        userDao.add(user);
    }


    @Override
//    @Transactional(timeout = 1)  设置请求超时
    public void delete(Integer id) throws Exception {
   //     Thread.sleep(2000);
        userDao.delete(id);
    }

    @Override
    @Logger(name = "这个是查询用户的select")
    public User select(Integer id) throws Exception {
        System.out.println("id = " + id);
        return userDao.select(id);
    }

    @Override
    public void update(User user) throws Exception {
        userDao.update(user);

    }
}
