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

ProjectManagerApp.controller('ProjectDiscussionCtrl',['$scope', '$modalInstance',
                        'MemberData', 'authentication', 'managers', 'comments',
                                                
	function ($scope, $modalInstance, MemberData, authentication, managers, comments) {
		var editingComment = null;
		
		$scope.comment = "";
		$scope.member = authentication.getMember();
		$scope.comments = comments;
		
		$scope.authors = new Array(comments.length);
		for(var i = 0, len = comments.length; i < len; i++){
			(function(i){
				MemberData.get({id: comments[i].author}).$promise.then(function(member) {
		            $scope.authors[i] = member;
		        });
			})(i);
		}

		$scope.save = function(){
			$modalInstance.close({comments: $scope.comments});
		};

		$scope.addComment = function(){
			if($scope.comment.length > 0){
				if(!editingComment){
					$scope.comments.push({
						author: $scope.member.id,
						body: $scope.comment,
						timestamp: Math.floor(new Date().getTime() / 1000)
					});
					
					$scope.authors[$scope.comments.length - 1] = $scope.member;
					
					$scope.comment = "";
				}else{
					var index = $scope.comments.indexOf(editingComment);
					
					$scope.comments[index].author = $scope.member.id;
					$scope.comments[index].body = $scope.comment;
					
					$scope.comment = "";
					editingComment = null;
				}
			}
		};
		
		$scope.removeComment = function(index){
			$scope.comments.splice(index, 1);
		};
		
		$scope.editComment = function(comment){
			editingComment = comment;
			
			$scope.comment = comment.body;
		};
		
		$scope.canEditComment = function(comment){
			return comment.author === $scope.member.id || managers.indexOf($scope.member.id) != -1; 
		};
		
		$scope.printTimestamp = function(timestamp){
			var date = new Date(timestamp * 1000);
			
			var year = date.getFullYear();
		    var month = date.getMonth() + 1;
		    var day = date.getDate();
		    var hour = date.getHours();
		    var minutes = date.getMinutes();
		    var seconds = date.getSeconds();
		    
		    if(month < 10){
		    	month = '0' + month;
		    }
		    
		    if(day < 10){
		    	day = '0' + day;
		    }
		    
		    if(hour < 10){
		    	hour = '0' + hour;
		    }
		    
		    if(minutes < 10){
		    	minutes = '0' + minutes;
		    }
		    
		    if(seconds < 10){
		    	seconds = '0' + seconds;
		    }
		    
		    return day + "." + month + "." + year + " " + hour + ":" + minutes + ":" + seconds;
		}
}]);

