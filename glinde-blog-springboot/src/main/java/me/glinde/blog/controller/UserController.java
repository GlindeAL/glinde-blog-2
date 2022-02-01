package me.glinde.blog.controller;

import java.util.Arrays;
import java.util.Map;

import me.glinde.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import me.glinde.blog.entity.UserEntity;
import me.glinde.blog.service.UserService;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户
 *
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserEntity user, HttpServletRequest request){
        return userService.login(user,request);
    }

}
