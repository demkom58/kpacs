<!-- index.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome To KPac resource</title>
</head>
<body>
<div class="nav-container">
    <span>Navigation: </span>
    <a href="${pageContext.request.contextPath}/kpacs" class="nav-button">Kpacs</a>
    <a href="${pageContext.request.contextPath}/sets" class="nav-button">Kpac Sets</a>
</div>
</body>
</html>