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
        console.log("Dashboard Controller reporting for duty.");
        
        $scope.animationsEnabled = true;

        $scope.createProject = createProject;
        $scope.removeProject = removeProject;
        $scope.openProject = openProject;
        
        $scope.search = search;
        
        $scope.projects = ProjectData.query(function(data){});

        function createProject() {
            
        	ProjectData.save(function(data){
        		$scope.projects.unshift(data);
        	});
        	
		};
        
        function removeProject(project) {
        	ProjectData.remove({id: project.id},function(data){
        		 var index = $scope.projects.indexOf(project);
        		  $scope.projects.splice(index, 1);     
        	});
        };
        
        function openProject(project) {
        	
        };
        
        
        function search(row) {
            return (angular.lowercase(row.name).indexOf($scope.query || '') !== -1 || angular.lowercase(row.description).indexOf($scope.query || '') !== -1);
        };
        
        
        
        
    }]);


