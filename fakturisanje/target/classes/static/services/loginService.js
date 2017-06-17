app.factory('loginService', function loginService($http){
	
loginService.loginUser = function(user){
		
		return $http({
			method: 'POST',
			url: 'api/users/me/',
			data: user
		});
	}
	
	return loginService;
});