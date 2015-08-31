'use strict';

/**
 * @ngdoc Simple controller definition that have the $scope and firstService
 *        injected by angular. The $scope is the glue between the controller 
 *        and the view that displays the information. The controller is not 
 *        aware about the view that displays the information. 
 *        
 * @name avAngularStartupApp.controller:MainCtrl
 * @description # MainCtrl Controller
 */

ProjectManagerApp.controller('NavigationCtrl', ['$http', '$scope', '$state', '$rootScope', 'authentication',
    function ($http, $scope, $state, $rootScope, authentication) {
		$scope.member = null;
        
        $scope.logout = function(){
        	authentication.logout();
        };
        
		$rootScope.$on("authenticationInit", function(){
	    	$scope.member = authentication.getMember();
	    });
        
        $rootScope.$on("authenticationLogin", function(){
        	$scope.member = authentication.getMember();
        });
        
        $rootScope.$on("authenticationRegistration", function(){
        	$scope.member = authentication.getMember();
        });
        
        $rootScope.$on("authenticationLogout", function(){
        	$scope.member = authentication.getMember();
        	
        	$state.go('index');
        });
	}]);