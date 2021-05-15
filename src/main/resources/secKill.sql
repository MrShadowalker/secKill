-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `seckill_stock`;
CREATE TABLE `seckill_stock`
(
    `id`      int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`    varchar(50)      NOT NULL DEFAULT '' COMMENT '名称',
    `count`   int(11)          NOT NULL COMMENT '库存',
    `sale`    int(11)          NOT NULL COMMENT '已售',
    `version` int(11)          NOT NULL COMMENT '乐观锁，版本号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `seckill_stock` (id, name, count, sale, version)
VALUES (1, 'iPhone X', 10, 0, 0);
INSERT INTO `seckill_stock` (id, name, count, sale, version)
VALUES (2, 'Mac', 200, 0, 0);
INSERT INTO `seckill_stock` (id, name, count, sale, version)
VALUES (3, 'iPad', 300, 0, 0);

-- ----------------------------
-- Table structure for stock_order
-- ----------------------------
DROP TABLE IF EXISTS `seckill_stock_order`;
CREATE TABLE `seckill_stock_order`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `sid`         int(11)          NOT NULL COMMENT '库存ID',
    `name`        varchar(30)      NOT NULL DEFAULT '' COMMENT '商品名称',
    `user_id`     int(11)          NOT NULL DEFAULT '0',
    `create_time` timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of stock_order
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `seckill_user`;
CREATE TABLE `seckill_user`
(
    `id`        bigint(20)   NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `seckill_user` (id, user_name)
VALUES (1, '李昇懋');
INSERT INTO `seckill_user` (id, user_name)
VALUES (2, '陈子豪');
INSERT INTO `seckill_user` (id, user_name)
VALUES (3, '张三');
INSERT INTO `seckill_user` (id, user_name)
VALUES (4, '牛慧升');
INSERT INTO `seckill_user` (id, user_name)
VALUES (5, '金林');
