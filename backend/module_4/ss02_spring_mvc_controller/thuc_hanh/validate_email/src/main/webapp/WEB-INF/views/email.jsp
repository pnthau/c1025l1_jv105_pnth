<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Validate Email</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; padding: 50px; background-color: #f4f7f6; }
        .container { max-width: 500px; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); margin: auto; }
        .error { color: #d9534f; margin-bottom: 15px; font-weight: bold;}
        .success { color: #5cb85c; margin-bottom: 15px; font-weight: bold;}
        input[type=text] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { background-color: #5cb85c; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px;}
        button:hover { background-color: #4cae4c; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Kiểm tra định dạng Email</h2>

        <!-- Hiển thị lỗi (Khi return thẳng file jsp, xài Model bình thường) -->
        <c:if test="${not empty errorMsg}">
            <div class="error">❌ ${errorMsg}</div>
        </c:if>

        <!-- Hiển thị thành công (Khi Redirect, xài FlashAttribute) -->
        <c:if test="${not empty msg}">
            <div class="success">✅ ${msg}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/form/email" method="post">
            <label for="email">Địa chỉ Email:</label>
            <!-- Mẹo giữ chữ khách đã gõ: value="${param.email}" -->
            <input type="text" id="email" name="email" value="${param.email}" placeholder="VD: john.doe@gmail.com" />
            
            <button type="submit">Kiểm tra ngay</button>
        </form>
    </div>
</body>
</html>
