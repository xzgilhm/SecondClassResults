package com.liu.web;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TRole;
import com.liu.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/12/27.
*/
@RestController
@RequestMapping("/t/role")
public class TRoleController {
    @Resource
    private TRoleService tRoleService;

    @PostMapping("/add")
    public Result add(TRole tRole) {
        tRoleService.save(tRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TRole tRole) {
        tRoleService.update(tRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TRole tRole = tRoleService.findById(id);
        return ResultGenerator.genSuccessResult(tRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TRole> list = tRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
