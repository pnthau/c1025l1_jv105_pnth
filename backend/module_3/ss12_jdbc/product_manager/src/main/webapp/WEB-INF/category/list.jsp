<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách loại sản phẩm</title>
    <!-- Tích hợp Bootstrap 5 Offline -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .card {
            border: none;
            border-radius: 12px;
        }

        .table-responsive {
            border-radius: 10px;
        }
    </style>
</head>
<body class="py-5">

<div class="container">
    <!-- Header Section -->
    <div class="row align-items-center mb-4">
        <div class="col-md-6">
            <h2 class="text-primary fw-bold mb-0">📁 Quản lý loại sản phẩm</h2>
            <p class="text-muted mb-0">Danh sách các danh mục hàng hóa trong hệ thống</p>
        </div>
        <div class="col-md-6 text-md-end mt-3 mt-md-0">
            <a href="${pageContext.request.contextPath}/products/display"
               class="btn btn-outline-primary fw-bold px-4 py-2 shadow-sm">
                📦 Quay lại danh sách sản phẩm
            </a>
        </div>
    </div>

    <!-- Data Table Section -->
    <div class="card shadow-sm">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-striped mb-0 align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th class="ps-4" style="width: 15%;">Mã loại</th>
                        <th>Tên loại sản phẩm</th>
                        <th class="text-end pe-4" style="width: 25%;">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${not empty categoryList}">
                            <c:forEach var="cat" items="${categoryList}">
                                <tr>
                                    <td class="ps-4 fw-semibold text-muted">#${cat.id}</td>
                                    <td class="fw-bold fs-5 text-dark">${cat.name}</td>
                                    <td class="text-end pe-4">
                                        <a href="${pageContext.request.contextPath}/products/search?keyword=&category=${cat.id}"
                                           class="btn btn-primary btn-sm px-3 fw-semibold shadow-sm">
                                            🔍 Xem sản phẩm
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="3" class="text-center py-5 text-muted">
                                    Không tìm thấy loại sản phẩm nào
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
