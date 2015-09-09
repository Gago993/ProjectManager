
/**
 * @ngdoc here we are configuring the module exposed through the FirstApp
 *        variable. The method receives an array that has a function as a last
 *        argument. Here, the angular inject the dependencies defined as strings
 *        in the array to the corresponding elements in the function. <br/> The
 *        $routeProvider is used to configure the routes. It maps templateUrl
 *        and optionally a controller to a given path. This is used by the
 *        ng-view directive. It replaces the content of the defining element
 *        with the content of the templateUrl, and connects it to the controller
 *        through the $scope.
 * @see https://docs.angularjs.org/guide/di
 */
ProjectManagerApp.config([ '$stateProvider', '$urlRouterProvider',
  function($stateProvider, $urlRouterProvider) {
	
	$stateProvider

    .state('index', {
	  url: "/index",
      templateUrl: "app/views/index.html",
      controller: "IndexCtrl",
      data: {
		  css: ["app/css/index.css"]
	  }
    })
    
    .state('login', {
      url: "/login",
      templateUrl: "app/views/login.html",
	  controller: "LoginCtrl",
	  data: {
		  css: ["app/css/login.css"]
	  }
    })
    
    .state('member', {
      url: "/member/:memberId",
      templateUrl: "app/views/member.html",
      controller: "MemberCtrl",
      data: {
    	  css: ["app/css/member.css"]
      }
    })
    
	.state('dashboard', {
	  url: "/dashboard",
      templateUrl: "app/views/dashboard.html",
      controller: "DashboardCtrl",
      data: {
		  css: ["app/css/dashboard.css","app/css/index.css"]
	  }
    })
	
	.state('project', {
		  url: "/project/:projectId",
	      templateUrl: "app/views/project.html",
	      controller: "ProjectCtrl",
	      data: {
	    	  css: ["app/css/project.css","app/css/angucomplete-alt.css",
			        "app/css/index.css", "app/css/discussion.popup.css",
			        "app/css/members.popup.css"]
		  }  
	})
	
	.state('task', {
		  parent: 'project',
		  url: "/task",
	      templateUrl: "app/views/task.html",
	      controller: "TaskCtrl",
	      params: {taskIndex: null},
	      data: {
			  css: ["app/css/task.css"]
		  }  
	});
	
	
	$urlRouterProvider.when('', ['$state','$match', function ($state, $match) {
	      $state.go('index');
	}]).when('/', ['$state','$match', function ($state, $match) {
	      $state.go('index');
	}]);
	
	/*.state('/', {
		  url: "project/:projectId",
	      templateUrl: "app/views/project.html",
	      controller: "ProjectCtrl",
	      data: {
			  css: ["app/css/project.css","app/css/angucomplete-alt.css"]
		  }  
	})*/
	

} ])
.run(["$rootScope", "$state", "authentication", function($rootScope, $state, authentication) {
    // register listener to watch route changes
	$rootScope.$on('$stateChangeStart', 
		function(event, toState, toParams, fromState, fromParams){
			//TODO: fix this
			if(toState.name == 'dashboard' || toState.name == 'project'){
				/*if(authentication.getMember()){
					event.preventDefault();
					$state.go('login');
				}*/
			}else if(toState.name == 'login'){
				/*if(authentication.getMember()){
					event.preventDefault();
					$state.go('dashboard');
				}*/
			}
		});
 }]);