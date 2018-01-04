package com.liu.web;
import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TModule;
import com.liu.service.TModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/01/04.
*/
@RestController
@RequestMapping("/t/module")
public class TModuleController {
    @Resource
    private TModuleService tModuleService;

    @PostMapping("/add")
    public Result add(TModule tModule) {
        tModuleService.save(tModule);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tModuleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TModule tModule) {
        tModuleService.update(tModule);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TModule tModule = tModuleService.findById(id);
        return ResultGenerator.genSuccessResult(tModule);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TModule> list = tModuleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
