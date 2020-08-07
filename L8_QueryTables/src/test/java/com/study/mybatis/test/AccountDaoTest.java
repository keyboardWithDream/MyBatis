package com.study.mybatis.test;

import com.study.mybatis.dao.IAccountDao;
import com.study.mybatis.domain.Account;
import com.study.mybatis.domain.AccountUser;
import com.study.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/6 20:48
 */
public class AccountDaoTest {

    private InputStream in;
    private SqlSession session;
    private IAccountDao accountDao;

    /**
     * 获取session数据库操作对象
     * @throws IOException 异常
     */
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);
    }

    /**
     * 释放资源
     * @throws IOException 异常
     */
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查询操作
     */
    @Test
    public void testFindAll() throws IOException {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.print(account + " --- ");
            System.out.println(account.getUser());
        }
    }


    /**
     * 查询所有账户并包含用户名和地址信息
     */
    @Test
    public void testFindAllAccount(){
        List<AccountUser> accountUsers = accountDao.findAllAccount();
        for (AccountUser accountUser : accountUsers) {
            System.out.println(accountUser);
        }
    }
}
