package Dao;

import Entity.brandGroup;

import java.util.List;

public interface brandGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(brandGroup record);

    int insertSelective(brandGroup record);

    brandGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(brandGroup record);

    int updateByPrimaryKey(brandGroup record);

    List<brandGroup> getBrandGroupByClass(Integer id);
}