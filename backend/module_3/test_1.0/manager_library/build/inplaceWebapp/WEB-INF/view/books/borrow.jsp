<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="vi">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Mượn Sách - Quản lý Thư viện</title>

            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
        </head>

        <body class="bg-light min-vh-100 py-5">

            <div class="container" style="max-width: 650px;">

                <div class="card border-0 shadow-lg rounded-4 overflow-hidden bg-white">

                    <div class="bg-primary text-white p-4 text-center">
                        <h3 class="fw-bold mb-1">📖 Thẻ Mượn Sách</h3>
                        <p class="mb-0 opacity-75 small">Điền đầy đủ thông tin để tạo phiếu mượn sách mới</p>
                    </div>


                    <div class="card-body p-4 p-md-5">
                        <form id="borrowForm" action="${pageContext.request.contextPath}/books/borrow" method="post">

                            <div class="mb-3">
                                <label for="cardCode" class="form-label fw-semibold text-dark">Mã Mượn Sách <span
                                        class="text-danger">*</span></label>
                                <input type="text" id="cardCode" name="cardCode"
                                    class="form-control form-control-lg rounded-3" value="${cardCode}"
                                    placeholder="Ví dụ: MS-0001" pattern="MS-\d{4}"
                                    title="Mã mượn sách phải đúng định dạng MS-XXXX (X là 4 số nguyên dương)" required>
                            </div>


                            <div class="mb-3">
                                <label for="bookName" class="form-label fw-semibold text-dark">Tên Sách</label>
                                <input type="text" id="bookName" class="form-control form-control-lg rounded-3 bg-light"
                                    value="${book.name}" readonly>
                                <input type="hidden" name="bookId" value="${book.id}">
                            </div>


                            <div class="mb-3">
                                <label for="studentId" class="form-label fw-semibold text-dark">Tên Học Sinh <span
                                        class="text-danger">*</span></label>
                                <select id="studentId" name="studentId" class="form-select form-select-lg rounded-3"
                                    required>
                                    <option value="" disabled <c:if test="${empty selectedStudentId}">selected</c:if>>--
                                        Chọn học sinh mượn sách --</option>
                                    <c:forEach items="${studentList}" var="student">
                                        <option value="${student.id}" <c:if test="${student.id == selectedStudentId}">
                                            selected</c:if>>${student.name} (${student.className})</option>
                                    </c:forEach>
                                </select>
                            </div>


                            <div class="mb-3">
                                <label for="borrowDate" class="form-label fw-semibold text-dark">Ngày Mượn Sách</label>
                                <input type="date" id="borrowDate" name="borrowDate"
                                    class="form-control form-control-lg rounded-3 bg-light" value="${today}" readonly>
                            </div>


                            <div class="mb-4">
                                <label for="returnDate" class="form-label fw-semibold text-dark">Ngày Trả Sách <span
                                        class="text-danger">*</span></label>
                                <input type="date" id="returnDate" name="returnDate"
                                    class="form-control form-control-lg rounded-3" value="${returnDateStr}" required>
                            </div>


                            <div class="d-flex gap-3 pt-2">
                                <a href="${pageContext.request.contextPath}/books"
                                    class="btn btn-outline-secondary btn-lg w-50 rounded-3"
                                    onclick="return confirm('Bạn có chắc chắn muốn trở về danh sách không?')">
                                    Trở về danh sách
                                </a>
                                <button type="submit" class="btn btn-primary btn-lg w-50 rounded-3 shadow-sm">
                                    Xác Nhận Mượn Sách
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>


            <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>

            <div class="modal fade" id="borrowModal" tabindex="-1" aria-labelledby="borrowModalLabel" aria-hidden="true"
                data-bs-backdrop="static">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content rounded-4 border-0 shadow-lg">
                        <div class="modal-header bg-warning text-dark border-0 rounded-top-4">
                            <h5 class="modal-title fw-bold" id="borrowModalLabel">⚠️ Thông Báo Mượn Sách</h5>
                        </div>
                        <div class="modal-body p-4 text-center">
                            <p class="fs-5 fw-semibold text-dark mb-0" id="borrowModalMessage"></p>
                        </div>
                        <div class="modal-footer border-0 justify-content-center pb-4">
                            <button type="button" class="btn btn-warning btn-lg px-4 rounded-3 fw-bold shadow-sm"
                                data-bs-dismiss="modal">
                                OK
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    const form = document.getElementById('borrowForm');
                    
                    function showErrorModal(title, message) {
                        var modalEl = document.getElementById('borrowModal');
                        document.getElementById('borrowModalLabel').innerText = title;
                        document.getElementById('borrowModalMessage').innerText = message;
                        
                        const header = modalEl.querySelector('.modal-header');
                        const okBtn = modalEl.querySelector('.modal-footer button');
                        header.classList.remove('bg-success', 'text-white');
                        header.classList.add('bg-warning', 'text-dark');
                        okBtn.classList.remove('btn-success');
                        okBtn.classList.add('btn-warning');
                        
                        var modal = bootstrap.Modal.getInstance(modalEl) || new bootstrap.Modal(modalEl);
                        modal.show();
                    }

                    if (form) {
                        form.addEventListener('submit', async function (e) {
                            e.preventDefault();

                            const cardCodeInput = document.getElementById('cardCode');
                            if (!cardCodeInput.validity.valid) {
                                showErrorModal('⚠️ Lỗi Mã Mượn Sách', 'Mã mượn sách không được để trống và phải đúng định dạng MS-XXXX (ví dụ: MS-0001).');
                                return;
                            }

                            const returnDateInput = document.getElementById('returnDate');
                            if (!returnDateInput.validity.valid) {
                                showErrorModal('⚠️ Lỗi Ngày Mượn Trả', 'Ngày trả sách không được để trống.');
                                return;
                            }

                            if (!form.checkValidity()) {
                                form.reportValidity();
                                return;
                            }

                            const borrowDateStr = document.getElementById('borrowDate').value;
                            const returnDateStr = document.getElementById('returnDate').value;
                            if (borrowDateStr && returnDateStr) {
                                const bDate = new Date(borrowDateStr);
                                const rDate = new Date(returnDateStr);
                                if (rDate < bDate) {
                                    showErrorModal('⚠️ Lỗi Ngày Mượn Trả', 'Ngày trả sách không được phép trước ngày mượn sách!');
                                    return;
                                }
                            }

                            const formData = new FormData(form);
                            const params = new URLSearchParams(formData);

                            const response = await fetch(form.action, {
                                method: 'POST',
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/x-www-form-urlencoded'
                                },
                                body: params.toString()
                            });

                            const res = await response.json();

                            var modalEl = document.getElementById('borrowModal');
                            var modal = new bootstrap.Modal(modalEl);

                            const header = modalEl.querySelector('.modal-header');
                            const okBtn = modalEl.querySelector('.modal-footer button');

                            if (res.success) {
                                document.getElementById('borrowModalLabel').innerText = '✅ Thành Công';
                                document.getElementById('borrowModalMessage').innerText = 'Mượn sách thành công!';
                                header.classList.remove('bg-warning', 'text-dark');
                                header.classList.add('bg-success', 'text-white');
                                okBtn.classList.remove('btn-warning');
                                okBtn.classList.add('btn-success');
                            } else {
                                document.getElementById('borrowModalLabel').innerText = res.errorTitle || '⚠️ Thông Báo Lỗi';
                                document.getElementById('borrowModalMessage').innerText = res.errorMessage || 'Có lỗi xảy ra. Vui lòng kiểm tra lại.';
                                header.classList.remove('bg-success', 'text-white');
                                header.classList.add('bg-warning', 'text-dark');
                                okBtn.classList.remove('btn-success');
                                okBtn.classList.add('btn-warning');
                            }

                            modal.show();

                            modalEl.addEventListener('hidden.bs.modal', function onHidden() {
                                modalEl.removeEventListener('hidden.bs.modal', onHidden);
                                if (res.success) {
                                    window.location.href = '${pageContext.request.contextPath}/books';
                                }
                            });
                        });
                    }
                });
            </script>
        </body>

        </html>