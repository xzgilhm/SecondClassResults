package com.liu.web;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TUser;
import com.liu.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/12/27.
*/
@RestController
public class TUserController {
    @Resource
    private TUserService tUserService;


    @PostMapping("/add")
    public Result add(TUser tUser) {
        tUserService.save(tUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TUser tUser) {
        tUserService.update(tUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TUser tUser = tUserService.findById(id);
        return ResultGenerator.genSuccessResult(tUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TUser> list = tUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/test")
    public Result test() {
        TUser tUser = tUserService.findBy("name","liu1");
        return ResultGenerator.genSuccessResult(tUser);
    }

}
