<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Participants Management Application</title>
</head>
<body>

    <header>
        <nav>
            <div>
                <h1> Participants Management App </h1>
            </div>

            <ul>
                <li><a href="<%=request.getContextPath()%>/participants/list">Participants</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div>
        <div>
            <h3>List of Participants</h3>
            <hr>
            <div>
                <a href="<%=request.getContextPath()%>/participants/new">Add New Participant</a>
            </div>
            <br>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Batch Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="participant" items="${sessionScope.participantList}">
                        <tr>
                            <td><c:out value="${participant.participantId}" /></td>
                            <td><c:out value="${participant.participantName}" /></td>
                            <td><c:out value="${participant.age}" /></td>
                            <td><c:out value="${participant.gender}" /></td>
                           <td><c:out value="${participant.batchName}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>/participants/edit?id=<c:out value='${participant.participantId}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="<%=request.getContextPath()%>/participants/delete?id=<c:out value='${participant.participantId}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>