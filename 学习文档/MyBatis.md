# 1. MyBatis 核心概念

## 1.1 SQL 映射配置文件

- MyBatis 使用 XML 文件来配置和映射 SQL 语句。
- 每个映射文件需要对应一个 Mapper 接口。

## 1.2 SqlSessionFactory

- MyBatis 通过 `SqlSessionFactory` 来创建 `SqlSession` 实例。
- `SqlSession` 提供了执行 SQL 命令的所有方法，并且能通过 Mapper 接口执行映射的 SQL 语句。

## 1.3 Mapper 接口

- Mapper 接口是 MyBatis 的核心，用于定义 SQL 语句对应的方法。
- 方法的实现由 MyBatis 自动生成。

# 2. 配置 MyBatis

## 2.1 引入依赖

在 `pom.xml` 中添加 MyBatis 相关依赖。

```java
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.0</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.25</version>
</dependency>
```

## 2.2 配置数据库连接

在 `application.properties` 中配置数据库连接信息。

```java
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
mybatis.mapper-locations=classpath:mapper/*.xml
```

# 3. 定义实体类

```java
public class Student {
    private Long id;
    private String name;
    private int age;

    // getters and setters
}
```

# 4. 创建 Mapper 接口和 XML 文件

## 4.1 创建 Mapper 接口

- `@Mapper` 注解：标识该接口为 MyBatis 的 Mapper 接口，Spring 会自动扫描到它并将其作为一个 Bean 注入到 Spring 容器中。

```java
java复制代码import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findStudentById(Long id);

    void insertStudent(Student student);

    List<Student> findAllStudents();

    void deleteStudent(Long id);
}
```

## 4.2 创建 Mapper XML 文件

在 `resources/mapper` 目录下创建 `StudentMapper.xml`。

- `namespace`：指定对应的 Mapper 接口全限定名。

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.demo.mapper.StudentMapper">

    <select id="findStudentById" parameterType="Long" resultType="Student">
        SELECT * FROM students WHERE id = #{id}
    </select>

    <insert id="insertStudent" parameterType="Student">
        INSERT INTO students (name, age) VALUES (#{name}, #{age})
    </insert>

    <select id="findAllStudents" resultType="Student">
        SELECT * FROM students
    </select>

    <delete id="deleteStudent" parameterType="Long">
        DELETE FROM students WHERE id = #{id}
    </delete>

</mapper>
```

# 5. Spring Boot 配置

## 5.1 主应用程序

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyBatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisDemoApplication.class, args);
    }
}
```

## 5.2 配置 MyBatis

在 `resources/application.properties` 中添加 MyBatis 配置。

```java
mybatis.type-aliases-package=com.example.demo.entity
mybatis.mapper-locations=classpath:mapper/*.xml
```

# 6. 编写服务类

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public Student findStudentById(Long id) {
        return studentMapper.findStudentById(id);
    }

    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    public List<Student> findAllStudents() {
        return studentMapper.findAllStudents();
    }

    public void deleteStudent(Long id) {
        studentMapper.deleteStudent(id);
    }
}
```

# 7. 深入学习 MyBatis

## 7.1 动态 SQL

- MyBatis 提供了强大的动态 SQL 功能，可以根据条件生成不同的 SQL 语句。
- 使用 `<if>`、`<choose>`、`<when>`、`<otherwise>`、`<trim>`、`<where>` 和 `<set>` 等标签来编写动态 SQL。

```java
<select id="findStudentsByCondition" parameterType="map" resultType="Student">
    SELECT * FROM students
    <where>
        <if test="name != null and name != ''">
            AND name = #{name}
        </if>
        <if test="age != null">
            AND age = #{age}
        </if>
    </where>
</select>
```

## 7.2 MyBatis 缓存

- MyBatis 提供了一级缓存和二级缓存。
- 一级缓存是 SqlSession 级别的缓存，默认开启。二级缓存是 Mapper 级别的缓存，需要显式配置。

### 7.2.1 一级缓存

- 在同一个 SqlSession 对象内，执行相同的 SQL 查询时，如果缓存中有数据，会直接从缓存中获取数据，而不再访问数据库。
- 每次查询操作，MyBatis 会先检查一级缓存中是否存在对应的缓存数据，如果存在则直接返回缓存数据，否则执行查询并将结果存入一级缓存。
- 当 SqlSession 执行 `insert`、`update`、`delete` 操作时，一级缓存会被清空，以确保数据的一致性。

**一级缓存的示例**

假设我们有一个 `StudentMapper` 接口和相应的 XML 配置文件。

```java
@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM students WHERE id = #{id}")
    Student findStudentById(Long id);
}
```

在服务类中使用 `SqlSession` 进行查询操作。

```java
@Service
public class StudentService {
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public Student findStudentById(Long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            Student student1 = studentMapper.findStudentById(id);
            Student student2 = studentMapper.findStudentById(id);
            return student1;
        }
    }
}
```

在上面的代码中，第一次查询会从数据库中获取数据并缓存到一级缓存中，第二次查询会直接从缓存中获取数据。

### 7.2.2 二级缓存

- 二级缓存是 Mapper 级别的缓存，需要显式配置。
- 不同 SqlSession 共享同一个二级缓存。
- 二级缓存的作用范围比一级缓存更广，可以跨 SqlSession 共享数据。

**工作原理：**

- 二级缓存通过缓存接口 `Cache` 来实现，MyBatis 提供了 `PerpetualCache` 作为默认的缓存实现，并提供了 `LRU`（Least Recently Used，最近最少使用）算法。
- 当查询数据时，如果一级缓存没有命中，会检查二级缓存，二级缓存有数据则返回，没有数据则从数据库查询并存入二级缓存。
- 当执行 `insert`、`update`、`delete` 操作时，对应的二级缓存会被清空。
- 在 Mapper XML 文件中配置 `<cache>` 标签启用二级缓存。

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.demo.mapper.StudentMapper">

    <cache />

    <select id="findStudentById" parameterType="Long" resultType="Student">
        SELECT * FROM students WHERE id = #{id}
    </select>
</mapper>
```

**使用二级缓存**

在服务类中，通过不同的 SqlSession 进行查询操作。

```java
@Service
public class StudentService {
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public Student findStudentById(Long id) {
        Student student1;
        Student student2;
        try (SqlSession sqlSession1 = sqlSessionFactory.openSession()) {
            StudentMapper studentMapper1 = sqlSession1.getMapper(StudentMapper.class);
            student1 = studentMapper1.findStudentById(id);
        }
        try (SqlSession sqlSession2 = sqlSessionFactory.openSession()) {
            StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);
            student2 = studentMapper2.findStudentById(id);
        }
        return student2;
    }
}
```

在上面的代码中，第一次查询通过 `sqlSession1` 获取数据并缓存到二级缓存中，第二次查询通过 `sqlSession2` 直接从二级缓存中获取数据。

## 7.3 分页插件

- MyBatis 需要通过插件实现分页功能，例如 MyBatis-PageHelper 插件。

### 7.3.1 引入依赖

在 `pom.xml` 中添加分页插件依赖。

```java
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.4.2</version>
</dependency>
```

### 7.3.2 配置分页插件

在 `application.properties` 中配置分页插件。

```java
pagehelper.helperDialect=mysql
pagehelper.reasonable=true
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql
```

### 7.3.3 使用分页插件

在服务类中使用 `PageHelper` 进行分页查询。

```java
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public PageInfo<Student> findStudentsByPage(int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<Student> students = studentMapper.findAllStudents();
    return new PageInfo<>(students);
}
```

## 7.4 自定义类型处理器

- MyBatis 提供了 `TypeHandler` 接口来处理自定义类型的转换。

### 7.4.1 实现自定义类型处理器

创建一个自定义类型处理器类，实现 `TypeHandler` 接口。

```java
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomTypeHandler implements TypeHandler<CustomType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, CustomType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public CustomType getResult(ResultSet rs, String columnName) throws SQLException {
        return CustomType.valueOf(rs.getString(columnName));
    }

    @Override
    public CustomType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return CustomType.valueOf(rs.getString(columnIndex));
    }

    @Override
    public CustomType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return CustomType.valueOf(cs.getString(columnIndex));
    }
}
```

### 7.4.2 注册自定义类型处理器

在 MyBatis 配置文件中注册自定义类型处理器。

```java
<typeHandlers>
    <typeHandler handler="com.example.demo.handler.CustomTypeHandler"/>
</typeHandlers>
```