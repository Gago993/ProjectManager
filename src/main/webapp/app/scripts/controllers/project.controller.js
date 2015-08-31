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

ProjectManagerApp.controller('ProjectCtrl', ['$scope', '$stateParams', '$modal', 
                        'ProjectData',
    function ($scope, $stateParams, $modal, ProjectData) {
        console.log("Project Controller reporting for duty.");
       
        $scope.project = ProjectData.get({id: $stateParams.projectId },function(data){
        });
        
        
        $scope.addTag = addTag;
        $scope.removeTag = remove;
        $scope.newMember = addNewMember;
        $scope.createTask = createTask;
        //updates model changes
        $scope.updateProject = updateProject;
        
        
        function addTag(result) {
        	console.log("project",result);
        	$scope.project.tags.push(result);
        	ProjectData.update($scope.project,function(data){
        		console.log(data);
        		$scope.project.tags = data.tags;
        	});
        }
        
        function remove(index) {
        	console.log("remove",index);
        	$scope.project.tags.splice(index, 1);
        	ProjectData.update($scope.project,function(data){
        		console.log(data);
        		$scope.project.tags = data.tags;
        	});
        }
        
        function addNewMember() {
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/newMember.html',
                controller: 'CreateMemberCtrl',
                resolve: {
                	employees: function () {
                        return $scope.project.employees;
                    },
                    managers: function () {
                        return $scope.project.managers;
                    },
                }
            });
            modalInstance.result.then(function (newMembers) {
            	angular.extend($scope.project.managers, newMembers.managers);
            	angular.extend($scope.project.employees, newMembers.employees);
            	ProjectData.update($scope.project,function(data){
           		 	console.log(data);
            		$scope.project = data;
            	});
            });
        }
        
        function updateProject() {
        	ProjectData.update($scope.project,function(data){
        		console.log("update project",data);
        		$scope.project = data;
        	});
        }
        
        function createTask() {
        	$scope.project.tasks.push({});
        	ProjectData.update($scope.project,function(data){
        		console.log("create new task",data);
        		$scope.project = data;
        	});
        }
        
    }]);


