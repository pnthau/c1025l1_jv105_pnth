create database manager_student;
use manager_student;

create table students(id int auto_increment primary key, 
name nvarchar(50) not null);

create table teachers(id int auto_increment primary key, 
name nvarchar(50) not null, 
dob date not null check(dob > '1-1-1900'),
country nvarchar(50) not null);
