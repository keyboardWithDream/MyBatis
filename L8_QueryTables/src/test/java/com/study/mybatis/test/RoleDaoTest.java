package com.study.mybatis.test;

import com.study.mybatis.dao.IRoleDao;
import com.study.mybatis.domain.Role;
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
 * @date 2020/8/7 23:23
 */
public class RoleDaoTest {

    private InputStream in;
    private SqlSession session;
    private IRoleDao dao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        session = factory.openSession();
        dao = session.getMapper(IRoleDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 测试查询所有角色, 并包含角色赋予的用户
     */
    @Test
    public void testFindAll(){
        List<Role> roles = dao.findAll();
        for (Role role : roles) {
            System.out.print(role + " ---- ");
            System.out.println(role.getUsers());
        }
    }
}
