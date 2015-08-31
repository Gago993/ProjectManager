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

ProjectManagerApp.controller('MemberCtrl', ['$http', '$scope', '$state', '$rootScope', '$routeParams', 'MemberData',
    function ($http, $scope, $state, $rootScope, $routeParams, MemberData) {
        console.log("Member Controller reporting for duty.");
        
        MemberData.get({id: $routeParams['memberId']}).$promise.then(function(member) {
            $scope.member = member;
        });
        
        var saveMember = function(){
        	MemberData.update({}, $scope.member);
        };
        
        $scope.changePicture = function(){
        	//TODO: fetch file contents
        	
        	MemberData.changePicture({id: $routeParams['memberId']}, fileContents).$promise.then(function(member) {
                $scope.member = member;
            });
        };
        
        $scope.removePicture = function(){
        	MemberData.removePicture({id: $routeParams['memberId']}).$promise.then(function(member) {
                $scope.member = member;
            });
        };
        
        $scope.addExperience = function(){
        	$scope.member.experience.push({});
        	
        	saveMember();
        };
        
        $scope.removeExperience = function(index){
        	$scope.member.experience.slice(index, 1);
        	
        	saveMember();
        };
    }]);


