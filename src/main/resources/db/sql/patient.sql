CREATE TABLE `t_patient` (
                          `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'pk',
                          `name` varchar(20) NOT NULL DEFAULT '' COMMENT '患者名',
                          `sex` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别 0未知 1男 2女',
                          `status` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '1启用 0禁用',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '用户表';