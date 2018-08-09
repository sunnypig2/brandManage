package Dao;

import Entity.relationItem;

public interface relationItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(relationItem record);

    int insertSelective(relationItem record);

    relationItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(relationItem record);

    int updateByPrimaryKey(relationItem record);


}