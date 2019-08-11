package com.multimodule.application.Controller;
import com.multimodule.application.Service.IService;
import com.multimodule.application.pojo.User;
import com.multimodule.redisconfig.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private IService service;
    @Autowired
    private RedisUtils redisUtils;

    public Controller() {
    }

    @RequestMapping("/testRedisUtil")
    public String test(){
        redisUtils.set("haha","test");
        return "测试成功";
    }
    @RequestMapping("/testJsonSerializer")
    public Object testJsonSerializer(){
        User user=new User();
        user.setName("lyk");
        user.setSex("man");
        User user2=new User();
        user2.setName("zms");
        user2.setSex("woman");
        List<User> users=new ArrayList<>();
        users.add(user);
        users.add(user2);
        //redisUtils.set("user",user);
        redisUtils.set("usersList",users);//集合可以像普通对象一样存入缓存.lSet()是一个键对应多个值。
       // redisUtils.get("user");
        return redisUtils.get("usersList");
    }
    @RequestMapping("/testCacheable")
    @Cacheable(value="cache2",key="targetClass.getName()+'.'+methodName")
    public List<User> testCacheable(){
        User user=new User();
        user.setName("lyk");
        user.setSex("man");
        User user2=new User();
        user2.setName("zms");
        user2.setSex("woman");
        List<User> users=new ArrayList<User>();
        users.add(user);
        users.add(user2);
        System.out.println("控制台没看到这行字说明从缓存中读取数据");
        return users;
    }
    @RequestMapping("/testKeyGenerator")
    @Cacheable(value="cache3",keyGenerator="keyGenerator")
    public List<User> testCacheableKey(){
        User user=new User();
        user.setName("lyk");
        user.setSex("man");
        User user2=new User();
        user2.setName("zms");
        user2.setSex("woman");
        List<User> users=new ArrayList<User>();
        users.add(user);
        users.add(user2);
        System.out.println("控制台没看到这行字说明从缓存中读取数据");
        return users;
    }
    @GetMapping("/testService")
    public User testService(){
        return service.test();
    }
}
