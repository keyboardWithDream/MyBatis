package com.study.mybatis.mybatis.io;

import java.io.InputStream;

/**
 * @author Harlan
 * @date 2020/8/4 14:45
 * 使用类加载器读取配置文件的类
 */
public class Resources {

    /**
     * 根据传入的参数, 获取一个字节输入流
     * @param fileName 文件名
     * @return 字节输入流
     */
    public static InputStream getResourceAsStream(String fileName){
        return Resources.class.getClassLoader().getResourceAsStream(fileName);
    }
}
