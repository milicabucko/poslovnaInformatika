app.controller('stopaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService', 'vrstaPDVaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService, vrstaPDVaService){
	
	vrstaPDVaService.getAllVrste().then(function(response){
		 
		 $scope.vrste = response.data;
		 $scope.vrsteSize = $scope.vrste.length;
	 
	});
	
	$scope.kreirajStopuPDVa = function(){
		stopaPDVaService.createStopu($scope.stopa).then(function(response){
			alert("dodato");
			
		});
	}
	
}]);