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

ProjectManagerApp.controller('CreateMemberCtrl',['$scope', '$modalInstance',
                        'MemberData', 'managers', 'employees',
                                                
	function ($scope, $modalInstance, MemberData, managers, employees) {
		console.log("Create Member Controller reporting for duty.");
				
		$scope.managers = managers;
		$scope.employees = employees;
		$scope.result = [];
		$scope.result.managers = [];
		$scope.result.employees = [];
		
		$scope.createMember = function () {
		    $modalInstance.close($scope.result);
		};
		
		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};
		
		$scope.addMember = addMember;
		
		
		function addMember(member){
			if(member && member.id) {
				var id = member.id;
				if($scope.result.employees.indexOf(id) == -1) {
					$scope.result.employees.push(id);
				}
				$scope.$broadcast('angucomplete-alt:clearInput', 'members');
			}
		}
		
				    
}]);

