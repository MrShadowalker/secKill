package cn.monitor4all.miaoshadao.dao;

import lombok.Data;

@Data
public class Stock {
    private Integer id;

    private String name;

    private Integer count;

    private Integer sale;

    private Integer version;
}