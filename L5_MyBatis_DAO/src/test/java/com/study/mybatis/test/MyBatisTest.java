package com.study.mybatis.test;

import com.study.mybatis.dao.IUserDao;
import com.study.mybatis.dao.impl.UserDaoImpl;
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
import java.util.Date;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/5 6:37
 */
public class MyBatisTest {

    private InputStream in;
    private IUserDao userDao;

    /**
     * 获取session数据库操作对象
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        userDao = new UserDaoImpl(factory);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After
    public void destroy() throws IOException {
        in.close();
    }


    /**
     * 测试查询操作
     */
    @Test
    public void testFindAll() throws IOException {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSaveUser() throws IOException {
        User user = new User();
        user.setUsername("MyBatis");
        user.setBirthday(new Date());
        user.setAddress("重庆市江北区");
        user.setSex("男");

        System.out.println("保存操作前: " + user);

        userDao.saveUser(user);

        System.out.println("保存操作后: " + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(54);
        user.setUsername("胡号南");
        user.setBirthday(new Date());
        user.setAddress("重庆市江北区");
        user.setSex("男");

        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(56);
    }

    /**
     * 测试单个查询操作
     */
    @Test
    public void testFindUserById(){
        User user = userDao.findUserById(54);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindUserByName(){
        String name = "%王%";
        //String name = "王";
        List<User> users = userDao.findUserByName(name);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询总记录条数
     * 使用聚合函数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }
}
