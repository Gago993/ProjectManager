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
                      'ProjectData',
    function ($scope, $stateParams, $state, ProjectData) {
        console.log("Task Controller reporting for duty.");
        console.log($stateParams);

        
        if($stateParams.taskIndex == null){
        	$state.go("project",{projectId: $stateParams.projectId});
        }
        
        $scope.taskId = $stateParams.taskIndex;

        $scope.createSubtask = createSubtask;
        console.log($scope.task);
        
        function createSubtask(){
        	$scope.project.tasks[$scope.taskId].subtasks.unshift({});
        	ProjectData.update($scope.project,function(data){
        		console.log("create new subtask",data);
        		$scope.project.tasks = data.tasks;
        	});
        }
        
        
        
    }]);


