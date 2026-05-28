create database furama;
use furama;

create table residences(
id int auto_increment primary key, 
resident_name varchar(50) not null,
usable_area double(10,2) unsigned not null check(usable_area  >= 0) default 0,
maximum_occupancy tinyint not null check(maximum_occupancy >= 0) default 0
);

create table rent_types(
id int auto_increment primary key, 
rent_type_name varchar(50) not null);


create table residence_renttype_link(
id int auto_increment primary key,
residence_id int not null,
rent_type int not null,
rent_price decimal unsigned not null check(rent_price > 0),
foreign key(residence_id) references residences(id) ON DELETE CASCADE,
foreign key(rent_type) references rent_types(id) ON DELETE CASCADE
);

create table villas(
id int primary key, 
room_standard varchar(100),
other_amenities_description varchar(255),
pool_area double(10, 2) unsigned,
number_of_floors smallint unsigned,
foreign key(id) references residences(id) on delete cascade
);

create table homes(
id int primary key, 
room_standard varchar(100),
other_amenities_description varchar(255),
number_of_floors smallint unsigned,
foreign key(id) references residences(id) on delete cascade
);

create table rooms(
id int primary key, 
complimentary_services varchar(255),
foreign key(id) references residences(id) on delete cascade
);

create table other_services(
id int auto_increment primary key, 
service_name varchar(50) not null
);


create table service_units(
id int auto_increment primary key, 
service_unit_name varchar(50) not null
);

create table other_service_service_unit_link(
id int auto_increment primary key, 
other_service_id int null,
service_unit_id int null,
price decimal(10,2) not null check(price > 0),
foreign key(other_service_id) references other_services(id) on delete set null,
foreign key(service_unit_id) references service_units(id) on delete set null
);

create table education_levels(
id int auto_increment primary key, 
education_level_name varchar(100) not null unique
);

create table roles(
id int auto_increment primary key, 
role_name varchar(100) not null unique
);

create table departments(
id int auto_increment primary key, 
department_name varchar(255) not null unique
);

create table employees(
id int auto_increment primary key, 
first_name varchar(100) not null,
last_name varchar(100) not null,
dob date not null check(year(dob) > 1900),
id_card varchar(20) not null unique,
phone varchar(20) not null unique,
email varchar(255) not null unique,
gender enum('male', 'female', 'other'),
education_level_id int not null, 
role_id int not null,
department_id int not null,
salary decimal unsigned not null check(salary > 0),
foreign key(role_id)references roles(id) on delete cascade,
foreign key(education_level_id)references education_levels(id) on delete cascade,
foreign key(department_id) references departments(id) on delete cascade
);


create table customer_types(
id int auto_increment primary key, 
customer_type_name varchar(100) not null default 'Member'
);

create table customers(
id int auto_increment primary key, 
first_name varchar(100) not null,
last_name varchar(100) not null,
id_card varchar(20) not null unique,
gender enum('male', 'female', 'other'),
phone varchar(20) not null unique,
email varchar(255) not null unique,
dob date not null check(year(dob) > 1900),
address varchar(255) not null,
customer_type_id int not null ,
foreign key(customer_type_id)references customer_types(id) on delete cascade
);

	
create table contracts(
id int auto_increment primary key, 
customer_id int not null,
residence_renttype_link_id int not null,
employee_id int not null,
total_money decimal(10,2) unsigned not null check(total_money >= 0) default 0,
deposit decimal(10,2) unsigned not null check(deposit > 0),
contract_number varchar(150) not null unique,
contract_start_date  datetime not null default current_timestamp,
contract_end_date datetime not null ,
foreign key(customer_id) references customers(id) on delete restrict,
foreign key(residence_renttype_link_id) references residence_renttype_link(id) on delete restrict,
foreign key(employee_id) references employees(id) on delete restrict,
check ( contract_end_date > contract_start_date)
);

create table contract_detail_other_service(
id int auto_increment primary key, 
contract_id int not null,
other_service_id int null,
price decimal(10,2) not null check(price > 0),
foreign key(contract_id) references contracts(id) on delete restrict,
foreign key(other_service_id) references other_services(id) on delete restrict
);


-- Query
-- 1.Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 ký tự