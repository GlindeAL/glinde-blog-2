package me.glinde.blog.controller;

import me.glinde.blog.dto.LoginForm;
import me.glinde.blog.service.UserService;
import me.glinde.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户
 *
 * @author glinde
 * @date 2022-01-28 22:26:41
 */
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request){
        return userService.login(loginForm,request).message("登录成功");
    }

    @GetMapping("/check")
    public Result check(){
        return Result.ok().message(null);
    }
}
