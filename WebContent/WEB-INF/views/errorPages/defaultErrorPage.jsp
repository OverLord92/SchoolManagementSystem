<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isErrorPage="true"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>access denied</title>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/teacher.js"/>"></script>
</head>
<body>
<jsp:include page="../../jspFragments/header.jsp" />
	
<div class="container">
	<center><h1>An internal Error occurred.</h1></center>
</div>

<jsp:include page="../../jspFragments/footer.jsp" />
</body>
</html>