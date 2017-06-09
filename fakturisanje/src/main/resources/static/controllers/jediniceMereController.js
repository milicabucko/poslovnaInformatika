app.controller('pregledJedinicaMereController',['$scope', '$location', '$mdDialog', 'companyService', 'jediniceMereService', function($scope, $location, $mdDialog, companyService, jediniceMereService){

		
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
		
		$scope.prikaziJedinice = function() {
			if ($scope.selected[0] === undefined) {
				$scope.jedinice = [];
				$scope.jediniceSize = 0;
			}
			else{
				//$scope.grupe = $scope.selected[0].grupe;
				var id = $scope.selected[0].id;
				jediniceMereService.getAllJedinice(id).then(function(response){
					$scope.jedinice = response.data;
				});
				$scope.jediniceSize = $scope.jedinice.length;
			}
		}
		
		$scope.dodajJedinicu = function(){
			$location.path("/jedinicaMere");
		}
		
		$scope.kreirajJedinicuMere = function(){
			jediniceMereService.createJedinicu($scope.jedinica).then(function(response){
				 alert("dodato");
			 
			});
		}
		
	
		
		
		$scope.stavke = [];
		$scope.stavkeSize = 0;
		$scope.businessPartnersSize = 0;
		$scope.omogucenoDodavanje = false;
		$scope.omogucenaIzmena = false;
		$scope.omogucenoBrisanje = false;

		
		$scope.selectedGrupa = [];
		$scope.selected = [];


}]);