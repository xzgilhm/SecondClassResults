package com.liu.serviceTest;

import com.liu.Tester;
import com.liu.core.Result;
import com.liu.model.CustomModel;
import com.liu.model.TModule;
import com.liu.model.TType;
import com.liu.model.TUserSubmit;
import com.liu.service.CustomService;
import com.liu.service.TUserService;
import com.liu.service.TUserSubmitService;
import org.junit.Test;


import org.springframework.test.annotation.Rollback;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.util.List;

public class UserSubmitTest extends Tester {

    @Resource
    TUserSubmitService tUserSubmitService;

    @Resource
    CustomService customService;
    //在user_submit中通过userid查询所有信息
    @Test
    public void findByUserId(){
        Condition condition = new Condition(TUserSubmit.class);
        condition.createCriteria().andCondition("userid = 5");
        List<TUserSubmit> tUserSubmits = tUserSubmitService.findByCondition(condition);
        Result r = new Result();
        r.setData(tUserSubmits);
        System.out.println(r.toString());
    }

    /*  user_submit中通过userid查询module的信息
     *   @return List<TUserModule>
     */
    @Test
    public void findModuleById(){
        List<TModule> tModules = customService.findSubmitModuleByUserId("5");
        Result r = new Result();
        r.setData(tModules);
        System.out.println(r.toString());
    }

    @Test
    @Rollback(false)
    public void updateSubmit(){
        Condition condition = new Condition(TUserSubmit.class);
        condition.createCriteria().andCondition("userid=5 AND moduleid='01' AND typeid='0101'");
        int userId=Integer.parseInt("5");
        int roleId=Integer.parseInt("5");
        String moduleId="01";
        String typeId="0101";
        int standardId=Integer.parseInt("19");
        int creditId=Integer.parseInt("6");
        int evidenceId=Integer.parseInt("1");
        TUserSubmit tUserSubmit = new TUserSubmit();
        tUserSubmit.setUserid(userId);
        tUserSubmit.setRoleid(roleId);
        tUserSubmit.setModuleid(moduleId);
        tUserSubmit.setTypeid(typeId);
        tUserSubmit.setStandardid(standardId);
        tUserSubmit.setCreditid(creditId);
        tUserSubmit.setEvidenceid(evidenceId);
        tUserSubmitService. updateByCondition(condition,tUserSubmit);
    }
}
