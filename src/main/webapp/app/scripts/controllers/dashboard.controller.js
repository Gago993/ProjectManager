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

ProjectManagerApp.controller('DashboardCtrl', ['ProjectData', '$scope', '$modal',
                                          
    function (ProjectData, $scope, $modal) {
        $scope.animationsEnabled = true;
        
        $scope.projects = ProjectData.query();

        $scope.createProject = function(){
        	ProjectData.save(function(data){
        		$scope.projects.push(data);
        	});
		};
		
		$scope.updateProject = function(project){
        	ProjectData.update({}, project);
        };
		
        $scope.removeProject = function(project){
        	ProjectData.remove({id: project.id}, function(data){
	       		var index = $scope.projects.indexOf(project);
	       		$scope.projects.splice(index, 1);     
	    	});
        };
        
        $scope.search = function(row){
            return (angular.lowercase(row.name).indexOf($scope.query || '') !== -1 || angular.lowercase(row.description).indexOf($scope.query || '') !== -1);
        };
    }]);