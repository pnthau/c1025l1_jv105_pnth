CREATE DATABASE IF NOT EXISTS cart_demo;
USE cart_demo;

CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    description TEXT,
    image VARCHAR(255)
);

INSERT INTO product (name, price, description, image) VALUES 
('iPhone 15 Pro Max', 1199.99, 'Flagship smartphone from Apple with A17 Pro chip and titanium body.', 'https://example.com/images/iphone15promax.jpg'),
('Samsung Galaxy S24 Ultra', 1299.99, 'Premium Android phone with Snapdragon 8 Gen 3 and AI features.', 'https://example.com/images/s24ultra.jpg'),
('MacBook Pro 16-inch M3 Max', 3499.00, 'Powerful laptop for professionals with M3 Max chip.', 'https://example.com/images/macbookpro16.jpg'),
('Dell XPS 15', 1899.50, 'High-performance Windows laptop with InfinityEdge display.', 'https://example.com/images/dellxps15.jpg'),
('Sony PlayStation 5', 499.99, 'Next-generation gaming console from Sony.', 'https://example.com/images/ps5.jpg'),
('Xbox Series X', 499.99, 'Microsoft flagship gaming console with 4K gaming.', 'https://example.com/images/xboxseriesx.jpg'),
('Nintendo Switch OLED', 349.99, 'Hybrid console with an OLED screen.', 'https://example.com/images/switcholed.jpg'),
('Sony WH-1000XM5', 398.00, 'Industry-leading noise-canceling wireless headphones.', 'https://example.com/images/sonywh1000xm5.jpg'),
('AirPods Pro (2nd Gen)', 249.00, 'Wireless earbuds from Apple with active noise cancellation.', 'https://example.com/images/airpodspro2.jpg'),
('iPad Pro 12.9-inch (M2)', 1099.00, 'Premium tablet from Apple with M2 chip and Mini-LED display.', 'https://example.com/images/ipadpro129.jpg'),
('Samsung Galaxy Tab S9 Ultra', 1199.99, 'Large Android tablet with AMOLED display and S-Pen.', 'https://example.com/images/tabs9ultra.jpg'),
('Apple Watch Ultra 2', 799.00, 'Rugged smartwatch from Apple for outdoor activities.', 'https://example.com/images/applewatchultra2.jpg'),
('Garmin Fenix 7 Pro', 899.99, 'Premium multisport GPS watch with solar charging.', 'https://example.com/images/fenix7pro.jpg'),
('Nvidia RTX 4090', 1599.00, 'Flagship graphics card for ultimate gaming performance.', 'https://example.com/images/rtx4090.jpg'),
('AMD Ryzen 9 7950X3D', 699.00, 'High-end desktop processor with 3D V-Cache technology.', 'https://example.com/images/ryzen9.jpg'),
('LG C3 OLED 55-inch TV', 1399.99, 'Premium OLED TV with excellent picture quality.', 'https://example.com/images/lgc3.jpg'),
('Samsung Odyssey G9', 1299.99, '49-inch curved ultrawide gaming monitor.', 'https://example.com/images/odysseyg9.jpg'),
('Logitech MX Master 3S', 99.99, 'Advanced wireless mouse for productivity.', 'https://example.com/images/mxmaster3s.jpg'),
('Keychron Q1 Pro', 199.00, 'Custom wireless mechanical keyboard.', 'https://example.com/images/keychronq1.jpg'),
('Dyson V15 Detect', 749.99, 'Powerful cordless vacuum cleaner with laser illumination.', 'https://example.com/images/dysonv15.jpg');
