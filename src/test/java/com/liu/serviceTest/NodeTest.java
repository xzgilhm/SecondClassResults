package com.liu.serviceTest;

import com.liu.Tester;
import com.liu.core.Result;
import com.liu.model.*;
import com.liu.service.CustomService;
import com.liu.service.TNodeService;
import com.liu.service.TRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
public class NodeTest extends Tester {
    @Autowired
    TNodeService tNodeService;

    @Autowired
    CustomService customService;



    @Test
    public void findByModuleId(){
        String moduleId = "01";
        List<CustomModel> tn = customService.findByModuleId(moduleId);
        TypeArray tempType = new TypeArray();
        int typeCount = 0;
        int standardCount = 0;
        List<TypeArray> typeArray = new ArrayList<TypeArray>();

        for (int i = 0; i < tn.size() ; i++) {
            if(typeArray.isEmpty()){
                StandardArray sa = new StandardArray();
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

        Result r = new Result();
        r.setData(typeArray);
        System.out.println(r.toString());

    }

    @Test
    public void findStardByMuduleIdAndTypeId(){
        String moduleid = "01";
        String typeid = "0101";
        List<StandardWithCredit> ts = customService.findStardByMuduleIdAndTypeId(moduleid,typeid);
        Result s = new Result();
        s.setData(ts);
        System.out.println(s.toString());
    }
}
