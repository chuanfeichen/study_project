package com.tulingxyuan.dao;

import com.tulingxyuan.entity.User;

/**
 * Created by ChenCF on 2021/10/10
 */
public interface UserDao {

    void add(User user) throws Exception;
    void  delete(Integer id) throws Exception;
    User select(Integer id) throws Exception;
    void update(User user) throws Exception;
}
