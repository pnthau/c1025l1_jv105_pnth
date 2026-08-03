<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách khách hàng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f4f4f4; }
        .btn-add { display: inline-block; padding: 10px 15px; background: #28a745; color: white; text-decoration: none; border-radius: 4px; }
        .btn-add:hover { background: #218838; }
        .action-link { color: #007bff; text-decoration: none; margin-right: 10px; }
        .action-link:hover { text-decoration: underline; }
        .text-center { text-align: center; }
    </style>
</head>
<body>

    <h2>Danh sách khách hàng</h2>
    
    <a href="${pageContext.request.contextPath}/customers/create" class="btn-add">+ Thêm khách hàng mới</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên khách hàng</th>
                <th>Email</th>
                <th>Địa chỉ</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customerList}">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.email}</td>
                    <td>${customer.address}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/customers/show/${customer.id}" class="action-link">Xem</a>
                    </td>
                </tr>
            </c:forEach>
            
            <c:if test="${empty customerList}">
                <tr>
                    <td colspan="5" class="text-center">Chưa có dữ liệu khách hàng</td>
                </tr>
            </c:if>

            <c:if test="${not empty msg}">
                <script>
                    confirm("${msg}");
                </script>
            </c:if>
        </tbody>
    </table>

</body>
</html>
