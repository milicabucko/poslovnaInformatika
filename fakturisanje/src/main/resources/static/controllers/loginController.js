app.controller('loginController',['$scope', '$location', 'loginService', function($scope, $location, loginService){

	
	$scope.login = function(){
		
		var username = $scope.username;
		
		loginService.login(username).then(function(response) {
			
			var password = $scope.password;

			if(password == response.data.password){
				$location.path("/home");
			}
			else{
				$scope.username = "";
				$scope.password = "";
			}
		
		});
	
	}
	
}]);
