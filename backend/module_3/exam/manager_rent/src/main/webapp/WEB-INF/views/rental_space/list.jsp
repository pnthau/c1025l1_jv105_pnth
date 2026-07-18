<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Quản lý mặt bằng</title>
                <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
                <style>
                    .table th,
                    .table td {
                        vertical-align: middle;
                        text-align: center;
                    }

                    .header-title {
                        background-color: #1a73e8;
                        color: white;
                        padding: 10px 0;
                        margin-bottom: 20px;
                        text-transform: uppercase;
                        font-weight: bold;
                    }
                </style>
            </head>

            <body>
                <div class="container mt-4 border p-0 rounded bg-white shadow-sm" style="overflow: hidden;">
                    <!-- Header -->
                    <div class="header-title text-center">
                        <h4 class="m-0 fw-bold">QUẢN LÝ MẶT BẰNG</h4>
                    </div>

                    <div class="p-3">
                        <!-- Search Form -->
                        <form action="${pageContext.request.contextPath}/rental-spaces" method="get"
                            class="row align-items-end mb-4">
                            <div class="col-md-3">
                                <label class="form-label fw-bold">Loại mặt bằng</label>
                                <select name="type" class="form-select">
                                    <option value="">Tất cả</option>
                                    <option value="Văn phòng chia sẻ" ${searchType=='Văn phòng chia sẻ' ? 'selected'
                                        : '' }>Văn phòng chia sẻ</option>
                                    <option value="Văn phòng trọn gói" ${searchType=='Văn phòng trọn gói' ? 'selected'
                                        : '' }>Văn phòng trọn gói</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <label class="form-label fw-bold">Tầng</label>
                                <select name="floor" class="form-select">
                                    <option value="">Tất cả</option>
                                    <c:forEach var="i" begin="1" end="15">
                                        <option value="${i}" ${searchFloor==i ? 'selected' : '' }>${i}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-primary">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-search me-1" viewBox="0 0 16 16">
                                        <path
                                            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                                    </svg> Tìm kiếm
                                </button>
                            </div>
                            <div class="col-md-4 text-end">
                                <a href="${pageContext.request.contextPath}/rental-spaces?action=create"
                                    class="btn btn-success">Thêm mới mặt bằng</a>
                            </div>
                        </form>

                        <!-- Table -->
                        <table class="table table-bordered table-hover mt-3">
                            <thead class="table-light">
                                <tr>
                                    <th>STT</th>
                                    <th>Mã MB</th>
                                    <th>Diện tích</th>
                                    <th>Trạng thái</th>
                                    <th>Tầng</th>
                                    <th>Loại mặt bằng</th>
                                    <th>Giá cho thuê</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${rentalSpaces}" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${item.code}</td>
                                        <td>
                                            <fmt:formatNumber value="${item.area}" pattern="#,###.##" /> m&sup2;
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.status == 'Trống'}">
                                                    <span class="badge bg-success">${item.status}</span>
                                                </c:when>
                                                <c:when test="${item.status == 'Đầy đủ'}">
                                                    <span class="badge bg-primary">${item.status}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge bg-warning text-dark">${item.status}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${item.floor}</td>
                                        <td>${item.type}</td>
                                        <td>
                                            <fmt:formatNumber value="${item.price}" pattern="#,###" /> VNĐ
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-danger btn-sm"
                                                onclick="showDeleteModal('${item.code}')">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"
                                                    fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                    <path
                                                        d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z" />
                                                    <path
                                                        d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z" />
                                                </svg> Xóa
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty rentalSpaces}">
                                    <tr>
                                        <td colspan="8" class="text-center">Không có dữ liệu.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Success Modal -->
                <c:if test="${not empty param.successCode}">
                    <div class="modal fade" id="successModal" tabindex="-1" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content border-success">
                                <div class="modal-header bg-success text-white">
                                    <h5 class="modal-title">Thành công</h5>
                                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body text-center fs-5">
                                    Đã tạo thành công mã mặt bằng <br><strong
                                        class="text-success fs-4">${param.successCode}</strong>
                                </div>
                                <div class="modal-footer justify-content-center">
                                    <button type="button" class="btn btn-success" data-bs-dismiss="modal">Đóng</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <!-- Delete Modal -->
                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header bg-danger text-white">
                                <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn xóa mặt bằng với mã số <strong id="deleteCodeDisplay"
                                    class="text-danger"></strong> không?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                <button type="button" class="btn btn-danger" onclick="confirmDelete()">Yes</button>
                            </div>
                        </div>
                    </div>
                </div>

                <script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
                <script>
                    let successCode = '${param.successCode}';
                    if (successCode) {
                        document.addEventListener("DOMContentLoaded", function () {
                            var successModal = new bootstrap.Modal(document.getElementById('successModal'));
                            successModal.show();
                        });
                    }

                    let codeToDelete = '';
                    let deleteModalElement = document.getElementById('deleteModal');
                    let deleteModal = new bootstrap.Modal(deleteModalElement);

                    function showDeleteModal(code) {
                        codeToDelete = code;
                        document.getElementById('deleteCodeDisplay').innerText = code;
                        deleteModal.show();
                    }

                    async function confirmDelete() {
                        if (!codeToDelete) return;

                        try {
                            const response = await fetch(`${pageContext.request.contextPath}/rental-spaces?code=` + encodeURIComponent(codeToDelete), {
                                method: 'DELETE'
                            });
                            const result = await response.json();

                            if (result.success) {
                                window.location.reload();
                            } else {
                                alert("Xóa thất bại: " + result.message);
                                deleteModal.hide();
                            }
                        } catch (error) {
                            console.error("Lỗi khi xóa:", error);
                            alert("Đã xảy ra lỗi kết nối.");
                            deleteModal.hide();
                        }
                    }
                </script>
            </body>

            </html>