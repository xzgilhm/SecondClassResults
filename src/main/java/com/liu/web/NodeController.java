package com.liu.web;

import com.liu.core.Result;
import com.liu.core.ResultGenerator;
import com.liu.model.*;
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


    //根据moduleid获得type
    @GetMapping("/findTypeByModule/{moduleId}")
    public Result findTypeByModule(@PathVariable("moduleId") String moduleId){
        System.out.println(moduleId);
        List<CustomModel> tn = customService.findByModuleId(moduleId);
        TypeArray tempType = new TypeArray();
        int typeCount = 0;
        List<TypeArray> typeArray = new ArrayList<TypeArray>();

        for (int i = 0; i < tn.size() ; i++) {
            //当type数组未空时,对第一个对象赋值
            if(typeArray.isEmpty()){
                tempType.setTypeId(tn.get(0).getTypeid());
                tempType.setTypeName(tn.get(0).getTypename());
                tempType.setEvidenceid(tn.get(0).getEvidenceid());
                tempType.setEvidenceTitle(tn.get(0).getEvidenceTitle());
                typeArray.add(tempType);
            }
            //当type数组中的name相同
            if(typeArray.get(typeCount).getTypeId().equals(tn.get(i).getTypeid())){
                continue;
            }
            else{
                typeCount++;
                //这里必须new一个TypeArray
                TypeArray temp = new TypeArray();
                temp.setTypeId(tn.get(i).getTypeid());
                temp.setTypeName(tn.get(i).getTypename());
                temp.setEvidenceid(tn.get(i).getEvidenceid());
                temp.setEvidenceTitle(tn.get(i).getEvidenceTitle());
                typeArray.add(temp);
            }
        }
        return ResultGenerator.genSuccessResult(typeArray);
    }


    //通过moduleid和typeid获得标准以及学分
    @GetMapping("/findStardByMuduleIdAndTypeId/{str}")
    public Result findStardByMuduleIdAndTypeId(@PathVariable("str") String str){
        String[] s = str.split("&");
        String moduleId = s[0];
        String typeId = s[1];
        System.out.println(moduleId + " ---- " + typeId);
        List<StandardWithCredit> ts = customService.findStardByMuduleIdAndTypeId(moduleId,typeId);
        return ResultGenerator.genSuccessResult(ts);
    }
}
