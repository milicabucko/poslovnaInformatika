app.controller('pregledPreduzecaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService){
	
	$scope.onSelectEvent = function() {
		console.log('selektovano: ' + $scope.selected[0].id);
		if ($scope.selected[0] === undefined) {
			$scope.businessPartners = [];
		}
		else {
			businessPartnerService.getAllBusinessPartners($scope.selected[0].id).then(function(response){
				console.log(response.data[0].company2);
				$scope.businessPartners = response.data;
					
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
	
	$scope.selected = [];
	$scope.bpselected = [];
	

	
	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
	 
	});
	
	$scope.kreirajPartnera = function(preduzeceId){
		businessPartnerService.aktivnaFirma = preduzeceId;
		$location.path("/listaSvihPreduzecaPP");
	}
	
	
	
	



}]);

