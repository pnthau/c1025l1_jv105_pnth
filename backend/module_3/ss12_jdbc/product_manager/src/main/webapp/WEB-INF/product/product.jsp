<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
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
            <h2 class="text-primary fw-bold mb-0">📦 Quản lý sản phẩm</h2>
            <p class="text-muted mb-0">Danh sách các sản phẩm đang bán trên hệ thống</p>
        </div>
        <div class="col-md-6 text-md-end mt-3 mt-md-0">
            <a href="${pageContext.request.contextPath}/categories/display" class="btn btn-outline-primary fw-bold px-4 py-2 shadow-sm me-2">
                📁 Danh sách loại sản phẩm
            </a>
            <a href="${pageContext.request.contextPath}/products/create" class="btn btn-success fw-bold px-4 py-2 shadow-sm">
                ➕ Thêm sản phẩm mới
            </a>
        </div>
    </div>

    <!-- Search Form Section -->
    <div class="card shadow-sm mb-4">
        <div class="card-body p-3">
            <form action="${pageContext.request.contextPath}/products/search" method="get" class="row g-2 align-items-center mb-0">
                <div class="col-md-5">
                    <input type="text" name="keyword" class="form-control" placeholder="Nhập tên sản phẩm cần tìm..." value="${param.keyword}">
                </div>
                <div class="col-md-4">
                    <select name="category" class="form-select">
                        <option value="0">-- Tất cả danh mục --</option>
                        <c:forEach var="cat" items="${categoryList}">
                            <option value="${cat.id}" ${param.category == cat.id ? 'selected' : ''}>${cat.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3 d-grid">
                    <button type="submit" class="btn btn-primary fw-bold">🔍 Tìm kiếm</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Data Table Section -->
    <div class="card shadow-sm">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-striped mb-0 align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th class="ps-4">Tên sản phẩm</th>
                            <th>Giá bán (USD)</th>
                            <th>Loại sản phẩm</th>
                            <th class="text-end pe-4">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty productList}">
                                <c:forEach var="product" items="${productList}">
                                    <tr>
                                        <td class="ps-4 fw-bold">
                                            <a href="${pageContext.request.contextPath}/products/view?id=${product.getId()}" class="text-decoration-none text-dark hover-primary">
                                                ${product.getName()}
                                            </a>
                                        </td>
                                        <td>$${product.getPrice()}</td>
                                        <td>
                                            <span class="badge bg-secondary px-2.5 py-1.5">
                                                <c:set var="hasCat" value="false" />
                                                <c:forEach var="cat" items="${categoryList}">
                                                    <c:if test="${cat.id == product.categoryId}">
                                                        ${cat.name}
                                                        <c:set var="hasCat" value="true" />
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${!hasCat}">
                                                    Danh mục #${product.categoryId}
                                                </c:if>
                                            </span>
                                        </td>
                                        <td class="text-end pe-4">
                                            <a href="${pageContext.request.contextPath}/products/edit?id=${product.getId()}" class="btn btn-outline-primary btn-sm me-1 fw-semibold">
                                                Sửa
                                            </a>
                                            <button type="button" class="btn btn-outline-danger btn-sm fw-semibold" onclick="confirmDelete('${product.getId()}', '${product.getName().replace('\'', '\\\'')}')">
                                                Xóa
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="4" class="text-center py-5 text-muted">
                                        <p class="mb-0 fs-5">Không tìm thấy sản phẩm nào phù hợp</p>
                                        <a href="${pageContext.request.contextPath}/products/display" class="btn btn-sm btn-link mt-2 text-decoration-none">Quay lại danh sách chính</a>
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

<!-- Modal Xác nhận Xóa -->
<div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content shadow-lg border-0">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="deleteModalLabel">⚠️ Xác nhận xóa sản phẩm</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body p-4 text-center">
        <p class="fs-5 mb-1">Bạn có chắc chắn muốn xóa sản phẩm này?</p>
        <p class="text-danger fw-bold fs-4 mb-2" id="deleteProductName"></p>
        <p class="text-muted small mb-0">Hành động này sẽ xóa vĩnh viễn sản phẩm khỏi hệ thống và không thể hoàn tác.</p>
      </div>
      <div class="modal-footer bg-light border-0">
        <button type="button" class="btn btn-secondary px-4" data-bs-dismiss="modal">Hủy</button>
        <button type="button" class="btn btn-danger px-4" onclick="executeDelete()">Đồng ý Xóa</button>
      </div>
    </div>
  </div>
</div>

<!-- Form Xóa Ẩn -->
<form id="globalDeleteForm" action="${pageContext.request.contextPath}/products/remove" method="post" style="display:none;">
    <input type="hidden" id="globalDeleteId" name="id">
</form>

<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    let productToDeleteId = null;

    function confirmDelete(id, name) {
        productToDeleteId = id;
        document.getElementById('deleteProductName').innerText = name;
        
        let deleteModal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
        deleteModal.show();
    }

    function executeDelete() {
        if (productToDeleteId) {
            document.getElementById('globalDeleteId').value = productToDeleteId;
            document.getElementById('globalDeleteForm').submit();
        }
    }

</script>
</body>
</html>
