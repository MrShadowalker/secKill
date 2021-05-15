package org.shadowalker.seckilldao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.shadowalker.seckilldao.dao.Stock;

/**
 * @author Shadowalker
 */
@Mapper
public interface StockMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer id);

    Stock selectByPrimaryKeyForUpdate(Integer id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    int updateByOptimistic(Stock record);

    int updateSaleAndVersionByOptimistic(Stock record);
}