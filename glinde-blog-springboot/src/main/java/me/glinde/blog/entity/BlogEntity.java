package me.glinde.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博客
 * 
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
@Data
@TableName("blog")
public class BlogEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	@TableId
	private Integer id;

	private String title;

	private String description;

	private String content;

	private Date date;

	private Integer state;

}
