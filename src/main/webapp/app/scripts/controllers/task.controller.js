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

ProjectManagerApp.controller('TaskCtrl', ['$scope', '$stateParams', '$state',
    function ($scope, $stateParams, $state) {
        console.log("Task Controller reporting for duty.");
        console.log($stateParams);

        
        if(!$stateParams.task){
        	$state.go("project",{projectId: $stateParams.projectId});
        }
        

        $scope.task = $stateParams.task;
        
        
        
    }]);


