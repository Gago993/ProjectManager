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

ProjectManagerApp.controller('ProjectMembersCtrl',['$scope', '$modalInstance',
                        'MemberData', 'managers', 'employees',
                                                
	function ($scope, $modalInstance, MemberData, managers, employees) {
		$scope.managers = new Array(managers.length);
		$scope.employees = new Array(employees.length);
		
		for(var i = 0, len = managers.length; i < len; i++){
			(function(i) {
				MemberData.get({id: managers[i]}).$promise.then(function(manager) {
		            $scope.managers[i] = manager;
		        });
			})(i);
		}
		
		for(var i = 0, len = employees.length; i < len; i++){
			(function(i) {
				MemberData.get({id: employees[i]}).$promise.then(function(employee) {
		            $scope.employees[i] = employee;
		        });
			})(i);
		}
		
		function getManagersIds(){
			var managersIds = [];
			
			for(var i = 0, len = $scope.managers.length; i < len; i++){
				managersIds.push($scope.managers[i].id);
			}
			
			return managersIds;
		};
		
		function getEmployeesIds(){
			var employeeIds = [];
			
			for(var i = 0, len = $scope.employees.length; i < len; i++){
				employeeIds.push($scope.employees[i].id);
			}
			
			return employeeIds;
		};

		$scope.save = function(){
			$modalInstance.close({managers: getManagersIds(), employees: getEmployeesIds()});
		};
		
		$scope.cancel = function(){
			$modalInstance.dismiss('cancel');
		};
		
		$scope.addEmployee = function(member){
			if(member && member.originalObject && member.originalObject.id) {
				var employeesIds = getEmployeesIds();
				if(employeesIds.indexOf(member.originalObject.id) == -1) {
					$scope.employees.push(member.originalObject);
				}
				$scope.$broadcast('angucomplete-alt:clearInput', 'members');
			}
		};
		
		$scope.removeEmployee = function(member){
			if(member && member.id){
				var employeesIds = getEmployeesIds();
				if(employeesIds.indexOf(member.id) != -1){
					var index = employeesIds.indexOf(member.id);
					$scope.employees.splice(index, 1);
				}
			}
		}; 
		
		$scope.promoteEmployee = function(member){
			if(member && member.id){
				var employeesIds = getEmployeesIds(); 
				if(employeesIds.indexOf(member.id) != -1){
					var index = employeesIds.indexOf(member.id);
					$scope.employees.splice(index, 1);
					$scope.managers.push(member);
				}
			}
		};
}]);

