DROP DATABASE IF EXISTS manager_rent;
CREATE DATABASE manager_rent CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE manager_rent;

CREATE TABLE rental_space (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    area DOUBLE NOT NULL,
    status VARCHAR(50) NOT NULL,
    floor INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL
);

-- Insert some dummy data
INSERT INTO rental_space (code, area, status, floor, type, price) VALUES
('ABC-12-34', 50.5, 'Trống', 5, 'Văn phòng chia sẻ', 5000000),
('XYZ-99-88', 120.0, 'Hạ tầng', 12, 'Văn phòng trọn gói', 20000000),
('AAA-11-22', 25.0, 'Đầy đủ', 1, 'Văn phòng chia sẻ', 1500000);
