package com.study.mybatis.mybatis.sqlsessionfactorybuilder;

import com.study.mybatis.mybatis.cfg.Configuration;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.defaults.DefaultSqlSessionFactory;
import com.study.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author Harlan
 * @date 2020/8/4 15:01
 * 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config 配置文件的字节输入流
     * @return SqlSessionFactory工厂
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
