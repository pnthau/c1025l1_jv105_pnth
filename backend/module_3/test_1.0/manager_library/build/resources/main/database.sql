CREATE DATABASE IF NOT EXISTS manager_library;
USE manager_library;

-- Tắt kiểm tra khóa ngoại để làm sạch dữ liệu cũ
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS membership_cards;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS students;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Tạo bảng books
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_code VARCHAR(125) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL UNIQUE,
    author VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Tạo bảng students
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(125) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    class VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Tạo bảng membership_cards
-- LƯU Ý: ĐÃ BỎ UNIQUE ĐƠN Ở book_id VÀ student_id (Vì nếu đặt UNIQUE đơn thì 1 học sinh chỉ được mượn 1 cuốn sách duy nhất trong đời!)
-- Chỉ giữ lại UNIQUE phức hợp (book_id, student_id) để ngăn 1 học sinh mượn lại CÙNG 1 cuốn sách 2 lần.
CREATE TABLE membership_cards (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    card_code VARCHAR(125) NOT NULL UNIQUE,
    book_id INT NOT NULL,
    student_id INT NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    start_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    end_date DATETIME,
    
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT unique_student_book UNIQUE (student_id, book_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Chèn 10 cuốn sách mẫu vào bảng books
INSERT INTO books (book_code, name, author, description, quantity) VALUES 
('B-0001', 'Lập Trình Java Căn Bản', 'Nguyễn Văn A', 'Sách giáo trình nhập môn Java Core cho người mới bắt đầu', 10),
('B-0002', 'Cấu Trúc Dữ Liệu & Giải Thuật', 'Trần Thị B', 'Hướng dẫn chi tiết về Data Structures & Algorithms', 5),
('B-0003', 'Thiết Kế Cơ Sở Dữ Liệu SQL', 'Lê Văn C', 'Kiến thức cốt lõi về Relational Database & SQL Queries', 0),
('B-0004', 'Spring Boot Thực Chiến', 'Phạm Văn D', 'Xây dựng RESTful API và Web Application với Spring Boot', 8),
('B-0005', 'Clean Code - Mã Sạch', 'Robert C. Martin', 'Nghệ thuật viết code sạch, tối ưu và dễ bảo trì trong dự án thực tế', 12),
('B-0006', 'Design Patterns trong Java', 'Erich Gamma', '23 Mẫu thiết kế phần mềm kinh điển ứng dụng trong Java OOP', 7),
('B-0007', 'Lập Trình Web Servlet & JSP', 'Hoàng Văn E', 'Hướng dẫn xây dựng ứng dụng Web Java Backend với Servlet & JSP', 15),
('B-0008', 'Microservices với Spring Cloud', 'Đỗ Thị F', 'Kiến trúc hệ thống phân tán, Gateway và Service Discovery', 3),
('B-0009', 'Lập Trình Hướng Đối Tượng Java', 'Vũ Văn G', 'Nắm vững 4 tính chất OOP: Đóng gói, Kế thừa, Đa hình, Trừu tượng', 9),
('B-0010', 'Tối Ưu Hiệu Năng MySQL Database', 'Bùi Văn H', 'Kỹ thuật Indexing, Query Optimization và Database Tuning nâng cao', 4);

-- Chèn 10 học sinh mẫu vào bảng students
INSERT INTO students (student_code, name, class) VALUES 
('S-0001', 'Nguyễn Văn Hậu', 'C1025L1'),
('S-0002', 'Trần Thị Mai', 'C1025L1'),
('S-0003', 'Lê Hoàng Nam', 'C1025L1'),
('S-0004', 'Phạm Quốc Bảo', 'C1025L2'),
('S-0005', 'Hoàng Thanh Tùng', 'C1025L2'),
('S-0006', 'Đỗ Kim Anh', 'C1025L2'),
('S-0007', 'Vũ Minh Trí', 'C1025L3'),
('S-0008', 'Bùi Quang Huy', 'C1025L3'),
('S-0009', 'Đặng Phương Thảo', 'C1025L3'),
('S-0010', 'Võ Tiến Dũng', 'C1025L3');
