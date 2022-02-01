package me.glinde.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.glinde.blog.utils.JedisUtils;
import me.glinde.blog.utils.Result;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.glinde.blog.dao.UserDao;
import me.glinde.blog.entity.UserEntity;
import me.glinde.blog.service.UserService;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private JedisUtils jedisUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result login(UserEntity user, HttpServletRequest request) {
        UserEntity userFound = userDao.selectOne(new QueryWrapper<UserEntity>()
                .eq("username",user.getUsername()));
        if(null == userFound){
            return Result.fail("用户名错误");
        }
        if(!passwordEncoder.matches(user.getPassword(), userFound.getPassword())){
            return Result.fail("密码错误");
        }
        if(!userFound.isEnabled()){
            return Result.fail("账号已禁用");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userFound,null, userFound.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = DigestUtils.md5DigestAsHex((UUID.randomUUID().toString()
                +System.currentTimeMillis()
                +userFound.getUsername())
                .getBytes(StandardCharsets.UTF_8));
        jedisUtils.set(token,user.getUsername());

        return Result.ok("登录成功",token);
    }
}