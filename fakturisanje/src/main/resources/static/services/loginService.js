app.factory('loginService', 'authenticationService', function loginService($http, authenticationService){
	
	loginService.login = function(username){
		return $http({
			method: 'GET',
			url: 'api/users/findByUsername/' + username
		});
	}
	
	return loginService;
});