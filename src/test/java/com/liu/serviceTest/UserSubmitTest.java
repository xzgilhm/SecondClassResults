package com.liu.serviceTest;

import com.liu.Tester;
import com.liu.core.Result;
import com.liu.model.TUserSubmit;
import com.liu.service.TUserService;
import com.liu.service.TUserSubmitService;
import org.junit.Test;


import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.util.List;

public class UserSubmitTest extends Tester {

    @Resource
    TUserSubmitService tUserSubmitService;

    //在user_submit中通过userid查询
    @Test
    public void findByUserId(){
        Condition condition = new Condition(TUserSubmit.class);
        condition.createCriteria().andCondition("userid = 5");
        List<TUserSubmit> tUserSubmits = tUserSubmitService.findByCondition(condition);
        Result r = new Result();
        r.setData(tUserSubmits);
        System.out.println(r.toString());
    }

}
