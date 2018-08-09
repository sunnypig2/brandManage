package Dao;

import Entity.brandClass;

import java.util.List;

public interface brandClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(brandClass record);

    int insertSelective(brandClass record);

    brandClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(brandClass record);

    int updateByPrimaryKey(brandClass record);

    List<brandClass> getAllBrandClass();
}