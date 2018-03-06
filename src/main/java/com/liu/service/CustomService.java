package com.liu.service;

import com.liu.model.CustomModel;
import com.liu.model.StandardWithCredit;
import com.liu.model.TModule;

import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
public interface CustomService {
    //学生  用moduleId查询自定义model
    List<CustomModel> findByModuleId(String moduleId);
//    List<StandardWithCredit> findStardByMuduleIdAndTypeId(String moduleId, String typeId);
    //学生  修改时查询module  多表查询
    List<TModule> findSubmitModuleByUserId(String userId);

}
