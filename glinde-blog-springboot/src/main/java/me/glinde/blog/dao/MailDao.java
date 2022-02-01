package me.glinde.blog.dao;

import me.glinde.blog.entity.MailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 信件
 * 
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
@Mapper
public interface MailDao extends BaseMapper<MailEntity> {
	
}
