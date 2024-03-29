DROP DATABASE IF EXISTS `db_mew`;
CREATE DATABASE `db_mew`;
USE `db_mew`;
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password_md5` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `user_token` varchar(50) NOT NULL DEFAULT '' COMMENT 'token值',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT  INTO `tb_admin_user`(`id`,`user_name`,`password_md5`,`user_token`,`is_deleted`,`create_time`) VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','d87edfdd63674b9591602b26bfb7f93f',0,'2018-07-04 11:21:14'),(2,'test2','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:28'),(3,'test3','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:32'),(4,'test4','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:32'),(5,'test5','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:33'),(6,'test6','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:34'),(7,'test7','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:35'),(8,'test8','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:35'),(9,'test9','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:36'),(10,'test10','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:37'),(11,'test11','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:38'),(12,'test12','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:38'),(13,'test13','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:39'),(14,'test14','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:40'),(15,'test15','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:40'),(16,'test16','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:41'),(17,'test17','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:41'),(18,'test18','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:42'),(19,'test19','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:46'),(20,'admin2','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:28'),(21,'admin3','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:32'),(22,'admin4','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:32'),(23,'admin5','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:33'),(24,'admin6','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:34'),(25,'admin7','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:35'),(26,'admin8','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:35'),(27,'admin9','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:36'),(28,'admin10','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:37'),(29,'admin11','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:38'),(30,'admin12','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:38'),(31,'admin13','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:39'),(32,'admin14','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:40'),(33,'admin15','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:40'),(34,'admin16','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:41'),(35,'admin17','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:41'),(36,'admin18','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:42'),(37,'admin19','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:46'),(38,'admin011','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:38'),(39,'admin02','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:28'),(40,'admin03','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:32'),(41,'admin04','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:32'),(42,'admin05','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:33'),(43,'admin06','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:34'),(44,'admin07','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:35'),(45,'admin08','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:35'),(46,'admin09','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:36'),(47,'admin010','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:37'),(48,'admin011','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:38'),(49,'admin012','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:38'),(50,'admin013','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:39'),(51,'admin014','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:40'),(52,'admin015','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:40'),(53,'admin016','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:41'),(54,'admin017','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:41'),(55,'admin018','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:42'),(56,'admin019','098f6bcd4621d373cade4e832627b4f6','\'\'',0,'2018-07-09 17:22:46'),(57,'ZHENFENG13','77c9749b451ab8c713c48037ddfbb2c4','592e62069daf32211a71aa892ec1b8f5',0,'2018-07-12 16:08:49'),(58,'213312','eqwfasdfa','\'\'',0,'2018-07-12 16:10:14'),(59,'14415143','51435135','\'\'',0,'2018-07-12 19:43:06'),(60,'shisan','e10adc3949ba59abbe56e057f20f883e','d9ab78a7c39f383e47b7c4ffbb407c87',0,'2018-07-12 19:45:32'),(61,'zhangsan','fcea920f7412b5da7be0cf42b8c93759','',0,'2018-07-12 20:20:22'),(150,'test-user1','3d0faa930d336ba748607ab7076ebce2','\'\'',0,'2018-08-04 17:37:32'),(151,'3123213213','6fdce2f14f4baf2d666fa13dfd8d1945','\'\'',0,'2018-08-15 20:43:42'),(152,'lou2','25f9e794323b453885f5181f1b624d0b','\'\'',0,'2019-01-05 19:55:30'),(153,'lou3','e10adc3949ba59abbe56e057f20f883e','\'\'',0,'2019-01-06 00:28:06'),(154,'lou1','e10adc3949ba59abbe56e057f20f883e','\'\'',0,'2019-01-10 11:05:52');

DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE tb_employee (`id` int auto_increment,
	`employee_name` varchar(10) NOT NULL UNIQUE,
	`account` decimal(14,6) DEFAULT 0.0,
	`income` decimal(14,6) DEFAULT 0.0,
	`is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	PRIMARY KEY (`id`),
	INDEX `idx_employee_employee_name` (`employee_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';

DROP TABLE IF EXISTS `tb_drug`;
CREATE TABLE tb_drug (`drug_id` int AUTO_INCREMENT,
	`drug_name` varchar(20) NOT NULL UNIQUE,
	`drug_trade_name` varchar(20) NULL,
	`drug_package` varchar(12) NULL,
	`drug_no` varchar(20) NULL,
	`manufacture_id` int NULL,
    `drug_unit` varchar(12) NOT NULL,
    `drug_price` decimal(10,6) NOT NULL,
	`is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	PRIMARY KEY (`drug_id`),
	INDEX `idx_drug_drug_name` (`drug_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药品信息表';

DROP TABLE IF EXISTS `tb_storage`;
CREATE TABLE tb_storage (`storage_id` int AUTO_INCREMENT,
	`storage_name` varchar(20) NOT NULL UNIQUE,
	`is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	PRIMARY KEY (`storage_id`),
	INDEX `idx_storage_storage_name` (`storage_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库信息表';
INSERT  INTO `tb_storage`(`storage_name`) VALUES ('药房');

DROP TABLE IF EXISTS `tb_input`;
CREATE TABLE tb_input (`input_id` int AUTO_INCREMENT,
	`drug_id` int NOT NULL,
	`price` DECIMAL(14,6) NULL,
	`input_num` DECIMAL(12,4) NULL,
	`batch_no` varchar(20) NULL,
	`duration` datetime NOT NULL,
	`is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	PRIMARY KEY (`input_id`),
	INDEX `idx_input_drug_id` (`drug_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药品入库表';

DROP TABLE IF EXISTS `tb_output`;
CREATE TABLE tb_output (`output_id` int AUTO_INCREMENT,
    `input_id` Long NOT NULL,
	`drug_id` Long NOT NULL,
	`employee_id` Long NOT NULL,
	`price` DECIMAL(14,6) NULL,
	`output_num` DECIMAL(12,4) NULL,
	`duration` datetime NOT NULL,
	`is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	PRIMARY KEY (`output_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='领药信息表';

DROP TABLE IF EXISTS `tb_diagnose`;
CREATE TABLE tb_diagnose (`diagnose_id` int AUTO_INCREMENT,
	`employee_id` Long NOT NULL,
	`illness` VARCHAR(2000),
	`is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	PRIMARY KEY (`diagnose_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='诊断信息表';

DROP TABLE IF EXISTS `tb_wastage`;
CREATE TABLE tb_wastage (`wastage_id` int AUTO_INCREMENT,
	`input_id` Long NOT NULL,
	`drug_id` Long NOT NULL,
	`wastage_num` DECIMAL(9,4) NOT NULL,
	`wastage_reason` VARCHAR(2000),
	`is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已删除 0未删除 1已删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
	PRIMARY KEY (`wastage_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='损耗信息表';