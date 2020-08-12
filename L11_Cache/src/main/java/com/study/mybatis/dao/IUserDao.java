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
     * 通过用户id查询用户
     * @param uid 用户id
     * @return 用户信息
     */
    User findByUid(Integer uid);

    /**
     * 更新用户信息
     * @param user 用户
     */
    void updateUser(User user);
}
