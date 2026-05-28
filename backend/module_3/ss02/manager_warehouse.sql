create database manager_warehouse;
use manager_warehouse;

create table materials(
material_id int auto_increment primary key,
marterial_name varchar(255)
);

create table export_receipts(
export_receipt_id int auto_increment primary key,
export_date date default current_timestamp check(export_date >= current_date())
);

create table material_export_receipt(
material_export_receipt_id int auto_increment primary key,
delivery_order varchar(255) not null unique,
quatity smallint unsigned default 0,
material_id int null,
export_receipt_id int not null,
foreign key(material_id) references materials(material_id) on delete restrict,
foreign key(export_receipt_id) references export_receipts(export_receipts_id) on delete restrict
);

create table import_receipts(
import_receipt_id int auto_increment primary key,
import_date date default current_timestamp check(import_date >= current_date())
);

create table marterial_import_receipit(
material_import_receipt_id int auto_increment primary key,
delivery_order varchar(255) not null unique,
material_id int null,
import_receipt_id int not null,
foreign key(material_id) references materials(material_id) on delete restrict,
foreign key(import_receipt_id) references import_receipts(import_receipt_id) on delete restrict
);

create table orders(
order_id int auto_increment primary key,
order_date date default current_timestamp check(import_date >= current_date())
);

create table material_order(
material_order_id int auto_increment primary key,
order_id int not null,
material_id int null,
foreign key(material_id) references materials(material_id) on delete restrict,
foreign key(import_receipt_id) references import_receipts(import_receipt_id) on delete restrict
);

create table suppliers(
supplier_id int auto_increment primary key,
supplier_name nvarchar(255) not null unique,
address nvarchar(255) not null
);

create table supplier_phone_numbers(
supplier_phone_numbers nvarchar(30) primary key,
supplier_id int not null,
foreign key(supplier_id) references suppliers(supplier_id) on delete cascade
);
