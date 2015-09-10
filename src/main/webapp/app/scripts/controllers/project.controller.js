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
                        'ProjectData', 'authentication',
    function ($scope, $stateParams, $state, $modal, ProjectData, authentication) {
        $scope.member = authentication.getMember();
        
        ProjectData.get({id: $stateParams.projectId}, function(project){
        	$scope.project = project;
        	
        	$scope.addTag = function(result) {
            	$scope.project.tags.push(result);
            	ProjectData.update($scope.project,function(data){
            		$scope.project.tags = data.tags;
            	});
            };
            
            $scope.removeTag = function(index) {
            	$scope.project.tags.splice(index, 1);
            	ProjectData.update($scope.project,function(project){
            		$scope.project = project;
            	});
            };
            
            $scope.projectMembers = function() {
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
                		$scope.project = data;
                	});
                });
            };
            
            $scope.projectDiscussion = function(){
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
                		$scope.project = data;
                	});
                });
            };
            
            $scope.createTask = function() {
            	var newTask = {assignedTo: [$scope.member.id]};
            	$scope.project.tasks.push(newTask);
            	ProjectData.update($scope.project,function(data){
            		$scope.project = data;
            	});
            };
            
            //updates model changes
            $scope.updateProject = function() {
            	ProjectData.update($scope.project,function(data){
            		$scope.project = data;
            	});
            };
            
            $scope.openTask = function(index) {
            	if($scope.canRemoveTask(index)){
            		$state.go("task", {taskIndex: index});
            	}
            };
            
            $scope.canRemoveTask = function(index) {
            	return $scope.project.managers.indexOf($scope.member.id) != -1 || $scope.project.tasks[index].assignedTo.indexOf($scope.member.id) != -1;
            };
            
            $scope.removeTask = function(index) {
            	$scope.project.tasks.splice(index, 1);
            	ProjectData.update($scope.project, function(project){        		
            		$scope.project = project;
            	});
            };
            
            $scope.openCodeSnippet = function openCodeSnippet(index){
            	var modalInstance = $modal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'app/views/popups/codeSnippet.html',
                    controller: 'CodeSnippetCtrl',
                    resolve: {
                    	snippet: function(){
                    		return $scope.project.codeSnippets[index];
                    	}
                    }
                });
                modalInstance.result.then(function(codeSnippet){
                	$scope.project.codeSnippets[index] = codeSnippet;
            		ProjectData.update($scope.project, function(project){
                		$scope.project = project;
                	});
                });
            };
            
            $scope.createCodeSnippet = function(){
            	$scope.project.codeSnippets.push({extension: 'java', name: '', code: ''});
            	$scope.openCodeSnippet($scope.project.codeSnippets.length - 1);
            };
            
            $scope.canRemoveCodeSnippet = function(index){
            	return $scope.project.managers.indexOf($scope.member.id) != -1 || $scope.project.codeSnippets[index].author == $scope.member.id; 
            };
            
            $scope.removeCodeSnippet = function(index){
        		$scope.project.codeSnippets.splice(index, 1);
        		ProjectData.update($scope.project, function(project){
            		$scope.project = project;
            	});
            };
            
            $scope.uploadAttachment = function() {
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
            };
            
            $scope.canRemoveAttachment = function(index){
            	return $scope.project.managers.indexOf($scope.member.id) != -1 || $scope.project.attachments[index].author == $scope.member.id; 
            };
            
            $scope.removeAttachment = function(index){
            	ProjectData.removeAttachment({id: $stateParams['projectId'], index: index}).$promise.then(function() {
        			$scope.project.attachments.splice(index, 1);
                });
            };
        });

    }]);


