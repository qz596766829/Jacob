package cn.jacob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SwaggerApplication
 * @Description: TODO
 * @Author wzq
 * @Date 2020/2/14
 * @Version V1.0
 **/
@SpringBootApplication
//@MapperScan(basePackages = {"cn.jacob.mapper"})
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}