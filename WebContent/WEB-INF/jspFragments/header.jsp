<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="navbar navbar-static-top">
	<div class="container">

		<p class="navbar-brand">School Management System</p>

		<button class="navbar-toggle" data-toggle="collapse"
			data-target=".navHeaderCollapse">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>

		<div class="collapse navbar-collapse navHeaderCollapse">
			<ul class="nav navbar-nav navbar-right">

				<li class="active"><c:url value="/home" var="homeUrl" /> <a
					href="${homeUrl}">Home</a></li>

				<sec:authorize access="hasRole('ADMIN')">
					<li><c:url value="/register" var="registerUrl" />
					<a href="${registerUrl}">Users</a></li>
					
					<li><c:url value="/adminPage" var="adminUrl" />
					<a href="${adminUrl}">Courses</a></li>
				</sec:authorize>

				<sec:authorize access="hasRole('TEACHER')">
					<li><c:url value="/teacherAccount" var="teacherUrl" />
					<a href="${teacherUrl}">Go to your account</a></li>
				</sec:authorize>

				<sec:authorize access="hasRole('STUDENT')">
					<li><c:url value="/studentAccount" var="studentUrl" />
					<a href="${studentUrl}">My Account</a></li>
				</sec:authorize>

				<sec:authorize access="!isAuthenticated()">
					<li><c:url value="/login" var="loginUrl" /> <a
						href="${loginUrl}">Login</a></li>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li><c:url value="/logout" var="logoutUrl" /> <a
						href="${logoutUrl}">Logout</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
	<hr>
</div>
