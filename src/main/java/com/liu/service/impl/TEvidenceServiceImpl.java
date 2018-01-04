package com.liu.service.impl;

import com.liu.dao.TEvidenceMapper;
import com.liu.model.TEvidence;
import com.liu.service.TEvidenceService;
import com.liu.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
@Service
@Transactional
public class TEvidenceServiceImpl extends AbstractService<TEvidence> implements TEvidenceService {
    @Resource
    private TEvidenceMapper tEvidenceMapper;

}
