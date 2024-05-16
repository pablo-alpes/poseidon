-- CREATION OF THE DATABASE
CREATE DATABASE IF NOT EXISTS test;
use test;

-- CREATION OF THE TABLES
CREATE TABLE IF NOT EXISTS BIDLIST (
  id integer PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity DOUBLE,
  askQuantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125)
);

CREATE TABLE CURVEPOINT (
                            Id integer PRIMARY KEY AUTO_INCREMENT,
                            curveid tinyint,
                            asofdate TIMESTAMP ,
                            term DOUBLE,
                            valuenumber DOUBLE,
                            creationdate TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Trade (
  TradeId integer AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE ,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (TradeId)
);


CREATE TABLE IF NOT EXISTS Rating (
  id integer PRIMARY KEY AUTO_INCREMENT,
  moodysrating VARCHAR(125),
  sandprating VARCHAR(125),
  fitchrating VARCHAR(125),
  ordernumber tinyint
);

CREATE TABLE IF NOT EXISTS RuleName (
  Id integer PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125)
);

CREATE TABLE IF NOT EXISTS Users (
  Id integer PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125)
);

insert into Users(fullname, username, password, role) values('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN');
insert into Users(fullname, username, password, role) values('User', 'user', '$2a$12$GMfjcJHr85d37Jzl0rMOXe1Us7PuJEWb.Kf3dBZQ7v0Taxr5KwHbS', 'USER');
insert into Users(fullname, username, password, role) values('User', 'test', '$2a$12$GMfjcJHr85d37Jzl0rMOXe1Us7PuJEWb.Kf3dBZQ7v0Taxr5KwHbS', 'USER');
-- test test


COMMIT;