package me.glinde.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import me.glinde.blog.dao.MailDao;
import me.glinde.blog.entity.MailEntity;
import me.glinde.blog.service.MailService;


@Service("mailService")
public class MailServiceImpl extends ServiceImpl<MailDao, MailEntity> implements MailService {



}