package com.liu.serviceTest;

import com.liu.Tester;
import com.liu.model.TRole;
import com.liu.model.TUser;
import com.liu.service.TRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 73559 on 2017/12/27.
 */
public class RoleTest extends Tester {
    @Autowired
    TRoleService trs;

    @Test
    public void findByName() throws Exception {
        List<TRole> list = trs.findAll();
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getUnitname());
        }
    }

}
