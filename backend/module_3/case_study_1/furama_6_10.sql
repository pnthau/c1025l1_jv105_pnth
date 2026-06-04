-- Câu 6: Dịch vụ đặt năm 2020 nhưng KHÔNG đặt năm 2021
SELECT res.id, res.residence_name, res.usable_area,res.maximum_occupancy, rl.rent_price, res.residence_type
FROM residences res
JOIN residence_renttype_link rl ON  rl.residence_id = res.id 
 WHERE EXISTS (
  SELECT 1 FROM contracts con
  WHERE con.residence_renttype_link_id = rl.id
  AND YEAR(con.contract_start_date) = 2020)
  AND NOT EXISTS ( SELECT 1 FROM contracts con
  WHERE con.residence_renttype_link_id = rl.id
  AND YEAR(con.contract_start_date) = 2021
  );



-- Câu 7: Hiển thị Họ tên không trùng nhau (3 cách)
-- Cách 1: DISTINCT
SELECT DISTINCT customer_name FROM customers;

-- Cách 2: GROUP BY
SELECT customer_name FROM customers GROUP BY customer_name;

-- Cách 3: UNION
SELECT customer_name FROM customers UNION SELECT customer_name FROM customers;


-- Câu 8: Thống kê khách hàng theo tháng (2021)
SELECT
    MONTH(con.contract_start_date) AS month,
    COUNT(DISTINCT con.customer_id) AS total_customer
FROM contracts as con
WHERE con.contract_start_date between '2021-01-01' and '2021-12-31'
GROUP BY month(contract_start_date)
ORDER BY month;

-- Câu 9: Số lượng dịch vụ đi kèm theo từng Hợp đồng
SELECT con.id, con.contract_start_date, con.contract_end_date, con.deposit, SUM(COALESCE(cds.quantity, 0)) AS count_other_serivce
FROM contracts con
LEFT JOIN contract_detail_other_service cds ON  cds.contract_id = con.id
GROUP BY con.id;


-- Câu 10: Dịch vụ đi kèm của khách Diamond (Vinh/Quảng Ngãi)
SELECT os.service_name, os.unit, os.price
FROM other_services os
JOIN contract_detail_other_service cds ON cds.other_service_id = os.id
JOIN contracts con ON cds.contract_id = con.id
JOIN (
     SELECT cus.id, cus.address
        FROM customers cus
        JOIN customer_types ct ON cus.customer_type_id = ct.id
       WHERE ct.customer_type_name = 'Diamond'
) AS customer_diamond ON con.customer_id = customer_diamond.id
 WHERE (customer_diamond.address LIKE '%Vinh%'
       OR customer_diamond.address LIKE '%Quảng Ngãi%'); 