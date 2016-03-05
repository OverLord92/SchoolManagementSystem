<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user registration</title>
</head>
<body>
<jsp:include page="../jspFragments/header.jsp" />
<h1>admin</h1>
<div class="container">
	<div class="col-md-4">
	<h3>student form:</h3>
	<c:url value="/registerStudent" var="saveStudent" />
	<form:form modelAttribute="user" action="${saveStudent}" method="POST">
		<fieldset>
		<div class="form-group">
			<label for="username">username</label>
			<form:input path="username"/>
		</div>		
		<div class="form-group">
			<label for="username">password</label>
			<form:input path="rawPassword"/>
		</div>
		<div class="form-group">
			<label for="username">first name</label>
			<form:input path="firstName"/>
		</div>
		<div class="form-group">
			<label for="username">last name</label>
			<form:input path="lastName"/>
		</div>
		<input type="submit" value="Add Student">
		</fieldset>
	</form:form>
	</div>
	
	<div class="col-md-4">
	<h3>teacher form:</h3>
	<c:url value="/registerTeacher" var="saveTeacher" />
	<form:form modelAttribute="user" action="${saveTeacher}">
		<fieldset>
		<div class="form-group">
			<label for="username">username</label>
			<form:input path="username"/>
		</div>		
		<div class="form-group">
			<label for="username">password</label>
			<form:input path="rawPassword"/>
		</div>
		<div class="form-group">
			<label for="username">first name</label>
			<form:input path="firstName"/>
		</div>
		<div class="form-group">
			<label for="username">last name</label>
			<form:input path="lastName"/>
		</div>
		<input type="submit" value="Add Teacher">
		</fieldset>
	</form:form>
	</div>

	<div class="col-md-4">
	<h3>admin form:</h3>
	<c:url value="/registerAdmin" var="saveAdmin" />
	<form:form modelAttribute="user" action="${saveAdmin}">
		<fieldset>
		<div class="form-group">
			<label for="username">username</label>
			<form:input path="username"/>
		</div>		
		<div class="form-group">
			<label for="username">password</label>
			<form:input path="rawPassword"/>
		</div>
		<div class="form-group">
			<label for="username">first name</label>
			<form:input path="firstName"/>
		</div>
		<div class="form-group">
			<label for="username">last name</label>
			<form:input path="lastName"/>
		</div>
		<input type="submit" value="Add admin">
		</fieldset>
	</form:form>
	</div>
<jsp:include page="../jspFragments/footer.jsp" />
</div>
</body>
</html>