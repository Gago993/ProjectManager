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

ProjecManagerApp.controller('ProjectCtrl', ['$scope', '$stateParams', 'ProjectData',
    function ($scope, $stateParams, ProjectData) {
        console.log("Project Controller reporting for duty.");
       
        $scope.project = ProjectData.get({id: $stateParams.projectId },function(data){
        });
        
        $scope.tasks = [
                        {
                        	name: "task1",
                        	description: "tsdfdafd d fds fdasf dsfd fdf d",
                        	assignedTo: [],
                        	dateDue: "21323231232",
                        	subtasks: [{sdf:"afdf"}],
                        	codeSnippets: [],
                        	attachments: [],
                        	comments:[],
                        	finished: true,
                        	timestamp: "",
                        	
                        },
                        {
                        	name: "task2",
                        	description: "tsdfdasdsadfdasf dsfd fdf d",
                        	assignedTo: [],
                        	dateDue: "3434343434343",
                        	subtasks: [],
                        	codeSnippets: [],
                        	attachments: [],
                        	comments:[],
                        	finished: false,
                        	timestamp: "",
                        	
                        }
                        
                        ];
        
        
        $scope.addTag = addTag;
        $scope.removeTag = remove;
        $scope.newMember = addNewMember;
        $scope.updateProject = updateProject;
        
        
        console.log($stateParams);
        
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
        	//$modal
        }
        
        function updateProject() {
        	ProjectData.update($scope.project,function(data){
        		console.log("update project",data);
        		$scope.project = data;
        	});
        }
        
    }]);


