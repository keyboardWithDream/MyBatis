package com.study.mybatis.dao;

import com.study.mybatis.domain.User;
import com.study.mybatis.mybatis.io.Resources;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.SqlSession;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.SqlSessionFactory;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/3 20:22
 * MyBatis入门案例
 */
public class UserDaoTest {

    public static void main(String[] args) throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口代理
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
