<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<body ng-app="projectManagerApp">
   <div class="container">
        <!--
                  ng-view is directive that declares that the element will be
                  place holder for the partial files included through the router
               -->
        <div ui-view></div>

    </div>

<!-- Project StyleSheet -->
<link rel="stylesheet" href="app/css/bootstrap.min.css"></link>


<!-- AngularJs scripts -->
<script src="app/components/angular.min.js"></script>
<script src="app/components/angular-route.min.js"></script>
<script src="app/components/angular-resource.min.js"></script>
<script src="app/components/angular-ui-router.min.js"></script>
<script src="app/components/angular-md5.min.js"></script>
<script src="app/components/ui-router-styles.js"></script>



<!-- The definition and the configuration of the application module -->
<script src="app/scripts/app.js"></script>
<script src="app/scripts/router.js"></script>


<!-- controllers -->
<script src="app/scripts/controllers/main.controller.js"></script>
<script src="app/scripts/controllers/login.controller.js"></script>
<script src="app/scripts/controllers/dashboard.controller.js"></script>


<!-- services -->
<script src="app/scripts/services/services.js"></script>
<script src="app/scripts/services/dashboard.data.js"></script>

</body>
</html>

