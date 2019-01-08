create database blog_database encoding 'UTF8';
create user blog with encrypted password 'blogpass';
grant all privileges on database blog_database to blog;
