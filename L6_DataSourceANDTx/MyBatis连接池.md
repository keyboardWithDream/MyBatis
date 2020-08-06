# `MyBatis`连接池

使用连接池可以减少获取连接所消耗的时间

## `MyBatis`的3种连接池配置方式

**配置位置**:`SqlMapConfig.xml`中的`dataSource`标签, `type`属性表示了采用何种连接池方式.

`type`属性的取值:

1. `POOLED` : 采用传统的`javax.sql.DataSource`规范中的连接池, `MyBatis`中针对规范的实现.
2. `UNPOOLED` : 采用传统的获取连接的方式, 虽然实现类`javax.sql.DataSource`接口, 但没有使用池的思想.
3. `JNDI` : 采用服务器提供的`JNDI`实现, 来获取`DataSource`对象, 不同的服务器所能拿到的`DataSource`时不一样的 ----! 注意: 如果不是`web`或者`maven`的`war`工程, 是不能使用的, `tomcat`服务器所采用的是`dbcp`连接池.

