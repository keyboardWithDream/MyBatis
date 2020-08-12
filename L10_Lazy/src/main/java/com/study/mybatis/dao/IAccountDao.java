package com.study.mybatis.dao;

import com.study.mybatis.domain.Account;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/8 22:34
 */
public interface IAccountDao {

    /**
     * 查询所有账户信息
     * @return 账户列表
     */
    List<Account> findAll();

    /**
     * 通过用户id查询账户
     * @param uid 用户id
     * @return 账户列表
     */
    List<Account> findByUid(Integer uid);
}
