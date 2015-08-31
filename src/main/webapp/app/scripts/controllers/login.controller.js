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

ProjectManagerApp.controller('LoginCtrl', ['$http', '$scope', '$rootScope', '$state', 'md5', 'authentication',
    function ($http, $scope, $rootScope, $state, md5, authentication) {
		$scope.member = {};
		$scope.newMember = {};
		
		$scope.showLogin = true;
        
        $scope.showRegister = function(){
        	$scope.showLogin = !$scope.showLogin;
        };
        
        $scope.login = function(){
        	authentication.login($scope.member.email, md5.createHash($scope.member.password));
        };
        
        $scope.register = function(){
        	authentication.register($scope.newMember.email, md5.createHash($scope.newMember.password), $scope.newMember.firstname, $scope.newMember.lastname);
        };
        
        $rootScope.$on('authenticationLogin', function(){
        	if(authentication.getMember()){
        		$state.go('dashboard');
        	}else{
        		//operation failed
        	}
        });
        
        $rootScope.$on('authenticationRegister', function(){
        	if(authentication.getMember()){
        		$state.go('dashboard');
        	}else{
        		//operation failed
        	}
        });
    }]);