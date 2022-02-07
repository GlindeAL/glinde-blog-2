package me.glinde.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.glinde.blog.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
