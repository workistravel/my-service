CREATE TABLE `mybase`.`employee` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `date_empl` DATETIME NULL DEFAULT NULL,
  `is_active` BIT(1) NOT NULL DEFAULT b'0',
  `salary` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `mybase`.`union` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `mybase`.`employee_union` (
  `employee_id` BIGINT(20) NOT NULL,
  `union_id` BIGINT(20) NOT NULL,
  INDEX `fk_employee_idx` (`employee_id` ASC) VISIBLE,
  INDEX `fk_union_idx` (`union_id` ASC) VISIBLE,
  CONSTRAINT `fk_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `mybase`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_union`
    FOREIGN KEY (`union_id`)
    REFERENCES `mybase`.`union` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


INSERT INTO `mybase`.`unions` (`id`, `name`) VALUES ('4', 'WORKER');
INSERT INTO `mybase`.`unions` (`id`, `name`) VALUES ('5', 'ADMIN');

INSERT INTO `mybase`.`employee_union` (`employee_id`, `union_id`) VALUES ('2', '2');
INSERT INTO `mybase`.`employees_unions` (`employees_id`, `unions_id`) VALUES ('2', '1');

DELETE FROM `mybase`.`employees_unions` WHERE (`employees_id` = '2') and (`unions_id` = '2');
