package com.multimodule.application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//通过扫描其他模块的包注解，其他包的bean(@SpringBootApplication包含的有@ComponentScan注解，默认扫描的是
// 启动类所在的包及所在包的子包)
@ComponentScan(basePackages = {"com.multimodule.application","com.multimodule.redisconfig"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
