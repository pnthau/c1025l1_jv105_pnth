<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thêm sản phẩm mới</title>
    <!-- Tích hợp Bootstrap 5 Offline -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
        }
        .card {
            border: none;
            border-radius: 15px;
        }
    </style>
</head>
<body class="d-flex align-items-center py-5">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-lg p-4">
                <div class="card-body">
                    <h3 class="card-title text-center text-primary mb-4 fw-bold">✨ Thêm sản phẩm mới</h3>
                    
                    <form action="${pageContext.request.contextPath}/products/create" method="post">
                        <div class="mb-3">
                            <label for="name" class="form-label fw-semibold">Tên sản phẩm:</label>
                            <input type="text" id="name" name="name" class="form-control" required placeholder="Nhập tên sản phẩm">
                        </div>
                        
                        <div class="mb-3">
                            <label for="price" class="form-label fw-semibold">Giá sản phẩm (USD):</label>
                            <div class="input-group">
                                <span class="input-group-text">$</span>
                                <input type="number" id="price" name="price" class="form-control" required min="0" placeholder="Nhập giá sản phẩm">
                            </div>
                        </div>
                        
                        <div class="mb-4">
                            <label for="category" class="form-label fw-semibold">Loại sản phẩm:</label>
                            <select id="category" name="category" class="form-select" required>
                                <option value="">-- Chọn loại sản phẩm --</option>
                                <c:forEach var="cat" items="${categoryList}">
                                    <option value="${cat.id}">${cat.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary py-2 fw-bold shadow-sm">➕ Thêm mới</button>
                            <a href="${pageContext.request.contextPath}/products/display" class="btn btn-light py-2 text-muted border">← Quay lại danh sách</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
