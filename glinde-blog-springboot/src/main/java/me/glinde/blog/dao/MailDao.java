package me.glinde.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.glinde.blog.entity.MailEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 信件
 * 
 * @author glinde
 * @date 2022-01-28 22:26:41
 */
@Mapper
public interface MailDao extends BaseMapper<MailEntity> {
	
}
