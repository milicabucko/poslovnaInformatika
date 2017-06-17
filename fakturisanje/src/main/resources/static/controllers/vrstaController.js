app.controller('vrstaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService', 'vrstaPDVaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService, vrstaPDVaService){
	
	stopaPDVaService.getAllStope().then(function(response){
		 
		 $scope.stope = response.data;
		 $scope.stopeSize = $scope.stope.length;
	 
	});
	
	
	var urlParams = $location.search();
	var idVrste = urlParams.vrstaID;
	
	if(idVrste != null){
		vrstaPDVaService.findVrstu(idStope).then(function(response){
			$scope.vrstaa = response.data;
		});
	}
	
	
	$scope.kreirajVrstuPDVa = function(){
		if(!("undefined" === typeof $scope.vrstaa)){
			$scopa.vrsta.id = $scope.vrstaa.id;
			var stopaID = $scope.vrsta.stopaPDVa.id;
			vrstaPDVaService.updateVrstu(vrsta, stopaID),then(function(response){
				$location.path('/pregledVrstiPDVa');
			});
			
		}else{
		var stopaZaSlanje = $scope.vrsta.stopaPDVa.id;
		vrstaPDVaService.createVrstu($scope.vrsta, stopaZaSlanje).then(function(response){
			$location.path('/pregledVrstiPDVa');
			
		});
	}
	}
	
}]);