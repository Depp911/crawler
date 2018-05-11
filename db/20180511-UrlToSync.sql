CREATE TABLE `UrlToSync` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start` tinyint(4) NOT NULL,
  `url` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `addTime` datetime(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
