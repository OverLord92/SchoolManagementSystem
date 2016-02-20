<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/teacher.js"/>"></script>
</head>
<body>
<h1>ticr</h1>

<fieldset class="teacherCourses">
	<c:forEach items="${teachersCourses}" var="course">
		<div class="teacher">
			<c:url value="/getStudentsCourse/${course.id}" var="getStudentsLink"/>
			<a class="chooseCourseLink" href="${getStudentsLink}">${course.name}</a><br>
		</div>
	</c:forEach>
</fieldset>

<fieldset class="studentsOfCourse">
</fieldset>

</body>
</html>