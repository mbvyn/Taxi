-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema taxi
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `taxi` ;

-- -----------------------------------------------------
-- Schema taxi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taxi` DEFAULT CHARACTER SET utf8 ;
USE `taxi` ;

-- -----------------------------------------------------
-- Table `taxi`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `phone_number` BIGINT(10) NOT NULL,
  `discount` TINYINT NOT NULL DEFAULT 0,
  `role` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`route` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `departure` VARCHAR(300) NOT NULL,
  `arrival` VARCHAR(300) NOT NULL,
  `distance` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `route_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `number_of_passangers` INT NOT NULL,
  `create_date` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`id`, `route_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_route1_idx` (`route_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `taxi`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`car_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`car_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(10) NOT NULL,
  `number_of_seats` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `category_UNIQUE` (`category` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` ENUM('to_order', 'in_run', 'inactive') NOT NULL,
  `car_details_id` INT NOT NULL,
  PRIMARY KEY (`id`, `car_details_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_car_car_details1_idx` (`car_details_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_car_details1`
    FOREIGN KEY (`car_details_id`)
    REFERENCES `taxi`.`car_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`order_has_car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`order_has_car` (
  `order_id` INT NOT NULL,
  `car_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `car_id`),
  INDEX `fk_order_has_car_car1_idx` (`car_id` ASC) VISIBLE,
  INDEX `fk_order_has_car_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_has_car_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `taxi`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_has_car_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `taxi`.`car` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `short_name` VARCHAR(2) NOT NULL,
  `full_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `short_name_UNIQUE` (`short_name` ASC) VISIBLE,
  UNIQUE INDEX `full_name_UNIQUE` (`full_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`account_has_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`account_has_order` (
  `account_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`account_id`, `order_id`),
  INDEX `fk_account_has_order_order1_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_account_has_order_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_has_order_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `taxi`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_has_order_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `taxi`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `taxi`.`car_has_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `taxi`.`car_has_language` (
  `car_id` INT NOT NULL,
  `language_id` INT NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`car_id`, `language_id`),
  INDEX `fk_car_has_language_language1_idx` (`language_id` ASC) VISIBLE,
  INDEX `fk_car_has_language_car1_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_has_language_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `taxi`.`car` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_car_has_language_language1`
    FOREIGN KEY (`language_id`)
    REFERENCES `taxi`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO account VALUES(DEFAULT, 'admin', 'admin@admin', '0cc175b9c0f1b6a831c399e269772661', '1234567890',  DEFAULT, true);
INSERT INTO account VALUES(DEFAULT, 'user', 'user@user', '7b774effe4a349c6dd82ad4f4f21d34c', '0987654321',  DEFAULT, DEFAULT);
INSERT INTO account VALUES(DEFAULT, 'user1', 'user1@user', '7b774effe4a349c6dd82ad4f4f21d34c', '0887654321',  DEFAULT, DEFAULT);

INSERT INTO language VALUES (default, 'en', 'English');
INSERT INTO language VALUES (default, 'uk', 'Українська');

INSERT INTO car_details VALUES (DEFAULT, 'economic', 3);
INSERT INTO car_details VALUES (DEFAULT, 'comfort', 3);
INSERT INTO car_details VALUES (DEFAULT, 'business', 3);
INSERT INTO car_details VALUES (DEFAULT, 'minivan', 6);

INSERT INTO car VALUES (DEFAULT, 'to_order', 1);
INSERT INTO car VALUES (DEFAULT, 'to_order', 1);
INSERT INTO car VALUES (DEFAULT, 'to_order', 1);
INSERT INTO car VALUES (DEFAULT, 'to_order', 1);
INSERT INTO car VALUES (DEFAULT, 'to_order', 1);
INSERT INTO car VALUES (DEFAULT, 'to_order', 1);
INSERT INTO car VALUES (DEFAULT, 'to_order', 1);
INSERT INTO car VALUES (DEFAULT, 'to_order', 2);
INSERT INTO car VALUES (DEFAULT, 'to_order', 2);
INSERT INTO car VALUES (DEFAULT, 'to_order', 2);
INSERT INTO car VALUES (DEFAULT, 'to_order', 2);
INSERT INTO car VALUES (DEFAULT, 'to_order', 2);
INSERT INTO car VALUES (DEFAULT, 'to_order', 2);
INSERT INTO car VALUES (DEFAULT, 'to_order', 3);
INSERT INTO car VALUES (DEFAULT, 'to_order', 3);
INSERT INTO car VALUES (DEFAULT, 'to_order', 3);
INSERT INTO car VALUES (DEFAULT, 'to_order', 3);
INSERT INTO car VALUES (DEFAULT, 'to_order', 4);
INSERT INTO car VALUES (DEFAULT, 'to_order', 4);
INSERT INTO car VALUES (DEFAULT, 'to_order', 4);

insert into  car_has_language VALUES (1, 1, 'Honda Insight red colour');
insert into  car_has_language VALUES (1, 2, 'Honda Insight червоного кольору');
insert into  car_has_language VALUES (2, 1, 'Toyota Prius yellow colour');
insert into  car_has_language VALUES (2, 2, 'Toyota Prius жовтого кольору');
insert into  car_has_language VALUES (3, 1, 'Hyundai Ioniq blue colour');
insert into  car_has_language VALUES (3, 2, 'Hyundai Ioniq синього кольору');
insert into  car_has_language VALUES (4, 1, 'Toyota Corolla green colour');
insert into  car_has_language VALUES (4, 2, 'Toyota Corolla зеленого кольору');
insert into  car_has_language VALUES (5, 1, 'Hyundai Elantra pink colour');
insert into  car_has_language VALUES (5, 2, 'Hyundai Elantra рожевого кольору');
insert into  car_has_language VALUES (6, 1, 'Honda Accord grey colour');
insert into  car_has_language VALUES (6, 2, 'Honda Accord сірого кольору');
insert into  car_has_language VALUES (7, 1, 'Toyota Camry white colour');
insert into  car_has_language VALUES (7, 2, 'Toyota Camry білого кольору');
insert into  car_has_language VALUES (8, 1, 'Audi A6 red colour');
insert into  car_has_language VALUES (8, 2, 'Audi A6 червоного кольору');
insert into  car_has_language VALUES (9, 1, 'Audi Q7 yellow colour');
insert into  car_has_language VALUES (9, 2, 'Audi Q7 жовтого кольору');
insert into  car_has_language VALUES (10, 1, 'Audi S6 blue colour');
insert into  car_has_language VALUES (10, 2, 'Audi S6 синього кольору');
insert into  car_has_language VALUES (11, 1, 'BMW X3 green colour');
insert into  car_has_language VALUES (11, 2, 'BMW X3 зеленого кольору');
insert into  car_has_language VALUES (12, 1, 'BMW M5 pink colour');
insert into  car_has_language VALUES (12, 2, 'BMW M5 рожевого кольору');
insert into  car_has_language VALUES (13, 1, 'BMW B7 grey colour');
insert into  car_has_language VALUES (13, 2, 'BMW B7 сірого кольору');
insert into  car_has_language VALUES (14, 1, 'Tesla Model 3 red colour');
insert into  car_has_language VALUES (14, 2, 'Tesla Model 3 червоного кольору');
insert into  car_has_language VALUES (15, 1, 'Skoda Superb IV yellow colour');
insert into  car_has_language VALUES (15, 2, 'Skoda Superb IV жовтого кольору');
insert into  car_has_language VALUES (16, 1, 'Volvo XC40 blue colour');
insert into  car_has_language VALUES (16, 2, 'Volvo XC40 синього кольору');
insert into  car_has_language VALUES (17, 1, 'Range Rover Evoque green colour');
insert into  car_has_language VALUES (17, 2, 'Range Rover Evoque зеленого кольору');
insert into  car_has_language VALUES (18, 1, 'Ford Transit Connect pink colour');
insert into  car_has_language VALUES (18, 2, 'Ford Transit Connect рожевого кольору');
insert into  car_has_language VALUES (19, 1, 'Toyota Sienna grey colour');
insert into  car_has_language VALUES (19, 2, 'Toyota Sienna сірого кольору');
insert into  car_has_language VALUES (20, 1, 'Dodge Grand Caravan white colour');
insert into  car_has_language VALUES (20, 2, 'Dodge Grand Caravan білого кольору');

INSERT INTO route VALUES (DEFAULT, 'Railway_Station', 'Market_Square', 3.23);
INSERT INTO route VALUES (DEFAULT, 'Railway_Station', 'Park_of_Culture', 4.03);
INSERT INTO route VALUES (DEFAULT, 'Railway_Station', 'Lychakiv_Cemetery', 6.17);
INSERT INTO route VALUES (DEFAULT, 'Market_Square', 'Railway_Station', 3.53);
INSERT INTO route VALUES (DEFAULT, 'Market_Square', 'Park_of_Culture', 2.17);
INSERT INTO route VALUES (DEFAULT, 'Market_Square', 'Lychakiv_Cemetery', 3.68);
INSERT INTO route VALUES (DEFAULT, 'Park_of_Culture', 'Railway_Station', 4.23);
INSERT INTO route VALUES (DEFAULT, 'Park_of_Culture', 'Market_Square', 2.37);
INSERT INTO route VALUES (DEFAULT, 'Park_of_Culture', 'Lychakiv_Cemetery', 4.33);
INSERT INTO route VALUES (DEFAULT, 'Lychakiv_Cemetery', 'Railway_Station', 6.37);
INSERT INTO route VALUES (DEFAULT, 'Lychakiv_Cemetery', 'Market_Square', 3.88);
INSERT INTO route VALUES (DEFAULT, 'Lychakiv_Cemetery', 'Park_of_Culture', 4.53);

INSERT INTO `order` VALUES (DEFAULT, 2, 10.5, 3, DEFAULT);
INSERT INTO `order` VALUES (DEFAULT, 7, 45, 3, DEFAULT);
INSERT INTO `order` VALUES (DEFAULT, 1, 2, 3, DEFAULT);
INSERT INTO `order` VALUES (DEFAULT, 9, 240, 3, DEFAULT);
INSERT INTO `order` VALUES (DEFAULT, 10, 21, 3, DEFAULT);
INSERT INTO `order` VALUES (DEFAULT, 4, 781, 3, DEFAULT);
INSERT INTO `order` VALUES (DEFAULT, 11, 289, 3, DEFAULT);

INSERT INTO account_has_order VALUES (3, 1);
INSERT INTO account_has_order VALUES (2, 2);
INSERT INTO account_has_order VALUES (2, 3);
INSERT INTO account_has_order VALUES (2, 4);
INSERT INTO account_has_order VALUES (2, 5);
INSERT INTO account_has_order VALUES (2, 6);
INSERT INTO account_has_order VALUES (2, 7);

INSERT INTO order_has_car VALUES (1, 1);
INSERT INTO order_has_car VALUES (2, 3);
INSERT INTO order_has_car VALUES (3, 5);
INSERT INTO order_has_car VALUES (4, 6);
INSERT INTO order_has_car VALUES (5, 18);
INSERT INTO order_has_car VALUES (6, 19);
INSERT INTO order_has_car VALUES (7, 8);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
