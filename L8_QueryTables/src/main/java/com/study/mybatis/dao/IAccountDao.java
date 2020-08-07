package com.study.mybatis.dao;

import com.study.mybatis.domain.Account;
import com.study.mybatis.domain.AccountUser;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/6 20:40
 */
public interface IAccountDao {

    /**
     * 查询所有账户
     * @return 账户列表
     */
    List<Account> findAll();

    /**
     * 查询所有账户, 并且带有用户名称和地址
     * @return 信息
     */
    List<AccountUser> findAllAccount();
}
