<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,java.sql.*, util.BatchUtil"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:set var="batches" value="<%= BatchUtil.getBatches() %>" />
<!DOCTYPE html>
<html>
<head>
<title>Add Participant</title>
</head>
<body>

	<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/a_z_backend" user="root"
		password="root" />

	<c:if test="${sessionScope.participant != null}">
		<form action="<%=request.getContextPath()%>/participants/update" method="post">
	</c:if>
	<c:if test="${sessionScope.participant == null}">
		<form action="<%=request.getContextPath()%>/participants/insert" method="post">
	</c:if>

	<caption>
		<h2>
			<c:if test="${sessionScope.participant != null}">
                            Edit Participant
                        </c:if>
			<c:if test="${sessionScope.participant == null}">
                            Add New participant
                        </c:if>
		</h2>
	</caption>

	<c:if test="${sessionScope.participant != null}">
		<input type="hidden" name="id" value="<c:out value='${participant.participantId}' />" />
	</c:if>
	
	
		<label for="participantName">Participant Name:</label> <input
			type="text" name="participantName" value="<c:out value='${participant.participantName}' />" required><br> 
			
			<label for="age">Age:</label> 
			<input type="number" name="age" value="<c:out value='${participant.age}' />" required><br>

		<label for="gender">Gender:</label> <input type="text" name="gender"
			value="<c:out value='${participant.gender}' />" required><br> 
			
			<label for="batchId">Batch Name:</label> <select
			name="batchId" required>
			<c:if test="${sessionScope.participant == null}">
		<c:forEach var="batch" items="${batches}">
				<option value="${batch.batchId}">${batch.batchName}</option>
			</c:forEach>
	</c:if>
	
	<c:if test="${sessionScope.participant != null}">
    <c:forEach var="batch" items="${batches}">
        <option value="${batch.batchId}" 
            <c:if test="${batch.batchId eq sessionScope.participant.batchId}">
                selected
            </c:if>
        >${batch.batchName}</option>
    </c:forEach>
</c:if>
			
		</select><br> 
		<c:if test="${sessionScope.participant != null}">
		<input type="submit" value="Edit Participant">
		</c:if>
		<c:if test="${sessionScope.participant == null}">
		<input type="submit" value="Add Participant">
		</c:if>
		
	</form>



	<c:set var="batches" value="<%= BatchUtil.getBatches() %>" />

	

</body>
</html>