#身份验证信息
CREATE TABLE `user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT, 
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `pid` varchar(18) NOT NULL COMMENT '身份证',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL  COMMENT '更新日期',  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息';