<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user registration</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/register.js"/>"></script>
</head>
<body>
	<jsp:include page="../jspFragments/header.jsp" />
	<div class="container">
		<h1>admin</h1>
	</div>
	<div class="container">
		<div class="col-md-4">
			<h3>student form:</h3>
			<c:url value="/registerStudent" var="saveStudent" />
			<form:form modelAttribute="student" action="${saveStudent}"
				method="POST">
				<div>
					<div class="form-group">
						<label for="username">username</label>
						<form:input path="username" class="username" />
						<span class="usernameAvailability"></span>
						<form:errors class="help-block" path="username" />
					</div>
					<div class="form-group">
						<label for="rawPsssword">password</label>
						<form:input path="rawPassword" />
						<form:errors class="help-block" path="rawPassword" />
					</div>
					<div class="form-group">
						<label for="firstName">first name</label>
						<form:input path="firstName" />
						<form:errors class="help-block" path="firstName" />
					</div>
					<div class="form-group">
						<label for="lastName">last name</label>
						<form:input path="lastName" />
					</div>
					<input type="submit" value="Add Student">
				</div>
			</form:form>
		</div>

		<div class="col-md-4">
			<h3>teacher form:</h3>
			<c:url value="/registerTeacher" var="saveTeacher" />
			<form:form modelAttribute="teacher" action="${saveTeacher}">
				<div>
					<div class="form-group">
						<label for="username">username</label>
						<form:input path="username" class="username" />
						<span class="usernameAvailability"></span>
						<form:errors class="help-block" path="username" />
					</div>
					<div class="form-group">
						<label for="rawPassword">password</label>
						<form:input path="rawPassword" />
						<form:errors class="help-block" path="rawPassword" />
					</div>
					<div class="form-group">
						<label for="firstName">first name</label>
						<form:input path="firstName" />
						<form:errors class="help-block" path="firstName" />
					</div>
					<div class="form-group">
						<label for="lastName">last name</label>
						<form:input path="lastName" />
						<form:errors class="help-block" path="lastName" />
					</div>
					<input type="submit" value="Add Teacher">
				</div>
			</form:form>
		</div>

		<div class="col-md-4">
			<h3>admin form:</h3>
			<c:url value="/registerAdmin" var="saveAdmin" />
			<form:form modelAttribute="admin" action="${saveAdmin}">
				<div>
					<div class="form-group">
						<label for="username">username</label>
						<form:input path="username" class="username" />
						<span class="usernameAvailability"></span>
						<form:errors class="help-block" path="username" />

					</div>
					<div class="form-group">
						<label for="rawPassword">password</label>
						<form:input path="rawPassword" />
						<form:errors class="help-block" path="rawPassword" />
					</div>
					<div class="form-group">
						<label for="firstName">first name</label>
						<form:input path="firstName" />
						<form:errors class="help-block" path="firstName" />
					</div>
					<div class="form-group">
						<label for="lastName">last name</label>
						<form:input path="lastName" />
						<form:errors class="help-block" path="lastName" />
					</div>
					<input type="submit" value="Add admin">
				</div>
			</form:form>
		</div>
		<jsp:include page="../jspFragments/footer.jsp" />
	</div>
</body>
</html>