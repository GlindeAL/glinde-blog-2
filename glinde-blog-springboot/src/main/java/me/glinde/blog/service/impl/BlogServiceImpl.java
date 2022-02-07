package me.glinde.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.glinde.blog.dao.BlogDao;
import me.glinde.blog.entity.BlogEntity;
import me.glinde.blog.service.BlogService;
import org.springframework.stereotype.Service;


@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogDao, BlogEntity> implements BlogService {



}