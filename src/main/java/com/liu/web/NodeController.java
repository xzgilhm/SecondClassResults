package com.liu.web;

import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.CustomModel;
import com.liu.model.StandardArray;
import com.liu.model.TModule;
import com.liu.model.TypeArray;
import com.liu.service.CustomService;
import com.liu.service.TModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Autowired
    CustomService customService;
    //获得所有module的name
    @GetMapping("/getAllModuleName")
    public Result getAllModuleName(){
        List<TModule> tModules = tModuleService.findAll();
        return ResultGenerator.genSuccessResult(tModules);
    }


    //获得所有module的name
    @PostMapping("/findTypeByModule")
    public Result getAllModuleName(@RequestBody String str){
        String moduleId = str.substring(str.length()-4,str.length()-2);
        System.out.println(moduleId);
        List<CustomModel> tn = customService.findByModuleId(moduleId);
        TypeArray tempType = new TypeArray();
        int typeCount = 0;
        List<TypeArray> typeArray = new ArrayList<TypeArray>();

        for (int i = 0; i < tn.size() ; i++) {
            if(typeArray.isEmpty()){
                tempType.setTypeId(tn.get(0).getTypeid());
                tempType.setTypeName(tn.get(0).getTypename());
                typeArray.add(tempType);
            }

            if(typeArray.get(typeCount).getTypeId().equals(tn.get(i).getTypeid())){

            }
            else{
                typeCount++;
                //这里必须new一个TypeArray
                TypeArray temp = new TypeArray();
                temp.setTypeId(tn.get(i).getTypeid());
                temp.setTypeName(tn.get(i).getTypename());
                typeArray.add(temp);
            }
        }
        return ResultGenerator.genSuccessResult(typeArray);
    }

}
