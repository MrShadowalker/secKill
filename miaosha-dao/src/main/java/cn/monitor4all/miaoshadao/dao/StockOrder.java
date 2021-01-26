package cn.monitor4all.miaoshadao.dao;

import lombok.Data;

import java.util.Date;

@Data
public class StockOrder {
    private Integer id;

    private Integer sid;

    private String name;

    private Integer userId;

    private Date createTime;
}