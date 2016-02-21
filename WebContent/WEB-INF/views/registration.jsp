<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>admin</h1>
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
	<input type="submit">
	</fieldset>
</form:form>

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
	<input type="submit">
	</fieldset>
</form:form>

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
	<input type="submit">
	</fieldset>
</form:form>
</body>
</html>