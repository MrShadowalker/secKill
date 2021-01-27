package cn.monitor4all.miaoshadao.dao;

import lombok.Data;

/**
 * 库存实体类
 *
 * @author Shadowalker
 */
@Data
public class Stock {
    private Integer id;

    // 名称
    private String name;

    // 库存
    private Integer count;

    // 已售
    private Integer sale;

    // 版本号
    private Integer version;
}