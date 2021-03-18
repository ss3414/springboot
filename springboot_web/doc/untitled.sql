/************************************************************分割线************************************************************/
/*
todo 动态表结构
字段类型（文本框/多媒体）
资源类型=元数据+包含的字段列表
资源=元数据+包含的字段列表
*/

/* 字段类型 */
CREATE TABLE `field`  (
  `field_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_type` int(11) NULL DEFAULT 0,
  `field_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`field_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/* 资源类型元数据 */
CREATE TABLE `type_meta`  (
  `type_meta_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_meta_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`type_meta_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/* 资源类型字段 */
CREATE TABLE `type_field`  (
  `type_field_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_field_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `field_id` int(11) NULL DEFAULT 0,
  `type_meta_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`type_field_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/* 资源元数据 */
CREATE TABLE `resource_meta`  (
  `resource_meta_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_meta_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`resource_meta_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/* 资源字段 */
CREATE TABLE `resource_field`  (
  `resource_field_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_field_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `type_field_id` int(11) NULL DEFAULT 0,
  `resource_meta_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`resource_field_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/************************************************************半分割线******************************/

/* 数据 */
INSERT INTO `field` VALUES (1, 1, '文本框');
INSERT INTO `field` VALUES (2, 2, '多媒体');

INSERT INTO `type_field` VALUES (1, '标题', 1, 1);
INSERT INTO `type_field` VALUES (2, '图片', 2, 1);

INSERT INTO `type_meta` VALUES (1, '文章');

INSERT INTO `resource_field` VALUES (1, '文章标题', 1, 1);
INSERT INTO `resource_field` VALUES (2, '文章图片', 2, 1);

INSERT INTO `resource_meta` VALUES (1, '文章');

/************************************************************半分割线******************************/

/* 查询资源类型所有字段 */
SELECT a.*,c.*,b.type_field_id,b.type_field_title
FROM `type_meta` a,`type_field` b,`field` c
WHERE a.type_meta_id = b.type_meta_id AND b.field_id = c.field_id AND a.type_meta_id = 1

/* 查询资源所有字段 */
SELECT a.*,c.type_field_id,c.type_field_title,b.resource_field_id,b.resource_field_content
FROM `resource_meta` a,`resource_field` b,`type_field` c
WHERE a.resource_meta_id = b.resource_meta_id AND b.type_field_id = c.type_field_id AND a.resource_meta_id = 1

/************************************************************分割线************************************************************/
/* todo 节点 */

CREATE TABLE `node`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0,
  `level` int(11) NULL DEFAULT 1, /* 树深度 */
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `node`(`id`, `parent_id`, `level`, `title`) VALUES (1, 0, 1, '父节点');
INSERT INTO `node`(`id`, `parent_id`, `level`, `title`) VALUES (2, 1, 2, '子节点1');
INSERT INTO `node`(`id`, `parent_id`, `level`, `title`) VALUES (3, 1, 2, '子节点2');
INSERT INTO `node`(`id`, `parent_id`, `level`, `title`) VALUES (4, 2, 3, '孙节点1');