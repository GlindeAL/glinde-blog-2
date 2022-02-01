package me.glinde.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.glinde.blog.entity.UserEntity;
import me.glinde.blog.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 *
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
public interface UserService extends IService<UserEntity> {


    Result login(UserEntity user, HttpServletRequest request);
}

