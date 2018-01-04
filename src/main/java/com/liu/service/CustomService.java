package com.liu.service;

import com.liu.model.CustomModel;
import com.liu.model.StandardWithCredit;
import com.liu.model.TStandard;

import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
public interface CustomService {
    List<CustomModel> findByModuleId(String moduleId);
    List<StandardWithCredit> findStardByMuduleIdAndTypeId(String moduleId, String typeId);
}
