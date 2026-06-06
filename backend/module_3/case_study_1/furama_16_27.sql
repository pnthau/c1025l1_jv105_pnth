-- Câu 16: Nâng cấp khách hàng Platinum lên Diamond
UPDATE customers cus
SET cus.customer_type_id = (SELECT id FROM customer_types WHERE customer_type_name = 'Diamond')
WHERE cus.customer_type_id = (SELECT id FROM customer_types WHERE customer_type_name = 'Platinum')
AND exists (
    SELECT 1 
    FROM contracts con
    WHERE con.customer_id = cus.id and con.contract_start_date between '2020-01-01' and '2020-12-31'
    HAVING SUM(con.total_money) > 10000000
);


-- Câu 17: Xóa khách hàng có hợp đồng trước năm 2021
 DELETE cus FROM customers cus
 WHERE EXISTS (
 SELECT 1 FROM contracts con
 WHERE con.customer_id = cus.id AND con.contract_start_date < '2021-01-01')
    AND NOT EXISTS (
     SELECT 1 FROM contracts con
     WHERE con.customer_id = cus.id AND con.contract_start_date >= '2021-01-01' );
     
-- Câu 18: Cập nhật giá DV đi kèm dùng > 10 lần trong 2020
UPDATE other_services os
SET price = price * 2
WHERE exists (
    SELECT 1
    FROM contract_detail_other_service cds
    JOIN contracts con ON cds.contract_id = con.id
    WHERE cds.other_service_id = os.id and YEAR(con.contract_start_date) = 2020
    HAVING SUM(quantity) > 10
);


-- Câu 19: Hợp nhất danh sách Nhân viên và Khách hàng
SELECT id, employee_name, email, phone, dob, address FROM employees
UNION ALL
SELECT id, customer_name, email, phone, dob, address FROM customers;


-- Câu 20: Tạo View v_nhan_vien
CREATE VIEW v_nhan_vien AS
SELECT e.* FROM employees e
JOIN contracts ct ON e.id = ct.employee_id
WHERE e.address LIKE '%Hải Châu%'
AND ct.contract_start_date = '2019-12-12';


-- Câu 21: Cập nhật thông qua View
UPDATE v_nhan_vien SET address = 'Liên Chiểu';

-- Câu 22: Stored Procedure xóa khách hàng
DELIMITER //
CREATE PROCEDURE sp_xoa_khach_hang(IN p_ma_khach_hang INT)
BEGIN
    DELETE FROM customers WHERE id = p_ma_khach_hang;
END //
DELIMITER ;


-- Câu 23: Stored Procedure thêm mới hợp đồng
DELIMITER //
CREATE PROCEDURE sp_them_moi_hop_dong(
    IN p_customer_id INT, 
    IN p_link_id INT, 
    IN p_employee_id INT, 
    IN p_deposit DECIMAL, 
    IN p_number VARCHAR(150),
    IN p_start DATETIME,
    IN p_end DATETIME
)
BEGIN
    -- Logic kiểm tra tồn tại FK và PK ở đây
    INSERT INTO contracts(customer_id, residence_renttype_link_id, employee_id, deposit, contract_number, contract_start_date, contract_end_date)
    VALUES (p_customer_id, p_link_id, p_employee_id, p_deposit, p_number, p_start, p_end);
END //
DELIMITER ;



-- Câu 24: Trigger khi xóa Hợp đồng
DELIMITER //
CREATE TRIGGER tr_delete_contract
AFTER DELETE ON contracts
FOR EACH ROW
BEGIN
    DECLARE total INT;
    DECLARE msg_text VARCHAR(255); 
    
    SELECT COUNT(*) INTO total FROM contracts;
    set msg_text = CONCAT('count contract :  ', total);
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = msg_text;
END //
DELIMITER ;


-- Câu 25: Trigger khi cập nhật ngày kết thúc
DELIMITER //
CREATE TRIGGER tr_update_contract
BEFORE UPDATE ON contracts
FOR EACH ROW
BEGIN
    IF DATEDIFF(NEW.contract_end_date, NEW.contract_start_date) < 2 THEN
        SIGNAL SQLSTATE '45000';
        SET MESSAGE_TEXT = 'contract end date larger then contract start date 2 days';
    END IF;
END //
DELIMITER ;


-- Câu 26: Function đếm dịch vụ và tính thời gian
-- a.count contract function.
DELIMITER //
CREATE FUNCTION func_count_contract() RETURNS INT DETERMINISTIC
BEGIN
    RETURN (SELECT COUNT(*) FROM contracts WHERE total_money > 2000000);
END //

-- b. calculate time contracts.
CREATE FUNCTION func_calculate_time_contract(p_customer_id INT) RETURNS INT DETERMINISTIC
BEGIN
    RETURN (SELECT MAX(DATEDIFF(contract_end_date, contract_start_date)) 
            FROM contracts WHERE customer_id = p_customer_id);
END //
DELIMITER ;


-- Câu 27: Stored Procedure xóa Room (Cascading Delete)
DELIMITER //
CREATE PROCEDURE sp_delete_contract_residence_room()
BEGIN
    CREATE TEMPORARY TABLE temp_ids(residence_id int) AS
    SELECT res.id FROM residences res
    WHERE res.residence_type = 'Room'
    AND res.id IN (
        SELECT rl.residence_id FROM contracts con
        JOIN residence_renttype_link rl ON con.residence_renttype_link_id = rl.id
        WHERE YEAR(con.contract_start_date) BETWEEN 2015 AND 2019
    );

    DELETE FROM contract_detail_other_service WHERE contract_id IN (
        SELECT id FROM contracts WHERE residence_renttype_link_id IN (
            SELECT id FROM residence_renttype_link WHERE residence_id IN (SELECT id FROM temp_ids)
        )
    );
    
    DELETE FROM contracts WHERE residence_renttype_link_id IN (
        SELECT id FROM residence_renttype_link WHERE residence_id IN (SELECT id FROM temp_ids)
    );
    
    DELETE FROM residences WHERE id IN (SELECT id FROM temp_ids);
    
    DROP TEMPORARY TABLE temp_ids;
END //
DELIMITER ;

