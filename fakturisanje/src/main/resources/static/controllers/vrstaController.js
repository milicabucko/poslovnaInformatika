app.controller('vrstaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService', 'vrstaPDVaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService, vrstaPDVaService){
	
	
	
	$scope.kreirajVrstuPDVa = function(){
		vrstaPDVaService.createVrstu($scope.vrsta).then(function(response){
			alert("dodato");
			
		});
	}
	
}]);