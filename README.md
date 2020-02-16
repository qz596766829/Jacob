# Jacob
Jacob项目整合各模块
# Jacob-swagger 模块
该模块为SpringBoot 整合 Swagger-bootstrap-ui(后更名 knife4j)
官方文档：https://doc.xiaominfo.com/guide/
1. 添加依赖
```xml
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>swagger-bootstrap-ui</artifactId>
            <version>1.9.6</version>
        </dependency>     
```
2. 添加配置类
```java
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.jacob"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://localhost:8081/")
                .contact("5967666829@qq.com")
                .version("1.0")
                .build();
    }
}
```
3. 测试
http://{ip}:{port}/doc.html

# Jacob-mbatis-plus 模块
MyBatis-Plus（简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。
官方文档：https://mp.baomidou.com/

1. 准备工作
```sql
CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```
2. 添加依赖
```xml
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.3.1.tmp</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
```
3. 配置数据源
```yml
spring:
  #数据源
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password:
    hikari:
      maximum-pool-size: 20
      minimum-idle: 2
    url: jdbc:mysql://121.36.9.198:3306/local?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&tinyInt1isBit=false
```
4. 编码
编写实体类
```java
    @Data
    public class User {
        @TableId(value = "id", type = IdType.AUTO)
        @ApiModelProperty("主键ID")
        private Long id;
        @ApiModelProperty("姓名")
        private String name;
        @ApiModelProperty("年龄")
        private Integer age;
        @ApiModelProperty("邮箱")
        private String email;
    }
```
编写mapper接口及xml。
```java
    @Mapper
    public interface UserMapper extends BaseMapper<User> {
    }
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.jacob.mapper.UserMapper">
</mapper>
```
编写service接口及实现类。
```java
    public interface UserService extends IService<User> {
    }
```
```java
    @Service
    public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    }
```
编写controller
```java
    @RestController
    @Slf4j
    @Api(value = "用户管理", tags = "用户管理")
    @RequestMapping("/user")
    @AllArgsConstructor
    public class UserController {

        private UserService userService;

        @GetMapping("/list")
        @ApiOperation(value = "用户列表",notes = "用户列表")
        public List<User> list(){
            return userService.list();
        }

    }
```
5. 测试
http://{ip}:{port}/doc.html
![](https://images-1259173443.cos.ap-chengdu.myqcloud.com/bootstrap-swagger-ui-mybatis-plus.png)
