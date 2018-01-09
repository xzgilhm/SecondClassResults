package com.liu.dao;

import com.liu.model.*;

import java.util.List;

/**
 * Created by 73559 on 2018/1/4.
 */
public interface CustomMapper {
    List<CustomModel> findByModuleId(String moduleId);

    List<StandardWithCredit> findStardByMuduleIdAndTypeId(String moduleId, String typeId);

    List<TModule> findSubmitModuleByUserId(String userId);

}
