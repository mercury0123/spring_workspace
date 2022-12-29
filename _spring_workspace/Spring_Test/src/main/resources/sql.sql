create database springtest;
use springtest;
/*유저생성구문*/
create user springuser@localhost identified by 'mysql';
/*권한부여구문*/
grant all privileges on springtest.* to springuser@localhost identified by 'mysql';

flush privileges;

create table board (
bno int not null auto_increment,
title varchar(200),
content text,
writer varchar(100),
isDel varchar(50) default "N",
registerDate datetime default now(),
read_count int,
primary key(bno));

create table user (
id varchar(100) not null,
pw varchar(100) not null,
age int,
name varchar(100),
email varchar(100),
home varchar(100),
primary key(id));

create table comment(
cno int auto_increment,
bno int not null,
writer varchar(100) not null,
content text not null,
reg_at datetime default now(),
mod_at datetime default now(),
primary key(cno));

create table file (
uuid varchar(256),
save_dir varchar(256) not null,
file_name varchar(512) not null,
file_type tinyint(1) default 0,
bno int,
file_size int,
reg_at datetime default now(),
primary key(uuid));