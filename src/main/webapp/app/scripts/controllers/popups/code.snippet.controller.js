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

ProjectManagerApp.controller('CodeSnippetCtrl',['$scope', '$modalInstance',
                    'codeSnippet',                             
	function ($scope, $modalInstance, codeSnippet) {
		console.log("Code Snippet Controller reporting for duty.");
				
		$scope.mode = "java";
		
		$scope.codeSnippet = codeSnippet;
		$scope.aceOption = {
			    mode: $scope.mode.toLowerCase(),
			    onLoad: function (_ace) {
			 
			      // HACK to have the ace instance in the scope...
			      $scope.modeChanged = function () {
			        _ace.getSession().setMode("ace/mode/" + $scope.mode.toLowerCase());
			      };
			 
			    }
			  };
			
		
		
		
		$scope.createMember = function () {
		    $modalInstance.close($scope.result);
		};
		
		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};
		
				    
}]);

