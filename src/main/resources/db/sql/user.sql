CREATE TABLE `t_user` (
                          `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'pk',
                          `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '电话',
                          `birthday` date COMMENT '出生日期',
                          `sex` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '性别 0未知 1男 2女',
                          `balance` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '余额',
                          `status` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '1启用 0禁用',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`),
                          KEY `idx_mobile` (`mobile`) USING BTREE COMMENT 'mobile索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '用户表';
