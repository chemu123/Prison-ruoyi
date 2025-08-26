/*
 Navicat Premium Dump SQL

 Source Server         : 新连接1
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : ry-che

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 03/07/2024 18:43:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for prisoner_photos
-- ----------------------------
DROP TABLE IF EXISTS `prisoner_photos`;
CREATE TABLE `prisoner_photos`  (
  `first_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `inmate_img` longblob NULL COMMENT '照片'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '囚犯照片' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prisoner_photos
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
