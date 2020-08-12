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
import java.util.Date;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/12 18:11
 */
public class UserDao {

    private InputStream in;
    private SqlSession session;
    private IUserDao dao;


    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        dao = session.getMapper(IUserDao.class);
    }


    @After
    public void  destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }


    @Test
    public void testFindAll(){
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("HHN");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("???????");
        dao.save(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(61);
        user.setUsername("?????");
        dao.updateUser(user);
    }

    @Test
    public void testDelete(){
        dao.delete(61);
    }

    @Test
    public void testFindById(){
        User user = dao.findById(57);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        String name = "%王%";
        List<User> users = dao.findByName(name);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int total = dao.findTotal();
        System.out.println("总共 " + total + " 条数据.");
    }
}
