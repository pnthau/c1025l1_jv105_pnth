<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Choose your sandwich condiments:</h2>
    <form action="${pageContext.request.contextPath}/ingredients" method="post">
        <label><input type="checkbox" name="condiment" value="Lettuce"> Lettuce</label><br>
        <label><input type="checkbox" name="condiment" value="Tomato"> Tomato</label><br>
        <label><input type="checkbox" name="condiment" value="Mustard"> Mustard</label><br>
        <label><input type="checkbox" name="condiment" value="Sprouts"> Sprouts</label><br>
        <br>
        <button type="submit">Save</button>
    </form>
</body>
</html>
