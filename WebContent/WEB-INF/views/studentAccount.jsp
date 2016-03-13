<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>student account</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/student.js"/>"></script>
</head>
<body>
	<jsp:include page="../jspFragments/header.jsp" />
	<div class="container">
		<h1>student</h1>
	</div>
	<div class="container">
		<div class="col-md-4">
			<h3>attending courses</h3>
			<c:forEach items="${attendingCourses}" var="currentCourse">
				<div>
					<a class="requestLink" href="#">${currentCourse.name}</a><br>
				</div>
			</c:forEach>
		</div>

		<div class="col-md-4">
			<h3>pending requests</h3>
			<c:forEach items="${pendingRequests}" var="currentCourseRequest">
				<div>
					<a class="requestLink" href="#">${currentCourseRequest.courseName}</a><br>
				</div>
			</c:forEach>
		</div>

		<div class="col-md-4">
			<h3>request course</h3>
			<c:forEach items="${allAvailableCourses}" var="currentCourse">
				<div>
					<c:url value="/requestCourse/${currentCourse.id}"
						var="requestCourseLink" />
					<a class="requestLink" href="${requestCourseLink}">${currentCourse.name}</a><br>
				</div>
			</c:forEach>
		</div>
		<jsp:include page="../jspFragments/footer.jsp" />
	</div>
</body>
</html>