<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/student.js"/>"></script>
</head>
<body>
<h1>stjudent</h1>


<h3>attending courses</h3>
<c:forEach items="${attendingCourses}" var="currentCourse">
	<div>
		<a class="requestLink" href="#">${currentCourse.name}</a><br>
	</div>
</c:forEach>


<h3>pending requests</h3>
<c:forEach items="${pendingRequests}" var="currentCourseRequest">
	<div>
		<a class="requestLink" href="#">${currentCourseRequest.id}</a><br>
	</div>
</c:forEach>

<h3>request course</h3>
<c:forEach items="${allAvailableCourses}" var="currentCourse">
	<div>
		<c:url value="/requestCourse/${currentCourse.id}" var="requestCourseLink"/>
		<a class="requestLink" href="${requestCourseLink}">${currentCourse.name}</a><br>
	</div>
</c:forEach>
</body>
</html>