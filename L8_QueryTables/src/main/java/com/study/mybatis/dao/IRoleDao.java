package com.study.mybatis.dao;

import com.study.mybatis.domain.Role;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/7 22:41
 */
public interface IRoleDao {

    /**
     * 查询所有角色 同时包含所属用户信息
     * @return 角色
     */
    List<Role> findAll();
}
