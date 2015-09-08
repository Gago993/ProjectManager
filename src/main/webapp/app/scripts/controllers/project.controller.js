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
                        'ProjectData', 'FileUploader', 'authentication',
    function ($scope, $stateParams, $state, $modal, ProjectData, FileUploader, authentication) {
        console.log("Project Controller reporting for duty.");
        
        $scope.member = authentication.getMember();
        
        $scope.project = ProjectData.get({id: $stateParams.projectId },function(data){
        	console.log('refresh');
        });
        
        
        $scope.addTag = addTag;
        $scope.removeTag = remove;
        $scope.projectMembers = projectMembers;
        $scope.projectDiscussion = projectDiscussion;
        $scope.createTask = createTask;
        //updates model changes
        $scope.updateProject = updateProject;
        $scope.openTask = openTask;
        $scope.createCodeSnippet = openCodeSnippet;
        $scope.removeCodeSnippet = removeSnippet;
        $scope.uploadAttachment = uploadAttachment;
        $scope.canRemoveAttachment = canRemoveAttachment;
        $scope.removeAttachment = removeAttachment;
        
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
        
        function projectMembers() {
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/projectMembers.html',
                controller: 'ProjectMembersCtrl',
                resolve: {
                	employees: function () {
                        return $scope.project.employees;
                    },
                    managers: function () {
                        return $scope.project.managers;
                    },
                }
            });
            modalInstance.result.then(function(result) {
            	$scope.project.managers = result.managers;
            	$scope.project.employees = result.employees;
            	
            	ProjectData.update($scope.project,function(data){
           		 	console.log(data);
            		$scope.project = data;
            	});
            });
        }
        
        function projectDiscussion() {
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/projectDiscussion.html',
                controller: 'ProjectDiscussionCtrl',
                resolve: {
                	managers: function () {
                		return $scope.project.managers;
                	},
                	comments: function () {
                        return $scope.project.comments;
                    }
                }
            });
            modalInstance.result.then(function(result) {
            	$scope.project.comments = result.comments;
            	
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
        	var newTask = {assignedTo: [$scope.member.id]};
        	$scope.project.tasks.push(newTask);
        	ProjectData.update($scope.project,function(data){
        		console.log("create new task",data);
        		$scope.project = data;
        	});
        }
        
        function openTask(index) {
        	if($scope.project.managers.indexOf($scope.member.id) != -1 || $scope.project.tasks[index].assignedTo.indexOf($scope.member.id) != -1){
        		$state.go("task", {taskIndex: index});
        	}
        }
        
        
        
        function openCodeSnippet() {
        	
        	var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/codeSnippet.html',
                controller: 'CodeSnippetCtrl',
                resolve: {
                }
            });
            modalInstance.result.then(function (codeSnippet) {
            	var formData = new FormData();
            	formData.append('snippet', codeSnippet.code);
        		formData.append('extension', codeSnippet.extension);
        		formData.append('name', codeSnippet.name);
        		formData.append('description', codeSnippet.description);
        		
        		ProjectData.uploadSnippet({id: $stateParams['projectId']}, formData).$promise.then(function(project) {
        			$scope.project = project;
                });
            });
        }
        
        function removeSnippet(index){
        	ProjectData.removeSnippet({id: $stateParams['projectId'], index: index}).$promise.then(function() {
    			$scope.project.codeSnippets.splice(index, 1);
            });
        }
        
        function uploadAttachment() {
        	var fileSelector = angular.element('input[name=fu-attachment]');
        	fileSelector.unbind();
        	
        	fileSelector.change(function(){
        		if(this.files && this.files[0]){
	        		var file = this.files[0];
	        		
	        		var formData = new FormData();
	        		formData.append('attachment', file);
	        		formData.append('name', file.name);
	        		formData.append('description', '');
	    			
	        		ProjectData.uploadAttachment({id: $stateParams['projectId']}, formData).$promise.then(function(project) {
	        			$scope.project = project;
	        			fileSelector.replaceWith(fileSelector.clone(true));
	                });
        		}
        	});
        	
        	fileSelector.click();
        }
        
        function canRemoveAttachment(index){
        	return $scope.project.managers.indexOf($scope.member.id) != -1 || $scope.project.attachments[index].author == $scope.member.id; 
        }
        
        function removeAttachment(index){
        	ProjectData.removeAttachment({id: $stateParams['projectId'], index: index}).$promise.then(function() {
    			$scope.project.attachments.splice(index, 1);
            });
        }
        
    }]);


