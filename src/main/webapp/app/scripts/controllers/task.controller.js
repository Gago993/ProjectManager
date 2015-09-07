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

        function createSubtask(){
        	$scope.project.tasks[$scope.taskId].subtasks.unshift({});
        	ProjectData.update($scope.project,function(data){
        		console.log("create new subtask",data);
        		$scope.project.tasks = data.tasks;
        	});
        }
        
        function backToProject(){
        	console.log($stateParams.projectId);
        	$state.go("project",{projectId: $stateParams.projectId});
        }
        
	    
	    function open($event) {
	        $scope.status.opened = true;
	      };

      
        function formatDate(task) {
      	  task.dateDue = new Date(task.dateDue).getTime();
        };
        
        
        
        function taskDiscussion(task) {
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/taskDiscussion.html',
                controller: 'TaskDiscussionCtrl',
                resolve: {
                	comments: function () {
                        return task.comments;
                    }
                }
            });
            modalInstance.result.then(function(result) {
            	task.comments = result.comments;
            	
            	ProjectData.update($scope.project,function(data){
           		 	console.log(data);
           		    $scope.project.tasks = data.tasks;
            	});
            });
        }
          
        
    }]);


