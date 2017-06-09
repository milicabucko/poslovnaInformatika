app.controller('artikalController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'artikalService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, artikalService){
	
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