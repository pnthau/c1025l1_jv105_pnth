<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chỉnh sửa sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f9f9f9;
        }
        .form-container {
            max-width: 400px;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .btn {
            background-color: #0066cc;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #0052a3;
        }
        .back-link {
            display: inline-block;
            margin-top: 15px;
            color: #0066cc;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Chỉnh sửa sản phẩm</h2>
    <form action="${pageContext.request.contextPath}/product/edit" method="post">
        <!-- Input ẩn giữ lại ID sản phẩm cần update -->
        <input type="hidden" name="id" value="${product.getId()}">
        
        <div class="form-group">
            <label for="name">Tên sản phẩm:</label>
            <input type="text" id="name" name="name" value="${product.getName()}" required>
        </div>
        <div class="form-group">
            <label for="price">Giá sản phẩm:</label>
            <input type="number" id="price" name="price" value="${product.getPrice()}" required min="0">
        </div>
        <button type="submit" class="btn">Cập nhật</button>
    </form>
    <a href="${pageContext.request.contextPath}/product/display" class="back-link">← Quay lại danh sách</a>
</div>

</body>
</html>
