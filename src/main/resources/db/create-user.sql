DROP USER IF EXISTS 'petuser'@'localhost';
CREATE USER 'petuser'@'localhost' IDENTIFIED BY 'petuser123';
GRANT ALL PRIVILEGES ON petstoredb.* TO 'petuser'@'localhost';
FLUSH PRIVILEGES ;

DROP DATABASE IF EXISTS petstoredb;
CREATE DATABASE petstoredb;