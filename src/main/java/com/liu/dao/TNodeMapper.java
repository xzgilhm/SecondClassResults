package com.liu.dao;

import com.liu.core.Mapper;
import com.liu.model.TNode;

import java.util.List;
import java.util.Map;

public interface TNodeMapper extends Mapper<TNode> {
    List<Map<String,Object>> getStandard(String moduleId, String typeId);
}