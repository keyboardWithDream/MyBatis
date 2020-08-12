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
 * @date 2020/8/5 6:37
 */
public class FistLevelCache {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;
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
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
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


    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFindByUid() throws IOException {
        User user1 = userDao.findByUid(54);
        System.out.println("user1 ---- " +user1.hashCode());

        //清除SqlSession缓存
        session.clearCache();

        User user2 = userDao.findByUid(54);
        System.out.println("user2 ---- " +user2.hashCode());

        //判断两个对象是否一样
        System.out.println(user1 == user2);
    }

    /**
     * 测试一级缓存同步
     */
    @Test
    public void testClearCache() throws IOException {
        //根据id查询用户
        User user1 = userDao.findByUid(54);
        System.out.println("user1 ---- " +user1.hashCode());

        //更新用户信息
        user1.setUsername("Cache");
        userDao.updateUser(user1);

        //再次查询
        User user2 = userDao.findByUid(54);
        System.out.println("user2 ---- " +user2.hashCode());

        //判断两个对象是否一样
        System.out.println(user1 == user2);
    }
}
