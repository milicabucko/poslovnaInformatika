app.controller('grupaArtikalaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'vrstaPDVaService', 'grupaArtikalaService','authenticationService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, vrstaPDVaService, grupaArtikalaService, authenticationService){
	
	vrstaPDVaService.getAllVrste().then(function(response){
		 
		 $scope.vrste = response.data;
		 $scope.vrsteSize = $scope.vrste.length;
	 
	});
	
	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	companyService.getAllCompanies().then(function(response){
		$scope.firme = response.data;
	});
	
	var urlParams =  $location.search();
	var id = $scope.user.company.id;
	var idGrupe = urlParams.grupaID;
	
	if(idGrupe != null){
		grupaArtikalaService.findGrupu(idGrupe).then(function(response){
			$scope.grupaa = response.data;
		});
	}
	
	
	$scope.kreirajGrupuArtikala = function(){
		var vrstaID = $scope.grupa.vrstaPDVa.id;
		if(!("undefined" === typeof $scope.grupaa)){
			grupaArtikalaService.updateGrupu($scope.grupa, vrstaID).then(function(response){
				$location.path('/grupeArtikala');
			});
		}else{
		grupaArtikalaService.createGrupu($scope.grupa, id, vrstaID).then(function(response){
			$location.path('/grupeArtikala');
		});
		}
	}
	
	
}]);