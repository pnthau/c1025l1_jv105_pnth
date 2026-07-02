-- -------------------------------------------------------------
-- SQL Script: Tạo dữ liệu mẫu cho Database product_management
-- Yêu cầu: 5 categories và 100 products tương ứng.
-- -------------------------------------------------------------

CREATE DATABASE IF NOT EXISTS product_management;
USE product_management;

-- Tạm thời tắt ràng buộc khóa ngoại để reset sạch dữ liệu cũ
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE products;
TRUNCATE TABLE categories;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Chèn dữ liệu mẫu cho bảng categories (5 loại)
INSERT INTO categories (id, name) VALUES
(1, 'Điện tử & Công nghệ'),
(2, 'Thời trang & Phụ kiện'),
(3, 'Gia dụng & Đời sống'),
(4, 'Sách & Văn phòng phẩm'),
(5, 'Sức khỏe & Sắc đẹp');

-- 2. Chèn dữ liệu mẫu cho bảng products (100 sản phẩm phân bố đều)
INSERT INTO products (name, price, category_id) VALUES
-- Category 1: Điện tử & Công nghệ (id = 1)
('iPhone 15 Pro Max 256GB', 1200.00, 1),
('Samsung Galaxy S24 Ultra', 1100.00, 1),
('MacBook Pro M3 Pro 14"', 2000.00, 1),
('Dell XPS 15 9530', 1800.00, 1),
('Asus ROG Strix G16 2024', 1500.00, 1),
('iPad Air 5 M1 64GB WiFi', 600.00, 1),
('Tai nghe AirPods Pro 2 USB-C', 250.00, 1),
('Tai nghe chụp tai Sony WH-1000XM5', 350.00, 1),
('Chuột không dây Logitech MX Master 3S', 100.00, 1),
('Bàn phím cơ không dây Keychron K8', 120.00, 1),
('Smart TV Samsung 4K QLED 65 inch', 800.00, 1),
('Loa Bluetooth di động JBL Charge 5', 150.00, 1),
('Đồng hồ thông minh Apple Watch Series 9', 400.00, 1),
('Cáp sạc nhanh Anker PowerLine III USB-C', 15.00, 1),
('Sạc dự phòng đa năng Shargeek 100W', 180.00, 1),
('Màn hình Dell UltraSharp U2723QE 27"', 450.00, 1),
('Webcam Livestream Logitech C922 Pro', 80.00, 1),
('Ổ cứng SSD Samsung 990 Pro PCIe 4.0 1TB', 110.00, 1),
('Router Wi-Fi 6 Gaming Asus RT-AX58U', 130.00, 1),
('Máy chơi game Sony PlayStation 5 Slim', 500.00, 1),

-- Category 2: Thời trang & Phụ kiện (id = 2)
('Áo thun nam Cotton Basic', 15.00, 2),
('Quần jean nam Slim Fit Co giãn', 30.00, 2),
('Váy nữ dáng xòe hoa nhí thanh lịch', 25.00, 2),
('Áo khoác hoodie nỉ bông Unisex', 35.00, 2),
('Giày Sneaker Nike Air Force 1 All White', 110.00, 2),
('Giày chạy bộ Adidas Ultraboost Light', 140.00, 2),
('Balo nam nữ thời trang chống nước', 40.00, 2),
('Ví da nam cầm tay dáng ngang cao cấp', 50.00, 2),
('Thắt lưng da bò nguyên tấm thời trang', 25.00, 2),
('Kính râm unisex chống tia UV400', 20.00, 2),
('Mũ lưỡi trai thêu chữ phong cách bụi bặm', 12.00, 2),
('Tất cổ trung thể thao (hộp 5 đôi)', 10.00, 2),
('Áo sơ mi nam công sở dài tay chống nhăn', 28.00, 2),
('Chân váy chữ A dáng dài công sở', 22.00, 2),
('Đồng hồ nam dây da Orient Bambino Gen 2', 180.00, 2),
('Dây chuyền bạc nữ đính đá lấp lánh', 35.00, 2),
('Giày cao gót nữ 7cm mũi nhọn da lì', 45.00, 2),
('Áo khoác gió nam 2 lớp chống mưa nhẹ', 55.00, 2),
('Quần tây nam dáng ống đứng lịch lãm', 32.00, 2),
('Túi xách nữ thời trang da vân nổi', 60.00, 2),

-- Category 3: Gia dụng & Đời sống (id = 3)
('Nồi chiên không dầu Lock&Lock 5.2L', 90.00, 3),
('Robot hút bụi lau nhà Roborock Q Revo', 450.00, 3),
('Máy lọc không khí thông minh Xiaomi 4 Pro', 150.00, 3),
('Quạt đứng điện tử Mitsubishi LV16-RV', 85.00, 3),
('Máy pha cà phê Espresso mini tự động', 120.00, 3),
('Bình giữ nhiệt Lock&Lock 500ml', 18.00, 3),
('Bộ nồi inox 3 đáy Sunhouse (3 chiếc)', 40.00, 3),
('Chảo chống dính cao cấp Tefal 24cm', 25.00, 3),
('Đèn bàn học chống cận thông minh Philips', 30.00, 3),
('Gối cao su non thiên nhiên chống mỏi cổ', 22.00, 3),
('Nệm cao su non bông ép gấp 3 Vạn Thành', 110.00, 3),
('Bàn là hơi nước đứng cầm tay Philips', 35.00, 3),
('Máy ép chậm hoa quả công suất lớn Elmich', 75.00, 3),
('Bộ cây lau nhà tự vắt thông minh xoay 360', 20.00, 3),
('Thảm lau chân silicon siêu thấm hút nước', 8.00, 3),
('Kệ để chén bát trên chậu rửa inox 2 tầng', 30.00, 3),
('Máy làm sữa hạt đa năng chống ồn Unie', 130.00, 3),
('Máy xay sinh tố cầm tay mini sạc USB', 25.00, 3),
('Tủ vải quần áo khung gỗ 3 buồng 8 ngăn', 18.00, 3),
('Bộ bát đĩa sứ viền kim sang trọng (24 món)', 65.00, 3),

-- Category 4: Sách & Văn phòng phẩm (id = 4)
('Sách Đắc Nhân Tâm (Bìa mềm)', 6.00, 4),
('Sách Nhà Giả Kim - Paulo Coelho', 5.00, 4),
('Sách Lược Sử Loài Người - Yuval Noah Harari', 12.00, 4),
('Sách Tư Duy Nhanh Và Chậm', 10.00, 4),
('Sách Người Giàu Có Nhất Thành Babylon', 5.50, 4),
('Hộp bút chì màu Colleen Nhật Bản 24 màu', 8.00, 4),
('Bút bi Thiên Long FO-03 (Hộp 20 cây)', 4.00, 4),
('Sổ tay da PU cao cấp size A5 có còng', 3.00, 4),
('Bút ký kim loại cao cấp khắc tên Parker', 50.00, 4),
('Máy tính khoa học Casio FX-580VN X', 28.00, 4),
('Giấy in Double A A4 70gsm (Ream 500 tờ)', 4.50, 4),
('Bút dạ quang highlight màu pastel (Set 6 màu)', 3.50, 4),
('Kéo văn phòng Deli lưỡi thép không gỉ', 2.00, 4),
('Bảng vẽ tự xóa thông minh LCD 8.5 inch', 5.00, 4),
('Băng keo hai mặt siêu dính Acrylic (Cuộn 5m)', 1.50, 4),
('Bìa lá đựng tài liệu A4 mỏng (Xấp 10 cái)', 2.50, 4),
('Bộ dụng cụ học tập Compa & thước kẻ Deli', 6.00, 4),
('Bút marker vẽ tranh chuyên nghiệp Touchliit 48 màu', 15.00, 4),
('Sách Dạy Con Làm Giàu (Tập 1)', 4.80, 4),
('Sách Cha Giàu Cha Nghèo', 5.20, 4),

-- Category 5: Sức khỏe & Sắc đẹp (id = 5)
('Nước tẩy trang Bioderma Sensibio H2O 500ml', 16.00, 5),
('Sữa rửa mặt tạo bọt La Roche-Posay Effaclar 400ml', 18.00, 5),
('Kem chống nắng bảo vệ tối ưu Anessa 60ml', 22.00, 5),
('Serum cấp ẩm hồi phục HA B5 The Ordinary 30ml', 12.00, 5),
('Son kem lì mịn môi 3CE Velvet Lip Tint', 14.00, 5),
('Mặt nạ giấy dưỡng da BNBG Jelly Mask (Hộp 10 miếng)', 9.00, 5),
('Nước hoa nữ Chanel Coco Mademoiselle EDP 100ml', 150.00, 5),
('Dầu gội ngăn rụng tóc Thick & Full Biotin & Collagen', 13.00, 5),
('Sữa tắm dưỡng ẩm da mịn màng Dove 500ml', 6.50, 5),
('Kem dưỡng ẩm sâu Klairs Rich Moist Soothing Cream', 15.00, 5),
('Tẩy tế bào chết body hạt cà phê Đắk Lắk Cocoon', 7.00, 5),
('Tẩy da chết hóa học BHA Obagi Clenziderm 2%', 32.00, 5),
('Bông tẩy trang 100% cotton tự nhiên Ipek 150 miếng', 2.50, 5),
('Nước hoa hồng Some By Mi AHA-BHA-PHA 30 Days Tonic', 11.00, 5),
('Xịt khoáng làm dịu da nhạy cảm Avene 300ml', 12.50, 5),
('Kem dưỡng mắt Estee Lauder Advanced Night Repair', 65.00, 5),
('Bàn chải điện thông minh Oral-B Vitality', 25.00, 5),
('Máy rửa mặt công nghệ Sonic Halio', 35.00, 5),
('Dầu dưỡng tóc phục hồi hư tổn Moroccanoil 100ml', 42.00, 5),
('Kem cải thiện quầng thâm mắt Meishoku Whitening', 9.50, 5);
