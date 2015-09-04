<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ProjectManager</title>

<!-- Project StyleSheet -->
<link rel="stylesheet" href="app/css/bootstrap.min.css"></link>
<link rel="stylesheet" href="app/css/navigation.css"></link>

<!-- jQuery proper, Angular bootstraps it automatically -->
<script src="app/components/jquery.min.js"></script>

<!-- AngularJs scripts -->
<script src="app/components/angular.min.js"></script>
<script src="app/components/angular-route.min.js"></script>
<script src="app/components/angular-resource.min.js"></script>
<script src="app/components/angular-ui-router.min.js"></script>
<script src="app/components/angular-md5.min.js"></script>
<script src="app/components/ui-router-styles.js"></script>
<script src="app/components/ui-bootstrap-tpls-0.13.3.min.js"></script>
<script src="app/components/angucomplete-alt.js"></script>
<script src="app/components/angular-file-upload.min.js"></script>
<script src="app/components/ace/ace.js"></script>
<script src="app/components/ui-ace.js"></script>


<!-- The definition and the configuration of the application module -->
<script src="app/scripts/app.js"></script>

<!-- services -->
<script src="app/scripts/services/authentication.js"></script>
<script src="app/scripts/services/project.data.js"></script>
<script src="app/scripts/services/member.data.js"></script>

<!-- directives -->
<script src="app/directives/tags.directive.js"></script>
<script src="app/directives/tags.input.directive.js"></script>

<!-- routes -->
<script src="app/scripts/router.js"></script>

<!-- controllers -->
<script src="app/scripts/controllers/navigation.controller.js"></script>
<script src="app/scripts/controllers/index.controller.js"></script>
<script src="app/scripts/controllers/member.controller.js"></script>
<script src="app/scripts/controllers/login.controller.js"></script>
<script src="app/scripts/controllers/dashboard.controller.js"></script>
<script src="app/scripts/controllers/project.controller.js"></script>
<script src="app/scripts/controllers/task.controller.js"></script>

<!-- popup controllers -->
<script src="app/scripts/controllers/popups/project.members.controller.js"></script>
<script src="app/scripts/controllers/popups/project.discussion.controller.js"></script>
<script src="app/scripts/controllers/popups/code.snippet.controller.js"></script>



</head>
<body ng-app="projectManagerApp">
	<div class="navbar navbar-inverse navbar-fixed-top pm-navbar-top" role="navigation" ng-controller="NavigationCtrl">	
		<div class="navbar-header">
	        <div class="navbar-header"><a class="navbar-brand" href="#/index">Project Manager</a></div>
		</div>
		
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav pull-left">
				<li data-ng-show="member"><a ui-sref="dashboard">Dashboard</a></li>
			</ul>
			
			<ul class="nav navbar-nav pull-right">
				<li data-ng-show="member">
					<a ui-sref="member({memberId: member.id})">
						<img class="pm-navbar-member-image" ng-src="{{member.picture + '.png'}}" />
					</a>
				</li>
				<li data-ng-show="member"><a ng-click="logout()">Logout</a></li>
				<li data-ng-show="!member"><a ui-sref="login">Login</a></li>
			</ul>
		</div>
	</div>
	
    <div class="container">
        <div ui-view></div>
    </div>
</body>
</html>