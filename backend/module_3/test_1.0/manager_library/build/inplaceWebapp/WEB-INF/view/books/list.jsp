<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Thư viện - Danh sách Sách</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
</head>
<body class="bg-light min-vh-100 py-4">

<div class="container">
    <div class="card border-0 shadow rounded-4 p-4 bg-white">
        <div class="table-responsive rounded-3 border">
            <table class="table table-hover table-striped align-middle mb-0">
                <thead class="table-primary">
                <tr>
                    <th class="text-center" style="width: 60px;">STT</th>
                    <th style="width: 110px;">Mã Sách</th>
                    <th>Tên Sách</th>
                    <th>Tác Giả</th>
                    <th class="text-center" style="width: 120px;">Số Lượng</th>
                    <th>Mô Tả</th>
                    <th class="text-center" style="width: 130px;">Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty books}">
                        <c:forEach items="${books}" var="book" varStatus="loop">
                            <tr>
                                <td class="text-center fw-bold text-secondary">${loop.index + 1}</td>
                                <td><span class="badge bg-secondary-subtle text-dark border">MS-${book.id}</span></td>
                                <td class="fw-semibold text-dark">${book.name}</td>
                                <td class="text-secondary">${book.author}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${book.quantity > 0}">
                                            <span class="badge bg-success rounded-pill px-3 py-2">${book.quantity} quyển</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-danger rounded-pill px-3 py-2">Hết sách</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="text-muted small">${book.description}</td>
                                <td class="text-center">
                                    <c:if test="${book.quantity > 0}">
                                        <a href="${pageContext.request.contextPath}/books/borrow?id=${book.id}"
                                           class="btn btn-success btn-sm px-3 rounded-2">
                                            Mượn
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7" class="text-center py-5 text-muted">
                                Chưa có dữ liệu sách nào trong thư viện.
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
