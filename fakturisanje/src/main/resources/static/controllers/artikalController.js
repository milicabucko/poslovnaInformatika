app.controller('artikalController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'artikalService','jediniceMereService','authenticationService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, artikalService, jediniceMereService, authenticationService){
	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	var urlParams = $location.search();
	var id = $scope.user.company.id;
	var idArtikla = urlParams.artikalID;
	
	jediniceMereService.getAllJedinice(id).then(function(response){
		$scope.jedinice = response.data;
	});
	
	
	$scope.kreirajArtikal = function(){
		console.log(urlParams.companyId + " url param");
		var idToSend = urlParams.grupaID;
		var idJedinice = $scope.artikal.jedinicaMere.id;
		
		if(!("undefined" === typeof $scope.artikaal)){
			$scope.artikal.id = $scope.artikaal.id;
			artikalService.updateArtikal($cope.artikal).then(function(response){
				$location.path('/grupeArtikala');
			});
		}
		
		else{
		artikalService.createArtikal($scope.artikal, idToSend, idJedinice).then(function(response){
			$location.path('/grupeArtikala');
		});
		}
	}
	
	if(idArtikla != null){
		artikalService.findArtikal(idArtikla).then(function(response){
			$scope.artikaal = response.data;
		});
	}
	
}]);