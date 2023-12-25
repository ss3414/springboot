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
