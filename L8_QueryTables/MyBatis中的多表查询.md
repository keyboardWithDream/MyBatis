# `MyBatis`中的多表查询

## 表中的关系

**一对一**: 一个人只有一张身份证

**一对多**：一个人可以有N张信用卡

**多对一**：N张信用卡属于一个人

**多对多**：一个学生可以被多个老师教过 && 一个老师教过多个学生

`MyBatis`中把**多对一**处理为**一对一**

***

## 多对一 (一对一)

用户和账户的示例：

> 一个用户可以有多个账户
>
> 一个账户只能属于一个用户 （多个账户也可以属于同一个用户）

建立两张表:  <u>用户表</u>  ---  <u>账户表</u>  ,让两张表之间具有一对多的关系: 需要使用外键在账户表中添加

建立两个实体类:  <u>用户实体类</u> ---- <u>账户实体类</u> ,让用户和账户的实体类能体现一对多的关系

建立两个配置文件: <u>用户配置文件</u> --- <u>账户配置文件</u>

**要求**:

1. 当我们查询用户时, 可以同时得到用户下所包含的账户信息
2. 当我们查询账户时, 可以同时得到账户的所属用户信息

配置实现:

 1. 从表实体应该包含一个主表实体的对象引用(`Account`实体 包含 `User`引用)

    ```java
    public class Account implements Serializable {
    
        private Integer id;
        private Integer uid;
        private Double money;
        
        //从表实体应该包含一个主表实体的对象引用
        private User user;
    
        public User getUser() {
            return user;
        }
    
        public void setUser(User user) {
            this.user = user;
        }
    
        public Integer getId() {
            return id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public Integer getUid() {
            return uid;
        }
    
        public void setUid(Integer uid) {
            this.uid = uid;
        }
    
        public Double getMoney() {
            return money;
        }
    
        public void setMoney(Double money) {
            this.money = money;
        }
    }
    ```

	2. 编写SQL语句, 应起别名来区分主键和外键

    ```sql
    SELECT a.*, u.username, u.address FROM account a, user u WHERE a.UID = u.id
    ```

	3. 定义封装`Account`实体的`resultMap`

    因为其中包含`User`实体, 必须对实体进行封装 采用 `<association>`标签配置

    `property`: `Account`实体中对应`User`实体的变量名

    `column`: 从表和主表关联的字段(此处有别名)

    `javaType`: 封装到哪个对象中

    ```xml
    <!-- 定义封装Account的User的resultMap -->
    <resultMap id="accountUserMap" type="Account">
        <id property="id" column="aid"/>
        <result property="uid" column="uid"/>
        <result property="money" column="money"/>
        
        <!-- 一对一的关系映射, 配置封装user的内容 -->
        <association property="user" column="uid" javaType="User">
            <id property="id" column="id"/>
            <result property="username" column="username"/>
            <result property="address" column="address"/>
            <result property="sex" column="sex"/>
            <result property="birthday" column="birthday"/>
        </association>
    </resultMap>
    ```

	4. 方法映射关系配置

    ```xml
    <!-- 查询所有账户包含姓名和地址 -->
    <select id="findAll" resultMap="accountUserMap">
        SELECT a.*, u.username, u.address FROM account a, user u WHERE a.UID = u.id
    </select>
    ```