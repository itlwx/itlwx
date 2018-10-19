/*
SQLyog Enterprise - MySQL GUI v8.14 
MySQL - 5.0.18-nt : Database - itlwx
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`itlwx` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `itlwx`;

/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增长id',
  `title` varchar(32) NOT NULL COMMENT '标题',
  `intro` varchar(255) NOT NULL COMMENT '简介',
  `content` text NOT NULL COMMENT '内容',
  `authro_id` int(11) NOT NULL COMMENT '作者',
  `hits` int(11) NOT NULL default '0' COMMENT '点击数',
  `post_num` int(11) NOT NULL default '0' COMMENT '评论数',
  `deleted` int(1) NOT NULL default '1' COMMENT '删除标记(1:正常，2;删除)',
  `create_time` datetime NOT NULL COMMENT '发表时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `post_time` datetime NOT NULL COMMENT '评论时间',
  `category_id` int(2) NOT NULL COMMENT '类别',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于储存文章';

/*Data for the table `t_article` */

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` int(2) NOT NULL auto_increment COMMENT '自增id',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `type` int(1) NOT NULL COMMENT '类别种类(1:文章，2:音频，3:视频)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注信息',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用于储存系统中的各种类别';

/*Data for the table `t_category` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment COMMENT '自增id',
  `alias` varchar(18) NOT NULL COMMENT '昵称',
  `login_name` varchar(24) NOT NULL COMMENT '登录名称',
  `password` varchar(32) NOT NULL COMMENT '登录密码',
  `deleted` int(1) NOT NULL default '1' COMMENT '删除标记(1:正常，2:已删除)',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
