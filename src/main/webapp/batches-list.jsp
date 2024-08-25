<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Batches Management Application</title>
</head>
<body>

    <header>
        <nav>
            <div>
                <h1> Batches Management App </h1>
            </div>

            <ul>
                <li><a href="<%=request.getContextPath()%>/batches/list">Batches</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div>
        <div>
            <h3>List of Batches</h3>
            <hr>
            <div>
                <a href="<%=request.getContextPath()%>/batches/new">Add New Batch</a>
            </div>
            <br>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Schedule</th>
                        <th>Instructor</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="batch" items="${sessionScope.batchList}">
                        <tr>
                            <td><c:out value="${batch.batchId}" /></td>
                            <td><c:out value="${batch.batchName}" /></td>
                            <td><c:out value="${batch.schedule}" /></td>
                            <td><c:out value="${batch.instructor}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>/batches/edit?id=<c:out value='${batch.batchId}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="<%=request.getContextPath()%>/batches/delete?id=<c:out value='${batch.batchId}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>