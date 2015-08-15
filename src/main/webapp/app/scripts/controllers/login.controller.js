'use strict';

/**
 * @ngdoc Simple controller definition that have the $scope and firstService
 *        injected by angular. The $scope is the glue between the controller 
 *        and the view that displays the information. The controller is not 
 *        aware about the view that displays the information. 
 *        
 * @name avAngularStartupApp.controller:MainCtrl
 * @description # MainCtrl Controller of the avAngularStartupApp
 */

ProjecManagerApp.controller('LoginCtrl', ['$http', '$scope', '$state', 'md5',
                                          
    function ($http, $scope, $state, md5) {
        console.log("Login Controller reporting for duty.");
        
        $scope.showLogin = true;
        $scope.user = {};
        $scope.newUser = {};
        
        $scope.showRegister = showRegister;
        $scope.login = login;
        $scope.register = register;
        
        
        function showRegister(){
        	$scope.showLogin = !$scope.showLogin;
        }
        
        function login(){
        	$scope.user.password = md5.createHash($scope.user.password);
        	console.log("login",$scope.user);
        	
        	 var req = {
        		    method: 'POST',
        		    url: 'login',
        		    data: $scope.user,
        		    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        		    transformRequest: function(obj) {
        		      var str = [];
        		      for(var p in obj)
        		      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        		      return str.join("&");
        		    }
        		  }
        	$http(req).
        	  then(function(response) {
        	    // this callback will be called asynchronously
        	    // when the response is available
        		  console.log(response);
        		  $state.go('pm.main');
        		  
        	  }, function(response) {
        	    // called asynchronously if an error occurs
        	    // or server returns response with an error status.
        		  console.log(response);
        	  });
        }
        
        function register(){
        	$scope.newUser.password = md5.createHash($scope.newUser.password);
        	console.log("register",$scope.newUser);
        	
        	var req = {
        		    method: 'POST',
        		    url: 'register',
        		    data: $scope.newUser,
        		    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        		    transformRequest: function(obj) {
        		      var str = [];
        		      for(var p in obj)
        		      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        		      return str.join("&");
        		    }
        		  }
        	$http(req).
        	  then(function(response) {
        	    // this callback will be called asynchronously
        	    // when the response is available
        		  console.log(response);
        		  $state.go('pm.main');
        	  }, function(response) {
        	    // called asynchronously if an error occurs
        	    // or server returns response with an error status.
        		  console.log(response);
        	  });
        }
        
    }]);


