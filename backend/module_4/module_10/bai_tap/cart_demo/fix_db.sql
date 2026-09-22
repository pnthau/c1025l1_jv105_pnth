USE cart_demo;
ALTER TABLE order_detail DROP FOREIGN KEY order_detail_ibfk_1;
DROP TABLE IF EXISTS `order`;
ALTER TABLE order_detail ADD CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id);
