package com.study.mybatis.dao;

import com.study.mybatis.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author Harlan
 * @date 2020/8/12 21:22
 */
public interface IAccountDao {

    /**
     * 查询所有账户信息, 并包含用户
     * @return 账户信息列表
     */
    @Select("SELECT * FROM account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(property = "user", column = "uid", one = @One(select = "com.study.mybatis.dao.IUserDao.findById", fetchType = FetchType.EAGER))
    })
    List<Account> findAll();


    /**
     * 通过用户id查询账户
     * @param uid 用户id
     * @return 账户信息
     */
    @Select("SELECT * FROM account WHERE uid=#{uid}")
    List<Account> findByUid(Integer uid);
}
