package me.glinde.blog.controller;

import java.util.Arrays;
import java.util.Map;

import me.glinde.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.glinde.blog.entity.MailEntity;
import me.glinde.blog.service.MailService;



/**
 * 信件
 *
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){


        return Result.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id){
		MailEntity mail = mailService.getById(id);

        return Result.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody MailEntity mail){
		mailService.save(mail);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody MailEntity mail){
		mailService.updateById(mail);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids){
		mailService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
