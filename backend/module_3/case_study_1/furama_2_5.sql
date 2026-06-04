-- Câu 2: Lọc Khách hàng theo tuổi và địa chỉ Khách hàng 18-50 tuổi, ở Đà Nẵng hoặc Quảng Trị.
SELECT * FROM customers 
WHERE timestampdiff(year, dob, curdate()) BETWEEN 18 AND 50
AND (address LIKE '%Đà Nẵng' OR address LIKE '%Quảng Trị');

-- Câu 3: Đếm số lần đặt phòng (Diamond).Đếm số lần đặt phòng của khách Diamond, sắp xếp tăng dần.
SELECT c.id, c.customer_name, COUNT(ct.id) AS ordered_count
FROM customers cus
JOIN customer_types ct ON c.customer_type_id = ct.id
JOIN contracts con ON ct.customer_id = con.id
WHERE ct.customer_type_name = 'Diamond'
GROUP BY c.id, c.customer_name
ORDER BY ordered_count ASC;

-- Câu 4: Tính Tổng tiền Hợp đồng Hiển thị **ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien** 
-- (_Với tổng tiền được tính theo công thức như sau: Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet)_ 
-- cho tất cả các khách hàng đã từng đặt phòng. (**những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra**)
SELECT 
    cus.id as customer_id, 
    cus.customer_name  , 
    ct.customer_type_name  , 
    con.id as contract_id , 
    res.residence_name , 
    con.contract_start_date, 
    con.contract_end_date ,
    (COALESCE(rl.rent_price, 0) + SUM(COALESCE(cd.quantity * os.price, 0))) AS total_money
FROM customers cus
LEFT JOIN customer_types ct ON   cus.customer_type_id = ct.id
LEFT JOIN contracts con ON  con.customer_id = cus.id
LEFT JOIN residence_renttype_link rl ON con.residence_renttype_link_id = rl.id
LEFT JOIN residences res ON rl.residence_id = res.id
LEFT JOIN contract_detail_other_service cds ON cds.contract_id = con.id 
LEFT JOIN other_services os ON cds.other_service_id = os.id
GROUP BY  cus.id,  
cus.customer_name, 
ct.customer_type_name, 
con.id,
res.residence_name,
con.contract_start_date, 
con.contract_end_date;

-- Câu 5: Dịch vụ chưa từng được đặt (Quý 1/2021)
-- Hiển thị **ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu** của tất cả các loại dịch vụ 
-- chưa từng được khách hàng thực hiện đặt từ **quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3)**.
SELECT res.id, res.residence_name, res.usable_area, rl.rent_price, res.residence_type
   FROM residences res
   inner JOIN residence_renttype_link rl ON  rl.residence_id = res.id
   where not  exists(
       select 1 from contracts con
       where con.residence_renttype_link_id = rl.id and quarter (con.contract_start_date) = 1 and
   year(con.contract_start_date) = 2021
   )
