package com.multimodule.springbootswagger2.controller;

import com.multimodule.springbootswagger2.vo.R;
import com.multimodule.springbootswagger2.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Api(value = "UserContoller")
@RestController
@RequestMapping("/user")
public class UserController {
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("/login")
    public R login(String userName, String password) {
        return new R("登录成功");
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping(value = "/{id}")
    public R getUserById(@PathVariable(value = "id") Integer id) {
        User user = new User(id, "lyk", 18, new Date());
        return new R(user);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/usersList")
    public R getUserList() {
        User user1 = new User(1, "lyk", 18, new Date());
        User user2 = new User(2, "zms", 18, new Date());
        return new R(Arrays.asList(user1, user2));
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping
    public R add(@RequestBody User user) {
        return new R(user);
    }
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping(value = "/{id}")
    public R delete (@PathVariable(value = "id") Integer id){
        return new R("删除成功");
    }
    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @PutMapping(value = "{id}")
    public R update (@PathVariable("id") Integer id, @RequestBody User user){
       return new R(user);
    }
    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi you!";
    }
}
