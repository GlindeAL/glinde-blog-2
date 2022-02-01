package me.glinde.blog.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import me.glinde.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.glinde.blog.entity.BlogEntity;
import me.glinde.blog.service.BlogService;




/**
 * 博客
 *
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(){
        List<BlogEntity> blogList = blogService.list();

        return Result.ok(blogList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id){
		BlogEntity blog = blogService.getById(id);

        return Result.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody BlogEntity blog){
		blogService.save(blog);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody BlogEntity blog){
		blogService.updateById(blog);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids){
		blogService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}
