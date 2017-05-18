app.controller('fakturaController',['$scope', '$location', '$mdDialog', 'companyService', 'businessPartnerService', function($scope, $location, $mdDialog, companyService, businessPartnerService){

	$scope.onSelectBPEvent = function() {
		if ($scope.bpselected[0] === undefined) {
			$scope.knaziv = ""; 
			$scope.kadresa = ""; 
			$scope.kpib = ""; 
			$scope.kmbr = ""; 
			$scope.kracun = ""; 
		}
		else {
			
			$scope.knaziv = $scope.bpselected[0].name; 
			$scope.kadresa = $scope.bpselected[0].address;
			$scope.kpib = $scope.bpselected[0].pib;
			$scope.kmbr = $scope.bpselected[0].cidnumber;
			$scope.kracun = $scope.bpselected[0].account;
			
		}
	}
	
	
	$scope.onSelectEvent = function() {
		if ($scope.selected[0] === undefined) {
			$scope.businessPartners = [];
			
			$scope.naziv = ""; 
			$scope.adresa = ""; 
			$scope.pib = ""; 
			$scope.mbr = ""; 
			$scope.racun = ""; 
			
			$scope.knaziv = ""; 
			$scope.kadresa = ""; 
			$scope.kpib = ""; 
			$scope.kmbr = ""; 
			$scope.kracun = ""; 
		}
		else {
			
			$scope.naziv = $scope.selected[0].name; 
			$scope.adresa = $scope.selected[0].address;
			$scope.pib = $scope.selected[0].pib;
			$scope.mbr = $scope.selected[0].cidnumber;
			$scope.racun = $scope.selected[0].account;
			$scope.businessPartners = $scope.selected[0].businessPartners;
			
			$scope.knaziv = ""; 
			$scope.kadresa = ""; 
			$scope.kpib = ""; 
			$scope.kmbr = ""; 
			$scope.kracun = ""; 
			
		}
	}
	
	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
	 
	});
	
	
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
		
		$scope.selected = [];
		$scope.bpselected = [];
		
		
	 
}]);