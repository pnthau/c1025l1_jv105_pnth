<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mượn Sách - Quản lý Thư viện</title>
    <!-- Local Bootstrap 5 CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body class="bg-light min-vh-100 py-5">

<div class="container" style="max-width: 650px;">
    <!-- Main Card -->
    <div class="card border-0 shadow-lg rounded-4 overflow-hidden bg-white">
        <!-- Card Header -->
        <div class="bg-primary text-white p-4 text-center">
            <h3 class="fw-bold mb-1">📖 Thẻ Mượn Sách</h3>
            <p class="mb-0 opacity-75 small">Điền đầy đủ thông tin để tạo phiếu mượn sách mới</p>
        </div>

        <!-- Form body -->
        <div class="card-body p-4 p-md-5">
            <form id="borrowForm" action="${pageContext.request.contextPath}/books/borrow" method="post">
                <!-- Mã mượn sách -->
                <div class="mb-3">
                    <label for="cardCode" class="form-label fw-semibold text-dark">Mã Mượn Sách <span class="text-danger">*</span></label>
                    <input type="text" id="cardCode" name="cardCode" class="form-control form-control-lg rounded-3"
                           value="${cardCode}" placeholder="Ví dụ: MS-0001" required>
                </div>

                <!-- Tên sách -->
                <div class="mb-3">
                    <label for="bookName" class="form-label fw-semibold text-dark">Tên Sách</label>
                    <input type="text" id="bookName" class="form-control form-control-lg rounded-3 bg-light" 
                           value="${book.name}" readonly>
                    <input type="hidden" name="bookId" value="${book.id}">
                </div>

                <!-- Tên học sinh -->
                <div class="mb-3">
                    <label for="studentId" class="form-label fw-semibold text-dark">Tên Học Sinh <span class="text-danger">*</span></label>
                    <select id="studentId" name="studentId" class="form-select form-select-lg rounded-3" required>
                        <option value="" disabled <c:if test="${empty selectedStudentId}">selected</c:if>>-- Chọn học sinh mượn sách --</option>
                        <c:forEach items="${studentList}" var="student">
                            <option value="${student.id}" <c:if test="${student.id == selectedStudentId}">selected</c:if>>${student.name} (${student.className})</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Ngày mượn sách -->
                <div class="mb-3">
                    <label for="borrowDate" class="form-label fw-semibold text-dark">Ngày Mượn Sách</label>
                    <input type="date" id="borrowDate" name="borrowDate" class="form-control form-control-lg rounded-3 bg-light" 
                           value="${today}" readonly>
                </div>

                <!-- Ngày trả sách -->
                <div class="mb-4">
                    <label for="returnDate" class="form-label fw-semibold text-dark">Ngày Trả Sách <span class="text-danger">*</span></label>
                    <input type="date" id="returnDate" name="returnDate" class="form-control form-control-lg rounded-3" 
                           value="${returnDateStr}" required>
                </div>

                <!-- Action Buttons -->
                <div class="d-flex gap-3 pt-2">
                    <a href="${pageContext.request.contextPath}/books" class="btn btn-outline-secondary btn-lg w-50 rounded-3">
                        Hủy Bỏ
                    </a>
                    <button type="submit" class="btn btn-primary btn-lg w-50 rounded-3 shadow-sm">
                        Xác Nhận Mượn Sách
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Local Bootstrap 5 JS -->
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>

<div class="modal fade" id="notificationModal" tabindex="-1" aria-labelledby="notificationModalLabel" aria-hidden="true" data-bs-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content rounded-4 border-0 shadow-lg">
            <div class="modal-header bg-warning text-dark border-0 rounded-top-4" id="notificationModalHeader">
                <h5 class="modal-title fw-bold" id="notificationModalLabel">⚠️ Thông Báo</h5>
            </div>
            <div class="modal-body p-4 text-center">
                <p class="fs-5 fw-semibold text-dark mb-0" id="notificationModalMessage"></p>
            </div>
            <div class="modal-footer border-0 justify-content-center pb-4">
                <button type="button" id="notificationModalBtn" class="btn btn-warning btn-lg px-4 rounded-3 fw-bold shadow-sm" data-bs-dismiss="modal">
                    OK
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.getElementById('borrowForm');
        if (form) {
            form.addEventListener('submit', async function(e) {
                e.preventDefault();
                
                const formData = new FormData(form);
                const params = new URLSearchParams(formData);
                
                try {
                    const response = await fetch(form.action, {
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: params.toString()
                    });
                    
                    const data = await response.json();
                    const modalEl = document.getElementById('notificationModal');
                    const modalHeader = document.getElementById('notificationModalHeader');
                    const modalLabel = document.getElementById('notificationModalLabel');
                    const modalMessage = document.getElementById('notificationModalMessage');
                    const modalBtn = document.getElementById('notificationModalBtn');
                    
                    if (data.success) {
                        modalHeader.className = 'modal-header bg-success text-white border-0 rounded-top-4';
                        modalLabel.innerText = '✅ Thành Công';
                        modalMessage.innerText = 'Mượn sách thành công!';
                        modalBtn.className = 'btn btn-success btn-lg px-4 rounded-3 fw-bold shadow-sm';
                        
                        modalEl.addEventListener('hidden.bs.modal', function () {
                            window.location.href = '${pageContext.request.contextPath}/books';
                        }, { once: true });
                        
                        var modal = new bootstrap.Modal(modalEl);
                        modal.show();
                    } else {
                        modalHeader.className = 'modal-header bg-warning text-dark border-0 rounded-top-4';
                        modalLabel.innerText = data.errorTitle || '⚠️ Thông Báo Lỗi';
                        modalMessage.innerText = data.errorMessage || 'Có lỗi xảy ra. Vui lòng kiểm tra lại.';
                        modalBtn.className = 'btn btn-warning btn-lg px-4 rounded-3 fw-bold shadow-sm';
                        
                        var modal = new bootstrap.Modal(modalEl);
                        modal.show();
                    }
                } catch (error) {
                    console.error('Error:', error);
                    const modalEl = document.getElementById('notificationModal');
                    const modalHeader = document.getElementById('notificationModalHeader');
                    const modalLabel = document.getElementById('notificationModalLabel');
                    const modalMessage = document.getElementById('notificationModalMessage');
                    const modalBtn = document.getElementById('notificationModalBtn');
                    
                    modalHeader.className = 'modal-header bg-danger text-white border-0 rounded-top-4';
                    modalLabel.innerText = '⚠️ Lỗi Kết Nối';
                    modalMessage.innerText = 'Không thể kết nối đến máy chủ.';
                    modalBtn.className = 'btn btn-danger btn-lg px-4 rounded-3 fw-bold shadow-sm';
                    
                    var modal = new bootstrap.Modal(modalEl);
                    modal.show();
                }
            });
        }
    });
</script>
</body>
</html>
