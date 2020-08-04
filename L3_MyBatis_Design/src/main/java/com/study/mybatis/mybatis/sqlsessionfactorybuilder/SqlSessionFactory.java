package com.study.mybatis.mybatis.sqlsessionfactorybuilder;

/**
 * @author Harlan
 * @date 2020/8/4 15:03
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     * @return SqlSession对象
     */
    SqlSession openSession();
}
