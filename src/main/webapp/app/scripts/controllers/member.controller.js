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

ProjectManagerApp.controller('MemberCtrl', ['$http', '$scope', '$state', '$rootScope', '$stateParams', 'MemberData', 'authentication', 
    function ($http, $scope, $state, $rootScope, $stateParams, MemberData, authentication) {
        console.log("Member Controller reporting for duty.");
        
        MemberData.get({id: $stateParams['memberId']}).$promise.then(function(member) {
            $scope.member = member;
            $scope.authenticatedMember = authentication.getMember(); 
            /*updatePicture();*/
        });
    
        
        $scope.updateMember = updateMember;
       
        $scope.canEditTest = canEditTest;
        
        $scope.changePicture = changePicture;
        $scope.removePicture = removePicture;
        
        $scope.addExperience = addExperience;
        $scope.removeExperience = removeExperience;
        
        $scope.addSkill = addSkill;
        $scope.removeSkill = removeSkill;
        
     
        
        function updateMember(){
        	MemberData.update({}, $scope.member);
        }
        
        
        function canEditTest(){
        	return $scope.member && $scope.authenticatedMember && $scope.member.id === $scope.authenticatedMember.id;
        }
        
        function changePicture(){
        	var fileSelector = angular.element('input[name=picture]');
        	fileSelector.unbind();
        	
        	fileSelector.change(function(){
        		var file = this.files[0];
            	var filetype = file.type;
            	if(filetype === "image/jpg" || filetype === "image/jpeg" || filetype === "image/png"){
            		var formData = new FormData();
            		formData.append('picture', file);
        			
            		MemberData.changePicture({id: $stateParams['memberId']}, formData).$promise.then(function(member) {
            			$scope.member = member;
                        /*updatePicture();*/
                    });
            		fileSelector.replaceWith(fileSelector.clone(true));
    			}
        	});
        	
        	fileSelector.click();
        };
        
        
        function removePicture(){
        	MemberData.removePicture({id: $stateParams['memberId']}).$promise.then(function(member) {
        		$scope.member = member;
        		/*updatePicture();*/
            });
        };
        
        
        function addExperience(){
        	$scope.member.experience.push({});
        	
        	updateMember();
        };
        
        
        function removeExperience(index){
        	$scope.member.experience.slice(index, 1);
        	
        	updateMember();
        };
        
        
        function addSkill(result){
        	$scope.member.skills.push(result);
        	updateMember();
        }
        
        function removeSkill(index){
        	console.log(index);
        	$scope.member.skills.slice(index,1);
        	updateMember();
        }
        
        
        
        
        
    }]);

/*var updatePicture = function(){
if($scope.member.picture != ''){
	$http.get('app/uploads/pictures/' + $scope.member.picture + '.png').then(function(response){
		angular.element("div.image-container img").attr('src', response.data);
    }, function(response) {
    	angular.element("div.image-container img").attr('src', 'app/uploads/pictures/default.png');
    });
}else{
	angular.element("div.image-container img").attr('src', 'app/uploads/pictures/default.png');
}
};*/
