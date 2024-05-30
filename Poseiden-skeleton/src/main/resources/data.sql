-- CREATION OF THE DATABASE
CREATE DATABASE IF NOT EXISTS test;
use test;

-- CREATION OF THE TABLES
CREATE TABLE IF NOT EXISTS bidlist (
  id integer PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity DOUBLE,
  ask_quantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bid_list_date TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125)
);

CREATE TABLE curvepoint (
                            id integer PRIMARY KEY AUTO_INCREMENT,
                            curve_id tinyint,
                            as_of_date TIMESTAMP ,
                            term DOUBLE,
                            value_number DOUBLE,
                            creation_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS trade (
  trade_id integer AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buy_quantity DOUBLE,
  sell_quantity DOUBLE,
  buy_price DOUBLE ,
  sell_price DOUBLE,
  trade_date TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (trade_id)
);


CREATE TABLE IF NOT EXISTS rating (
  id integer PRIMARY KEY AUTO_INCREMENT,
  moodys_rating VARCHAR(125),
  sp_rating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number tinyint
);

CREATE TABLE IF NOT EXISTS rulename (
  id integer PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlstr VARCHAR(125),
  sqlpart VARCHAR(125)
);

CREATE TABLE IF NOT EXISTS users (
  id integer PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  full_name VARCHAR(125),
  role VARCHAR(125)
);

insert into users(full_name, username, password, role) values('Administrator', 'admin', '$2a$12$GMfjcJHr85d37Jzl0rMOXe1Us7PuJEWb.Kf3dBZQ7v0Taxr5KwHbS', 'ADMIN');
insert into users(full_name, username, password, role) values('User', 'user', '$2a$12$GMfjcJHr85d37Jzl0rMOXe1Us7PuJEWb.Kf3dBZQ7v0Taxr5KwHbS', 'USER');
insert into users(full_name, username, password, role) values('User', 'test', '$2a$12$GMfjcJHr85d37Jzl0rMOXe1Us7PuJEWb.Kf3dBZQ7v0Taxr5KwHbS', 'USER');
-- record 203 for testing
insert into bidlist(id, account,type, bid_quantity, ask_quantity, bid, ask,
                    benchmark, bid_list_date, commentary, security, status, trader, book,
                    creation_name, creation_date, revision_name, revision_date, deal_name,
                    deal_type, source_list_id, side)
    values(203,'testAcc','typeAcc','300','300','300','300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

insert into bidlist(id, account,type, bid_quantity, ask_quantity, bid, ask,
                    benchmark, bid_list_date, commentary, security, status, trader, book,
                    creation_name, creation_date, revision_name, revision_date, deal_name,
                    deal_type, source_list_id, side)
values(204,'testAcc','typeAcc','300','300','300','300',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

insert into curvepoint(                            id,
                                                   curve_id,
                                                   as_of_date,
                                                   term,
                                                   value_number,
                                                   creation_date)
    values(203,1,NULL,NULL,NULL,NULL);

insert into curvepoint(                            id,
                                                   curve_id,
                                                   as_of_date,
                                                   term,
                                                   value_number,
                                                   creation_date)
values(204,2,NULL,NULL,NULL,NULL);

INSERT INTO trade (trade_id, account, type, buy_quantity, sell_quantity, buy_price, sell_price, trade_date, security, status, trader, benchmark, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side)
VALUES (203, 'ACCT001', 'BUY', 100.00, 0.00, 10.00, 0.00, '2022-01-01 12:00:00', 'XYZ', 'OPEN', 'John Doe', 'BENCHMARK1', 'BOOK1', 'Jane Smith', '2022-01-01 12:00:00', 'Jane Smith', '2022-01-01 12:00:00', 'DEAL1', 'TYPE1', 'SOURCE1', 'SELL');

INSERT INTO trade (trade_id, account, type, buy_quantity, sell_quantity, buy_price, sell_price, trade_date, security, status, trader, benchmark, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side)
VALUES (204, 'ACCT001', 'BUY', 100.00, 0.00, 10.00, 0.00, '2022-01-01 12:00:00', 'XYZ', 'OPEN', 'John Doe', 'BENCHMARK1', 'BOOK1', 'Jane Smith', '2022-01-01 12:00:00', 'Jane Smith', '2022-01-01 12:00:00', 'DEAL1', 'TYPE1', 'SOURCE1', 'SELL');

INSERT INTO rating (id, moodys_rating, sp_rating, fitch_rating, order_number)
VALUES (203, 'A1', 'AA', 'BBB', 1);

INSERT INTO rating (id, moodys_rating, sp_rating, fitch_rating, order_number)
VALUES (204, 'A1', 'AA', 'BBB', 1);

INSERT INTO rulename (id, name, description, json, template, sqlstr, sqlpart)
VALUES (203, 'Rule1', 'Description of Rule1', '{"key": "value"}', 'SELECT * FROM table WHERE condition', 'SELECT column FROM table', 'WHERE condition');

INSERT INTO rulename (id, name, description, json, template, sqlstr, sqlpart)
VALUES (204, 'Rule1', 'Description of Rule1', '{"key": "value"}', 'SELECT * FROM table WHERE condition', 'SELECT column FROM table', 'WHERE condition');

COMMIT;