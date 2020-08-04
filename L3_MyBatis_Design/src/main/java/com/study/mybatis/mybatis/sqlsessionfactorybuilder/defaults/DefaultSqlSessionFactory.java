package com.study.mybatis.mybatis.sqlsessionfactorybuilder.defaults;

import com.study.mybatis.mybatis.cfg.Configuration;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.SqlSession;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.SqlSessionFactory;

/**
 * @author Harlan
 * @date 2020/8/4 16:31
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库的对象
     * @return 数据库操作对象
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
