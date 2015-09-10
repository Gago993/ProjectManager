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

ProjectManagerApp.controller('CodeSnippetCtrl',['$scope', '$modalInstance', 'snippet',
                                         
	function ($scope, $modalInstance, snippet) {
		$scope.codeSnippet = snippet;
		
		$scope.modes = {
			java: {mode:'Java',extension:'java'},
			javascript: {mode:'JavaScript',extension:'js'},
			c_cpp: {mode:'C++',extension:'cpp'},
			csharp: {mode:'C#',extension:'cs'},
			objectivec: {mode:'Objective C',extension:'m'},
			php: {mode:'PHP',extension:'php'},
			html: {mode:'HTML',extension:'html'},
			css: {mode:'CSS',extension:'css'},
			sql: {mode:'SQL',extension:'sql'}
		};
		
		$scope.aceOption = {
			mode: $scope.codeSnippet.extension,
			onLoad: function (_ace) {
			    //HACK to have the ace instance in the scope...
			    $scope.modeChanged = function () {
			        _ace.getSession().setMode("ace/mode/" + $scope.codeSnippet.extension);
			    };
			}
		};
		
		$scope.createSnippet = function () {
		    $modalInstance.close($scope.codeSnippet);
		};
		
		$scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
		};
		
				    
}]);

