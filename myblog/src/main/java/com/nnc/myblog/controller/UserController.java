package com.nnc.myblog.controller;


import com.nnc.myblog.common.lang.Result;
import com.nnc.myblog.entity.User;
import com.nnc.myblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2022-11-06
 */
@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;
    @RequiresAuthentication
    @GetMapping("/index")

    public Result index(){
       User user=userService.getById(1);
       return  Result.succ(user);
    }

    @PostMapping("/save")

    public Result save(@Validated @RequestBody User user){

        return  Result.succ(user);
    }
}
