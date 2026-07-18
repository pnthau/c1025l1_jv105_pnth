<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Thư viện - Danh sách Thẻ Mượn Sách</title>
    <!-- Local Bootstrap 5 CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body class="bg-light min-vh-100 py-4">

<div class="container">
    <div class="card border-0 shadow rounded-4 p-4 bg-white">
        <!-- Header Page -->
        <div class="d-flex justify-content-between align-items-center mb-4 pb-2 border-bottom">
            <div>
                <h3 class="fw-bold text-primary mb-1">📋 Danh Sách Thẻ Mượn Sách</h3>
                <p class="text-secondary small mb-0">Theo dõi toàn bộ nhật ký mượn và trả sách trong thư viện</p>
            </div>
            <a href="${pageContext.request.contextPath}/books" class="btn btn-outline-secondary rounded-3 px-3">
                ← Quay lại Danh sách Sách
            </a>
        </div>

        <!-- Table List Cards -->
        <div class="table-responsive rounded-3 border">
            <table class="table table-hover table-striped align-middle mb-0">
                <thead class="table-primary">
                <tr>
                    <th class="text-center" style="width: 50px;">STT</th>
                    <th style="width: 120px;">Mã Thẻ</th>
                    <th>Tên Sách</th>
                    <th>Tác Giả</th>
                    <th>Học Sinh Mượn</th>
                    <th class="text-center" style="width: 100px;">Lớp</th>
                    <th class="text-center" style="width: 130px;">Ngày Mượn</th>
                    <th class="text-center" style="width: 130px;">Ngày Trả</th>
                    <th class="text-center" style="width: 120px;">Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty cards}">
                        <c:forEach items="${cards}" var="card" varStatus="loop">
                            <tr>
                                <td class="text-center fw-bold text-secondary">${loop.index + 1}</td>
                                <td>
                                    <span class="badge bg-primary-subtle text-primary border border-primary-subtle fw-semibold px-2 py-1">
                                        ${card.cardCode}
                                    </span>
                                </td>
                                <td class="fw-semibold text-dark">${card.bookName}</td>
                                <td class="text-secondary">${card.author}</td>
                                <td class="fw-medium text-dark">${card.studentName}</td>
                                <td class="text-center">
                                    <span class="badge bg-secondary-subtle text-dark border px-2 py-1">${card.className}</span>
                                </td>
                                <td class="text-center text-success fw-medium">
                                    <fmt:formatDate value="${card.startDate}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td class="text-center text-danger fw-medium">
                                    <fmt:formatDate value="${card.endDate}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/cards/return?id=${card.id}&bookId=${card.bookId}"
                                       class="btn btn-warning btn-sm px-3 rounded-2 shadow-sm fw-semibold"
                                       onclick="return confirm('Bạn có chắc chắn muốn trả sách này không?')">
                                        Trả Sách
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="9" class="text-center py-5 text-muted">
                                <div>📭 Chưa có dữ liệu thẻ mượn sách nào trong hệ thống.</div>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Local Bootstrap 5 JS -->
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
