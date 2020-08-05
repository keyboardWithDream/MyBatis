package com.study.mybatis.dao;

import com.study.mybatis.domain.User;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/5 6:28
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return 用户list
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user 用户对象
     */
    void saveUser(User user);


    /**
     * 更新用户
     * @param user 用户对象
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id 用户对象
     */
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户对象
     */
    User findUserById(Integer id);

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名称
     * @return 用户对象集
     */
    List<User> findUserByName(String username);

    /**
     * 查询总用户数
     * @return 数量
     */
    int findTotal();

}
