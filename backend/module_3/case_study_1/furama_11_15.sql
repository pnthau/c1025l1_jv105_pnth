-- Câu 11: Hợp đồng 3 tháng cuối 2020 nhưng chưa có 6 tháng đầu 2021

    SELECT
        con.id,
        e.employee_name,
        cus.customer_name,
        cus.phone,
        res.residence_name,
        SUM(COALESCE(cds.quantity, 0)) AS total_quantity,
        con.deposit
     FROM contracts con
     JOIN employees e ON con.employee_id = e.id
     JOIN customers cus ON con.customer_id = cus.id
     JOIN residence_renttype_link rl ON con.residence_renttype_link_id = rl.id
     JOIN residences res ON rl.residence_id = res.id
     LEFT JOIN contract_detail_other_service cds ON cds.contract_id = con.id
     WHERE (con.contract_start_date BETWEEN '2020-10-01' AND '2020-12-31')
     AND NOT EXISTS (
        SELECT 1
        FROM contracts con_check
        WHERE con_check.customer_id = con.customer_id 
        AND con_check.contract_start_date BETWEEN '2021-01-01' AND '2021-06-30' )
    GROUP BY con.id;
    
    
-- Câu 12: Dịch vụ đi kèm được sử dụng nhiều nhất

SELECT * FROM (
    SELECT
        os.id, os.service_name,
        SUM(cds.quantity) AS total_quantity,
        DENSE_RANK() OVER(ORDER BY total_quantity DESC) AS usage_rank
        FROM other_services os
        JOIN contract_detail_other_service cds ON os.id = cds.other_service_id
        GROUP BY os.id, os.service_name
     ) AS ranking_table
 WHERE usage_rank = 1; 
 
 
 -- Câu 13: Dịch vụ đi kèm chỉ mới được sử dụng 1 lần duy nhất

  SELECT
         con.id AS contract_id,
         rt.rent_type_name,
         os.service_name,
         COUNT(cds.other_service_id) AS usage_count
     FROM other_services os
     JOIN contract_detail_other_service cds ON os.id = cds.other_service_id
     JOIN contracts con ON cds.contract_id = con.id
     JOIN residence_renttype_link rl ON con.residence_renttype_link_id = rl.id
     JOIN rent_types rt ON rl.rent_type_id = rt.id
     GROUP BY
     os.id,
	 os.service_name,
     con.id,
     rt.rent_type_name
HAVING COUNT(cds.other_service_id) = 1;


-- Câu 14: Nhân viên lập tối đa 3 hợp đồng (2020-2021)
SELECT e.id, e.employee_name, el.education_level_name, d.department_name, e.phone, e.address
FROM employees e
JOIN education_levels el ON e.education_level_id = el.id
JOIN departments d ON e.department_id = d.id
JOIN contracts con ON e.id = con.employee_id
WHERE con.contract_start_date BETWEEN '2020-01-01' AND '2021-12-31'
GROUP BY e.id
HAVING COUNT(con.id) <= 3;


### Câu 15: Xóa Nhân viên chưa từng lập hợp đồng (2019-2021)
  DELETE e 
  FROM employees e
  WHERE NOT EXISTS (
    SELECT 1
    FROM contracts con
    WHERE con.employee_id = e.id 
       AND con.contract_start_date BETWEEN '2019-01-01' AND '2021-12-31'
   );
