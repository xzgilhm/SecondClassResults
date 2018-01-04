package com.liu.service.impl;

import com.liu.dao.TModuleMapper;
import com.liu.model.TModule;
import com.liu.service.TModuleService;
import com.liu.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
@Service
@Transactional
public class TModuleServiceImpl extends AbstractService<TModule> implements TModuleService {
    @Resource
    private TModuleMapper tModuleMapper;

}
