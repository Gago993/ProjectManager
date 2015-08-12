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

ProjecManagerApp.controller('LoginCtrl', ['CompanyData', '$scope',
                                          
    function (CompanyData, $scope) {
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
        	console.log("login",$scope.user);
        }
        
        function register(){
        	console.log("register",$scope.newUser);
        }
        
    }]);


