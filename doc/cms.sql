-- ---------------------CMS Begin---------------------

-- -----------insert data to menu-----------
insert into MENU(`MENUNAME`,`STATUS`,`ORDERNUM`,`CREATETIME`)values('CMS管理',1,2,'2012-08-08 08:08:08');

-- -----------insert data to catalog-----------
INSERT INTO `catalog` (`CATALOGNAME`, `STATUS`, `ORDERNUM`, `MENU_ID`, `CREATETIME`) VALUES('内容管理', 1, 1, 3, '2012-08-01 08:08:08');
INSERT INTO `catalog` (`CATALOGNAME`, `STATUS`, `ORDERNUM`, `MENU_ID`, `CREATETIME`) VALUES('CMS设置', 1, 1, 3, '2012-08-01 08:08:08');
INSERT INTO `catalog` (`CATALOGNAME`, `STATUS`, `ORDERNUM`, `MENU_ID`, `CREATETIME`) VALUES('自定义管理', 1, 1, 3, '2012-08-01 08:08:08');
INSERT INTO `catalog` (`CATALOGNAME`, `STATUS`, `ORDERNUM`, `MENU_ID`, `CREATETIME`) VALUES('广告管理', 1, 1, 3, '2012-08-01 08:08:08');
INSERT INTO `catalog` (`CATALOGNAME`, `STATUS`, `ORDERNUM`, `MENU_ID`, `CREATETIME`) VALUES('文章分类', 1, 1, 3, '2012-08-01 08:08:08');
INSERT INTO `catalog` (`CATALOGNAME`, `STATUS`, `ORDERNUM`, `MENU_ID`, `CREATETIME`) VALUES('列表页管理', 1, 1, 3, '2012-08-01 08:08:08');
INSERT INTO `catalog` (`CATALOGNAME`, `STATUS`, `ORDERNUM`, `MENU_ID`, `CREATETIME`) VALUES('友链管理', 1, 1, 3, '2012-08-01 08:08:08');

-- -----------insert data to module-----------
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('文章管理', '/admin/article', 'articleAction', 5, 1,'2012-08-01 08:08:08');

insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('频道管理', '/admin/channel', 'channelAction', 5, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('文章栏目', '/admin/articleColumn', 'articleColumnAction', 10, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('文章类别', '/admin/articleCategory', 'articleCategoryAction', 5, 1,'2012-08-01 08:08:08');

insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('常规设置', '/admin/setting', 'settingAction', 6, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('自定义页面', '/admin/customPage', 'customPageAction', 7, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('广告位管理', '/admin/adSpace', 'adSpaceAction', 8, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('广告管理', '/admin/ad', 'adAction', 8, 1,'2012-08-01 08:08:08');

insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('友链网站', '/admin/linkSite', 'linkSiteAction', 9, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('友链页面', '/admin/linkPage', 'linkPageAction', 10, 1,'2012-08-01 08:08:08');

insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('模板管理', '/admin/templete', 'templeteAction', 5, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('模板标签', '/admin/templeteTag', 'templeteTagAction', 9, 1,'2012-08-01 08:08:08');
insert into MODULE(`MODULENAME`,`MODULEURL`,`MODULEACT`,`CATALOG_ID`,`STATUS`,`CREATETIME`)values('块管理', '/admin/block', 'blockAction', 9, 1,'2012-08-01 08:08:08');



DROP TABLE IF EXISTS ARTICLE CASCADE;
CREATE TABLE `ARTICLE` (
  `ID` int(11) NOT NULL auto_increment,
  `COLUMN_CODE` varchar(255) NOT NULL COMMENT '栏目编码',
  `CATEGORY_CODE` varchar(255) NOT NULL COMMENT '类别编码',
  `TITLE` varchar(255) NOT NULL COMMENT '标题',
  `AUTH` varchar(255) default NULL COMMENT '作者',
  `SHORT_TITLE` varchar(255) default NULL COMMENT '短标题',
  `ARTICLE_IMG` varchar(255) default NULL COMMENT '导读图',
  `BRIEF` TEXT default NULL COMMENT '摘要',
  `CONTENT` LONGTEXT default NULL COMMENT '内容',
  `STATUS` int(2) NOT NULL COMMENT '文章状态',
  `TEMPLETE_ID` int(11) NOT NULL COMMENT '文章模板',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';


DROP TABLE IF EXISTS TEMPLETE CASCADE;
CREATE TABLE `TEMPLETE` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `TYPE` int(11) default 0 COMMENT '类型：1、首页；2、列表页；3、文章页',
  `CONTENT` TEXT default NULL COMMENT '内容',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板';

insert into TEMPLETE(`NAME`,`TYPE`,`CONTENT`,`CREATEUSER`,`CREATETIME`,`UPDATEUSER`,`UPDATETIME`)values('文章通用模板',3,'<!DOCTYPE html><html lang="zh-cn"><head><meta charset="${setting.getCharSet()}" /><title>文章模板</title><meta name="keywords" content="${article.getTitle()}" /><meta name="description" content="${article.getTitle()}" /></head><body>${article.getTitle()}<br/>作者：${article.getCreateuser()}发表时间：${article.getCreatetime()?string("yyyy-MM-dd HH:mm:ss")}<br/>${article.getContent()}</body></html>','管理员','2012-08-08 08:08:08','管理员','2012-08-08 08:08:08');
insert into TEMPLETE(`NAME`,`TYPE`,`CONTENT`,`CREATEUSER`,`CREATETIME`,`UPDATEUSER`,`UPDATETIME`)values('列表页通用模板',2,'<!DOCTYPE html><html lang="zh-cn"><head><meta charset="${setting.getCharSet()}" /><title>文章模板</title><meta name="keywords" content="${article.getTitle()}" /><meta name="description" content="${article.getTitle()}" /></head><body>${article.getTitle()}<br/>作者：${article.getCreateuser()}发表时间：${article.getCreatetime()?string("yyyy-MM-dd HH:mm:ss")}<br/>${article.getContent()}</body></html>','管理员','2012-08-08 08:08:08','管理员','2012-08-08 08:08:08');
insert into TEMPLETE(`NAME`,`TYPE`,`CONTENT`,`CREATEUSER`,`CREATETIME`,`UPDATEUSER`,`UPDATETIME`)values('首页通用模板',1,'<!DOCTYPE html><html lang="zh-cn"><head><meta charset="${setting.getCharSet()}" /><title>首页模板</title><meta name="keywords" content="${article.getTitle()}" /><meta name="description" content="${article.getTitle()}" /></head><body>${article.getTitle()}<br/>作者：${article.getCreateuser()}发表时间：${article.getCreatetime()?string("yyyy-MM-dd HH:mm:ss")}<br/>${article.getContent()}</body></html>','管理员','2012-08-08 08:08:08','管理员','2012-08-08 08:08:08');

DROP TABLE IF EXISTS TEMPLETE_TAG CASCADE;
CREATE TABLE `TEMPLETE_TAG` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `CODE` varchar(255) NOT NULL COMMENT '编码',
  `TYPE` int(11) default 0 COMMENT '类型：1、首页；2、列表页；3、文章页',
  `DATA_TYPE` int(11) default 0 COMMENT '数据类型：1、文章',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板标签';

DROP TABLE IF EXISTS SETTING CASCADE;
CREATE TABLE `SETTING` (
  `ID` int(11) NOT NULL auto_increment,
  `SITE_TITLE` varchar(255) NOT NULL COMMENT '站点标题',
  `SECONDARY_TITLE` varchar(255) NOT NULL COMMENT '副标题',
  `SITE_PATH` varchar(255) NOT NULL COMMENT '站点发布路径',
  `SITE_URL` varchar(255) NOT NULL COMMENT '站点访问路径',
  `CHAR_SET` varchar(255) default NULL COMMENT '文件编码',
  `LIST_COUNT` int(11) default 20 COMMENT '列表页每页显示数据条数',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常规设置';

insert into SETTING(`SITE_TITLE`,`SECONDARY_TITLE`,`SITE_PATH`,`SITE_URL`, `CHAR_SET`,`LIST_COUNT`,`UPDATEUSER`,`UPDATETIME`)values('示例站点','这是一个示例站点','D:/acomp/','http://localhost/','UTF-8',20,'admin','2012-08-08 08:08:08');


DROP TABLE IF EXISTS CHANNEL CASCADE;
CREATE TABLE `CHANNEL` (
  `ID` int(11) NOT NULL auto_increment,
  `CODE` varchar(255) NOT NULL COMMENT '编码',
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `BRIEF` TEXT default NULL COMMENT '摘要',
  `TEMPLETE_ID` int(11) default 0 COMMENT '模板ID',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `ORDERNUM` int(11) NOT NULL COMMENT '排序',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='频道';


DROP TABLE IF EXISTS ARTICLE_COLUMN CASCADE;
CREATE TABLE `ARTICLE_COLUMN` (
  `ID` int(11) NOT NULL auto_increment,
  `CODE` varchar(255) NOT NULL COMMENT '编码',
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `CHANNEL_ID` int(11) NOT NULL COMMENT '频道ID',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `ORDERNUM` int(11) NOT NULL COMMENT '排序',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章栏目';


DROP TABLE IF EXISTS ARTICLE_CATEGORY CASCADE;
CREATE TABLE `ARTICLE_CATEGORY` (
  `ID` int(11) NOT NULL auto_increment,
  `CODE` varchar(255) NOT NULL COMMENT '编码',
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `COLUMN_ID` int(11) default 0 COMMENT '栏目ID',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `ORDERNUM` int(11) NOT NULL COMMENT '排序',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章类别';


DROP TABLE IF EXISTS BLOCK CASCADE;
CREATE TABLE `BLOCK` (
  `ID` int(11) NOT NULL auto_increment,
  `NAME` varchar(255) NOT NULL COMMENT '名称',
  `FILE_NAME` varchar(255) NOT NULL COMMENT '文件名称',
  `TYPE` int(2) NOT NULL COMMENT '类型：1、自动块；2、手动块',
  `DESCRIPTION` varchar(255) default NULL COMMENT '描述',
  `INCLUDE_PATH` varchar(255) default NULL COMMENT '包含地址',
  `SOURCE_TYPE` int(2) NOT NULL COMMENT '数据来源类型：1、数据筛选；2、外部接口',
  `DATA_TYPE` int(2) NOT NULL COMMENT '数据类型：1、文章',
  `CONDITION` TEXT default NULL COMMENT '筛选条件',
  `TEMPLETE_ID` int(11) NOT NULL COMMENT '模板ID',
  `COUNT`int(11) NOT NULL COMMENT '数据条数',
  `INTERFACE_URL` varchar(255) default NULL COMMENT '外部接口地址',
  `CONTENT` TEXT default NULL COMMENT '块内容',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='块';


DROP TABLE IF EXISTS LINK_SITE CASCADE;
CREATE TABLE `LINK_SITE` (
  `ID` int(11) NOT NULL auto_increment,
  `SITE_NAME` varchar(255) NOT NULL COMMENT '网站名称',
  `SITE_URL` varchar(255) NOT NULL COMMENT '网站URL',
  `SITE_LOGO` varchar(255) default NULL COMMENT '网站LOGO',
  `DESCRIPTION` TEXT default NULL COMMENT '网站简介',
  `CONTACT` varchar(255) default NULL COMMENT '联系人',
  `QQ` varchar(255) default NULL COMMENT 'QQ',
  `TEL` varchar(255) default NULL COMMENT '座机',
  `MOBILE` varchar(255) default NULL COMMENT '手机',
  `EMAIL` varchar(255) default NULL COMMENT 'Email',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友链网站';


DROP TABLE IF EXISTS LINK_PAGE CASCADE;
CREATE TABLE `LINK_PAGE` (
  `ID` int(11) NOT NULL auto_increment,
  `PAGE_NAME` varchar(255) NOT NULL COMMENT '页面名称',
  `INCLUDE_PATH` varchar(255) default NULL COMMENT '包含地址',
  `DESCRIPTION` TEXT default NULL COMMENT '网站简介',
  `TEMPLETE_ID` int(11) NOT NULL COMMENT '模板ID',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友链网页';

DROP TABLE IF EXISTS LINK_RELATION CASCADE;
CREATE TABLE `LINK_RELATION` (
  `ID` int(11) NOT NULL auto_increment,
  `PAGE_ID` int(11) NOT NULL COMMENT '页面ID',
  `SITE_ID` int(11) NOT NULL COMMENT '网站ID',
  `ORDERNUM` int(11) NOT NULL COMMENT '排序',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友链网页与网站关系';


DROP TABLE IF EXISTS COMMENT CASCADE;
CREATE TABLE `COMMENT` (
  `ID` int(11) NOT NULL auto_increment,
  `CONTENT` LONGTEXT default NULL COMMENT '内容',
  `USER_ID` varchar(255) NOT NULL COMMENT '评论人',
  `COMMENTTIME` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';


DROP TABLE IF EXISTS CUSTOM_PAGE CASCADE;
CREATE TABLE `CUSTOM_PAGE` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(255) NOT NULL COMMENT '标题',
  `CODE` varchar(255) default NULL COMMENT '页面编号',
  `CUSTOM_PATH` varchar(255) default NULL COMMENT '自定义页面路径',
  `REMARK` varchar(255) default NULL COMMENT '备注',
  `CONTENT` LONGTEXT default NULL COMMENT '内容',
  `STATUS` int(2) NOT NULL COMMENT '页面状态',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义页面';


DROP TABLE IF EXISTS AD_SPACE CASCADE;
CREATE TABLE `AD_SPACE` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(255) NOT NULL COMMENT '标题',
  `CODE` varchar(255) default NULL COMMENT '广告编号',
  `TYPE` int(2) default NULL COMMENT '广告类型：1、图文广告；2、脚本广告',
  `WIDTH` int(11) default 0 COMMENT '宽',
  `HEIGHT` int(11) default 0 COMMENT '高',
  `CONTENT` TEXT default NULL COMMENT '内容',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告位';

DROP TABLE IF EXISTS AD CASCADE;
CREATE TABLE `AD` (
  `ID` int(11) NOT NULL auto_increment,
  `AD_SPACE_CODE` varchar(255) NOT NULL COMMENT '所属广告位编号',
  `TITLE` varchar(255) NOT NULL COMMENT '标题',
  `IMG` varchar(255) NOT NULL COMMENT '图片地址',
  `URL` varchar(255) NOT NULL COMMENT '链接地址',
  `BRIEF` TEXT default NULL COMMENT '摘要',
  `STATUS` int(2) NOT NULL COMMENT '状态',
  `ORDERNUM` int(11) default 0 COMMENT '排序',
  `CREATEUSER` varchar(255) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime default '2000-01-01 00:00:00' COMMENT '创建时间',
  `UPDATEUSER` varchar(255) default NULL COMMENT '修改人',
  `UPDATETIME` datetime default '2000-01-01 00:00:00' COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告';

-- ---------------------CMS End---------------------