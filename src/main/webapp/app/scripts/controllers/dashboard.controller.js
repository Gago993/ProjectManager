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

ProjecManagerApp.controller('DashboardCtrl', ['ProjectData', '$scope', '$modal',
                                          
    function (ProjectData, $scope, $modal) {
        console.log("Dashboard Controller reporting for duty.");
        
        $scope.animationsEnabled = true;

        $scope.createProject = createProject;
        $scope.removeProject = removeProject;
        $scope.openProject = openProject;
        
        $scope.search = search;
        
        $scope.projects = ProjectData.query(function(data){});
        
        
        
        
        
        function createProject() {
            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'app/views/popups/newProjectDialog.html',
                controller: 'CreateProjectCtrl',
                resolve: {
                }
            });
            modalInstance.result.then(function (retProject) {
            	console.log("returned project",retProject);
            	
            	ProjectData.save(retProject,function(data){
           		 $scope.projects.push(data);
            	});
            });
		};
        
        function removeProject(id) {
        	ProjectData.remove({id: id},function(data){
        		$scope.projects.splice(id,1);
        	});
        };
        
        function openProject(project) {
        	
        };
        
        
        function search(row) {
            return (angular.lowercase(row.name).indexOf($scope.query || '') !== -1 || angular.lowercase(row.description).indexOf($scope.query || '') !== -1);
        };
        
        
        
        
    }]);


