package com.study.mybatis.mybatis.sqlsessionfactorybuilder;

/**
 * @author Harlan
 * @date 2020/8/4 15:06
 * 自定义MyBatis中和数据库交互的核类
 * 可以创建dao接口的代理对象
 */
public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T> dao类型
     * @return 代理对象
     */
    <T> T getMapper(Class<T> daoInterfaceClass);


    /**
     * 释放资源
     */
    void close();
}
