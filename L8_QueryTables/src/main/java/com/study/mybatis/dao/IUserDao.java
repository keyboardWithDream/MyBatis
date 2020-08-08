package com.study.mybatis.dao;

import com.study.mybatis.domain.User;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/5 20:04
 */
public interface IUserDao {

    /**
     * 查询所有用户 同时获取用户下所有账户信息
     * @return 用户list
     */
    List<User> findAll();


    /**
     * 查询所有用户信息, 同时获取用户所包含的角色
     * @return 用户信息
     */
    List<User> findAllWithRole();
}
