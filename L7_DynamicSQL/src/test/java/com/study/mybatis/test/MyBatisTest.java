package com.study.mybatis.test;

import com.study.mybatis.dao.IUserDao;
import com.study.mybatis.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/5 6:37
 */
public class MyBatisTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

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
     * 测试单个查询操作
     */
    @Test
    public void testFindUserById(){
        User user = userDao.findUserById(56);
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
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindUserByQueryVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("传智播客");
        vo.setUser(user);

        user = userDao.findUserByVo(vo);
        System.out.println(user);
    }

    /**
     * 测试通过条件查询用户
     */
    @Test
    public void testFindUserByCondition(){
        User user = new User();
        user.setUsername("Harlan");
        user.setSex("男");
        List<User> users = userDao.findUserByCondition(user);
        for (User resultUser : users) {
            System.out.println(resultUser);
        }
    }

    /**
     * 测试通过ID集合查询用户
     */
    @Test
    public void testFindUserByIds(){
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(41);
        ids.add(45);
        ids.add(54);
        ids.add(60);
        vo.setIds(ids);

        List<User> users = userDao.findUserByIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
