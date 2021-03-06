mysql -u root -p
<rootPwd>

CREATE DATABASE 'db_messageCloud'; 
DROP USER 'sbvb'@'localhost';
CREATE USER 'sbvb'@'localhost' IDENTIFIED BY 'sbvbpwd';
GRANT ALL ON db_messageCloud.* TO 'sbvb'@'localhost';
flush privileges;
exit


///////////////

mysql -u sbvb -p
<sbvbPwd>
USE 'db_messageCloud';

drop table tb_message;
drop table tb_author;

CREATE TABLE `tb_author` (
	`author_id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` TEXT NULL DEFAULT NULL,
	PRIMARY KEY (`author_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;


CREATE TABLE `tb_message` (
	`message_id` INT(11) NOT NULL AUTO_INCREMENT,
	`author_id` INT(11) NULL DEFAULT NULL,
	`data` TEXT NULL,
	`mydate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`message_id`),
	INDEX `FK__tb_author` (`author_id`),
	CONSTRAINT `FK__tb_author` FOREIGN KEY (`author_id`) REFERENCES `tb_author` (`author_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;


show tables;

delete from tb_author;

INSERT INTO tb_author (name) VALUES ('author sbvb');
INSERT INTO tb_author (name) VALUES ('author someone');

drop table tb_message;


INSERT INTO tb_message (data,date) VALUES ('message from sbvb',now()) where 
author_id in (select author_id from tb_author where name = 'author sbvb');

INSERT INTO tb_message (data,author_id,mydate) VALUES ('message from sbvb',3,now());

select author_id from tb_author where name = 'author sbvb' as author_int;

INSERT INTO tb_message (data,author_id,mydate) 
select 'my new message',author_id,now() from tb_author where name = 'author sbvb';



where 
author_id_int in (select author_id from tb_author where name = 'author sbvb');




select author_id from tb_author where name = 'author sbvb';

