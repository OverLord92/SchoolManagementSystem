<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>teacher account</title>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/teacher.js"/>"></script>
</head>
<body>
<jsp:include page="../jspFragments/header.jsp" />
<div class="container">
	<h1>ticher</h1>
</div>

<div class="container">
	<div class="col-md-4">
		click on a course name to choose a course
		<div class="teacherCourses">
			<ul>
			<c:forEach items="${teachersCourses}" var="course">
				<li>
				<div class="course" id="${course.id}">
					${course.name}
				</div>
				</li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="col-md-4">
		click on a student username to add grades of absences
		<div class="studentsOfCourse">
		</div>
	</div>
	<div class="col-md-4">
		<div class="absencesAndGradesDiv">
		</div>
	</div>
<jsp:include page="../jspFragments/footer.jsp" />
</div>
</body>
</html>