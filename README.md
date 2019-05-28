# GraphQL-java-SDL
#### 该demo主要是Java对GraphQL的实现，是在项目开发之前对GraphQL的一次探索，基于大项目进行构建。利用SDL（IDL）进行schema文件定义，个人感觉SDL比纯Java代码实现GraphQL会更简洁明了，不会出现代码冗余的情况。本demo是基于[这个案例](https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/) 进行的扩展延伸,刚刚开始接触GraphQL-java的同学建议先熟悉上面的案例。
### 以下是一些学习graphql的网站  
- [GraphQL桌面调试工具](https://github.com/prisma/graphql-playground/releases/tag/v1.8.10)  
- [GraphQL学习文档](https://www.graphql-java.com/documentation)  
- [GraphQL中文学习官网](https://graphql.cn/learn/schema/)  
- [GraphQL英文学习官网](https://graphql.org/learn/schema/)  
- [schema语法规范](https://developer.github.com/v4/)  
- [graphql-schema-language-cheat-sheet(SDL基本语法)](https://github.com/sogko/graphql-schema-language-cheat-sheet)

### 如果遇到以下问题可参考这个demo  
- 如何建立多个schema文件  
- 如何对多个schema文件进行合并  
- 如何在分模块开发的情况下生成全局唯一的RuntimeWiring  
### 环境要求
- JDK1.8 + 
### 有关GraphQL用到的依赖
```
        <!--GraphQL-->
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-spring-boot-starter</artifactId>
            <version>4.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphiql-spring-boot-starter</artifactId>
            <version>4.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java-spring-boot-starter-webmvc</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>com.graphql-java</groupId>
            <artifactId>graphql-java</artifactId>
            <version>11.0</version>
        </dependency>
```

### 测试用的数据库表格及内容  
```
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : graphqltest

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-05-23 09:10:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` varchar(64) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of author
-- ----------------------------
INSERT INTO `author` VALUES ('author-1', 'Joanne', 'Rowling', '2019-05-22 17:03:59', '2019-05-22 17:04:30');
INSERT INTO `author` VALUES ('author-2', 'Herman', 'Melville', '2019-05-22 17:03:59', '2019-05-22 17:04:30');
INSERT INTO `author` VALUES ('author-3', 'Anne', 'Rice', '2019-05-22 17:03:59', '2019-05-22 17:04:30');
INSERT INTO `author` VALUES ('author-5', '莫言', '黄花', '2019-05-22 17:03:59', '2019-05-22 17:04:30');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `page_count` int(32) NOT NULL,
  `author_id` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='books';

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('book-1', '哈利波特与死亡圣器', '101', 'author-1', '2019-05-22 17:05:33', '2019-05-22 17:05:33');
INSERT INTO `book` VALUES ('book-3', '人生', '500', 'author-3', '2019-05-22 17:05:33', '2019-05-22 17:05:33');
INSERT INTO `book` VALUES ('book-4', '平凡的世界', '1000', 'author-4', '2019-05-22 17:05:33', '2019-05-22 17:05:33');
INSERT INTO `book` VALUES ('book-5', '草原', '500', 'author-5', '2019-05-22 17:05:33', '2019-05-22 17:05:33');
INSERT INTO `book` VALUES ('book-6', '原野', '576', 'author-6', '2019-05-22 17:05:33', '2019-05-22 17:05:33');
INSERT INTO `book` VALUES ('book-7', '原来如初', '666', 'author-7', '2019-05-22 17:05:33', '2019-05-22 17:05:33');
INSERT INTO `book` VALUES ('book-8', '穆斯林的葬礼', '999', 'author-8', '2019-05-22 17:05:33', '2019-05-22 17:05:33');
INSERT INTO `book` VALUES ('book-9', '草根成长记（3）', '789', 'author-9', '2019-05-22 17:09:17', '2019-05-22 17:13:17');
```
