package com.liu.service.impl;

import com.liu.dao.TNodeMapper;
import com.liu.model.TNode;
import com.liu.service.TNodeService;
import com.liu.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
@Service
@Transactional
public class TNodeServiceImpl extends AbstractService<TNode> implements TNodeService {
    @Resource
    private TNodeMapper tNodeMapper;

    public List<Map<String,Object>> getStandard(String moduleId, String typeId){
        return tNodeMapper.getStandard(moduleId,  typeId);
    }

}
