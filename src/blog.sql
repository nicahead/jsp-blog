CREATE TABLE maincategory(
	id int(50) NOT NULL AUTO_INCREMENT,
  name varchar(500) NOT NULL,
  PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;

CREATE TABLE subcategory(
	id int(50) NOT NULL AUTO_INCREMENT,
  name varchar(500) NOT NULL,
  main_id int(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (main_id) REFERENCES maincategory(id)
)DEFAULT CHARSET=utf8;

CREATE TABLE article(
  id int(50) NOT NULL AUTO_INCREMENT,
  title varchar(500) NOT NULL,
  subtitle varchar(500) DEFAULT NULL,
  md_content text,
  html_content text,
  createdate varchar(500) NOT NULL,
  sub_id int(20),
  main_id int(20) NOT NULL,
	top int(10) DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (sub_id) REFERENCES subcategory(id),
  FOREIGN KEY (main_id) REFERENCES maincategory(id)
)DEFAULT CHARSET=utf8;

CREATE TABLE user(
  id int(50) NOT NULL AUTO_INCREMENT,
	username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;