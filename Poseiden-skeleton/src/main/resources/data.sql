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
-- record 203 for testing
insert into bidlist(id, account,type, bidQuantity, askQuantity, bid, ask,
                    benchmark, bidListDate, commentary, security, status, trader, book,
                    creationName, creationDate, revisionName, revisionDate, dealName,
                    dealType, sourceListId, side)
    values(203,'testAcc','typeAcc','300','300','300','300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

insert into bidlist(id, account,type, bidQuantity, askQuantity, bid, ask,
                    benchmark, bidListDate, commentary, security, status, trader, book,
                    creationName, creationDate, revisionName, revisionDate, dealName,
                    dealType, sourceListId, side)
values(204,'testAcc','typeAcc','300','300','300','300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

insert into curvepoint(                            Id,
                                                   curveid,
                                                   asofdate,
                                                   term,
                                                   valuenumber,
                                                   creationdate)
    values(203,1,NULL,NULL,NULL,NULL);

insert into curvepoint(                            Id,
                                                   curveid,
                                                   asofdate,
                                                   term,
                                                   valuenumber,
                                                   creationdate)
values(204,2,NULL,NULL,NULL,NULL);

INSERT INTO Trade (TradeId, account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
VALUES (203, 'ACCT001', 'BUY', 100.00, 0.00, 10.00, 0.00, '2022-01-01 12:00:00', 'XYZ', 'OPEN', 'John Doe', 'BENCHMARK1', 'BOOK1', 'Jane Smith', '2022-01-01 12:00:00', 'Jane Smith', '2022-01-01 12:00:00', 'DEAL1', 'TYPE1', 'SOURCE1', 'SELL');

INSERT INTO Trade (TradeId, account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
VALUES (204, 'ACCT001', 'BUY', 100.00, 0.00, 10.00, 0.00, '2022-01-01 12:00:00', 'XYZ', 'OPEN', 'John Doe', 'BENCHMARK1', 'BOOK1', 'Jane Smith', '2022-01-01 12:00:00', 'Jane Smith', '2022-01-01 12:00:00', 'DEAL1', 'TYPE1', 'SOURCE1', 'SELL');

INSERT INTO Rating (id, moodysrating, sandprating, fitchrating, ordernumber)
VALUES (203, 'A1', 'AA', 'BBB', 1);

INSERT INTO Rating (id, moodysrating, sandprating, fitchrating, ordernumber)
VALUES (204, 'A1', 'AA', 'BBB', 1);

INSERT INTO RuleName (Id, name, description, json, template, sqlStr, sqlPart)
VALUES (203, 'Rule1', 'Description of Rule1', '{"key": "value"}', 'SELECT * FROM table WHERE condition', 'SELECT column FROM table', 'WHERE condition');

INSERT INTO RuleName (Id, name, description, json, template, sqlStr, sqlPart)
VALUES (204, 'Rule1', 'Description of Rule1', '{"key": "value"}', 'SELECT * FROM table WHERE condition', 'SELECT column FROM table', 'WHERE condition');

COMMIT;