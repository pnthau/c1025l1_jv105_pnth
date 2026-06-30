<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sản phẩm mới</title>
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
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #45a049;
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
    <h2>Thêm sản phẩm mới</h2>
    <form action="${pageContext.request.contextPath}/product/create" method="post">
        <div class="form-group">
            <label for="name">Tên sản phẩm:</label>
            <input type="text" id="name" name="name" required placeholder="Nhập tên sản phẩm">
        </div>
        <div class="form-group">
            <label for="price">Giá sản phẩm:</label>
            <input type="number" id="price" name="price" required min="0" placeholder="Nhập giá sản phẩm">
        </div>
        <button type="submit" class="btn">Thêm mới</button>
    </form>
    <a href="${pageContext.request.contextPath}/product/display" class="back-link">← Quay lại danh sách</a>
</div>

</body>
</html>
