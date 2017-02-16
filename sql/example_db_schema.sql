CREATE TABLE `product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(11) NOT NULL,
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `pics` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci,
  `product_desc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci DEFAULT NULL,
  `product_detail` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_product_store` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci