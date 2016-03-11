<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/admin.css"/>"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>home</title>
</head>
<body>
<jsp:include page="../jspFragments/header.jsp" />
	<div class="container">

		<sec:authorize access="!isAuthenticated()">
			<center><h1>Welcome to the School Management System</h1>
			<h2>Login to get access to data.</h2></center>
		</sec:authorize>

		<sec:authorize access="hasRole('ADMIN')">
			<center><h1>Welcome to the School Management System, Admin.</h1>
			<c:url value="/register" var="registerUrl" /> 
			<a href="${registerUrl}">Register new users</a><br>
			<c:url value="/adminPage" var="adminUrl" /> 
			<a href="${adminUrl}">Show course related tasks</a></center>
		</sec:authorize>
		
		<sec:authorize access="hasRole('TEACHER')">
			<center><h1>Welcome to the School Management System, Teacher.</h1>
			<c:url value="/teacherAccount" var="teacherUrl" /> 
			<a href="${teacherUrl}">Go to your account</a></center>
		</sec:authorize>
		
		<sec:authorize access="hasRole('STUDENT')">
			<center><h1>Welcome to the School Management System, Student.</h1>
			<c:url value="/studentAccount" var="studentUrl" /> 
			<a href="${studentUrl}">Go to your account</a></center>
		</sec:authorize>
	
	
	</div>
	<jsp:include page="../jspFragments/footer.jsp" />
</body>
</html>