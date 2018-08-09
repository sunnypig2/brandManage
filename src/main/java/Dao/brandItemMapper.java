package Dao;

import Entity.brandItem;

import java.util.List;

public interface brandItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(brandItem record);

    int insertSelective(brandItem record);

    brandItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(brandItem record);

    int updateByPrimaryKey(brandItem record);

    List<brandItem> getItemByGroup(Integer id);
}