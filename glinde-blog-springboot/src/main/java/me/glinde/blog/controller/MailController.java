package me.glinde.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.glinde.blog.dto.QueryInfo;
import me.glinde.blog.entity.MailEntity;
import me.glinde.blog.service.MailService;
import me.glinde.blog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;



/**
 * 信件
 *
 * @author glinde
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
    @GetMapping("/home/list")
    public Result list(QueryInfo queryInfo){
        Page<MailEntity> page = new Page<>(queryInfo.getPageCurrent(), queryInfo.getPageSize());
        IPage<MailEntity> mailList = mailService.page(page,
                new QueryWrapper<MailEntity>()
                        .like("content",queryInfo.getQuery())
                        .eq("state",1)
                        .orderByDesc("date"));

        return Result.ok(mailList).message(null);
    }

    @GetMapping("/admin/list")
    public Result adminList(QueryInfo queryInfo){
        Page<MailEntity> page = new Page<>(queryInfo.getPageCurrent(), queryInfo.getPageSize());
        IPage<MailEntity> mailList = mailService.page(page,
                new QueryWrapper<MailEntity>()
                        .like("content",queryInfo.getQuery())
                        .orderByDesc("date"));

        return Result.ok(mailList).message(null);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id){
		MailEntity mail = mailService.getById(id);

        return Result.ok().message(null);
    }

    /**
     * 保存
     */
    @PostMapping("/home/save")
    public Result save(@RequestBody MailEntity mail){
        mail.setDate(new Date());
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

    @PutMapping("/admin/state")
    public Result updateState(@RequestParam("id") Integer id,@RequestParam("state") Integer state){
        return mailService.update(
                new UpdateWrapper<MailEntity>().eq("id",id).set("state",state))
                ? Result.ok() : Result.fail();
    }

    /**
     * 删除
     */
    @RequestMapping("/admin/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return mailService.removeById(id) ? Result.ok() : Result.fail();
    }

}
