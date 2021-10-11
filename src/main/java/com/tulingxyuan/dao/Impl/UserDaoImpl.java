package com.tulingxyuan.dao.Impl;

import com.tulingxyuan.dao.UserDao;
import com.tulingxyuan.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenCF on 2021/10/10
 */

@Repository
public class UserDaoImpl implements UserDao  {
    @Override
    public void add(User user) throws Exception {
        if (user == null) {
            throw new Exception("user  不能为空");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("id  不能为空");
        }

    }

    @Override
    public User select(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("id  不能为空");
        }
        return new User();
    }

    @Override
    public void update(User user) throws Exception {
        if (user == null) {
            throw new Exception("user  不能为空");
        }
    }
}
