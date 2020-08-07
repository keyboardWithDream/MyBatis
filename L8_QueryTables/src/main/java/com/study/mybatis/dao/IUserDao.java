package com.study.mybatis.dao;

import com.study.mybatis.domain.User;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/5 20:04
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return 用户list
     */
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户对象
     */
    User findUserById(Integer id);
}
