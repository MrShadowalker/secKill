package cn.monitor4all.miaoshadao.mapper;

import cn.monitor4all.miaoshadao.dao.StockOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockOrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StockOrder record);

    int insertSelective(StockOrder record);

    StockOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockOrder record);

    int updateByPrimaryKey(StockOrder record);
}