package com.liu.serviceTest;

import com.liu.Tester;
import com.liu.model.TNode;
import com.liu.service.TNodeService;
import com.liu.service.TRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
public class NodeTest extends Tester {
    @Autowired
    TNodeService tNodeService;

    @Test
    public void findByModuleId(){
        String moduleId = "01";
        Condition condition = new Condition(TNode.class);
        condition.createCriteria().andCondition("moduleid = " + moduleId);
        List<TNode> tn = tNodeService.findByCondition(condition);
        for (int i = 0; i < tn.size() ; i++) {
            System.out.println(tn.get(i).getTypeid());
        }
    }
}
