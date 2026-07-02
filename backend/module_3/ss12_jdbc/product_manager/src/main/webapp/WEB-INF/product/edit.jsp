<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chỉnh sửa sản phẩm</title>
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
                    <h3 class="card-title text-center text-primary mb-4 fw-bold">📝 Chỉnh sửa sản phẩm</h3>
                    
                    <form id="editProductForm" action="${pageContext.request.contextPath}/products/edit" method="post">
                        <!-- Input ẩn giữ lại ID sản phẩm -->
                        <input type="hidden" name="id" value="${product.getId()}">
                        
                        <div class="mb-3">
                            <label for="name" class="form-label fw-semibold">Tên sản phẩm:</label>
                            <input type="text" id="name" name="name" class="form-control" value="${product.getName()}" required placeholder="Nhập tên sản phẩm">
                        </div>
                        
                        <div class="mb-3">
                            <label for="price" class="form-label fw-semibold">Giá sản phẩm (USD):</label>
                            <div class="input-group">
                                <span class="input-group-text">$</span>
                                <input type="number" id="price" name="price" class="form-control" value="${product.getPrice()}" required min="0" placeholder="Nhập giá sản phẩm">
                            </div>
                        </div>
                        
                        <div class="mb-4">
                            <label for="category" class="form-label fw-semibold">Loại sản phẩm:</label>
                            <select id="category" name="category" class="form-select" required>
                                <option value="">-- Chọn loại sản phẩm --</option>
                                <c:forEach var="cat" items="${categoryList}">
                                    <option value="${cat.id}" ${cat.id == product.categoryId ? 'selected' : ''}>${cat.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary py-2 fw-bold shadow-sm">💾 Cập nhật</button>
                            <a href="${pageContext.request.contextPath}/products/display" class="btn btn-light py-2 text-muted border">← Quay lại danh sách</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal Xác nhận Cập nhật -->
<div class="modal fade" id="updateConfirmModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content shadow-lg border-0">
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title" id="updateModalLabel">📝 Xác nhận cập nhật</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body p-4 text-center">
        <p class="fs-5 mb-1">Bạn có chắc chắn muốn cập nhật sản phẩm?</p>
        <p class="text-primary fw-bold fs-4 mb-2">"${product.getName()}"</p>
        <p class="text-muted small mb-0">Hành động này sẽ lưu các thay đổi mới vào hệ thống.</p>
      </div>
      <div class="modal-footer bg-light border-0">
        <button type="button" class="btn btn-secondary px-4" data-bs-dismiss="modal">Hủy</button>
        <button type="button" class="btn btn-primary px-4" id="btnConfirmUpdate">Đồng ý</button>
      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const editForm = document.getElementById('editProductForm');
        const updateModal = new bootstrap.Modal(document.getElementById('updateConfirmModal'));
        const btnConfirmUpdate = document.getElementById('btnConfirmUpdate');

        editForm.addEventListener('submit', function(event) {
            event.preventDefault(); // Ngăn submit form mặc định
            updateModal.show(); // Hiển thị modal xác nhận
        });

        btnConfirmUpdate.addEventListener('click', function() {
            editForm.submit(); // Submit form trực tiếp (bỏ qua event listener)
        });
    });
</script>
</body>
</html>
