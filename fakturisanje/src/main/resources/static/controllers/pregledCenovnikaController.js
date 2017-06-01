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
				$scope.cenovniciSize = 0;
			}
			else{
				$scope.cenovnici = $scope.selected[0].cenovnici;
				$scope.cenovniciSize = $scope.cenovnici.length;
			}
		}
		
		$scope.onSelectEventCenovnik = function() {
			if ($scope.selectedCenovnik[0] === undefined) {
				$scope.stavke = [];
				$scope.stavkeSize = 0;
			}
			else{
				console.log($scope.selectedCenovnik[0].stavkecenovnika[0]);
				$scope.stavke = $scope.selectedCenovnik[0].stavkecenovnika;
				$scope.stavkeSize = $scope.stavke.length;
			}
		}
		
		$scope.selected = [];
		$scope.selectedCenovnik = [];
		$scope.cenovniciSize = 0;
		$scope.selectedStavka = [];
		$scope.stavkeSize = 0;
}]);