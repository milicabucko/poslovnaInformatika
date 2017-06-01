app.controller('listaSvihPreduzecaPPController',['$scope', '$location', '$mdDialog', 'companyService','businessPartnerService', function($scope, $location, $mdDialog, companyService, businessPartnerService){

	/*companyService.getAllCompaniesExceptCurrent().then(function(response){
		 
		 $scope.items = response.data;
	 
	});*/
	
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
	

	$scope.kreirajPartnera = function(){
		
		var naziv = $scope.name;
		var pib = $scope.pib;
		var adresa = $scope.address;
		var maticniBroj = $scope.cidnumber;
		var email = $scope.email;
		var tekuciRacun = $scope.account;
		
		alert(businessPartnerService.aktivnaFirma + "id firme")
		alert($scope.selected[0].id + "id firme2")
		businessPartnerService.kreirajPartnera(businessPartnerService.aktivnaFirma,$scope.selected[0].id).then(function(response){
			
			alert("kreiraj partnera")
		});
		
	}

}]);