package com.study.mybatis.dao.impl;

import com.study.mybatis.dao.IUserDao;
import com.study.mybatis.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/5 11:51
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession调用selectList()方法 , 参数为获取配置信息的key
        List<User> users = session.selectList("com.study.mybatis.dao.IUserDao.findAll");
        //3.释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession调用方法 , 参数为获取配置信息的key
        session.insert("com.study.mybatis.dao.IUserDao.saveUser", user);
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession调用方法 , 参数为获取配置信息的key
        session.update("com.study.mybatis.dao.IUserDao.updateUser", user);
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    public void deleteUser(Integer id) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession调用方法 , 参数为获取配置信息的key
        session.delete("com.study.mybatis.dao.IUserDao.deleteUser", id);
        //3.提交事务
        session.commit();
        //4.释放资源
        session.close();
    }

    public User findUserById(Integer id) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession调用方法 , 参数为获取配置信息的key
        User user = session.selectOne("com.study.mybatis.dao.IUserDao.findUserById", id);
        //4.释放资源
        session.close();
        return user;
    }

    public List<User> findUserByName(String username) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession调用方法 , 参数为获取配置信息的key
        List<User> users = session.selectList("com.study.mybatis.dao.IUserDao.findUserByName", username);
        //4.释放资源
        session.close();
        return users;
    }

    public int findTotal() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession调用方法 , 参数为获取配置信息的key
        int total = session.selectOne("com.study.mybatis.dao.IUserDao.findTotal");
        //4.释放资源
        session.close();
        return total;
    }
}
