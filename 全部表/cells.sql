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

 Date: 03/07/2024 18:43:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cells
-- ----------------------------
DROP TABLE IF EXISTS `cells`;
CREATE TABLE `cells`  (
  `cell_id` int NOT NULL COMMENT '囚犯ID',
  `block_number` int NULL DEFAULT NULL COMMENT '监区号',
  `cell_number` int NULL DEFAULT NULL COMMENT '牢房号',
  PRIMARY KEY (`cell_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '牢房信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cells
-- ----------------------------
INSERT INTO `cells` VALUES (27, 0, 216);
INSERT INTO `cells` VALUES (28, 0, 216);
INSERT INTO `cells` VALUES (29, 0, 216);
INSERT INTO `cells` VALUES (30, 0, 216);
INSERT INTO `cells` VALUES (31, 0, 216);
INSERT INTO `cells` VALUES (32, 0, 216);
INSERT INTO `cells` VALUES (33, 1, 217);
INSERT INTO `cells` VALUES (34, 1, 217);
INSERT INTO `cells` VALUES (35, 1, 217);
INSERT INTO `cells` VALUES (36, 1, 217);
INSERT INTO `cells` VALUES (37, 1, 217);
INSERT INTO `cells` VALUES (38, 1, 217);
INSERT INTO `cells` VALUES (39, 2, 218);
INSERT INTO `cells` VALUES (40, 2, 218);
INSERT INTO `cells` VALUES (41, 2, 218);
INSERT INTO `cells` VALUES (42, 2, 218);
INSERT INTO `cells` VALUES (43, 2, 218);
INSERT INTO `cells` VALUES (44, 0, 219);
INSERT INTO `cells` VALUES (45, 0, 219);
INSERT INTO `cells` VALUES (46, 0, 219);
INSERT INTO `cells` VALUES (47, 0, 219);
INSERT INTO `cells` VALUES (48, 0, 219);
INSERT INTO `cells` VALUES (49, 1, 220);
INSERT INTO `cells` VALUES (50, 1, 220);
INSERT INTO `cells` VALUES (51, 1, 220);
INSERT INTO `cells` VALUES (52, 1, 220);
INSERT INTO `cells` VALUES (53, 1, 220);

SET FOREIGN_KEY_CHECKS = 1;
