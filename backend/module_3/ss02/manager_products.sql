create database manager_products;
use manager_products;

create table customers(
id int auto_increment primary key,
first_name varchar(100) not null,
last_name varchar(100) not null,
phone varchar(50) not null unique,
email varchar(255) null unique,
dob date null 
);

create table employees(
id int auto_increment primary key,
first_name varchar(100) not null,
last_name varchar(100) not null,
phone varchar(50) not null unique,
email varchar(255) null unique,
dob date null 
);

create table exp_products(
id int auto_increment primary key,
exp_date date not null
);

create table products(
id int auto_increment primary key,
product_name varchar(100) not null,
prodcut_description text,
quantity int  default 0,
price decimal check(price > 0),
exp_product_id int not null,
foreign key(exp_product_id) references exp_products(id)
);

create table orders(id int auto_increment primary key,
id_number varchar(100)  not null,
customer_id int not null,
employee_id int not null,
total_money decimal,
foreign key(customer_id) references customers(id),
foreign key(employee_id) references employees(id)
);

create table order_details(id int auto_increment primary key,
product_id int null,
quantity int  not null,
price decimal not null check(price > 0),
order_id int not null,
foreign key(product_id) references products(id),
foreign key(order_id) references orders(id)
);


