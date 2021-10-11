package com.tulingxyuan.service;

import com.tulingxyuan.entity.User;

/**
 * Created by ChenCF on 2021/10/8
 */
public interface UserService {

    void add(User user) throws Exception;
    void  delete(Integer id) throws Exception;
    User select(Integer id) throws Exception;
    void update(User user) throws Exception;
}
