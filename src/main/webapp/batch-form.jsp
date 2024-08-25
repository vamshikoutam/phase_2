	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1" import="java.util.*,java.sql.*, util.BatchUtil"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
	<c:set var="batches" value="<%= BatchUtil.getBatches() %>" />
	<c:set var="categories" value="<%= BatchUtil.getCategories() %>" /> 
	
	<!DOCTYPE html>
	<html>
	<head>
	    <title>Add Batch</title>
	</head>
	<body>
	
	    <c:if test="${sessionScope.batch != null}">
	        <form action="<%=request.getContextPath()%>/batches/update" method="post">
	    </c:if>
	    <c:if test="${sessionScope.batch == null}">
	        <form action="<%=request.getContextPath()%>/batches/insert" method="post">
	    </c:if>
	
	    <caption>
	        <h2>
	            <c:if test="${sessionScope.batch != null}">
	                Edit Batch
	            </c:if>
	            <c:if test="${sessionScope.batch == null}">
	                Add New Batch
	            </c:if>
	        </h2>
	    </caption>
	
	    <c:if test="${sessionScope.batch != null}">
	        <input type="hidden" name="id" value="<c:out value='${batch.batchId}' />" />
	    </c:if>
	
	    <label for="batchName">Batch Name:</label>
	
	  
	<c:if test="${sessionScope.batch == null}">
	    <select name="categoryId" required>
	        <c:forEach var="category" items="${categories}">
	            <option value="${category.categoryId}">${category.batchName}</option>
	        </c:forEach>
	    </select><br>
	</c:if>
	
	<c:if test="${sessionScope.batch != null}">
	    <select name="categoryId" required>
	        <c:forEach var="category" items="${categories}">
	            <option value="${category.categoryId}"
	                <c:if test="${category.categoryId eq sessionScope.batch.categoryId}">
	                    selected
	                </c:if>
	            >${category.batchName}</option>
	        </c:forEach>
	    </select><br>
	</c:if>
	
	
	
	    <label for="schedule">Schedule:</label> 
	    <input type="text" name="schedule" value="<c:out value='${batch.schedule}' />" required><br>
	
	    <label for="instructor">Instructor:</label> 
	    <input type="text" name="instructor" value="<c:out value='${batch.instructor}' />" required><br>
	<c:if test="${sessionScope.batch != null}">
	    <input type="submit" value="Edit Batch">
	    </c:if>
	    	<c:if test="${sessionScope.batch == null}">
	    <input type="submit" value="Add Batch">
	    </c:if>
	    
	    </form>
	
	    
	</body>
	</html>