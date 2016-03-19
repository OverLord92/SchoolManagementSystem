<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/admin.css"/>" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login page</title>
</head>
<body>
	<jsp:include page="../jspFragments/header.jsp" />
	<div class="container">

		<h3>Login with Username and Password</h3>
		<br>
		<br>
		<form name='f' action='${pageContext.request.contextPath}/login'
			method='POST'>
			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='username' value=''
						class="form-control"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password'
						class="form-control" /></td>
				</tr>
				<tr>
					<td>Remember me:</td>
					<td><input type='checkbox' id="remember_me" name='remember-me' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Login" class="btn btn-default" /></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../jspFragments/footer.jsp" />
</body>
</html>