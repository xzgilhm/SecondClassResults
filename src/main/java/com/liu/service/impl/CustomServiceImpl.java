package com.liu.service.impl;

import com.liu.dao.CustomMapper;
import com.liu.model.CustomModel;
import com.liu.model.StandardWithCredit;
import com.liu.model.TModule;
import com.liu.service.CustomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
@Service
@Transactional
public class CustomServiceImpl implements CustomService{
    @Resource
    CustomMapper customMapper;

    public List<CustomModel> findByModuleId(String moduleId) {
        return customMapper.findByModuleId(moduleId);
    }
//
//    public List<StandardWithCredit> findStardByMuduleIdAndTypeId(String moduleId, String typeId) {
//        return customMapper.findStardByMuduleIdAndTypeId(moduleId,typeId);
//    }


    @Override
    public List<TModule> findSubmitModuleByUserId(String userId) {
        return customMapper.findSubmitModuleByUserId(userId);
    }


}
