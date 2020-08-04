package com.study.mybatis.utils;

import com.study.mybatis.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Harlan
 * @date 2020/8/4 16:57
 * 创建数据源的工具类
 */
public class DataSourceUtil {
    /**
     * 用于获取连接
     * @param cfg 配置信息
     * @return 连接对象
     */
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
