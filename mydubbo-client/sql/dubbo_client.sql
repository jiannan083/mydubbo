CREATE TABLE `user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `name` varchar(20) COMMENT '姓名',
  `pid` varchar(20) COMMENT '身份证', 
  `version` int NOT NULL COMMENT '版本号,乐观锁',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL  COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

CREATE TABLE `user_order` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `status` int NOT NULL COMMENT '订单状态;0--提交订单待支付;1--未付款用户自己取消;2--未付款超时系统取消;3--支付完成(待发货);4--已发货;5--确认收货;6--退货',
  `version` int NOT NULL COMMENT '版本号,乐观锁',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL  COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

CREATE TABLE `product` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` double(20,2) NOT NULL COMMENT '商品价格',
  `version` int NOT NULL COMMENT '版本号,乐观锁',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL  COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品表';

CREATE TABLE `order_detail` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `order_id` bigint(19) NOT NULL COMMENT '订单id',
  `product_id` bigint(19) NOT NULL COMMENT '商品id',
  `version` int NOT NULL COMMENT '版本号,乐观锁',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL  COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单详细';