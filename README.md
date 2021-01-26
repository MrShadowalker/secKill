## 秒杀系统

### 秒杀场景

秒杀系统主要应用在商品抢购的场景，例如：

- 电商抢购限量商品
- 抢票，火车票、周董演唱会门票……
- ……

### 为什么要独立做秒杀系统

秒杀系统抽象来说就是以下步骤：

- 用户选品下单
- 校验库存
- 扣减库存
- 创建用户订单
- 用户支付，等待后续步骤

听起来其实是蛮简单的流程，但为何要独立出来做一个模块呢？

如果项目流量非常小，完全不担心有并发的购买请求，那么做秒杀模块意义就不大了。

但如果系统要像淘宝、12306、大麦网一样，接受高并发访问和下单的考验，那么就需要一套完整的**流程保护措施**，来保证系统在用户流量高峰期不会被搞挂，并且还需要满足业务需要。

### 功能 & 措施

- 严格防止超卖：库存 100 件，如果卖了 120 件，就凉凉。
- 防止黑产：防止不怀好意的人通过各种技术手段把本该让正常用户买到的全部收入囊中。
- 尽力保证用户体验：高并发下，网页打不开、支付不成功、购物车进不去、地址改不了……极度影响用户体验，造成客户流失。

## 打造简易秒杀系统

该项目为基于SpringBoot的简易秒杀系统 demo

### 对应教程

[【秒杀系统】（一）：防止超卖](https://github.com/MrShadowalker/miaosha/blob/master/doc/%E9%98%B2%E6%AD%A2%E8%B6%85%E5%8D%96.md)

[【秒杀系统】（二）：令牌桶限流 + 再谈超卖](https://github.com/MrShadowalker/miaosha/blob/master/doc/%E4%BB%A4%E7%89%8C%E6%A1%B6%E9%99%90%E6%B5%81%20%2B%20%E8%B6%85%E5%8D%96%20plus.md)

[【秒杀系统】（三）：抢购接口隐藏 + 单用户限制频率](https://github.com/MrShadowalker/miaosha/blob/master/doc/%E6%8A%A2%E8%B4%AD%E6%8E%A5%E5%8F%A3%E9%9A%90%E8%97%8F%20%2B%20%E5%8D%95%E7%94%A8%E6%88%B7%E9%99%90%E5%88%B6%E9%A2%91%E7%8E%87.md)

[【秒杀系统】（四）：缓存与数据库双写问题的争议](https://github.com/MrShadowalker/miaosha/blob/master/doc/%E7%BC%93%E5%AD%98%E4%B8%8E%E6%95%B0%E6%8D%AE%E5%BA%93%E5%8F%8C%E5%86%99%E9%97%AE%E9%A2%98%E7%9A%84%E4%BA%89%E8%AE%AE.md)

[【秒杀系统】番外篇：阿里开源MySQL中间件Canal快速入门](https://github.com/MrShadowalker/miaosha/blob/master/doc/%E9%98%BF%E9%87%8C%E5%BC%80%E6%BA%90MySQL%E4%B8%AD%E9%97%B4%E4%BB%B6Canal%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8.md)

[【秒杀系统】（五）：如何优雅的实现订单异步处理](https://github.com/MrShadowalker/miaosha/blob/master/doc/%E5%A6%82%E4%BD%95%E4%BC%98%E9%9B%85%E7%9A%84%E5%AE%9E%E7%8E%B0%E8%AE%A2%E5%8D%95%E5%BC%82%E6%AD%A5%E5%A4%84%E7%90%86.md)

### 项目使用简介

项目是 SpringBoot 工程，并且是父子工程，直接导入 IDEA 即可使用。

1. 导入 miaosha.sql 文件到 MySQL 数据库
2. 配置 application.properties 文件，修改为数据库连接地址
3. mvn clean install 最外层的父工程 pom.xml，有些包如果在公司无法导入，可能是网络原因，后期会尽量改成公司内部可以使用的包。
4. 运行 miaosha-web，在 POSTMAN 或者浏览器直接访问请求链接即可

### 项目规划

- 乐观锁防止超卖
- 令牌桶限流
- Redis 缓存
- 消息队列异步处理订单
- ……



