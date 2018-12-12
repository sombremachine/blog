DROP TABLE posts IF EXISTS;
DROP TABLE blog_seq IF EXISTS;

create sequence blog_seq;

CREATE TABLE posts
(
  id          INTEGER default blog_seq.nextval PRIMARY KEY,
  title       VARCHAR(255) NOT NULL,
  content     VARCHAR(255) NOT NULL
);