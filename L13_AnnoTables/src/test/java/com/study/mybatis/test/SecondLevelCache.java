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

/**
 * @author Harlan
 * @date 2020/8/12 22:23
 */
public class SecondLevelCache {

    private InputStream in;
    private SqlSession session;
    private SqlSessionFactory factory;
    private IUserDao dao;


    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        session = factory.openSession();
        dao = session.getMapper(IUserDao.class);
    }


    @After
    public void  destroy() throws IOException {
        in.close();
    }
    @Test
    public void testCache(){
        User user1 = dao.findById(54);
        System.out.println("user1 ---- " + user1.hashCode());

        //释放一级缓存
        session.close();

        SqlSession session1 = factory.openSession();
        IUserDao dao1 = session1.getMapper(IUserDao.class);

        User user2 = dao1.findById(54);
        System.out.println("user2 ---- " + user2.hashCode());

        System.out.println(user1 == user2);
        session1.close();
    }
}
