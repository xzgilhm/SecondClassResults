package com.liu.service.impl;

import com.liu.dao.TUserSubmitMapper;
import com.liu.model.TUserSubmit;
import com.liu.service.TUserSubmitService;
import com.liu.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by sliu on 2018/01/08.
 */
@Service
@Transactional
public class TUserSubmitServiceImpl extends AbstractService<TUserSubmit> implements TUserSubmitService {
    @Resource
    private TUserSubmitMapper tUserSubmitMapper;

}
