package com.liu.service.impl;

import com.liu.dao.TStandardMapper;
import com.liu.model.TStandard;
import com.liu.service.TStandardService;
import com.liu.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
@Service
@Transactional
public class TStandardServiceImpl extends AbstractService<TStandard> implements TStandardService {
    @Resource
    private TStandardMapper tStandardMapper;

}
