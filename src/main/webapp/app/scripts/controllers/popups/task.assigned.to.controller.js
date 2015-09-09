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

ProjectManagerApp.controller('TaskAssignedToCtrl', ['$scope', '$modalInstance',
                        'MemberData', 'authentication', 'assignedTo', 'managers', 'employees',
                        
	function ($scope, $modalInstance, MemberData, authentication, assignedTo, managers, employees) {
		$scope.currentMember = authentication.getMember();
		
		$scope.assignedTos = new Array(assignedTo.length);
		$scope.allOtherEmployees = new Array(managers.length + employees.length - assignedTo.length);
		
		var init = function(){
			for(var i = 0, len = assignedTo.length; i < len; i++){
				(function(i){
					MemberData.get({id: assignedTo[i]}).$promise.then(function(member){
			            $scope.assignedTos[i] = member;
			        });
				})(i);
			}
			
			var employeesTotal = managers.slice().concat(employees);
			for(var i = 0, len = employeesTotal.length; i < len; i++){
				if(managers.indexOf(employeesTotal[i].id) == -1 && employees.indexOf(employeesTotal[i].id) == -1){
					(function(i) {
						MemberData.get({id: employeesTotal[i]}).$promise.then(function(employee) {
							$scope.allOtherEmployees[i] = employee;
				        });
					})(i);
				}
			}
		};
		init();
		
		function getAssignedToIds(){
			var assignedToIds = [];
			
			for(var i = 0, len = $scope.assignedTos.length; i < len; i++){
				assignedToIds.push($scope.assignedTos[i].id);
			}
			
			return assignedToIds;
		};

		$scope.save = function(){
			$modalInstance.close({assignedTo: getAssignedToIds()});
		};
		
		$scope.cancel = function(){
			$modalInstance.dismiss('cancel');
		};
		
		$scope.addEmployee = function(assignedTo){
			if(assignedTo && assignedTo.originalObject && assignedTo.originalObject.id) {
				var assignedToIds = getAssignedToIds();
				if(assignedToIds.indexOf(assignedTo.originalObject.id) == -1) {
					$scope.assignedTos.push(assignedTo.originalObject);
				}
				$scope.$broadcast('angucomplete-alt:clearInput', 'assignees');
			}
		};
		
		$scope.removeEmployee = function(assignedTo){
			if(assignedTo && assignedTo.id){
				var index = getAssignedToIds().indexOf(assignedTo.id); 
				if(index != -1){
					$scope.assignedTos.splice(index, 1);
				}
			}
		}; 
		
		$scope.canRemoveEmployee = function(assignedTo){
			if(assignedTo){
				return $scope.currentMember.id == assignedTo.id || managers.indexOf($scope.currentMember.id) != -1;
			}else{
				return false;
			}
		}
}]);

