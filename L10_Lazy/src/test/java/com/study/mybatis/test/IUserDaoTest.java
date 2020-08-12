package com.study.mybatis.test;

import com.study.mybatis.dao.IUserDao;
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
 * @date 2020/8/8 22:57
 */
public class IUserDaoTest {
    private InputStream in;
    private SqlSession session;
    private IUserDao dao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        dao= session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindById(){
        User user = dao.findByUid(58);
        System.out.println(user);
    }

    @Test
    public void testFindAll(){
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.print(user + " ---- ");
            System.out.println(user.getAccounts());
        }
    }
}
