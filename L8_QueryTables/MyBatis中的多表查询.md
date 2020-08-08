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

***



## 一对多

功能要求: 在查询用户的同时, 查询出用户下的所有账户

**配置实现**:

1. 在主表实体中配置 一对多关系映射, 主表实体应该包含从表实体的集合引用

   ```java
   public class User {
   
       private Integer id;
       private String username;
       private Date birthday;
       private String sex;
       private String address;
       
       //一对多关系映射, 主表实体应该包含从表实体的集合引用
       private List<Account> accounts;
       
       public List<Account> getAccounts() {
           return accounts;
       }
   
       public void setAccounts(List<Account> accounts) {
           this.accounts = accounts;
       }
   
       public Integer getId() {
           return id;
       }
   
       public void setId(Integer id) {
           this.id = id;
       }
   
       public String getUsername() {
           return username;
       }
   
       public void setUsername(String username) {
           this.username = username;
       }
   
       public Date getBirthday() {
           return birthday;
       }
   
       public void setBirthday(Date birthday) {
           this.birthday = birthday;
       }
   
       public String getSex() {
           return sex;
       }
   
       public void setSex(String sex) {
           this.sex = sex;
       }
   
       public String getAddress() {
           return address;
       }
   
       public void setAddress(String address) {
           this.address = address;
       }
   }
   ```

2. 编写`resultMap`对其封装 其中要封装`Account`实体的集合 采用`<collection>`标签

   `property`: 主表实体类中从表实体集合的引用

   `ofType`: 封装到那个实体中

   ```xml
   <!-- 定义一个User的resultMap -->
   <resultMap id="userAccountMap" type="User">
       <id property="id" column="id"/>
       <result property="username" column="username"/>
       <result property="sex" column="sex"/>
       <result property="address" column="address"/>
       <result property="birthday" column="birthday"/>
   
       <!-- 封装Accounts集合 -->
       <collection property="accounts" ofType="account">
           <id property="id" column="aid"/>
           <result property="uid" column="uid"/>
           <result property="money" column="money"/>
       </collection>
   </resultMap>
   ```

3. 方法映射关系配置:

   ```xml
   <!-- 查询所有用户并包含所有账户 -->
   <select id="findAll" resultMap="userAccountMap">
       SELECT * FROM user u LEFT JOIN account a ON u.id = a.UID
   </select>
   ```

***



## 多对多

用户和角色示例:

> 一个用户可以有多个角色
>
> 一个角色可以赋予多个用户

在数据库中体现多对多的关系, 需要使用**中间表**, 中间表中包含各自的主键, 在中间表中为外键

建立两个实体: **用户实体** 和 **角色实体**, 并在两个实体中体现多对多的关系 即包含对方的一个集合引用

<u>在实现功能时, SQL的语句编写最为关键</u>

**功能需求**: 

1. 查询用户时, 可以得到用户所包含的角色信息. 
2. 查询角色时, 可以得到角色所赋予的用户信息.

**功能实现**:



![中间表user_role](E:\Code\MyBatis\L8_QueryTables\中间表user_role.png)中间表user_role



 1. 在**用户实体**中添加**角色实体集合**的引用

    ```java
    public class User {
    
        private Integer id;
        private String username;
        private Date birthday;
        private String sex;
        private String address;
    
        /**
         * 多对多关系映射 对方表实体集合的引用
         */
        private List<Role> roles;
    
    
        public List<Role> getRoles() {
            return roles;
        }
    
        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }
    
        public Integer getId() {
            return id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getUsername() {
            return username;
        }
    
        public void setUsername(String username) {
            this.username = username;
        }
    
        public Date getBirthday() {
            return birthday;
        }
    
        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    
        public String getSex() {
            return sex;
        }
    
        public void setSex(String sex) {
            this.sex = sex;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    }
    ```

    

 2. 在**角色实体**中添加**用户实体集合**的引用

    ```java
    public class Role {
        private Integer id;
        private String name;
        private String desc;
        
        /**
         * 多对多中 对方表实体集合的引用
         */
        private List<User> users;
    
        public Integer getId() {
            return id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getDesc() {
            return desc;
        }
    
        public void setDesc(String desc) {
            this.desc = desc;
        }
    
        public List<User> getUsers() {
            return users;
        }
    
        public void setUsers(List<User> users) {
            this.users = users;
        }
    }
    
    ```

    

 3. 编写查找用户信息的SQL

    ```sql
    SELECT u.* , r.id AS rid, r.role_desc, r.role_name FROM user u
                LEFT OUTER JOIN user_role ur ON u.id = ur.UID
                LEFT OUTER JOIN role r ON r.id = ur.RID;
    ```

    

 4. 编写查找角色信息的SQL

    ```sql
    SELECT u.* , r.id AS rid, r.role_desc, r.role_name FROM role r
                LEFT OUTER JOIN user_role ur ON r.id = ur.RID
                LEFT OUTER JOIN `user` u ON u.id = ur.UID;
    ```

    

 5. 编写`resultMap`封装信息

     1. UserMapper.xml

        ```xml
        <!-- 定义用户角色查询的resultMap -->
            <resultMap id="userRoleMap" type="User">
                <id property="id" column="id"/>
                <result property="username" column="username"/>
                <result property="birthday" column="birthday"/>
                <result property="sex" column="sex"/>
                
                <!-- 封装User实体中的 Role集合 -->
                <collection property="roles" ofType="Role">
                    <id property="id" column="rid"/>
                    <result property="desc" column="ROLE_DESC"/>
                    <result property="name" column="ROLE_NAME"/>
                </collection>
            </resultMap>
        ```

        

     2. RoleMapper.xml

        ```xml
        <resultMap id="roleMap" type="Role">
                <id property="id" column="rid"/>
                <result property="name" column="ROLE_NAME"/>
                <result property="desc" column="ROLE_DESC"/>
            
            	<!-- 封装Role实体中的 User 集合 -->
                <collection property="users" ofType="User">
                    <id property="id" column="id"/>
                    <result property="username" column="username"/>
                    <result property="sex" column="sex"/>
                    <result property="birthday" column="birthday"/>
                    <result property="address" column="address"/>
                </collection>
            </resultMap>
        ```

 6. 在`dao`配置文件中添加方法映射

      1. UserMapper.xml

          ```xml
           <!-- 查询所有用户并包含角色信息 -->
              <select id="findAllWithRole" resultMap="userRoleMap">
                  SELECT u.* , r.id AS rid, r.role_desc, r.role_name FROM user u
                      LEFT OUTER JOIN user_role ur ON u.id = ur.UID
                      LEFT OUTER JOIN role r ON r.id = ur.RID
              </select>
          ```

          

      2. RoleMapper.xml

          ```xml
          <select id="findAll" resultMap="roleMap">
                  SELECT u.* , r.id AS rid, r.role_desc, r.role_name FROM role r
                      LEFT OUTER JOIN user_role ur ON r.id = ur.RID
                      LEFT OUTER JOIN `user` u ON u.id = ur.UID
              </select>
          ```

          

