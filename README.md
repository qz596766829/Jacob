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
https://images-1259173443.cos.ap-chengdu.myqcloud.com/bootstrap-swagger-ui-mybatis-plus.png


1. 准备工作
准备两个数据库(此模块中两个数据库一个为本地 一个为远程，本地为主，远程为从)。然后建表。
```sql
#本地库
CREATE TABLE `username` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
#远程库
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```
2. 创建SpringBoot项目

最终目录结构：
![](https://images-1259173443.cos.ap-chengdu.myqcloud.com/multDataSourcesStructure.png)
3. 添加依赖
```xml
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
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.1.0</version>
        </dependency>

        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.1.0</version>
        </dependency>
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
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.18</version>
        </dependency>
```
4. 编写配置文件
```yml
server:
  port: 8083

swagger:
  enabled: true

mybatis:
  mapper-locations: classpath:mapper/*/*.xml,classpath:mapper/*.xml
## 主数据源
master:
  package: cn.jacob.datasource.mapper.master
  datasource:
    url: jdbc:mysql://localhost:3306/tst?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=UTC
    username: root
    password:
    driverClassName: com.mysql.cj.jdbc.Driver
## 从数据源
cluster:
    datasource:
      url: jdbc:mysql://121.36.9.198:3306/local?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=UTC
      username: root
      password:
      driverClassName: com.mysql.cj.jdbc.Driver

# -------------------------------------这一块配置不加会导致项目启动很慢-------------------------------------
# 连接池的配置信息
# 初始化大小，最小，最大
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    initialSize: 5
    minIdle: 5
    maxActive: 20
    # 配置获取连接等待超时的时间
    maxWait: 60000
    # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    timeBetweenEvictionRunsMillis: 60000
    # 配置一个连接在池中最小生存的时间，单位是毫秒
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    # 打开PSCache，并且指定每个连接上PSCache的大小
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
    # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    filters: stat,wall,log4j
    # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
# -------------------------------------------------------------------------------------------------------
```
5. 编写配置类
swagger配置类
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
                    .termsOfServiceUrl("http://localhost:8083/")
                    .contact("m15870979735@qq.com")
                    .version("1.0")
                    .build();
        }
    }
```
主数据源配置类
```java
    @Configuration
    //扫描 Mapper 接口并容器管理
    @MapperScan(basePackages = {"cn.jacob.datasource.mapper.master"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
    public class MasterDataSourceConfig {

        // 精确到 master 目录，以便跟其他数据源隔离
        static final String PACKAGE = "cn.jacob.datasource.mapper.master";
        static final String MAPPER_LOCATION = "classpath:mapper/**/*.xml";

        @Value("${master.datasource.url}")
        private String url;

        @Value("${master.datasource.username}")
        private String user;

        @Value("${master.datasource.password}")
        private String password;

        @Value("${master.datasource.driverClassName}")
        private String driverClass;

        @Primary
        @Bean(name = "masterDataSource")
        public DataSource masterDataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(driverClass);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            return dataSource;
        }

        @Bean(name = "masterTransactionManager")
        @Primary
        public DataSourceTransactionManager masterTransactionManager() {
            return new DataSourceTransactionManager(masterDataSource());
        }

        @Bean(name = "masterSqlSessionFactory")
        @Primary
        public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
                throws Exception {
            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(masterDataSource);
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources(MAPPER_LOCATION));
            return sessionFactory.getObject();
        }
    }
```
从数据源配置类
```java
    @Configuration
    //扫描 Mapper 接口并容器管理
    @MapperScan(basePackages = {"cn.jacob.datasource.mapper.cluster"}, sqlSessionFactoryRef = "clusterSqlSessionFactory")
    public class ClusterDataSourceConfig {

        // 精确到 cluster 目录，以便跟其他数据源隔离
        static final String PACKAGE = "cn.jacob.datasource.mapper.cluster";
        static final String MAPPER_LOCATION = "classpath:mapper/**/*.xml";

        @Value("${cluster.datasource.url}")
        private String url;

        @Value("${cluster.datasource.username}")
        private String user;

        @Value("${cluster.datasource.password}")
        private String password;

        @Value("${cluster.datasource.driverClassName}")
        private String driverClass;

        @Bean(name = "clusterDataSource")
        public DataSource clusterDataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(driverClass);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            return dataSource;
        }

        @Bean(name = "clusterTransactionManager")
        public DataSourceTransactionManager clusterTransactionManager() {
            return new DataSourceTransactionManager(clusterDataSource());
        }

        @Bean(name = "clusterSqlSessionFactory")
        public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
                throws Exception {
            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(clusterDataSource);
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources(MAPPER_LOCATION));
            return sessionFactory.getObject();
        }
    }
```
6. 实体类，Dao层，Service层不再概括
7. 控制层
便于区分创建两个控制器
```java

    @RestController
    @Slf4j
    @Api(value = "主数据源", tags = "主数据源")
    @RequestMapping("/username")
    @AllArgsConstructor
    public class UsernameController {

        private UsernameService usernameService;

        @GetMapping("/getList")
        @ApiOperation(value = "用户列表",notes = "用户列表")
        public List<Username> getList(){
            return usernameService.getList();
        }

    }
-----------------------------------------------------------------------------------------------------------------
    @RestController
    @Slf4j
    @Api(value = "从数据源", tags = "从数据源")
    @RequestMapping("/user")
    @AllArgsConstructor
    public class UserController {

        private UserService userService;

        @GetMapping("/getList")
        @ApiOperation(value = "用户列表",notes = "用户列表")
        public List<User> getList(){
            return userService.getList();
        }

    }
```
