# `properties`引用标签

可用于引用jdbcConfig.properties外部配置信息

`resource`属性(常用): 用于指定配置文件的位置,也可以通过属性引用外部配置文件信息

`url`属性: 要求按照url的写法来写地址 协议+主机+端口+URI



**jdbcConfig.properties**文件

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC
jdbc.username=root
jdbc.password=Hhn004460
```

对其的引用

**resource**写法:

```xml
<properties resource="jdbcConfig.properties"/>
```

**url**写法

```xml
<properties url="file:///E:/Code/MyBatis/L4_MyBatis_CRUD/src/main/resources/jdbcConfig.properties"/>
```

***



# `typeAliases`别名标签

使用`typeAliases`配置别名, 只能配置domain中的别名

## 第一种别名: `typeAlias` 标签

`typeAlias`用于配置别名 `type`属性指定的是实体类全限定类名, `alias`属性指定别名

当指定了别名就不区分大小写

```xml
<!-- 使用typeAliases配置别名, 只能配置domain中的别名 -->
<typeAliases>
    <!-- typeAlias用于配置别名 type属性指定的是实体类全限定类名 alias属性指定别名 当指定了别名就不区分大小写 -->
    <typeAlias type="com.study.mybatis.domain.User" alias="user"/>
</typeAliases>
```



## 第二种别名: `package` 标签

`package`用于指定要配置别名的包, 当指定后,该包下的实体类都会注册别名, 并且类名就是别名, 不再区分大小写

```xml
<typeAliases>
    <!-- package用于指定要配置别名的包, 当指定后,该包下的实体类都会注册别名, 并且类名就是别名, 不再区分大小写 -->
    <package name="com.study.mybatis.domain"/>
</typeAliases>
```

***

`package`也同时可用于指定DAO接口所在的包, 当指定了之后就不需要再写 `mapper` 以及 `resource` 或者 `class`了

```xml
<mappers>
    <package name="com.study.mybatis.dao"/>
</mappers>
```

