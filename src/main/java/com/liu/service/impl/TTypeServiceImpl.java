package com.liu.service.impl;

import com.liu.dao.TTypeMapper;
import com.liu.model.TType;
import com.liu.service.TTypeService;
import com.liu.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
@Service
@Transactional
public class TTypeServiceImpl extends AbstractService<TType> implements TTypeService {
    @Resource
    private TTypeMapper tTypeMapper;

}
