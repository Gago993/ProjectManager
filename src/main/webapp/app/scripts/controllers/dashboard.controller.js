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

ProjecManagerApp.controller('DashboardCtrl', ['$scope',
                                          
    function ($scope) {
        console.log("Dashboard Controller reporting for duty.");
        
        $scope.createProject = createProject;
        $scope.i = 1;
        $scope.projects = [];
        
        
        
        
        function createProject() {
        	$scope.tmp = {};
        	angular.copy($scope.project,$scope.tmp);
        	$scope.tmp.id = $scope.i;
        	$scope.i = $scope.i + 1;
			$scope.projects.push($scope.tmp);
		}
        
        
        $scope.project = 	{
         	   id: "123456",
         	   name: "Prv proekt",
         	   description: "Ova e prviot proekt",
         	   managers: [{ name: "gago" }],
         	   employees: [{ name: "petko" }],
         	   dateDue: "1439571420",
         	   tasks: [{
         		   name: "Napravi prvoto"
         			   },
         			   {
 				   name: "Napravi vtoro"	   
         			   },
         			   {
 				   name: "Napravi treto"		   
         			   }],
 			   codeSnippets: [{
         		   name: "public function main(){dfafd; fdf df ;}"
         			   },
         			   {
 				   name: "public function vtora(){//ova e vtora funkcija}"	   
         			   }],
 			   attachments: [{
         		   name: ".pdf"
         			   },
         			   ],
 			   comments: [{
         		   name: "gago",
         		   comment: "ide li?"
         			   },
         			   {
 				   name: "petko",
         		   comment: "se tera nekako"	   
         			   },
         			   {
 				   name: "gago",
         		   comment: "super pozz"		   
         			   }],
 			   tags: [{
         		   name: ".NET"
         			   },
         			   {
 				   name: "Android"	   
         			   },
         			   {
 				   name: "Ruby"		   
         			   }],
				};
        
        
    }]);


