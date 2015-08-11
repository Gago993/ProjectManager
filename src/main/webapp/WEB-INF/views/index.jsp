<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
<script type="text/javascript" src="app/scripts/angular.min.js"></script>
</head>
<body ng-app="">
	<center>
		<h2>Spring - ok</h2>
		<p>
			Web Model - ${message}
		</p>
		<p>
		Name: <input ng-model="name"><br />
        Hello {{name}}!<br /><br />
        
        AngularJS - ok
        </p>
	</center>
</body>
</html>