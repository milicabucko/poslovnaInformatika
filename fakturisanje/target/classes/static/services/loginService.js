app.factory('loginService', function loginService($http){
	
	loginService.login = function(username){
		return $http({
			method: 'GET',
			url: 'api/users/findByUsername/' + username
		});
	}
	
	return loginService;
});