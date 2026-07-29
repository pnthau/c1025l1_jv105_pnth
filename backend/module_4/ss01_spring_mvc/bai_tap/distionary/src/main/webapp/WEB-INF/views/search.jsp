<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <style>
        label{
            display : block;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/search" method="get">
    <label for="word">Enter your word </label>
    <input type="text" name="word" id="word">
    <button type="submit">Search</button>
</form>
<div class="result">
    <c:if test="${searched}">
        <c:choose>
            <c:when test="${not empty meaning}">
                <c:forEach items="${meaning}" var="m" varStatus="loop">
                    <p>"${m}"</p>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>Not found</p>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>
</body>
<script>
</script>
</html>
