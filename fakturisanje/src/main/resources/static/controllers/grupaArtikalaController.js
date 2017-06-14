app.controller('grupaArtikalaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'vrstaPDVaService', 'grupaArtikalaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, vrstaPDVaService, grupaArtikalaService){
	
	vrstaPDVaService.getAllVrste().then(function(response){
		 
		 $scope.vrste = response.data;
		 $scope.vrsteSize = $scope.vrste.length;
	 
	});
	
	companyService.getAllCompanies().then(function(response){
		$scope.firme = response.data;
	});
	
	var urlParams =  $location.search();
	var id = urlParams.companyID;
	var idGrupe = urlParams.grupaID;
	
	if(idGrupe != null){
		grupaArtikalaService.findGrupu(idGrupe).then(function(response){
			$scope.grupaa = response.data;
		});
	}
	
	
	$scope.kreirajGrupuArtikala = function(){
		var vrstaID = $scope.grupa.vrstaPDVa.id;
		grupaArtikalaService.createGrupu($scope.grupa, id, vrstaID).then(function(response){
			alert("dodato");
			
		});
	}
	
	
}]);