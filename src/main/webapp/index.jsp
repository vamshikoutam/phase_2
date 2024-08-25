<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Gym Management System</title>
</head>
<body>

<h2>Welcome to Gym Management System</h2>

<ul>
    <li><a href="<%=request.getContextPath()%>/participants/list">Participants Management</a></li>
    <li><a href="<%=request.getContextPath()%>/batches/list">Batches Management</a></li>
</ul>

</body>
</html>