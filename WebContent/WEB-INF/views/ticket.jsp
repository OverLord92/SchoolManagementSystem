<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$.get('getFavorites', function(data) {
			
			var list = $.parseJSON(data);
			alert(list.length);
			
		});
	});
</script>
</head>
<body>
	<h1>ticket</h1>
	<div id=1 style="background-color:red">neki div</div>
	<div id=2 style="background-color:green">neki div</div>
	<div id=3 style="background-color:blue">neki div</div>
	<div id=4 style="background-color:yellow">neki div</div>
	<div id=5 style="background-color:grey">neki div</div>
</body>
</html>