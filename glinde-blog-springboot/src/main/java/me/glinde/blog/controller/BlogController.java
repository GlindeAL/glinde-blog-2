package me.glinde.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.glinde.blog.dto.QueryInfo;
import me.glinde.blog.entity.BlogEntity;
import me.glinde.blog.service.BlogService;
import me.glinde.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;




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
    @GetMapping("/home/list")
    public Result list(QueryInfo queryInfo){
        Page<BlogEntity> page = new Page<>(queryInfo.getPageCurrent(), queryInfo.getPageSize());
        IPage<BlogEntity> blogList = blogService.page(page,
                new QueryWrapper<BlogEntity>()
                        .like("title",queryInfo.getQuery())
                        .eq("state",1)
                        .orderByDesc("date"));

        return Result.ok(blogList).message(null);
    }

    /**
     * 信息
     */
    @GetMapping("/home/info/{id}")
    public Result info(@PathVariable("id") Integer id){
		BlogEntity blog = blogService.getById(id);
		if(blog.getState() != 1){
		    return Result.fail().message("该博客不处于公开状态");
        }

        return Result.ok(blog).message(null);
    }

    @GetMapping("/admin/list")
    public Result adminList(QueryInfo queryInfo){
        Page<BlogEntity> page = new Page<>(queryInfo.getPageCurrent(),queryInfo.getPageSize());
        IPage<BlogEntity> blogList = blogService.page(page,
                new QueryWrapper<BlogEntity>()
                        .orderByDesc("date"));

        return Result.ok(page).message(null);
    }

    @GetMapping("/admin/info/{id}")
    public Result adminInfo(@PathVariable("id") Integer id){
        return Result.ok(blogService.getById(id)).message(null);
    }

    @PutMapping("/admin/state")
    public Result updateState(@RequestParam("id") Integer id,@RequestParam("state") Integer state){
        return blogService.update(
                new UpdateWrapper<BlogEntity>()
                        .eq("id",id).set("state",state))
                ? Result.ok() : Result.fail();
    }

    /**
     * 保存
     */
    @PostMapping("/admin/save")
    public Result save(@RequestBody BlogEntity blog){
        blog.setDate(new Date());

        return blogService.save(blog) ? Result.ok() : Result.fail();
    }

    /**
     * 修改
     */
    @PutMapping("/admin/update")
    public Result update(@RequestBody BlogEntity blog){
        return blogService.updateById(blog) ? Result.ok() : Result.fail();
    }

    /**
     * 删除
     */
    @DeleteMapping("/admin/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return blogService.removeById(id)
                ? Result.ok() : Result.fail();
    }

}
