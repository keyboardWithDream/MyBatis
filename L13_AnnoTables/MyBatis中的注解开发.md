# `MyBatis`中的注解开发

## 注解的使用

在`MyBatis`种如果使用注解开发, 则不需要编写`Mapper.xml`配置文件

并且在`SqlMapConfig.xml`中 `<mappers>`标签中的标签需使用`<package>` 或 `<mapper class="">`, 不能使用`<mapper resource="">`配置(指定配置文件配置)

<font color=red>**在使用注解时, `Mapper.xml`配置文件不能同时存在, 否则报错**</font>

## CRUD注解

**`MyBatis`中的CRUD注解有4种: `@Select` , `@Insert` , `@Update` , `@Delete`**

1. `@Select`注解, 查询操作

   ```java
   /**
   * 查询所有用户
   * @return 用户列表
   */
   @Select("SELECT * FROM user")
   List<User> findAll();
   ```

2. `@Insert`注解, 插入操作

   ```java
   /**
   * 保存用户
   * @param user 用户信息
   */
   @Insert("INSERT INTO user(username, address, sex, birthday) VALUES (#{username}, #{address}, #{sex}, #{birthday})")
   void save(User user);
   ```

3. `@Update`注解, 更新操作

   ```java
   /**
   * 更新用户
   * @param user 用户信息
   */
   @Update("UPDATE user SET username=#{username} WHERE id=#{id}")
   void updateUser(User user);
   ```

4. `@Delete`注解, 删除操作

   ```java
   /**
   * 删除用户
   * @param id 用户id
   */
   @Delete("DELETE FROM user WHERE id=#{id}")
   void delete(Integer id);
   ```

***



## `@Results`注解

解决实体类与数据表中字段名不一致的问题, 相当于`Mapper.xml`配置文件中的`<ResultMap>`标签功能

`id` : 指定`@Results`的id, 可用于重复使用注解信息

`value` : 其中包含一个 `@Result` 数组

`@Result` 中 `id` 指定是否为主键, `column` 指定表中字段名, `property` 指定实体类属性名

```java
/**
* 查询所有用户
* @return 用户列表
*/
@Select("SELECT * FROM user")
@Results(id = "userMap", value = {
    @Result(id = true, column = "id", property = "uId"),
    @Result(column = "username", property = "uName"),
    @Result(column = "address", property = "uAddress"),
    @Result(column = "sex", property = "uSex"),
    @Result(column = "birthday", property = "uBirthday")
})
List<User> findAll();
```



**通过`@Results`中的`id`属性值重复使用注解**

`@ResultMap` 中的 `value` 属性为一个 `String`数组

可通过`value` 指定 多个`@Result` 的`id` 值来使用注解

```java
/**
* 通过id查询用户
* @param id 用户id
* @return 用户信息
*/
@Select("SELECT * FROM user WHERE id=#{id}")
@ResultMap("userMap")
User findById(Integer id);
```

***



## 对一

`@Result`注解中 `one`属性值指定 对方实体的封装

**`@One`注解**

1. `select` : 指定对方查询的唯一标识方法
2. `fetchType`: 指定加载类型(延迟/ 立即)

```java
/**
* 查询所有账户信息, 并包含用户
* @return 账户信息列表
*/
@Select("SELECT * FROM account")
@Results(id = "accountMap", value = {
    @Result(id = true, column = "id", property = "id"),
    @Result(column = "uid", property = "uid"),
    @Result(column = "money", property = "money"),
    @Result(property = "user", column = "uid", one = @One(select = "com.study.mybatis.dao.IUserDao.findById", fetchType = FetchType.EAGER))
})
List<Account> findAll();
```

***

## 对多

`many`属性

`@Many`注解:

1. `select`: 指定查询方法
2. `fetchType`: 指定加载类型(延迟/ 立即)

```java
/**
 * 查询所有用户
 * @return 用户列表
 */
@Select("SELECT * FROM user")
@Results(id = "userMap", value = {
        @Result(id = true, column = "id", property = "uId"),
        @Result(column = "username", property = "uName"),
        @Result(column = "address", property = "uAddress"),
        @Result(column = "sex", property = "uSex"),
        @Result(column = "birthday", property = "uBirthday"),
        @Result(property = "accounts", column = "id", many = @Many(select = "com.study.mybatis.dao.IAccountDao.findByUid", fetchType = FetchType.LAZY))
})
List<User> findAll();
```

***



## 缓存配置

**开启二级缓存**

在`Dao`中加入注解 `@@CacheNamespace` , 设置`blocking = true`

```java
@CacheNamespace(blocking = true)
public interface IUserDao {
	...
}
```

***

