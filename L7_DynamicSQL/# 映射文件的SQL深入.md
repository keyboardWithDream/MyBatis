# 映射文件的SQL深入

## 动态SQL语句

可根据条件判断动态生成SQL

***

### if标签

**语句格式**

```xml
<if test=" 条件判断 ">
	SQL语句
</if>
```

`test`属性为: 判断的条件, 当条件为true时, 添加标签中的语句

**示例**(判断user的属性值 是否为空)

```xml
<!-- 根据条件查询 -->
<select id="findUserByCondition" parameterType="user" resultType="user">
    SELECT * FROM user WHERE 1=1
    <if test="username != null">
        AND username=#{username}
    </if>
    <if test="sex != null">
        AND sex=#{sex}
    </if>
</select>
```

**!注意**: 判断当中的 '与' 必须使用 `AND`

***

### where标签

不需要在SQL语句后加 WHERE 条件

**语句格式**

```xml
<where>
    SQL语句
</where>
```

**示例**

```xml
<select id="findUserByCondition" parameterType="user" resultType="user">
    SELECT * FROM user
    <where>
        <if test="username != null">
            AND username=#{username}
        </if>
        <if test="sex != null">
            AND sex=#{sex}
        </if>
    </where>
</select>
```

***

### foreach标签

**语句格式**

```xml
<foreach collection="集合" open="语句的开始" close="语句的结束" item="元素变量名" separator="分割符">
    #{元素变量名}
</foreach>
```

`collection`属性为: 需要遍历的集合

`open`属性为: SQL 语句的开始

`close`属性为: SQL 语句的结束

`item`属性为: 集合元素的变量名 -- 相当于 `for(int i = 0; i < 10; i++){...}` 里的`i`变量

`separator`属性为: 元素间以什么字符分割

使用`#{变量名}` 可取出元素的值

以上的真实SQL语句为: `语句的开始 元素1 分割符 元素2 分割符 元素3 ... 语句结束`

**示例**(更具ID的集合 查询用户) SQL语句为 `SELECT * FROM user WHERE id in (元素1, 元素2, 元素3, ...);`

```xml
<!-- 根据QueryVo中的Id集合查询用户 -->
<select id="findUserByIds" parameterType="QueryVo" resultType="User">
    SELECT * FROM user
    <where>
        <if test="ids != null and ids.size() > 0">
            <foreach collection="ids" open="and id in (" close=")" item="uid" separator=",">
                #{uid}
            </foreach>
        </if>
    </where>
</select>
```

***

### sql标签

可以抽取重复的SQL语句, 节省代码量

**语句格式**`<sql>`

```xml
<sql id="引用id">
    SQL语句
</sql>
```

`id`属性为: SQL语句的引用名

**引用格式**`<include>`

```xml
<select id="***" resultType="***">
    <include refid="引用id"/>
</select>
```

`refid`属性为: 需要引用的SQL语句 引用名

**示例**

```xml
<sql id="select">
    SELECT * FROM user;
</sql>

<select id="findAll" resultType="user">
    <include refid="select"/>
</select>
```

