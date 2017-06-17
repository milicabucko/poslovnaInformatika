app.controller('pregledStopaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService','authenticationService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService, authenticationService){

	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
	
	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
		 $scope.itemsSize = $scope.items.length;
	 
	});
	
	stopaPDVaService.getAllStope().then(function(response){
		 
		 $scope.stope = response.data;
		 $scope.stopeSize = $scope.stope.length;
	 
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
		
		$scope.dodajStopu = function(){
			
			$location.path("/stopa");
		} 
		
		$scope.izmeniStopu = function(stopa){
			$location.path("/stopa").search({stopaID:$scope.selectedStopa[0].id })
		}
		
		
		$scope.obrisiStopu = function(stopa){
			var id = $scope.selectedStopa[0].id;
			stopaPDVaService.removeStopu(id).then(function(response){
				$scope.stope = response.data;
				$scope.stopeSize = $scope.stope.length;
			});
		}
		
		$scope.goToHome = function(){
			$location.path('/home');
		}
		
		
		
		
		$scope.selected = [];
		$scope.selectedStopa = [];
		$scope.stopeSize = 0;
}]);