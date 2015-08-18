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

ProjecManagerApp.controller('CreateProjectCtrl', ['$scope', '$modalInstance',
                                  
    function ($scope, $modalInstance) {
        console.log("Create Project Controller reporting for duty.");
        
        $scope.createProject = function () {
            $modalInstance.close($scope.project);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.openDatePicker = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.opened = !$scope.opened;
        };

        $scope.ismeridian = false
        $scope.formatDate = function () {
         // $scope.project.dateDue = $filter('date')($scope.project.dateDue, 'yyyy-MM-ddTHH:mm:ssZ');
            $scope.project.dateDue = new Date($scope.project.dateDue).getTime();
        };
        
        
    }]);


