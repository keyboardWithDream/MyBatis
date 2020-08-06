package com.study.mybatis.dao;

import com.study.mybatis.domain.QueryVo;
import com.study.mybatis.domain.User;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/5 20:04
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return 用户list
     */
    List<User> findAll();

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
     * 根据queryVo中的条件查询用户
     * @param vo 查询条件
     * @return 用户对象
     */
    User findUserByVo(QueryVo vo);

    /**
     * 根据条件查询用户
     * @param user 用户信息条件(可能不全)
     * @return 用户列表
     */
    List<User> findUserByCondition(User user);


    /**
     * 根据QueryVo中的id集合查询用户信息
     * @param vo 信息
     * @return 用户列表
     */
    List<User> findUserByIds(QueryVo vo);
}
