package com.study.mybatis.dao;

import com.study.mybatis.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/12 17:55
 * 注解配置
 * 在MyBatis中 针对CRUD一共由四个注解
 * @Select , @Insert , @Update , @Delete
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Select("SELECT * FROM user")
    List<User> findAll();


    /**
     * 保存用户
     * @param user 用户信息
     */
    @Insert("INSERT INTO user(username, address, sex, birthday) VALUES (#{username}, #{address}, #{sex}, #{birthday})")
    void save(User user);

    /**
     * 更新用户
     * @param user 用户信息
     */
    @Update("UPDATE user SET username=#{username} WHERE id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id 用户id
     */
    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(Integer id);

    /**
     * 通过id查询用户
     * @param id 用户id
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(Integer id);

    /**
     * 通过用户名查询用户
     * @param name 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE username LIKE #{name}")
    List<User> findByName(String name);

    /**
     * 查询记录条数
     * @return 条数
     */
    @Select("SELECT COUNT(id) FROM user")
    int findTotal();
}
