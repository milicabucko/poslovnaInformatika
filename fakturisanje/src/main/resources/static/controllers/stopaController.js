app.controller('stopaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService', 'vrstaPDVaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService, vrstaPDVaService){
	
	vrstaPDVaService.getAllVrste().then(function(response){
		 
		 $scope.vrste = response.data;
		 $scope.vrsteSize = $scope.vrste.length;
	 
	});
	
	var urlParams = $location.search();
	var idStope = urlParams.stopaID;
	
	if(idStope != null){
		stopaPDVaService.findStopu(idStope).then(function(response){
			$scope.stopaa = response.data;
		});
	}
	
	$scope.dpChanged = function() {
		var danasnjiDatum = new Date();
		
			if ($scope.stopa.datumStope < danasnjiDatum ) {
				$scope.stopa.datumStope = null;
					$mdDialog.show(
							$mdDialog.alert()
							.clickOutsideToClose(true)
							.title('Greska!')
							.textContent('Datum ne moze biti manji od danasnjeg!')
							.ok('Ok!')
					);
			}
		
	}
	
	
	$scope.kreirajStopuPDVa = function(){
		if(!("undefined" === typeof $scope.stopaa)){
			$scope.stopa.id = $scope.stopaa.id;
			stopaPDVaService.updateStopu($scope.stopa).then(function(response){
				$location.path('/pregledStopa');
			});
		}
		else{
		stopaPDVaService.createStopu($scope.stopa).then(function(response){
			$location.path('/pregledStopa');
			
		});
	}
	}
	
}]);