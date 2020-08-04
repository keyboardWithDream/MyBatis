package com.study.mybatis.mybatis.sqlsessionfactorybuilder.defaults;

import com.study.mybatis.mybatis.cfg.Configuration;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.SqlSession;
import com.study.mybatis.mybatis.sqlsessionfactorybuilder.proxy.MapperProxy;
import com.study.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Harlan
 * @date 2020/8/4 16:34
 * SqlSession的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        conn = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(), new Class[]{daoInterfaceClass}, new MapperProxy(cfg.getMappers(), conn));
    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
