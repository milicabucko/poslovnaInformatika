app.controller('grupaArtikalaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'vrstaPDVaService', 'grupaArtikalaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, vrstaPDVaService, grupaArtikalaService){
	
	vrstaPDVaService.getAllVrste().then(function(response){
		 
		 $scope.vrste = response.data;
		 $scope.vrsteSize = $scope.vrste.length;
	 
	});
	
	companyService.getAllCompanies().then(function(response){
		$scope.firme = response.data;
	});
	
	
	$scope.kreirajGrupuArtikala = function(){
		var id = $scope.grupa.company.id;
		var vrstaID = $scope.grupa.vrstaPDVa.id;
		grupaArtikalaService.createGrupu($scope.grupa, id, vrstaID).then(function(response){
			alert("dodato");
			
		});
	}
	
	
}]);