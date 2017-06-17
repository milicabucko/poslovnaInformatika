app.controller('loginController',['$scope', '$location', 'loginService','authenticationService', function($scope, $location, loginService,authenticationService){
	
	
	$scope.login = function () {
		console.log($scope.user);
//        authenticationService.login($scope.user, function () {
//            $http.get('/api/users/me')
//                .success(function (user) {
//                    authenticationService.setUser(user);
//                    $state.transitionTo('home');
//                })
//                .error(error);
//        }, error);
		
		loginService.loginUser($scope.user).then(function(response){
			if(($scope.user.username === response.data.username) && ($scope.user.password === response.data.password)){
				
				authenticationService.setUser(response.data);
				$location.path('/home');
			}else {
				$mdDialog.show(
				        $mdDialog.alert()
				            .parent(angular.element(document.body))
				            .title('Neuspesno logovanje!')
				            .content('Pogresno korisnicko ime ili lozinka.')
				            .ok('Ok')
				    );
			}

		});
    };
	
}]);
