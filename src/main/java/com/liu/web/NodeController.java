package com.liu.web;

import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.TModule;
import com.liu.service.TModuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 * return all Node Info
 */

@RestController
@RequestMapping("/node")
public class NodeController {
    @Resource
    private TModuleService tModuleService;


    //获得所有module的name
    @GetMapping("/getAllModuleName")
    public Result getAllModuleName(){
        List<TModule> tModules = tModuleService.findAll();
        return ResultGenerator.genSuccessResult(tModules);
    }

}
