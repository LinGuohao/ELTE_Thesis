/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : movie_db

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/04/2022 09:55:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- create database
create database `movie_db`;
SET character_set_client = utf8;
use movie_db;
-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IMDb` double NULL DEFAULT NULL,
  `Tomatoes` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('12345', 'Forrest Gump', 8.8, 70);
INSERT INTO `info` VALUES ('123456', 'The Shawshank Redemption', 9.3, 91);
INSERT INTO `info` VALUES ('12345678', 'The Legend of 1900', 8, 54);
INSERT INTO `info` VALUES ('123456789', 'Schindler\'s List ', 9, 98);

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('12345', 'Forrest Gump');
INSERT INTO `movie` VALUES ('123456', 'The Shawshank Redemption');
INSERT INTO `movie` VALUES ('12345678', 'The Legend of 1900');
INSERT INTO `movie` VALUES ('123456789', 'Schindler\'s List ');

-- ----------------------------
-- Table structure for movie_lines
-- ----------------------------
DROP TABLE IF EXISTS `movie_lines`;
CREATE TABLE `movie_lines`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sentence` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `line_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie_lines
-- ----------------------------
INSERT INTO `movie_lines` VALUES ('12345', 'Life was like a box of chocolates, you never know what you\'re going to get.', 'Gump', 'dadasd');
INSERT INTO `movie_lines` VALUES ('12345', 'I don\'t know if we each have a destiny, or if we\'re all just floating around accidental—like on a breeze.', 'Gump', 'fdsfeqe');
INSERT INTO `movie_lines` VALUES ('12345', 'You have got to put the past behind you before you can move on.', 'Gump', 'afdfqeq');
INSERT INTO `movie_lines` VALUES ('123456', 'I guess it comes down to a simple choice: get busy living or get busy dying.', 'The Shawshank Redemption', 'agdfase');
INSERT INTO `movie_lines` VALUES ('123456', 'Remember,Red, hope is good thing, mabye the best of things. And no good thing ever dies.', 'The Shawshank Redemption', 'fafaweq');
INSERT INTO `movie_lines` VALUES ('123456', 'Here’swhere it makes the most sense. You need it so you don\"t foget . Foget thatthere are palce in the world that aren’t made out of stone . That there’sa---there’s a---there’s something inside that’s yours, that they can’t touch.', 'The Shawshank Redemption', 'asfawqe');

-- ----------------------------
-- Table structure for movie_user
-- ----------------------------
DROP TABLE IF EXISTS `movie_user`;
CREATE TABLE `movie_user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roles` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie_user
-- ----------------------------
INSERT INTO `movie_user` VALUES ('test1@gmail.com', 'c8ba490d22ca6abe9b28c75cafb8bbb76f51c27a73d86859f4457b9043aa722a', 'test1', 'user');
INSERT INTO `movie_user` VALUES ('test@gmail.com', '071acf9f1aac9d0207dde828a4852454937aeb122ae0c89afa317a3f33f2f8d0', 'lgh', 'user');
INSERT INTO `movie_user` VALUES ('admin@gmail.com', 'c8ba490d22ca6abe9b28c75cafb8bbb76f51c27a73d86859f4457b9043aa722a', 'admin', 'admin');
INSERT INTO `movie_user` VALUES ('test2@gmail.com', 'c8ba490d22ca6abe9b28c75cafb8bbb76f51c27a73d86859f4457b9043aa722a', 'vk', 'user');

-- ----------------------------
-- Table structure for user_comment
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment`  (
  `commentID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `movieID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_comment
-- ----------------------------
INSERT INTO `user_comment` VALUES ('164918825327383614', 'admin@gmail.com', '12345', 'Ut has tritani vivendum pericula, qui accusam quaestio accommodare et. Eam ad vidisse democritum sadipscing. Summo minimum vivendum has ad, facilisi intellegat ne sit. Mel probatus facilisi cu, an modo deleniti qui, et quo virtute appareat delicata. Ex iriure iuvaret accumsan est, vim hinc fabulas te. Eu vivendo voluptatum appellantur eum, nemore vocent saperet at vim.');
INSERT INTO `user_comment` VALUES ('164920635283882568', 'admin@gmail.com', '12345', 'Wisi regione nec ut, causae concludaturque cu has. Ex harum noster volumus vis, pri eu autem aliquid. At choro sapientem ius, ne dicant eruditi appareat cum, eos ne tempor volumus. Cum sint augue lobortis et, no mea utamur contentiones. Pro sale blandit abhorreant ad. Quem tritani in vix.');
INSERT INTO `user_comment` VALUES ('164921369882816674', 'test1@gmail.com', '12345', 'Euismod percipit vim no. Ex dicta disputando sed. Essent prompta et vix, mei id vivendum erroribus. Debet platonem ea est, labore signiferumque an per. Tota patrioque honestatis et vim. Nibh alterum definitionem cum et, at saepe nonumes qui. Eam tantas libris petentium at.');

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_like
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
