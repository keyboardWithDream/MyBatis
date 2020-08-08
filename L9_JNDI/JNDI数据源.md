# JNDI数据源

> JNDI(Java Naming and Directory Interface,Java命名和目录接口)是[SUN公司](https://baike.baidu.com/item/SUN公司)提供的一种标准的Java命名系统接口，JNDI提供统一的[客户端](https://baike.baidu.com/item/客户端/101081)API，通过不同的访问提供者接口JNDI服务供应接口(SPI)的实现，由管理者将JNDI API映射为特定的命名服务和目录系统，使得Java[应用程序](https://baike.baidu.com/item/应用程序/5985445)可以和这些命名服务和[目录服务](https://baike.baidu.com/item/目录服务/10413830)之间进行交互。

JNDI数据结构 -- `Map`结构 (模仿`Windows` 注册表结构)

**`Windows`注册表结构**

|                      `key`: 路径 + 名称                      | `value`: 数据值 (对象) |
| :----------------------------------------------------------: | :--------------------: |
| <font color=blue>计算机\HKEY_CURRENT_USER\AppEvents\EventLabels\.Default</font> + <font color=red>DispFileName</font> |   `@mmres.dll,-5824`   |
| <font color=blue>计算机\HKEY_CURRENT_USER\AppEvents\EventLabels\ActivatingDocument</font> + <font color=red>DispFileName</font> | `@ieframe.dll,-10321`  |



**`tomcat`服务器数据源结构**

|                  **key: 字符串(`String`)**                   |   **value: 对象(Object)**   |
| :----------------------------------------------------------: | :-------------------------: |
| <u>Directory(目录)是固定的</u>, <u>Name(名称)是可以自己指定的</u> | <u>通过配置文件指定对象</u> |

***

## 配置

1. `SqlMapConfig.xml`中配置数据源

  指定数据源时 value属性 `java:comp/env` 为固定路径

  ```xml
  <!-- 配置mybatis的环境 -->
  <environments default="mysql">
   	<!-- 配置mysql的环境 -->
   	<environment id="mysql">
       	<!-- 配置事务控制的方式 -->
       	<transactionManager type="JDBC"/>
       	<!-- 配置连接数据库的必备信息  type属性表示是否使用数据源（连接池）-->
       	<dataSource type="JNDI">
              <!-- 指定数据源名称 -->
           	<property name="data_source" value="java:comp/env/jdbc/mybatis_jndi"/>
       	</dataSource>
   	</environment>
  </environments>
  ```

2. 在`webapp`目录下新建`META-INF`目录 并创建数据源信息 `context.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <Context>
       <!--
       <Resource
       name="jdbc/mybatis_jndi"                  数据源的名称
       type="javax.sql.DataSource"                   数据源类型
       auth="Container"                        数据源提供者
       maxActive="20"                         最大活动数
       maxWait="10000"                            最大等待时间
       maxIdle="5"                               最大空闲数
       username="root"                            用户名
       password="1234"                            密码
       driverClassName="com.mysql.jdbc.Driver"          驱动类
       url="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"   连接url字符串
       />
        -->
       <Resource
               name="jdbc/mybatis_jndi"
               type="javax.sql.DataSource"
               auth="Container"
               maxActive="20"
               maxWait="10000"
               maxIdle="5"
               username="root"
               password="Hhn004460"
               driverClassName="com.mysql.cj.jdbc.Driver"
               url="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"
       />
   </Context>
   ```

