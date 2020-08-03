package com.study.mybatis.dao;

import com.study.mybatis.domain.User;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/3 20:18
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return 用户list
     */
    List<User> findAll();
}
