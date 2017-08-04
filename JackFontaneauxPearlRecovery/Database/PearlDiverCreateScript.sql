SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
-- -----------------------------------------------------
-- Schema PearlDiver
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `PearlDiver` ;
-- -----------------------------------------------------
-- Schema PearlDiver
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PearlDiver` DEFAULT CHARACTER SET utf8 ;
USE `PearlDiver` ;
-- -----------------------------------------------------
-- Table `PearlDiver`.`Posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`Posts` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`Posts` (
  `PostId` INT NOT NULL AUTO_INCREMENT,
  `PostDate` DATE NOT NULL,
  `ExpirationDate` DATE NULL,
  `Title` VARCHAR(50) NOT NULL,
  `Synopsis` VARCHAR(200) NOT NULL,
  `Body` LONGTEXT NOT NULL,
  `Published` TINYINT(1) NOT NULL DEFAULT 0,
  `Completed` TINYINT(1) NOT NULL DEFAULT 0,
  `Latitude` DECIMAL(6,4) NULL,
  `Longitude` DECIMAL(7,4) NULL,
  PRIMARY KEY (`PostId`),
  UNIQUE INDEX `PostId_UNIQUE` (`PostId` ASC),
  UNIQUE INDEX `Title_UNIQUE` (`Title` ASC),
  UNIQUE INDEX `Synopsis_UNIQUE` (`Synopsis` ASC));
-- -----------------------------------------------------
-- Table `PearlDiver`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`Users` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`Users` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(2000) NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `Role` VARCHAR(20) NOT NULL,
  `FirstName` VARCHAR(20) NOT NULL,
  `LastName` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Latitude` DECIMAL(6,4) NULL,
  `Longitude` DECIMAL(7,4) NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE(`UserName`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC),
  UNIQUE INDEX `UserId_UNIQUE` (`UserId` ASC));
-- -----------------------------------------------------
-- Table `PearlDiver`.`Authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`Authorities` ;
CREATE TABLE IF NOT EXISTS `Authorities` (
    `UserName` VARCHAR(20) NOT NULL,
    `Authority` VARCHAR(20) NOT NULL,
    CONSTRAINT `authorities_fk_username`
	  FOREIGN KEY (`UserName`)
	  REFERENCES `PearlDiver`.`Users` (`UserName`)
);
-- -----------------------------------------------------
-- Table `PearlDiver`.`PostsUsers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`PostsUsers` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`PostsUsers` (
  `PostId` INT NOT NULL,
  `UserId` INT NOT NULL,
  PRIMARY KEY (`PostId`, `UserId`),
  INDEX `fk_UserId_idx` (`UserId` ASC),
  CONSTRAINT `fk_PostId`
    FOREIGN KEY (`PostId`)
    REFERENCES `PearlDiver`.`Posts` (`PostId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserId`
    FOREIGN KEY (`UserId`)
    REFERENCES `PearlDiver`.`Users` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`Categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`Categories` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`Categories` (
  `CategoryId` INT NOT NULL AUTO_INCREMENT,
  `Category` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`CategoryId`),
  UNIQUE INDEX `CategoryId_UNIQUE` (`CategoryId` ASC));
-- -----------------------------------------------------
-- Table `PearlDiver`.`PostsCategories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`PostsCategories` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`PostsCategories` (
  `PostId` INT NOT NULL,
  `CategoryId` INT NOT NULL,
  PRIMARY KEY (`PostId`, `CategoryId`),
  INDEX `fk_CategoryId_idx` (`CategoryId` ASC),
  CONSTRAINT `fk_Post_Id`
    FOREIGN KEY (`PostId`)
    REFERENCES `PearlDiver`.`Posts` (`PostId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CategoryId`
    FOREIGN KEY (`CategoryId`)
    REFERENCES `PearlDiver`.`Categories` (`CategoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`PageCategories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`PageCategories` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`PageCategories` (
  `PageCategoryId` INT NOT NULL AUTO_INCREMENT,
  `PageCategory` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`PageCategoryId`),
  UNIQUE INDEX `PageCategoryId_UNIQUE` (`PageCategoryId` ASC),
  UNIQUE INDEX `PageCategory_UNIQUE` (`PageCategory` ASC));
-- -----------------------------------------------------
-- Table `PearlDiver`.`StaticPages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`StaticPages` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`StaticPages` (
  `PageId` INT NOT NULL AUTO_INCREMENT,
  `PageCategoryId` INT NULL,
  `NavName` VARCHAR(25) NOT NULL,
  `Title` VARCHAR(50) NOT NULL,
  `PublishDate` DATE NOT NULL,
  `TextArea` LONGTEXT NOT NULL,
  `Published` TINYINT(1) NOT NULL DEFAULT 0,
  `Completed` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`PageId`),
  UNIQUE INDEX `Title_UNIQUE` (`Title` ASC),
  UNIQUE INDEX `PageId_UNIQUE` (`PageId` ASC),
  CONSTRAINT `fk_PageCategoryId`
    FOREIGN KEY (`PageCategoryId`)
    REFERENCES `PearlDiver`.`PageCategories` (`PageCategoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`PagesUsers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`PagesUsers` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`PagesUsers` (
  `PageId` INT NOT NULL,
  `UserId` INT NOT NULL,
  PRIMARY KEY (`PageId`, `UserId`),
  INDEX `fk_User_Id_idx` (`UserId` ASC),
  CONSTRAINT `fk_PageId`
    FOREIGN KEY (`PageId`)
    REFERENCES `PearlDiver`.`StaticPages` (`PageId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Id`
    FOREIGN KEY (`UserId`)
    REFERENCES `PearlDiver`.`Users` (`UserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`Tags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`Tags` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`Tags` (
  `TagId` INT NOT NULL AUTO_INCREMENT,
  `Tag` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`TagId`),
  UNIQUE INDEX `Tag_UNIQUE` (`Tag` ASC),
  UNIQUE INDEX `TagId_UNIQUE` (`TagId` ASC));
-- -----------------------------------------------------
-- Table `PearlDiver`.`PostsTags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`PostsTags` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`PostsTags` (
  `PostId` INT NOT NULL,
  `TagId` INT NOT NULL,
  PRIMARY KEY (`PostId`, `TagId`),
  INDEX `fk_Post_TagId_idx` (`TagId` ASC),
  CONSTRAINT `fk_Tag_PostId`
    FOREIGN KEY (`PostId`)
    REFERENCES `PearlDiver`.`Posts` (`PostId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_TagId`
    FOREIGN KEY (`TagId`)
    REFERENCES `PearlDiver`.`Tags` (`TagId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`ImageTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`ImageTypes` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`ImageTypes` (
  `ImageTypeId` INT NOT NULL AUTO_INCREMENT,
  `ImageType` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`ImageTypeId`));
-- -----------------------------------------------------
-- Table `PearlDiver`.`Images`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`Images` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`Images` (
  `ImageId` INT NOT NULL AUTO_INCREMENT,
  `Path` VARCHAR(50) NOT NULL,
  `ImageTypeId` INT NOT NULL,
  PRIMARY KEY (`ImageId`),
  UNIQUE INDEX `ImageId_UNIQUE` (`ImageId` ASC),
  INDEX `fk_ImageTypeId_idx` (`ImageTypeId` ASC),
  CONSTRAINT `fk_ImageTypeId`
    FOREIGN KEY (`ImageTypeId`)
    REFERENCES `PearlDiver`.`ImageTypes` (`ImageTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`PostsImages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`PostsImages` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`PostsImages` (
  `PostId` INT NOT NULL,
  `ImageId` INT NOT NULL,
  PRIMARY KEY (`PostId`, `ImageId`),
  INDEX `fk_PostImgId_idx` (`ImageId` ASC),
  CONSTRAINT `fk_ImgPostId`
    FOREIGN KEY (`PostId`)
    REFERENCES `PearlDiver`.`Posts` (`PostId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PostImgId`
    FOREIGN KEY (`ImageId`)
    REFERENCES `PearlDiver`.`Images` (`ImageId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`PagesImages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`PagesImages` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`PagesImages` (
  `PageId` INT NOT NULL COMMENT '',
  `ImageId` INT NOT NULL,
  PRIMARY KEY (`PageId`, `ImageId`),
  INDEX `fk_PageImgId_idx` (`ImageId` ASC),
  CONSTRAINT `fk_ImgPageId`
    FOREIGN KEY (`PageId`)
    REFERENCES `PearlDiver`.`StaticPages` (`PageId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PageImgId`
    FOREIGN KEY (`ImageId`)
    REFERENCES `PearlDiver`.`Images` (`ImageId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `PearlDiver`.`Comments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PearlDiver`.`Comments` ;
CREATE TABLE IF NOT EXISTS `PearlDiver`.`Comments` (
 `CommentId` INT NOT NULL AUTO_INCREMENT,
 `PostId` INT NOT NULL,
 `Comment` TEXT NULL,
 `Name` TEXT NULL,
 PRIMARY KEY (`CommentId`),
 UNIQUE INDEX `CommentId_UNIQUE` (`CommentId` ASC),
 INDEX `fk_CommentsPostId_idx` (`PostId` ASC),
 CONSTRAINT `fk_CommentsPostId`
   FOREIGN KEY (`PostId`)
   REFERENCES `PearlDiver`.`Posts` (`PostId`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION);
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;