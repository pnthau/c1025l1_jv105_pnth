<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h2>Product List</h2>

<div style="margin-bottom: 15px; display: flex; justify-content: space-between; align-items: center;">
    <form action="${pageContext.request.contextPath}/product/search" method="get" style="margin: 0;">
        <input type="text" name="keyword" placeholder="enter product name" value="${param.keyword}">
        <button type="submit">Search</button>
    </form>
    
    <a href="${pageContext.request.contextPath}/product/create" style="display: inline-block; padding: 6px 12px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 4px; font-family: Arial, sans-serif; font-size: 14px;">+ Thêm sản phẩm mới</a>
</div>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/product/view?id=${product.getId()}">${product.getName()}</a>
            </td>
            <td>${product.getPrice()}</td>
            <td>
                <a href="${pageContext.request.contextPath}/product/edit?id=${product.getId()}" style="color: #0066cc; text-decoration: none; margin-right: 10px;">Sửa</a>
                <form action="${pageContext.request.contextPath}/product/remove" method="post" 
                      onsubmit="return confirm('Bạn có chắc chắn muốn xóa sản phẩm \'${product.getName()}\' này không?');" 
                      style="display: inline;">
                    <input type="hidden" name="id" value="${product.getId()}">
                    <button type="submit" style="background: none; border: none; color: #cc0000; text-decoration: underline; cursor: pointer; padding: 0; font-family: inherit; font-size: inherit;">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
