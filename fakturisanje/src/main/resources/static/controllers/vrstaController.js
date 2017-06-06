app.controller('vrstaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService', 'vrstaPDVaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService, vrstaPDVaService){
	
	stopaPDVaService.getAllStope().then(function(response){
		 
		 $scope.stope = response.data;
		 $scope.stopeSize = $scope.stope.length;
	 
	});
	
	
	$scope.kreirajVrstuPDVa = function(){
		var stopaZaSlanje = $scope.vrsta.stopaPDVa.id;
		vrstaPDVaService.createVrstu($scope.vrsta, stopaZaSlanje).then(function(response){
			alert("dodato");
			
		});
	}
	
}]);