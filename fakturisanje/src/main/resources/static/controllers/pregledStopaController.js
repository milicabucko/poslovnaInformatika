app.controller('pregledStopaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', 'stopaPDVaService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService, stopaPDVaService){

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
		
		
		
		
		$scope.selected = [];
		$scope.selectedStopa = [];
		$scope.stopeSize = 0;
}]);