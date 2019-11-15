/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.27-log : Database - bases
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bases` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bases`;

/*Table structure for table `attendance_record` */

DROP TABLE IF EXISTS `attendance_record`;

CREATE TABLE `attendance_record` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `month_time` int(11) DEFAULT '0',
  `sum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attendance_record` */

insert  into `attendance_record`(`id`,`uid`,`month_time`,`sum`) values ('191103K09GKXHA3C','1910248RD0ZGZ5S8',3,8);

/*Table structure for table `check_in` */

DROP TABLE IF EXISTS `check_in`;

CREATE TABLE `check_in` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL COMMENT '签到时间',
  `finish_time_str` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '1人脸2现场',
  `time_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `check_in` */

insert  into `check_in`(`id`,`user_id`,`finish_time`,`finish_time_str`,`status`,`time_str`) values ('191028B833GHMZHH','1910248RD0ZGZ5S8','2019-10-23 07:49:07',23,1,'2019-10-23 07:49:07'),('191028B863W46DGC','1910248RD0ZGZ5S8','2019-10-24 07:49:26',24,1,'2019-10-24 07:49:26'),('191028DCXGPF2CH0','1910248RD0ZGZ5S8','2019-10-26 10:51:40',26,1,'2019-10-26 10:51:40'),('191028DD73HZDN0H','1910248RD0ZGZ5S8','2019-10-28 10:52:42',28,1,'2019-10-28 10:52:42'),('191029FSDY837SCH','1910248RD0ZGZ5S8','2019-10-29 12:44:31',29,1,'2019-10-29 20:44:31'),('191031C4PX38SKD4','1910248RD0ZGZ5S8','2019-10-31 09:03:05',31,1,'2019-10-31 17:03:05'),('191103K1R35WFNC0','1910248RD0ZGZ5S8','2019-11-03 15:54:27',3,2,'2019-11-03 23:54:27'),('191106BKN8CXA9KP','1910248RD0ZGZ5S8','2019-11-06 08:17:52',6,1,'2019-11-06 16:17:52'),('1911118HFPND85GC','1910248RD0ZGZ5S8','2019-11-11 04:02:10',11,2,'2019-11-11 12:02:10');

/*Table structure for table `department_employee` */

DROP TABLE IF EXISTS `department_employee`;

CREATE TABLE `department_employee` (
  `id` varchar(255) NOT NULL,
  `pid` varchar(255) DEFAULT NULL COMMENT '父id',
  `label_name` varchar(255) DEFAULT NULL COMMENT '栏目名称',
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `FK_ID` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `department_employee_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`id`),
  CONSTRAINT `department_employee_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `department_employee` */

insert  into `department_employee`(`id`,`pid`,`label_name`,`uid`) values ('1910248TRTC7PCBC',NULL,'部门A',NULL),('1910248TYF81N0H0',NULL,'部门B',NULL),('1910248W0A3T5D40',NULL,'部门C',NULL),('1910248W24FM8M80',NULL,'部门D',NULL),('1910248W3X7G3NF8',NULL,'部门E',NULL),('1910248W6G71KMNC',NULL,'部门F',NULL),('1910248WB7WN1CSW',NULL,'部门G',NULL),('1910248WGN6KN8BC','1910248TRTC7PCBC','张三','1910248P87AMAWZC'),('1910248Y93G4N3F8','1910248TYF81N0H0','陈浩楠','1910248RD0ZGZ5S8'),('1910248YD4PHF51P','1910248TRTC7PCBC','张三2','1910248S0M2YK968'),('1910248YK3X9S428','1910248W0A3T5D40','张三3','1910248S6WWZ6B7C'),('1910248YP036PHM8','1910248W24FM8M80','张三4','1910248SF6WCBSCH'),('1910248YSACG2KD4','1910248W6G71KMNC','王五','1910248SN65Z084H');

/*Table structure for table `face` */

DROP TABLE IF EXISTS `face`;

CREATE TABLE `face` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `face_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `face` */

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `id` varchar(255) NOT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL COMMENT '工程图纸',
  `designer` varchar(255) DEFAULT NULL COMMENT '设计者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `picture` */

insert  into `picture`(`id`,`pid`,`name`,`image`,`designer`) values ('191109GHWFRH6RAW',NULL,'底层基础','/engineeringPic/dcjc.jpg',''),('191109GK5TFYH5WH',NULL,'电路安装','/engineeringPic/dl.jpg',''),('191109GK6RRDH8SW',NULL,'家电装修','/engineeringPic/jd.jpg',''),('191109GK9ZN32FW0',NULL,'室内装修','/engineeringPic/sn.jpg',''),('191109GKPAS17PDP',NULL,'楼外装修','/engineeringPic/lw.jpg',''),('191109GMDA0MBA14','191109GHWFRH6RAW','Aa','/engineeringPic/sg1.jpg','XX'),('191109GMMBA31PM8','191109GHWFRH6RAW','Ab','/engineeringPic/sg2.jpg','XX'),('191109GMWYWCGFY8','191109GHWFRH6RAW','C','/engineeringPic/sg3.jpg','XX'),('191109GMYR96772W','191109GK5TFYH5WH','D','/engineeringPic/sg4.jpg','XX'),('191109GMZFF15W6W','191109GK6RRDH8SW','E','/engineeringPic/sg5.jpg','XX'),('191109GN18DRTDYW','191109GK9ZN32FW0','F','/engineeringPic/sg6.jpg','XX'),('191109GN3K3GGCM8','191109GK5TFYH5WH','G','/engineeringPic/sg7.jpg','XX');

/*Table structure for table `search_records` */

DROP TABLE IF EXISTS `search_records`;

CREATE TABLE `search_records` (
  `id` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `search_records` */

insert  into `search_records`(`id`,`content`) values ('191107C2PSX8A0SW','aa'),('191107C4S8HBC568','aa'),('191107C4XPYKB2FW','aa'),('191107C4Z35D7PDP','bb'),('191107C4ZWRZRGR4','bb'),('191107DDR27NAXYW','bb'),('191107DRRBSM1028','bb'),('191107DRS1SKNMW0','bb'),('191107DRSTAMGAA8','aa'),('191107G3XN4670H0','aa'),('191107G3Z34S59GC','bb'),('19111089FH6DBNF8','bb');

/*Table structure for table `site_check_in` */

DROP TABLE IF EXISTS `site_check_in`;

CREATE TABLE `site_check_in` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `site_check_in` */

insert  into `site_check_in`(`id`,`uid`,`address`,`image`,`time`) values ('191103K1PRWPS9KP','1910248RD0ZGZ5S8','广东省广州市天河区天府路1号','/1910248RD0ZGZ5S8/faceTest/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.e6p4PhnBWv72e202726d2c365d7fff005c04ef377714.jpg','2019-11-03 23:54:25'),('191107DM4S8XCRGC','1910248RD0ZGZ5S8','广东省广州市天河区天府路1号','/1910248RD0ZGZ5S8/faceTest/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.w77YZE4n42714c89a10b0d771e91b9dc4580918669dd.jpg','2019-11-07 19:07:28'),('1911118HFFFN5MA8','1910248RD0ZGZ5S8','广东省广州市天河区天府路1号','/1910248RD0ZGZ5S8/faceTest/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.NGTkfeSGo6sj755e0222c2b4f8810fb219d7610640db.jpg','2019-11-11 12:02:09');

/*Table structure for table `user_department` */

DROP TABLE IF EXISTS `user_department`;

CREATE TABLE `user_department` (
  `id` varchar(255) NOT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_department` */

insert  into `user_department`(`id`,`uid`,`pid`) values ('191025C2PTDKGFK4','1910248P87AMAWZC','1910248TRTC7PCBC'),('191025C30MW15HBC','1910248RD0ZGZ5S8','1910248TYF81N0H0'),('191025C37HC30X40','1910248S0M2YK968','1910248TRTC7PCBC'),('191025C3BSRN437C','1910248S6WWZ6B7C','1910248W0A3T5D40'),('191025C3KKGAWM80','1910248SF6WCBSCH','1910248W24FM8M80'),('191025C3RGM1BTC0','1910248SN65Z084H','1910248W6G71KMNC');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(255) NOT NULL COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '工号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(11) NOT NULL COMMENT '电话',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `id_card` varchar(255) NOT NULL COMMENT '身份证',
  `face_image` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `status` int(11) DEFAULT NULL COMMENT '0用户 1管理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`phone`,`name`,`id_card`,`face_image`,`status`) values ('1910248P87AMAWZC','10000','4QrcOUm6Wau+VuBX8g+IPg==','13666666666','张三','440582199808020470','/1910248P87AMAWZC/face/20191024210706.jpg',1),('1910248RD0ZGZ5S8','10001','4QrcOUm6Wau+VuBX8g+IPg==','13666666666','陈浩楠','440582199808020472','/1910248RD0ZGZ5S8/face/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.eMilzVrd0Yc6bcbfb12a6f8fd02dfc7add959a8818fd.jpg',0),('1910248S0M2YK968','10002','4QrcOUm6Wau+VuBX8g+IPg==','13666666666','张三2','440582199808020470','/1910248S0M2YK968/face/20191024210654.jpg',0),('1910248S6WWZ6B7C','10003','4QrcOUm6Wau+VuBX8g+IPg==','13666666666','张三3','440582199808020470','/1910248S6WWZ6B7C/face/20191024210658.jpg',0),('1910248SF6WCBSCH','10004','4QrcOUm6Wau+VuBX8g+IPg==','13666666666','张三4','440582199808020470','/1910248SF6WCBSCH/face/20191024210702.jpg',0),('1910248SN65Z084H','10005','4QrcOUm6Wau+VuBX8g+IPg==','13666666666','王五','440582199808020470','/1910248SN65Z084H/face/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.1r3Ighxy2UDL28e2fa19ad5d6b84bca6da8e3e53181a.jpg',0);

/*Table structure for table `users_report` */

DROP TABLE IF EXISTS `users_report`;

CREATE TABLE `users_report` (
  `id` varchar(255) NOT NULL,
  `deal_user_id` varchar(255) DEFAULT NULL,
  `deal_video_id` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users_report` */

insert  into `users_report`(`id`,`deal_user_id`,`deal_video_id`,`title`,`content`,`userid`,`create_date`) values ('191107CDCD6S6GF8','1910248RD0ZGZ5S8','191106AZCZ40YDWH','涉嫌诈骗','asdas','1910248RD0ZGZ5S8','2019-11-07 17:29:12');

/*Table structure for table `videos` */

DROP TABLE IF EXISTS `videos`;

CREATE TABLE `videos` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `video_desc` varchar(255) DEFAULT NULL COMMENT '视频描述',
  `video_path` varchar(255) DEFAULT NULL COMMENT '视频路径',
  `video_seconds` float(6,2) DEFAULT NULL COMMENT '视频秒数',
  `video_width` int(6) DEFAULT NULL COMMENT '视频宽度',
  `video_height` int(6) DEFAULT NULL COMMENT '视频高度',
  `cover_path` varchar(255) DEFAULT NULL COMMENT '封面图路径',
  `status` int(11) DEFAULT NULL COMMENT '1-已发布，0禁止(管理操作)',
  `create_time` varchar(255) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `videos` */

insert  into `videos`(`id`,`user_id`,`video_desc`,`video_path`,`video_seconds`,`video_width`,`video_height`,`cover_path`,`status`,`create_time`) values ('191105A0ZBYKK6Y8','1910248RD0ZGZ5S8','aaa','/1910248RD0ZGZ5S8/video/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.PBiAp8cbGlJw712a2ab21dc275999b2d495417662558.mp4',6.06,448,960,'/1910248RD0ZGZ5S8/video/76dba24b-4536-483f-959c-183a6e9c85ab.jpg',1,'2019-11-05 14:03:38'),('191106AZCZ40YDWH','1910248RD0ZGZ5S8','aaaa','/1910248RD0ZGZ5S8/video/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.31OrSVs2V5S133eaad2191027e019c7b877679a45d60.mp4',19.33,654,368,'/1910248RD0ZGZ5S8/video/40af469b-16a5-4bec-911f-35d774dd22df.jpg',1,'2019-11-06 15:23:09'),('191106B00ZZSND8H','1910248RD0ZGZ5S8','bbb','/1910248RD0ZGZ5S8/video/wxcc3fceeaef268950.o6zAJs2svISCeHweJXKKXZTGDuYM.vfRyEoOPnNyX712a2ab21dc275999b2d495417662558.mp4',6.06,448,960,'/1910248RD0ZGZ5S8/video/8d989aa4-4a01-4b65-a7e1-44c72612adcf.jpg',1,'2019-11-06 15:24:52');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
