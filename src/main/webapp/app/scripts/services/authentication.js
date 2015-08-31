ProjectManagerApp.factory('authentication', ['$http', '$rootScope', function($http, $rootScope){
	var member = null;
	
	function requestTransformationFunction(obj){
		var str = [];
	    for(var p in obj){
	    	str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	    }
	    return str.join("&");
	}
	
	return {
		getMember: function(){
			return member;
		},
		
		login: function(email, password){
			var reqBody = {
				email: email,
				password: password
			};
			
			var req = {
			      url: 'login',
			      method: "POST",
			      transformRequest: requestTransformationFunction,
			      data: reqBody,
			      headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
		    };
			
			$http(req).then(function(response) {
				member = response.data;
				
				$rootScope.$broadcast('authenticationLogin');
			}, function(response) {
				member = null;
				
				$rootScope.$broadcast('authenticationLogin');
			});
		},
		
		register: function(){
			var reqBody = {
				email: email,
				password: md5.createHash(password),
				firstname: firstname,
				lastname: lastname
			};
			
			var req = {
				    method: 'POST',
				    url: 'register',
				    transformRequest: requestTransformationFunction,
				    data: reqBody,
				    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				};
			
			$http(req).then(function(response) {
				member = response.data;
				
				$rootScope.$broadcast('authenticationRegistration');
			}, function(response) {
				member = null;
				
				$rootScope.$broadcast('authenticationRegistration');
			});
		},
		
		logout: function(){
			$http.get('logout').then(function(response) {
				member = null;
				
				$rootScope.$broadcast('authenticationLogout');
			}, function(response) {

			});
		}
	};
}]);