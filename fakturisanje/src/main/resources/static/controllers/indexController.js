var app = angular.module('projectPI',['ngRoute', 'ngMaterial', 'md.data.table', 'ngMdIcons']);

app.controller('indexController',['$scope', '$location', '$mdDialog', 'loginService', function($scope, $location, $mdDialog, loginService){
	
	$scope.ulogovan = false;
	
	$scope.login = function(){
		
		var username = $scope.username;
		
		loginService.login(username).then(function(response) {
			
			var password = $scope.password;

			if(password == response.data.password){
				$scope.ulogovan = true;
				$location.path("/home");		
			}
			else{
				$scope.username = "";
				$scope.password = "";
				$scope.ulogovan = false;
			}
		
		});
	
	}
	
	$scope.goToPregledPreduzeca = function() {
		$location.path("/pregledPreduzeca");
	}
	
	$scope.goToFaktura = function() {
		$location.path("/faktura");
	}
	
	$scope.goToSveFakture = function() {
		$location.path("/listaSvihFaktura");
	}
	
	$scope.goToNarudzbenica = function() {
		$location.path("/narudzbenica");
	}
	
	$scope.dodajCenovnik = function() {
		$location.path("/cenovnik");
	}
	
	
	
	
}]);