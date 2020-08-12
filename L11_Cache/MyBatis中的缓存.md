# `MyBatis`中的缓存

## 什么是缓存

存在于内存中的临时数据. 使用缓存可以减少和数据库交互次数, 提高执行效率

## 什么数据适用缓存

经常查询的数据并且不经常改变的

数据的正确与否对最终结果影响不大的

<font color=blue>不适用于缓存的数据:</font>

1. 经常改变的数据
2. 数据的正确与否对最终结果影响很大

***



## 一级缓存

<font color=red>**`MyBatis`中的一级缓存指`SqlSession`对象的缓存**</font>.

**一级缓存中存放的是对象**

当我们执行查询后, 查询的结果会同时存入到`SqlSession`为我们提供的一块区域中

该区域的结构是一个Map, 当我们在次查询同样的数据, `MyBatis`会先去`SqlSession`中查询是否有, 如果有则取出

当`SqlSession`对象消失时, `MyBatis`的一级缓存也就消失了



### 测试一级缓存

`SqlSession`对象的`clearCache()`方法用于清空一级缓存

测试结果为`false`不是同一个对象, 如果没有调用`clearCache()`方法则为`true`

```java
/**
* 测试一级缓存
*/
@Test
public void testFindByUid() throws IOException {
	User user1 = userDao.findByUid(54);
	System.out.println("user1 ---- " +user1.hashCode());
        
    //清除SqlSession缓存
	session.clearCache();

	User user2 = userDao.findByUid(54);
	System.out.println("user2 ---- " +user2.hashCode());

	//判断两个对象是否一样
	System.out.println(user1 == user2);
}
```

<font color=red>**!注意:**</font><u>当`SqlSession`对象调用了`update()`, `delete()`, `inster()`, `commit()` 方法后会自动清除一级缓存</u>.

***



## 二级缓存

<font color=red>**`MyBatis`中的二级缓存指`SqlSessionFactory`对象的缓存**</font>.

**由同一个`SqlSessioFactory`对象创建的`SqlSeesion`对象共享其缓存**

**二级缓存中存放的是数据而不是对象**

### 二级缓存的使用步骤

1. 在`SqlMapConfig.xml`中配置, 使`MyBatis`支持二级缓存
	| 设置名         | 描述                                                     | 有效值        | 默认值 |
| :------------- | :------------------------------------------------------- | :------------ | :----- |
| `cacheEnabled` | 全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。 | true \| false | true   |

	```xml
	<!-- 配置信息 -->
	<settings>
		<setting name="cacheEnabled" value="true"/>
	</settings>
	```
	
	
	
2. 在`IUserMapper.xml`中配置, 使映射文件支持二级缓存

   ```xml
   <!-- 开启支持二级缓存 -->
   <cache/>
   ```

   

3. 在`<select>`标签中配置, 使当前操作支持二级缓存

   `userCache="true"`

   ```xml
   <select id="findByUid" resultType="User" parameterType="int" useCache="true">
       SELECT * FROM user WHERE id=#{id}
   </select>
   ```



### 测试二级缓存

`session1` 和 `session2` 使用的是同一个 `SqlSessionFactory`对象

结果为`false` 因为`SqlSessionFactory` 存入的是数据不是对象

`session`将数据封装 返回新的对象

```java
/**
 * 测试二级缓存
 */
@Test
public void testFindByUid() {

    SqlSession session1 = factory.openSession();
    SqlSession session2 = factory.openSession();

    IUserDao dao1 = session1.getMapper(IUserDao.class);
    IUserDao dao2 = session2.getMapper(IUserDao.class);

    User user1 = dao1.findByUid(54);
    System.out.println("user1 ---- " +user1.hashCode());
    session1.close();


    User user2 = dao2.findByUid(54);
    System.out.println("user2 ---- " +user2.hashCode());
    session2.close();

    //判断两个对象是否一样
    System.out.println(user1 == user2);
}
```