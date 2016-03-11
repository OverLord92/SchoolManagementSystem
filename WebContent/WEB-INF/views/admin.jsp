<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>admin page</title>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/admin.js"/>"></script>
	
</head>
<body>
<jsp:include page="../jspFragments/header.jsp" />
	<div class="container">
		<h1>course related tasks</h1>
	</div>
	<div class="container">
		<div class="col-md-4">
		<h3>add course</h3>
		<c:url value="/addCourse" var="saveCourse"/>
			<form:form action="${saveCourse}" modelAttribute="course"  method="POST">
				<fieldset>
				<div class="form-group">
					<label for="name">name</label>
					<form:input path="name"/>
				</div>
			<div class="form-group">
				<label for="code">code</label>
				<form:input path="code"/>
			</div>
			<input type="submit" value="Add Course">
			</fieldset>
			</form:form>
		</div>
		<div class="col-md-4">
			<h3>assign course to teacher</h3>
			click on the teacher's username
			<fieldset>
				<c:forEach items="${allTeachers}" var="currentTeacher">
					<div class="teacher" id="${currentTeacher.id}">
						${currentTeacher.username}
					</div><br>
				</c:forEach>
			</fieldset>
		
			<fieldset class="availableCourses">		
			</fieldset>
		</div>
	<h3>approve course requests</h3>
	<fieldset>
		<c:forEach items="${allCourseRequests}" var="courseRequest">
			<div class="courseRequest">
				student username: ${courseRequest.studentUsername}<br>
				course name: ${courseRequest.courseName}<br>
				<a class="courseRequest" href="#" id="${courseRequest.id}">
					approve request</a>
			</div><br>
		</c:forEach>
	</fieldset>
<jsp:include page="../jspFragments/footer.jsp" />
</div>

</body>
</html>