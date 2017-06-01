app.controller('pregledCenovnikaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService){

	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
		 $scope.itemsSize = $scope.items.length;
	 
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
		
		$scope.dodajCenovnik = function(){
			
			$location.path("/cenovnik");
		}
		
		$scope.onSelectEvent = function() {
			if ($scope.selected[0] === undefined) {
				$scope.cenovnici = [];
			}
			else{
				$scope.cenovnici = $scope.selected[0].cenovnici;
			}
			}
		
		$scope.selected = [];
		$scope.bpselected = [];
}]);