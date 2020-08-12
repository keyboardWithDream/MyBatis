# `MyBatis`的延迟加载

<font color=red>**查询中存在的问题**</font>

<u>前提: 在一对多的关系中, 有一个用户, 对应100个账户</u>

> 在查询用户的时候, 是否需要把关联的账户查询出来?
>
> 在查询账户的时候, 是否需要把关联的用户查询出来?

<font color=green>**如何查询**</font>

> 在查询用户时, 用户下的账户应该什么时候使用, 什么时候查询 <font color=blue>(**按照需求加载**)</font>
>
> 在查询账户时, 账户的所属用户信息应该时随着账户查询时一起查询出来<font color=blue>(**立即加载**)</font>

在查询用户时, 是按照需求查询该用户下的账户信息, 体现了**延迟加载**.

两种查询的模式因为查询数据的时机而不同.

***



## 延迟加载&立即加载

### 延迟加载

在真正使用数据时才发起查询, 不用的时候不查询, 按需求加载 <font color=red>(**懒加载**)</font>

### 立即加载

不管用不用, 只要一调用方法, 马上发起查询

### 对应四种表关系

|     表关系     |       加载模式       |
| :------------: | :------------------: |
| 多对一, 一对一 | 通常采用**立即加载** |
| 一对多, 多对多 | 通常采用**延迟加载** |

***



## 对一: `<association>`标签下使用延迟加载

`SqlMapConfig.xml`下配置是否开启延迟加载:

```xml
<!-- 配置参数 -->
    <settings>
        <!-- 开启MyBatis支持延迟加载 -->
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="aggressiveLazyLoading" value="false"/>
    </settings>
```

| 设置名                  | 描述                                                         | 有效值        | 默认值                                       |
| :---------------------- | :----------------------------------------------------------- | :------------ | :------------------------------------------- |
| `lazyLoadingEnabled`    | 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 `fetchType` 属性来覆盖该项的开关状态。 | true \| false | false                                        |
| `aggressiveLazyLoading` | 开启时，任一方法的调用都会加载该对象的所有延迟加载属性。 否则，每个延迟加载属性会按需加载（参考 `lazyLoadTriggerMethods`)。 | true \| false | false （在 3.4.1 及之前的版本中默认为 true） |



`Mapper.xml`下配置`resultMap`:

`<association>`中 

`select` 属性取值为查询的方法: 全限定类名+方法名

`column` 属性取值为查询的参数

```xml
<resultMap id="accountMap" type="Account">
        <id property="id" column="id"/>
        <result property="uid" column="uid"/>
        <result property="money" column="money"/>

        <!--
        select 属性指定的内容为: 查询用户的唯一标识方法
        column 属性指定的内容为: 方法所需要的参数值
        -->
        <association property="user" column="uid" javaType="User" select="com.study.mybatis.dao.IUserDao.findById">
            <id property="id" column="uid"/>
            <result property="username" column="username"/>
            <result property="sex" column="sex"/>
            <result property="address" column="address"/>
            <result property="birthday" column="birthday"/>
        </association>
    </resultMap>
```

***



## 对多: `<association>`标签下使用延迟加载

`SqlMapConfig.xml`中配置全局延迟加载 同上

`Mapper.xml`下配置`resultMap`:

`<association>`中 

`select` 属性取值为查询的方法: 全限定类名+方法名

`column` 属性取值为查询的参数

```xml
<resultMap id="userMap" type="User">
        <id property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="birthday" column="birthday"/>
        <result property="sex" column="sex"/>
        <result property="address" column="address"/>
        <collection property="accounts" ofType="Account" select="com.study.mybatis.dao.IAccountDao.findByUid" column="id">
            <id property="id" column="aid"/>
            <result property="uid" column="uid"/>
            <result property="money" column="money"/>
        </collection>
    </resultMap>
```

