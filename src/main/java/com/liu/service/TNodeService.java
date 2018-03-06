package com.liu.service;
import com.liu.model.TNode;
import com.liu.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/01/04.
 */
public interface TNodeService extends Service<TNode> {
    List<Map<String,Object>> getStandard(String moduleId,String typeId);
}
