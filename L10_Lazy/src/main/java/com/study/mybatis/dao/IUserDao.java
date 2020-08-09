package com.study.mybatis.dao;

import com.study.mybatis.domain.User;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/8 22:33
 */
public interface IUserDao {

    /**
     * 查询所有用户信息
     * @return 用户列表
     */
    List<User> findAll();

    /**
     * 通过ID查询用户
     * @param id 用户id
     * @return 用户信息
     */
    User findById(Integer id);
}
