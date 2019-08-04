package com.multimodule.application.Controller;
import com.multimodule.application.pojo.User;
import com.multimodule.redisconfig.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private RedisUtils redisUtils;
    @RequestMapping("/testRedisUtil")
    public String test(){
        redisUtils.set("haha","test");
        return "测试成功";
    }
    @RequestMapping("/testJsonSerializer")
    public String testJsonSerializer(){
        User user=new User();
        user.setName("lyk");
        user.setSex("man");
        redisUtils.set("user",user);
        return "已存入redis,到redis内查看";
    }
    @RequestMapping("/testCacheable")
    @Cacheable(value="cache1",key="#root.methodName")
    public User testCacheable(){
        User user=new User();
        user.setName("lyk");
        user.setSex("man");
        return user;
    }
}
