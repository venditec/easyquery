
-- .---------------------------------------------------------------------------.
-- |  Software: EasyQuery                                  					   |
-- |   Version: 1.0                                                            |
-- |   Contact: via sourceforge.net or ramon.sepi@gmail.com                    |
-- |      Info: http://sourceforge.net/projects/easyquery1/                    |
-- |   Support: http://sourceforge.net/projects/easyquery1/                    |
-- | ------------------------------------------------------------------------- |
-- |     Admin: Ramon Segarra Pijuan (project admininistrator)                 |
-- |   Author : Ramon Segarra Pijuan 										   |
-- |   Founder: Ramon Segarra Pijuan (original founder)                        |
-- | Copyright (c) 2012-2013, Ramon Segarra Pijuan. All Rights Reserved.       |
-- | ------------------------------------------------------------------------- |
-- |   License: Distributed under the Creative Commons 						   |
-- |            Attribution-NonCommercial 3.0 Unported License.     		   |
-- |            http://creativecommons.org/licenses/by-nc/3.0/                 |
-- | This program is distributed in the hope that it will be useful -          |
-- | YOU ARE FREE: to Share — to copy, distribute and transmit the work.       |
-- |               to Remix — to adapt the work.							   |
-- | UNDER THE FOLLOWING CONDITIONS: 										   |
-- |		Noncomercial - You may not use this work for commercial purposes.  |
-- |		Attribution  — You must attribute the work to Ramon Segarra Pijuan |
-- |                      like the author or licensor (but not in any way that |
-- |					   suggests that they endorse you or your use 		   |
-- |                      of the work).										   |
-- | ------------------------------------------------------------------------- |
-- |          EasyQuery by Ramon Segarra Pijuan is licensed under a 		   |
-- |     Creative Commons Attribution-NonCommercial 3.0 Unported License.      |
-- '---------------------------------------------------------------------------'

-- 
-- Database Name: EASYQUERY_DB
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `admin`
-- 

CREATE TABLE `admin` (
  `employeenumber` int(10) NOT NULL,
  PRIMARY KEY  (`employeenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `admin` VALUES (12345);

-- --------------------------------------------------------

-- 
-- Table structure for table `answer`
-- 

CREATE TABLE `answer` (
  `id` int(8) NOT NULL auto_increment,
  `text` text NOT NULL,
  `question_id` int(8) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=latin1 AUTO_INCREMENT=135 ;

-- --------------------------------------------------------

-- 
-- Table structure for table `assign`
-- 

CREATE TABLE `assign` (
  `group_id` int(8) NOT NULL,
  `questionary_id` int(8) NOT NULL,
  PRIMARY KEY  (`group_id`,`questionary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Table structure for table `groupp`
-- 

CREATE TABLE `groupp` (
  `id` int(8) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

INSERT INTO `groupp` VALUES (1, 'Admins');

-- --------------------------------------------------------

-- 
-- Table structure for table `member`
-- 

CREATE TABLE `member` (
  `employeenumber` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `address` text NOT NULL,
  `city` varchar(50) NOT NULL,
  `postalcode` int(15) NOT NULL,
  `country` varchar(50) NOT NULL,
  `privatemail` varchar(80) NOT NULL,
  `phone` int(20) NOT NULL,
  PRIMARY KEY  (`employeenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `member` VALUES (12345, 'Admin', 'Admin', 'Street 1A', 'Barcelona', 000001, 'Spain', 'admin@admin.com', 0001020304);

-- --------------------------------------------------------

-- 
-- Table structure for table `question`
-- 

CREATE TABLE `question` (
  `id` int(8) NOT NULL auto_increment,
  `text` text NOT NULL,
  `questionary_id` int(8) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

-- --------------------------------------------------------

-- 
-- Table structure for table `questionary`
-- 

CREATE TABLE `questionary` (
  `id` int(8) NOT NULL auto_increment,
  `name` varchar(40) NOT NULL,
  `description` text NOT NULL,
  `startdate` varchar(16) NOT NULL,
  `enddate` varchar(16) NOT NULL,
  `employeenumber` int(10) NOT NULL,
  `locked` int(1) NOT NULL default '1',
  `lockedresults` int(1) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

-- --------------------------------------------------------

-- 
-- Table structure for table `reply`
-- 

CREATE TABLE `reply` (
  `id` int(8) NOT NULL auto_increment,
  `enddate` varchar(16) NOT NULL,
  `employeenumber` int(8) NOT NULL,
  `questionary_id` int(8) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=latin1 AUTO_INCREMENT=145 ;

-- --------------------------------------------------------

-- 
-- Table structure for table `result`
-- 

CREATE TABLE `result` (
  `reply_id` int(8) NOT NULL,
  `question_id` int(8) NOT NULL,
  `answer_id` int(8) NOT NULL,
  PRIMARY KEY  (`reply_id`,`question_id`,`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Table structure for table `user`
-- Password is encrypted in sha1()
-- 

CREATE TABLE `user` (
  `employeenumber` int(10) NOT NULL,
  `group_id` int(8) default NULL,
  `employeepassword` text,
  `role` char(1) NOT NULL,
  `mail` varchar(80) default NULL,
  `activated` int(1) NOT NULL default '0',
  PRIMARY KEY  (`employeenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user` VALUES (12345, 1, 'd033e22ae348aeb5660fc2140aec35850c4da997', 'A', 'admin@admin.com', 0);
