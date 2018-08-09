package Dao;

import Entity.freqItemsets;

public interface freqItemsetsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(freqItemsets record);

    int insertSelective(freqItemsets record);

    freqItemsets selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(freqItemsets record);

    int updateByPrimaryKeyWithBLOBs(freqItemsets record);

    int updateByPrimaryKey(freqItemsets record);
}