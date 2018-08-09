package Dao;

import Entity.relationGroup;

import java.util.List;
import java.util.Map;

public interface relationGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(relationGroup record);

    int insertSelective(relationGroup record);

    relationGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(relationGroup record);

    int updateByPrimaryKey(relationGroup record);

    List<relationGroup> getAllRelationGroup();

    List<relationGroup> getRelationGroupByNameAndClass(Map<String,Object> map);
}