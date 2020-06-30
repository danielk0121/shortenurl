CREATE TABLE `shortenurl` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`encodedUrl` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`url` TEXT(65535) NOT NULL DEFAULT '' COLLATE 'utf8_general_ci',
	`viewcnt` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `encodedUrl` (`encodedUrl`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=10
;
