SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `contacts` DEFAULT CHARACTER SET utf8 ;
USE `contacts` ;

-- -----------------------------------------------------
-- Table `contactlist`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contacts`.`country` (
  `idCountry` INT(11) NOT NULL,
  `city` LONGTEXT NULL DEFAULT NULL,
  `region` LONGTEXT NULL DEFAULT NULL,
  `country` LONGTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idCountry`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contactlist`.`p_c`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `contacts`.`p_c` (
  `firstName` LONGTEXT NULL DEFAULT NULL,
  `lastName` LONGTEXT NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `city` VARCHAR(50) NULL DEFAULT NULL,
  `eMail` VARCHAR(30) NOT NULL,
  `phoneNumber` INT(11) NULL,
  PRIMARY KEY (`eMail`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
