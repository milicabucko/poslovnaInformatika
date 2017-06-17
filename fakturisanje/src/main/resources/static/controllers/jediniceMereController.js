app.controller('pregledJedinicaMereController',['$scope', '$location', '$mdDialog','$routeParams', 'companyService', 'jediniceMereService','authenticationService', function($scope, $location, $mdDialog, $routeParams, companyService, jediniceMereService, authenticationService){

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
		
		jediniceMereService.getAllJedinice($scope.user.company.id).then(function(response){
			$scope.jedinice = response.data;
		});
		
		$scope.dodajJedinicu = function(){
			$location.path("/jedinicaMere").search({companyId:  $scope.selected[0].id })
		}
		
		$scope.obrisiJedinicu = function(jedinica){
			var idJedinice = $scope.selectedJedinica[0].id;
			jediniceMereService.deleteJedinica(idJedinice).then(function(response){
				$scope.jedinice = response.data
				$scope.jediniceSize = $scope.jedinice.length;
			});
		}
		
		$scope.izmeniJedinicu = function(jedinica){
			$location.path("/jedinicaMere").search({jedinicaID:$scope.selectedJedinica[0].id })
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

		
		$scope.selectedJedinica = [];
		$scope.selected = [];


}]);