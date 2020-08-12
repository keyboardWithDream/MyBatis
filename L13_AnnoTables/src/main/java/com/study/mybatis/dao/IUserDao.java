package com.study.mybatis.dao;

import com.study.mybatis.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/12 17:55
 * 注解配置
 * 在MyBatis中 针对CRUD一共由四个注解
 * @Select , @Insert , @Update , @Delete
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Select("SELECT * FROM user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "uId"),
            @Result(column = "username", property = "uName"),
            @Result(column = "address", property = "uAddress"),
            @Result(column = "sex", property = "uSex"),
            @Result(column = "birthday", property = "uBirthday"),
            @Result(property = "accounts", column = "id", many = @Many(select = "com.study.mybatis.dao.IAccountDao.findByUid", fetchType = FetchType.LAZY))
    })
    List<User> findAll();


    /**
     * 通过id查询用户
     * @param id 用户id
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    @ResultMap("userMap")
    User findById(Integer id);


    /**
     * 通过用户名查询用户
     * @param name 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE username LIKE #{name}")
    @ResultMap("userMap")
    List<User> findByName(String name);
}
