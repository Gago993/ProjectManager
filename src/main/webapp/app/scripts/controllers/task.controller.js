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
        console.log("Task Controller reporting for duty.");
        console.log($stateParams);

        if($stateParams.taskIndex == null){
        	$state.go("project",{projectId: $stateParams.projectId});
        }

        $scope.taskId = $stateParams.taskIndex;
        
        $scope.createSubtask = createSubtask;
        $scope.backToProject = backToProject;
        
        $scope.open = open;
        $scope.formatDate = formatDate;
        
        $scope.animationsEnabled=true;
        $scope.taskDiscussion = taskDiscussion;
        
        $scope.assignedTo = assignedTo;
        
        $scope.formatDate = formatDate;
        
        function createSubtask(){
        	$scope.project.tasks[$scope.taskId].subtasks.push({});
        	ProjectData.update($scope.project,function(data){
        		console.log("create new subtask",data);
        		$scope.project.tasks = data.tasks;
        	});
        }
        
        function backToProject(){
        	console.log($stateParams.projectId);
        	$state.go("project", {projectId: $stateParams.projectId});
        }
        
	    function open($event) {
	    	$scope.status.opened = true;
	    };
      
        function formatDate(task) {
      	  task.dateDue = new Date(task.dateDue).getTime();
        };
        
        function taskDiscussion() {
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
        }
        
        function assignedTo(){
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
        }
        
        function formatDate() {
        	$scope.project.tasks[$scope.taskId].dateDue = Math.floor(new Date($scope.project.tasks[$scope.taskId].dateDue).getTime()); 
        };
    }]);


