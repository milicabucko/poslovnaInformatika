app.controller('pregledGrupaController',['$scope', '$location', '$mdDialog', 'companyService','grupaArtikalaService','artikalService', function($scope, $location, $mdDialog, companyService, grupaArtikalaService, artikalService){

		
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
		
		$scope.prikaziGrupe = function() {
			if ($scope.selected[0] === undefined) {
				$scope.grupe = [];
				$scope.grupeSize = 0;
			}
			else{
				//$scope.grupe = $scope.selected[0].grupe;
				var id = $scope.selected[0].id;
				grupaArtikalaService.getAllGrupe(id).then(function(response){
					 console.log(response.data);
					 $scope.grupe = response.data;
					 $scope.grupeSize = $scope.grupe.length;
				 
				});
				$scope.grupeSize = $scope.grupe.length;
			}
		}
		
		$scope.prikaziArtikle = function() {
			if ($scope.selectedGrupa[0] === undefined) {
				$scope.artikli = [];
				$scope.artikliSize = 0;
			}
			else{
				//$scope.grupe = $scope.selected[0].grupe;
				var idGrupe = $scope.selectedGrupa[0].id;
				artikalService.getAllArtikle(idGrupe).then(function(response){
					 console.log(response.data);
					 $scope.artikli = response.data;
					 $scope.artikliSize = $scope.artikli.length;
				 
				});
				$scope.artikliSize = $scope.artikli.length;
			}
		}
		
		
			$scope.dodajArtikal = function(){
			
			$location.path("/artikal").search({grupaID:  $scope.selectedGrupa[0].id, companyID: $scope.selected[0].id})
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