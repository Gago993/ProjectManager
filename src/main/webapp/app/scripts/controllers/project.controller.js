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

ProjectManagerApp.controller('ProjectCtrl', ['$scope', '$stateParams', '$state', '$modal', 
                        'ProjectData', 'FileUploader',
    function ($scope, $stateParams, $state, $modal, ProjectData, FileUploader) {
        console.log("Project Controller reporting for duty.");
       
        $scope.project = ProjectData.get({id: $stateParams.projectId },function(data){
        });
        
        
        $scope.addTag = addTag;
        $scope.removeTag = remove;
        $scope.newMember = addNewMember;
        $scope.createTask = createTask;
        //updates model changes
        $scope.updateProject = updateProject;
        $scope.openTask = openTask;
        $scope.openCodeSnippet = openCodeSnippet;
        
        
        $scope.uploader = new FileUploader({
            url: '',
            removeAfterUpload: true,
            autoUpload: false,
            onCompleteItem: function (fileItem, response, status, headers) {
                console.log(response);
                
            }
        });
        
        
        
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
        	$scope.project.tasks.unshift({});
        	ProjectData.update($scope.project,function(data){
        		console.log("create new task",data);
        		$scope.project = data;
        	});
        }
        
        function openTask(index) {
        	console.log(index);
        	$state.go("task",{taskIndex: index});
        }
        
        function openCodeSnippet() {
        	
        	var snippet = {code: "public class HelloWorld {" +
        			"public static void main(String[] args) {" +
        			"System.out.println('Hello, World'); }}"};
        	
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/codeSnippet.html',
                controller: 'CodeSnippetCtrl',
                resolve: {
                	codeSnippet: function () {
                        return snippet;
                    },
                }
            });
            modalInstance.result.then(function () {
            	
            });
        }
        
        
        
    }]);


