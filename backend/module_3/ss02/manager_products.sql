create database manager_products_db;
use manager_products_db;

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
product_description text,
quantity int  default 0,
price decimal(10,2) check(price > 0),
exp_product_id int not null,
foreign key(exp_product_id) references exp_products(id)
);

create table orders(id int auto_increment primary key,
id_number varchar(100)  not null,
customer_id int not null,
employee_id int not null,
create_at datetime,
total_money decimal(10,2),
foreign key(customer_id) references customers(id),
foreign key(employee_id) references employees(id)
);

create table order_details(id int auto_increment primary key,
product_id int null,
quantity int  not null,
price decimal(10,2) not null check(price > 0),
order_id int not null,
foreign key(product_id) references products(id),
foreign key(order_id) references orders(id)
);

-- thêm khách hàng
INSERT INTO customers (first_name, last_name, phone, email, dob)
VALUES
('Nguyen', 'Van A', '0901234567', 'vana@example.com', '1990-05-12'),
('Tran', 'Thi B', '0912345678', 'thib@example.com', '1992-08-20'),
('Le', 'Quang C', '0923456789', 'quangc@example.com', '1988-03-15');

-- Thêm nhân viên
INSERT INTO employees (first_name, last_name, phone, email, dob)
VALUES
('Pham', 'Minh', '0987654321', 'minh.pham@example.com', '1995-07-10'),
('Nguyen', 'Hoa', '0978123456', 'hoa.nguyen@example.com', '1990-02-25');

-- Thêm hạn sử dụng sản phẩm
INSERT INTO exp_products (exp_date)
VALUES
('2026-12-31'),
('2027-06-30');

-- Thêm sản phẩm
INSERT INTO products (product_name, product_description, quantity, price, exp_product_id)
VALUES
('Bánh quy', 'Bánh quy bơ giòn', 100, 25.50, 1),
('Sữa tươi', 'Sữa tươi nguyên chất', 50, 15.00, 2),
('Sữa Bò', 'Sữa bò nguyên chất', 50, 15.00, 2);

-- Thêm đơn hàng
INSERT INTO orders (id_number, customer_id, employee_id, total_money)
VALUES
('ORD001', 1, 1, 51.00),
('ORD002', 2, 2, 30.00),
('ORD003', 2, 1, 30.00),
('ORD003', 2, 1, 80.00);

-- Thêm chi tiết đơn hàng
INSERT INTO order_details (product_id, quantity, price, order_id)
VALUES
(1, 2, 25.50, 1),
(2, 2, 15.00, 2),
(3, 2, 15.00, 2);


-- -------------------------------------------------------------
-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select o.id, od.price, o.create_at from orders o
inner join order_details od on od.order_id = o.id;
-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select CONCAT(cus.first_name, ' ', cus.last_name ) AS full_name, p.product_name from customers cus
inner join orders o on o.customer_id = cus.id  
inner join order_details od on od.order_id = o.id
inner join products p on od.product_id = p.id
group by cus.id,p.product_name;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select CONCAT(cus.first_name, ' ', cus.last_name ) AS full_name from customers cus
where not exists(select 1 from customers cus2
inner join orders o on o.customer_id = cus.id
where o.customer_id = cus2.id 
);
-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn 
-- (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. 
-- Giá bán của từng loại được tính = odQTY*pPrice)

select o.id_number, o.create_at,sum(od.price * od.quantity) from orders o 
inner join order_details od on od.order_id = o.id
group by o.id