package com.liu.service.impl;

import com.liu.dao.TRoleMapper;
import com.liu.model.TRole;
import com.liu.service.TRoleService;
import com.liu.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/12/27.
 */
@Service
@Transactional
public class TRoleServiceImpl extends AbstractService<TRole> implements TRoleService {
    @Resource
    private TRoleMapper tRoleMapper;

}
