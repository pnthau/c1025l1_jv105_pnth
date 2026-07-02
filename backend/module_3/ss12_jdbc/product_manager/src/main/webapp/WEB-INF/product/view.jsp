<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${product != null}">
        <h2>${product.getName()}</h2>
        <h3>${product.getPrice()}</h3>
    </c:when>
    <c:otherwise>
        <td>Không có tìm sản phẩm</td>
    </c:otherwise>
</c:choose>
</body>
</html>
