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
 * @date 2020/8/12 17:10
 */
public class SecondLevelCache {

    private InputStream in;
    private SqlSessionFactory factory;

    /**
     * 获取session数据库操作对象
     * @throws IOException 异常
     */
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
    }

    /**
     * 释放资源
     * @throws IOException 异常
     */
    @After
    public void destroy() throws IOException {
        in.close();
    }


    /**
     * 测试二级缓存
     */
    @Test
    public void testFindByUid() {

        SqlSession session1 = factory.openSession();
        SqlSession session2 = factory.openSession();

        IUserDao dao1 = session1.getMapper(IUserDao.class);
        IUserDao dao2 = session2.getMapper(IUserDao.class);

        User user1 = dao1.findByUid(54);
        System.out.println("user1 ---- " +user1.hashCode());
        session1.close();


        User user2 = dao2.findByUid(54);
        System.out.println("user2 ---- " +user2.hashCode());
        session2.close();

        //判断两个对象是否一样
        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存同步
     */
//    @Test
//    public void testClearCache() throws IOException {
//        //根据id查询用户
//        User user1 = userDao.findByUid(54);
//        System.out.println("user1 ---- " +user1.hashCode());
//
//        //更新用户信息
//        user1.setUsername("Cache");
//        userDao.updateUser(user1);
//
//        //再次查询
//        User user2 = userDao.findByUid(54);
//        System.out.println("user2 ---- " +user2.hashCode());
//
//        //判断两个对象是否一样
//        System.out.println(user1 == user2);
//    }
}
