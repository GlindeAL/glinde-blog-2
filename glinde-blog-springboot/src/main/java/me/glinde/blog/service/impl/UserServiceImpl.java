package me.glinde.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.glinde.blog.dao.UserDao;
import me.glinde.blog.dto.LoginForm;
import me.glinde.blog.dto.TokenBean;
import me.glinde.blog.entity.UserEntity;
import me.glinde.blog.service.UserService;
import me.glinde.blog.utils.JedisUtils;
import me.glinde.blog.utils.Result;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Result login(LoginForm loginForm, HttpServletRequest request) {
        UserEntity user = userDao.selectOne(new QueryWrapper<UserEntity>()
                .eq("username",loginForm.getUsername()));
        if(null == user){
            return Result.fail().message("用户名错误");
        }
        if(!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())){
            return Result.fail().message("密码错误");
        }
        if(!user.isEnabled()){
            return Result.fail().message("账号已禁用");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        byte[] hash = DigestUtils.sha256((UUID.randomUUID().toString()
                + System.currentTimeMillis()
                + user.getUsername())
                .getBytes(StandardCharsets.UTF_8));
        String token = Hex.encodeHexString(hash);
        jedisUtils.set(token,user.getUsername());

        return Result.ok("登录成功",new TokenBean(token,user.getId(),user.getUsername()));
    }
}