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
		
		
		
		businessPartnerService.kreirajPartnera(businessPartnerService.aktivnaFirma,$scope.selected[0].id).then(function(response){
			
			if(response.data.company1 == null){
					    $mdDialog.show(
					      $mdDialog.alert()
					        .clickOutsideToClose(true)
					        .title('Greska!')
					        .textContent('Partnerstvo vec postoji!')
					        .ok('Ok!')
					    );
			}
			
		});
		
	}

}]);