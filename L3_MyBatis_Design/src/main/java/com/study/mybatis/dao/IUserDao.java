package com.study.mybatis.dao;

import com.study.mybatis.domain.User;
import com.study.mybatis.mybatis.annotations.Select;


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
    @Select("SELECT * FROM user")
    List<User> findAll();
}
