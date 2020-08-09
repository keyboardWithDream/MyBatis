package com.study.mybatis.test;

import com.study.mybatis.dao.IAccountDao;
import com.study.mybatis.dao.IUserDao;
import com.study.mybatis.domain.Account;
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
 * @date 2020/8/8 23:53
 */
public class IAccountDaoTest {
    private InputStream in;
    private SqlSession session;
    private IAccountDao dao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        dao= session.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<Account> accounts = dao.findAll();
        for (Account account : accounts) {
            System.out.print(account + " ---- ");
            System.out.println(account.getUser());
        }
    }
}
