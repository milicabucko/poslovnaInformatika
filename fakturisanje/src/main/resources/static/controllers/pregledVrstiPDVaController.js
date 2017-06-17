app.controller('pregledVrstiPDVaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'vrstaPDVaService','authenticationService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, vrstaPDVaService, authenticationService){

	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
		 $scope.itemsSize = $scope.items.length;
	 
	});
	
	vrstaPDVaService.getAllVrste().then(function(response){
		 
		 $scope.vrste = response.data;
		 $scope.vrsteSize = $scope.vrste.length;
	 
	});
	
	$scope.pretraziPoImenu = function() {

		if($scope.imeZaPretragu == "") {
			companyService.getAllCompanies().then(function(response){ 
				 $scope.items = response.data;
				 $scope.itemsSize = $scope.items.length;
			});
		}
		else {	
			companyService.findByNameContaining($scope.imeZaPretragu).then(function(response){ 
				$scope.items = response.data;
				$scope.itemsSize = $scope.items.length;
			});
		}
	}
	
	$scope.options = {
			//autoSelect: true,
			boundaryLinks: true,
			//largeEditDialog: true,
			//pageSelector: true,
			rowSelection: true
		};
		
		$scope.query = {
				    order: 'name',
				    limit: 5,
				    page: 1
		};
		
		$scope.queryStavke = {
			    order: 'naziv',
			    limit: 5,
			    page: 1
		};
		
		$scope.dodajVrstu = function(){
			
			$location.path("/vrsta");
		}
		
//		$scope.onSelectEvent = function() {
//			if ($scope.selected[0] === undefined) {
//				$scope.stope = [];
//				$scope.stopeSize = 0;
//			}
//			else{
//				$scope.stope = $scope.selected[0].stope;
//				$scope.stopeSize = $scope.stope.length;
//			}
//		}
		
		$scope.obrisiVrstu = function(){
			console.log($scope.selectedVrsta[0].id);
			vrstaPDVaService.removeVrstu($scope.selectedVrsta[0].id).then(function(response){
				$scope.vrste = response.data;
				$scope.vrsteSize = $scope.vrste.length;
			});
		}
		
		$scope.izmeniVrstu = function(vrsta){
			$location.path("/vrsta").search({vrstaID:$scope.selectedVrsta[0].id })
		}
		
		$scope.goToHome = function(){
			$location.path('/home');
		}
		
		$scope.selected = [];
		$scope.selectedVrsta = [];
		$scope.vrsteSize = 0;
}]);