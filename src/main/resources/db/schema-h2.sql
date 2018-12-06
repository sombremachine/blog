DROP TABLE posts IF EXISTS;

CREATE TABLE posts
(
  id          INTEGER auto_increment PRIMARY KEY,
  title       VARCHAR(255) NOT NULL,
  content     VARCHAR(255) NOT NULL
);