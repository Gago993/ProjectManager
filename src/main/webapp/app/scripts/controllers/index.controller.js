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

ProjecManagerApp.controller('IndexCtrl', ['$http', '$scope', '$state',
    function ($http, $scope, $state) {
        console.log("Index Controller reporting for duty.");
       
        $scope.logout = logout;
        
        
        function logout() {
        	$http.get('logout').then(function(response) {
        	    // this callback will be called asynchronously
        	    // when the response is available
        		  console.log(response);
        		  $state.go('login');
        		  
        	  }, function(response) {
        	    // called asynchronously if an error occurs
        	    // or server returns response with an error status.
        		  console.log(status);
        		  alert("Something went wrong " + response);
        	  });
        }
        
        
    }]);


