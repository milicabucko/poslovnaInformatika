var app = angular.module('projectPI',['ngRoute', 'ngMaterial', 'md.data.table', 'ngMdIcons']);

app.controller('indexController',['$scope', '$location', '$window','$mdDialog', 'loginService', function($scope, $location, $window, $mdDialog, loginService){
	
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
	
	$scope.goToPrimka = function() {
		$location.path("/primka");
	}
	
	$scope.dodajCenovnik = function() {
		$location.path("/cenovnik");
	}
	
	$scope.goToPreduzece = function() {
		$location.path("/preduzece");
	}
	
	$scope.dodajMagacin = function() {
		$location.path("/magacin");
	}
	$scope.pregledCenovnika = function() {
		$location.path("/pregledCenovnika");
	}
	
	$scope.pregledMagacina = function() {
		$location.path("/pregledMagacina");
	}
	
	$scope.goToStopa = function() {
		$location.path("/pregledStopa");
	}
	$scope.goToVrsta = function() {
		$location.path("/pregledVrstiPDVa");
	}
	$scope.goToGrupaArtikala = function() {
		$location.path("/grupa");
	}
	$scope.pregledajGrupe = function() {
		$location.path("/grupeArtikala");
	}
	$scope.goToJediniceMere = function() {
		$location.path("/jediniceMere");
	}
	
}]);