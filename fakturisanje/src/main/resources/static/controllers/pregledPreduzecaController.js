app.controller('pregledPreduzecaController',['$scope', '$location', '$mdDialog', 'companyService', '$timeout','businessPartnerService', function($scope, $location, $mdDialog, companyService, $timeout, businessPartnerService){

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
	
	
	
	$scope.ispisiNesto = function() {
		if($scope.selected === undefined) {
		
		}
		else {
			$scope.businessPartners = $scope.selected["0"].businessPartners;
		}
		console.log($scope.selected[0]);
		
		/*if($scope.selected[0].id != "undefined"){
			
			businessPartnerService.getAllBusinessPartners($scope.selected[0].id).then(function(response){
				
				$scope.businessPartners = response.data;
				
			});
			
		}*/
		

	}
	
	companyService.getAllCompanies().then(function(response){
		 
		 $scope.items = response.data;
	 
	});
	
	
	
	



}]);

