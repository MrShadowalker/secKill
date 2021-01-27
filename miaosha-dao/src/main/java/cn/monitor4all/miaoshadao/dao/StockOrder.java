package cn.monitor4all.miaoshadao.dao;

import lombok.Data;

import java.util.Date;

/**
 * 订单实体类
 *
 * @author Shadowalker
 */
@Data
public class StockOrder {

    private Integer id;

    // 库存 ID
    private Integer sid;

    // 商品名称
    private String name;

    // 用户 ID
    private Integer userId;

    // 订单创建时间
    private Date createTime;
}