package org.shadowalker.seckilldao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.shadowalker.seckilldao.dao.StockOrder;

@Mapper
public interface StockOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StockOrder record);

    int insertSelective(StockOrder record);

    StockOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockOrder record);

    int updateByPrimaryKey(StockOrder record);
}