app.controller('stopaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService', 'vrstaPDVaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService, vrstaPDVaService){
	
	vrstaPDVaService.getAllVrste().then(function(response){
		 
		 $scope.vrste = response.data;
		 $scope.vrsteSize = $scope.vrste.length;
	 
	});
	
	$scope.kreirajStopuPDVa = function(){
		console.log($scope.stopa.vrstaPDVa.id);
		var id = $scope.stopa.vrstaPDVa.id;
		stopaPDVaService.createStopu($scope.stopa,id).then(function(response){
			alert("dodato");
			
		});
	}
	
}]);