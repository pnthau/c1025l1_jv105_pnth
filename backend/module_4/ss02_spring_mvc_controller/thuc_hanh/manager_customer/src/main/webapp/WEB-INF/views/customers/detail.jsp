<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết khách hàng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .detail-box { border: 1px solid #ddd; padding: 20px; max-width: 400px; border-radius: 5px; background: #f9f9f9; }
        .detail-item { margin-bottom: 15px; }
        .detail-item strong { display: inline-block; width: 100px; }
        .btn-back { display: inline-block; margin-top: 15px; padding: 8px 15px; background: #6c757d; color: white; text-decoration: none; border-radius: 4px; }
        .btn-back:hover { background: #5a6268; }
    </style>
</head>
<body>

    <h2>Thông tin chi tiết</h2>
    
    <div class="detail-box">
        <div class="detail-item">
            <strong>ID:</strong> ${customer.id}
        </div>
        <div class="detail-item">
            <strong>Tên:</strong> ${customer.name}
        </div>
        <div class="detail-item">
            <strong>Email:</strong> ${customer.email}
        </div>
        <div class="detail-item">
            <strong>Địa chỉ:</strong> ${customer.address}
        </div>
        
        <a href="${pageContext.request.contextPath}/customers" class="btn-back">Quay lại danh sách</a>
    </div>

</body>
</html>
