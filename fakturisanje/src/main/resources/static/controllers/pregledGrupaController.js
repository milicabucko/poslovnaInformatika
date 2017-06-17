app.controller('pregledGrupaController',['$scope', '$location', '$mdDialog', 'companyService','grupaArtikalaService','artikalService','authenticationService', function($scope, $location, $mdDialog, companyService, grupaArtikalaService, artikalService, authenticationService){

	$scope.user = authenticationService.getUser();
	$scope.authService = authenticationService;
		
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
		
		grupaArtikalaService.getAllGrupe($scope.user.company.id).then(function(response){
			 console.log(response.data);
			 $scope.grupe = response.data;
			 $scope.grupeSize = $scope.grupe.length;
		 
		});
		
		$scope.dodajGrupu = function(){
			$location.path("/grupa").search({companyID:$scope.user.company.id })
		}
		
		$scope.prikaziArtikle = function() {
			console.log($scope.selectedGrupa[0].id);
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
			}
		}
		
		$scope.obrisiGrupu = function(grupa){
			var idToDelete = $scope.selectedGrupa[0].id;
			grupaArtikalaService.deleteGrupu(idToDelete).then(function(response){
				$scope.grupe = response.data;
				$scope.grupeSize = $scope.grupe.length;
				$scope.artikli = [];
				$scope.artikliSize = 0;
			});
		}
		
		$scope.obrisiArtikal = function(artikal){
			var idArtikla = $scope.selectedArtikal[0].id;
			artikalService.deleteArtikal(idArtikla).then(function(response){
				$scope.artikli = response.data
				$scope.artikliSize = $scope.artikli.length;
			});
		}
		
		$scope.izmeniArtikal = function(artikal){
			$location.path("/artikal").search({artikalID:$scope.selectedArtikal[0].id})
		}
		
		$scope.izmeniGrupu = function(grupa){
			$location.path("/grupa").search({grupaID:$scope.selectedGrupa[0].id })
		}
		
		
			$scope.dodajArtikal = function(){
			
			$location.path("/artikal").search({grupaID:  $scope.selectedGrupa[0].id, companyID: $scope.user.company.id})
		}
		
	
			$scope.goToHome = function(){
				$location.path('/home');
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