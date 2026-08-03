<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm khách hàng mới</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-container { border: 1px solid #ddd; padding: 20px; max-width: 400px; border-radius: 5px; background: #f9f9f9; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; font-weight: bold; margin-bottom: 5px; }
        .form-group input { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { padding: 10px 15px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .btn-submit:hover { background: #218838; }
        .btn-back { display: inline-block; margin-top: 15px; text-decoration: none; color: #007bff; }
        .btn-back:hover { text-decoration: underline; }
    </style>
</head>
<body>

    <h2>Thêm khách hàng mới</h2>

    <div class="form-container">
        <form action="${pageContext.request.contextPath}/customers/create" method="post">
            <div class="form-group">
                <label for="id">ID:</label>
                <input type="number" id="id" name="id" required>
            </div>
            
            <div class="form-group">
                <label for="name">Tên khách hàng:</label>
                <input type="text" id="name" name="name" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="address">Địa chỉ:</label>
                <input type="text" id="address" name="address" required>
            </div>
            
            <button type="submit" class="btn-submit">Lưu khách hàng</button>
        </form>
        
        <a href="${pageContext.request.contextPath}/customers" class="btn-back">Quay lại danh sách</a>
    </div>
    <c:if test="${not empty errorMsg}">
        <script>
            confirm("${errorMsg}");
        </script>
    </c:if>

</body>
</html>
