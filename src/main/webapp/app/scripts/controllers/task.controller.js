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

ProjectManagerApp.controller('TaskCtrl', ['$scope', '$stateParams', '$state', '$modal',
                      'ProjectData',
    function ($scope, $stateParams, $state, $modal, ProjectData) {
        if($stateParams.taskIndex == null){
        	$state.go("project",{projectId: $stateParams.projectId});
        }

        $scope.taskId = $stateParams.taskIndex;
        
        $scope.createSubtask = function(){
        	$scope.project.tasks[$scope.taskId].subtasks.push({});
        	ProjectData.update($scope.project, function(project){
        		$scope.project = project;
        	});
        };
        
        $scope.backToProject = function(){
        	console.log($stateParams.projectId);
        	$state.go("project", {projectId: $stateParams.projectId});
        };
        
        $scope.removeSubtask = function removeSubtask(index){
        	$scope.project.tasks[$scope.taskId].subtasks.splice(index, 1);
        	ProjectData.update($scope.project, function(project){
        		$scope.project = project;
        	});
        };
        
        $scope.open = function($event) {
	    	$scope.status.opened = true;
	    };
	    
        $scope.formatDate = function(task) {
      		task.dateDue = new Date(task.dateDue).getTime();
        };
        
        $scope.animationsEnabled = true;
        
        $scope.taskDiscussion = function() {
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/taskDiscussion.html',
                controller: 'TaskDiscussionCtrl',
                resolve: {
                	managers: function () {
                		return $scope.project.managers;
                	},
                	comments: function () {
                        return $scope.project.tasks[$scope.taskId].comments;
                    }
                }
            });
            modalInstance.result.then(function(result) {
            	$scope.project.tasks[$scope.taskId].comments = result.comments;
            	
            	ProjectData.update($scope.project, function(project){
           		    $scope.project = project;
            	});
            });
        };
        
        $scope.assignedTo = function(){
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/taskAssignedTo.html',
                controller: 'TaskAssignedToCtrl',
                resolve: {
                	assignedTo: function(){
                		return $scope.project.tasks[$scope.taskId].assignedTo;
                	},
                	managers: function () {
                        return $scope.project.managers;
                    },
                	employees: function () {
                        return $scope.project.employees;
                    }
                }
            });
            modalInstance.result.then(function(result) {
            	$scope.project.tasks[$scope.taskId].assignedTo = result.assignedTo;
            	ProjectData.update($scope.project, function(project){
           		    $scope.project = project;
            	});
            });
        };
        
        $scope.formatDate = function() {
        	$scope.project.tasks[$scope.taskId].dateDue = Math.floor(new Date($scope.project.tasks[$scope.taskId].dateDue).getTime()); 
        };
        
    }]);


